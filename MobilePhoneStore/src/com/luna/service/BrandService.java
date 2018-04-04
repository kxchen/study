package com.luna.service;

import java.sql.Connection;
import java.util.List;

import com.luna.dao.IBrandDAO;
import com.luna.dao.impl.BrandDAOImpl;
import com.luna.database.DataBase;
import com.luna.dto.Brand;

public class BrandService {

	IBrandDAO brandDAO = new BrandDAOImpl();

	public boolean save(Brand brand) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			brandDAO.setConn(connection);
			flag = brandDAO.save(brand);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return flag;
	}

	public boolean delete(Brand brand) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			brandDAO.setConn(connection);
			flag = brandDAO.delete(brand);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return flag;
	}

	public boolean update(Brand brand) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			brandDAO.setConn(connection);
			flag = brandDAO.update(brand);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return flag;
	}

	public Brand get(int brandId) throws Exception {
		Brand brand = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			brandDAO.setConn(connection);
			brand = brandDAO.get(brandId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return brand;
	}

	public List<Brand> list() throws Exception {
		List<Brand> brandList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			brandDAO.setConn(connection);
			brandList = brandDAO.list();
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return brandList;
	}

}
