package com.luna.dto;

/**
 * @ClassName: AdminInfo
 * @Description:管理员类
 * @author c-kx@outlook.com
 * @date 2015年11月24日 上午10:02:56
 * 
 */
public class AdminInfo {
	/**
	 * @Fields adminId :主键
	 */
	private int adminId;
	/**
	 * @Fields adminName : 管理员的登录名
	 */
	private String adminName;
	/**
	 * @Fields password : 管理员的登录密码
	 */
	private String password;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adminId;
		result = prime * result + ((adminName == null) ? 0 : adminName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminInfo other = (AdminInfo) obj;
		if (adminId != other.adminId)
			return false;
		if (adminName == null) {
			if (other.adminName != null)
				return false;
		} else if (!adminName.equals(other.adminName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AdminInfo [adminId=" + adminId + ", adminName=" + adminName + ", password=" + password + "]";
	}

}
