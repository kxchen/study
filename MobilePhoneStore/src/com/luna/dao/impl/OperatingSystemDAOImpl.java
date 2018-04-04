package com.luna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.luna.dao.IOperatingSystemDAO;
import com.luna.database.DataBase;
import com.luna.dto.OperatingSystem;

public class OperatingSystemDAOImpl implements IOperatingSystemDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}

	@Override
	public boolean save(OperatingSystem operatingSystem) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into operating_system(ope_system_name) values (?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, operatingSystem.getOpeSystemName());
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
	public boolean delete(OperatingSystem operatingSystem) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from operating_system where ope_system_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, operatingSystem.getOpeSystemId());
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
	public boolean update(OperatingSystem operatingSystem) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "update operating_system set ope_system_name=? where ope_system_id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, operatingSystem.getOpeSystemName());
			pst.setInt(i++, operatingSystem.getOpeSystemId());
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
	public OperatingSystem get(int opeSystemId) throws Exception {
		OperatingSystem operatingSystem = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from operating_system where ope_system_id =?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, opeSystemId);
			rs = pst.executeQuery();
			if (rs.next()) {
				operatingSystem = new OperatingSystem();
				operatingSystem.setOpeSystemName(rs.getString("ope_system_name"));
				operatingSystem.setOpeSystemId(rs.getInt("ope_system_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs,pst);
		}
		return operatingSystem;
	}

	@Override
	public List<OperatingSystem> list() throws Exception {
		List<OperatingSystem> operatingSystemList = null;
		OperatingSystem operatingSystem = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from operating_system";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			operatingSystemList = new ArrayList<OperatingSystem>();
			while (rs.next()) {
				operatingSystem = new OperatingSystem();
				operatingSystem.setOpeSystemName(rs.getString("ope_system_name"));
				operatingSystem.setOpeSystemId(rs.getInt("ope_system_id"));
				operatingSystemList.add(operatingSystem);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs,pst);
		}
		return operatingSystemList;
	}

}
