package com.luna.dao;

import java.sql.Connection;
import java.util.List;

import com.luna.dto.CarInfo;
import com.luna.dto.ShopCarInfo;
import com.luna.dto.UserInfo;

public interface IShopCarDAO {
	public void setConn(Connection conn);

	public boolean save(ShopCarInfo shopCarInfo) throws Exception;

	public boolean delete(ShopCarInfo shopCarInfo) throws Exception;

	public boolean deleteAll(UserInfo userInfo) throws Exception;

	public boolean update(ShopCarInfo shopCarInfo) throws Exception;

	public ShopCarInfo get(String shopCarId) throws Exception;

	public ShopCarInfo get(UserInfo userInfo, CarInfo carInfo) throws Exception;

	public List<ShopCarInfo> list(UserInfo user) throws Exception;
	
	public boolean delete(String result) throws Exception ;
}
