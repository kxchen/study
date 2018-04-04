package com.luna.dao;

import java.sql.Connection;

import com.luna.dto.AdminInfo;

/**
 * @ClassName: IAdminDAO
 * @Description: 管理员业务逻辑接口
 * @author c-kx@outlook.com
 * @date 2015年12月25日 下午2:41:20
 * 
 */
public interface IAdminDAO {
	/**
	 * @Description: 管理员登录
	 * @param adminName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public AdminInfo login(String adminName, String password) throws Exception;

	public void setConn(Connection connection);

}
