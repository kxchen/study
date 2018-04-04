package com.luna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.luna.dao.IBrandDAO;
import com.luna.database.DataBase;
import com.luna.dto.Brand;

public class BrandDAOImpl implements IBrandDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}

	@Override
	public boolean save(Brand brand) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into brand(brand_name) values (?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, brand.getBrandName());
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
	public boolean delete(Brand brand) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from brand where brand_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, brand.getBrandId());
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
	public boolean update(Brand brand) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "update brand set brand_name=? where brand_id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, brand.getBrandName());
			pst.setInt(i++, brand.getBrandId());
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
	public Brand get(int brandId) throws Exception {
		Brand brand = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from brand where brand_id =?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, brandId);
			rs = pst.executeQuery();
			if (rs.next()) {
				brand = new Brand();
				brand.setBrandName(rs.getString("brand_name"));
				brand.setBrandId(rs.getInt("brand_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return brand;
	}

	@Override
	public List<Brand> list() throws Exception {
		List<Brand> brandList = null;
		Brand brand = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from brand";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			brandList = new ArrayList<Brand>();
			while (rs.next()) {
				brand = new Brand();
				brand.setBrandName(rs.getString("brand_name"));
				brand.setBrandId(rs.getInt("brand_id"));
				brandList.add(brand);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return brandList;
	}

}
