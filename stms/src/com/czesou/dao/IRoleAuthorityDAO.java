package com.czesou.dao;

import java.sql.Connection;
import java.util.List;

import com.czesou.pojo.Authority;
import com.czesou.pojo.Role;
import com.czesou.pojo.RoleAuthority;


public interface IRoleAuthorityDAO {
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
	 * @param roleAuthority
	 * @return
	 * @throws Exception
	 */
	public boolean save(RoleAuthority roleAuthority) throws Exception;

	/**
	 * @Title: delete
	 * @Description: 删除
	 * @return: boolean
	 * @param roleAuthority
	 * @return
	 * @throws Exception
	 */
	public boolean delete(RoleAuthority roleAuthority) throws Exception;

	public boolean delete(int roleId) throws Exception;

	/**
	 * @Title: update
	 * @Description: 更新
	 * @return: boolean
	 * @param roleAuthority
	 * @return
	 * @throws Exception
	 */
	public boolean update(RoleAuthority roleAuthority) throws Exception;

	/**
	 * @Title: get
	 * @Description: 根据id获取
	 * @return: RoleAuthority
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public RoleAuthority get(String id) throws Exception;

	/**
	 * @Title: list
	 * @Description:角色权限列表
	 * @return: List<RoleAuthority>
	 * @return
	 * @throws Exception
	 */
	public List<RoleAuthority> list() throws Exception;

	/**
	 * @Title: list
	 * @Description: 当前角色的角色权限列表
	 * @return: List<RoleAuthority>
	 * @param Role
	 * @return
	 * @throws Exception
	 */
	public List<RoleAuthority> list(Role Role) throws Exception;

	/**
	 * @Title: authorityList
	 * @Description: 当前角色的权限列表
	 * @return: List<Authority>
	 * @param Role
	 * @return
	 * @throws Exception
	 */
	public List<Authority> authorityList(Role Role) throws Exception;

	/**
	 * @Title: list
	 * @Description: 拥有当前权限的角色
	 * @return: List<RoleAuthority>
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	public List<RoleAuthority> list(Authority authority) throws Exception;
	
	public List<Integer> list(int roleId) throws Exception;
	public List<String> listAuthorityUrl(int roleId) throws Exception;

}
