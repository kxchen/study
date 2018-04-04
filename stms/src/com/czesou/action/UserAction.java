package com.czesou.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.czesou.pojo.Authority;
import com.czesou.pojo.User;
import com.czesou.service.AuthorityService;
import com.czesou.service.DepartmentService;
import com.czesou.service.RoleAuthorityService;
import com.czesou.service.RoleService;
import com.czesou.service.UserService;
import com.luna.util.MD5;
import com.luna.util.Uuid;

/**
 * Servlet implementation class UserCation
 */
public class UserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAction() {
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
				if (operate.equals("login")) {
					login(request, response);
					return;
				}
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
			if (authority.indexOf(operate) != -1) {
				if (roleAu.indexOf(operate) == -1) {
					response.sendRedirect(request.getContextPath() + "/error.jsp");
					return;
				}
			}

			if (operate.equals("login"))
				login(request, response);
			else if (operate.equals("changePassword"))
				changePassword(request, response);
			else if (operate.equals("doChangePassword"))
				doChangePassword(request, response);
			else if (operate.equals("quit"))
				quit(request, response);
			else if (operate.equals("addUser"))
				addUser(request, response);
			else if (operate.equals("doAddUser"))
				doAddUser(request, response);
			else if (operate.equals("updateUser"))
				updateUser(request, response);
			else if (operate.equals("doUpdateUser"))
				doUpdateUser(request, response);
			else if (operate.equals("resetPassword"))
				resetPassword(request, response);
			else if (operate.equals("listUser"))
				listUser(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<User> userInfoList = null;
		UserService userService = null;
		int pageNo = 1;
		int pageSize = 10;
		int count = 0;
		String queryString = "";
		String search = "";
		try {
			userInfoList = new ArrayList<User>();
			userService = new UserService();
			if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals(""))
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			search = request.getParameter("search");
			if (search != null && !search.equals("")) {
				queryString = " where  user_name like '%" + search + "%' ";
			}
			userInfoList = userService.list(pageSize, pageNo, queryString);
			count = userService.getCount(queryString);

			request.setAttribute("userInfoList", userInfoList);
			request.setAttribute("search", search);
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", (int) Math.ceil((float) count / pageSize));
			request.getRequestDispatcher("user/list_user.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(request.getParameter("user_id"));
		User userInfo = null;
		UserService userService = null;
		try {
			userService = new UserService();
			userInfo = new User();
			userInfo = userService.get(request.getParameter("userId"));
			MD5 getMD5 = new MD5();
			String psw = getMD5.getMD5ofStr("123456");
			userInfo.setPassword(psw);
			String result = "{\"state\":\"error\"}";
			if (userService.update(userInfo)) {
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

	private void doUpdateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User userInfo = null;
		UserService userService = null;
		DepartmentService departmentService = null;
		RoleService roleService = null;
		try {
			String result = "{\"state\":\"error\"}";
			userService = new UserService();

			userInfo = new User();
			userInfo = userService.get(request.getParameter("userId"));
			departmentService = new DepartmentService();
			roleService = new RoleService();
			userInfo.setDepartment(departmentService.get(Integer.parseInt(request.getParameter("departmentId"))));

			userInfo.setRole(roleService.get(Integer.parseInt(request.getParameter("roleId"))));
			userInfo.setState(Integer.parseInt(request.getParameter("state")));
			userInfo.setUserName(request.getParameter("userName"));

			if (userService.update(userInfo)) {
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

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserService userService = null;
		RoleService roleService = null;
		DepartmentService departmentService = null;
		try {
			userService = new UserService();
			roleService = new RoleService();
			departmentService = new DepartmentService();
			request.setAttribute("roleList", roleService.list());
			request.setAttribute("departmentList", departmentService.list());
			request.setAttribute("userInfo", userService.get(request.getParameter("userId")));
			request.getRequestDispatcher("user/update_user.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void doAddUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User userInfo = null;
		UserService userService = null;
		DepartmentService departmentService = null;
		RoleService roleService = null;
		try {
			String result = "{\"state\":\"error\"}";
			userService = new UserService();
			if (userService.list(10, 1, " where phone = " + request.getParameter("phone")).size() < 1) {
				userInfo = new User();
				departmentService = new DepartmentService();
				roleService = new RoleService();
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();// 得到系统当前时间
				String strDate = dateformat.format(date);// 将系统当前时间格式化
				Date time = java.sql.Date.valueOf(strDate);
				userInfo.setCreateDate(time);
				userInfo.setDepartment(departmentService.get(Integer.parseInt(request.getParameter("departmentId"))));
				userInfo.setId(Uuid.uuid());
				userInfo.setPhone(request.getParameter("phone"));
				MD5 getMD5 = new MD5();
				String psw = getMD5.getMD5ofStr(request.getParameter("password"));
				userInfo.setPassword(psw);
				userInfo.setRole(roleService.get(Integer.parseInt(request.getParameter("roleId"))));
				//
				userInfo.setState(Integer.parseInt(request.getParameter("state")));
				userInfo.setUserName(request.getParameter("userName"));

				if (userService.save(userInfo)) {
					result = "{\"state\":\"success\"}";
				}
			} else {
				result = "{\"state\":\"当前手机号已被注册\"}";
			}
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RoleService roleService = null;
		DepartmentService departmentService = null;
		try {
			roleService = new RoleService();
			departmentService = new DepartmentService();
			request.setAttribute("roleList", roleService.list());
			request.setAttribute("departmentList", departmentService.list());
			request.getRequestDispatcher("user/add_user.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void quit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();// 获取session
		try {
			if (session != null) {
				session.removeAttribute("userInfo");
				session.removeAttribute("authorityList");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void doChangePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();// 获取session
		UserService userService = null;
		User userInfoInstance = null;
		String oldPassword;
		String newPassword;
		String result = "{\"state\":\"error\"}";
		try {
			userService = new UserService();
			userInfoInstance = new User();
			userInfoInstance = (User) session.getAttribute("userInfo");
			MD5 getMD5 = new MD5();
			oldPassword = request.getParameter("oldPassword");
			newPassword = request.getParameter("newPassword");

			System.out.println("旧密码：" + oldPassword);
			System.out.println("旧密码：" + userInfoInstance.getPassword());
			System.out.println("旧密码：" + getMD5.getMD5ofStr(oldPassword.trim()));
			if (!getMD5.getMD5ofStr(oldPassword.trim()).equals(userInfoInstance.getPassword())) {
				result = "{\"state\":\"旧密码输入错误\"}";
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.write(result);
				return;
			}
			userInfoInstance.setPassword(getMD5.getMD5ofStr(newPassword));
			if (userService.update(userInfoInstance))
				result = "{\"state\":\"success\"}";
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.write(result);
		}
	}

	private void changePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			request.getRequestDispatcher("user/change_password.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = null;
		User userInfoInstance = null;
		StringBuffer sbuffer = null;
		HttpSession session = request.getSession();
		List<String> authorityList = null;
		RoleService roleService = null;
		RoleAuthorityService roleAuthorityService = null;
		try {
			roleService = new RoleService();
			roleAuthorityService = new RoleAuthorityService();
			authorityList = new ArrayList<String>();
			userService = new UserService();
			userInfoInstance = new User();
			sbuffer = new StringBuffer();
			String phone = request.getParameter("mobilePhone");
			if (phone == null || phone.equals("")) {
				sbuffer.append("您的电话号码不能为空！<br/>");
			}
			String password = request.getParameter("password");
			if (password == null || password.equals("")) {
				sbuffer.append("您的密码不能为空！<br/>");
			}

			if (sbuffer.length() != 0) {
				request.setAttribute("mobilePhone", phone);
				request.setAttribute("password", password);
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				MD5 getMD5 = new MD5();
				String psw = getMD5.getMD5ofStr(password);
				userInfoInstance = userService.login(phone, psw);
				if (userInfoInstance != null) {
					if (userInfoInstance.getState() == 0) {
						authorityList = roleAuthorityService.listAuthorityUrl(userInfoInstance.getRole().getId());
						System.out.println(authorityList);
						session.setAttribute("userInfo", userInfoInstance);// 向session域中保存
						session.setAttribute("listUrl", authorityList.toString());
						if (userInfoInstance.getRole().getId() == 1)
							response.sendRedirect(request.getContextPath() + "/UserAction?operate=listUser");
						else
							response.sendRedirect(request.getContextPath() + "/CaseAction?operate=listCase");

					} else {
						request.setAttribute("mobilePhone", phone);
						request.setAttribute("password", password);
						request.setAttribute("msg", "账号被禁用");
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}

				} else {
					request.setAttribute("mobilePhone", phone);
					request.setAttribute("password", password);
					request.setAttribute("msg", "用户名或密码错误！");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
