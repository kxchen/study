package com.luna.dao;

import java.sql.Connection;
import java.util.List;

import com.luna.dto.UserInfo;

/**
 * @ClassName: IUserInfoDAO
 * @Description: 用户业务逻辑接口
 * @author c-kx@outlook.com
 * @date 2015年11月24日 下午3:16:09
 * 
 */
public interface IUserInfoDAO {
	public int getCount(String queryString) throws Exception;

	public void setConn(Connection conn);

	public boolean save(UserInfo userInfo) throws Exception;

	public boolean delete(UserInfo userInfo) throws Exception;

	public boolean update(UserInfo userInfo) throws Exception;

	public UserInfo get(int userId) throws Exception;
	
	public UserInfo active(String code) throws Exception;

	public List<UserInfo> list(int pageSize, int pageNo,String queryString) throws Exception;

	public UserInfo login(String loginName, String password) throws Exception;
}
