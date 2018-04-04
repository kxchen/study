package com.luna.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.luna.dto.NetworkType;
import com.luna.service.NetworkTypeService;

/**
 * Servlet implementation class NetworkTypeController
 */
public class NetworkTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NetworkTypeController() {
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
		int id = Integer.parseInt(request.getParameter("netTypeId").trim());
		NetworkTypeService networkTypeService = null;
		try {
			networkTypeService = new NetworkTypeService();
			if (networkTypeService.delete(networkTypeService.get(id))) {
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
		NetworkTypeService networkTypeService = null;
		StringBuffer sbuffer = null;
		NetworkType networkTypeInstance = null;
		try {
			networkTypeService = new NetworkTypeService();
			sbuffer = new StringBuffer();
			networkTypeInstance = new NetworkType();
			int id = Integer.parseInt(request.getParameter("netTypeId").trim());
			networkTypeInstance = networkTypeService.get(id);
			String netTypeName = request.getParameter("netTypeName");
			if (netTypeName == null || netTypeName.equals("")) {
				sbuffer.append("网络类型不能为空！<br/>");
			} else {
				networkTypeInstance.setNetTypeName(netTypeName);
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				// request.setAttribute("networkType", networkTypeInstance);
				// request.getRequestDispatcher("/mobilePhone/category.jsp").forward(request,
				// response);
				list(request, response);
			} else {
				if (networkTypeService.update(networkTypeInstance)) {
					request.setAttribute("msg", "修改成功！");
					list(request, response);
				} else {
					request.setAttribute("msg", "修改失败！");
					// request.setAttribute("networkType", networkTypeInstance);
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
		NetworkTypeService networkTypeService = null;
		List<NetworkType> networkTypeList = null;
		try {
			networkTypeList = new ArrayList<NetworkType>();
			networkTypeService = new NetworkTypeService();
			networkTypeList = networkTypeService.list();
			request.setAttribute("networkTypeList", networkTypeList);
			request.getRequestDispatcher("/category/networkType.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		NetworkTypeService networkTypeService = null;
		StringBuffer sbuffer = null;
		NetworkType networkTypeInstance = null;
		try {
			networkTypeService = new NetworkTypeService();
			sbuffer = new StringBuffer();
			networkTypeInstance = new NetworkType();
			String netTypeName = request.getParameter("netTypeName");
			if (netTypeName == null || netTypeName.equals("")) {
				sbuffer.append("网络类型不能为空！<br/>");
			} else {
				networkTypeInstance.setNetTypeName(netTypeName);
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				// request.getRequestDispatcher("/mobilePhone/category.jsp").forward(request,
				// response);
				list(request, response);
			} else {
				if (networkTypeService.save(networkTypeInstance)) {
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
