package com.czesou.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.czesou.dao.IAppendixDAO;
import com.czesou.db.DataBase;
import com.czesou.pojo.Appendix;

public class AppendixDAOImpl implements IAppendixDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}

	@Override
	public boolean save(Appendix appendix) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into appendix(id,appendix_name,path,create_date,owner)" + "values (?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, appendix.getId());
			pst.setString(i++, appendix.getAppendixName());
			pst.setString(i++, appendix.getPath());
			pst.setDate(i++, (Date) appendix.getCreateDate());
			pst.setString(i++, appendix.getOwner());
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
	public boolean delete(Appendix appendix) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from appendix where id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, appendix.getId());
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
	public boolean update(Appendix appendix) throws Exception {

		return false;
	}

	@Override
	public Appendix get(String appendixId) throws Exception {
		Appendix appendix = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from appendix where id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, appendixId);
			rs = pst.executeQuery();
			if (rs.next()) {
				appendix = new Appendix();
				appendix.setId(rs.getString("id"));
				appendix.setAppendixName(rs.getString("appendix_name"));
				appendix.setPath(rs.getString("path"));
				appendix.setOwner(rs.getString("owner"));
				appendix.setCreateDate(rs.getDate("create_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return appendix;
	}

	@Override
	public List<Appendix> list() throws Exception {
		List<Appendix> appendixList = null;
		Appendix appendix = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from appendix ";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			appendixList = new ArrayList<Appendix>();
			while (rs.next()) {
				appendix = new Appendix();
				appendix.setId(rs.getString("id"));
				appendix.setAppendixName(rs.getString("appendix_name"));
				appendix.setPath(rs.getString("path"));
				appendix.setOwner(rs.getString("owner"));
				appendix.setCreateDate(rs.getDate("create_date"));
				appendixList.add(appendix);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return appendixList;
	}

	@Override
	public int getCount() throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(*) from appendix";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return count;
	}

	@Override
	public List<Appendix> listByOwner(String id) throws Exception {
		List<Appendix> appendixList = null;
		Appendix appendix = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from appendix where owner=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, id);
			rs = pst.executeQuery();
			appendixList = new ArrayList<Appendix>();
			while (rs.next()) {
				appendix = new Appendix();
				appendix.setId(rs.getString("id"));
				appendix.setAppendixName(rs.getString("appendix_name"));
				appendix.setPath(rs.getString("path"));
				appendix.setOwner(rs.getString("owner"));
				appendix.setCreateDate(rs.getDate("create_date"));
				appendixList.add(appendix);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return appendixList;
	}

}
