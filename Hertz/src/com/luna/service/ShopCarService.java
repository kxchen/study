package com.luna.service;

import java.sql.Connection;
import java.util.List;

import com.luna.dao.IShopCarDAO;
import com.luna.dao.impl.ShopCarDAOImpl;
import com.luna.db.DataBase;
import com.luna.dto.CarInfo;
import com.luna.dto.ShopCarInfo;
import com.luna.dto.UserInfo;

/** 
* @ClassName: ShopCarService 
* @Description: 购物车服务层
* @author  xiazhenghao   
* @date 2015年12月25日 下午2:52:59 
*  
*/
public class ShopCarService {
	IShopCarDAO shopCarDAOImpl = new ShopCarDAOImpl();

	public boolean save(ShopCarInfo shopCarInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			shopCarDAOImpl.setConn(connection);
			flag = shopCarDAOImpl.save(shopCarInfo);
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

	public boolean delete(ShopCarInfo shopCarInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			shopCarDAOImpl.setConn(connection);
			flag = shopCarDAOImpl.delete(shopCarInfo);
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
	public boolean delete(String string) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			shopCarDAOImpl.setConn(connection);
			flag = shopCarDAOImpl.delete(string);
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
			shopCarDAOImpl.setConn(connection);
			flag = shopCarDAOImpl.deleteAll(userInfo);
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

	public boolean update(ShopCarInfo shopCarInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			shopCarDAOImpl.setConn(connection);
			flag = shopCarDAOImpl.update(shopCarInfo);
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

	public ShopCarInfo get(String shopCarId) throws Exception {
		ShopCarInfo shopCarInfo = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			shopCarDAOImpl.setConn(connection);
			shopCarInfo = shopCarDAOImpl.get(shopCarId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return shopCarInfo;
	}

	public ShopCarInfo get(UserInfo userInfo, CarInfo carInfo) throws Exception {
		ShopCarInfo shopCarInfo = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			shopCarDAOImpl.setConn(connection);
			shopCarInfo = shopCarDAOImpl.get(userInfo, carInfo);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return shopCarInfo;
	}

	public List<ShopCarInfo> list(UserInfo user) throws Exception {
		List<ShopCarInfo> shopCarInfoList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			shopCarDAOImpl.setConn(connection);
			shopCarInfoList = shopCarDAOImpl.list(user);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return shopCarInfoList;
	}

}