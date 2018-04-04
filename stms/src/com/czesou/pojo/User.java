package com.czesou.pojo;

import java.util.Date;

/**
 * @ClassName: User
 * @Description: 用户类
 * @author: 陈凯旋
 * @date: 2017年2月23日 下午4:36:12
 */
public class User {
	private String id;
	/**
	 * @fieldName: name
	 * @fieldType: String
	 * @Description: 用户名
	 */
	private String userName;
	/**
	 * @fieldName: password
	 * @fieldType: String
	 * @Description: 登陆密码
	 */
	private String password;
	/**
	 * @fieldName: phone
	 * @fieldType: String
	 * @Description:电话号码（用于登陆）
	 */
	private String phone;
	/**
	 * @fieldName: role
	 * @fieldType: Role
	 * @Description: 用户的角色
	 */
	private Role role;
	/**
	 * @fieldName: department
	 * @fieldType: Department
	 * @Description: 用户的系部
	 */
	private Department department;
	/**
	 * @fieldName: createDate
	 * @fieldType: Date
	 * @Description: 创建时间
	 */
	private Date createDate;

	/**
	 * @fieldName: state
	 * @fieldType: int
	 * @Description: 用户状态，0正常，1禁用。
	 */
	private int state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", phone=" + phone + ", role="
				+ role + ", department=" + department + ", createDate=" + createDate + ", state=" + state + "]";
	}
	
}
