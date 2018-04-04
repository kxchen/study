package com.school.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.school.database.DataBase;
import com.school.dto.StudentInfo;

public class StudentInfoService {
	// public StudentInfo login(StudentInfo studentInfoInstance) throws
	// Exception {
	// StudentInfo studentInfo = null;
	// Connection conn = null;
	// PreparedStatement pst = null;
	// ResultSet rs = null;
	// try {
	// String sql = "select * from student_info where account=? and password=?";
	// conn = DataBase.getConn();
	// pst = conn.prepareStatement(sql);
	// pst.setString(1, studentInfoInstance.getAccount());
	// pst.setString(2, studentInfoInstance.getPassword());
	// rs = pst.executeQuery();
	// DataBase.commit();
	// if (rs.next()) {
	// studentInfo = new StudentInfo();
	// studentInfo.setAccount(studentInfoInstance.getAccount());
	// studentInfo.setStudentName(rs.getString("student_name"));
	// studentInfo.setId(rs.getInt("id"));
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// DataBase.rollback();
	// throw e;
	// } finally {
	// rs.close();
	// pst.close();
	// DataBase.releaseConnection(conn);
	// }
	// return studentInfo;
	// }

	public boolean save(StudentInfo studentInfoInstance) throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		int state = 0;
		boolean flag = false;
		try {
			String sql = "insert into student_info(id,sex,student_name,student_num,major,classes,status)values (?,?,?,?,?,?,?)";
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, studentInfoInstance.getId());
			pst.setString(2, studentInfoInstance.getSex());
			pst.setString(3, studentInfoInstance.getStudentName());
			pst.setString(4, studentInfoInstance.getStudentNum());
			pst.setString(5, studentInfoInstance.getMajor());
			pst.setString(6, studentInfoInstance.getClasses());
			pst.setString(7, studentInfoInstance.getStatus());
			state = pst.executeUpdate();
			DataBase.commit();
			if (state > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			return false;
		} finally {
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return flag;
	}

	public List<StudentInfo> list() throws Exception {
		ArrayList<StudentInfo> studentList = null;
		StudentInfo studentInfo = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from student_info where status=?";
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, "show");
			rs = pst.executeQuery();
			DataBase.commit();
			studentList = new ArrayList<StudentInfo>();
			while (rs.next()) {
				studentInfo = new StudentInfo();
				studentInfo.setStudentName(rs.getString("student_name"));
				studentInfo.setId(rs.getString("id"));
				studentInfo.setSex(rs.getString("sex"));
				studentInfo.setStudentNum(rs.getString("student_num"));
				studentInfo.setMajor(rs.getString("major"));
				studentInfo.setClasses(rs.getString("classes"));
				studentList.add(studentInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			throw e;
		} finally {
			rs.close();
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return studentList;
	}

	public List<StudentInfo> recycleBinList() throws Exception {
		ArrayList<StudentInfo> studentList = null;
		StudentInfo studentInfo = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from student_info where status=?";
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, "hide");
			rs = pst.executeQuery();
			DataBase.commit();
			studentList = new ArrayList<StudentInfo>();
			while (rs.next()) {
				studentInfo = new StudentInfo();
				studentInfo.setStudentName(rs.getString("student_name"));
				studentInfo.setId(rs.getString("id"));
				studentInfo.setSex(rs.getString("sex"));
				studentInfo.setStudentNum(rs.getString("student_num"));
				studentInfo.setMajor(rs.getString("major"));
				studentInfo.setClasses(rs.getString("classes"));
				studentList.add(studentInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			throw e;
		} finally {
			rs.close();
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return studentList;
	}

	public boolean update(StudentInfo studentInfoInstance) throws SQLException {
		String sql = "update student_info set sex=?,student_name=?,student_num=?,major=?,classes=? where id=?";
		Connection conn = null;
		PreparedStatement pst = null;
		int state = 0;
		boolean flag = false;
		try {
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, studentInfoInstance.getSex());
			pst.setString(2, studentInfoInstance.getStudentName());
			pst.setString(3, studentInfoInstance.getStudentNum());
			pst.setString(4, studentInfoInstance.getMajor());
			pst.setString(5, studentInfoInstance.getClasses());
			pst.setString(6, studentInfoInstance.getId());
			state = pst.executeUpdate();
			DataBase.commit();
			if (state > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			return false;
		} finally {
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return flag;
	}

	public StudentInfo get(String id) throws Exception {
		StudentInfo studentInfo = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from student_info where id =?";
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			DataBase.commit();
			if (rs.next()) {
				studentInfo = new StudentInfo();
				studentInfo.setStudentName(rs.getString("student_name"));
				studentInfo.setId(rs.getString("id"));
				studentInfo.setSex(rs.getString("sex"));
				studentInfo.setStudentNum(rs.getString("student_num"));
				studentInfo.setMajor(rs.getString("major"));
				studentInfo.setClasses(rs.getString("classes"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			throw e;
		} finally {
			rs.close();
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return studentInfo;
	}

	public boolean status(StudentInfo studentInfoInstance) throws SQLException {
		String sql = "update student_info set status=? where id = ?";
		Connection conn = null;
		PreparedStatement pst = null;
		int state = 0;
		boolean flag = false;
		try {
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, studentInfoInstance.getStatus());
			pst.setString(2, studentInfoInstance.getId());
			state = pst.executeUpdate();
			DataBase.commit();
			if (state > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			return false;
		} finally {
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return flag;
	}

	public boolean delete(String id) throws SQLException {
		String sql = "delete from student_info where id=?";
		Connection conn = null;
		PreparedStatement pst = null;
		int state = 0;
		boolean flag = false;
		try {
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			state = pst.executeUpdate();
			DataBase.commit();
			if (state > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			return false;
		} finally {
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return flag;
	}

	public boolean deleteByStatus() throws SQLException {
		String sql = "delete from student_info where status=?";
		Connection conn = null;
		PreparedStatement pst = null;
		int state = 0;
		boolean flag = false;
		try {
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, "hide");
			state = pst.executeUpdate();
			DataBase.commit();
			if (state > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			return false;
		} finally {
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return flag;
	}

	public List<StudentInfo> findByName(String account) throws Exception {
		ArrayList<StudentInfo> studentList = null;
		StudentInfo studentInfo = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from student_info where student_name like ?";
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + account + "%");
			rs = pst.executeQuery();
			DataBase.commit();
			studentList = new ArrayList<StudentInfo>();
			while (rs.next()) {
				studentInfo = new StudentInfo();
				studentInfo.setStudentName(rs.getString("student_name"));
				studentInfo.setId(rs.getString("id"));
				studentInfo.setSex(rs.getString("sex"));
				studentInfo.setStudentNum(rs.getString("student_num"));
				studentInfo.setMajor(rs.getString("major"));
				studentInfo.setClasses(rs.getString("classes"));
				studentList.add(studentInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			throw e;
		} finally {
			rs.close();
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return studentList;

	}

	public StudentInfo groupSearch(String id, String studentNum) throws SQLException {
		StudentInfo studentInfo = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from student_info where id =? and student_num=?";
			conn = DataBase.getConn();
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, studentNum);
			rs = pst.executeQuery();
			DataBase.commit();
			if (rs.next()) {
				studentInfo = new StudentInfo();
				studentInfo.setStudentName(rs.getString("student_name"));
				studentInfo.setId(rs.getString("id"));
				studentInfo.setSex(rs.getString("sex"));
				studentInfo.setStudentNum(rs.getString("student_num"));
				studentInfo.setMajor(rs.getString("major"));
				studentInfo.setClasses(rs.getString("classes"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
			throw e;
		} finally {
			rs.close();
			pst.close();
			DataBase.releaseConnection(conn);
		}
		return studentInfo;
	}
}