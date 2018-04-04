package com.luna.service;

import java.sql.Connection;
import java.util.List;

import com.luna.dao.ICarDAO;
import com.luna.dao.impl.CarDAOImpl;
import com.luna.db.DataBase;
import com.luna.dto.CarInfo;

/**
 * @ClassName: CarService
 * @Description: 车的服务层
 * @author xiazhenghao
 * @date 2015年12月25日 上午10:21:18
 * 
 */
public class CarService {
	ICarDAO carDAOImpl = new CarDAOImpl();

	public int getCount(String queryString) throws Exception {
		Connection connection = null;
		int count = 0;
		try {
			connection = DataBase.getConn();
			carDAOImpl.setConn(connection);
			count = carDAOImpl.getCount(queryString);
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

	public List<CarInfo> list(int pageSize, int pageNo, String queryString) throws Exception {
		List<CarInfo> carInfoList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			carDAOImpl.setConn(connection);
			carInfoList = carDAOImpl.list(pageSize, pageNo, queryString);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return carInfoList;
	}

	public boolean save(CarInfo carInfo) throws Exception {
		System.out.println("CarService method=save");
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			carDAOImpl.setConn(connection);
			flag = carDAOImpl.save(carInfo);
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

	public boolean delete(CarInfo carInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			carDAOImpl.setConn(connection);
			flag = carDAOImpl.delete(carInfo);
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

	public boolean update(CarInfo carInfo) throws Exception {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DataBase.getConn();
			carDAOImpl.setConn(connection);
			flag = carDAOImpl.update(carInfo);
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

	public CarInfo get(String carId) throws Exception {
		CarInfo carInfo = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			carDAOImpl.setConn(connection);
			carInfo = carDAOImpl.get(carId);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return carInfo;
	}

	public List<CarInfo> list(String queryString) throws Exception {
		List<CarInfo> comboInfoList = null;
		Connection connection = null;
		try {
			connection = DataBase.getConn();
			carDAOImpl.setConn(connection);
			comboInfoList = carDAOImpl.list(queryString);
			DataBase.commit();
		} catch (Exception e) {
			DataBase.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(connection);
		}
		return comboInfoList;
	}
}