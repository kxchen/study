package com.czesou.service;

import java.sql.Connection;
import java.util.List;

import com.czesou.dao.IUserDAO;
import com.czesou.dao.impl.UserDAOImpl;
import com.czesou.db.DataBase;
import com.czesou.pojo.User;

public class UserService {
	IUserDAO userDAOImpl = new UserDAOImpl();

	public boolean save(User user) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			userDAOImpl.setConn(connection);
			flag = userDAOImpl.save(user);
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

	public boolean delete(User user) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			userDAOImpl.setConn(connection);
			flag = userDAOImpl.delete(user);
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

	public boolean update(User user) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			userDAOImpl.setConn(connection);
			flag = userDAOImpl.update(user);
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

	public User login(String phone, String password) throws Exception {
		User userInfo = null;
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

	public User get(String id) throws Exception {
		User userInfo = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			userDAOImpl.setConn(connection);
			userInfo = userDAOImpl.get(id);
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

	public List<User> list() throws Exception {
		List<User> userInfoList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			userDAOImpl.setConn(connection);
			userInfoList = userDAOImpl.list();
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

	public List<User> list(int pageSize, int pageNo, String queryString) throws Exception {
		List<User> userInfoList = null;
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
