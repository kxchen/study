package com.czesou.dao;

import java.sql.Connection;
import java.util.List;

import com.czesou.pojo.Role;


public interface IRoleDAO {
	/**
	 * @Title: setConn
	 * @Description: 获取数据库链接
	 * @param conn
	 * @return: void
	 */
	public void setConn(Connection conn);

	public boolean save(Role role) throws Exception;

	public boolean delete(Role role) throws Exception;
	public Role get(String id) throws Exception ;
	public boolean update(Role role) throws Exception;

	/**
	 * @Title: get
	 * @Description: 根据id获取角色
	 * @return: Role
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	public Role get(int i) throws Exception;

	/**
	 * @Title: list
	 * @Description: 角色列表
	 * @return: List<Role>
	 * @return
	 * @throws Exception
	 */
	public List<Role> list() throws Exception;

}
