package com.luna.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.luna.dto.ScreenSize;
import com.luna.service.ScreenSizeService;

/**
 * Servlet implementation class ScreenSizeController
 */
public class ScreenSizeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ScreenSizeController() {
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
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("scrSizeId").trim());
		ScreenSizeService screenSizeService = null;
		try {
			screenSizeService = new ScreenSizeService();
			if (screenSizeService.delete(screenSizeService.get(id))) {
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
		ScreenSizeService screenSizeService = null;
		StringBuffer sbuffer = null;
		ScreenSize screenSizeInstance = null;
		try {
			screenSizeService = new ScreenSizeService();
			sbuffer = new StringBuffer();
			screenSizeInstance = new ScreenSize();
			int id = Integer.parseInt(request.getParameter("scrSizeId").trim());
			screenSizeInstance = screenSizeService.get(id);
			String scrSizeName = request.getParameter("scrSizeName");
			if (scrSizeName == null || scrSizeName.equals("")) {
				sbuffer.append("屏幕尺寸不能为空！<br/>");
			} else {
				screenSizeInstance.setScrSizeName(scrSizeName);
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				request.setAttribute("screenSize", screenSizeInstance);
				request.getRequestDispatcher("/mobilePhone/category.jsp").forward(request, response);
			} else {
				if (screenSizeService.update(screenSizeInstance)) {
					request.setAttribute("msg", "修改成功！");
					list(request, response);
				} else {
					request.setAttribute("msg", "修改失败！");
					// request.setAttribute("screenSize", screenSizeInstance);
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
		ScreenSizeService screenSizeService = null;
		List<ScreenSize> screenSizeList = null;
		try {
			screenSizeList = new ArrayList<ScreenSize>();
			screenSizeService = new ScreenSizeService();
			screenSizeList = screenSizeService.list();
			request.setAttribute("screenSizeList", screenSizeList);
			request.getRequestDispatcher("/category/screenSize.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ScreenSizeService screenSizeService = null;
		StringBuffer sbuffer = null;
		ScreenSize screenSizeInstance = null;
		try {
			screenSizeService = new ScreenSizeService();
			sbuffer = new StringBuffer();
			screenSizeInstance = new ScreenSize();
			String scrSizeName = request.getParameter("scrSizeName");
			if (scrSizeName == null || scrSizeName.equals("")) {
				sbuffer.append("屏幕尺寸不能为空！<br/>");
			} else {
				screenSizeInstance.setScrSizeName(scrSizeName);
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				// request.getRequestDispatcher("/mobilePhone/category.jsp").forward(request,
				// response);
				list(request, response);
			} else {
				if (screenSizeService.save(screenSizeInstance)) {
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
