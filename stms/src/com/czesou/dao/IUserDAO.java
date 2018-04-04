package com.czesou.dao;

import java.sql.Connection;
import java.util.List;

import com.czesou.pojo.User;


public interface IUserDAO {
	/**
	 * @Title: setConn
	 * @Description: 获取数据库链接
	 * @param conn
	 * @return: void
	 */
	public void setConn(Connection conn);

	/**
	 * @Title: save
	 * @Description: 添加
	 * @return: boolean
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean save(User user) throws Exception;

	/**
	 * @Title: delete
	 * @Description: 删除
	 * @return: boolean
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean delete(User user) throws Exception;

	/**
	 * @Title: update
	 * @Description: 更新
	 * @return: boolean
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean update(User user) throws Exception;

	/**
	 * @Title: login
	 * @Description: 登陆
	 * @return: User
	 * @param phone
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public User login(String phone, String password) throws Exception;

	/**
	 * @Title: get
	 * @Description: 根据id获取用户
	 * @return: User
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User get(String id) throws Exception;

	/**
	 * @Title: list
	 * @Description: 所有用户列表
	 * @return: List<User>
	 * @return
	 * @throws Exception
	 */
	public List<User> list() throws Exception;

	/**
	 * @Title: list
	 * @Description: 根据查询条件获取用户列表
	 * @return: List<User>
	 * @param pageSize
	 * @param pageNo
	 * @param queryString
	 * @return
	 * @throws Exception
	 */
	public List<User> list(int pageSize, int pageNo, String queryString) throws Exception;
	
	public int getCount(String queryString) throws Exception;
}
