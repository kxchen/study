package com.luna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.luna.dao.IAdminDAO;
import com.luna.db.DataBase;
import com.luna.dto.AdminInfo;

public class AdminDAOImpl implements IAdminDAO {

	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public AdminInfo login(String adminName, String password) throws Exception {
		AdminInfo adminInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from admin_info where admin_name=? and password=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, adminName);
			pst.setString(i++, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				adminInfo = new AdminInfo();
				adminInfo.setAdminName(rs.getString("admin_name"));
				adminInfo.setPassword(rs.getString("password"));
				adminInfo.setId(rs.getString("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return adminInfo;
	}
}
