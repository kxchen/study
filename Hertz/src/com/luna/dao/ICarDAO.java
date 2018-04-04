package com.luna.dao;

import java.sql.Connection;
import java.util.List;

import com.luna.dto.CarInfo;

/**
 * @ClassName: ICarDAO
 * @Description: 车的接口
 * @author xiazhenghao
 * @date 2015年12月25日 上午10:18:46
 * 
 */
public interface ICarDAO {

	public void setConn(Connection conn);

	public boolean save(CarInfo carInfo) throws Exception;

	public boolean delete(CarInfo carInfo) throws Exception;

	public boolean update(CarInfo carInfo) throws Exception;

	public CarInfo get(String carId) throws Exception;

	public List<CarInfo> list(String string) throws Exception;

	public List<CarInfo> list(int pageSize, int pageNo, String queryString) throws Exception;

	public int getCount(String queryString) throws Exception;
}
