package com.czesou.service;

import java.sql.Connection;
import java.util.List;

import com.czesou.dao.IRoleAuthorityDAO;
import com.czesou.dao.impl.RoleAuthorityDAOImpl;
import com.czesou.db.DataBase;
import com.czesou.pojo.Authority;
import com.czesou.pojo.Role;
import com.czesou.pojo.RoleAuthority;

public class RoleAuthorityService {
	IRoleAuthorityDAO roleAuthorityDAOImpl = new RoleAuthorityDAOImpl();

	public boolean save(RoleAuthority roleAuthority) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			roleAuthorityDAOImpl.setConn(connection);
			flag = roleAuthorityDAOImpl.save(roleAuthority);
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

	public boolean delete(RoleAuthority roleAuthority) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			roleAuthorityDAOImpl.setConn(connection);
			flag = roleAuthorityDAOImpl.delete(roleAuthority);
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

	public boolean delete(int roleId) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			roleAuthorityDAOImpl.setConn(connection);
			flag = roleAuthorityDAOImpl.delete(roleId);
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

	public boolean update(RoleAuthority roleAuthority) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			roleAuthorityDAOImpl.setConn(connection);
			flag = roleAuthorityDAOImpl.update(roleAuthority);
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

	public RoleAuthority get(String id) throws Exception {
		RoleAuthority roleAuthority = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			roleAuthorityDAOImpl.setConn(connection);
			roleAuthority = roleAuthorityDAOImpl.get(id);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return roleAuthority;
	}

	public List<RoleAuthority> list() throws Exception {
		List<RoleAuthority> roleAuthorityList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			roleAuthorityDAOImpl.setConn(connection);
			roleAuthorityList = roleAuthorityDAOImpl.list();
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return roleAuthorityList;
	}

	public List<RoleAuthority> list(Role Role) throws Exception {
		List<RoleAuthority> roleAuthorityList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			roleAuthorityDAOImpl.setConn(connection);
			roleAuthorityList = roleAuthorityDAOImpl.list(Role);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return roleAuthorityList;
	}

	public List<RoleAuthority> list(Authority authority) throws Exception {
		List<RoleAuthority> roleAuthorityList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			roleAuthorityDAOImpl.setConn(connection);
			roleAuthorityList = roleAuthorityDAOImpl.list(authority);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return roleAuthorityList;
	}

	public List<Authority> authorityList(Role Role) throws Exception {
		List<Authority> authorityList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			roleAuthorityDAOImpl.setConn(connection);
			authorityList = roleAuthorityDAOImpl.authorityList(Role);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return authorityList;
	}

	public List<Integer> list(int RoleId) throws Exception {
		List<Integer> authorityList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			roleAuthorityDAOImpl.setConn(connection);
			authorityList = roleAuthorityDAOImpl.list(RoleId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return authorityList;
	}
	public List<String> listAuthorityUrl(int roleId) throws Exception {
		List<String> authorityList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			roleAuthorityDAOImpl.setConn(connection);
			authorityList = roleAuthorityDAOImpl.listAuthorityUrl(roleId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return authorityList;
	}
}
