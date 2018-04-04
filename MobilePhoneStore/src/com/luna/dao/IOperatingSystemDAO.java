package com.luna.dao;

import java.sql.Connection;
import java.util.List;

import com.luna.dto.OperatingSystem;

public interface IOperatingSystemDAO {
	public void setConn(Connection conn);

	public boolean save(OperatingSystem operatingSystem) throws Exception;

	public boolean delete(OperatingSystem operatingSystem) throws Exception;

	public boolean update(OperatingSystem operatingSystem) throws Exception;

	public OperatingSystem get(int opeSystemId) throws Exception;

	public List<OperatingSystem> list() throws Exception;
}
