package com.luna.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.luna.dto.Brand;
import com.luna.service.BrandService;

/**
 * Servlet implementation class BrandController
 */
public class BrandController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BrandController() {
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
		int id = Integer.parseInt(request.getParameter("brandId").trim());
		BrandService brandService = null;
		try {
			brandService = new BrandService();
			if (brandService.delete(brandService.get(id))) {
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
		BrandService brandService = null;
		StringBuffer sbuffer = null;
		Brand brandInstance = null;
		try {
			brandService = new BrandService();
			sbuffer = new StringBuffer();
			brandInstance = new Brand();
			int id = Integer.parseInt(request.getParameter("brandId").trim());
			brandInstance = brandService.get(id);
			String brandName = request.getParameter("brandName");
			if (brandName == null || brandName.equals("")) {
				sbuffer.append("品牌不能为空！<br/>");
			} else {
				brandInstance.setBrandName(brandName);
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				list(request, response);
				// request.setAttribute("brand", brandInstance);
				// request.getRequestDispatcher("/mobilePhone/category.jsp").forward(request,
				// response);
			} else {
				if (brandService.update(brandInstance)) {
					request.setAttribute("msg", "修改成功！");
					list(request, response);
				} else {
					request.setAttribute("msg", "修改失败！");
					list(request, response);
					// request.setAttribute("brand", brandInstance);
					// request.getRequestDispatcher("/mobilePhone/category.jsp").forward(request,
					// response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BrandService brandService = null;
		List<Brand> brandList = null;
		try {
			brandList = new ArrayList<Brand>();
			brandService = new BrandService();
			brandList = brandService.list();
			request.setAttribute("brandList", brandList);
			request.getRequestDispatcher("/category/brand.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BrandService brandService = null;
		StringBuffer sbuffer = null;
		Brand brandInstance = null;
		try {
			brandService = new BrandService();
			sbuffer = new StringBuffer();
			brandInstance = new Brand();
			String brandName = request.getParameter("brandName");
			if (brandName == null || brandName.equals("")) {
				sbuffer.append("品牌不能为空！<br/>");
			} else {
				brandInstance.setBrandName(brandName);
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				list(request, response);
			} else {
				if (brandService.save(brandInstance)) {
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
