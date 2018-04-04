package com.luna.dao;

import java.sql.Connection;
import java.util.List;

import com.luna.dto.ScreenSize;

public interface IScreenSizeDAO {
	public void setConn(Connection conn);

	public boolean save(ScreenSize screenSize) throws Exception;

	public boolean delete(ScreenSize screenSize) throws Exception;

	public boolean update(ScreenSize screenSize) throws Exception;

	public ScreenSize get(int scrSizeId) throws Exception;

	public List<ScreenSize> list() throws Exception;
}
