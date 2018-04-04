package com.czesou.dao;

import java.sql.Connection;
import java.util.List;

import com.czesou.pojo.Authority;


public interface IAuthorityDAO {
	/**
	 * @Title: setConn
	 * @Description: 获取数据库链接
	 * @param conn
	 * @return: void
	 */
	public void setConn(Connection conn);

	/**
	 * @Title: get
	 * @Description: 根据id获取权限
	 * @return: Authority
	 * @param authorityId
	 * @return
	 * @throws Exception
	 */
	public Authority get(int authorityId) throws Exception;

	/**
	 * @Title: list
	 * @Description: 权限列表
	 * @return: List<Authority>
	 * @return
	 * @throws Exception
	 */
	public List<Authority> list() throws Exception;

	public List<Authority> list(String string) throws Exception;

	public String listAuthorityUrl(String string) throws Exception;
}
