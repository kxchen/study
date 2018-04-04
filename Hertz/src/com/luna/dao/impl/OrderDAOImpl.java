package com.luna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.luna.dao.IOrderDAO;
import com.luna.dto.OrderInfo;
import com.luna.dto.UserInfo;

public class OrderDAOImpl implements IOrderDAO {
	private Connection conn = null;

	public OrderInfo get(String orderId) throws Exception {
		OrderInfo orderInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		UserInfo userInfo = null;
		try {
			String sql = "select * from order_info_view where order_id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, orderId);
			rs = pst.executeQuery();
			if (rs.next()) {
				orderInfo = new OrderInfo();
				orderInfo.setOrderId(rs.getString("order_id"));
				orderInfo.setBuyTime(rs.getDate("buy_time"));
				orderInfo.setPay(rs.getDouble("pay"));
				orderInfo.setReaPay(rs.getDouble("rea_pay"));
				orderInfo.setConPhone(rs.getString("con_phone"));
				orderInfo.setConAddress(rs.getString("con_address"));
				orderInfo.setContacts(rs.getString("contacts"));
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
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			rs.close();
			pst.close();
		}
		return orderInfo;
	}

	@Override
	public boolean save(OrderInfo orderInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into order_info(order_id,user_id,buy_time,pay,rea_pay,con_phone,con_address,contacts,is_pay) values (?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, orderInfo.getOrderId());
			pst.setString(i++, orderInfo.getUserInfo().getUserId());
			java.sql.Date buyTime = (java.sql.Date) orderInfo.getBuyTime();
			pst.setDate(i++, buyTime);
			pst.setDouble(i++, orderInfo.getPay());
			pst.setDouble(i++, orderInfo.getReaPay());
			pst.setString(i++, orderInfo.getConPhone());
			pst.setString(i++, orderInfo.getConAddress());
			pst.setString(i++, orderInfo.getContacts());
			pst.setInt(i++, orderInfo.getIsPay());
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
	public boolean update(OrderInfo orderInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "update order_info set user_id=?,buy_time=?,pay=?,rea_pay=?,"
					+ "con_phone=?,con_address=?,contacts=?,is_pay=? where order_id=?";
			System.out.println("OrderInfoDAOImpl method=undate sql=" + sql);
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, orderInfo.getUserInfo().getUserId());
			java.sql.Date buyTime = (java.sql.Date) orderInfo.getBuyTime();
			pst.setDate(i++, buyTime);
			pst.setDouble(i++, orderInfo.getPay());
			pst.setDouble(i++, orderInfo.getReaPay());
			pst.setString(i++, orderInfo.getConPhone());
			pst.setString(i++, orderInfo.getConAddress());
			pst.setString(i++, orderInfo.getContacts());
			pst.setInt(i++, orderInfo.getIsPay());
			pst.setString(i++, orderInfo.getOrderId());
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
	public boolean delete(OrderInfo orderInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from order_info where order_id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, orderInfo.getOrderId());
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
	public List<OrderInfo> getByUserId(String userId) throws Exception {
		List<OrderInfo> orderInfoList = null;
		OrderInfo orderInfo = null;
		UserInfo userInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from order_info_view where user_id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userId);
			rs = pst.executeQuery();
			orderInfoList = new ArrayList<OrderInfo>();
			while (rs.next()) {
				orderInfo = new OrderInfo();
				orderInfo.setOrderId(rs.getString("order_id"));
				orderInfo.setBuyTime(rs.getDate("buy_time"));
				orderInfo.setPay(rs.getDouble("pay"));
				orderInfo.setReaPay(rs.getDouble("rea_pay"));
				orderInfo.setConPhone(rs.getString("con_phone"));
				orderInfo.setConAddress(rs.getString("con_address"));
				orderInfo.setContacts(rs.getString("contacts"));
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

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			rs.close();
			pst.close();
		}
		return orderInfoList;
	}

	@Override
	public List<OrderInfo> list() throws Exception {
		List<OrderInfo> orderInfoList = null;
		OrderInfo orderInfo = null;
		UserInfo userInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from order_info_view ";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			orderInfoList = new ArrayList<OrderInfo>();
			while (rs.next()) {
				orderInfo = new OrderInfo();
				orderInfo.setOrderId(rs.getString("order_id"));
				orderInfo.setBuyTime(rs.getDate("buy_time"));
				orderInfo.setPay(rs.getDouble("pay"));
				orderInfo.setReaPay(rs.getDouble("rea_pay"));
				orderInfo.setConPhone(rs.getString("con_phone"));
				orderInfo.setConAddress(rs.getString("con_address"));
				orderInfo.setContacts(rs.getString("contacts"));
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
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			rs.close();
			pst.close();
		}
		return orderInfoList;
	}

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}
	/**/
}
