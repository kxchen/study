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

import com.czesou.pojo.Homework;
import com.czesou.pojo.User;
import com.czesou.service.AppendixService;
import com.czesou.service.AuthorityService;
import com.czesou.service.CaseService;
import com.czesou.service.HomeworkService;
import com.czesou.service.UserService;
import com.luna.util.Uuid;

/**
 * Servlet implementation class HomeworkAction
 */
public class HomeworkAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeworkAction() {
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

			if (operate.equals("addHomework"))
				addHomework(request, response);
			else if (operate.equals("doAddHomework"))
				doAddHomework(request, response);
			else if (operate.equals("deleteHomework"))
				deleteHomework(request, response);
			else if (operate.equals("updateHomework"))
				updateHomework(request, response);
			else if (operate.equals("doUpdateHomework"))
				doUpdateHomework(request, response);
			else if (operate.equals("listHomework"))
				listHomework(request, response);
			else if (operate.equals("listMyHomework"))
				listMyHomework(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void listMyHomework(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User userInfo = null;
		HttpSession session = request.getSession();// 获取session
		List<Homework> homeworkList = null;
		HomeworkService homeworkService = null;
		int pageNo = 1;
		int pageSize = 10;
		int count = 0;
		String queryString = "";
		try {
			userInfo = (User) session.getAttribute("userInfo");
			homeworkList = new ArrayList<Homework>();
			homeworkService = new HomeworkService();
			if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals(""))
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			if (request.getParameter("search") != null && !request.getParameter("search").equals("")) {
				queryString = " and homework_name like '%" + request.getParameter("search") + "%' ";
			}

			homeworkList = homeworkService.list(pageSize, pageNo,
					" where user_id = '" + userInfo.getId() + "'" + queryString);
			count = homeworkService.getCount(" where user_id = '" + userInfo.getId() + "'" + queryString);
			request.setAttribute("homeworkList", homeworkList);
			request.setAttribute("search", request.getParameter("search"));
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", (int) Math.ceil((float) count / pageSize));
			request.getRequestDispatcher("homework/list_my_homework.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void listHomework(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Homework> homeworkList = null;
		HomeworkService homeworkService = null;
		AppendixService appendixService = null;
		CaseService caseService = null;
		int pageNo = 1;
		int pageSize = 10;
		int count = 0;
		String queryString = null;
		try {
			String caseId = request.getParameter("caseId");
			appendixService = new AppendixService();
			caseService = new CaseService();
			homeworkList = new ArrayList<Homework>();
			homeworkService = new HomeworkService();
			if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals(""))
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			queryString = " where case_id = '" + caseId + "'";
			if (request.getParameter("search") != null && !request.getParameter("search").equals("")) {
				queryString = "where case_id = " + caseId + " and homework_name like '%"
						+ request.getParameter("search") + "%' and homework_name like '%"
						+ request.getParameter("search") + "%' ";
			}
			request.setAttribute("caseInfo", caseService.get(caseId));
			request.setAttribute("appendixList", appendixService.listByOwner(caseId));
			homeworkList = homeworkService.list(pageSize, pageNo, queryString);
			count = homeworkService.getCount(queryString);
			request.setAttribute("homeworkList", homeworkList);
			request.setAttribute("search", request.getParameter("search"));
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", (int) Math.ceil((float) count / pageSize));
			request.getRequestDispatcher("case/student_work.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void doUpdateHomework(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("doUpdateHomework+333333333");
		Homework homework = null;
		HomeworkService homeworkService = null;
		AppendixService appendixService = null;
		try {
			appendixService = new AppendixService();
			homework = new Homework();
			homeworkService = new HomeworkService();
			homework = homeworkService.get(request.getParameter("homeworkId"));
			homework.setAppendix(appendixService.get(request.getParameter("appendixId")));
			homework.setHomeworkName(request.getParameter("homeworkName"));
			homework.setRemark(request.getParameter("remark"));
			String result = "{\"state\":\"error\"}";
			if (homeworkService.update(homework)) {
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

	private void updateHomework(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HomeworkService homeworkService = null;
		try {
			homeworkService = new HomeworkService();
			request.setAttribute("homework", homeworkService.get(request.getParameter("homeworkId")));
			request.getRequestDispatcher("homework/update_homework.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void deleteHomework(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HomeworkService homeworkService = null;
		try {
			homeworkService = new HomeworkService();
			String result = "{\"state\":\"error\"}";
			if (homeworkService.delete(homeworkService.get(request.getParameter("homeworkId")))) {
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

	private void doAddHomework(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserService userService = null;
		HttpSession session = request.getSession();// 获取session
		CaseService caseService = null;
		AppendixService appendixService = null;
		Homework homework = null;
		HomeworkService homeworkService = null;
		User userInfo = null;
		try {
			homework = new Homework();
			homeworkService = new HomeworkService();
			userService = new UserService();
			caseService = new CaseService();
			userInfo = (User) session.getAttribute("userInfo");
			appendixService = new AppendixService();
			homework.setId(request.getParameter("homeworkId"));
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();// 得到系统当前时间
			String strDate = dateformat.format(date);// 将系统当前时间格式化
			Date time = java.sql.Date.valueOf(strDate);
			homework.setCreateDate(time);
			homework.setRemark(request.getParameter("remark"));
			homework.setHomeworkName(request.getParameter("homeworkName"));
			homework.setAppendix(appendixService.get(request.getParameter("appendixId")));
			homework.setCaseInfo(caseService.get(request.getParameter("caseId")));
			homework.setUser(userService.get(userInfo.getId()));
			String result = "{\"state\":\"error\"}";
			if (homeworkService.save(homework)) {
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

	private void addHomework(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			request.setAttribute("caseId", request.getParameter("caseId"));
			System.out.println("caseId:" + request.getParameter("caseId"));
			request.setAttribute("homeworkId", Uuid.uuid());
			request.getRequestDispatcher("homework/add_homework.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

}
