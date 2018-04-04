package com.luna.dao;

import java.sql.Connection;
import java.util.List;

import com.luna.dto.AdminInfo;

/** 
* @ClassName: IAdminInfoDAO 
* @Description: 管理员接口
* @author c-kx@outlook.com 
* @date 2015年12月17日 上午11:31:04 
*  
*/
public interface IAdminInfoDAO {
	/** 
	 * @Description: 建立Connection连接    
	 * @param conn           
	 */  
	public void setConn(Connection conn);

	public boolean save(AdminInfo adminInfo) throws Exception;

	public boolean delete(AdminInfo adminInfo) throws Exception;

	public boolean update(AdminInfo adminInfo) throws Exception;

	public AdminInfo get(int adminId) throws Exception;

	public List<AdminInfo> list() throws Exception;

	public AdminInfo login(String adminName, String password) throws Exception;
}
