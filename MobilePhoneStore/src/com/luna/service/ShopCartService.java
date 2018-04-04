package com.luna.service;

import java.sql.Connection;
import java.util.List;

import com.luna.dao.IShopCartDAO;
import com.luna.dao.impl.ShopCartDAOImpl;
import com.luna.database.DataBase;
import com.luna.dto.MobilePhoneInfo;
import com.luna.dto.ShopCart;
import com.luna.dto.UserInfo;

public class ShopCartService {
	IShopCartDAO shopCartDAOImpl = new ShopCartDAOImpl();

	public boolean save(ShopCart shopCart) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			shopCartDAOImpl.setConn(connection);
			flag = shopCartDAOImpl.save(shopCart);
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

	public boolean deleteAll(UserInfo userInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			shopCartDAOImpl.setConn(connection);
			flag = shopCartDAOImpl.deleteAll(userInfo);
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

	public boolean delete(ShopCart shopCart) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			shopCartDAOImpl.setConn(connection);
			flag = shopCartDAOImpl.delete(shopCart);
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

	public boolean update(ShopCart shopCart) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			shopCartDAOImpl.setConn(connection);
			flag = shopCartDAOImpl.update(shopCart);
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

	public ShopCart get(int shopCartId) throws Exception {
		ShopCart shopCart = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			shopCartDAOImpl.setConn(connection);
			shopCart = shopCartDAOImpl.get(shopCartId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return shopCart;
	}

	public ShopCart get(UserInfo user, MobilePhoneInfo mobPhone) throws Exception {
		ShopCart shopCart = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			shopCartDAOImpl.setConn(connection);
			shopCart = shopCartDAOImpl.get(user, mobPhone);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return shopCart;
	}

	public List<ShopCart> list(UserInfo user) throws Exception {
		List<ShopCart> shopCartList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			shopCartDAOImpl.setConn(connection);
			shopCartList = shopCartDAOImpl.list(user);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return shopCartList;
	}

}
