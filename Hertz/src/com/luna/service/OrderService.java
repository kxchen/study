package com.luna.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.luna.dao.IOrderDAO;
import com.luna.dao.IOrderDetailDAO;
import com.luna.dao.impl.OrderDAOImpl;
import com.luna.dao.impl.OrderDetailDAOImpl;
import com.luna.db.DataBase;
import com.luna.dto.OrderDetail;
import com.luna.dto.OrderInfo;

public class OrderService {

	IOrderDAO orderDAOImpl = new OrderDAOImpl();
	IOrderDetailDAO orderDetailDAOImpl = new OrderDetailDAOImpl();

	public boolean save(OrderInfo orderInfo, List<OrderDetail> orderDetailList) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			// 添加订单
			orderDAOImpl.setConn(connection);
			flag = orderDAOImpl.save(orderInfo);

			// 添加订单详情
			orderDetailDAOImpl.setConn(connection);

			for (int i = 0; i < orderDetailList.size(); i++) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail = orderDetailList.get(i);
				orderDetail.setOrderInfo(orderInfo);
				flag = orderDetailDAOImpl.save(orderDetail);
			}
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return flag;
	}

	public OrderDetail getOrderDetail(String orderDetailId) throws Exception {
		Connection connection = null;
		OrderDetail orderInfo = null;
		try {
			connection = DataBase.getConn();
			orderDetailDAOImpl.setConn(connection);
			orderInfo = new OrderDetail();
			orderInfo = orderDetailDAOImpl.get(orderDetailId);
			DataBase.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return orderInfo;
	}

	public OrderInfo get(String orderId) throws Exception {
		Connection connection = null;
		OrderInfo orderInfo = null;
		try {
			connection = DataBase.getConn();
			orderDAOImpl.setConn(connection);
			orderInfo = new OrderInfo();
			orderInfo = orderDAOImpl.get(orderId);
			DataBase.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return orderInfo;
	}

	public List<OrderDetail> getOrderDetailList(String orderId) throws Exception {
		Connection connection = null;
		List<OrderDetail> orderDetailList = null;
		try {
			connection = DataBase.getConn();
			orderDetailDAOImpl.setConn(connection);
			orderDetailList = new ArrayList<OrderDetail>();
			orderDetailList = orderDetailDAOImpl.getOrderDetail(orderId);
			DataBase.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return orderDetailList;
	}

	public List<OrderDetail> getOrderDetailListgetByUserId(String query) throws Exception {
		Connection connection = null;
		List<OrderDetail> orderDetailList = null;
		try {
			connection = DataBase.getConn();
			orderDetailDAOImpl.setConn(connection);
			orderDetailList = new ArrayList<OrderDetail>();
			orderDetailList = orderDetailDAOImpl.getByUserId(query);
			DataBase.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return orderDetailList;
	}

	public boolean update(OrderInfo orderInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			orderDAOImpl.setConn(connection);
			flag = orderDAOImpl.update(orderInfo);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return flag;
	}
	public boolean update(OrderDetail orderInfo) throws Exception {
		System.out.println("OrderService method=update");
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			orderDetailDAOImpl.setConn(connection);
			flag = orderDetailDAOImpl.update(orderInfo);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return flag;
	}
	public List<OrderDetail> list(int pageSize, int pageNo, String queryString) throws Exception {
		Connection connection = null;
		List<OrderDetail> orderDetailList = null;
		try {
			connection = DataBase.getConn();
			orderDetailDAOImpl.setConn(connection);
			orderDetailList = new ArrayList<OrderDetail>();
			orderDetailList = orderDetailDAOImpl.list(pageSize, pageNo, queryString);
			DataBase.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return orderDetailList;

	}

	public int getCount(String queryString) throws Exception {
		Connection connection = null;
		int count = 0;
		try {
			connection = DataBase.getConn();
			orderDetailDAOImpl.setConn(connection);
			count = orderDetailDAOImpl.getCount(queryString);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return count;
	}

	public boolean delete(OrderDetail userInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			orderDetailDAOImpl.setConn(connection);
			flag = orderDetailDAOImpl.delete(userInfo);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return flag;
	}
}
