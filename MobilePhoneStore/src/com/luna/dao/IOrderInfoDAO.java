package com.luna.dao;

import java.sql.Connection;
import java.util.List;

import com.luna.dto.OrderInfo;

public interface IOrderInfoDAO {

	public int getOrderCountByUserId(int userId) throws Exception;

	public OrderInfo get(Long id) throws Exception;

	public boolean addOrderInfo(OrderInfo orderInfo) throws Exception;

	public boolean update(OrderInfo orderInfo) throws Exception;

	public boolean delete(OrderInfo orderInfo) throws Exception;

	public List<OrderInfo> list(int pageSize, int pageNo, String string) throws Exception;

	public int getCount(String queryString) throws Exception;

	public void setConn(Connection conn);

	List<OrderInfo> getOrderInfoByUserId(int pageSize, int pageNo, int userId) throws Exception;
}
