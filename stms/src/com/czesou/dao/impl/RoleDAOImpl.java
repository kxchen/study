package com.czesou.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.czesou.dao.IRoleDAO;
import com.czesou.db.DataBase;
import com.czesou.pojo.Role;

public class RoleDAOImpl implements IRoleDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}

	@Override
	public Role get(int id) throws Exception {
		Role role = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from role where id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setInt(i++, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				role = new Role();
				role.setId(rs.getInt("id"));
				role.setRoleName(rs.getString("role_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return role;
	}
	@Override
	public Role get(String name) throws Exception {
		Role role = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from role where role_name=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, name);
			rs = pst.executeQuery();
			if (rs.next()) {
				role = new Role();
				role.setId(rs.getInt("id"));
				role.setRoleName(rs.getString("role_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return role;
	}
	@Override
	public List<Role> list() throws Exception {
		List<Role> roleList = null;
		Role role = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from role ";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			roleList = new ArrayList<Role>();
			while (rs.next()) {
				role = new Role();
				role.setId(rs.getInt("id"));
				role.setRoleName(rs.getString("role_name"));
				roleList.add(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return roleList;
	}

	@Override
	public boolean save(Role role) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into role(id,role_name)" + "values (?,?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setInt(i++, role.getId());
			pst.setString(i++, role.getRoleName());
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
	public boolean delete(Role role) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from role where id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, role.getId());
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
	public boolean update(Role role) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "update role set role_name=? where id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, role.getRoleName());
			pst.setInt(i++, role.getId());
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

}
