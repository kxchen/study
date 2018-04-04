package com.luna.dto;

import java.util.Date;

/**
 * @ClassName: UserInfo
 * @Description:用户类
 * @author c-kx@outlook.com
 * @date 2015年11月24日 上午10:00:24
 * 
 */
public class UserInfo {

	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private int userId;
	/**
	 * @Fields loginName : 登陆名
	 */
	private String loginName;
	/**
	 * @Fields realName : 真实姓名
	 */
	private String realName;
	/**
	 * @Fields password : 密码
	 */
	private String password;
	/**
	 * @Fields phone : 电话
	 */
	private String phone;
	/**
	 * @Fields address : 地址
	 */
	private String address;
	/**
	 * @Fields email : 电子邮件
	 */
	private String email;
	/**
	 * @Fields regtime : 注册时间
	 */
	private Date regtime;

	/**
	 * @Fields state : 是否激活
	 */
	private int state;
	/**
	 * @Fields code : 激活码
	 */
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((realName == null) ? 0 : realName.hashCode());
		result = prime * result + ((regtime == null) ? 0 : regtime.hashCode());
		result = prime * result + userId;
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
		UserInfo other = (UserInfo) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (realName == null) {
			if (other.realName != null)
				return false;
		} else if (!realName.equals(other.realName))
			return false;
		if (regtime == null) {
			if (other.regtime != null)
				return false;
		} else if (!regtime.equals(other.regtime))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", loginName=" + loginName + ", realName=" + realName + ", password="
				+ password + ", phone=" + phone + ", address=" + address + ", email=" + email + ", regtime=" + regtime
				+ "]";
	}

}
