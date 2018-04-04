package com.luna.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.luna.dto.NetworkType;
import com.luna.dto.OrderDetail;
import com.luna.dto.OrderInfo;
import com.luna.dto.ShopCart;
import com.luna.dto.UserInfo;
import com.luna.service.NetworkTypeService;
import com.luna.service.OrderService;
import com.luna.service.ShopCartService;

/**
 * Servlet implementation class OrderController
 */
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderController() {
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
			if (operate.equals("saveOrder")) {
				saveOrder(request, response);
			} else if (operate.equals("updateOrder")) {
				updateOrder(request, response);
			} else if (operate.equals("list")) {
				list(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	

	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<OrderInfo> orderInfoList = null;
		OrderService orderService = null;
		StringBuffer queryString = null;
		boolean flag = false;
		int pageNo = 1;
		int pageSize = 10;
		int count = 0;
		try {
			queryString = new StringBuffer();
			orderService = new OrderService();
			if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals(""))
				pageNo = Integer.parseInt(request.getParameter("pageNo"));

			if (request.getParameter("orderId") != null && !request.getParameter("orderId").equals("")) {
				queryString.append(" order_id like '%");
				queryString.append(request.getParameter("orderId"));
				queryString.append("%' ");
				flag = true;
			}

			if (request.getParameter("loginName") != null && !request.getParameter("loginName").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" login_name like '%");
				queryString.append(request.getParameter("loginName"));
				queryString.append("%' ");
				flag = true;
			}
			if (request.getParameter("isDeliver") != null && !request.getParameter("isDeliver").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" is_deliver = '");
				queryString.append(request.getParameter("isDeliver"));
				queryString.append("' ");
				flag = true;
			}
			if (request.getParameter("isPay") != null && !request.getParameter("isPay").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" is_pay = '");
				queryString.append(request.getParameter("isPay"));
				queryString.append("' ");
				flag = true;
			}
			if (request.getParameter("search") != null && !request.getParameter("search").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" order_id like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" login_name like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
			}
			if (queryString.toString().equals("")) {
				orderInfoList = orderService.list(pageSize, pageNo, "");
				count = orderService.getCount("");
			} else {
				orderInfoList = orderService.list(pageSize, pageNo, " where " + queryString.toString());
				count = orderService.getCount(" where " + queryString.toString());
			}

			request.setAttribute("orderInfoList", orderInfoList);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("count", count);
			request.setAttribute("totalPage", (int) Math.ceil((float) count / pageSize));
			request.setAttribute("orderId", request.getParameter("orderId"));
			request.setAttribute("loginName", request.getParameter("loginName"));
			request.setAttribute("search", request.getParameter("search"));
			request.setAttribute("isDeliver", request.getParameter("isDeliver"));
			request.setAttribute("isPay", request.getParameter("isPay"));

			request.getRequestDispatcher("/admin/listOrder.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrderService orderService = null;
		OrderInfo orderInfo = null;
		try {
			orderService = new OrderService();
			orderInfo = new OrderInfo();
			long id = Long.parseLong(request.getParameter("orderId").trim());
			orderInfo = orderService.get(id);

			String isPay = request.getParameter("isPay");
			if (isPay != null && !isPay.equals("")) {
				orderInfo.setIsPay(1);
			}
			String isDeliver = request.getParameter("isDeliver");
			if (isDeliver != null && !isDeliver.equals("")) {
				orderInfo.setIsDeliver(1);
			}
			if (orderService.updateOrders(orderInfo)) {
				request.setAttribute("msg", "修改成功！");
				list(request, response);
			} else {
				request.setAttribute("msg", "修改失败！");
				list(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void saveOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ShopCartService shopCartService = null;
		List<ShopCart> shopCartList = null;
		List<OrderDetail> orderDetailList = null;
		UserInfo userInfo = null;
		HttpSession session = request.getSession();
		double totalPrice = 0;
		ShopCart shopCart = null;
		OrderInfo orderInfo = null;
		OrderDetail orderDetail = null;
		OrderService orderService = null;
		try {
			orderInfo = new OrderInfo();
			orderDetail = new OrderDetail();
			orderService = new OrderService();

			// 得到用户
			userInfo = (UserInfo) session.getAttribute("userInfo");
			shopCartList = new ArrayList<ShopCart>();
			shopCartService = new ShopCartService();
			// 得到购物车列表
			shopCartList = shopCartService.list(userInfo);

			// 得到订单

			// 生成订单编号：当前时间+用户ID
			Long orderId = System.currentTimeMillis();
			orderId = Long.parseLong(orderId.toString() + userInfo.getUserId());

			orderInfo.setOrderId(orderId);
			orderInfo.setUser(userInfo);
			// 设置付款状态为0（未付款）
			orderInfo.setIsPay(0);
			// 设置发货状态为0（未发货）
			orderInfo.setIsDeliver(0);

			// 日期格式工具
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();// 得到系统当前时间
			String strDate = dateformat.format(date);// 将系统当前时间格式化
			Date submitTime = java.sql.Date.valueOf(strDate);
			orderInfo.setSubmitTime(submitTime);

			for (int i = 0; i < shopCartList.size(); i++) {
				shopCart = shopCartList.get(i);
				totalPrice = totalPrice + shopCart.getMobilePhone().getPrice() * shopCart.getQuantity();
			}
			orderInfo.setTotalPrice(totalPrice);

			// 得到订单详情
			orderDetailList = new ArrayList<OrderDetail>();
			for (int i = 0; i < shopCartList.size(); i++) {
				shopCart = new ShopCart();
				shopCart = shopCartList.get(i);
				orderDetail = new OrderDetail();
				orderDetail.setAmount(shopCart.getMobilePhone().getPrice() * shopCart.getQuantity());
				orderDetail.setBuyPrice(shopCart.getMobilePhone().getRealPrice());
				orderDetail.setMobilePhone(shopCart.getMobilePhone());
				orderDetail.setOrder(orderInfo);
				orderDetailList.add(orderDetail);
			}
			orderService.save(orderInfo, orderDetailList);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

}
