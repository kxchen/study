package com.luna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.luna.dao.IShopCarDAO;
import com.luna.db.DataBase;
import com.luna.dto.CarInfo;
import com.luna.dto.ShopCarInfo;
import com.luna.dto.UserInfo;

public class ShopCarDAOImpl implements IShopCarDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}

	@Override
	public boolean save(ShopCarInfo shopCarInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into shop_car_info(shop_car_id,amount,user_id,car_id,take_time,ret_time) values (?,?,?,?,?,?)";
			System.out.println("ShopCarDAOImpl method=save sql=" + sql);
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, shopCarInfo.getShopCarId());
			pst.setInt(i++, shopCarInfo.getAmount());
			pst.setString(i++, shopCarInfo.getUserInfo().getUserId());
			pst.setString(i++, shopCarInfo.getCarInfo().getCarId());
			java.sql.Date takeTime = (java.sql.Date) shopCarInfo.getTakeTime();
			pst.setDate(i++, takeTime);
			java.sql.Date retTime = (java.sql.Date) shopCarInfo.getRetTime();
			pst.setDate(i++, retTime);
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
	public boolean delete(String result) throws Exception {
		PreparedStatement ps = null;
		boolean flag = false;
		try {
			String sql = "delete from shop_car_info where shop_car_id in (" + result + ")";
			System.out.println("ShopCarDAOImpl method= delete sql="+sql);
			ps = conn.prepareStatement(sql);
			System.out.println();
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			ps.close();
		}
		return flag;
	}

	@Override
	public boolean delete(ShopCarInfo shopCarInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from shop_car_info where shop_car_id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, shopCarInfo.getShopCarId());
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
	public boolean deleteAll(UserInfo userInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from shop_car_info where user_id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userInfo.getUserId());
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
	public boolean update(ShopCarInfo shopCarInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "update shop_car_info set amount=?,user_id=?,car_id=?,take_id=?,ret_time=? where shop_car_id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setInt(i++, shopCarInfo.getAmount());
			pst.setString(i++, shopCarInfo.getUserInfo().getUserId());
			pst.setString(i++, shopCarInfo.getCarInfo().getCarId());
			java.sql.Date takeTime = (java.sql.Date) shopCarInfo.getTakeTime();
			pst.setDate(i++, takeTime);
			java.sql.Date retTime = (java.sql.Date) shopCarInfo.getRetTime();
			pst.setDate(i++, retTime);
			pst.setString(i++, shopCarInfo.getShopCarId());
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
	public ShopCarInfo get(String shopCarId) throws Exception {
		ShopCarInfo shopCarInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		UserInfo userInfo = null;
		CarInfo carInfo = new CarInfo();
		try {
			String sql = "select * from shop_car_view where shop_car_id =?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, shopCarId);
			rs = pst.executeQuery();
			if (rs.next()) {
				shopCarInfo = new ShopCarInfo();
				shopCarInfo.setAmount(rs.getInt("amount"));
				shopCarInfo.setRetTime(rs.getDate("ret_time"));
				shopCarInfo.setTakeTime(rs.getDate("take_time"));
				shopCarInfo.setShopCarId(rs.getString("shop_car_id"));

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

				shopCarInfo.setCarInfo(carInfo);

				userInfo = new UserInfo();
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setIdNum(rs.getString("id_num"));
				userInfo.setIdImg(rs.getString("id_img"));
				userInfo.setDriverImg(rs.getString("driver_img"));
				userInfo.setIsAct(rs.getInt("is_act"));
				userInfo.setIsCer(rs.getInt("is_cer"));
				userInfo.setCode(rs.getString("code"));
				userInfo.setUserId(rs.getString("user_id"));
				userInfo.setRemBal(rs.getDouble("rem_bal"));
				userInfo.setDisCou(rs.getDouble("dis_cou"));
				userInfo.setHeadImg(rs.getString("head_img"));
				userInfo.setAddress(rs.getString("address"));
				userInfo.setOthPhone(rs.getString("oth_phone"));
				userInfo.setOthName(rs.getString("oth_name"));
				userInfo.setOther(rs.getString("other"));
				shopCarInfo.setUserInfo(userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return shopCarInfo;
	}

	@Override
	public ShopCarInfo get(UserInfo user, CarInfo carenfo) throws Exception {
		ShopCarInfo shopCarInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		UserInfo userInfo = null;
		CarInfo carInfo = null;
		try {
			String sql = "select * from shop_cart_view where user_id =? and mob_phone_id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUserId());
			pst.setString(2, carenfo.getCarId());
			rs = pst.executeQuery();
			if (rs.next()) {
				shopCarInfo = new ShopCarInfo();
				shopCarInfo.setAmount(rs.getInt("amount"));
				shopCarInfo.setRetTime(rs.getDate("ret_time"));
				shopCarInfo.setTakeTime(rs.getDate("take_time"));
				shopCarInfo.setShopCarId(rs.getString("shop_car_id"));
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

				shopCarInfo.setCarInfo(carInfo);

				userInfo = new UserInfo();
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setIdNum(rs.getString("id_num"));
				userInfo.setIdImg(rs.getString("id_img"));
				userInfo.setDriverImg(rs.getString("driver_img"));
				userInfo.setIsAct(rs.getInt("is_act"));
				userInfo.setIsCer(rs.getInt("is_cer"));
				userInfo.setCode(rs.getString("code"));
				userInfo.setUserId(rs.getString("user_id"));
				userInfo.setRemBal(rs.getDouble("rem_bal"));
				userInfo.setDisCou(rs.getDouble("dis_cou"));
				userInfo.setHeadImg(rs.getString("head_img"));
				userInfo.setAddress(rs.getString("address"));
				userInfo.setOthPhone(rs.getString("oth_phone"));
				userInfo.setOthName(rs.getString("oth_name"));
				userInfo.setOther(rs.getString("other"));
				shopCarInfo.setUserInfo(userInfo);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return shopCarInfo;
	}

	@Override
	public List<ShopCarInfo> list(UserInfo user) throws Exception {
		ShopCarInfo shopCarInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		UserInfo userInfo = null;
		CarInfo carInfo = new CarInfo();
		List<ShopCarInfo> shopCarInfoList = null;
		try {
			shopCarInfoList = new ArrayList<ShopCarInfo>();
			String sql = "select * from shop_car_view where user_id =?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUserId());
			rs = pst.executeQuery();
			while (rs.next()) {
				shopCarInfo = new ShopCarInfo();
				shopCarInfo.setAmount(rs.getInt("amount"));
				shopCarInfo.setRetTime(rs.getDate("ret_time"));
				shopCarInfo.setTakeTime(rs.getDate("take_time"));
				shopCarInfo.setShopCarId(rs.getString("shop_car_id"));
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

				shopCarInfo.setCarInfo(carInfo);

				userInfo = new UserInfo();
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setIdNum(rs.getString("id_num"));
				userInfo.setIdImg(rs.getString("id_img"));
				userInfo.setDriverImg(rs.getString("driver_img"));
				userInfo.setIsAct(rs.getInt("is_act"));
				userInfo.setIsCer(rs.getInt("is_cer"));
				userInfo.setCode(rs.getString("code"));
				userInfo.setUserId(rs.getString("user_id"));
				userInfo.setRemBal(rs.getDouble("rem_bal"));
				userInfo.setDisCou(rs.getDouble("dis_cou"));
				userInfo.setHeadImg(rs.getString("head_img"));
				userInfo.setAddress(rs.getString("address"));
				userInfo.setOthPhone(rs.getString("oth_phone"));
				userInfo.setOthName(rs.getString("oth_name"));
				userInfo.setOther(rs.getString("other"));
				shopCarInfo.setUserInfo(userInfo);
				shopCarInfoList.add(shopCarInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return shopCarInfoList;
	}

}
