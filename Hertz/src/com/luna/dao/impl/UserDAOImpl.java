package com.luna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.luna.dao.IUserDAO;
import com.luna.db.DataBase;
import com.luna.dto.UserInfo;

public class UserDAOImpl implements IUserDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}

	@Override
	public boolean save(UserInfo userInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into user_info(phone,password,user_name,sex,id_num,id_img,driver_img,is_act,is_cer,"
					+ "code,user_id,rem_bal,head_img ,dis_cou,address,oth_phone,oth_name,other) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, userInfo.getPhone());
			pst.setString(i++, userInfo.getPassword());
			pst.setString(i++, userInfo.getUserName());
			pst.setString(i++, userInfo.getSex());
			pst.setString(i++, userInfo.getIdNum());
			pst.setString(i++, userInfo.getIdImg());
			pst.setString(i++, userInfo.getDriverImg());
			pst.setInt(i++, userInfo.getIsAct());
			pst.setInt(i++, userInfo.getIsCer());
			pst.setString(i++, userInfo.getCode());
			pst.setString(i++, userInfo.getUserId());
			pst.setDouble(i++, userInfo.getRemBal());
			pst.setString(i++, userInfo.getHeadImg());
			pst.setDouble(i++, userInfo.getDisCou());
			pst.setString(i++, userInfo.getAddress());
			pst.setString(i++, userInfo.getOthPhone());
			pst.setString(i++, userInfo.getOthName());
			pst.setString(i++, userInfo.getOther());
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
	public boolean delete(UserInfo userInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from user_info where user_id=?";
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
	public boolean update(UserInfo userInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "update user_info set phone=?,password=?,user_name=?,sex=?,id_num=?,id_img=?,driver_img=?,"
					+ "is_act=?,is_cer=?,code=?,rem_bal=?,head_img=?,dis_cou=?,address=?,oth_phone=?,oth_name=?,other=? where user_id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, userInfo.getPhone());
			pst.setString(i++, userInfo.getPassword());
			pst.setString(i++, userInfo.getUserName());
			pst.setString(i++, userInfo.getSex());
			pst.setString(i++, userInfo.getIdNum());
			pst.setString(i++, userInfo.getIdImg());
			pst.setString(i++, userInfo.getDriverImg());
			pst.setInt(i++, userInfo.getIsAct());
			pst.setInt(i++, userInfo.getIsCer());
			pst.setString(i++, userInfo.getCode());
			pst.setDouble(i++, userInfo.getRemBal());
			pst.setString(i++, userInfo.getHeadImg());
			pst.setDouble(i++, userInfo.getDisCou());
			pst.setString(i++, userInfo.getAddress());
			pst.setString(i++, userInfo.getOthPhone());
			pst.setString(i++, userInfo.getOthName());
			pst.setString(i++, userInfo.getOther());
			pst.setString(i++, userInfo.getUserId());
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
	public UserInfo get(String userId) throws Exception {
		UserInfo userInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user_info where user_id=? ";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, userId);
			rs = pst.executeQuery();
			if (rs.next()) {
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
				userInfo.setAddress(rs.getString("address"));
				userInfo.setOthPhone(rs.getString("oth_phone"));
				userInfo.setOthName(rs.getString("oth_name"));
				userInfo.setOther(rs.getString("other"));
				userInfo.setHeadImg(rs.getString("head_img"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return userInfo;
	}

	@Override
	public UserInfo getByPhone(String phone) throws Exception {
		UserInfo userInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user_info where phone=? ";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, phone);
			rs = pst.executeQuery();
			if (rs.next()) {
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
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return userInfo;
	}

	@Override
	public List<UserInfo> list(int pageSize, int pageNo, String queryString) throws Exception {
		List<UserInfo> userInfoList = null;
		UserInfo userInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user_info " + queryString +  " limit ?,? ";
			System.out.println("UserDAOImop method=list sql=" + sql);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, pageSize * (pageNo - 1));
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			userInfoList = new ArrayList<UserInfo>();
			while (rs.next()) {
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
				userInfoList.add(userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return userInfoList;
	}

	@Override
	public UserInfo login(String phone, String password) throws Exception {
		UserInfo userInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user_info where phone=? and password=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, phone);
			pst.setString(i++, password);
			rs = pst.executeQuery();
			if (rs.next()) {
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
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return userInfo;
	}

	@Override
	public int getCount(String queryString) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(*) from user_info";
			pst = conn.prepareStatement(sql + queryString);
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
