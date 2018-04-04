package com.czesou.service;

import java.sql.Connection;
import java.util.List;

import com.czesou.dao.ICaseDAO;
import com.czesou.dao.impl.CaseDAOImpl;
import com.czesou.db.DataBase;
import com.czesou.pojo.Case;

public class CaseService {
	ICaseDAO caseDAOImpl = new CaseDAOImpl();

	public boolean save(Case caseInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			caseDAOImpl.setConn(connection);
			flag = caseDAOImpl.save(caseInfo);
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

	public boolean delete(Case caseInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			caseDAOImpl.setConn(connection);
			flag = caseDAOImpl.delete(caseInfo);
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

	public boolean update(Case caseInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			caseDAOImpl.setConn(connection);
			flag = caseDAOImpl.update(caseInfo);
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

	public Case get(String caseId) throws Exception {
		Case caseInfo = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			caseDAOImpl.setConn(connection);
			caseInfo = caseDAOImpl.get(caseId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return caseInfo;
	}

	public List<Case> list() throws Exception {
		List<Case> caseList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			caseDAOImpl.setConn(connection);
			caseList = caseDAOImpl.list();
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return caseList;
	}

	public List<Case> list(int pageSize, int pageNo, String queryString) throws Exception {
		List<Case> caseList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			caseDAOImpl.setConn(connection);
			caseList = caseDAOImpl.list(pageSize, pageNo, queryString);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return caseList;
	}

	public int getCount(String queryString) throws Exception {
		Connection connection = null;
		int count = 0;
		try {
			connection = DataBase.getConn();
			caseDAOImpl.setConn(connection);
			count = caseDAOImpl.getCount(queryString);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return count;
	}
}
