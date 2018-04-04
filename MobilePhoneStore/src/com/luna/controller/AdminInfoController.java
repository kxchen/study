package com.luna.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.luna.dto.AdminInfo;
import com.luna.service.AdminInfoService;

/**
 * Servlet implementation class AdminInfoController
 */
/**
 * @ClassName: AdminInfoController
 * @Description: 管理员Servlet
 * @author c-kx@outlook.com
 * @date 2015年12月17日 上午11:25:58
 * 
 */
public class AdminInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminInfoController() {
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
			if (operate.equals("login")) {
				login(request, response);
			} else if (operate.equals("save")) {
				save(request, response);
			} else if (operate.equals("list")) {
				list(request, response);
			} else if (operate.equals("update")) {
				update(request, response);
			} else if (operate.equals("delete")) {
				delete(request, response);
			} else if (operate.equals("quit")) {
				quit(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	/**
	 * @Description: 更新管理员
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		AdminInfo adminInfoInstance = null;
		AdminInfoService adminInfoService = null;
		StringBuffer sbuffer = null;
		try {
			adminInfoInstance = (AdminInfo) session.getAttribute("adminInfo");
			adminInfoService = new AdminInfoService();
			sbuffer = new StringBuffer();
			String password = request.getParameter("password");
			if (password == null || password.equals("")) {
				sbuffer.append("旧密码不能为空！<br/>");
			}
			if (adminInfoInstance.getPassword().equals(password)) {
				String newPassword1 = request.getParameter("newPassword1");
				String newPassword2 = request.getParameter("newPassword2");
				if (newPassword1 == null || newPassword1.equals("")) {
					sbuffer.append("新密码不能为空！<br/>");
				}
				if (newPassword2 == null || newPassword2.equals("")) {
					sbuffer.append("再次输入新密码不能为空！<br/>");
				}
				if (newPassword1.equals(newPassword2)) {
					adminInfoInstance.setPassword(newPassword2);
				} else {
					sbuffer.append("两次输入的新密码不同！<br/>");
				}
			} else {
				sbuffer.append("旧密码输入错误！<br/>");
			}

			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("admin/update.jsp").forward(request, response);
			} else {
				if (adminInfoService.update(adminInfoInstance)) {
					request.setAttribute("msg", "修改密码成功！");
					request.getRequestDispatcher("admin/login.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "修改密码失败");
					request.getRequestDispatcher("admin/update.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	/**
	 * @Description:添加管理员
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminInfoService adminInfoService = null;
		StringBuffer sbuffer = null;
		AdminInfo adminInfoInstance = null;
		try {
			adminInfoService = new AdminInfoService();
			sbuffer = new StringBuffer();
			adminInfoInstance = new AdminInfo();
			String adminName = request.getParameter("adminName");
			if (adminName == null || adminName.equals(" ")) {
				sbuffer.append("您的用户名不能为空！<br/>");
			} else {
				adminInfoInstance.setAdminName(adminName);
			}
			String password = request.getParameter("password");
			if (password == null || password.equals("")) {
				sbuffer.append("您的密码不能为空！<br/>");
			} else {
				adminInfoInstance.setPassword(password);
			}

			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("/admin/save.jsp").forward(request, response);
			} else {
				if (adminInfoService.save(adminInfoInstance)) {
					request.setAttribute("msg", "添加成功");
					list(request, response);
					// request.getRequestDispatcher("/admin/list.jsp").forward(request,
					// response);
				} else {
					request.setAttribute("msg", "添加失败！");
					request.getRequestDispatcher("/admin/save.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminInfoService adminInfoService = null;
		List<AdminInfo> adminInfoList = null;
		try {
			adminInfoList = new ArrayList<AdminInfo>();
			adminInfoService = new AdminInfoService();
			adminInfoList = adminInfoService.list();
			request.setAttribute("adminInfoList", adminInfoList);
			request.getRequestDispatcher("/admin/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("adminId").trim());
		AdminInfoService adminInfoService = null;
		try {
			adminInfoService = new AdminInfoService();
			if (adminInfoService.delete(adminInfoService.get(id))) {
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

	private void quit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();// 获取session
		try {
			if (session != null) {
				session.removeAttribute("adminInfo");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		AdminInfoService adminInfoService = null;
		StringBuffer sbuffer = null;
		AdminInfo adminInfoInstance = null;
		String adminName = "";
		String password = "";
		try {
			adminInfoService = new AdminInfoService();
			sbuffer = new StringBuffer();
			adminInfoInstance = new AdminInfo();
			if (request.getParameter("adminName") == null || request.getParameter("adminName").equals("")) {
				sbuffer.append("您的登录名不能为空！<br/>");
			} else {
				adminName = request.getParameter("adminName");
			}
			if (request.getParameter("password") == null || request.getParameter("password").equals("")) {
				sbuffer.append("您的密码名不能为空！<br/>");
			} else {
				password = request.getParameter("password");
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				adminInfoInstance = adminInfoService.login(adminName, password);
				if (adminInfoInstance != null) {
					session.setAttribute("adminInfo", adminInfoInstance);// 向session域中保存
					response.sendRedirect(request.getContextPath() + "/MobilePhoneInfoController?operate=list");
					// request.getRequestDispatcher("/admin/index.jsp").forward(request,
					// response);
				} else {
					request.setAttribute("msg", "用户名或密码错误");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

}
