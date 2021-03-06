package com.luna.dao;

import java.sql.Connection;
import java.util.List;

import com.luna.dto.UserInfo;

public interface IUserDAO {

	public void setConn(Connection conn);

	public boolean save(UserInfo userInfo) throws Exception;

	public boolean delete(UserInfo userInfo) throws Exception;

	public boolean update(UserInfo userInfo) throws Exception;

	public UserInfo get(String userId) throws Exception;

	public UserInfo getByPhone(String code) throws Exception;

	public List<UserInfo> list(int pageSize, int pageNo, String queryString) throws Exception;

	public UserInfo login(String loginName, String password) throws Exception;
	
	public int getCount(String queryString) throws Exception;
}
