package com.czesou.dao;

import java.sql.Connection;
import java.util.List;

import com.czesou.pojo.Department;

public interface IDepartmentDAO {
	/**
	 * @Title: setConn
	 * @Description: 获取数据库链接
	 * @param conn
	 * @return: void
	 */
	public void setConn(Connection conn);

	/**
	 * @Title: save
	 * @Description: 添加附件
	 * @return: boolean
	 * @param appendix
	 * @return
	 * @throws Exception
	 */
	public boolean save(Department department) throws Exception;

	/**
	 * @Title: delete
	 * @Description: 删除系部
	 * @return: boolean
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Department department) throws Exception;

	/**
	 * @Title: update
	 * @Description: 更新
	 * @return: boolean
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public boolean update(Department department) throws Exception;

	/**
	 * @Title: get
	 * @Description: 根据id获取系部
	 * @return: Department
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Department get(int id) throws Exception;

	/**
	 * @Title: get
	 * @Description: TODO
	 * @return: Department
	 * @param string
	 * @return
	 * @throws Exception
	 */
	/**
	 * @Title: get
	 * @Description: 根据部门名称得到部门
	 * @return: Department
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public Department get(String string) throws Exception;

	/**
	 * @Title: list
	 * @Description: 系部列表
	 * @return: List<Department>
	 * @return
	 * @throws Exception
	 */
	public List<Department> list() throws Exception;
}
