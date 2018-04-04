package com.luna.service;

import java.sql.Connection;
import java.util.List;

import com.luna.dao.INetworkTypeDAO;
import com.luna.dao.impl.NetworkTypeDAOImpl;
import com.luna.database.DataBase;
import com.luna.dto.NetworkType;

public class NetworkTypeService {
	INetworkTypeDAO networkTypeDAO = new NetworkTypeDAOImpl();

	public boolean save(NetworkType networkType) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			networkTypeDAO.setConn(connection);
			flag = networkTypeDAO.save(networkType);
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

	public boolean delete(NetworkType networkType) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			networkTypeDAO.setConn(connection);
			flag = networkTypeDAO.delete(networkType);
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

	public boolean update(NetworkType networkType) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			networkTypeDAO.setConn(connection);
			flag = networkTypeDAO.update(networkType);
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

	public NetworkType get(int netTypeId) throws Exception {
		NetworkType networkType = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			networkTypeDAO.setConn(connection);
			networkType = networkTypeDAO.get(netTypeId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return networkType;
	}

	public List<NetworkType> list() throws Exception {
		List<NetworkType> networkTypeList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			networkTypeDAO.setConn(connection);
			networkTypeList = networkTypeDAO.list();
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return networkTypeList;
	}

}
