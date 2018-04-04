package com.czesou.pojo;

/**
 * @ClassName: Authority
 * @Description: 权限类
 * @author: 陈凯旋
 * @date: 2017年2月23日 下午4:28:13
 */
public class Authority {
	private int id;
	/**
	 * @fieldName: authorityName
	 * @fieldType: String
	 * @Description: 权限名称
	 */
	private String authorityName;
	/**
	 * @fieldName: parentId
	 * @fieldType: String
	 * @Description: 父id
	 */
	private int parentId;
	/**
	 * @fieldName: url
	 * @fieldType: String
	 * @Description: 权限控制的访问路径
	 */
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "Authority [id=" + id + ", authorityName=" + authorityName + ", parentId=" + parentId + ", url=" + url
				+ "]";
	}

}
