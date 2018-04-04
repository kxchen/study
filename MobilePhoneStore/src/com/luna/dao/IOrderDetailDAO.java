package com.luna.dao;

import java.sql.Connection;
import java.util.List;

import com.luna.dto.OrderDetail;
import com.luna.dto.OrderInfo;

public interface IOrderDetailDAO {
	public List<OrderDetail> getOrderDetail(long orderId) throws Exception;

	public boolean addOrderDetail(OrderDetail orderDetail) throws Exception;

	public void setConn(Connection conn) throws Exception;

	public boolean delete(OrderInfo orderInfo) throws Exception;
}
