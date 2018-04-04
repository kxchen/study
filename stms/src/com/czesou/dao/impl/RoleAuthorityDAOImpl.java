package com.czesou.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.czesou.dao.IRoleAuthorityDAO;
import com.czesou.db.DataBase;
import com.czesou.pojo.Authority;
import com.czesou.pojo.Role;
import com.czesou.pojo.RoleAuthority;

public class RoleAuthorityDAOImpl implements IRoleAuthorityDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}

	@Override
	public boolean save(RoleAuthority roleAuthority) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into role_authority(role_id,authority_id)" + "values (?,?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setInt(i++, roleAuthority.getRole().getId());
			pst.setInt(i++, roleAuthority.getAuthority().getId());
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
	public boolean delete(RoleAuthority roleAuthority) throws Exception {

		return false;
	}

	@Override
	public boolean update(RoleAuthority roleAuthority) throws Exception {

		return false;
	}

	@Override
	public RoleAuthority get(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleAuthority> list() throws Exception {

		return null;
	}

	@Override
	public List<RoleAuthority> list(Role role) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Authority> authorityList(Role role) throws Exception {
		List<Authority> authorityList = null;
		Authority authority = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from role_authority_view where role_id=? ";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, role.getId());
			rs = pst.executeQuery();
			authorityList = new ArrayList<Authority>();
			while (rs.next()) {
				authority = new Authority();
				authority.setId(rs.getInt("authority_id"));
				authority.setAuthorityName(rs.getString("authority_name"));
				authority.setUrl(rs.getString("url"));
				authorityList.add(authority);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return authorityList;
	}

	@Override
	public List<RoleAuthority> list(Authority authority) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int roleId) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from role_authority where role_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, roleId);
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
	public List<Integer> list(int roleId) throws Exception {
		List<Integer> stringList = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from role_authority_view where role_id=? ";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, roleId);
			rs = pst.executeQuery();
			stringList = new ArrayList<Integer>();
			while (rs.next()) {
				stringList.add(rs.getInt("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return stringList;
	}

	@Override
	public List<String> listAuthorityUrl(int roleId) throws Exception {
		List<String> stringList = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from role_authority_view where role_id=? ";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, roleId);
			rs = pst.executeQuery();
			stringList = new ArrayList<String>();
			while (rs.next()) {
				stringList.add(rs.getString("url"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return stringList;
	}

}
