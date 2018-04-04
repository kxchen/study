package com.czesou.pojo;

import java.util.Date;

/**
 * @ClassName: Appendix
 * @Description: 上传附件信息
 * @author: 陈凯旋
 * @date: 2017年2月23日 下午4:24:45
 */
public class Appendix {
	/**
	 * @fieldName: id
	 * @fieldType: String
	 * @Description: 唯一标示，使用UUID
	 */
	private String id;
	/**
	 * @fieldName: appendixName
	 * @fieldType: String
	 * @Description: 上传文件名
	 */
	private String appendixName;
	/**
	 * @fieldName: path
	 * @fieldType: String
	 * @Description: ；上传路径
	 */
	private String path;
	/**
	 * @fieldName: createDate
	 * @fieldType: Date
	 * @Description: 创建时间
	 */
	private Date createDate;
	/**
	 * @fieldName: owner
	 * @fieldType: String
	 * @Description: 属于哪个案例或者案例，记录案例或者作业的id
	 */
	private String owner;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppendixName() {
		return appendixName;
	}

	public void setAppendixName(String appendixName) {
		this.appendixName = appendixName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Appendix [id=" + id + ", appendixName=" + appendixName + ", path=" + path + ", createDate=" + createDate
				+ ", owner=" + owner + "]";
	}

}
