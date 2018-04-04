package com.czesou.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.czesou.dao.IDepartmentDAO;
import com.czesou.db.DataBase;
import com.czesou.pojo.Department;

public class DepartmentDAOImpl implements IDepartmentDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}

	@Override
	public boolean save(Department department) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into department(department_name)" + "values (?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, department.getDepartmentName());
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
	public boolean delete(Department department) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from department where id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, department.getId());
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
	public boolean update(Department department) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "update department set department_name=? where id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, department.getDepartmentName());
			pst.setInt(i++, department.getId());
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
	public Department get(int id) throws Exception {
		Department department = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from department where id=? ";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setInt(i++, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				department = new Department();
				department.setId(rs.getInt("id"));
				department.setDepartmentName(rs.getString("department_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return department;
	}

	@Override
	public List<Department> list() throws Exception {
		List<Department> departmentList = null;
		Department department = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from department ";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			departmentList = new ArrayList<Department>();
			while (rs.next()) {
				department = new Department();
				department.setId(rs.getInt("id"));
				department.setDepartmentName(rs.getString("department_name"));
				departmentList.add(department);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return departmentList;
	}

	@Override
	public Department get(String string) throws Exception {
		Department department = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from department where department_name=? ";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, string);
			rs = pst.executeQuery();
			if (rs.next()) {
				department = new Department();
				department.setId(rs.getInt("id"));
				department.setDepartmentName(rs.getString("department_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return department;
	}

}
