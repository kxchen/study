package com.luna.dao;

import java.sql.Connection;
import java.util.List;

import com.luna.dto.MobilePhoneInfo;
import com.luna.dto.ShopCart;
import com.luna.dto.UserInfo;

public interface IShopCartDAO {
	public void setConn(Connection conn);

	public boolean save(ShopCart shopCart) throws Exception;

	public boolean delete(ShopCart shopCart) throws Exception;

	public boolean deleteAll(UserInfo userInfo) throws Exception;

	public boolean update(ShopCart shopCart) throws Exception;

	public ShopCart get(int shopCartId) throws Exception;

	public ShopCart get(UserInfo userInfo, MobilePhoneInfo mobPhone) throws Exception;

	public List<ShopCart> list(UserInfo user) throws Exception;
}
