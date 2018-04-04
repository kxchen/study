package com.luna.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.luna.dto.MobilePhoneInfo;
import com.luna.dto.ShopCart;
import com.luna.dto.UserInfo;
import com.luna.service.MobilePhoneInfoService;
import com.luna.service.ShopCartService;

/**
 * Servlet implementation class ShopCartController
 */
public class ShopCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShopCartController() {
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
		int id = Integer.parseInt(request.getParameter("shopCart").trim());
		ShopCartService shopCartService = null;
		try {
			shopCartService = new ShopCartService();
			if (shopCartService.delete(shopCartService.get(id))) {
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
		ShopCartService shopCartService = null;
		StringBuffer sbuffer = null;
		ShopCart shopCartInstance = null;
		try {
			shopCartService = new ShopCartService();
			sbuffer = new StringBuffer();
			shopCartInstance = new ShopCart();

			int id = Integer.parseInt(request.getParameter("shopCartId").trim());

			shopCartInstance = shopCartService.get(id);
			String quantity = request.getParameter("brandName");
			if (quantity == null || quantity.equals("")) {
				shopCartService.delete(shopCartInstance);
				sbuffer.append("数量不能为空！<br/>");
			} else {
				int shuliang = Integer.parseInt(request.getParameter("quantity").trim());
				if (shuliang < 1)
					shopCartService.delete(shopCartInstance);
				shopCartInstance.setQuantity(shuliang);
			}
			if (sbuffer.length() != 0) {
				list(request, response);
			} else {
				if (shopCartService.update(shopCartInstance)) {
					request.setAttribute("msg", "修改成功！");
					list(request, response);
				} else {
					request.setAttribute("msg", "修改失败！");
					list(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ShopCartService shopCartService = null;
		List<ShopCart> shopCartList = null;
		UserInfo userInfo = null;
		HttpSession session = request.getSession();
		try {
			userInfo = (UserInfo) session.getAttribute("userInfo");
			shopCartList = new ArrayList<ShopCart>();
			shopCartService = new ShopCartService();
			shopCartList = shopCartService.list(userInfo);
			request.setAttribute("brandList", shopCartList);
			request.getRequestDispatcher("/category/brand.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ShopCartService shopCartService = null;
		MobilePhoneInfoService mobilePhoneInfoService = null;
		ShopCart shopCartInstance = null;
		MobilePhoneInfo mobilePhoneInfoInstance = null;
		UserInfo userInfo = null;
		HttpSession session = request.getSession();
		boolean flag = false;
		try {
			shopCartService = new ShopCartService();
			shopCartInstance = new ShopCart();
			// 得到用户信息
			userInfo = (UserInfo) session.getAttribute("userInfo");
			// 得到手机信息
			mobilePhoneInfoService = new MobilePhoneInfoService();
			int mobPhoneId = Integer.parseInt(request.getParameter("mobPhoneId").trim());
			mobilePhoneInfoInstance = mobilePhoneInfoService.get(mobPhoneId);

			if (shopCartService.get(userInfo, mobilePhoneInfoInstance) != null) {
				shopCartInstance = shopCartService.get(userInfo, mobilePhoneInfoInstance);
				shopCartInstance.setQuantity(shopCartInstance.getQuantity() + 1);
				flag = shopCartService.update(shopCartInstance);
			}
			// 封装购物车
			else {
				shopCartService = new ShopCartService();
				shopCartInstance = new ShopCart();
				int quantity = 1;
				shopCartInstance.setQuantity(quantity);
				shopCartInstance.setMobilePhone(mobilePhoneInfoInstance);
				shopCartInstance.setUser(userInfo);
				flag = shopCartService.save(shopCartInstance);
			}
			if (flag) {
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

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

}
