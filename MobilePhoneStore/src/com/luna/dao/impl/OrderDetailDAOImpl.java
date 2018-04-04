package com.luna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.luna.dao.IOrderDetailDAO;
import com.luna.database.DataBase;
import com.luna.dto.Brand;
import com.luna.dto.MobilePhoneInfo;
import com.luna.dto.NetworkType;
import com.luna.dto.OperatingSystem;
import com.luna.dto.OrderDetail;
import com.luna.dto.OrderInfo;
import com.luna.dto.ScreenSize;
import com.luna.dto.UserInfo;

public class OrderDetailDAOImpl implements IOrderDetailDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}

	@Override
	public List<OrderDetail> getOrderDetail(long orderId) throws Exception {
		List<OrderDetail> orderDetailList = null;
		OrderDetail orderDetail = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		MobilePhoneInfo mobilePhoneInfo = null;
		Brand brand = null;
		NetworkType networkType = null;
		OperatingSystem operatingSystem = null;
		ScreenSize screenSize = null;
		OrderInfo orderInfo = null;
		UserInfo userInfo = null;
		try {
			String sql = "select * from order_detail_view where order_id=?";
			pst = conn.prepareStatement(sql);
			pst.setLong(1, orderId);
			rs = pst.executeQuery();
			orderDetailList = new ArrayList<OrderDetail>();
			while (rs.next()) {
				orderDetail = new OrderDetail();
				mobilePhoneInfo = new MobilePhoneInfo();

				brand = new Brand();
				brand.setBrandName(rs.getString("brand_name"));
				brand.setBrandId(rs.getInt("brand_id"));

				networkType = new NetworkType();
				networkType.setNetTypeName((rs.getString("net_type_name")));
				networkType.setNetTypeId(rs.getInt("net_type_id"));

				operatingSystem = new OperatingSystem();
				operatingSystem.setOpeSystemName(rs.getString("ope_system_name"));
				operatingSystem.setOpeSystemId(rs.getInt("ope_system_id"));

				screenSize = new ScreenSize();
				screenSize.setScrSizeName(rs.getString("scr_size_name"));
				screenSize.setScrSizeId(rs.getInt("scr_size_id"));

				mobilePhoneInfo.setBrand(brand);
				mobilePhoneInfo.setNetworkType(networkType);
				mobilePhoneInfo.setOperatingSystem(operatingSystem);
				mobilePhoneInfo.setScreenSize(screenSize);

				mobilePhoneInfo.setModel(rs.getString("model"));
				mobilePhoneInfo.setColor(rs.getString("color"));
				mobilePhoneInfo.setDescipt(rs.getString("descipt"));
				mobilePhoneInfo.setImgPath(rs.getString("img_path"));
				mobilePhoneInfo.setPixels(rs.getString("pixels"));
				mobilePhoneInfo.setRam(rs.getString("ram"));
				mobilePhoneInfo.setRom(rs.getString("rom"));
				mobilePhoneInfo.setPrice(rs.getDouble("price"));
				mobilePhoneInfo.setRealPrice(rs.getDouble("real_price"));
				mobilePhoneInfo.setRegDate(rs.getDate("reg_date"));
				mobilePhoneInfo.setMobPhoneId(rs.getInt("mob_phone_id"));
				mobilePhoneInfo.setState(rs.getInt("state"));

				orderInfo = new OrderInfo();

				userInfo = new UserInfo();
				userInfo.setLoginName(rs.getString("login_name"));
				userInfo.setAddress(rs.getString("address"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setRealName(rs.getString("real_name"));
				userInfo.setRegtime(rs.getDate("regtime"));
				userInfo.setUserId(rs.getInt("user_id"));

				orderInfo.setUser(userInfo);
				orderInfo.setIsDeliver(rs.getInt("is_deliver"));
				orderInfo.setIsPay(rs.getInt("is_pay"));
				orderInfo.setOrderId(rs.getLong("order_id"));
				orderInfo.setSubmitTime(rs.getDate("submit_time"));
				orderInfo.setTotalPrice(rs.getDouble("total_price"));

				orderDetail.setMobilePhone(mobilePhoneInfo);
				orderDetail.setOrder(orderInfo);
				orderDetail.setAmount(rs.getDouble("amount"));
				orderDetail.setBuyPrice(rs.getDouble("buy_price"));
				orderDetail.setOrderDetailId(rs.getInt("order_detail_id"));
				orderDetailList.add(orderDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return orderDetailList;
	}

	@Override
	public boolean addOrderDetail(OrderDetail orderDetail) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into order_detail(order_id,mob_phone_id,buy_price,amount) values (?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setLong(i++, orderDetail.getOrder().getOrderId());
			pst.setInt(i++, orderDetail.getMobilePhone().getMobPhoneId());
			pst.setDouble(i++, orderDetail.getBuyPrice());
			pst.setDouble(i++, orderDetail.getAmount());
			if (pst.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(pst);
		}
		return flag;
	}

	public boolean delete(OrderInfo orderInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from order_detail where order_id=?";
			pst = conn.prepareStatement(sql);
			pst.setLong(1, orderInfo.getOrderId());
			if (pst.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(pst);
		}
		return flag;
	}
}
