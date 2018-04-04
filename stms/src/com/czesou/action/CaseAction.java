package com.czesou.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import com.czesou.pojo.Appendix;
import com.czesou.pojo.Case;
import com.czesou.pojo.User;
import com.czesou.service.AppendixService;
import com.czesou.service.AuthorityService;
import com.czesou.service.CaseService;
import com.czesou.service.HomeworkService;
import com.luna.util.Uuid;

import sun.misc.BASE64Encoder;

/**
 * Servlet implementation class CaseAction
 */
public class CaseAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CaseAction() {
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

			if (operate.equals("addCase"))
				addCase(request, response);
			else if (operate.equals("doAddCase"))
				doAddCase(request, response);
			else if (operate.equals("deleteCase"))
				deleteCase(request, response);
			else if (operate.equals("updateCase"))
				updateCase(request, response);
			else if (operate.equals("doUpdateCase"))
				doUpdateCase(request, response);
			else if (operate.equals("listCase"))
				listCase(request, response);
			else if (operate.equals("listMyCase"))
				listMyCase(request, response);
			else if (operate.equals("delApp"))
				delApp(request, response);
			else if (operate.equals("showAppendix"))
				showAppendix(request, response);
			else if (operate.equals("studentWork"))
				studentWork(request, response);
			else if (operate.equals("download"))
				download(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AppendixService appendixService = null;
		Appendix appendix = null;
		try {
			appendixService = new AppendixService();
			appendix = appendixService.get(request.getParameter("appendixId"));
			// 文件下载路径
			String filename = getServletContext().getRealPath("/" + appendix.getPath());
			System.out.println("文件下载路径：" + filename);
			String framename = filenameEncoding(appendix.getAppendixName(), request);
			String contentType = this.getServletContext().getMimeType(filename);// 通过文件名称获取MIME类型
			String contentDisposition = "attachment;filename=" + framename;
			// 一个流
			FileInputStream input = new FileInputStream(filename);

			// 设置头
			response.setHeader("Content-Type", contentType);
			response.setHeader("Content-Disposition", contentDisposition);

			// 获取绑定了响应端的流
			ServletOutputStream output = response.getOutputStream();

			IOUtils.copy(input, output);// 把输入流中的数据写入到输出流中。

			input.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	// 用来对下载的文件名称进行编码的！
	public static String filenameEncoding(String filename, HttpServletRequest request) throws IOException {
		String agent = request.getHeader("User-Agent"); // 获取浏览器
		if (agent.contains("Firefox")) {
			BASE64Encoder base64Encoder = new BASE64Encoder();
			filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
		} else if (agent.contains("MSIE")) {
			filename = URLEncoder.encode(filename, "utf-8");
		} else {
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;
	}

	private void studentWork(HttpServletRequest request, HttpServletResponse response) {
		AppendixService appendixService = null;
		CaseService caseService = null;
		HomeworkService homeworkService = null;
		try {

			appendixService = new AppendixService();
			caseService = new CaseService();
			homeworkService = new HomeworkService();
			String caseId = request.getParameter("caseId");
			request.setAttribute("caseInfo", caseService.get(caseId));
			request.setAttribute("appendixList", appendixService.listByOwner(caseId));
			request.getRequestDispatcher("case/student_work.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showAppendix(HttpServletRequest request, HttpServletResponse response) {
		AppendixService appendixService = null;
		try {
			appendixService = new AppendixService();
			request.setAttribute("appendixList", appendixService.listByOwner(request.getParameter("caseId")));
			request.getRequestDispatcher("case/show_appendix.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void delApp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AppendixService appendixService = null;
		System.out.println("删除附件Id" + request.getParameter("appendixId"));
		String result = "{\"state\":\"error\"}";
		try {
			appendixService = new AppendixService();
			if (appendixService.delete(appendixService.get(request.getParameter("appendixId"))))
				result = "{\"success\": \"true\"}";
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			result = "{\"state\":\"error\"}";
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.write(result);
		}

	}

	private void listMyCase(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User userInfo = null;
		HttpSession session = request.getSession();// 获取session
		List<Case> caseList = null;
		CaseService caseService = null;
		int pageNo = 1;
		int pageSize = 10;
		int count = 0;
		String queryString = "";
		try {
			userInfo = (User) session.getAttribute("userInfo");
			caseList = new ArrayList<Case>();
			caseService = new CaseService();
			if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals(""))
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			if (request.getParameter("search") != null && !request.getParameter("search").equals("")) {
				queryString = " and case_name like '%" + request.getParameter("search") + "%' ";
			}
			caseList = caseService.list(pageSize, pageNo, "where user_id = '" + userInfo.getId() + "'" + queryString);
			count = caseService.getCount("where user_id = '" + userInfo.getId() + "'" + queryString);
			request.setAttribute("caseList", caseList);
			request.setAttribute("search", request.getParameter("search"));
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", (int) Math.ceil((float) count / pageSize));
			request.getRequestDispatcher("case/list_my_case.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void listCase(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Case> caseList = null;
		CaseService caseService = null;
		int pageNo = 1;
		int pageSize = 10;
		int count = 0;
		String queryString = "";

		try {
			caseList = new ArrayList<Case>();
			caseService = new CaseService();
			if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals(""))
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			if (request.getParameter("search") != null && !request.getParameter("search").equals("")) {
				queryString = " where case_name like '%" + request.getParameter("search") + "%' and case_name like '%"
						+ request.getParameter("search") + "%' ";
			}
			caseList = caseService.list(pageSize, pageNo, queryString);
			count = caseService.getCount(queryString);
			request.setAttribute("caseList", caseList);
			request.setAttribute("search", request.getParameter("search"));
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", (int) Math.ceil((float) count / pageSize));
			request.getRequestDispatcher("case/list_case.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void doUpdateCase(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CaseService caseService = null;
		Case caseInfo = null;
		try {
			caseInfo = new Case();
			caseService = new CaseService();
			caseInfo = caseService.get(request.getParameter("caseId"));
			caseInfo.setCaseName(request.getParameter("caseName"));
			caseInfo.setRemark(request.getParameter("remark"));
			String result = "{\"state\":\"error\"}";
			if (caseService.update(caseInfo)) {
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

	private void updateCase(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CaseService caseService = null;
		AppendixService appendixService = null;
		try {
			caseService = new CaseService();
			appendixService = new AppendixService();
			request.setAttribute("caseInfo", caseService.get(request.getParameter("caseId")));
			request.setAttribute("appendixList", appendixService.listByOwner(request.getParameter("caseId")));
			request.getRequestDispatcher("case/update_case.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void deleteCase(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CaseService caseService = null;
		HomeworkService homeworkService = null;
		try {
			homeworkService = new HomeworkService();
			caseService = new CaseService();
			String result = "{\"state\":\"error\"}";
			if (caseService.delete(caseService.get(request.getParameter("caseId")))) {
				// 只删除案例不删除学生作业
				// && homeworkService.delete(request.getParameter("caseId"))
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

	private void doAddCase(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();// 获取session
		User userInfo = null;
		CaseService caseService = null;
		Case caseInfo = null;
		try {
			userInfo = new User();
			userInfo = (User) session.getAttribute("userInfo");
			caseService = new CaseService();
			caseInfo = new Case();
			caseInfo.setCaseName(request.getParameter("caseName"));
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();// 得到系统当前时间
			String strDate = dateformat.format(date);// 将系统当前时间格式化
			Date time = java.sql.Date.valueOf(strDate);
			caseInfo.setCreateDate(time);
			caseInfo.setId(request.getParameter("caseId"));
			caseInfo.setRemark(request.getParameter("remark"));
			caseInfo.setUser(userInfo);
			String result = "{\"state\":\"error\"}";
			if (caseService.save(caseInfo)) {
				result = "{\"state\":\"success\"}";
			}
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.write(result);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void addCase(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			request.setAttribute("caseId", Uuid.uuid());
			request.getRequestDispatcher("case/add_case.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

}
