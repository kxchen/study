package com.czesou.service;

import java.sql.Connection;
import java.util.List;

import com.czesou.dao.IHomeworkDAO;
import com.czesou.dao.impl.HomeworkDAOImpl;
import com.czesou.db.DataBase;
import com.czesou.pojo.Homework;

public class HomeworkService {
	IHomeworkDAO homeworkDAOImpl = new HomeworkDAOImpl();

	public boolean save(Homework homework) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			homeworkDAOImpl.setConn(connection);
			flag = homeworkDAOImpl.save(homework);
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

	public boolean delete(Homework homework) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			homeworkDAOImpl.setConn(connection);
			flag = homeworkDAOImpl.delete(homework);
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

	public boolean delete(String caseId) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			homeworkDAOImpl.setConn(connection);
			flag = homeworkDAOImpl.delete(caseId);
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

	public boolean update(Homework homework) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			homeworkDAOImpl.setConn(connection);
			flag = homeworkDAOImpl.update(homework);
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

	public Homework get(String homeworkId) throws Exception {
		Homework homework = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			homeworkDAOImpl.setConn(connection);
			homework = homeworkDAOImpl.get(homeworkId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return homework;
	}

	public List<Homework> list() throws Exception {
		List<Homework> homeworkList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			homeworkDAOImpl.setConn(connection);
			homeworkList = homeworkDAOImpl.list();
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return homeworkList;
	}

	public List<Homework> list(int pageSize, int pageNo, String queryString) throws Exception {
		List<Homework> homeworkList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			homeworkDAOImpl.setConn(connection);
			homeworkList = homeworkDAOImpl.list(pageSize, pageNo, queryString);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return homeworkList;
	}

	public int getCount(String queryString) throws Exception {
		Connection connection = null;
		int count = 0;
		try {
			connection = DataBase.getConn();
			homeworkDAOImpl.setConn(connection);
			count = homeworkDAOImpl.getCount(queryString);
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
