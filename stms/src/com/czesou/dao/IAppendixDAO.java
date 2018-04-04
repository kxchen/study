package com.czesou.dao;

import java.sql.Connection;
import java.util.List;

import com.czesou.pojo.Appendix;


public interface IAppendixDAO {
	/**
	 * @Title: setConn
	 * @Description: 获取数据库链接
	 * @param conn
	 * @return: void
	 */
	public void setConn(Connection conn);

	/**
	 * @Title: save
	 * @Description: 添加附件
	 * @return: boolean
	 * @param appendix
	 * @return
	 * @throws Exception
	 */
	public boolean save(Appendix appendix) throws Exception;

	/**
	 * @Title: delete
	 * @Description: 删除
	 * @return: boolean
	 * @param appendix
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Appendix appendix) throws Exception;

	/**
	 * @Title: update
	 * @Description: 修改附件
	 * @return: boolean
	 * @param appendix
	 * @return
	 * @throws Exception
	 */
	public boolean update(Appendix appendix) throws Exception;

	/**
	 * @Title: get
	 * @Description: 查找附件
	 * @return: Appendix
	 * @param appendixId
	 * @return
	 * @throws Exception
	 */
	public Appendix get(String appendixId) throws Exception;

	/**
	 * @Title: list
	 * @Description: 附件列表
	 * @return: List<Appendix>
	 * @return
	 * @throws Exception
	 */
	public List<Appendix> list() throws Exception;

	/**
	 * @Title: getCount
	 * @Description: 统计附件个数
	 * @return: int
	 * @return
	 * @throws Exception
	 */
	public int getCount() throws Exception;

	/**
	 * @Title: listByOwner
	 * @Description: 根据所有者获取
	 * @return: List<Appendix>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Appendix> listByOwner(String id) throws Exception;
}
