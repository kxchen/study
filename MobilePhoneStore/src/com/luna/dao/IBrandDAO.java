package com.luna.dao;

import java.sql.Connection;
import java.util.List;
import com.luna.dto.Brand;

public interface IBrandDAO {
	public void setConn(Connection conn);

	public boolean save(Brand brand) throws Exception;

	public boolean delete(Brand brand) throws Exception;

	public boolean update(Brand brand) throws Exception;

	public Brand get(int brandId) throws Exception;

	public List<Brand> list() throws Exception;
}
