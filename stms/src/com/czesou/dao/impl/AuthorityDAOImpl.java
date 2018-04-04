package com.czesou.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.czesou.dao.IAuthorityDAO;
import com.czesou.db.DataBase;
import com.czesou.pojo.Authority;

public class AuthorityDAOImpl implements IAuthorityDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}

	@Override
	public Authority get(int authorityId) throws Exception {
		Authority authority = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from authority where id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setInt(i++, authorityId);
			rs = pst.executeQuery();
			if (rs.next()) {
				authority = new Authority();
				authority.setId(rs.getInt("id"));
				authority.setAuthorityName(rs.getString("authority_name"));
				authority.setUrl(rs.getString("url"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return authority;
	}

	@Override
	public List<Authority> list() throws Exception {
		List<Authority> authorityList = null;
		Authority authority = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from authority ";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			authorityList = new ArrayList<Authority>();
			while (rs.next()) {
				authority = new Authority();
				authority.setId(rs.getInt("id"));
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
	public List<Authority> list(String string) throws Exception {
		List<Authority> authorityList = null;
		Authority authority = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from authority " + string;
			System.out.println(sql);
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			authorityList = new ArrayList<Authority>();
			while (rs.next()) {
				authority = new Authority();
				authority.setId(rs.getInt("id"));
				authority.setAuthorityName(rs.getString("authority_name"));
				authority.setUrl(rs.getString("url"));
				authority.setParentId(rs.getInt("parent_id"));
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
	public String listAuthorityUrl(String string) throws Exception {
		StringBuffer sb = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			sb = new StringBuffer();
			String sql = "select * from authority where parent_id != 0 " + string;
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				sb.append(rs.getString("url"));
				sb.append(",");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return sb.toString();
	}

}
