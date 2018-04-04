package com.luna.dto;

import java.util.List;

public class PageBean<T> {
	/**
	 * @Fields pageCode : 当前页码
	 */
	private int pageCode;
	// private int tp;// 总页数total page
	/**
	 * @Fields totalRecord : 总记录数
	 */
	private int totalRecord;//
	/**
	 * @Fields pageSize : 每页记录数
	 */
	private int pageSize;//
	/**
	 * @Fields beanList : 当前页的记录
	 */
	private List<T> beanList;

	/**
	 * @Fields url : 它就是url后的条件！
	 */
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageCode() {
		return pageCode;
	}

	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

	/**
	 * 通过总记录数和每页记录数来计算总页数
	 * 
	 * @return 总页数
	 */
	public int getTotalPage() {
		// 通过总记录数和每页记录数来计算总页数
		int totalPage = totalRecord / pageSize;
		return totalRecord % pageSize == 0 ? totalPage : totalPage + 1;
	}
}
