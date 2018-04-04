package com.luna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.luna.dao.IOrderDetailDAO;
import com.luna.db.DataBase;
import com.luna.dto.CarInfo;
import com.luna.dto.OrderDetail;
import com.luna.dto.OrderInfo;
import com.luna.dto.UserInfo;

/**
 * @ClassName: OrderDetailDAOImpl
 * @Description: 订单详情业务实现类
 * @author c-kx@outlook.com
 * @date 2015年12月24日 下午8:44:27
 * 
 */
public class OrderDetailDAOImpl implements IOrderDetailDAO {
	private Connection conn = null;

	@Override
	public List<OrderDetail> getOrderDetail(String orderId) throws Exception {
		List<OrderDetail> orderDetailList = null;
		OrderDetail orderDetail = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		CarInfo carInfo = null;
		OrderInfo orderInfo = null;
		UserInfo userInfo = null;
		try {
			String sql = "select * from order_detail_view where order_id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, orderId);
			rs = pst.executeQuery();
			orderDetailList = new ArrayList<OrderDetail>();
			while (rs.next()) {
				orderDetail = new OrderDetail();
				orderDetail.setOrderDetailId(rs.getString("order_detail_id"));
				orderDetail.setQuantity(rs.getInt("quantity"));
				orderDetail.setReaPrice(rs.getDouble("rea_price"));
				orderDetail.setTakeTime(rs.getDate("take_time"));
				orderDetail.setReaRetTime(rs.getDate("rea_ret_time"));
				orderDetail.setRetTime(rs.getDate("ret_time"));
				orderDetail.setIsSend(rs.getInt("is_send"));
				orderDetail.setIsReceive(rs.getInt("is_receive"));
				orderDetail.setIsReturn(rs.getInt("is_return"));
				orderDetail.setIsAbolish(rs.getInt("is_abolish"));
				orderDetail.setPaid(rs.getDouble("paid"));

				// 获取车辆信息
				carInfo = new CarInfo();
				carInfo.setBrand(rs.getString("brand"));
				carInfo.setModel(rs.getString("model"));
				carInfo.setType(rs.getString("type"));
				carInfo.setBrand(rs.getString("brand"));
				carInfo.setBuyDate(rs.getDate("buy_date"));
				carInfo.setKm(rs.getString("km"));
				carInfo.setCarefulImg(rs.getString("careful_img"));
				carInfo.setCarImg(rs.getString("car_img"));
				carInfo.setGears(rs.getString("gears"));
				carInfo.setCc(rs.getString("cc"));
				carInfo.setColor(rs.getString("color"));
				carInfo.setCriteria(rs.getString("criteria"));
				carInfo.setPrice(rs.getDouble("price"));
				carInfo.setSeats(rs.getString("seats"));
				carInfo.setIsCheck(rs.getInt("is_check"));
				carInfo.setIsRent(rs.getInt("is_rent"));
				carInfo.setRelTime(rs.getDate("rel_time"));
				carInfo.setCarId(rs.getString("car_id"));
				carInfo.setOwnerId(rs.getString("owner_id"));
				carInfo.setPurpose(rs.getString("purpose"));
				carInfo.setRemarks(rs.getString("remarks"));
				carInfo.setPaidPer(rs.getDouble("paid_per"));
				carInfo.setComboName(rs.getString("combo_name"));
				carInfo.setCount(rs.getInt("count"));
				orderDetail.setCarInfo(carInfo);

				// 获取订单信息（包含用户信息）
				orderInfo = new OrderInfo();
				orderInfo.setOrderId(rs.getString("order_id"));

				orderInfo.setBuyTime(rs.getDate("buy_time"));
				orderInfo.setPay(rs.getDouble("pay"));
				orderInfo.setReaPay(rs.getDouble("rea_pay"));
				orderInfo.setIsPay(rs.getInt("is_pay"));

				userInfo = new UserInfo();
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setIdNum(rs.getString("id_num"));
				userInfo.setIdImg(rs.getString("id_img"));
				userInfo.setDriverImg(rs.getString("driver_img"));
				userInfo.setIsAct(rs.getInt("is_act"));
				userInfo.setIsCer(rs.getInt("is_cer"));
				userInfo.setCode(rs.getString("code"));
				userInfo.setUserId(rs.getString("user_id"));
				userInfo.setRemBal(rs.getDouble("rem_bal"));
				userInfo.setDisCou(rs.getDouble("dis_cou"));
				userInfo.setAddress(rs.getString("address"));
				userInfo.setOthPhone(rs.getString("oth_phone"));
				userInfo.setOthName(rs.getString("oth_name"));
				userInfo.setOther(rs.getString("other"));
				userInfo.setHeadImg(rs.getString("head_img"));
				orderInfo.setUserInfo(userInfo);
				orderDetail.setOrderInfo(orderInfo);

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
	public boolean save(OrderDetail orderDetail) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into order_detail(order_detail_id,"
					+ "order_id,car_id,rea_price,quantity,take_time,rea_ret_time,ret_time,is_send,is_receive,is_return,is_abolish,paid) values "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, orderDetail.getOrderDetailId());
			pst.setString(i++, orderDetail.getOrderInfo().getOrderId());
			pst.setString(i++, orderDetail.getCarInfo().getCarId());
			pst.setDouble(i++, orderDetail.getReaPrice());
			pst.setInt(i++, orderDetail.getQuantity());

			java.sql.Date takeTime = (java.sql.Date) orderDetail.getTakeTime();
			pst.setDate(i++, takeTime);
			java.sql.Date reaRetTime = (java.sql.Date) orderDetail.getReaRetTime();
			pst.setDate(i++, reaRetTime);
			java.sql.Date retTime = (java.sql.Date) orderDetail.getRetTime();
			pst.setDate(i++, retTime);
			pst.setInt(i++, orderDetail.getIsSend());
			pst.setInt(i++, orderDetail.getIsReceive());
			pst.setInt(i++, orderDetail.getIsReturn());
			pst.setInt(i++, orderDetail.getIsAbolish());
			pst.setDouble(i++, orderDetail.getPaid());
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

	@Override
	public boolean delete(OrderInfo orderInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from order_detail where order_id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, orderInfo.getOrderId());
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

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean delete(OrderDetail orderDetail) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from order_detail where order_detail_id=?";
			System.out.println("OrderDetailDAOImpl method=delete sql="+sql);
			pst = conn.prepareStatement(sql);
			pst.setString(1, orderDetail.getOrderDetailId());
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

	@Override
	public OrderDetail get(String orderDetailId) throws Exception {
		OrderDetail orderDetail = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		CarInfo carInfo = null;
		OrderInfo orderInfo = null;
		UserInfo userInfo = null;
		try {
			String sql = "select * from order_detail_view where order_detail_id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, orderDetailId);
			rs = pst.executeQuery();
			while (rs.next()) {
				orderDetail = new OrderDetail();
				orderDetail.setOrderDetailId(rs.getString("order_detail_id"));
				orderDetail.setQuantity(rs.getInt("quantity"));
				orderDetail.setReaPrice(rs.getDouble("rea_price"));
				orderDetail.setTakeTime(rs.getDate("take_time"));
				orderDetail.setReaRetTime(rs.getDate("rea_ret_time"));
				orderDetail.setRetTime(rs.getDate("ret_time"));
				orderDetail.setIsSend(rs.getInt("is_send"));
				orderDetail.setIsReceive(rs.getInt("is_receive"));
				orderDetail.setIsReturn(rs.getInt("is_return"));
				orderDetail.setIsAbolish(rs.getInt("is_abolish"));
				orderDetail.setPaid(rs.getDouble("paid"));

				// 获取车辆信息
				carInfo = new CarInfo();
				carInfo.setBrand(rs.getString("brand"));
				carInfo.setModel(rs.getString("model"));
				carInfo.setType(rs.getString("type"));
				carInfo.setBrand(rs.getString("brand"));
				carInfo.setBuyDate(rs.getDate("buy_date"));
				carInfo.setKm(rs.getString("km"));
				carInfo.setCarefulImg(rs.getString("careful_img"));
				carInfo.setCarImg(rs.getString("car_img"));
				carInfo.setGears(rs.getString("gears"));
				carInfo.setCc(rs.getString("cc"));
				carInfo.setColor(rs.getString("color"));
				carInfo.setCriteria(rs.getString("criteria"));
				carInfo.setPrice(rs.getDouble("price"));
				carInfo.setSeats(rs.getString("seats"));
				carInfo.setIsCheck(rs.getInt("is_check"));
				carInfo.setIsRent(rs.getInt("is_rent"));
				carInfo.setRelTime(rs.getDate("rel_time"));
				carInfo.setCarId(rs.getString("car_id"));
				carInfo.setOwnerId(rs.getString("owner_id"));
				carInfo.setPurpose(rs.getString("purpose"));
				carInfo.setRemarks(rs.getString("remarks"));
				carInfo.setPaidPer(rs.getDouble("paid_per"));
				carInfo.setComboName(rs.getString("combo_name"));
				carInfo.setCount(rs.getInt("count"));
				orderDetail.setCarInfo(carInfo);

				// 获取订单信息（包含用户信息）
				orderInfo = new OrderInfo();
				orderInfo.setOrderId(rs.getString("order_id"));
				orderInfo.setIsPay(rs.getInt("is_pay"));
				orderInfo.setBuyTime(rs.getDate("buy_time"));
				orderInfo.setPay(rs.getDouble("pay"));
				orderInfo.setReaPay(rs.getDouble("rea_pay"));

				userInfo = new UserInfo();
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setIdNum(rs.getString("id_num"));
				userInfo.setIdImg(rs.getString("id_img"));
				userInfo.setDriverImg(rs.getString("driver_img"));
				userInfo.setIsAct(rs.getInt("is_act"));
				userInfo.setIsCer(rs.getInt("is_cer"));
				userInfo.setCode(rs.getString("code"));
				userInfo.setUserId(rs.getString("user_id"));
				userInfo.setRemBal(rs.getDouble("rem_bal"));
				userInfo.setDisCou(rs.getDouble("dis_cou"));
				userInfo.setAddress(rs.getString("address"));
				userInfo.setOthPhone(rs.getString("oth_phone"));
				userInfo.setOthName(rs.getString("oth_name"));
				userInfo.setOther(rs.getString("other"));
				userInfo.setHeadImg(rs.getString("head_img"));
				orderInfo.setUserInfo(userInfo);
				orderDetail.setOrderInfo(orderInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return orderDetail;
	}

	@Override
	public List<OrderDetail> getByUserId(String query) throws Exception {
		System.out.println("OrderDetailImpl method=getByUserId ");
		List<OrderDetail> orderDetailList = null;
		OrderDetail orderDetail = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		CarInfo carInfo = null;
		OrderInfo orderInfo = null;
		UserInfo userInfo = null;
		try {
			String sql = "select * from order_detail_view where " + query;
			System.out.println("OrderDetailImpl method=getByUserId sql=" + sql);
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			orderDetailList = new ArrayList<OrderDetail>();
			while (rs.next()) {
				orderDetail = new OrderDetail();
				orderDetail.setOrderDetailId(rs.getString("order_detail_id"));
				orderDetail.setQuantity(rs.getInt("quantity"));
				orderDetail.setReaPrice(rs.getDouble("rea_price"));
				orderDetail.setTakeTime(rs.getDate("take_time"));
				orderDetail.setReaRetTime(rs.getDate("rea_ret_time"));
				orderDetail.setRetTime(rs.getDate("ret_time"));
				orderDetail.setIsSend(rs.getInt("is_send"));
				orderDetail.setIsReceive(rs.getInt("is_receive"));
				orderDetail.setIsReturn(rs.getInt("is_return"));
				orderDetail.setIsAbolish(rs.getInt("is_abolish"));
				orderDetail.setPaid(rs.getDouble("paid"));

				// 获取车辆信息
				carInfo = new CarInfo();
				carInfo.setBrand(rs.getString("brand"));
				carInfo.setModel(rs.getString("model"));
				carInfo.setType(rs.getString("type"));
				carInfo.setBrand(rs.getString("brand"));
				carInfo.setBuyDate(rs.getDate("buy_date"));
				carInfo.setKm(rs.getString("km"));
				carInfo.setCarefulImg(rs.getString("careful_img"));
				carInfo.setCarImg(rs.getString("car_img"));
				carInfo.setGears(rs.getString("gears"));
				carInfo.setCc(rs.getString("cc"));
				carInfo.setColor(rs.getString("color"));
				carInfo.setCriteria(rs.getString("criteria"));
				carInfo.setPrice(rs.getDouble("price"));
				carInfo.setSeats(rs.getString("seats"));
				carInfo.setIsCheck(rs.getInt("is_check"));
				carInfo.setIsRent(rs.getInt("is_rent"));
				carInfo.setRelTime(rs.getDate("rel_time"));
				carInfo.setCarId(rs.getString("car_id"));
				carInfo.setOwnerId(rs.getString("owner_id"));
				carInfo.setPurpose(rs.getString("purpose"));
				carInfo.setRemarks(rs.getString("remarks"));
				carInfo.setPaidPer(rs.getDouble("paid_per"));
				carInfo.setComboName(rs.getString("combo_name"));
				carInfo.setCount(rs.getInt("count"));
				orderDetail.setCarInfo(carInfo);

				// 获取订单信息（包含用户信息）
				orderInfo = new OrderInfo();
				orderInfo.setOrderId(rs.getString("order_id"));
				orderInfo.setIsPay(rs.getInt("is_pay"));
				orderInfo.setBuyTime(rs.getDate("buy_time"));
				orderInfo.setPay(rs.getDouble("pay"));
				orderInfo.setReaPay(rs.getDouble("rea_pay"));

				userInfo = new UserInfo();
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setIdNum(rs.getString("id_num"));
				userInfo.setIdImg(rs.getString("id_img"));
				userInfo.setDriverImg(rs.getString("driver_img"));
				userInfo.setIsAct(rs.getInt("is_act"));
				userInfo.setIsCer(rs.getInt("is_cer"));
				userInfo.setCode(rs.getString("code"));
				userInfo.setUserId(rs.getString("user_id"));
				userInfo.setRemBal(rs.getDouble("rem_bal"));
				userInfo.setDisCou(rs.getDouble("dis_cou"));
				userInfo.setAddress(rs.getString("address"));
				userInfo.setOthPhone(rs.getString("oth_phone"));
				userInfo.setOthName(rs.getString("oth_name"));
				userInfo.setOther(rs.getString("other"));
				userInfo.setHeadImg(rs.getString("head_img"));
				orderInfo.setUserInfo(userInfo);
				orderDetail.setOrderInfo(orderInfo);

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
	public List<OrderDetail> list(int pageSize, int pageNo, String queryString) throws Exception {
		List<OrderDetail> orderDetailList = null;
		OrderDetail orderDetail = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		CarInfo carInfo = null;
		OrderInfo orderInfo = null;
		UserInfo userInfo = null;
		try {
			String sql = "select * from order_detail_view " + queryString + " limit ?,? ";
			System.out.println("OerderDetailDAOImop method=list sql=" + sql);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, pageSize * (pageNo - 1));
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			orderDetailList = new ArrayList<OrderDetail>();
			while (rs.next()) {
				orderDetail = new OrderDetail();
				orderDetail.setOrderDetailId(rs.getString("order_detail_id"));
				orderDetail.setQuantity(rs.getInt("quantity"));
				orderDetail.setReaPrice(rs.getDouble("rea_price"));
				orderDetail.setTakeTime(rs.getDate("take_time"));
				orderDetail.setReaRetTime(rs.getDate("rea_ret_time"));
				orderDetail.setRetTime(rs.getDate("ret_time"));
				orderDetail.setIsSend(rs.getInt("is_send"));
				orderDetail.setIsReceive(rs.getInt("is_receive"));
				orderDetail.setIsReturn(rs.getInt("is_return"));
				orderDetail.setIsAbolish(rs.getInt("is_abolish"));
				orderDetail.setPaid(rs.getDouble("paid"));

				// 获取车辆信息
				carInfo = new CarInfo();
				carInfo.setBrand(rs.getString("brand"));
				carInfo.setModel(rs.getString("model"));
				carInfo.setType(rs.getString("type"));
				carInfo.setBrand(rs.getString("brand"));
				carInfo.setBuyDate(rs.getDate("buy_date"));
				carInfo.setKm(rs.getString("km"));
				carInfo.setCarefulImg(rs.getString("careful_img"));
				carInfo.setCarImg(rs.getString("car_img"));
				carInfo.setGears(rs.getString("gears"));
				carInfo.setCc(rs.getString("cc"));
				carInfo.setColor(rs.getString("color"));
				carInfo.setCriteria(rs.getString("criteria"));
				carInfo.setPrice(rs.getDouble("price"));
				carInfo.setSeats(rs.getString("seats"));
				carInfo.setIsCheck(rs.getInt("is_check"));
				carInfo.setIsRent(rs.getInt("is_rent"));
				carInfo.setRelTime(rs.getDate("rel_time"));
				carInfo.setCarId(rs.getString("car_id"));
				carInfo.setOwnerId(rs.getString("owner_id"));
				carInfo.setPurpose(rs.getString("purpose"));
				carInfo.setRemarks(rs.getString("remarks"));
				carInfo.setPaidPer(rs.getDouble("paid_per"));
				carInfo.setComboName(rs.getString("combo_name"));
				carInfo.setCount(rs.getInt("count"));
				orderDetail.setCarInfo(carInfo);

				// 获取订单信息（包含用户信息）
				orderInfo = new OrderInfo();
				orderInfo.setOrderId(rs.getString("order_id"));
				orderInfo.setIsPay(rs.getInt("is_pay"));
				orderInfo.setBuyTime(rs.getDate("buy_time"));
				orderInfo.setPay(rs.getDouble("pay"));
				orderInfo.setReaPay(rs.getDouble("rea_pay"));

				userInfo = new UserInfo();
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setIdNum(rs.getString("id_num"));
				userInfo.setIdImg(rs.getString("id_img"));
				userInfo.setDriverImg(rs.getString("driver_img"));
				userInfo.setIsAct(rs.getInt("is_act"));
				userInfo.setIsCer(rs.getInt("is_cer"));
				userInfo.setCode(rs.getString("code"));
				userInfo.setUserId(rs.getString("user_id"));
				userInfo.setRemBal(rs.getDouble("rem_bal"));
				userInfo.setDisCou(rs.getDouble("dis_cou"));
				userInfo.setAddress(rs.getString("address"));
				userInfo.setOthPhone(rs.getString("oth_phone"));
				userInfo.setOthName(rs.getString("oth_name"));
				userInfo.setOther(rs.getString("other"));
				userInfo.setHeadImg(rs.getString("head_img"));
				orderInfo.setUserInfo(userInfo);
				orderDetail.setOrderInfo(orderInfo);

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

	/*
	 * order_detail_id,
	 * order_id,car_id,rea_price,quantity,take_time,rea_ret_time,
	 * ret_time,is_send,is_receive,is_return,is_ablish,paid
	 * 
	 */
	@Override
	public boolean update(OrderDetail orderDetail) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "update order_detail set order_id=?,car_id=?,rea_price=?,"
					+ "quantity=?,take_time=?,rea_ret_time=?,ret_time=?,is_send=?,"
					+ "is_receive=?,is_return=?,is_abolish=?,paid=? where order_detail_id=?";
			
			System.out.println("OrderDetailDAOImpl method=update sql="+sql);
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, orderDetail.getOrderInfo().getOrderId());
			pst.setString(i++, orderDetail.getCarInfo().getCarId());
			pst.setDouble(i++, orderDetail.getReaPrice());
			pst.setInt(i++, orderDetail.getQuantity());

			java.sql.Date takeTime = (java.sql.Date) orderDetail.getTakeTime();
			pst.setDate(i++, takeTime);
			java.sql.Date reaRetTime = (java.sql.Date) orderDetail.getReaRetTime();
			pst.setDate(i++, reaRetTime);
			java.sql.Date retTime = (java.sql.Date) orderDetail.getRetTime();
			pst.setDate(i++, retTime);
			pst.setInt(i++, orderDetail.getIsSend());
			pst.setInt(i++, orderDetail.getIsReceive());
			pst.setInt(i++, orderDetail.getIsReturn());
			pst.setInt(i++, orderDetail.getIsAbolish());
			pst.setDouble(i++, orderDetail.getPaid());
			pst.setString(i++, orderDetail.getOrderDetailId());
			if (pst.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			pst.close();
		}
		return flag;
	}

	@Override
	public int getCount(String queryString) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(*) from order_detail_view";
			pst = conn.prepareStatement(sql + queryString);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return count;
	}

}
