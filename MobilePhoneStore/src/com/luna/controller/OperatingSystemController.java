package com.luna.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.luna.dto.OperatingSystem;
import com.luna.service.OperatingSystemService;

/**
 * Servlet implementation class OperatingSystemController
 */
public class OperatingSystemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OperatingSystemController() {
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
		try {
			String operate = request.getParameter("operate");
			if (operate.equals("save")) {
				save(request, response);
			} else if (operate.equals("list")) {
				list(request, response);
			} else if (operate.equals("update")) {
				update(request, response);
			} else if (operate.equals("delete")) {
				delete(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("opeSystemId").trim());
		OperatingSystemService operatingSystemService = null;
		try {
			operatingSystemService = new OperatingSystemService();
			if (operatingSystemService.delete(operatingSystemService.get(id))) {
				request.setAttribute("msg", "删除成功！");
				list(request, response);
			} else {
				request.setAttribute("msg", "删除失败");
				list(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OperatingSystemService operatingSystemService = null;
		StringBuffer sbuffer = null;
		OperatingSystem operatingSystemInstance = null;
		try {
			operatingSystemService = new OperatingSystemService();
			sbuffer = new StringBuffer();
			operatingSystemInstance = new OperatingSystem();
			int id = Integer.parseInt(request.getParameter("opeSystemId").trim());
			operatingSystemInstance = operatingSystemService.get(id);
			String opeSystemName = request.getParameter("opeSystemName");
			if (opeSystemName == null || opeSystemName.equals("")) {
				sbuffer.append("操作系统不能为空！<br/>");
			} else {
				operatingSystemInstance.setOpeSystemName(opeSystemName);
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				// request.setAttribute("operatingSystem",
				// operatingSystemInstance);
				// request.getRequestDispatcher("/mobilePhone/category.jsp").forward(request,
				// response);
				list(request, response);
			} else {
				if (operatingSystemService.update(operatingSystemInstance)) {
					request.setAttribute("msg", "修改成功！");
					list(request, response);
				} else {
					request.setAttribute("msg", "修改失败！");
					// request.setAttribute("operatingSystem",
					// operatingSystemInstance);
					// request.getRequestDispatcher("/mobilePhone/category.jsp").forward(request,
					// response);
					list(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OperatingSystemService operatingSystemService = null;
		List<OperatingSystem> operatingSystemList = null;
		try {
			operatingSystemList = new ArrayList<OperatingSystem>();
			operatingSystemService = new OperatingSystemService();
			operatingSystemList = operatingSystemService.list();
			request.setAttribute("operatingSystemList", operatingSystemList);
			request.getRequestDispatcher("/category/operatingSystem.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OperatingSystemService operatingSystemService = null;
		StringBuffer sbuffer = null;
		OperatingSystem operatingSystemInstance = null;
		try {
			operatingSystemService = new OperatingSystemService();
			sbuffer = new StringBuffer();
			operatingSystemInstance = new OperatingSystem();
			String opeSystemName = request.getParameter("opeSystemName");
			if (opeSystemName == null || opeSystemName.equals("")) {
				sbuffer.append("操作系统不能为空！<br/>");
			} else {
				operatingSystemInstance.setOpeSystemName(opeSystemName);
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				// request.getRequestDispatcher("/mobilePhone/category.jsp").forward(request,
				// response);
				list(request, response);
			} else {
				if (operatingSystemService.save(operatingSystemInstance)) {
					request.setAttribute("msg", "添加成功");
					// request.getRequestDispatcher("/mobilePhone/category.jsp").forward(request,
					// response);
					list(request, response);
				} else {
					request.setAttribute("msg", "添加失败！");
					// request.getRequestDispatcher("/mobilePhone/category.jsp").forward(request,
					// response);
					list(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

}
