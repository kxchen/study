package com.czesou.dao;

import java.sql.Connection;
import java.util.List;

import com.czesou.pojo.Case;

public interface ICaseDAO {
	/**
	 * @Title: setConn
	 * @Description: 获取数据库链接
	 * @param conn
	 * @return: void
	 */
	public void setConn(Connection conn);

	/**
	 * @Title: save
	 * @Description: 添加案例
	 * @return: boolean
	 * @param caseInfo
	 * @return
	 * @throws Exception
	 */
	public boolean save(Case caseInfo) throws Exception;

	/**
	 * @Title: delete
	 * @Description: 删除案例
	 * @return: boolean
	 * @param caseInfo
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Case caseInfo) throws Exception;

	/**
	 * @Title: update
	 * @Description: 更新案例
	 * @return: boolean
	 * @param caseInfo
	 * @return
	 * @throws Exception
	 */
	public boolean update(Case caseInfo) throws Exception;

	/**
	 * @Title: get
	 * @Description: 获取案例
	 * @return: Case
	 * @param caseId
	 * @return
	 * @throws Exception
	 */
	public Case get(String caseId) throws Exception;

	/**
	 * @Title: list
	 * @Description: 获取所有案例
	 * @return: List<Case>
	 * @return
	 * @throws Exception
	 */
	public List<Case> list() throws Exception;

	/**
	 * @Title: list
	 * @Description: 根据条件获取用户列表
	 * @return: List<Case>
	 * @param pageSize
	 * @param pageNo
	 * @param queryString
	 * @return
	 * @throws Exception
	 */
	public List<Case> list(int pageSize, int pageNo, String queryString) throws Exception;

	public int getCount(String queryString) throws Exception;
}
