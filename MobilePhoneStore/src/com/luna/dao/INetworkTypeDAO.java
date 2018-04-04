package com.luna.dao;

import java.sql.Connection;
import java.util.List;

import com.luna.dto.NetworkType;

public interface INetworkTypeDAO {
	public void setConn(Connection conn);

	public boolean save(NetworkType networkType) throws Exception;

	public boolean delete(NetworkType networkType) throws Exception;

	public boolean update(NetworkType networkType) throws Exception;

	public NetworkType get(int netTypeId) throws Exception;

	public List<NetworkType> list() throws Exception;
}
