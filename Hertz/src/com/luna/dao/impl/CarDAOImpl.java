package com.luna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.luna.dao.ICarDAO;
import com.luna.db.DataBase;
import com.luna.dto.CarInfo;

/**
 * @ClassName: CarDAOImpl
 * @Description: 车的接口实现类
 * @author xiazhenghao
 * @date 2015年12月25日 上午10:19:15
 * 
 */
public class CarDAOImpl implements ICarDAO {

	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean save(CarInfo carInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into car_info(brand,model,type,buy_date,km,careful_img,car_img,gears,cc,"
					+ "color,criteria,price, seats,is_check,is_rent,rel_time,car_id,owner_id,purpose,remarks,paid_per,combo_name,count) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			System.out.println("CarDAOImpl method=save sql=" + sql);
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, carInfo.getBrand());
			pst.setString(i++, carInfo.getModel());
			pst.setString(i++, carInfo.getType());
			java.sql.Date buyDate = (java.sql.Date) carInfo.getBuyDate();
			pst.setDate(i++, buyDate);
			pst.setString(i++, carInfo.getKm());
			pst.setString(i++, carInfo.getCarefulImg());
			pst.setString(i++, carInfo.getCarImg());
			pst.setString(i++, carInfo.getGears());
			pst.setString(i++, carInfo.getCc());
			pst.setString(i++, carInfo.getColor());
			pst.setString(i++, carInfo.getCriteria());
			pst.setDouble(i++, carInfo.getPrice());
			pst.setString(i++, carInfo.getSeats());
			pst.setInt(i++, carInfo.getIsCheck());
			pst.setInt(i++, carInfo.getIsRent());
			java.sql.Date relTime = (java.sql.Date) carInfo.getRelTime();
			pst.setDate(i++, relTime);
			pst.setString(i++, carInfo.getCarId());
			pst.setString(i++, carInfo.getOwnerId());
			pst.setString(i++, carInfo.getPurpose());
			pst.setString(i++, carInfo.getRemarks());
			pst.setDouble(i++, carInfo.getPaidPer());
			pst.setString(i++, carInfo.getComboName());
			pst.setInt(i++, carInfo.getCount());
			if (pst.executeUpdate() > 0) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(pst);
		}
		return flag;
	}

	@Override
	public boolean delete(CarInfo carInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from car_info where car_id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, carInfo.getCarId());
			if (pst.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(pst);
		}
		return flag;
	}

	@Override
	public boolean update(CarInfo carInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {

			String sql = "update car_info set brand=?,model=?,type=?,buy_date=?,km=?,careful_img=?,car_img=?,gears=?,cc=?,"
					+ "color=?,criteria=?,price=?, seats=?,is_check=?,is_rent=?,rel_time=?,owner_id=?,purpose=?,remarks=?,paid_per=?,combo_name=?,count=? where car_id=?";
			System.out.println("CarDAOImpl method=update sql="+sql);
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, carInfo.getBrand());
			pst.setString(i++, carInfo.getModel());
			pst.setString(i++, carInfo.getType());
			java.sql.Date buyDate = (java.sql.Date) carInfo.getBuyDate();
			pst.setDate(i++, buyDate);
			pst.setString(i++, carInfo.getKm());
			pst.setString(i++, carInfo.getCarefulImg());
			pst.setString(i++, carInfo.getCarImg());
			pst.setString(i++, carInfo.getGears());
			pst.setString(i++, carInfo.getCc());
			pst.setString(i++, carInfo.getColor());
			pst.setString(i++, carInfo.getCriteria());
			pst.setDouble(i++, carInfo.getPrice());
			pst.setString(i++, carInfo.getSeats());
			pst.setInt(i++, carInfo.getIsCheck());
			pst.setInt(i++, carInfo.getIsRent());
			java.sql.Date relTime = (java.sql.Date) carInfo.getRelTime();
			pst.setDate(i++, relTime);
			
			pst.setString(i++, carInfo.getOwnerId());
			pst.setString(i++, carInfo.getPurpose());
			pst.setString(i++, carInfo.getRemarks());
			pst.setDouble(i++, carInfo.getPaidPer());
			pst.setString(i++, carInfo.getComboName());
			pst.setInt(i++, carInfo.getCount());
			pst.setString(i++, carInfo.getCarId());
			if (pst.executeUpdate() > 0) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(pst);
		}
		return flag;
	}

	@Override
	public CarInfo get(String carId) throws Exception {
		CarInfo carInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from  car_info where car_id=?";
			System.out.println("CarDAOImol method=get sql=" + sql);
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, carId);
			rs = pst.executeQuery();
			if (rs.next()) {
				carInfo = new CarInfo();
				carInfo.setBrand(rs.getString("brand"));
				carInfo.setModel(rs.getString("model"));
				carInfo.setType(rs.getString("type"));
				carInfo.setBrand(rs.getString("brand"));
				carInfo.setBuyDate(rs.getDate("buy_date"));
				carInfo.setKm(rs.getString("km"));
				carInfo.setCarefulImg(rs.getString("careful_img"));
				carInfo.setCarImg(rs.getString("car_img"));
				carInfo.setGears(rs.getString("gears"));
				carInfo.setCc(rs.getString("cc"));
				carInfo.setColor(rs.getString("color"));
				carInfo.setCriteria(rs.getString("criteria"));
				carInfo.setPrice(rs.getDouble("price"));
				carInfo.setSeats(rs.getString("seats"));
				carInfo.setIsCheck(rs.getInt("is_check"));
				carInfo.setIsRent(rs.getInt("is_rent"));
				carInfo.setRelTime(rs.getDate("rel_time"));
				carInfo.setCarId(rs.getString("car_id"));
				carInfo.setOwnerId(rs.getString("owner_id"));
				carInfo.setPurpose(rs.getString("purpose"));
				carInfo.setRemarks(rs.getString("remarks"));
				carInfo.setPaidPer(rs.getDouble("paid_per"));
				carInfo.setComboName(rs.getString("combo_name"));
				carInfo.setCount(rs.getInt("count"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(pst);
		}
		return carInfo;
	}

	@Override
	public List<CarInfo> list(String string) throws Exception {
		List<CarInfo> carInfoList = null;
		CarInfo carInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from car_info" + string;
			System.out.println("carDAOImpl method=list sql=" + sql);
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			carInfoList = new ArrayList<CarInfo>();
			while (rs.next()) {
				carInfo = new CarInfo();
				carInfo.setBrand(rs.getString("brand"));
				carInfo.setModel(rs.getString("model"));
				carInfo.setType(rs.getString("type"));
				carInfo.setBrand(rs.getString("brand"));
				carInfo.setBuyDate(rs.getDate("buy_date"));
				carInfo.setKm(rs.getString("km"));
				carInfo.setCarefulImg(rs.getString("careful_img"));
				carInfo.setCarImg(rs.getString("car_img"));
				carInfo.setGears(rs.getString("gears"));
				carInfo.setCc(rs.getString("cc"));
				carInfo.setColor(rs.getString("color"));
				carInfo.setCriteria(rs.getString("criteria"));
				carInfo.setPrice(rs.getDouble("price"));
				carInfo.setSeats(rs.getString("seats"));
				carInfo.setIsCheck(rs.getInt("is_check"));
				carInfo.setIsRent(rs.getInt("is_rent"));
				carInfo.setRelTime(rs.getDate("rel_time"));
				carInfo.setCarId(rs.getString("car_id"));
				carInfo.setOwnerId(rs.getString("owner_id"));
				carInfo.setPurpose(rs.getString("purpose"));
				carInfo.setRemarks(rs.getString("remarks"));
				carInfo.setPaidPer(rs.getDouble("paid_per"));
				carInfo.setComboName(rs.getString("combo_name"));
				carInfo.setCount(rs.getInt("count"));
				carInfoList.add(carInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return carInfoList;
	}

	@Override
	public List<CarInfo> list(int pageSize, int pageNo, String queryString) throws Exception {
		List<CarInfo> carInfoList = null;
		CarInfo carInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from car_info " + queryString + " limit ?,? ";
			System.out.println("CarDAOImop method=list sql=" + sql);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, pageSize * (pageNo - 1));
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			carInfoList = new ArrayList<CarInfo>();
			while (rs.next()) {
				carInfo = new CarInfo();
				carInfo.setBrand(rs.getString("brand"));
				carInfo.setModel(rs.getString("model"));
				carInfo.setType(rs.getString("type"));
				carInfo.setBrand(rs.getString("brand"));
				carInfo.setBuyDate(rs.getDate("buy_date"));
				carInfo.setKm(rs.getString("km"));
				carInfo.setCarefulImg(rs.getString("careful_img"));
				carInfo.setCarImg(rs.getString("car_img"));
				carInfo.setGears(rs.getString("gears"));
				carInfo.setCc(rs.getString("cc"));
				carInfo.setColor(rs.getString("color"));
				carInfo.setCriteria(rs.getString("criteria"));
				carInfo.setPrice(rs.getDouble("price"));
				carInfo.setSeats(rs.getString("seats"));
				carInfo.setIsCheck(rs.getInt("is_check"));
				carInfo.setIsRent(rs.getInt("is_rent"));
				carInfo.setRelTime(rs.getDate("rel_time"));
				carInfo.setCarId(rs.getString("car_id"));
				carInfo.setOwnerId(rs.getString("owner_id"));
				carInfo.setPurpose(rs.getString("purpose"));
				carInfo.setRemarks(rs.getString("remarks"));
				carInfo.setPaidPer(rs.getDouble("paid_per"));
				carInfo.setComboName(rs.getString("combo_name"));
				carInfo.setCount(rs.getInt("count"));
				carInfoList.add(carInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return carInfoList;
	}
	
	@Override
	public int getCount(String queryString) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(*) from car_info "+ queryString;
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return count;

	}
}
