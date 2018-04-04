package com.luna.service;

import java.sql.Connection;
import java.util.List;

import com.luna.dao.IScreenSizeDAO;
import com.luna.dao.impl.ScreenSizeDAOImpl;
import com.luna.database.DataBase;
import com.luna.dto.ScreenSize;

public class ScreenSizeService {
	IScreenSizeDAO screenSizeDAO = new ScreenSizeDAOImpl();

	public boolean save(ScreenSize screenSize) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			screenSizeDAO.setConn(connection);
			flag = screenSizeDAO.save(screenSize);
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

	public boolean delete(ScreenSize screenSize) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			screenSizeDAO.setConn(connection);
			flag = screenSizeDAO.delete(screenSize);
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

	public boolean update(ScreenSize screenSize) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			screenSizeDAO.setConn(connection);
			flag = screenSizeDAO.update(screenSize);
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

	public ScreenSize get(int scrSizeId) throws Exception {
		ScreenSize screenSize = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			screenSizeDAO.setConn(connection);
			screenSize = screenSizeDAO.get(scrSizeId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return screenSize;
	}

	public List<ScreenSize> list() throws Exception {
		List<ScreenSize> screenSizeList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			screenSizeDAO.setConn(connection);
			screenSizeList = screenSizeDAO.list();
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return screenSizeList;
	}

}
