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

import com.luna.dto.Brand;
import com.luna.dto.MobilePhoneInfo;
import com.luna.dto.NetworkType;
import com.luna.dto.OperatingSystem;
import com.luna.dto.ScreenSize;
import com.luna.dto.UserInfo;
import com.luna.service.BrandService;
import com.luna.service.MobilePhoneInfoService;
import com.luna.service.NetworkTypeService;
import com.luna.service.OperatingSystemService;
import com.luna.service.ScreenSizeService;
import com.luna.service.UserInfoService;

/**
 * Servlet implementation class MobilePhoneInfoController
 */
public class MobilePhoneInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MobilePhoneInfoController() {
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
			if (operate.equals("create")) {
				create(request, response);
			} else if (operate.equals("save")) {
				save(request, response);
			} else if (operate.equals("list")) {
				list(request, response);
			} else if (operate.equals("update")) {
				update(request, response);
			} else if (operate.equals("show")) {
				show(request, response);
			} else if (operate.equals("edit")) {
				edit(request, response);
			} else if (operate.equals("delete")) {
				delete(request, response);
			} else if (operate.equals("changeState")) {
				changeState(request, response);
			} else if (operate.equals("hotSale")) {
				hotSale(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void hotSale(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MobilePhoneInfoService mobilePhoneInfoService = null;
		List<MobilePhoneInfo> mobilePhoneInfoList = null;
		try {
			mobilePhoneInfoList = new ArrayList<MobilePhoneInfo>();
			mobilePhoneInfoService = new MobilePhoneInfoService();
			mobilePhoneInfoList = mobilePhoneInfoService.hotSale();
			request.setAttribute("mobilePhoneInfoList", mobilePhoneInfoList);
			mobilePhoneInfoList.size();
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void show(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MobilePhoneInfoService mobilePhoneInfoService = null;
		MobilePhoneInfo mobilePhoneInfoInstance = null;
		try {
			mobilePhoneInfoService = new MobilePhoneInfoService();
			mobilePhoneInfoInstance = new MobilePhoneInfo();
			int id = Integer.parseInt(request.getParameter("mobPhoneId").trim());
			mobilePhoneInfoInstance = mobilePhoneInfoService.get(id);
			request.setAttribute("mobilePhoneInfo", mobilePhoneInfoInstance);
			request.getRequestDispatcher("/mobilePhone/show.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MobilePhoneInfoService mobilePhoneInfoService = null;
		MobilePhoneInfo mobilePhoneInfoInstance = null;
		BrandService brandService = null;
		List<Brand> brandList = null;
		NetworkTypeService networkTypeService = null;
		List<NetworkType> networkTypeList = null;
		OperatingSystemService operatingSystemService = null;
		List<OperatingSystem> operatingSystemList = null;
		ScreenSizeService screenSizeService = null;
		List<ScreenSize> screenSizeList = null;
		try {
			mobilePhoneInfoService = new MobilePhoneInfoService();
			mobilePhoneInfoInstance = new MobilePhoneInfo();
			int id = Integer.parseInt(request.getParameter("mobPhoneId").trim());
			mobilePhoneInfoInstance = mobilePhoneInfoService.get(id);
			request.setAttribute("mobilePhoneInfo", mobilePhoneInfoInstance);

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
			request.setAttribute("screenSizeList", screenSizeList);

			request.getRequestDispatcher("/mobilePhone/update.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BrandService brandService = null;
		List<Brand> brandList = null;
		NetworkTypeService networkTypeService = null;
		List<NetworkType> networkTypeList = null;
		OperatingSystemService operatingSystemService = null;
		List<OperatingSystem> operatingSystemList = null;
		ScreenSizeService screenSizeService = null;
		List<ScreenSize> screenSizeList = null;
		try {
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
			request.setAttribute("screenSizeList", screenSizeList);

			request.getRequestDispatcher("/mobilePhone/save.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("mobPhoneId").trim());
		MobilePhoneInfoService mobilePhoneInfoService = null;
		try {
			mobilePhoneInfoService = new MobilePhoneInfoService();
			if (mobilePhoneInfoService.delete(mobilePhoneInfoService.get(id))) {
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
		MobilePhoneInfoService mobilePhoneInfoService = null;
		StringBuffer sbuffer = null;
		BrandService brandService = null;
		NetworkTypeService networkTypeService = null;
		OperatingSystemService operatingSystemService = null;
		ScreenSizeService screenSizeService = null;
		MobilePhoneInfo mobilePhoneInfoInstance = null;
		try {
			brandService = new BrandService();
			networkTypeService = new NetworkTypeService();
			operatingSystemService = new OperatingSystemService();
			mobilePhoneInfoService = new MobilePhoneInfoService();
			screenSizeService = new ScreenSizeService();
			sbuffer = new StringBuffer();
			mobilePhoneInfoInstance = new MobilePhoneInfo();
			int mobPhoneId = Integer.parseInt(request.getParameter("mobPhoneId").trim());
			mobilePhoneInfoInstance = mobilePhoneInfoService.get(mobPhoneId);

			String brandId = request.getParameter("brandId");
			if (brandId == null || brandId.equals("")) {
				sbuffer.append("品牌不能为空<br/>");
			} else {
				int id = Integer.parseInt(request.getParameter("brandId").trim());
				Brand brand = brandService.get(id);
				mobilePhoneInfoInstance.setBrand(brand);
			}
			String model = request.getParameter("model");
			if (model == null || model.equals("")) {
				sbuffer.append("型号不能为空！<br/>");
			} else {
				mobilePhoneInfoInstance.setModel(model);
			}
			String opeSystemId = request.getParameter("opeSystemId");
			if (opeSystemId == null || opeSystemId.equals("")) {
				sbuffer.append("操作系统不能为空！<br/>");
			} else {
				int id = Integer.parseInt(request.getParameter("opeSystemId").trim());
				OperatingSystem operatingSystem = operatingSystemService.get(id);
				mobilePhoneInfoInstance.setOperatingSystem(operatingSystem);
			}
			String netTypeId = request.getParameter("netTypeId");
			if (netTypeId == null || netTypeId.equals("")) {
				sbuffer.append("网络制式不能为空！<br/>");
			} else {
				int id = Integer.parseInt(request.getParameter("netTypeId").trim());
				NetworkType networkType = networkTypeService.get(id);
				mobilePhoneInfoInstance.setNetworkType(networkType);
			}
			String scrSizeId = request.getParameter("scrSizeId");
			if (scrSizeId == null || scrSizeId.equals("")) {
				sbuffer.append("屏幕尺寸不能为空！<br/>");
			} else {
				int id = Integer.parseInt(request.getParameter("scrSizeId").trim());
				ScreenSize screenSize = screenSizeService.get(id);
				mobilePhoneInfoInstance.setScreenSize(screenSize);
			}
			String color = request.getParameter("color");
			if (color == null || color.equals("")) {
				sbuffer.append("颜色不能为空！<br/>");
			} else {
				mobilePhoneInfoInstance.setColor(color);
			}
			String price = request.getParameter("price");
			if (price == null || price.equals("")) {
				sbuffer.append("价格不能为空！<br/>");
			} else {
				double p = Double.parseDouble(price);
				mobilePhoneInfoInstance.setPrice(p);
			}
			String realPrice = request.getParameter("realPrice");
			if (realPrice == null || realPrice.equals("")) {
				sbuffer.append("实际价格不能为空！<br/>");
			} else {
				double p = Double.parseDouble(realPrice);
				mobilePhoneInfoInstance.setRealPrice(p);
			}

			String descipt = request.getParameter("descipt");
			mobilePhoneInfoInstance.setDescipt(descipt);

			String imgPath = request.getParameter("imgPath");
			if (imgPath != null && !imgPath.equals(""))
				mobilePhoneInfoInstance.setImgPath(imgPath);

			// 日期格式工具
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();// 得到系统当前时间
			String strDate = dateformat.format(date);// 将系统当前时间格式化
			Date regDate = java.sql.Date.valueOf(strDate);
			mobilePhoneInfoInstance.setRegDate(regDate);

			String pixels = request.getParameter("pixels");
			mobilePhoneInfoInstance.setPixels(pixels);

			String ram = request.getParameter("ram");
			mobilePhoneInfoInstance.setRam(ram);

			String rom = request.getParameter("rom");
			mobilePhoneInfoInstance.setRom(rom);
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				list(request, response);
			} else {
				if (mobilePhoneInfoService.update(mobilePhoneInfoInstance)) {
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

	private void changeState(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MobilePhoneInfoService mobilePhoneInfoService = null;
		StringBuffer sbuffer = null;
		MobilePhoneInfo mobilePhoneInfoInstance = null;
		try {
			sbuffer = new StringBuffer();
			mobilePhoneInfoService = new MobilePhoneInfoService();
			mobilePhoneInfoInstance = new MobilePhoneInfo();
			int mobPhoneId = Integer.parseInt(request.getParameter("mobPhoneId").trim());
			mobilePhoneInfoInstance = mobilePhoneInfoService.get(mobPhoneId);
			int state = Integer.parseInt(request.getParameter("state").trim());
			if (state == 0) {
				mobilePhoneInfoInstance.setState(1);
			} else {
				mobilePhoneInfoInstance.setState(0);
			}
			// 日期格式工具
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();// 得到系统当前时间
			String strDate = dateformat.format(date);// 将系统当前时间格式化
			Date regDate = java.sql.Date.valueOf(strDate);
			mobilePhoneInfoInstance.setRegDate(regDate);
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				list(request, response);
			} else {
				if (mobilePhoneInfoService.update(mobilePhoneInfoInstance)) {
					request.setAttribute("msg", "操作成功！");
					// request.getRequestDispatcher("/mobilePhone/category.jsp").forward(request,
					// response);
					list(request, response);
				} else {
					request.setAttribute("msg", "操作失败！");
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
		int pageSize = 10;
		int count = 0;
		String query;
		try {
			mobilePhoneInfoList = new ArrayList<MobilePhoneInfo>();
			mobilePhoneInfoService = new MobilePhoneInfoService();
			queryString = new StringBuffer();
			if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals(""))
				pageNo = Integer.parseInt(request.getParameter("pageNo"));

			if (request.getParameter("state") != null && !request.getParameter("state").equals("")) {
				queryString.append(" state = '");
				queryString.append(request.getParameter("state"));
				queryString.append("' ");
				flag = true;
			}
			if (request.getParameter("brandName") != null && !request.getParameter("brandName").equals("")) {
				if (flag)
					queryString.append(" and ");
				queryString.append(" brand_name like '%");
				queryString.append(request.getParameter("brandName"));
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
				query = " where ";
			} else {
				query = " ";
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

			request.setAttribute("screenSizeList", screenSizeList);
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", (int) Math.ceil((float) count / pageSize));
			request.setAttribute("state", request.getParameter("state"));
			request.setAttribute("brandName", request.getParameter("brandName"));
			request.setAttribute("model", request.getParameter("model"));
			request.setAttribute("search", request.getParameter("search"));
			request.getRequestDispatcher("mobilePhone/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MobilePhoneInfoService mobilePhoneInfoService = null;
		StringBuffer sbuffer = null;
		BrandService brandService = null;
		NetworkTypeService networkTypeService = null;
		OperatingSystemService operatingSystemService = null;
		ScreenSizeService screenSizeService = null;
		MobilePhoneInfo mobilePhoneInfoInstance = null;
		try {
			brandService = new BrandService();
			networkTypeService = new NetworkTypeService();
			operatingSystemService = new OperatingSystemService();
			mobilePhoneInfoService = new MobilePhoneInfoService();
			screenSizeService = new ScreenSizeService();
			sbuffer = new StringBuffer();
			mobilePhoneInfoInstance = new MobilePhoneInfo();
			String brandId = request.getParameter("brandId");
			if (brandId == null || brandId.equals("")) {
				sbuffer.append("品牌不能为空<br/>");
			} else {
				int id = Integer.parseInt(request.getParameter("brandId").trim());
				Brand brand = brandService.get(id);
				mobilePhoneInfoInstance.setBrand(brand);
			}
			String model = request.getParameter("model");
			if (model == null || model.equals("")) {
				sbuffer.append("型号不能为空！<br/>");
			} else {
				mobilePhoneInfoInstance.setModel(model);
			}
			String opeSystemId = request.getParameter("opeSystemId");
			if (opeSystemId == null || opeSystemId.equals("")) {
				sbuffer.append("操作系统不能为空！<br/>");
			} else {
				int id = Integer.parseInt(request.getParameter("opeSystemId").trim());
				OperatingSystem operatingSystem = operatingSystemService.get(id);
				mobilePhoneInfoInstance.setOperatingSystem(operatingSystem);
			}
			String netTypeId = request.getParameter("netTypeId");
			if (netTypeId == null || netTypeId.equals("")) {
				sbuffer.append("网络制式不能为空！<br/>");
			} else {
				int id = Integer.parseInt(request.getParameter("netTypeId").trim());
				NetworkType networkType = networkTypeService.get(id);
				mobilePhoneInfoInstance.setNetworkType(networkType);
			}
			String scrSizeId = request.getParameter("scrSizeId");
			if (scrSizeId == null || scrSizeId.equals("")) {
				sbuffer.append("屏幕尺寸不能为空！<br/>");
			} else {
				int id = Integer.parseInt(request.getParameter("scrSizeId").trim());
				ScreenSize screenSize = screenSizeService.get(id);
				mobilePhoneInfoInstance.setScreenSize(screenSize);
			}
			String color = request.getParameter("color");
			if (color == null || color.equals("")) {
				sbuffer.append("颜色不能为空！<br/>");
			} else {
				mobilePhoneInfoInstance.setColor(color);
			}
			String price = request.getParameter("price");
			if (price == null || price.equals("")) {
				sbuffer.append("价格不能为空！<br/>");
			} else {
				double p = Double.parseDouble(price);
				mobilePhoneInfoInstance.setPrice(p);
			}
			String realPrice = request.getParameter("realPrice");
			if (realPrice == null || realPrice.equals("")) {
				sbuffer.append("实际价格不能为空！<br/>");
			} else {
				double p = Double.parseDouble(realPrice);
				mobilePhoneInfoInstance.setRealPrice(p);
			}

			String descipt = request.getParameter("descipt");
			mobilePhoneInfoInstance.setDescipt(descipt);

			String imgPath = request.getParameter("imgPath");
			mobilePhoneInfoInstance.setImgPath(imgPath);

			// 日期格式工具
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();// 得到系统当前时间
			String strDate = dateformat.format(date);// 将系统当前时间格式化
			Date regDate = java.sql.Date.valueOf(strDate);
			mobilePhoneInfoInstance.setRegDate(regDate);

			String pixels = request.getParameter("pixels");
			mobilePhoneInfoInstance.setPixels(pixels);

			String ram = request.getParameter("ram");
			mobilePhoneInfoInstance.setRam(ram);

			String rom = request.getParameter("rom");
			mobilePhoneInfoInstance.setRom(rom);
			mobilePhoneInfoInstance.setState(0);
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				list(request, response);
			} else {
				if (mobilePhoneInfoService.save(mobilePhoneInfoInstance)) {
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
