package com.czesou.dao;

import java.sql.Connection;
import java.util.List;

import com.czesou.pojo.Homework;

public interface IHomeworkDAO {
	/**
	 * @Title: setConn
	 * @Description: 获取数据库链接
	 * @param conn
	 * @return: void
	 */
	public void setConn(Connection conn);

	public boolean save(Homework homework) throws Exception;

	public boolean delete(Homework homework) throws Exception;

	/**
	 * @Title: delete
	 * @Description: 根据案例id删除作业
	 * @return: boolean
	 * @param caseId
	 * @return
	 * @throws Exception
	 */
	public boolean delete(String caseId) throws Exception;

	public boolean update(Homework homework) throws Exception;

	public Homework get(String homeworkId) throws Exception;

	public List<Homework> list() throws Exception;

	public List<Homework> list(int pageSize, int pageNo, String queryString) throws Exception;

	public int getCount(String queryString) throws Exception;

}
