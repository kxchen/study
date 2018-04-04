package com.luna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.luna.dao.IAdminInfoDAO;
import com.luna.database.DataBase;
import com.luna.dto.AdminInfo;

public class AdminInfoDAOImpl implements IAdminInfoDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}

	@Override
	public boolean save(AdminInfo adminInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into admin_info(admin_name,password) values (?,?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, adminInfo.getAdminName());
			pst.setString(i++, adminInfo.getPassword());
			if (pst.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(pst);
		}
		return flag;
	}

	@Override
	public boolean delete(AdminInfo adminInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from admin_info where admin_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, adminInfo.getAdminId());
			if (pst.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(pst);
		}
		return flag;
	}

	@Override
	public boolean update(AdminInfo adminInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "update admin_info set admin_name=?,password=? where admin_id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, adminInfo.getAdminName());
			pst.setString(i++, adminInfo.getPassword());
			pst.setInt(i++, adminInfo.getAdminId());
			if (pst.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(pst);
			;
		}
		return flag;
	}

	@Override
	public AdminInfo get(int adminId) throws Exception {
		AdminInfo adminInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from admin_info where admin_id =?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, adminId);
			rs = pst.executeQuery();
			if (rs.next()) {
				adminInfo = new AdminInfo();
				adminInfo.setAdminName(rs.getString("admin_name"));
				adminInfo.setPassword(rs.getString("password"));
				adminInfo.setAdminId(rs.getInt("admin_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return adminInfo;
	}

	@Override
	public List<AdminInfo> list() throws Exception {
		List<AdminInfo> adminInfoList = null;
		AdminInfo adminInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from admin_info";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			adminInfoList = new ArrayList<AdminInfo>();
			while (rs.next()) {
				adminInfo = new AdminInfo();
				adminInfo.setAdminName(rs.getString("admin_name"));
				adminInfo.setPassword(rs.getString("password"));
				adminInfo.setAdminId(rs.getInt("admin_id"));
				adminInfoList.add(adminInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return adminInfoList;
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
				adminInfo.setAdminId(rs.getInt("admin_id"));
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
