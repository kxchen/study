package com.czesou.service;

import java.sql.Connection;
import java.util.List;

import com.czesou.dao.IAuthorityDAO;
import com.czesou.dao.impl.AuthorityDAOImpl;
import com.czesou.db.DataBase;
import com.czesou.pojo.Authority;

public class AuthorityService {
	IAuthorityDAO authorityDAOImpl = new AuthorityDAOImpl();

	public Authority get(int authorityId) throws Exception {
		Authority authority = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			authorityDAOImpl.setConn(connection);
			authority = authorityDAOImpl.get(authorityId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return authority;
	}

	public List<Authority> list() throws Exception {
		List<Authority> authorityList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			authorityDAOImpl.setConn(connection);
			authorityList = authorityDAOImpl.list();
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

	public List<Authority> list(String s) throws Exception {
		List<Authority> authorityList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			authorityDAOImpl.setConn(connection);
			authorityList = authorityDAOImpl.list(s);
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

	public String listAuthorityUrl(String string) throws Exception {
		String authorityList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			authorityDAOImpl.setConn(connection);
			authorityList = authorityDAOImpl.listAuthorityUrl(string);
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
