package com.luna.dao;

import java.sql.Connection;
import java.util.List;

import com.luna.dto.OrderDetail;
import com.luna.dto.OrderInfo;

public interface IOrderDetailDAO {

	public boolean save(OrderDetail orderDetail) throws Exception;

	public boolean delete(OrderInfo orderInfo) throws Exception;

	public boolean delete(OrderDetail orderDetail) throws Exception;

	public OrderDetail get(String orderDetailId) throws Exception;

	List<OrderDetail> getByUserId(String userId) throws Exception;

	public List<OrderDetail> list(int pageSize, int pageNo, String queryString) throws Exception;

	public List<OrderDetail> getOrderDetail(String orderId) throws Exception;

	public void setConn(Connection conn) throws Exception;

	public boolean update(OrderDetail orderDetail) throws Exception;

	public int getCount(String queryString) throws Exception;

}
