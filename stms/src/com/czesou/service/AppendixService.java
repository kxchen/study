package com.czesou.service;

import java.sql.Connection;
import java.util.List;

import com.czesou.dao.IAppendixDAO;
import com.czesou.dao.impl.AppendixDAOImpl;
import com.czesou.db.DataBase;
import com.czesou.pojo.Appendix;

public class AppendixService {
	IAppendixDAO appendixDAOImpl = new AppendixDAOImpl();

	public boolean save(Appendix appendix) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			appendixDAOImpl.setConn(connection);
			flag = appendixDAOImpl.save(appendix);
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

	public boolean delete(Appendix appendix) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			appendixDAOImpl.setConn(connection);
			flag = appendixDAOImpl.delete(appendix);
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

	public boolean update(Appendix appendix) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			appendixDAOImpl.setConn(connection);
			flag = appendixDAOImpl.update(appendix);
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

	public Appendix get(String appendixId) throws Exception {
		Appendix appendix = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			appendixDAOImpl.setConn(connection);
			appendix = appendixDAOImpl.get(appendixId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return appendix;
	}

	public List<Appendix> list() throws Exception {
		List<Appendix> appendixList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			appendixDAOImpl.setConn(connection);
			appendixList = appendixDAOImpl.list();
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return appendixList;
	}

	public List<Appendix> listByOwner(String id) throws Exception {
		List<Appendix> appendixList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			appendixDAOImpl.setConn(connection);
			appendixList = appendixDAOImpl.listByOwner(id);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return appendixList;
	}

	public int getCount() throws Exception {
		Connection connection = null;
		int count = 0;
		try {
			connection = DataBase.getConn();
			appendixDAOImpl.setConn(connection);
			count = appendixDAOImpl.getCount();
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
