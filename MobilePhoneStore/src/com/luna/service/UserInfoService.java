package com.luna.service;

import java.sql.Connection;
import java.util.List;

import com.luna.dao.IUserInfoDAO;
import com.luna.dao.impl.UserInfoDAOImpl;
import com.luna.database.DataBase;
import com.luna.dto.UserInfo;

public class UserInfoService {
	IUserInfoDAO userInfoDAOImpl = new UserInfoDAOImpl();

	public boolean save(UserInfo userInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			userInfoDAOImpl.setConn(connection);
			flag = userInfoDAOImpl.save(userInfo);
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

	public boolean delete(UserInfo userInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			userInfoDAOImpl.setConn(connection);
			flag = userInfoDAOImpl.delete(userInfo);
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

	public boolean update(UserInfo userInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			userInfoDAOImpl.setConn(connection);
			flag = userInfoDAOImpl.update(userInfo);
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

	public UserInfo get(int userId) throws Exception {
		UserInfo userInfo = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			userInfoDAOImpl.setConn(connection);
			userInfo = userInfoDAOImpl.get(userId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return userInfo;
	}

	public UserInfo active(String code) throws Exception {

		UserInfo userInfo = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			userInfoDAOImpl.setConn(connection);
			userInfo = userInfoDAOImpl.active(code);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return userInfo;

	}

	public List<UserInfo> list(int pageSize, int pageNo, String queryString) throws Exception {
		List<UserInfo> userInfoList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			userInfoDAOImpl.setConn(connection);
			userInfoList = userInfoDAOImpl.list(pageSize, pageNo, queryString);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return userInfoList;
	}

	public UserInfo login(String loginName, String password) throws Exception {
		UserInfo userInfo = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			userInfoDAOImpl.setConn(connection);
			userInfo = userInfoDAOImpl.login(loginName, password);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return userInfo;
	}

	public int getCount(String queryString) throws Exception {
		Connection connection = null;
		int count = 0;
		try {
			connection = DataBase.getConn();
			userInfoDAOImpl.setConn(connection);
			count = userInfoDAOImpl.getCount(queryString);
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
}
