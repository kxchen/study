package com.luna.action;

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

import com.luna.dto.CarInfo;
import com.luna.dto.OrderDetail;
import com.luna.dto.OrderInfo;
import com.luna.dto.ShopCarInfo;
import com.luna.dto.UserInfo;
import com.luna.service.CarService;
import com.luna.service.OrderService;
import com.luna.service.ShopCarService;

import com.luna.service.UserService;
import com.luna.util.DaysBetween;
import com.luna.util.MD5;
import com.luna.util.Mail;
import com.luna.util.MailUtils;
import com.luna.util.PaymentUtil;
import com.luna.util.Uuid;

/**
 * Servlet implementation class UserAction
 */
public class UserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String longId;

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
		try {
			String operate = request.getParameter("operate");
			if (session.getAttribute("userInfo") == null) {
				if (operate.equals("login")) {
					login(request, response);
				} else if (operate.equals("regist")) {
					regist(request, response);
				} else if (operate.equals("listCar")) {
					listCar(request, response);
				} else if (operate.equals("showCar")) {
					showCar(request, response);
				} else if (operate.equals("activate")) {
					activate(request, response);
				} else {
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} else {
				if (operate.equals("login")) {
					login(request, response);
				} else if (operate.equals("regist")) {
					regist(request, response);
				} else if (operate.equals("listCar")) {
					listCar(request, response);
				} else if (operate.equals("showCar")) {
					showCar(request, response);
				} else if (operate.equals("edit")) {
					edit(request, response);
				} else if (operate.equals("update")) {
					update(request, response);
				} else if (operate.equals("activate")) {
					activate(request, response);
				} else if (operate.equals("show")) {
					show(request, response);
				} else if (operate.equals("saveShopCar")) {
					saveShopCar(request, response);
				} else if (operate.equals("listShopCar")) {
					listShopCar(request, response);
				} else if (operate.equals("buys")) {
					buys(request, response);
				} else if (operate.equals("pay")) {
					pay(request, response);
				} else if (operate.equals("listOrder")) {
					listOrder(request, response);
				} else if (operate.equals("back")) {
					back(request, response);
				} else if (operate.equals("saveCar")) {
					saveCar(request, response);
				} else if (operate.equals("deleShopCar")) {
					deleShopCar(request, response);
				} else if (operate.equals("fabu")) {
					fabu(request, response);
				} else if (operate.equals("updateOrder")) {
					updateOrder(request, response);
				} else if (operate.equals("quit")) {
					quit(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrderService userService = null;
		OrderDetail userInfoInstance = null;
		try {
			userService = new OrderService();
			userInfoInstance = new OrderDetail();
			String id = request.getParameter("orderDetailId").trim();
			userInfoInstance = userService.getOrderDetail(id);

			userInfoInstance.setIsAbolish(1);
			if (userService.update(userInfoInstance)) {
				request.setAttribute("msg", "修改成功！");
				response.sendRedirect(request.getContextPath() + "/UserAction?operate=listOrder&state=yudingzhong");
			} else {
				request.setAttribute("msg", "修改失败！");
				response.sendRedirect(request.getContextPath() + "/UserAction?operate=listOrder&state=yudingzhong");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void fabu(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserInfo userInfo = null;
		try {
			userInfo = new UserInfo();
			userInfo = (UserInfo) session.getAttribute("userInfo");
			if (userInfo.getIsCer() == 2) {
				request.getRequestDispatcher("/fabu.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "你还没有实名认证！");
				edit(request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void deleShopCar(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("UserAction method=deleShopCar");
		ShopCarService shopCarService = null;
		StringBuffer sbuffer = null;
		try {
			sbuffer = new StringBuffer();
			shopCarService = new ShopCarService();
			String shopCarIds = request.getParameter("shopCarIds");
			String[] shopCarIdArray = shopCarIds.split(",");
			for (int i = 0; i < shopCarIdArray.length - 1; i++) {
				sbuffer.append(" '");
				sbuffer.append(shopCarIdArray[i]);
				sbuffer.append("' ");
				sbuffer.append(" , ");
			}
			sbuffer.append(" '");
			sbuffer.append(shopCarIdArray[shopCarIdArray.length - 1]);
			sbuffer.append("' ");
			shopCarService.delete(sbuffer.toString());
			request.setAttribute("msg", "删除成功");
			listShopCar(request, response);
		} catch (Exception e) {

		}

	}

	private void saveCar(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("UserAction method=saveCar");
		CarInfo carInfo = null;
		CarService carService = null;
		UserInfo userInfo = null;
		HttpSession session = request.getSession();
		StringBuffer sbuffer = null;
		try {
			sbuffer = new StringBuffer();
			userInfo = new UserInfo();
			userInfo = (UserInfo) session.getAttribute("userInfo");
			carService = new CarService();
			carInfo = new CarInfo();
			carInfo.setOwnerId(userInfo.getUserId());
			String id = Uuid.uuid();
			carInfo.setCarId(id);
			String brand = request.getParameter("brand");
			if (brand == null || brand.equals("")) {
				sbuffer.append("品牌不能为空！<br/>");
			} else {
				carInfo.setBrand(brand);
			}
			String model = request.getParameter("model");
			if (model == null || model.equals("")) {
				sbuffer.append("型号不能为空！<br/>");
			} else {
				carInfo.setModel(model);
			}
			String type = request.getParameter("type");
			if (type == null || type.equals("")) {
				sbuffer.append("类型不能为空！<br/>");
			} else {
				carInfo.setType(type);
			}
			String buyDate = request.getParameter("buyDate");
			if (buyDate == null || buyDate.equals("")) {
				sbuffer.append("购买时间不能为空！<br/>");
			} else {
				Date regtime = java.sql.Date.valueOf(buyDate);
				carInfo.setBuyDate(regtime);
			}
			String km = request.getParameter("km");
			if (km == null || km.equals("")) {
				sbuffer.append("公里数不能为空！<br/>");
			} else {
				carInfo.setKm(km);
			}

			String carefulImg = request.getParameter("imgPath1");
			if (carefulImg == null || carefulImg.equals("")) {
				sbuffer.append("年审照片不能为空！<br/>");
			} else {
				carInfo.setCarefulImg(carefulImg);
			}
			String carImg = request.getParameter("imgPath2");
			if (carImg == null || carImg.equals("")) {
				sbuffer.append("车辆照片不能为空！<br/>");
			} else {
				carInfo.setCarImg(carImg);
			}
			String gears = request.getParameter("gears");
			if (gears == null || gears.equals("")) {
				sbuffer.append("排档不能为空！<br/>");
			} else {
				carInfo.setGears(carImg);
			}
			String cc = request.getParameter("cc");
			if (cc == null || cc.equals("")) {
				sbuffer.append("排量不能为空！<br/>");
			} else {
				carInfo.setCc(cc);
			}
			String color = request.getParameter("color");
			if (cc == null || cc.equals("")) {
				sbuffer.append("颜色不能为空！<br/>");
			} else {
				carInfo.setColor(color);
			}
			String seats = request.getParameter("seats");
			if (seats == null || seats.equals("")) {
				sbuffer.append("座位数不能为空！<br/>");
			} else {
				carInfo.setSeats(seats);
			}
			String purpose = request.getParameter("purpose");
			if (purpose == null || purpose.equals("")) {
				sbuffer.append("车辆用途不能为空！<br/>");
			} else {
				carInfo.setPurpose(purpose);
			}
			String price = request.getParameter("price");
			if (price == null || price.equals("")) {
				sbuffer.append("出租价格不能为空！<br/>");
			} else {
				double p = Double.parseDouble(price);
				carInfo.setPrice(p);
			}
			// SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			// String strDate = dateformat.format(buyDate);// 将系统当前时间格式化
			// Date regtime = java.sql.Date.valueOf(strDate);
			// carInfo.setRelTime(regtime);
			carInfo.setCriteria(request.getParameter("criteria"));
			carInfo.setComboName("0");
			if (sbuffer.length() != 0) {
				request.setAttribute("carInfo", carInfo);
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("/fabu.jsp").forward(request, response);
			} else {
				if (carService.save(carInfo)) {
					request.setAttribute("msg", "车辆信息已提交，等待审核");
					request.getRequestDispatcher("/fabu.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "车辆信息提交失败");
					request.getRequestDispatcher("/fabu.jsp").forward(request, response);
				}

			}
		} catch (Exception e) {

		}

	}

	private void listOrder(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("UserAction method=listOrder");
		List<OrderDetail> orderDetailList = null;
		OrderService orderService = null;
		HttpSession session = request.getSession();
		UserInfo userInfo = null;
		StringBuffer query = null;
		try {
			query = new StringBuffer();
			userInfo = (UserInfo) session.getAttribute("userInfo");
			orderService = new OrderService();
			orderDetailList = new ArrayList<OrderDetail>();
			query.append("user_id = '");
			query.append(userInfo.getUserId());
			query.append("' and ");
			String state = request.getParameter("state");
			switch (state) {
			case "yiwancheng":
				System.out.println("UserAction method=listOrder yiwancheng");
				query.append("is_return = 1");
				orderDetailList = orderService.getOrderDetailListgetByUserId(query.toString());
				request.setAttribute("orderDetailList", orderDetailList);
				request.getRequestDispatcher("/yiwancheng.jsp").forward(request, response);
				break;
			case "yudingzhong":
				query.append("is_send = 0 and is_pay = 1 and is_abolish not in ( 2 )");
				orderDetailList = orderService.getOrderDetailListgetByUserId(query.toString());
				request.setAttribute("orderDetailList", orderDetailList);
				request.getRequestDispatcher("/yudingzhong.jsp").forward(request, response);
				break;
			case "zulinzhong":
				query.append("is_send = 1 and is_return = 0");
				orderDetailList = orderService.getOrderDetailListgetByUserId(query.toString());
				request.setAttribute("orderDetailList", orderDetailList);
				request.getRequestDispatcher("/zulinzhong.jsp").forward(request, response);
				break;
			default:
				break;
			}
		} catch (Exception e) {

		}

	}

	private void pay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserAction method=pay");
		String id = request.getParameter("orderId");
		OrderService orderService = null;
		OrderInfo orderInfo = null;
		StringBuffer sbuffer = null;
		List<OrderDetail> orderDetailList = null;
		try {
			orderInfo = new OrderInfo();
			orderService = new OrderService();
			sbuffer = new StringBuffer();
			orderInfo = orderService.get(id);
			String contacts = request.getParameter("contacts");
			if (contacts == null || contacts.equals("")) {
				sbuffer.append("联系人不能为空！<br/>");
			} else {
				orderInfo.setContacts(contacts);
			}
			String conPhone = request.getParameter("conPhone");
			if (conPhone == null || conPhone.equals("")) {
				sbuffer.append("联系电话不能为空！<br/>");
			} else {
				orderInfo.setConPhone(conPhone);
			}
			String conAddress = request.getParameter("conAddress");
			if (conAddress == null || conAddress.equals("")) {
				sbuffer.append("联系地址不能为空！<br/>");
			} else {
				orderInfo.setConAddress(conAddress);
			}

			if (sbuffer.length() != 0) {
				orderDetailList = new ArrayList<OrderDetail>();
				orderDetailList = orderService.getOrderDetailList(id);
				request.setAttribute("orderDetailList", orderDetailList);
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("/pay.jsp").forward(request, response);
			} else {
				if (orderService.update(orderInfo)) {
					Properties props = new Properties();
					InputStream input = this.getClass().getClassLoader().getResourceAsStream("merchantInfo.properties");
					props.load(input);
					/*
					 * 准备13参数
					 */
					String p0_Cmd = "Buy";// 业务类型
					String p1_MerId = props.getProperty("p1_MerId");// 商户编号
					String p2_Order = orderInfo.getOrderId();// 商户订单号
					double p3_Amt = 0.01;// orderInfo.getReaPay();// 支付金额
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
					String pd_FrpId = "CCB-NET";// request.getParameter("pd_FrpId");//
												// 支付通道编码(支付银行)
					/*
					 * 固定值为“1”: 需要应答机制;
					 * 收到易宝支付服务器点对点支付成功通知，必须回写以”success”（无关大小写）开头的字符串，
					 * 即使您收到成功通知时发现该订单已经处理过，
					 * 也要正确回写”success”，否则易宝支付将认为您的系统没有收到通知，启动重发机制，直到收到”success”
					 * 为止。 还有一种应答机制是引导客户端浏览器访问。
					 */
					String pr_NeedResponse = "1"; // 应答机制

					/*
					 * 计算hmac
					 */
					String keyValue = props.getProperty("keyValue");
					String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat,
							p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);

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
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

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
		ShopCarService shopCarService = null;
		StringBuffer sbuffer = null;
		System.out.println("userAction method=back ");
		OrderService orderService = new OrderService();
		List<OrderDetail> orderDetailList = null;
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

		orderDetailList = new ArrayList<OrderDetail>();
		String id = r6_Order;
		if (!bool) {// 如果校验失败
			try {
				orderDetailList = orderService.getOrderDetailList(id);
			} catch (Exception e) {

			}
			request.setAttribute("msg", "您不是什么好东西！");
			request.setAttribute("orderDetailList", orderDetailList);
			request.getRequestDispatcher("/pay.jsp").forward(request, response);
		}

		/*
		 * 3. 获取状态订单，确定是否要修改订单状态
		 */
		else {
			OrderInfo orderInfo = null;
			try {
				sbuffer = new StringBuffer();
				shopCarService = new ShopCarService();
				orderInfo = orderService.get(id);

				int state = orderInfo.getIsPay();
				if (state == 0) {
					orderInfo.setIsPay(1);
				}
				orderService.update(orderInfo);

				String[] shopCarIdArray = longId.split(",");
				for (int i = 0; i < shopCarIdArray.length - 1; i++) {
					sbuffer.append(" '");
					sbuffer.append(shopCarIdArray[i]);
					sbuffer.append("' ");
					sbuffer.append(" , ");
				}
				sbuffer.append(" '");
				sbuffer.append(shopCarIdArray[shopCarIdArray.length - 1]);
				sbuffer.append("' ");
				shopCarService.delete(sbuffer.toString());

			} catch (Exception e) {
				e.printStackTrace();
			}
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
		response.sendRedirect(request.getContextPath() + "/UserAction?operate=listOrder&state=yudingzhong");
	}

	private void buys(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("UserAction method=buys");
		ShopCarService shopCarService = null;
		List<OrderDetail> orderDetailList = null;
		UserInfo userInfo = null;
		HttpSession session = request.getSession();
		ShopCarInfo shopCarInfo = null;
		OrderInfo orderInfo = null;
		OrderDetail orderDetail = null;
		OrderService orderService = null;
		double totalPrices = 0;
		String shopCarIds = request.getParameter("shopCarIds");
		longId = shopCarIds;
		String[] shopCarIdArray = shopCarIds.split(",");
		try {
			shopCarService = new ShopCarService();
			orderDetailList = new ArrayList<OrderDetail>();
			userInfo = new UserInfo();
			shopCarInfo = new ShopCarInfo();
			orderInfo = new OrderInfo();
			orderService = new OrderService();
			userInfo = (UserInfo) session.getAttribute("userInfo");

			for (int i = 0; i < shopCarIdArray.length; i++) {
				shopCarInfo = shopCarService.get(shopCarIdArray[i]);
				orderDetail = new OrderDetail();
				orderDetail.setCarInfo(shopCarInfo.getCarInfo());
				orderDetail.setOrderDetailId(Uuid.uuid());
				orderDetail.setPaid((shopCarInfo.getCarInfo().getPaidPer() / 100) * shopCarInfo.getCarInfo().getPrice()
						* shopCarInfo.getAmount());
				orderDetail.setQuantity(shopCarInfo.getAmount());
				orderDetail.setReaPrice(shopCarInfo.getCarInfo().getPrice());
				orderDetail.setRetTime(shopCarInfo.getRetTime());
				orderDetail.setTakeTime(shopCarInfo.getTakeTime());
				orderDetailList.add(orderDetail);
				totalPrices = shopCarInfo.getCarInfo().getPrice() * shopCarInfo.getAmount() + totalPrices;
			}
			// 日期格式工具
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();// 得到系统当前时间
			String strDate = dateformat.format(date);// 将系统当前时间格式化
			Date buyTime = java.sql.Date.valueOf(strDate);
			orderInfo.setBuyTime(buyTime);
			orderInfo.setUserInfo(userInfo);
			orderInfo.setOrderId(Uuid.uuid());
			orderInfo.setPay(totalPrices);
			orderInfo.setReaPay(totalPrices * (shopCarInfo.getCarInfo().getPaidPer() / 100));
			if (orderService.save(orderInfo, orderDetailList)) {
				request.setAttribute("orderDetailList", orderDetailList);
				request.getRequestDispatcher("/pay.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "对不起，购买出错。。。");
				listShopCar(request, response);
			}
		} catch (Exception e) {
		}
	}

	private void listShopCar(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("UserAction method=listShopCar");
		HttpSession session = request.getSession();
		List<ShopCarInfo> shopCarInfoList = null;
		UserInfo userInfo = null;
		ShopCarService shopCarService = null;
		try {
			userInfo = (UserInfo) session.getAttribute("userInfo");
			shopCarInfoList = new ArrayList<ShopCarInfo>();
			shopCarService = new ShopCarService();
			shopCarInfoList = shopCarService.list(userInfo);
			request.setAttribute("shopCarInfoList", shopCarInfoList);
			request.getRequestDispatcher("/shopCar.jsp").forward(request, response);
		} catch (Exception e) {
		}
	}

	private void saveShopCar(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("UserAction method=saveShopCar");
		CarInfo carInfo = null;
		UserInfo userInfo = null;
		ShopCarInfo shopCarInfo = null;
		CarService carService = null;
		ShopCarService shopCarService = null;
		HttpSession session = request.getSession();
		StringBuffer sbuffer = null;
		try {
			sbuffer = new StringBuffer();
			carInfo = new CarInfo();
			userInfo = new UserInfo();
			shopCarInfo = new ShopCarInfo();
			carService = new CarService();
			shopCarService = new ShopCarService();
			userInfo = (UserInfo) session.getAttribute("userInfo");
			String carId = request.getParameter("carId");
			carInfo = carService.get(carId);
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();// 得到系统当前时间
			String strDate = dateformat.format(date);// 将系统当前时间格式化
			String takeTime = request.getParameter("takeTime");
			String retTime = request.getParameter("retTime");
			if (takeTime == null || takeTime.equals("") || retTime == null || retTime.equals("")) {
				sbuffer.append("租期不能为空！<br/>");
			} else {
				if (DaysBetween.daysBetween(strDate, takeTime) < 1) {
					sbuffer.append("取车时间不能小于当前时间");
				} else if (DaysBetween.daysBetween(takeTime, retTime) < 1) {
					sbuffer.append("取车时间不能大于还车时间");
				} else {
					Date taTime = java.sql.Date.valueOf(takeTime);
					shopCarInfo.setTakeTime(taTime);
					Date reTime = java.sql.Date.valueOf(retTime);
					shopCarInfo.setRetTime(reTime);
					shopCarInfo.setAmount(DaysBetween.daysBetween(taTime, reTime));
				}
			}
			shopCarInfo.setCarInfo(carInfo);
			shopCarInfo.setUserInfo(userInfo);
			shopCarInfo.setShopCarId(Uuid.uuid());
			String method = request.getParameter("method");
			if (sbuffer.length() != 0) {
				if (carInfo.getComboName() == null || carInfo.getComboName().equals("")) {
					request.setAttribute("carInfo", carInfo);
					request.setAttribute("msg", sbuffer.toString());
					request.getRequestDispatcher("/detail.jsp").forward(request, response);

				} else {
					request.setAttribute("carInfo", carInfo);
					request.setAttribute("msg", sbuffer.toString());
					request.getRequestDispatcher("/taoCan.jsp").forward(request, response);
				}
			}

			else if (method.equals("立即预定")) {
				shopCarService.save(shopCarInfo);
				response.sendRedirect(
						request.getContextPath() + "/UserAction?operate=buys&shopCarIds=" + shopCarInfo.getShopCarId());
			} else {

				shopCarService.save(shopCarInfo);
				request.setAttribute("msg", "添加成功");
				listShopCar(request, response);
			}
		} catch (Exception e) {

		}

	}

	private void showCar(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("UserAction method=showCar");
		CarInfo carInfo = null;
		CarService carService = null;
		try {
			carInfo = new CarInfo();
			carService = new CarService();
			String id = request.getParameter("carId");
			carInfo = carService.get(id);
			if (carInfo.getComboName() == null || carInfo.getComboName().equals("")) {
				request.setAttribute("carInfo", carInfo);
				request.getRequestDispatcher("/detail.jsp").forward(request, response);
			} else {
				request.setAttribute("carInfo", carInfo);
				request.getRequestDispatcher("/taoCan.jsp").forward(request, response);
			}
		} catch (Exception e) {

		}

	}

	private void listCar(HttpServletRequest request, HttpServletResponse response) {
		List<CarInfo> carInfoList = null;
		CarService carService = null;
		StringBuffer queryString = null;
		boolean flag = false;
		String query;
		String purpose;
		try {
			carInfoList = new ArrayList<>();
			carService = new CarService();
			queryString = new StringBuffer();
			purpose = request.getParameter("purpose");
			if (purpose != null && !purpose.equals("")) {
				queryString.append(" purpose = '");
				queryString.append(purpose);
				queryString.append("' ");
				flag = true;
			}
			if (request.getParameter("brand") != null && !request.getParameter("brand").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" brand = '");
				queryString.append(request.getParameter("brand"));
				queryString.append("' ");
				flag = true;
			}

			if (request.getParameter("model") != null && !request.getParameter("model").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" model = '");
				queryString.append(request.getParameter("model"));
				queryString.append("' ");
				flag = true;
			}

			if (request.getParameter("type") != null && !request.getParameter("type").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" type = '");
				queryString.append(request.getParameter("type"));
				queryString.append("' ");
				flag = true;
			}
			if (request.getParameter("color") != null && !request.getParameter("color").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" color = '");
				queryString.append(request.getParameter("color"));
				queryString.append("' ");
				flag = true;
			}
			queryString.append(" )");

			if (flag) {
				query = " where ( is_check = 1 and ";
			} else {
				query = " where ( is_check = 1 ";
			}

			if (request.getParameter("search") != null && !request.getParameter("search").equals("")) {
				queryString.append(" and ( ");
				queryString.append(" brand like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" model like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" gears like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" type like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" color like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" Seats like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" price like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' )");

				flag = true;
			}

			if (purpose != null && purpose.equals("婚庆用车")) {
				carInfoList = carService.list(query + "combo_name like '%套餐%' and " + queryString.toString());
				System.out.println(carInfoList.size());
				request.setAttribute("purpose", purpose);
				request.setAttribute("carInfoList", carInfoList);
				request.getRequestDispatcher("/hunQing.jsp").forward(request, response);
			} else if (request.getParameter("hot") != null && request.getParameter("hot").equals("hot")) {
				carInfoList = carService.list(query + queryString.toString());
				request.setAttribute("purpose", request.getParameter("purpose"));
				request.setAttribute("carInfoList", carInfoList);
				request.getRequestDispatcher("/list.jsp").forward(request, response);
			} else if (request.getParameter("index") != null && request.getParameter("index").equals("index")) {
				carInfoList = carService.list(query + queryString.toString());
				request.setAttribute("purpose", request.getParameter("purpose"));
				request.setAttribute("carInfoList", carInfoList);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			} else {
				carInfoList = carService.list(query + queryString.toString());
				request.setAttribute("purpose", request.getParameter("purpose"));
				request.setAttribute("carInfoList", carInfoList);
				request.getRequestDispatcher("/list.jsp").forward(request, response);
			}

		} catch (Exception e) {

		}

	}

	private void show(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserInfo userInfoInstance = null;
		try {
			userInfoInstance = (UserInfo) session.getAttribute("userInfo");
			request.setAttribute("userInfo", userInfoInstance);
			request.getRequestDispatcher("show.jsp").forward(request, response);
		} catch (Exception e) {

		}

	}

	private void activate(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = null;
		StringBuffer sbuffer = null;
		UserInfo userInfo = null;
		try {
			userInfo = new UserInfo();
			userService = new UserService();
			sbuffer = new StringBuffer();

			String phone = request.getParameter("phone");
			if (phone == null || phone.equals("")) {
				sbuffer.append("您的电话号码不能为空！<br/>");
			}
			String code = request.getParameter("code");
			if (code == null || code.equals("")) {
				sbuffer.append("激活码不能为空<br/>");
			}
			String password = request.getParameter("password");
			if (password == null || password.equals("")) {
				sbuffer.append("您的密码不能为空！<br/>");
			}
			String password1 = request.getParameter("password1");
			if (password1 == null || password1.equals("")) {
				sbuffer.append("您的确认密码不能为空！<br/>");
			}
			if (password != null && !password.equals("") && password1 != null && !password1.equals("")) {
				if (!password.equals(password1)) {
					sbuffer.append("两次密码输入不同！<br/>");
				}
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("phone", request.getParameter("phone"));
				request.setAttribute("password", request.getParameter("password"));
				request.setAttribute("password1", request.getParameter("password1"));
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("/regist.jsp").forward(request, response);
			} else {
				userInfo = userService.getByPhone(phone);
				if (userInfo.getCode().equals(code)) {
					MD5 getMD5 = new MD5();
					String psw = getMD5.getMD5ofStr(password);
					userInfo.setPassword(psw);
					userInfo.setIsAct(1);
					userService.update(userInfo);
					request.setAttribute("msg", "注册成功！请登录");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				} else {
					request.setAttribute("phone", request.getParameter("phone"));
					request.setAttribute("password", request.getParameter("password"));
					request.setAttribute("password1", request.getParameter("password1"));
					request.setAttribute("msg", "验证码错误！");
					request.getRequestDispatcher("/regist.jsp").forward(request, response);
				}
			}

		} catch (Exception e) {

		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = null;
		HttpSession session = request.getSession();
		StringBuffer sbuffer = null;
		UserInfo userInfoInstance = null;
		try {
			userService = new UserService();
			sbuffer = new StringBuffer();
			userInfoInstance = new UserInfo();
			userInfoInstance = (UserInfo) session.getAttribute("userInfo");
			String phone = request.getParameter("phone");
			if (phone == null || phone.equals("")) {
				sbuffer.append("您的电话号码不能为空！<br/>");
			} else {
				userInfoInstance.setPhone(phone);
			}
			String userName = request.getParameter("userName");
			if (userName == null || userName.equals("")) {
				sbuffer.append("您的姓名不能为空！<br/>");
			} else {
				userInfoInstance.setUserName(userName);
			}
			String sex = request.getParameter("sex");
			if (sex == null || sex.equals("")) {
				sbuffer.append("您的性别不能为空！<br/>");
			} else {
				userInfoInstance.setSex(sex);
			}
			String idNum = request.getParameter("idNum");
			if (idNum == null || idNum.equals("")) {
				sbuffer.append("您的身份证号码不能为空！<br/>");
			} else {
				userInfoInstance.setIdNum(idNum);
			}
			String idImg = request.getParameter("imgPath1");
			if (idImg == null || idImg.equals("")) {
				sbuffer.append("您的身份证照片不能为空！<br/>");
			} else {
				userInfoInstance.setIdImg(idImg);
			}
			String driverImg = request.getParameter("imgPath2");
			if (driverImg == null || driverImg.equals("")) {
				sbuffer.append("您的驾驶证照片不能为空！<br/>");
			} else {
				userInfoInstance.setDriverImg(driverImg);
			}
			userInfoInstance.setOther(request.getParameter("other"));
			userInfoInstance.setOthName(request.getParameter("othName"));
			userInfoInstance.setAddress(request.getParameter("address"));
			userInfoInstance.setOthPhone(request.getParameter("othPhone"));
			// 只要修改了，认证状态就改为待审核状态
			userInfoInstance.setIsCer(1);
			if (sbuffer.length() != 0) {
				request.setAttribute("userInfo", userInfoInstance);
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("/update.jsp").forward(request, response);
			} else {
				if (userService.update(userInfoInstance)) {
					request.setAttribute("msg", "认证信息已提交！");
					show(request, response);
				} else {
					request.setAttribute("msg", "认证信息提交失败");
					edit(request, response);
				}
			}
		} catch (Exception e) {
		}
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserInfo userInfoInstance = null;
		try {
			userInfoInstance = (UserInfo) session.getAttribute("userInfo");
			request.setAttribute("userInfo", userInfoInstance);
			request.getRequestDispatcher("update.jsp").forward(request, response);
		} catch (Exception e) {
		}
	}

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

	private void login(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = null;
		UserInfo userInfoInstance = null;
		StringBuffer sbuffer = null;
		HttpSession session = request.getSession();
		try {
			userService = new UserService();
			userInfoInstance = new UserInfo();
			sbuffer = new StringBuffer();
			String phone = request.getParameter("phone");
			if (phone == null || phone.equals("")) {
				sbuffer.append("您的电话号码不能为空！<br/>");
			}
			String password = request.getParameter("password");
			if (password == null || password.equals("")) {
				sbuffer.append("您的密码不能为空！<br/>");
			}

			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				MD5 getMD5 = new MD5();
				String psw = getMD5.getMD5ofStr(password);
				userInfoInstance = userService.login(phone, psw);
				if (userInfoInstance != null) {
					if (userInfoInstance.getIsAct() == 1) {
						session.setAttribute("userInfo", userInfoInstance);// 向session域中保存
						response.sendRedirect(request.getContextPath() + "/UserAction?operate=listCar&index=index");
					} else {
						request.setAttribute("msg", "账号还没有激活！！");
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}

				} else {
					request.setAttribute("msg", "用户名或密码错误！！");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {

		}

	}

	private void regist(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = null;
		StringBuffer sbuffer = null;
		UserInfo userInfoInstance = null;
		try {
			userService = new UserService();
			sbuffer = new StringBuffer();
			userInfoInstance = new UserInfo();
			userInfoInstance.setUserId(Uuid.uuid());
			String phone = request.getParameter("phone");
			if (phone == null || phone.equals("")) {
				sbuffer.append("您的电话号码不能为空！<br/>");
			} else {
				userInfoInstance.setPhone(phone);
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("phone", request.getParameter("phone"));
				request.setAttribute("password", request.getParameter("password"));
				request.setAttribute("password1", request.getParameter("password1"));
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("/regist.jsp").forward(request, response);
			} else {
				if (userService.getByPhone(phone) == null) {
					String str = Uuid.uuid();
					String code = str.substring(str.length() - 6, str.length());
					userInfoInstance.setCode(code);
					System.out.println("生成的验证码是：" + code);
					if (userService.save(userInfoInstance)) {

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
						String to = userInfoInstance.getPhone();// 获取收件人
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
						request.setAttribute("msg", "验证码已发送！");
						request.setAttribute("phone", request.getParameter("phone"));
						request.setAttribute("password", request.getParameter("password"));
						request.setAttribute("password1", request.getParameter("password1"));
						request.getRequestDispatcher("/regist.jsp").forward(request, response);
					} else {
						request.setAttribute("phone", request.getParameter("phone"));
						request.setAttribute("password", request.getParameter("password"));
						request.setAttribute("password1", request.getParameter("password1"));
						request.setAttribute("msg", "未知错误！");
						request.getRequestDispatcher("/regist.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("phone", request.getParameter("phone"));
					request.setAttribute("password", request.getParameter("password"));
					request.setAttribute("password1", request.getParameter("password1"));
					request.setAttribute("msg", "你已注册过了！");
					request.getRequestDispatcher("/regist.jsp").forward(request, response);
				}
			}
		} catch (

		Exception e)

		{

		}
	}

}
