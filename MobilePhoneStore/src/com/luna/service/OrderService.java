package com.luna.service;

import java.sql.Connection;
import java.util.List;
import com.luna.dao.IOrderDetailDAO;
import com.luna.dao.IOrderInfoDAO;
import com.luna.dao.IShopCartDAO;
import com.luna.dao.impl.OrderDetailDAOImpl;
import com.luna.dao.impl.OrderInfoDAOImpl;
import com.luna.dao.impl.ShopCartDAOImpl;
import com.luna.database.DataBase;
import com.luna.dto.OrderInfo;
import com.luna.dto.OrderDetail;

public class OrderService {
	IOrderInfoDAO orderInfoDAOImpl = new OrderInfoDAOImpl();
	IOrderDetailDAO orderDetailDAOImpl = new OrderDetailDAOImpl();
	IShopCartDAO shopCartDAOImpl = new ShopCartDAOImpl();

	public boolean save(OrderInfo orderInfo, List<OrderDetail> orderDetailList) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			// 添加订单
			orderInfoDAOImpl.setConn(connection);
			flag = orderInfoDAOImpl.addOrderInfo(orderInfo);

			// 添加订单详情
			orderDetailDAOImpl.setConn(connection);

			for (int i = 0; i < orderDetailList.size(); i++) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail = orderDetailList.get(i);
				flag = orderDetailDAOImpl.addOrderDetail(orderDetail);
			}
			// 清空购物车
			connection = DataBase.getConn();
			shopCartDAOImpl.setConn(connection);
			flag = shopCartDAOImpl.deleteAll(orderInfo.getUser());

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

	public boolean delete(OrderInfo orderInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();

			orderInfoDAOImpl.setConn(connection);
			flag = orderInfoDAOImpl.delete(orderInfo);

			orderDetailDAOImpl.setConn(connection);
			flag = orderDetailDAOImpl.delete(orderInfo);
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

	public boolean updateOrders(OrderInfo orderInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			orderInfoDAOImpl.setConn(connection);
			flag = orderInfoDAOImpl.update(orderInfo);
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

	/**
	 * @Description: 根据用户的ID查询用户的订单列表
	 * @param userId
	 * @param i
	 * @param pageNo
	 * @return List<OrderInfo>
	 * @throws Exception
	 */
	public List<OrderInfo> getOrderInfoByUserId(int pageSize, int pageNo, int userId) throws Exception {
		List<OrderInfo> orderInfoList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			orderInfoDAOImpl.setConn(connection);
			orderInfoList = orderInfoDAOImpl.getOrderInfoByUserId(pageSize, pageNo, userId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return orderInfoList;
	}

	public int getOrderCountByUserId(int userId) throws Exception {
		Connection connection = null;
		int count = 0;
		try {
			connection = DataBase.getConn();
			orderInfoDAOImpl.setConn(connection);
			count = orderInfoDAOImpl.getOrderCountByUserId(userId);
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

	public OrderInfo get(Long id) throws Exception {

		OrderInfo orderInfo = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			orderInfoDAOImpl.setConn(connection);
			orderInfo = orderInfoDAOImpl.get(id);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return orderInfo;
	}

	public List<OrderInfo> list(int pageSize, int pageNo, String string) throws Exception {
		List<OrderInfo> orderInfoList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			orderInfoDAOImpl.setConn(connection);
			orderInfoList = orderInfoDAOImpl.list(pageSize, pageNo, string);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return orderInfoList;

	}

	public int getCount(String queryString) throws Exception {
		Connection connection = null;
		int count = 0;
		try {
			connection = DataBase.getConn();
			orderInfoDAOImpl.setConn(connection);
			count = orderInfoDAOImpl.getCount(queryString);
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

	public List<OrderDetail> getOrederDetaillist(long id) throws Exception {
		List<OrderDetail> orderDetailList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			orderDetailDAOImpl.setConn(connection);
			orderDetailList = orderDetailDAOImpl.getOrderDetail(id);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return orderDetailList;
	}

}
