package com.czesou.pojo;

import java.util.Date;

/**
 * @ClassName: Case
 * @Description: 案例类
 * @author: 陈凯旋
 * @date: 2017年2月23日 下午4:30:30
 */
public class Case {
	private String id;
	/**
	 * @fieldName: name
	 * @fieldType: String
	 * @Description: 案例名称
	 */
	private String caseName;
	/**
	 * @fieldName: remark
	 * @fieldType: String
	 * @Description: 案例备注
	 */
	private String remark;
	/**
	 * @fieldName: user
	 * @fieldType: User
	 * @Description: 上传案例的用户
	 */
	private User user;
	/**
	 * @fieldName: createDate
	 * @fieldType: Date
	 * @Description: 创建时间
	 */
	private Date createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Case [id=" + id + ", caseName=" + caseName + ", remark=" + remark + ", user=" + user + ", createDate="
				+ createDate + "]";
	}

}
