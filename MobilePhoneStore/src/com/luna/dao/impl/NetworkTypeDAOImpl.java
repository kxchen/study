package com.luna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.luna.dao.INetworkTypeDAO;
import com.luna.database.DataBase;
import com.luna.dto.NetworkType;

public class NetworkTypeDAOImpl implements INetworkTypeDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean save(NetworkType networkType) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into network_type(net_type_name) values (?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, networkType.getNetTypeName());
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
	public boolean delete(NetworkType networkType) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from  network_type where net_type_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, networkType.getNetTypeId());
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
	public boolean update(NetworkType networkType) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "update network_type set net_type_name = ? where net_type_id = ?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, networkType.getNetTypeName());
			pst.setInt(i++, networkType.getNetTypeId());
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
	public NetworkType get(int netTypeId) throws Exception {
		NetworkType networkType = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from network_type where net_type_id =?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, netTypeId);
			rs = pst.executeQuery();
			if (rs.next()) {
				networkType = new NetworkType();
				networkType.setNetTypeName((rs.getString("net_type_name")));
				networkType.setNetTypeId(rs.getInt("net_type_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs,pst);
		}
		return networkType;
	}

	@Override
	public List<NetworkType> list() throws Exception {
		List<NetworkType> networkTypeList = null;
		NetworkType networkType = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from network_type";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			networkTypeList = new ArrayList<NetworkType>();
			while (rs.next()) {
				networkType = new NetworkType();
				networkType.setNetTypeName((rs.getString("net_type_name")));
				networkType.setNetTypeId(rs.getInt("net_type_id"));
				networkTypeList.add(networkType);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs,pst);
		}
		return networkTypeList;
	}

}
