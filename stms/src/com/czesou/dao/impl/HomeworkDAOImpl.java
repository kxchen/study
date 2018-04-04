package com.czesou.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.czesou.dao.IHomeworkDAO;
import com.czesou.db.DataBase;
import com.czesou.pojo.Appendix;
import com.czesou.pojo.Case;
import com.czesou.pojo.Department;
import com.czesou.pojo.Homework;
import com.czesou.pojo.Role;
import com.czesou.pojo.User;

public class HomeworkDAOImpl implements IHomeworkDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean save(Homework homework) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into homework(id,homework_name,remark,user_id,appendix_id,create_date,case_id) value (?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, homework.getId());
			pst.setString(i++, homework.getHomeworkName());
			pst.setString(i++, homework.getRemark());
			pst.setString(i++, homework.getUser().getId());
			pst.setString(i++, homework.getAppendix().getId());
			pst.setDate(i++, (Date) homework.getCreateDate());
			pst.setString(i++, homework.getCaseInfo().getId());
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
	public boolean delete(Homework homework) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from homework where id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, homework.getId());
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
	public boolean update(Homework homework) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "update homework set homework_name=?,remark=?,appendix_id=? where id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, homework.getHomeworkName());
			pst.setString(i++, homework.getRemark());
			pst.setString(i++, homework.getAppendix().getId());
			pst.setString(i++, homework.getId());
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
	public Homework get(String homeWorkId) throws Exception {
		Homework homework = null;
		Case caseInfo = null;
		User userInfo = null;
		Role role = null;
		Department department = null;
		Appendix appendix = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from homework_view where id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, homeWorkId);
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
				appendix = new Appendix();
				appendix.setId(rs.getString("appendix_id"));
				appendix.setAppendixName(rs.getString("appendix_name"));
				appendix.setPath(rs.getString("path"));
				appendix.setOwner(rs.getString("owner"));
				homework = new Homework();
				homework.setAppendix(appendix);
				homework.setCaseInfo(caseInfo);
				homework.setCreateDate(rs.getDate("create_date"));
				homework.setHomeworkName(rs.getString("homework_name"));
				homework.setId(rs.getString("id"));
				homework.setRemark(rs.getString("remark"));
				homework.setUser(userInfo);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return homework;
	}

	@Override
	public List<Homework> list() throws Exception {
		List<Homework> homeworkList = null;
		Homework homework = null;
		Case caseInfo = null;
		User userInfo = null;
		Role role = null;
		Department department = null;
		Appendix appendix = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from homework_view ";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			homeworkList = new ArrayList<Homework>();
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
				appendix = new Appendix();
				appendix.setId(rs.getString("appendix_id"));
				appendix.setAppendixName(rs.getString("appendix_name"));
				appendix.setPath(rs.getString("path"));
				appendix.setOwner(rs.getString("owner"));
				homework = new Homework();
				homework.setAppendix(appendix);
				homework.setCaseInfo(caseInfo);
				homework.setCreateDate(rs.getDate("create_date"));
				homework.setHomeworkName(rs.getString("homework_name"));
				homework.setId(rs.getString("id"));
				homework.setRemark(rs.getString("remark"));
				homework.setUser(userInfo);
				homeworkList.add(homework);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return homeworkList;
	}

	@Override
	public List<Homework> list(int pageSize, int pageNo, String queryString) throws Exception {
		List<Homework> homeworkList = null;
		Homework homework = null;
		Case caseInfo = null;
		User userInfo = null;
		Role role = null;
		Department department = null;
		Appendix appendix = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from homework_view " + queryString + " limit ?,? ";
			System.out.println(sql);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, pageSize * (pageNo - 1));
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			homeworkList = new ArrayList<Homework>();
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
				caseInfo.setId(rs.getString("case_id"));
				appendix = new Appendix();
				appendix.setId(rs.getString("appendix_id"));
				appendix.setAppendixName(rs.getString("appendix_name"));
				appendix.setPath(rs.getString("path"));
				appendix.setOwner(rs.getString("owner"));
				homework = new Homework();
				homework.setAppendix(appendix);
				homework.setCaseInfo(caseInfo);
				homework.setCreateDate(rs.getDate("create_date"));
				homework.setHomeworkName(rs.getString("homework_name"));
				homework.setId(rs.getString("id"));
				homework.setRemark(rs.getString("remark"));
				homework.setUser(userInfo);
				homeworkList.add(homework);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return homeworkList;
	}

	@Override
	public int getCount(String queryString) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(*) from homework_view ";
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

	@Override
	public boolean delete(String caseId) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from homework where case_id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, caseId);
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

}
