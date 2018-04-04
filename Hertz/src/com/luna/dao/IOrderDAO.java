package com.luna.dao;

import java.sql.Connection;
import java.util.List;
import com.luna.dto.OrderInfo;

public interface IOrderDAO {

	/**
	 * @Description: 根据订单id得到订单信息
	 * @param id
	 * @return OrderInfo
	 * @throws Exception
	 */
	public OrderInfo get(String orderId) throws Exception;

	/**
	 * @Description: 添加订单
	 * @param orderInfo
	 * @return Boolean
	 * @throws Exception
	 */
	public boolean save(OrderInfo orderInfo) throws Exception;

	/**
	 * @Description: 更新订单信息
	 * @param orderInfo
	 * @return Boolean
	 * @throws Exception
	 */
	public boolean update(OrderInfo orderInfo) throws Exception;

	/**
	 * @Description: 删除订单
	 * @param orderInfo
	 * @return
	 * @throws Exception
	 */
	public boolean delete(OrderInfo orderInfo) throws Exception;

	/**
	 * @Description: 根据用户Id得到OrderInfoList
	 * @param userId
	 * @return OrderInfoList
	 * @throws Exception
	 */
	List<OrderInfo> getByUserId(String userId) throws Exception;

	/**
	 * @Description: 订单列表
	 * @return OrderInfoList
	 * @throws Exception
	 */
	public List<OrderInfo> list() throws Exception;

	/** 
	 * @Description: 建立数据库连接     
	 * @param conn           
	 */  
	public void setConn(Connection conn);

}
