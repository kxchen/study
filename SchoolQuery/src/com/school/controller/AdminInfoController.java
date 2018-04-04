package com.school.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.school.dto.AdminInfo;
import com.school.service.AdminInfoService;

/**
 * Servlet implementation class AdminInfoController
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
		HttpSession session = request.getSession();
		try {
			String operate = request.getParameter("operate");
			if (operate.equals("login")) {
				login(request, response);
			} else if ((AdminInfo) session.getAttribute("adminInfo") != null) {
				if (operate.equals("create")) {
					create(request, response);
				} else if (operate.equals("changePsw")) {
					changePsw(request, response);
				} else if (operate.equals("save")) {
					save(request, response);
				} else if (operate.equals("list")) {
					list(request, response);
				} else if (operate.equals("edit")) {
					edit(request, response);
				} else if (operate.equals("update")) {
					update(request, response);
				} else if (operate.equals("show")) {
					show(request, response);
				} else if (operate.equals("delete")) {
					delete(request, response);
				} else if (operate.equals("search")) {
					search(request, response);
				} else if (operate.equals("loginOut")) {
					loginOut(request, response);
				}
			} else {
				request.setAttribute("msg", "请登录");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void changePsw(HttpServletRequest request, HttpServletResponse response) {
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
				request.getRequestDispatcher("adminInfo/changePsw.jsp").forward(request, response);
			} else {
				if (adminInfoService.update(adminInfoInstance)) {
					request.setAttribute("msg", "修改密码成功！");
					request.getRequestDispatcher("admin/default.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "修改密码失败");
					request.getRequestDispatcher("adminInfo/changePsw.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void show(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("adminId").trim());
		AdminInfoService adminInfoService = null;
		AdminInfo adminInfoInstance = null;
		try {
			adminInfoInstance = new AdminInfo();
			adminInfoService = new AdminInfoService();
			adminInfoInstance = adminInfoService.get(id);
			request.setAttribute("adminInfo", adminInfoInstance);
			request.getRequestDispatcher("adminInfo/show.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		AdminInfoService adminInfoService = null;
		List<AdminInfo> adminInfoListByAccount = null;
		List<AdminInfo> adminInfoListByName = null;
		try {
			adminInfoListByName = new ArrayList<AdminInfo>();
			adminInfoListByAccount = new ArrayList<AdminInfo>();
			adminInfoService = new AdminInfoService();
			String account = request.getParameter("account");
			if (account == null || account.equals("")) {
				request.setAttribute("searchMsg", "请输入用户名或姓名关键字");
				list(request, response);
				// request.getRequestDispatcher("adminInfo/list.jsp").forward(request,
				// response);
			} else {
				adminInfoListByAccount = adminInfoService.findByAccount(account);
				adminInfoListByName = adminInfoService.findByName(account);
				if (adminInfoListByAccount != null || adminInfoListByName != null) {
					request.setAttribute("searchMsg", "搜索到得数据为：");
					request.setAttribute("adminInfoListByAccount", adminInfoListByAccount);
					request.setAttribute("adminInfoListByName", adminInfoListByName);
					request.getRequestDispatcher("adminInfo/searchResult.jsp").forward(request, response);
				} else {
					request.setAttribute("searchMsg", "没有搜索到数据！");
					list(request, response);
					// request.getRequestDispatcher("adminInfo/list.jsp").forward(request,
					// response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("adminId").trim());
		AdminInfoService adminInfoService = null;
		try {
			adminInfoService = new AdminInfoService();
			if (adminInfoService.delete(id)) {
				request.setAttribute("msg", "删除成功！");
				list(request, response);
				// request.getRequestDispatcher("adminInfo/list.jsp").forward(request,
				// response);
			} else {
				request.setAttribute("msg", "删除管理员失败");
				list(request, response);
				// request.getRequestDispatcher("adminInfo/list.jsp").forward(request,
				// response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("adminId").trim());
		AdminInfoService adminInfoService = null;
		AdminInfo adminInfoInstance = null;
		try {
			adminInfoInstance = new AdminInfo();
			adminInfoService = new AdminInfoService();
			adminInfoInstance = adminInfoService.get(id);
			request.setAttribute("adminInfo", adminInfoInstance);
			request.getRequestDispatcher("adminInfo/update.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		AdminInfoService adminInfoService = null;
		AdminInfo adminInfoInstance = null;
		StringBuffer sbuffer = null;
		try {
			adminInfoService = new AdminInfoService();
			sbuffer = new StringBuffer();
			adminInfoInstance = new AdminInfo();
			int id = Integer.parseInt(request.getParameter("id"));
			adminInfoInstance = adminInfoService.get(id);
			String adminName = request.getParameter("adminName");
			if (adminName == null || adminName.equals("")) {
				sbuffer.append("您的姓名不能为空！<br/>");
			} else {
				adminInfoInstance.setAdminName(adminName);
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				request.setAttribute("adminInfo", adminInfoInstance);
				request.getRequestDispatcher("adminInfo/update.jsp").forward(request, response);
			} else {
				if (adminInfoService.update(adminInfoInstance)) {
					request.setAttribute("msg", "修改管理员成功！");
					list(request, response);
					// request.getRequestDispatcher("adminInfo/list.jsp").forward(request,
					// response);
				} else {
					request.setAttribute("msg", "修改管理员失败");
					request.setAttribute("adminInfo", adminInfoInstance);
					request.getRequestDispatcher("adminInfo/update.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void list(HttpServletRequest request, HttpServletResponse response) {
		AdminInfoService adminInfoService = null;
		List<AdminInfo> adminInfoList = null;
		try {
			adminInfoList = new ArrayList<AdminInfo>();
			adminInfoService = new AdminInfoService();
			adminInfoList = adminInfoService.list();
			request.setAttribute("adminInfoList", adminInfoList);
			request.getRequestDispatcher("adminInfo/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void save(HttpServletRequest request, HttpServletResponse response) {
		AdminInfoService adminInfoService = null;
		StringBuffer sbuffer = null;
		AdminInfo adminInfoInstance = null;
		try {
			adminInfoService = new AdminInfoService();
			sbuffer = new StringBuffer();
			adminInfoInstance = new AdminInfo();
			String account = request.getParameter("account");
			if (account == null || account.equals("")) {
				sbuffer.append("您的登录名不能为空！<br/>");
			} else {
				adminInfoInstance.setAccount(account);
			}
			String password = request.getParameter("password");
			if (password == null || password.equals("")) {
				sbuffer.append("您的密码名不能为空！<br/>");
			} else {
				adminInfoInstance.setPassword(password);
			}
			String adminName = request.getParameter("adminName");
			if (adminName == null || adminName.equals("")) {
				sbuffer.append("您的姓名不能为空！<br/>");
			} else {
				adminInfoInstance.setAdminName(adminName);
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("adminInfo/add.jsp").forward(request, response);
			} else {
				if (adminInfoService.save(adminInfoInstance)) {
					request.setAttribute("msg", "添加成功！");
					list(request, response);
					// request.getRequestDispatcher("adminInfo/list.jsp").forward(request,
					// response);
				} else {
					request.setAttribute("msg", "添加管理员失败");
					request.getRequestDispatcher("adminInfo/add.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void create(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/adminInfo/add.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();// 获取session
		AdminInfoService adminInfoService = null;
		StringBuffer sbuffer = null;
		AdminInfo adminInfoInstance = null;
		try {
			adminInfoService = new AdminInfoService();
			sbuffer = new StringBuffer();
			adminInfoInstance = new AdminInfo();
			String account = request.getParameter("account");
			if (account == null || account.equals("")) {
				sbuffer.append("您的登录名不能为空！<br/>");
			} else {
				adminInfoInstance.setAccount(account);
			}
			String password = request.getParameter("password");
			if (password == null || password.equals("")) {
				sbuffer.append("您的密码名不能为空！<br/>");
			} else {
				adminInfoInstance.setPassword(password);
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				adminInfoInstance = adminInfoService.login(adminInfoInstance);
				if (adminInfoInstance != null) {
					Cookie cookie = new Cookie("account", account);// 创建Cookie
					cookie.setMaxAge(60 * 60 * 24);// 设置cookie命长为1天
					response.addCookie(cookie);// 保存cookie
					session.setAttribute("adminInfo", adminInfoInstance);// 向session域中保存
					request.getRequestDispatcher("/admin/default.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "用户名或密码错误");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loginOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();// 获取session
		try {
			if (session != null) {
				session.removeAttribute("adminInfo");
				request.setAttribute("msg", "请登录");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
