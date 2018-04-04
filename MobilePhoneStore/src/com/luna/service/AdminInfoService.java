package com.luna.service;

import java.sql.Connection;
import java.util.List;

import com.luna.dao.IAdminInfoDAO;
import com.luna.dao.impl.AdminInfoDAOImpl;
import com.luna.database.DataBase;
import com.luna.dto.AdminInfo;

public class AdminInfoService {
	IAdminInfoDAO adminInfoDAOImplImpl = new AdminInfoDAOImpl();

	public boolean save(AdminInfo adminInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			adminInfoDAOImplImpl.setConn(connection);
			flag = adminInfoDAOImplImpl.save(adminInfo);
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

	public boolean delete(AdminInfo adminInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			adminInfoDAOImplImpl.setConn(connection);
			flag = adminInfoDAOImplImpl.delete(adminInfo);
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

	public boolean update(AdminInfo adminInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			adminInfoDAOImplImpl.setConn(connection);
			flag = adminInfoDAOImplImpl.update(adminInfo);
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

	public AdminInfo get(int userId) throws Exception {
		AdminInfo adminInfo = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			adminInfoDAOImplImpl.setConn(connection);
			adminInfo = adminInfoDAOImplImpl.get(userId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return adminInfo;
	}

	public List<AdminInfo> list() throws Exception {
		List<AdminInfo> adminInfoList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			adminInfoDAOImplImpl.setConn(connection);
			adminInfoList = adminInfoDAOImplImpl.list();
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return adminInfoList;
	}

	public AdminInfo login(String loginName, String password) throws Exception {
		AdminInfo adminInfo = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			adminInfoDAOImplImpl.setConn(connection);
			adminInfo = adminInfoDAOImplImpl.login(loginName, password);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return adminInfo;
	}
}
