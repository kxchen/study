package com.luna.service;

import java.sql.Connection;
import java.util.List;

import com.luna.dao.IOperatingSystemDAO;
import com.luna.dao.impl.OperatingSystemDAOImpl;
import com.luna.database.DataBase;
import com.luna.dto.OperatingSystem;

public class OperatingSystemService {
	IOperatingSystemDAO operatingSystemDAO = new OperatingSystemDAOImpl();

	public boolean save(OperatingSystem operatingSystem) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			operatingSystemDAO.setConn(connection);
			flag = operatingSystemDAO.save(operatingSystem);
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

	public boolean delete(OperatingSystem operatingSystem) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			operatingSystemDAO.setConn(connection);
			flag = operatingSystemDAO.delete(operatingSystem);
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

	public boolean update(OperatingSystem operatingSystem) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			operatingSystemDAO.setConn(connection);
			flag = operatingSystemDAO.update(operatingSystem);
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

	public OperatingSystem get(int opeSystemId) throws Exception {
		OperatingSystem operatingSystem = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			operatingSystemDAO.setConn(connection);
			operatingSystem = operatingSystemDAO.get(opeSystemId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return operatingSystem;
	}

	public List<OperatingSystem> list() throws Exception {
		List<OperatingSystem> operatingSystemList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			operatingSystemDAO.setConn(connection);
			operatingSystemList = operatingSystemDAO.list();
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return operatingSystemList;
	}

}
