package com.czesou.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.czesou.pojo.Department;
import com.czesou.service.AuthorityService;
import com.czesou.service.DepartmentService;
import com.czesou.service.UserService;

/**
 * Servlet implementation class DepartmentAction
 */
public class DepartmentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DepartmentAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		AuthorityService authorityService = null;
		try {

			String roleAu = (String) session.getAttribute("listUrl");
			authorityService = new AuthorityService();
			String authority = authorityService.listAuthorityUrl("");
			String operate = request.getParameter("operate");
			if (session.getAttribute("userInfo") == null || session.getAttribute("listUrl") == null) {
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
			if (authority.indexOf(operate) != -1) {
				if (roleAu.indexOf(operate) == -1) {
					response.sendRedirect(request.getContextPath() + "/error.jsp");
					return;
				}
			}
			if (operate.equals("addDep"))
				addDep(request, response);
			else if (operate.equals("doAddDep"))
				doAddDep(request, response);
			else if (operate.equals("deleteDep"))
				deleteDep(request, response);
			else if (operate.equals("updateDep"))
				updateDep(request, response);
			else if (operate.equals("doUpdateDep"))
				doUpdateDep(request, response);
			else if (operate.equals("listDep"))
				listDep(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void listDep(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {

			request.getRequestDispatcher("list_dep.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void doUpdateDep(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("更新部门");
		DepartmentService departmentService = null;
		Department department = null;
		String result = "{\"state\":\"error\"}";
		try {
			departmentService = new DepartmentService();
			department = new Department();
			department = departmentService.get(Integer.parseInt(request.getParameter("departmentId")));
			if (departmentService.get(request.getParameter("departmentName")) != null
					&& !department.equals(departmentService.get(request.getParameter("departmentName")))) {
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.write("{\"state\":\"部门名已存在\"}");
				return;
			}
			department.setDepartmentName(request.getParameter("departmentName"));

			if (departmentService.update(department)) {
				result = "{\"state\":\"success\"}";
			}
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void updateDep(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DepartmentService departmentService = null;
		try {
			departmentService = new DepartmentService();
			request.setAttribute("department",
					departmentService.get(Integer.parseInt(request.getParameter("departmentId"))));
			request.getRequestDispatcher("systemManage/update_dep.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void deleteDep(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DepartmentService departmentService = null;
		UserService userService = null;
		try {
			userService = new UserService();
			departmentService = new DepartmentService();
			String result = "{\"state\":\"error\"}";
			if (userService
					.getCount("where department_id = " + Integer.parseInt(request.getParameter("departmentId"))) < 1) {
				if (departmentService
						.delete(departmentService.get(Integer.parseInt(request.getParameter("departmentId"))))) {
					result = "{\"state\":\"success\"}";
				}
			} else
				result = "{\"state\":\"存在当前部门用户！\"}";
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void doAddDep(HttpServletRequest request, HttpServletResponse response) {
		Department department = null;
		DepartmentService departmentService = null;
		try {
			String result = "{\"state\":\"error\"}";
			department = new Department();
			departmentService = new DepartmentService();
			if (departmentService.get(request.getParameter("departmentName")) != null) {
				result = "{\"state\":\"部门名称已经存在\"}";
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.write(result);
				return;
			}
			department.setDepartmentName(request.getParameter("departmentName"));

			if (departmentService.save(department)) {
				result = "{\"state\":\"success\"}";
			}
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void addDep(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			request.getRequestDispatcher("systemManage/add_dep.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

}
