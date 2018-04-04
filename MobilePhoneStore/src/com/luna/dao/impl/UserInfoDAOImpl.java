package com.luna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.luna.dao.IUserInfoDAO;
import com.luna.database.DataBase;
import com.luna.dto.UserInfo;

/**
 * @ClassName: IUserInfoDAOImpl
 * @Description: 用户业务逻辑接口实现类
 * @author c-kx@outlook.com
 * @date 2015年11月24日 下午3:30:04
 * 
 */
public class UserInfoDAOImpl implements IUserInfoDAO {
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
			String sql = "insert into user_info(login_name,address,email,password,phone,real_name,regtime,state,code) values (?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, userInfo.getLoginName());
			pst.setString(i++, userInfo.getAddress());
			pst.setString(i++, userInfo.getEmail());
			pst.setString(i++, userInfo.getPassword());
			pst.setString(i++, userInfo.getPhone());
			pst.setString(i++, userInfo.getRealName());
			java.sql.Date regtime = (java.sql.Date) userInfo.getRegtime();
			pst.setDate(i++, regtime);
			pst.setInt(i++, userInfo.getState());
			pst.setString(i++, userInfo.getCode());
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
			pst.setInt(1, userInfo.getUserId());
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
			String sql = "update user_info set login_name=?, address=?,email=?,password=?,phone=?,real_name=?,regtime=?,state=? where user_id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, userInfo.getLoginName());
			pst.setString(i++, userInfo.getAddress());
			pst.setString(i++, userInfo.getEmail());
			pst.setString(i++, userInfo.getPassword());
			pst.setString(i++, userInfo.getPhone());
			pst.setString(i++, userInfo.getRealName());
			java.sql.Date regtime = (java.sql.Date) userInfo.getRegtime();
			pst.setDate(i++, regtime);
			pst.setInt(i++, userInfo.getState());
			pst.setInt(i++, userInfo.getUserId());
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
	public UserInfo get(int userId) throws Exception {
		UserInfo userInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user_info where user_id =?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			rs = pst.executeQuery();
			if (rs.next()) {
				userInfo = new UserInfo();
				userInfo.setLoginName(rs.getString("login_name"));
				userInfo.setAddress(rs.getString("address"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setRealName(rs.getString("real_name"));
				userInfo.setRegtime(rs.getDate("regtime"));
				userInfo.setUserId(rs.getInt("user_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return userInfo;
	}

	public UserInfo active(String code) throws Exception {
		UserInfo userInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user_info where code = ?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, code);
			rs = pst.executeQuery();
			if (rs.next()) {
				userInfo = new UserInfo();
				userInfo.setLoginName(rs.getString("login_name"));
				userInfo.setAddress(rs.getString("address"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setRealName(rs.getString("real_name"));
				userInfo.setRegtime(rs.getDate("regtime"));
				userInfo.setUserId(rs.getInt("user_id"));
				userInfo.setCode(rs.getString("code"));
				userInfo.setState(rs.getInt("state"));
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
			String sql = "select * from user_info " + queryString + " limit ?,?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, pageSize * (pageNo - 1));
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			userInfoList = new ArrayList<UserInfo>();
			while (rs.next()) {
				userInfo = new UserInfo();
				userInfo.setLoginName(rs.getString("login_name"));
				userInfo.setAddress(rs.getString("address"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setRealName(rs.getString("real_name"));
				userInfo.setRegtime(rs.getDate("regtime"));
				userInfo.setUserId(rs.getInt("user_id"));
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
	public UserInfo login(String loginName, String password) throws Exception {
		UserInfo userInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user_info where login_name=? and password=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, loginName);
			pst.setString(i++, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				userInfo = new UserInfo();
				userInfo.setLoginName(rs.getString("login_name"));
				userInfo.setAddress(rs.getString("address"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setRealName(rs.getString("real_name"));
				userInfo.setRegtime(rs.getDate("regtime"));
				userInfo.setUserId(rs.getInt("user_id"));
				userInfo.setCode(rs.getString("code"));
				userInfo.setState(rs.getInt("state"));
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
