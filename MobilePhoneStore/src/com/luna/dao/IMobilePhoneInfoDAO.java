package com.luna.dao;

import java.sql.Connection;
import java.util.List;

import com.luna.dto.MobilePhoneInfo;

public interface IMobilePhoneInfoDAO {
	public void setConn(Connection conn);

	public boolean save(MobilePhoneInfo mobilePhoneInfo) throws Exception;

	public boolean delete(MobilePhoneInfo mobilePhoneInfo) throws Exception;

	public boolean update(MobilePhoneInfo mobilePhoneInfo) throws Exception;

	public MobilePhoneInfo get(int mobPhoneId) throws Exception;

	public List<MobilePhoneInfo> list(int pageSize, int pageNo, String queryString) throws Exception;

	public int getCount(String queryString) throws Exception;

	public List<MobilePhoneInfo> hotSale() throws Exception;
}
