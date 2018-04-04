package com.luna.service;

import java.sql.Connection;
import java.util.List;

import com.luna.dao.IMobilePhoneInfoDAO;
import com.luna.dao.impl.MobilePhoneInfoDAOImpl;
import com.luna.database.DataBase;
import com.luna.dto.MobilePhoneInfo;

public class MobilePhoneInfoService {
	IMobilePhoneInfoDAO mobilePhoneInfoDAO = new MobilePhoneInfoDAOImpl();

	public boolean save(MobilePhoneInfo mobilePhoneInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			mobilePhoneInfoDAO.setConn(connection);
			flag = mobilePhoneInfoDAO.save(mobilePhoneInfo);
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

	public boolean delete(MobilePhoneInfo mobilePhoneInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			mobilePhoneInfoDAO.setConn(connection);
			flag = mobilePhoneInfoDAO.delete(mobilePhoneInfo);
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

	public boolean update(MobilePhoneInfo mobilePhoneInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			mobilePhoneInfoDAO.setConn(connection);
			flag = mobilePhoneInfoDAO.update(mobilePhoneInfo);
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

	public MobilePhoneInfo get(int mobilePhoneInfoId) throws Exception {
		MobilePhoneInfo mobilePhoneInfo = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			mobilePhoneInfoDAO.setConn(connection);
			mobilePhoneInfo = mobilePhoneInfoDAO.get(mobilePhoneInfoId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return mobilePhoneInfo;
	}

	public List<MobilePhoneInfo> hotSale() throws Exception {
		List<MobilePhoneInfo> mobilePhoneInfoList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			mobilePhoneInfoDAO.setConn(connection);
			mobilePhoneInfoList = mobilePhoneInfoDAO.hotSale();
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return mobilePhoneInfoList;
	}

	public List<MobilePhoneInfo> list(int pageSize, int pageNo, String queryString) throws Exception {
		List<MobilePhoneInfo> mobilePhoneInfoList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			mobilePhoneInfoDAO.setConn(connection);
			mobilePhoneInfoList = mobilePhoneInfoDAO.list(pageSize, pageNo, queryString);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return mobilePhoneInfoList;
	}

	public int getCount(String queryString) throws Exception {
		Connection connection = null;
		int count = 0;
		try {
			connection = DataBase.getConn();
			mobilePhoneInfoDAO.setConn(connection);
			count = mobilePhoneInfoDAO.getCount(queryString);
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
