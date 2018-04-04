package com.luna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.luna.dao.IOrderInfoDAO;
import com.luna.database.DataBase;
import com.luna.dto.OrderInfo;
import com.luna.dto.UserInfo;

public class OrderInfoDAOImpl implements IOrderInfoDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<OrderInfo> getOrderInfoByUserId(int pageSize, int pageNo, int userId) throws Exception {
		List<OrderInfo> orderInfoList = null;
		OrderInfo orderInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		UserInfo userInfo = null;
		try {
			String sql = "select * from order_info_view where user_id=? limit ?,?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			pst.setInt(2, pageSize * (pageNo - 1));
			pst.setInt(3, pageSize);
			rs = pst.executeQuery();
			orderInfoList = new ArrayList<OrderInfo>();
			while (rs.next()) {
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
				orderInfoList.add(orderInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return orderInfoList;
	}

	@Override
	public int getOrderCountByUserId(int userId) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(*) from order_info_view where user_id=? ";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
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

	@Override
	public boolean addOrderInfo(OrderInfo orderInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into order_info(order_id,user_id,submit_time,total_price,is_pay,is_deliver) values (?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setLong(i++, orderInfo.getOrderId());
			pst.setInt(i++, orderInfo.getUser().getUserId());
			java.sql.Date submitTime = (java.sql.Date) orderInfo.getSubmitTime();
			pst.setDate(i++, submitTime);
			pst.setDouble(i++, orderInfo.getTotalPrice());
			pst.setInt(i++, orderInfo.getIsPay());
			pst.setInt(i++, orderInfo.getIsDeliver());
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
	public boolean update(OrderInfo orderInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "update order_info set is_pay=?,is_deliver=? where order_id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setInt(i++, orderInfo.getIsPay());
			pst.setInt(i++, orderInfo.getIsDeliver());
			pst.setLong(i++, orderInfo.getOrderId());
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
	public OrderInfo get(Long id) throws Exception {
		OrderInfo orderInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		UserInfo userInfo = null;
		try {
			String sql = "select * from order_info_view where order_id =?";
			pst = conn.prepareStatement(sql);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
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
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return orderInfo;
	}

	@Override
	public List<OrderInfo> list(int pageSize, int pageNo, String string) throws Exception {
		List<OrderInfo> orderInfoList = null;
		OrderInfo orderInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		UserInfo userInfo = null;
		try {
			String sql = "select * from order_info_view " + string + "limit ?,?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, pageSize * (pageNo - 1));
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			orderInfoList = new ArrayList<OrderInfo>();
			while (rs.next()) {
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
				orderInfoList.add(orderInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return orderInfoList;
	}

	@Override
	public int getCount(String string) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(*) from order_info_view " + string;
			pst = conn.prepareStatement(sql);
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

	@Override
	public boolean delete(OrderInfo orderInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from order_info where order_id=?";
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
