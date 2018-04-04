package com.czesou.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.czesou.dao.ICaseDAO;
import com.czesou.db.DataBase;
import com.czesou.pojo.Case;
import com.czesou.pojo.Department;
import com.czesou.pojo.Role;
import com.czesou.pojo.User;

public class CaseDAOImpl implements ICaseDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}

	@Override
	public boolean save(Case caseInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into case_info(id,case_name,remark,user_id,cerate_date) value (?,?,?,?,?) ";
			System.out.println(sql);
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, caseInfo.getId());
			pst.setString(i++, caseInfo.getCaseName());
			pst.setString(i++, caseInfo.getRemark());
			pst.setString(i++, caseInfo.getUser().getId());
			pst.setDate(i++, (Date) caseInfo.getCreateDate());
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
	public boolean delete(Case caseInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from case_info where id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, caseInfo.getId());
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
	public boolean update(Case caseInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "update case_info set case_name=?,remark=? where id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, caseInfo.getCaseName());
			pst.setString(i++, caseInfo.getRemark());
			pst.setString(i++, caseInfo.getId());
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
	public Case get(String caseId) throws Exception {
		Case caseInfo = null;
		User userInfo = null;
		Role role = null;
		Department department = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from case_view where id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, caseId);
			rs = pst.executeQuery();
			if (rs.next()) {
				role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				department = new Department();
				department.setId(rs.getInt("department_id"));
				department.setDepartmentName(rs.getString("department_name"));
				userInfo = new User();
				userInfo.setId(rs.getString("user_id"));
				userInfo.setDepartment(department);
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setRole(role);
				userInfo.setPassword(rs.getString("password"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setState(rs.getInt("state"));
				caseInfo = new Case();
				caseInfo.setCaseName(rs.getString("case_name"));
				caseInfo.setCreateDate(rs.getDate("create_date"));
				caseInfo.setId(rs.getString("id"));
				caseInfo.setRemark(rs.getString("remark"));
				caseInfo.setUser(userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return caseInfo;
	}

	@Override
	public List<Case> list() throws Exception {
		List<Case> caseList = null;
		Case caseInfo = null;
		User userInfo = null;
		Role role = null;
		Department department = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from case_view ";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			caseList = new ArrayList<Case>();
			while (rs.next()) {
				role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				department = new Department();
				department.setId(rs.getInt("department_id"));
				department.setDepartmentName(rs.getString("department_name"));
				userInfo = new User();
				userInfo.setId(rs.getString("user_id"));
				userInfo.setDepartment(department);
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setRole(role);
				userInfo.setPassword(rs.getString("password"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setState(rs.getInt("state"));
				caseInfo = new Case();
				caseInfo.setCaseName(rs.getString("case_name"));
				caseInfo.setCreateDate(rs.getDate("create_date"));
				caseInfo.setId(rs.getString("id"));
				caseInfo.setRemark(rs.getString("remark"));
				caseInfo.setUser(userInfo);
				caseList.add(caseInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return caseList;
	}

	@Override
	public List<Case> list(int pageSize, int pageNo, String queryString) throws Exception {
		List<Case> caseList = null;
		Case caseInfo = null;
		User userInfo = null;
		Role role = null;
		Department department = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from case_view " + queryString + " limit ?,? ";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, pageSize * (pageNo - 1));
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			caseList = new ArrayList<Case>();
			while (rs.next()) {
				role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				department = new Department();
				department.setId(rs.getInt("department_id"));
				department.setDepartmentName(rs.getString("department_name"));
				userInfo = new User();
				userInfo.setId(rs.getString("user_id"));
				userInfo.setDepartment(department);
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setRole(role);
				userInfo.setPassword(rs.getString("password"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setState(rs.getInt("state"));
				caseInfo = new Case();
				caseInfo.setCaseName(rs.getString("case_name"));
				caseInfo.setCreateDate(rs.getDate("create_date"));
				caseInfo.setId(rs.getString("id"));
				caseInfo.setRemark(rs.getString("remark"));
				caseInfo.setUser(userInfo);
				caseList.add(caseInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return caseList;
	}

	@Override
	public int getCount(String queryString) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(*) from case_view ";
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
