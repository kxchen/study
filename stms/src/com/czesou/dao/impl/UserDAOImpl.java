package com.czesou.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.czesou.dao.IUserDAO;
import com.czesou.db.DataBase;
import com.czesou.pojo.Department;
import com.czesou.pojo.Role;
import com.czesou.pojo.User;

public class UserDAOImpl implements IUserDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}

	@Override
	public boolean save(User user) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into user(id,user_name,password,phone,role_id,department_id,create_date,state) values (?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, user.getId());
			pst.setString(i++, user.getUserName());
			pst.setString(i++, user.getPassword());
			pst.setString(i++, user.getPhone());
			pst.setInt(i++, user.getRole().getId());
			pst.setInt(i++, user.getDepartment().getId());
			pst.setDate(i++, (Date) user.getCreateDate());
			pst.setInt(i++, user.getState());
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
	public boolean delete(User user) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from user_info where id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getId());
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
	public boolean update(User user) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "update user set user_name=?,password=?,phone=?,role_id=?,department_id=?,state=? where id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, user.getUserName());
			pst.setString(i++, user.getPassword());
			pst.setString(i++, user.getPhone());
			pst.setInt(i++, user.getRole().getId());
			pst.setInt(i++, user.getDepartment().getId());
			pst.setInt(i++, user.getState());
			pst.setString(i++, user.getId());
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
	public User login(String phone, String password) throws Exception {
		User userInfo = null;
		Role role = null;
		Department department = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user_view where phone=? and password=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, phone);
			pst.setString(i++, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				department = new Department();
				department.setId(rs.getInt("department_id"));
				department.setDepartmentName(rs.getString("department_name"));
				userInfo = new User();
				userInfo.setId(rs.getString("id"));
				userInfo.setCreateDate(rs.getDate("create_date"));
				userInfo.setDepartment(department);
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setRole(role);
				userInfo.setPassword(rs.getString("password"));
				userInfo.setUserName(rs.getString("user_name"));
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
	public User get(String id) throws Exception {
		System.out.println("UserDAOImpl+get(String id)");
		User userInfo = null;
		Role role = null;
		Department department = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user_view where id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				department = new Department();
				department.setId(rs.getInt("department_id"));
				department.setDepartmentName(rs.getString("department_name"));
				userInfo = new User();
				userInfo.setId(rs.getString("id"));
				userInfo.setCreateDate(rs.getDate("create_date"));
				userInfo.setDepartment(department);
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setRole(role);
				userInfo.setPassword(rs.getString("password"));
				userInfo.setUserName(rs.getString("user_name"));
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
	public List<User> list() throws Exception {
		List<User> userList = null;
		User userInfo = null;
		Role role = null;
		Department department = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user_view ";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			userList = new ArrayList<User>();
			while (rs.next()) {
				role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				department = new Department();
				department.setId(rs.getInt("department_id"));
				department.setDepartmentName(rs.getString("department_name"));
				userInfo = new User();
				userInfo.setId(rs.getString("id"));
				userInfo.setCreateDate(rs.getDate("create_date"));
				userInfo.setDepartment(department);
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setRole(role);
				userInfo.setPassword(rs.getString("password"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setState(rs.getInt("state"));
				userList.add(userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return userList;
	}

	@Override
	public List<User> list(int pageSize, int pageNo, String queryString) throws Exception {
		Role role = null;
		Department department = null;
		List<User> userInfoList = null;
		User userInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user_view " + queryString + " limit ?,? ";
			System.out.println("UserDAOImop method=list sql=" + sql);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, pageSize * (pageNo - 1));
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			userInfoList = new ArrayList<User>();
			while (rs.next()) {
				role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				department = new Department();
				department.setId(rs.getInt("department_id"));
				department.setDepartmentName(rs.getString("department_name"));
				userInfo = new User();
				userInfo.setId(rs.getString("id"));
				userInfo.setCreateDate(rs.getDate("create_date"));
				userInfo.setDepartment(department);
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setRole(role);
				userInfo.setPassword(rs.getString("password"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setState(rs.getInt("state"));
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
	public int getCount(String queryString) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(*) from user_view ";
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
