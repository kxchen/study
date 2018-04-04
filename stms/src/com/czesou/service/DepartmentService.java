package com.czesou.service;

import java.sql.Connection;
import java.util.List;

import com.czesou.dao.IDepartmentDAO;
import com.czesou.dao.impl.DepartmentDAOImpl;
import com.czesou.db.DataBase;
import com.czesou.pojo.Department;

public class DepartmentService {

	IDepartmentDAO departmentDAOImpl = new DepartmentDAOImpl();

	public boolean save(Department department) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			departmentDAOImpl.setConn(connection);
			flag = departmentDAOImpl.save(department);
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

	public boolean delete(Department department) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			departmentDAOImpl.setConn(connection);
			flag = departmentDAOImpl.delete(department);
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

	public boolean update(Department department) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			departmentDAOImpl.setConn(connection);
			flag = departmentDAOImpl.update(department);
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

	public Department get(int id) throws Exception {
		Department department = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			departmentDAOImpl.setConn(connection);
			department = departmentDAOImpl.get(id);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return department;
	}

	public Department get(String depName) throws Exception {
		Department department = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			departmentDAOImpl.setConn(connection);
			department = departmentDAOImpl.get(depName);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return department;
	}

	public List<Department> list() throws Exception {
		List<Department> departmentList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			departmentDAOImpl.setConn(connection);
			departmentList = departmentDAOImpl.list();
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return departmentList;
	}
}
