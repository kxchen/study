package com.luna.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.luna.dto.Brand;
import com.luna.dto.MobilePhoneInfo;
import com.luna.dto.NetworkType;
import com.luna.dto.OperatingSystem;
import com.luna.dto.OrderDetail;
import com.luna.dto.OrderInfo;
import com.luna.dto.ScreenSize;
import com.luna.dto.ShopCart;
import com.luna.dto.UserInfo;
import com.luna.service.BrandService;
import com.luna.service.MobilePhoneInfoService;
import com.luna.service.NetworkTypeService;
import com.luna.service.OperatingSystemService;
import com.luna.service.OrderService;
import com.luna.service.ScreenSizeService;
import com.luna.service.ShopCartService;
import com.luna.service.UserInfoService;
import com.luna.util.Mail;
import com.luna.util.MailUtils;
import com.luna.util.PaymentUtil;
import com.luna.util.Uuid;

/**
 * Servlet implementation class UserInfoController
 */
public class UserInfoController extends HttpServlet {
	/**
	 * @Fields serialVersionUID : 用来表明类的不同版本间的兼容性
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoController() {
		super();

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
			if (operate.equals("login")) {
				login(request, response);
			} else if (operate.equals("regist")) {
				regist(request, response);
			} else if (operate.equals("list")) {
				list(request, response);
			} else if (operate.equals("edit")) {
				edit(request, response);
			} else if (operate.equals("update")) {
				update(request, response);
			} else if (operate.equals("delete")) {
				delete(request, response);
			} else if (operate.equals("quit")) {
				quit(request, response);
			} else if (operate.equals("show")) {
				show(request, response);
			} else if (operate.equals("changePsw")) {
				changePsw(request, response);
			} else if (operate.equals("listPhone")) {
				listPhone(request, response);
			} else if (operate.equals("product")) {
				product(request, response);
			} else if (operate.equals("saveShopCart")) {
				saveShopCart(request, response);
			} else if (operate.equals("listShopCart")) {
				listShopCart(request, response);
			} else if (operate.equals("updateShopCart")) {
				updateShopCart(request, response);
			} else if (operate.equals("deleteShopCart")) {
				deleteShopCart(request, response);
			} else if (operate.equals("deleteShopCartAll")) {
				deleteShopCartAll(request, response);
			} else if (operate.equals("saveOrder")) {
				saveOrder(request, response);
			} else if (operate.equals("listOrder")) {
				listOrder(request, response);
			} else if (operate.equals("listOredeDetail")) {
				listOredeDetail(request, response);
			} else if (operate.equals("deleteOrder")) {
				deleteOrder(request, response);
			} else if (operate.equals("zhiFu")) {
				zhiFu(request, response);
			} else if (operate.equals("active")) {
				active(request, response);
			} else if (operate.equals("back")) {
				back(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	/**
	 * 支付之去银行
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private String zhiFu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long id = Long.parseLong(request.getParameter("orderId"));
		OrderService orderService = null;
		OrderInfo order = null;
		try {
			orderService = new OrderService();
			order = orderService.get(id);
		} catch (Exception e) {

			e.printStackTrace();
		}
		Properties props = new Properties();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("merchantInfo.properties");
		props.load(input);
		/*
		 * 准备13参数
		 */
		String p0_Cmd = "Buy";// 业务类型
		String p1_MerId = props.getProperty("p1_MerId");// 商户编号
		long p2_Order = order.getOrderId();// 商户订单号
		double p3_Amt = order.getTotalPrice();// 支付金额
		String p4_Cur = "CNY";// 交易币种
		String p5_Pid = "";// 商品名称
		String p6_Pcat = "";// 商品种类
		String p7_Pdesc = "";// 商品描述
		/*
		 * 支付成功后易宝支付会向该地址发送两次成功通知，该地址可以带参数，如: “
		 * www.yeepay.com/callback.action?test=test”.
		 * 注意：如不填p8_Url的参数值支付成功后您将得不到支付成功的通知。
		 */
		String p8_Url = props.getProperty("p8_Url");// 商户接收支付成功数据的地址
		String p9_SAF = "";// 送货地址
		String pa_MP = "";// 商户扩展信息
		String pd_FrpId = request.getParameter("pd_FrpId");// 支付通道编码
		/*
		 * 固定值为“1”: 需要应答机制;
		 * 收到易宝支付服务器点对点支付成功通知，必须回写以”success”（无关大小写）开头的字符串，即使您收到成功通知时发现该订单已经处理过，
		 * 也要正确回写”success”，否则易宝支付将认为您的系统没有收到通知，启动重发机制，直到收到”success”为止。
		 * 还有一种应答机制是引导客户端浏览器访问。
		 */
		String pr_NeedResponse = "1"; // 应答机制

		/*
		 * 计算hmac
		 */
		String keyValue = props.getProperty("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);

		/*
		 * 连接易宝的网址和13+1个参数
		 */
		StringBuilder url = new StringBuilder(props.getProperty("url"));
		url.append("?p0_Cmd=").append(p0_Cmd);
		url.append("&p1_MerId=").append(p1_MerId);
		url.append("&p2_Order=").append(p2_Order);
		url.append("&p3_Amt=").append(p3_Amt);
		url.append("&p4_Cur=").append(p4_Cur);
		url.append("&p5_Pid=").append(p5_Pid);
		url.append("&p6_Pcat=").append(p6_Pcat);
		url.append("&p7_Pdesc=").append(p7_Pdesc);
		url.append("&p8_Url=").append(p8_Url);
		url.append("&p9_SAF=").append(p9_SAF);
		url.append("&pa_MP=").append(pa_MP);
		url.append("&pd_FrpId=").append(pd_FrpId);
		url.append("&pr_NeedResponse=").append(pr_NeedResponse);
		url.append("&hmac=").append(hmac);

		System.out.println("连接易宝的网址和13+1个参数" + url);

		/*
		 * 重定向到易宝
		 */
		response.sendRedirect(url.toString());

		return null;
	}

	/**
	 * 这个方法是易宝回调方法 我们必须要判断调用本方法的是不是易宝！
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private void back(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrderService orderService = new OrderService();
		/*
		 * 1. 获取11 + 1
		 */
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String hmac = request.getParameter("hmac");

		/*
		 * 2. 校验访问者是否为易宝！
		 */
		Properties props = new Properties();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("merchantInfo.properties");
		props.load(input);
		String keyValue = props.getProperty("keyValue");

		boolean bool = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid,
				r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);

		if (!bool) {// 如果校验失败
			request.setAttribute("msg", "您不是什么好东西！");
			listOrder(request, response);
		}

		/*
		 * 3. 获取状态订单，确定是否要修改订单状态
		 */
		long id = Long.parseLong(r6_Order);
		OrderInfo order = null;
		try {
			order = orderService.get(id);
			int state = order.getIsPay();
			if (state == 0) {
				order.setIsPay(1);
			}
			orderService.updateOrders(order);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * 4. 判断当前回调方式 如果为点对点，需要回馈以success开头的字符串
		 */
		if (r9_BType.equals("2")) {
			response.getWriter().print("success");
		}

		/*
		 * 5. 保存成功信息
		 */
		request.setAttribute("msg", "支付成功！等待卖家发货！你慢慢等~");
		listOrder(request, response);
	}

	/**
	 * @Description: 删除订单
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = Long.parseLong(request.getParameter("orderId"));
		OrderService orderService = null;
		try {
			orderService = new OrderService();
			if (orderService.delete(orderService.get(id))) {
				request.setAttribute("msg", "删除成功！");
				listOrder(request, response);
			} else {
				request.setAttribute("msg", "删除失败");
				listOrder(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	/**
	 * @Description: 订单详细列表
	 * @param request
	 * @param response
	 */
	private void listOredeDetail(HttpServletRequest request, HttpServletResponse response) {
		OrderService orderService = null;
		List<OrderDetail> orderDetailList = null;
		long id = Long.parseLong(request.getParameter("orderId"));
		OrderInfo orderInfo = null;
		try {
			orderDetailList = new ArrayList<OrderDetail>();
			orderService = new OrderService();
			orderInfo = orderService.get(id);
			orderDetailList = orderService.getOrederDetaillist(id);
			request.setAttribute("orderInfo", orderInfo);
			request.setAttribute("orderDetailList", orderDetailList);
			request.getRequestDispatcher("/user/listOrderDetail.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description:订单列表（根据用户）
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void listOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserInfo userInfo = null;
		HttpSession session = request.getSession();
		List<OrderInfo> orderInfoList = null;
		OrderService orderService = null;
		int pageNo = 1;
		int pageSize = 9;
		int count = 0;
		try {
			if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals(""))
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			userInfo = (UserInfo) session.getAttribute("userInfo");
			orderInfoList = new ArrayList<OrderInfo>();
			orderService = new OrderService();
			orderInfoList = orderService.getOrderInfoByUserId(pageSize, pageNo, userInfo.getUserId());
			count = orderService.getOrderCountByUserId(userInfo.getUserId());
			request.setAttribute("orderInfoList", orderInfoList);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("count", count);
			request.setAttribute("totalPage", (int) Math.ceil((float) count / pageSize));
			request.getRequestDispatcher("/user/listOrder.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	/**
	 * @Description: 添加订单
	 * @param request
	 * @param response
	 * @throws Exception
	 */
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
				totalPrice = totalPrice + shopCart.getMobilePhone().getRealPrice() * shopCart.getQuantity();
			}
			orderInfo.setTotalPrice(totalPrice);

			// 得到订单详情
			orderDetailList = new ArrayList<OrderDetail>();
			for (int i = 0; i < shopCartList.size(); i++) {
				shopCart = new ShopCart();
				shopCart = shopCartList.get(i);
				orderDetail = new OrderDetail();
				orderDetail.setAmount(shopCart.getQuantity());
				orderDetail.setBuyPrice(shopCart.getMobilePhone().getRealPrice());
				orderDetail.setMobilePhone(shopCart.getMobilePhone());
				orderDetail.setOrder(orderInfo);
				orderDetailList.add(orderDetail);
			}
			if (orderService.save(orderInfo, orderDetailList)) {
				request.setAttribute("msg", "订单提交成功！");
				listOrder(request, response);
			} else {
				request.setAttribute("msg", "订单提交失败");
				listShopCart(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	/**
	 * @Description:清空购物车
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void deleteShopCartAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserInfo userInfo = null;
		HttpSession session = request.getSession();
		ShopCartService shopCartService = null;
		try {
			userInfo = (UserInfo) session.getAttribute("userInfo");
			shopCartService = new ShopCartService();
			if (shopCartService.deleteAll(userInfo)) {
				request.setAttribute("msg", "删除成功！");
				listShopCart(request, response);
			} else {
				request.setAttribute("msg", "删除失败");
				listShopCart(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	/**
	 * @Description: 从购物车中删除
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void deleteShopCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("shopCartId").trim());
		ShopCartService shopCartService = null;
		try {
			shopCartService = new ShopCartService();
			if (shopCartService.delete(shopCartService.get(id))) {
				request.setAttribute("msg", "删除成功！");
				listShopCart(request, response);
			} else {
				request.setAttribute("msg", "删除失败");
				listShopCart(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	/**
	 * @Description: 修改购物车（商品数量）
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void updateShopCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ShopCartService shopCartService = null;
		StringBuffer sbuffer = null;
		ShopCart shopCartInstance = null;
		try {
			shopCartService = new ShopCartService();
			sbuffer = new StringBuffer();
			shopCartInstance = new ShopCart();

			int id = Integer.parseInt(request.getParameter("shopCartId").trim());

			shopCartInstance = shopCartService.get(id);
			String quantity = request.getParameter("quantity");
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
				listShopCart(request, response);
			} else {
				if (shopCartService.update(shopCartInstance)) {
					request.setAttribute("msg", "修改成功！");
					listShopCart(request, response);
				} else {
					request.setAttribute("msg", "修改失败！");
					listShopCart(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	/**
	 * @Description: 购物车列表（根据用户）
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void listShopCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ShopCartService shopCartService = null;
		List<ShopCart> shopCartList = null;
		UserInfo userInfo = null;
		HttpSession session = request.getSession();
		double zongjia = 0, zhekou, yingfu = 0;
		ShopCart shopCart = null;
		try {
			userInfo = (UserInfo) session.getAttribute("userInfo");
			shopCartList = new ArrayList<ShopCart>();
			shopCartService = new ShopCartService();
			shopCartList = shopCartService.list(userInfo);

			for (int i = 0; i < shopCartList.size(); i++) {
				shopCart = shopCartList.get(i);
				zongjia = zongjia + shopCart.getMobilePhone().getPrice() * shopCart.getQuantity();
				yingfu = yingfu + shopCart.getMobilePhone().getRealPrice() * shopCart.getQuantity();
			}
			zhekou = zongjia - yingfu;
			request.setAttribute("zongjia", zongjia);
			request.setAttribute("yingfu", yingfu);
			request.setAttribute("zhekou", zhekou);
			request.setAttribute("shopCartList", shopCartList);
			request.getRequestDispatcher("/user/listShopCart.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	/**
	 * @Description:添加购物车
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void saveShopCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
				listPhone(request, response);
			} else {
				request.setAttribute("msg", "添加失败！");
				listPhone(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	/**
	 * @Description:商品详细信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void product(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MobilePhoneInfoService mobilePhoneInfoService = null;
		MobilePhoneInfo mobilePhoneInfoInstance = null;
		try {
			mobilePhoneInfoService = new MobilePhoneInfoService();
			mobilePhoneInfoInstance = new MobilePhoneInfo();
			int id = Integer.parseInt(request.getParameter("mobPhoneId").trim());
			mobilePhoneInfoInstance = mobilePhoneInfoService.get(id);
			request.setAttribute("mobilePhoneInfo", mobilePhoneInfoInstance);
			request.getRequestDispatcher("/product.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	/**
	 * @Description: 手机列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void listPhone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MobilePhoneInfoService mobilePhoneInfoService = null;
		List<MobilePhoneInfo> mobilePhoneInfoList = null;
		BrandService brandService = null;
		List<Brand> brandList = null;
		NetworkTypeService networkTypeService = null;
		List<NetworkType> networkTypeList = null;
		OperatingSystemService operatingSystemService = null;
		List<OperatingSystem> operatingSystemList = null;
		ScreenSizeService screenSizeService = null;
		List<ScreenSize> screenSizeList = null;

		StringBuffer queryString = null;
		boolean flag = false;
		int pageNo = 1;
		int pageSize = 9;
		int count = 0;
		String query;
		try {
			mobilePhoneInfoList = new ArrayList<MobilePhoneInfo>();
			mobilePhoneInfoService = new MobilePhoneInfoService();
			queryString = new StringBuffer();
			if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals(""))
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			// queryString.append(" where state = 1 ");
			if (request.getParameter("brandName") != null && !request.getParameter("brandName").equals("")) {
				// queryString.append(" and ");
				queryString.append(" brand_name = '");
				queryString.append(request.getParameter("brandName"));
				queryString.append("' ");
				flag = true;
			}
			if (request.getParameter("netTypeName") != null && !request.getParameter("netTypeName").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" net_type_name = '");
				queryString.append(request.getParameter("netTypeName"));
				queryString.append("' ");
				flag = true;
			}

			if (request.getParameter("opeSystemName") != null && !request.getParameter("opeSystemName").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" ope_system_name = '");
				queryString.append(request.getParameter("opeSystemName"));
				queryString.append("' ");
				flag = true;
			}

			if (request.getParameter("scrSizeName") != null && !request.getParameter("scrSizeName").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" scr_size_name = '");
				queryString.append(request.getParameter("scrSizeName"));
				queryString.append("' ");
				flag = true;
			}
			if (request.getParameter("search") != null && !request.getParameter("search").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" brand_name like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" model like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" pixels like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" ram like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" ope_system_name like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" net_type_name like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" color like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				flag = true;
			}
			if (flag) {
				query = " where state = 1 and ";
			} else {
				query = " where state = 1 ";
			}
			if (queryString.toString().equals("")) {
				mobilePhoneInfoList = mobilePhoneInfoService.list(pageSize, pageNo, query);
				count = mobilePhoneInfoService.getCount(query);
			} else {
				mobilePhoneInfoList = mobilePhoneInfoService.list(pageSize, pageNo, query + queryString.toString());
				count = mobilePhoneInfoService.getCount(query + queryString.toString());
			}

			request.setAttribute("mobilePhoneInfoList", mobilePhoneInfoList);

			brandList = new ArrayList<Brand>();
			brandService = new BrandService();
			brandList = brandService.list();
			request.setAttribute("brandList", brandList);

			networkTypeList = new ArrayList<NetworkType>();
			networkTypeService = new NetworkTypeService();
			networkTypeList = networkTypeService.list();
			request.setAttribute("networkTypeList", networkTypeList);

			operatingSystemList = new ArrayList<OperatingSystem>();
			operatingSystemService = new OperatingSystemService();
			operatingSystemList = operatingSystemService.list();
			request.setAttribute("operatingSystemList", operatingSystemList);

			screenSizeList = new ArrayList<ScreenSize>();
			screenSizeService = new ScreenSizeService();
			screenSizeList = screenSizeService.list();

			mobilePhoneInfoList.size();
			request.setAttribute("search", request.getParameter("search"));
			request.setAttribute("screenSizeList", screenSizeList);
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", (int) Math.ceil((float) count / pageSize));
			request.getRequestDispatcher("/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	/**
	 * @Description: 修改密码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void changePsw(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserInfo userInfoInstance = null;
		UserInfoService userInfoService = null;
		StringBuffer sbuffer = null;
		try {
			userInfoInstance = (UserInfo) session.getAttribute("userInfo");
			userInfoService = new UserInfoService();
			sbuffer = new StringBuffer();
			String password = request.getParameter("password");
			if (password == null || password.equals("")) {
				sbuffer.append("旧密码不能为空！<br/>");
			}
			if (userInfoInstance.getPassword().equals(password)) {
				String newPassword1 = request.getParameter("newPassword1");
				String newPassword2 = request.getParameter("newPassword2");
				if (newPassword1 == null || newPassword1.equals("")) {
					sbuffer.append("新密码不能为空！<br/>");
				}
				if (newPassword2 == null || newPassword2.equals("")) {
					sbuffer.append("再次输入新密码不能为空！<br/>");
				}
				if (newPassword1.equals(newPassword2)) {
					userInfoInstance.setPassword(newPassword2);
				} else {
					sbuffer.append("两次输入的新密码不同！<br/>");
				}
			} else {
				sbuffer.append("旧密码输入错误！<br/>");
			}

			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("user/changePsw.jsp").forward(request, response);
			} else {
				if (userInfoService.update(userInfoInstance)) {
					if (session != null) {
						session.removeAttribute("userInfo");
						// request.setAttribute("msg", "请登录");
						request.setAttribute("msg", "修改密码成功，请重新登陆！");
						request.getRequestDispatcher("account.jsp").forward(request, response);
					}

				} else {
					request.setAttribute("msg", "修改密码失败");
					request.getRequestDispatcher("user/chengePsw.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	/**
	 * @Description: 用户详细信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void show(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserInfoService userInfoService = null;
		UserInfo userInfoInstance = null;
		try {
			userInfoInstance = new UserInfo();
			userInfoService = new UserInfoService();
			int id = Integer.parseInt(request.getParameter("userId").trim());
			userInfoInstance = userInfoService.get(id);
			request.setAttribute("userInfo", userInfoInstance);
			request.getRequestDispatcher("/user/show.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	/**
	 * @Description:用户资料修改
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserInfoService userInfoService = null;
		StringBuffer sbuffer = null;
		UserInfo userInfoInstance = null;
		try {
			userInfoService = new UserInfoService();
			sbuffer = new StringBuffer();
			userInfoInstance = new UserInfo();
			int id = Integer.parseInt(request.getParameter("userId").trim());
			userInfoInstance = userInfoService.get(id);
			String loginName = request.getParameter("loginName");
			if (loginName == null || loginName.equals("")) {
				sbuffer.append("您的用户名不能为空！<br/>");
			} else {
				userInfoInstance.setLoginName(loginName);
			}
			String realName = request.getParameter("realName");
			if (realName == null || realName.equals("")) {
				sbuffer.append("您的真是姓名不能为空！<br/>");
			} else {
				userInfoInstance.setRealName(realName);
			}
			String phone = request.getParameter("phone");
			if (phone == null || phone.equals("")) {
				sbuffer.append("您的联系电话不能为空！<br/>");
			} else {
				userInfoInstance.setPhone(phone);
			}
			String address = request.getParameter("address");
			if (address == null || address.equals("")) {
				sbuffer.append("您的收货地址不能为空！<br/>");
			} else {
				userInfoInstance.setAddress(address);
			}
			String email = request.getParameter("email");
			if (email == null || email.equals("")) {
				sbuffer.append("您的电子邮箱不能为空！<br/>");
			} else {
				userInfoInstance.setEmail(email);
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				request.setAttribute("userInfo", userInfoInstance);
				request.getRequestDispatcher("/user/update.jsp").forward(request, response);
			} else {
				if (userInfoService.update(userInfoInstance)) {
					request.setAttribute("msg", "修改成功！");
					request.setAttribute("userInfo", userInfoInstance);
					request.getRequestDispatcher("/user/update.jsp").forward(request, response);
					// list(request, response);
				} else {
					request.setAttribute("msg", "修改失败！");
					request.setAttribute("userInfo", userInfoInstance);
					request.getRequestDispatcher("/user/update.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	/**
	 * @Description:得到用户数据，显示到用户修改页
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("userId").trim());
		UserInfoService userInfoService = null;
		UserInfo userInfoInstance = null;
		try {
			userInfoInstance = new UserInfo();
			userInfoService = new UserInfoService();
			userInfoInstance = userInfoService.get(id);
			request.setAttribute("userInfo", userInfoInstance);
			request.getRequestDispatcher("/user/update.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	/**
	 * @Description:用列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserInfoService userInfoService = null;
		List<UserInfo> userInfoList = null;
		StringBuffer queryString = null;
		boolean flag = false;
		int pageNo = 1;
		int pageSize = 10;
		int count = 0;
		try {
			userInfoList = new ArrayList<UserInfo>();
			userInfoService = new UserInfoService();
			queryString = new StringBuffer();
			if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals(""))
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			if (request.getParameter("loginName") != null && !request.getParameter("loginName").equals("")) {
				queryString.append(" login_name like '%");
				queryString.append(request.getParameter("loginName"));
				queryString.append("%' ");
				flag = true;
			}

			if (request.getParameter("phone") != null && !request.getParameter("phone").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" phone like '%");
				queryString.append(request.getParameter("phone"));
				queryString.append("%' ");
				flag = true;
			}

			if (request.getParameter("email") != null && !request.getParameter("email").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" email like '%");
				queryString.append(request.getParameter("email"));
				queryString.append("%' ");
				flag = true;
			}
			if (request.getParameter("search") != null && !request.getParameter("search").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" login_name like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" real_name like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
			}
			if (queryString.toString().equals("")) {
				userInfoList = userInfoService.list(pageSize, pageNo, "");
				count = userInfoService.getCount("");
			} else {
				userInfoList = userInfoService.list(pageSize, pageNo, " where " + queryString.toString());
				count = userInfoService.getCount(" where " + queryString.toString());
			}
			request.setAttribute("userInfoList", userInfoList);
			request.setAttribute("loginName", request.getParameter("loginName"));
			request.setAttribute("phone", request.getParameter("phone"));
			request.setAttribute("email", request.getParameter("email"));
			request.setAttribute("search", request.getParameter("search"));
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", (int) Math.ceil((float) count / pageSize));
			request.getRequestDispatcher("/user/list.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("userId").trim());
		UserInfoService userInfoService = null;
		try {
			userInfoService = new UserInfoService();
			if (userInfoService.delete(userInfoService.get(id))) {
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

	/**
	 * @Description: 用户激活
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");
		UserInfoService userInfoService = null;
		UserInfo userInfoInstance = null;
		try {
			userInfoService = new UserInfoService();
			userInfoInstance = userInfoService.active(code);
			if (userInfoInstance == null)
				request.setAttribute("msg", "激活码无效！！！");
			else if (userInfoInstance.getState() == 1)
				request.setAttribute("msg", "您已经激活过了，不要再激活了");
			else {
				userInfoInstance.setState(1);
				userInfoService.update(userInfoInstance);
				request.setAttribute("msg", "你已激活，请登录！！！");
			}
			request.getRequestDispatcher("/account.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	/**
	 * @Description: 注册
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void regist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserInfoService userInfoService = null;
		StringBuffer sbuffer = null;
		UserInfo userInfoInstance = null;
		try {
			userInfoService = new UserInfoService();
			sbuffer = new StringBuffer();
			userInfoInstance = new UserInfo();
			String loginName = request.getParameter("loginName");
			if (loginName == null || loginName.equals("")) {
				sbuffer.append("您的用户名不能为空！<br/>");
			} else {
				userInfoInstance.setLoginName(loginName);
			}
			String realName = request.getParameter("realName");
			if (realName == null || realName.equals("")) {
				sbuffer.append("您的真是姓名不能为空！<br/>");
			} else {
				userInfoInstance.setRealName(realName);
			}
			String password = request.getParameter("password");
			if (password == null || password.equals("")) {
				sbuffer.append("您的密码不能为空！<br/>");
			} else {
				userInfoInstance.setPassword(password);
			}
			String phone = request.getParameter("phone");
			if (phone == null || phone.equals("")) {
				sbuffer.append("您的联系电话不能为空！<br/>");
			} else {
				userInfoInstance.setPhone(phone);
			}
			String address = request.getParameter("address");
			if (address == null || address.equals("")) {
				sbuffer.append("您的收货地址不能为空！<br/>");
			} else {
				userInfoInstance.setAddress(address);
			}
			String email = request.getParameter("email");

			if (email == null || email.equals("")) {
				sbuffer.append("您的电子邮箱不能为空！<br/>");
			} else {
				userInfoInstance.setEmail(email);
			}
			// 日期格式工具
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();// 得到系统当前时间
			String strDate = dateformat.format(date);// 将系统当前时间格式化
			Date regtime = java.sql.Date.valueOf(strDate);
			userInfoInstance.setRegtime(regtime);
			userInfoInstance.setState(0);

			userInfoInstance.setCode(Uuid.uuid());

			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("/account.jsp").forward(request, response);
			} else {
				if (userInfoService.save(userInfoInstance)) {

					/*
					 * 发邮件 准备配置文件！
					 */
					// 获取配置文件内容
					Properties props = new Properties();
					props.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
					String host = props.getProperty("host");// 获取服务器主机
					String uname = props.getProperty("uname");// 获取用户名
					String pwd = props.getProperty("pwd");// 获取密码
					String from = props.getProperty("from");// 获取发件人
					String to = userInfoInstance.getEmail();// 获取收件人
					String subject = props.getProperty("subject");// 获取主题
					String content = props.getProperty("content");// 获取邮件内容
					content = MessageFormat.format(content, userInfoInstance.getCode());// 替换配置文件中的{0}

					Session session = MailUtils.createSession(host, uname, pwd);// 得到session

					Mail mail = new Mail(from, to, subject, content);// 创建邮件对象
					try {
						MailUtils.send(session, mail);// 发邮件！
					} catch (MessagingException e) {
					}

					/*
					 * 1. 保存成功信息 2. 转发到msg.jsp
					 */
					request.setAttribute("msg", "恭喜，注册成功！请马上到邮箱激活");
					request.getRequestDispatcher("/account.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "注册失败！");
					request.getRequestDispatcher("/account.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	/**
	 * @Description: 退出
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void quit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();// 获取session
		try {
			if (session != null) {
				session.removeAttribute("userInfo");
				// request.setAttribute("msg", "请登录");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserInfoService userInfoService = null;
		StringBuffer sbuffer = null;
		UserInfo userInfoInstance = null;
		String loginName = "";
		String password = "";
		try {
			userInfoService = new UserInfoService();
			sbuffer = new StringBuffer();
			userInfoInstance = new UserInfo();
			if (request.getParameter("loginName") == null || request.getParameter("loginName").equals("")) {
				sbuffer.append("您的登录名不能为空！<br/>");
			} else {
				loginName = request.getParameter("loginName");
			}
			if (request.getParameter("password") == null || request.getParameter("password").equals("")) {
				sbuffer.append("您的密码名不能为空！<br/>");
			} else {
				password = request.getParameter("password");
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("account.jsp").forward(request, response);
			} else {
				userInfoInstance = userInfoService.login(loginName, password);
				if (userInfoInstance != null) {
					if (userInfoInstance.getState() == 1) {
						session.setAttribute("userInfo", userInfoInstance);// 向session域中保存
						request.getRequestDispatcher("/index.jsp").forward(request, response);
					} else {
						request.setAttribute("msg", "账号还没有激活！！");
						request.getRequestDispatcher("account.jsp").forward(request, response);
					}

				} else {
					request.setAttribute("msg", "用户名或密码错误！！");
					request.getRequestDispatcher("account.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}
}
