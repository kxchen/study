package com.school.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.school.database.DataBase;
import com.school.dto.AdminInfo;

public class AdminInfoService {
	public AdminInfo login(AdminInfo adminInfoInstance) throws Exception {
		AdminInfo adminInfo = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from admin_info where account=? and password=?";
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, adminInfoInstance.getAccount());
			pst.setString(2, adminInfoInstance.getPassword());
			rs = pst.executeQuery();
			DataBase.commit();
			if (rs.next()) {
				adminInfo = new AdminInfo();
				adminInfo.setAccount(adminInfoInstance.getAccount());
				adminInfo.setAdminName(rs.getString("admin_name"));
				adminInfo.setId(rs.getInt("id"));
				adminInfo.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			throw e;
		} finally {
			rs.close();
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return adminInfo;
	}

	public boolean save(AdminInfo adminInfoInstance) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		int state = 0;
		boolean flag = false;
		try {
			String sql = "insert into admin_info(account,password,admin_name) values (?,?,?)";
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, adminInfoInstance.getAccount());
			pst.setString(2, adminInfoInstance.getPassword());
			pst.setString(3, adminInfoInstance.getAdminName());
			state = pst.executeUpdate();
			DataBase.commit();
			if (state > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			return false;
		} finally {
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return flag;
	}

	public List<AdminInfo> list() throws Exception {
		ArrayList<AdminInfo> studentList = null;
		AdminInfo adminInfo = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from admin_info";
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			DataBase.commit();
			studentList = new ArrayList<AdminInfo>();
			while (rs.next()) {
				adminInfo = new AdminInfo();
				adminInfo.setAccount(rs.getString("account"));
				adminInfo.setAdminName(rs.getString("admin_name"));
				adminInfo.setId(rs.getInt("id"));
				studentList.add(adminInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			throw e;
		} finally {
			rs.close();
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return studentList;
	}

	public boolean update(AdminInfo adminInfoInstance) throws SQLException {
		String sql = "update admin_info set admin_name=?,password=? where id=?";
		Connection conn = null;
		PreparedStatement pst = null;
		int state = 0;
		boolean flag = false;
		try {
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, adminInfoInstance.getAdminName());
			pst.setString(2, adminInfoInstance.getPassword());
			pst.setInt(3, adminInfoInstance.getId());
			state = pst.executeUpdate();
			DataBase.commit();
			if (state > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			return false;
		} finally {
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return flag;
	}

	public AdminInfo get(int id) throws Exception {
		AdminInfo adminInfo = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from admin_info where id =?";
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			DataBase.commit();
			if (rs.next()) {
				adminInfo = new AdminInfo();
				adminInfo.setAccount(rs.getString("account"));
				adminInfo.setAdminName(rs.getString("admin_name"));
				adminInfo.setId(rs.getInt("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			throw e;
		} finally {
			rs.close();
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return adminInfo;
	}

	public boolean delete(int id) throws SQLException {
		String sql = "delete from admin_info where id=?";
		Connection conn = null;
		PreparedStatement pst = null;
		int state = 0;
		boolean flag = false;
		try {
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			state = pst.executeUpdate();
			DataBase.commit();
			if (state > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			return false;
		} finally {
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return flag;
	}

	public List<AdminInfo> findByAccount(String account) throws Exception {
		ArrayList<AdminInfo> studentList = null;
		AdminInfo adminInfo = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from admin_info where account like ?";
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + account + "%");
			rs = pst.executeQuery();
			DataBase.commit();
			studentList = new ArrayList<AdminInfo>();
			while (rs.next()) {
				adminInfo = new AdminInfo();
				adminInfo.setAccount(rs.getString("account"));
				adminInfo.setAdminName(rs.getString("admin_name"));
				adminInfo.setId(rs.getInt("id"));
				studentList.add(adminInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			throw e;
		} finally {
			rs.close();
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return studentList;

	}

	public List<AdminInfo> findByName(String account) throws Exception {
		ArrayList<AdminInfo> studentList = null;
		AdminInfo adminInfo = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from admin_info where admin_name like ?";
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + account + "%");
			rs = pst.executeQuery();
			DataBase.commit();
			studentList = new ArrayList<AdminInfo>();
			while (rs.next()) {
				adminInfo = new AdminInfo();
				adminInfo.setAccount(rs.getString("account"));
				adminInfo.setAdminName(rs.getString("admin_name"));
				adminInfo.setId(rs.getInt("id"));
				studentList.add(adminInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			throw e;
		} finally {
			rs.close();
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return studentList;

	}
}