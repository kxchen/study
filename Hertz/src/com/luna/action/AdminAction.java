package com.luna.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.luna.dto.AdminInfo;
import com.luna.dto.CarInfo;
import com.luna.dto.OrderDetail;
import com.luna.dto.UserInfo;
import com.luna.service.AdminService;
import com.luna.service.CarService;
import com.luna.service.OrderService;
import com.luna.service.UserService;
import com.luna.util.Uuid;

/**
 * Servlet implementation class AdminAction
 */
public class AdminAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAction() {
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
			if (operate.equals("login")) {
				login(request, response);
			} else if (operate.equals("listUser")) {
				listUser(request, response);
			} else if (operate.equals("updateUser")) {
				updateUser(request, response);
			} else if (operate.equals("deleteUser")) {
				deleteUser(request, response);
			} else if (operate.equals("listCar")) {
				listCar(request, response);
			} else if (operate.equals("updateCar")) {
				updateCar(request, response);
			} else if (operate.equals("deleteCar")) {
				deleteCar(request, response);
			} else if (operate.equals("saveCar")) {
				saveCar(request, response);
			} else if (operate.equals("listOrder")) {
				listOrder(request, response);
			} else if (operate.equals("deleteOrder")) {
				deleteOrder(request, response);
			} else if (operate.equals("updateOrder")) {
				updateOrder(request, response);
			} else if (operate.equals("quit")) {
				quit(request, response);
			} else if (operate.equals("showUser")) {
				showUser(request, response);
			} else if (operate.equals("showCar")) {
				showCar(request, response);
			} else if (operate.equals("showCombo")) {
				showCombo(request, response);
			} else if (operate.equals("showOrder")) {
				showOrder(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void showOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		OrderService userService = null;
		OrderDetail userInfoInstance = null;
		try {
			userService = new OrderService();
			userInfoInstance = new OrderDetail();
			String id = request.getParameter("orderDetailId").trim();
			userInfoInstance = userService.getOrderDetail(id);
			request.setAttribute("orderDetail", userInfoInstance);
			request.getRequestDispatcher("/admin/showOrder.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void showCombo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void showCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CarService carService = null;
		CarInfo carInfoInstance = null;
		try {
			carService = new CarService();
			carInfoInstance = new CarInfo();
			String id = request.getParameter("carId").trim();
			carInfoInstance = carService.get(id);
			request.setAttribute("carInfo", carInfoInstance);
			if (request.getParameter("method") != null && request.getParameter("method").equals("combo")) {
				request.getRequestDispatcher("/admin/showCombo.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/admin/showCar.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void showUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserService userService = null;
		UserInfo userInfoInstance = null;
		try {
			userService = new UserService();
			userInfoInstance = new UserInfo();
			String id = request.getParameter("userId").trim();
			userInfoInstance = userService.get(id);
			request.setAttribute("userInfo", userInfoInstance);
			request.getRequestDispatcher("/admin/showUser.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void listOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		OrderService orderService = null;
		List<OrderDetail> orderDetailList = null;
		StringBuffer queryString = null;
		int pageNo = 1;
		int pageSize = 10;
		int count = 0;
		try {
			orderDetailList = new ArrayList<OrderDetail>();
			orderService = new OrderService();
			queryString = new StringBuffer();
			if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals(""))
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			if (request.getParameter("userName") != null && !request.getParameter("userName").equals("")) {
				queryString.append(" and ");
				queryString.append(" user_name like '%");
				queryString.append(request.getParameter("userName"));
				queryString.append("%' ");

			}

			if (request.getParameter("phone") != null && !request.getParameter("phone").equals("")) {

				queryString.append(" and ");
				queryString.append(" phone like '%");
				queryString.append(request.getParameter("phone"));
				queryString.append("%' ");

			}

			if (request.getParameter("state") != null && !request.getParameter("state").equals("")) {
				queryString.append(" and ");
				String state = request.getParameter("state");
				switch (state) {
				case "2":
					queryString.append("is_return = 1");

					break;
				case "0":
					queryString.append("is_send = 0 and is_pay = 1");
					break;
				case "1":
					queryString.append("is_send = 1 and is_return = 0");
					break;
				default:
					break;
				}

			}
			if (request.getParameter("search") != null && !request.getParameter("search").equals("")) {

				queryString.append(" and ");
				queryString.append(" user_name like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" phone like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" brand like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" model like '%");
			}
			if (queryString.toString().equals("")) {
				orderDetailList = orderService.list(pageSize, pageNo, " where ( is_pay = 1 ) ");
				count = orderService.getCount(" where ( is_pay = 1 ) ");
			} else {
				orderDetailList = orderService.list(pageSize, pageNo,
						" where ( is_pay = 1 ) " + queryString.toString());
				count = orderService.getCount(" where ( is_pay = 1 ) " + queryString.toString());
			}
			request.setAttribute("orderDetailList", orderDetailList);
			request.setAttribute("userName", request.getParameter("userName"));
			request.setAttribute("phone", request.getParameter("phone"));
			request.setAttribute("isCer", request.getParameter("isCer"));
			request.setAttribute("search", request.getParameter("search"));
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", (int) Math.ceil((float) count / pageSize));
			request.getRequestDispatcher("/admin/listOrder.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		OrderService userService = null;
		OrderDetail userInfoInstance = null;
		try {
			userService = new OrderService();
			userInfoInstance = new OrderDetail();
			String id = request.getParameter("orderDetailId").trim();
			userInfoInstance = userService.getOrderDetail(id);
			String isCer = request.getParameter("Cer");
			if (isCer != null && isCer.equals("0")) {
				userInfoInstance.setIsSend(1);
			} else if (isCer != null && isCer.equals("1"))
				userInfoInstance.setIsReturn(1);
			else if (isCer != null && isCer.equals("2")) {
				userInfoInstance.setIsAbolish(2);
				userInfoInstance.setIsReturn(1);
			}

			if (userService.update(userInfoInstance)) {
				request.setAttribute("msg", "修改成功！");
				request.setAttribute("userInfo", userInfoInstance);
				listOrder(request, response);
			} else {
				request.setAttribute("msg", "修改失败！");
				request.setAttribute("userInfo", userInfoInstance);
				listOrder(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("orderDetailId").trim();
		OrderService userInfoService = null;
		try {
			userInfoService = new OrderService();
			if (userInfoService.delete(userInfoService.getOrderDetail(id))) {
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

	private void saveCar(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("UserAction method=saveCar");
		CarInfo carInfo = null;
		CarService carService = null;
		StringBuffer sbuffer = null;
		try {
			sbuffer = new StringBuffer();
			carService = new CarService();
			carInfo = new CarInfo();
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
			carInfo.setIsCheck(1);
			carInfo.setCriteria(request.getParameter("criteria"));

			if (request.getParameter("method") != null && request.getParameter("method").equals("saveCombo")) {

				String comboName = request.getParameter("comboName");
				if (comboName == null || comboName.equals("")) {
					sbuffer.append("套餐名不能为空！<br/>");
				} else {
					carInfo.setComboName(comboName);
				}

				String count = request.getParameter("count");
				if (count == null || count.equals("")) {
					sbuffer.append("数量不能为空！<br/>");
				} else {
					int cou = Integer.parseInt(count);
					carInfo.setCount(cou);
				}

				if (sbuffer.length() != 0) {
					request.setAttribute("carInfo", carInfo);
					request.setAttribute("msg", sbuffer.toString());
					request.getRequestDispatcher("/saveCombo.jsp").forward(request, response);
				} else {
					if (carService.save(carInfo)) {
						request.setAttribute("msg", "发布成功");
						response.sendRedirect(
								request.getContextPath() + "/AdminAction?operate=listCar&method=listCombo");
					} else {
						request.setAttribute("msg", "车辆信息提交失败");
						request.setAttribute("carInfo", carInfo);
						request.getRequestDispatcher("/saveCombo.jsp").forward(request, response);
					}
				}
			}

			else {
				if (sbuffer.length() != 0) {
					request.setAttribute("carInfo", carInfo);
					request.setAttribute("msg", sbuffer.toString());
					request.getRequestDispatcher("/saveCar.jsp").forward(request, response);
				} else {
					carInfo.setComboName("0");
					if (carService.save(carInfo)) {
						request.setAttribute("msg", "发布成功");
						listCar(request, response);
					} else {
						request.setAttribute("msg", "车辆信息提交失败");
						response.sendRedirect(request.getContextPath() + "/AdminAction?operate=listCar");
						request.getRequestDispatcher("/saveCar.jsp").forward(request, response);
					}
				}
			}
		} catch (

		Exception e)

		{

		}

	}

	private void listCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CarService carService = null;
		List<CarInfo> carInfoList = null;
		StringBuffer queryString = null;
		int pageNo = 1;
		int pageSize = 10;
		int count = 0;
		boolean flag = false;
		try {
			carInfoList = new ArrayList<CarInfo>();
			carService = new CarService();
			queryString = new StringBuffer();
			if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals(""))
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			if (request.getParameter("brand") != null && !request.getParameter("brand").equals("")) {
				queryString.append(" brand like '%");
				queryString.append(request.getParameter("brand"));
				queryString.append("%' ");
				flag = true;
			}

			if (request.getParameter("model") != null && !request.getParameter("model").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" model like '%");
				queryString.append(request.getParameter("model"));
				queryString.append("%' ");
				flag = true;
			}

			if (request.getParameter("isCheck") != null && !request.getParameter("isCheck").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" is_check like '%");
				queryString.append(request.getParameter("isCheck"));
				queryString.append("%' ");
				flag = true;
			}
			if (request.getParameter("search") != null && !request.getParameter("search").equals("")) {
				if (flag)
					queryString.append(" and  ");
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
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" purpose like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				flag = true;
			}

			if (request.getParameter("method") != null && request.getParameter("method").equals("listCombo")) {

				if (queryString.toString().equals("")) {
					carInfoList = carService.list(pageSize, pageNo, " where (combo_name like '%套餐%')");
					count = carService.getCount(" where (combo_name like '%套餐%')");
				} else {
					carInfoList = carService.list(pageSize, pageNo,
							" where ( combo_name like '%套餐%' ) and " + queryString.toString());
					count = carService.getCount(" where ( combo_name like '%套餐%' ) and " + queryString.toString());
				}
				request.setAttribute("carInfoList", carInfoList);
				request.setAttribute("userName", request.getParameter("userName"));
				request.setAttribute("brand", request.getParameter("brand"));
				request.setAttribute("model", request.getParameter("model"));
				request.setAttribute("isCheck", request.getParameter("isCheck"));
				request.setAttribute("search", request.getParameter("search"));
				request.setAttribute("count", count);
				request.setAttribute("pageNo", pageNo);
				request.setAttribute("totalPage", (int) Math.ceil((float) count / pageSize));
				request.getRequestDispatcher("/admin/listCombo.jsp").forward(request, response);
			} else {
				if (queryString.toString().equals("")) {
					carInfoList = carService.list(pageSize, pageNo, " where ( combo_name <> '婚庆套餐' ) ");
					count = carService.getCount(" where ( combo_name <> '婚庆套餐' ) ");
				} else {
					carInfoList = carService.list(pageSize, pageNo,
							" where (combo_name <> '婚庆套餐' ) and " + queryString.toString());
					count = carService.getCount("  where ( combo_name <> '婚庆套餐' ) and " + queryString.toString());
				}
				request.setAttribute("carInfoList", carInfoList);
				request.setAttribute("userName", request.getParameter("userName"));
				request.setAttribute("brand", request.getParameter("brand"));
				request.setAttribute("model", request.getParameter("model"));
				request.setAttribute("isCheck", request.getParameter("isCheck"));
				request.setAttribute("search", request.getParameter("search"));
				request.setAttribute("count", count);
				request.setAttribute("pageNo", pageNo);
				request.setAttribute("totalPage", (int) Math.ceil((float) count / pageSize));
				request.getRequestDispatcher("/admin/listCar.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void updateCar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CarService carService = null;
		CarInfo carInfoInstance = null;
		try {
			carService = new CarService();
			carInfoInstance = new CarInfo();
			String id = request.getParameter("carId").trim();
			carInfoInstance = carService.get(id);
			String isCer = request.getParameter("Check");
			if (isCer != null && isCer.equals("0")) {
				carInfoInstance.setIsCheck(1);
			} else
				carInfoInstance.setIsCheck(0);
			if (carService.update(carInfoInstance)) {
				request.setAttribute("msg", "修改成功！");
				request.setAttribute("carInfo", carInfoInstance);
				listCar(request, response);
			} else {
				request.setAttribute("msg", "修改失败！");
				request.setAttribute("carInfo", carInfoInstance);
				listCar(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void deleteCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("carId").trim();
		CarService carInfoService = null;
		try {
			carInfoService = new CarService();
			if (carInfoService.delete(carInfoService.get(id))) {
				request.setAttribute("msg", "删除成功！");
				listCar(request, response);
			} else {
				request.setAttribute("msg", "删除失败");
				listCar(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserService userService = null;
		List<UserInfo> userInfoList = null;
		StringBuffer queryString = null;
		int pageNo = 1;
		int pageSize = 10;
		int count = 0;
		try {
			userInfoList = new ArrayList<UserInfo>();
			userService = new UserService();
			queryString = new StringBuffer();
			if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals(""))
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			if (request.getParameter("userName") != null && !request.getParameter("userName").equals("")) {
				queryString.append(" and ");
				queryString.append(" user_name like '%");
				queryString.append(request.getParameter("userName"));
				queryString.append("%' ");

			}

			if (request.getParameter("phone") != null && !request.getParameter("phone").equals("")) {

				queryString.append(" and ");
				queryString.append(" phone like '%");
				queryString.append(request.getParameter("phone"));
				queryString.append("%' ");

			}

			if (request.getParameter("isCer") != null && !request.getParameter("isCer").equals("")) {
				queryString.append(" and ");
				queryString.append(" is_cer like '%");
				queryString.append(request.getParameter("isCer"));
				queryString.append("%' ");

			}
			if (request.getParameter("search") != null && !request.getParameter("search").equals("")) {

				queryString.append(" and ");
				queryString.append(" user_name like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
				queryString.append(" or ");
				queryString.append(" phone like '%");
				queryString.append(request.getParameter("search"));
				queryString.append("%' ");
			}
			if (queryString.toString().equals("")) {
				userInfoList = userService.list(pageSize, pageNo, " where ( is_act = 1 ) ");
				count = userService.getCount(" where ( is_act = 1 ) ");
			} else {
				userInfoList = userService.list(pageSize, pageNo, " where ( is_act = 1 ) " + queryString.toString());
				count = userService.getCount(" where ( is_act = 1 ) " + queryString.toString());
			}
			request.setAttribute("userInfoList", userInfoList);
			request.setAttribute("userName", request.getParameter("userName"));
			request.setAttribute("phone", request.getParameter("phone"));
			request.setAttribute("isCer", request.getParameter("isCer"));
			request.setAttribute("search", request.getParameter("search"));
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", (int) Math.ceil((float) count / pageSize));
			request.getRequestDispatcher("/admin/default.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserService userService = null;
		UserInfo userInfoInstance = null;
		try {
			userService = new UserService();
			userInfoInstance = new UserInfo();
			String id = request.getParameter("userId").trim();
			userInfoInstance = userService.get(id);
			String isCer = request.getParameter("Cer");
			if (isCer != null && isCer.equals("1")) {
				userInfoInstance.setIsCer(2);
			} else
				userInfoInstance.setIsCer(1);
			if (userService.update(userInfoInstance)) {
				request.setAttribute("msg", "修改成功！");
				request.setAttribute("userInfo", userInfoInstance);
				listUser(request, response);
			} else {
				request.setAttribute("msg", "修改失败！");
				request.setAttribute("userInfo", userInfoInstance);
				listUser(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("userId").trim();
		UserService userInfoService = null;
		try {
			userInfoService = new UserService();
			if (userInfoService.delete(userInfoService.get(id))) {
				request.setAttribute("msg", "删除成功！");
				listUser(request, response);
			} else {
				request.setAttribute("msg", "删除失败");
				listUser(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void quit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();// 获取session
		try {
			if (session != null) {
				session.removeAttribute("AdminInfo");
				response.sendRedirect(request.getContextPath() + "/UserAction?operate=listCar&index=index");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		AdminService adminService = null;
		StringBuffer sbuffer = null;
		AdminInfo adminInfoInstance = null;
		String adminName = "";
		String password = "";
		try {
			adminService = new AdminService();
			sbuffer = new StringBuffer();
			adminInfoInstance = new AdminInfo();
			if (request.getParameter("adminName") == null || request.getParameter("adminName").equals("")) {
				sbuffer.append("您的登录名不能为空！<br/>");
			} else {
				adminName = request.getParameter("adminName");
			}
			if (request.getParameter("password") == null || request.getParameter("password").equals("")) {
				sbuffer.append("您的密码名不能为空！<br/>");
			} else {
				password = request.getParameter("password");
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("admin/login.jsp").forward(request, response);
			} else {
				adminInfoInstance = adminService.login(adminName, password);
				if (adminInfoInstance != null) {
					session.setAttribute("adminInfo", adminInfoInstance);// 向session域中保存
					listUser(request, response);
				} else {
					request.setAttribute("msg", "用户名或密码错误");
					request.getRequestDispatcher("admin/login.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}
}
