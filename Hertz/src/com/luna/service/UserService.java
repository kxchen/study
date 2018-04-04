package com.luna.service;

import java.sql.Connection;
import java.util.List;

import com.luna.dao.IUserDAO;
import com.luna.dao.impl.UserDAOImpl;
import com.luna.db.DataBase;
import com.luna.dto.UserInfo;

public class UserService {
	IUserDAO userDAOImpl = new UserDAOImpl();

	public List<UserInfo> list(int pageSize, int pageNo, String queryString) throws Exception {
		List<UserInfo> userInfoList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			userDAOImpl.setConn(connection);
			userInfoList = userDAOImpl.list(pageSize, pageNo, queryString);
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

	public boolean save(UserInfo userInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			userDAOImpl.setConn(connection);
			flag = userDAOImpl.save(userInfo);
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
			userDAOImpl.setConn(connection);
			flag = userDAOImpl.delete(userInfo);
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
			userDAOImpl.setConn(connection);
			flag = userDAOImpl.update(userInfo);
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

	public UserInfo get(String userId) throws Exception {
		UserInfo userInfo = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			userDAOImpl.setConn(connection);
			userInfo = userDAOImpl.get(userId);
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

	public UserInfo getByPhone(String phone) throws Exception {
		UserInfo userInfo = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			userDAOImpl.setConn(connection);
			userInfo = userDAOImpl.getByPhone(phone);
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

	public UserInfo login(String phone, String password) throws Exception {
		UserInfo userInfo = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			userDAOImpl.setConn(connection);
			userInfo = userDAOImpl.login(phone, password);
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
			userDAOImpl.setConn(connection);
			count = userDAOImpl.getCount(queryString);
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
