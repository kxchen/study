package com.czesou.service;

import java.sql.Connection;
import java.util.List;

import com.czesou.dao.IRoleDAO;
import com.czesou.dao.impl.RoleDAOImpl;
import com.czesou.db.DataBase;
import com.czesou.pojo.Role;

public class RoleService {
	IRoleDAO roleDAOImpl = new RoleDAOImpl();

	public boolean save(Role role) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			roleDAOImpl.setConn(connection);
			flag = roleDAOImpl.save(role);
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

	public boolean delete(Role role) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			roleDAOImpl.setConn(connection);
			flag = roleDAOImpl.delete(role);
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

	public boolean update(Role role) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			roleDAOImpl.setConn(connection);
			flag = roleDAOImpl.update(role);
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

	public Role get(int i) throws Exception {
		Role role = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			roleDAOImpl.setConn(connection);
			role = roleDAOImpl.get(i);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return role;
	}
	
	public Role get(String name) throws Exception {
		Role role = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			roleDAOImpl.setConn(connection);
			role = roleDAOImpl.get(name);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return role;
	}

	public List<Role> list() throws Exception {
		List<Role> roleList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			roleDAOImpl.setConn(connection);
			roleList = roleDAOImpl.list();
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return roleList;
	}

}
