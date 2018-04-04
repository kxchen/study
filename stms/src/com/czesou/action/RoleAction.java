package com.czesou.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.czesou.pojo.Department;
import com.czesou.pojo.Role;
import com.czesou.pojo.RoleAuthority;
import com.czesou.service.AuthorityService;
import com.czesou.service.DepartmentService;
import com.czesou.service.RoleAuthorityService;
import com.czesou.service.RoleService;
import com.czesou.service.UserService;

/**
 * Servlet implementation class RoleAction
 */
public class RoleAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RoleAction() {
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

			if (operate.equals("addRole"))
				addRole(request, response);
			else if (operate.equals("doAddRole"))
				doAddRole(request, response);
			else if (operate.equals("deleteRole"))
				deleteRole(request, response);
			else if (operate.equals("updateRole"))
				updateRole(request, response);
			else if (operate.equals("doUpdateRole"))
				doUpdateRole(request, response);
			else if (operate.equals("systemManage"))
				systemManage(request, response);
			else if (operate.equals("roleAuthority"))
				roleAuthority(request, response);
			else if (operate.equals("doRoleAuthority"))
				doRoleAuthority(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void doRoleAuthority(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AuthorityService authorityService = null;
		RoleAuthorityService roleAuthorityService = null;
		Role role = null;
		RoleService roleService = null;
		RoleAuthority roleAuthority = null;
		try {
			roleService = new RoleService();
			authorityService = new AuthorityService();
			roleAuthorityService = new RoleAuthorityService();
			role = new Role();
			role = roleService.get(Integer.parseInt(request.getParameter("role_id")));
			String authorityIds = request.getParameter("authorityIds");
			String[] authorityIdArray = authorityIds.split(",");
			roleAuthorityService.delete(Integer.parseInt(request.getParameter("role_id")));
			String result = "{\"state\":\"error\"}";
			if (authorityIdArray.length > 0) {
				for (int i = 0; i < authorityIdArray.length; i++) {
					roleAuthority = new RoleAuthority();
					roleAuthority.setRole(role);
					roleAuthority.setAuthority(authorityService.get(Integer.parseInt(authorityIdArray[i])));
					roleAuthorityService.save(roleAuthority);
				}
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

	private void roleAuthority(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RoleService roleService = null;
		RoleAuthorityService roleAuthorityService = null;
		AuthorityService authorityService = null;
		try {
			authorityService = new AuthorityService();
			roleService = new RoleService();
			roleAuthorityService = new RoleAuthorityService();
			request.setAttribute("roleAuthorityList",
					roleAuthorityService.list(roleService.get(Integer.parseInt(request.getParameter("role_id")))));
			request.setAttribute("authorityList", authorityService.list());
			request.getRequestDispatcher("/role_authority.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void systemManage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Role> roleList = null;
		RoleService roleService = null;
		List<Department> departmentList = null;
		DepartmentService departmentService = null;
		try {

			roleService = new RoleService();
			roleList = new ArrayList<Role>();
			roleList = roleService.list();
			departmentService = new DepartmentService();
			departmentList = new ArrayList<Department>();
			departmentList = departmentService.list();
			request.setAttribute("departmentList", departmentList);
			request.setAttribute("roleList", roleList);
			request.getRequestDispatcher("systemManage/system_manage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void doUpdateRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RoleService roleService = null;
		Role role = null;
		RoleAuthorityService roleAuthorityService = null;
		RoleAuthority roleAuthority = null;
		AuthorityService authorityServic = null;
		try {
			authorityServic = new AuthorityService();
			roleAuthorityService = new RoleAuthorityService();
			roleService = new RoleService();
			role = new Role();
			role = roleService.get(Integer.parseInt(request.getParameter("roleId")));
			if (roleService.get(request.getParameter("roleName")) != null
					&& !role.equals(roleService.get(request.getParameter("roleName")))) {
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.write("{\"state\":\"角色名已存在\"}");
				return;
			}

			role.setRoleName(request.getParameter("roleName"));
			String authorityIds = request.getParameter("authorityIds");

			String[] authorityIdArray = authorityIds.split(",");

			roleAuthorityService.delete(Integer.parseInt(request.getParameter("roleId")));

			String result = "{\"state\":\"error\"}";
			if (roleService.update(role)) {
				if (authorityIdArray.length > 0) {
					for (int i = 0; i < authorityIdArray.length; i++) {
						roleAuthority = new RoleAuthority();
						roleAuthority.setRole(role);
						roleAuthority.setAuthority(authorityServic.get(Integer.parseInt(authorityIdArray[i])));
						roleAuthorityService.save(roleAuthority);
					}
				}
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

	private void updateRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
		RoleService roleListService = null;
		AuthorityService authorityService = null;
		RoleAuthorityService roleAuthorityService = null;
		try {
			roleAuthorityService = new RoleAuthorityService();
			authorityService = new AuthorityService();
			roleListService = new RoleService();

			request.setAttribute("role", roleListService.get(Integer.parseInt(request.getParameter("roleId"))));
			request.setAttribute("userAuIdList",
					roleAuthorityService.listAuthorityUrl(Integer.parseInt(request.getParameter("roleId"))).toString());
			request.setAttribute("uAuList", authorityService.list(" where parent_id = 1"));
			request.setAttribute("wAuList", authorityService.list(" where parent_id = 2"));
			request.setAttribute("cAuList", authorityService.list(" where parent_id = 3"));
			request.setAttribute("rAuList", authorityService.list(" where parent_id = 4"));
			request.setAttribute("dAuList", authorityService.list(" where parent_id = 5"));
			request.getRequestDispatcher("systemManage/update_role.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void deleteRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RoleService roleService = null;
		UserService userService = null;
		RoleAuthorityService roleAuthorityService = null;
		try {
			String result = "{\"state\":\"error\"}";
			userService = new UserService();
			roleService = new RoleService();
			roleAuthorityService = new RoleAuthorityService();
			if (userService.getCount(" where role_id = " + request.getParameter("roleId")) > 0) {
				result = "{\"state\":\"存在当前角色的用户\"}";
				System.out.println("存在当前角色的用户");
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.write(result);
				return;
			}

			System.out.println("执行删除角色");
			if (roleService.delete(roleService.get(Integer.parseInt(request.getParameter("roleId"))))
					&& roleAuthorityService.delete(Integer.parseInt(request.getParameter("roleId")))) {
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

	private void doAddRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RoleAuthorityService roleAuthorityService = null;
		AuthorityService authorityServic = null;
		Role role = null;
		RoleService roleService = null;
		RoleAuthority roleAuthority = null;
		try {

			roleService = new RoleService();
			if (roleService.get(request.getParameter("roleName")) != null) {

				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.write("{\"state\":\"角色名已存在\"}");
				return;
			}
			System.out.println(roleService.get(request.getParameter("roleName")));
			roleAuthorityService = new RoleAuthorityService();
			authorityServic = new AuthorityService();
			role = new Role();
			roleAuthority = new RoleAuthority();
			Random rnd = new Random();
			int num = 100000 + rnd.nextInt(90000000);
			role.setId(num);
			role.setRoleName(request.getParameter("roleName"));
			String authorityIds = request.getParameter("authorityIds");
			String[] authorityIdArray = authorityIds.split(",");
			String result = "{\"state\":\"error\"}";
			if (roleService.save(role)) {
				if (authorityIdArray.length > 0) {
					for (int i = 0; i < authorityIdArray.length; i++) {
						roleAuthority = new RoleAuthority();
						roleAuthority.setRole(role);
						roleAuthority.setAuthority(authorityServic.get(Integer.parseInt(authorityIdArray[i])));
						roleAuthorityService.save(roleAuthority);
					}
				}
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

	private void addRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AuthorityService authorityService = null;
		try {
			authorityService = new AuthorityService();
			request.setAttribute("uAuList", authorityService.list(" where parent_id = 1"));
			request.setAttribute("wAuList", authorityService.list(" where parent_id = 2"));
			request.setAttribute("cAuList", authorityService.list(" where parent_id = 3"));
			request.setAttribute("rAuList", authorityService.list(" where parent_id = 4"));
			request.setAttribute("dAuList", authorityService.list(" where parent_id = 5"));
			request.getRequestDispatcher("systemManage/add_role.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

}
