package com.czesou.pojo;

import java.util.Date;

public class Homework {
	private String id;
	/**
	 * @fieldName: homeworkName
	 * @fieldType: String
	 * @Description: 作业名称
	 */
	private String homeworkName;
	/**
	 * @fieldName: remark
	 * @fieldType: String
	 * @Description: 作业备注
	 */
	private String remark;
	/**
	 * @fieldName: user
	 * @fieldType: User
	 * @Description: 上传案例的用户
	 */
	private User user;
	/**
	 * @fieldName: appendix
	 * @fieldType: Appendix
	 * @Description: 案例中包含的附件
	 */
	private Appendix appendix;
	/**
	 * @fieldName: createDate
	 * @fieldType: Date
	 * @Description: 创建时间
	 */
	private Case caseInfo;
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

	public String getHomeworkName() {
		return homeworkName;
	}

	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
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

	public Appendix getAppendix() {
		return appendix;
	}

	public void setAppendix(Appendix appendix) {
		this.appendix = appendix;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Case getCaseInfo() {
		return caseInfo;
	}

	public void setCaseInfo(Case caseInfo) {
		this.caseInfo = caseInfo;
	}

	@Override
	public String toString() {
		return "Homework [id=" + id + ", homeworkName=" + homeworkName + ", remark=" + remark + ", user=" + user
				+ ", appendix=" + appendix + ", caseInfo=" + caseInfo + ", createDate=" + createDate + "]";
	}

}
