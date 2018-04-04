package com.luna.service;

import java.sql.Connection;

import com.luna.dao.IAdminDAO;
import com.luna.dao.impl.AdminDAOImpl;
import com.luna.db.DataBase;
import com.luna.dto.AdminInfo;

public class AdminService {

	IAdminDAO adminDAOImpl = new AdminDAOImpl();

	public AdminInfo login(String adminName, String password) throws Exception {
		AdminInfo adminInfo = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			adminDAOImpl.setConn(connection);
			adminInfo = adminDAOImpl.login(adminName, password);
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
