package com.luna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.luna.dao.IScreenSizeDAO;
import com.luna.database.DataBase;
import com.luna.dto.ScreenSize;

public class ScreenSizeDAOImpl implements IScreenSizeDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}

	@Override
	public boolean save(ScreenSize screenSize) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into screen_size(scr_size_name) values (?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, screenSize.getScrSizeName());
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
	public boolean delete(ScreenSize screenSize) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from screen_size where scr_size_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, screenSize.getScrSizeId());
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
	public boolean update(ScreenSize screenSize) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "update screen_size set scr_size_name=? where scr_size_id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, screenSize.getScrSizeName());
			pst.setInt(i++, screenSize.getScrSizeId());
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
	public ScreenSize get(int scrSizeId) throws Exception {
		ScreenSize screenSize = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from screen_size where scr_size_id =?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, scrSizeId);
			rs = pst.executeQuery();
			if (rs.next()) {
				screenSize = new ScreenSize();
				screenSize.setScrSizeName(rs.getString("scr_size_name"));
				screenSize.setScrSizeId(rs.getInt("scr_size_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return screenSize;
	}

	@Override
	public List<ScreenSize> list() throws Exception {
		List<ScreenSize> screenSizeList = null;
		ScreenSize screenSize = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from screen_size";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			screenSizeList = new ArrayList<ScreenSize>();
			while (rs.next()) {
				screenSize = new ScreenSize();
				screenSize.setScrSizeName(rs.getString("scr_size_name"));
				screenSize.setScrSizeId(rs.getInt("scr_size_id"));
				screenSizeList.add(screenSize);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return screenSizeList;
	}

}
