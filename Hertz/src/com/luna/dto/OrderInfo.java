package com.luna.dto;

import java.util.Date;

public class OrderInfo {
	/**
	 * @Fields id
	 */
	private String orderId;
	/**
	 * @Fields user
	 */
	private UserInfo userInfo;

	/**
	 * @Fields 下单时间
	 */
	private Date buyTime;

	/**
	 * @Fields 应付款
	 */
	private double pay;
	/**
	 * @Fields 实付款
	 */
	private double reaPay;
	/**
	 * @Fields conPhone : 联系电话
	 */
	private String conPhone;

	/**
	 * @Fields conAddress : 联系地址
	 */
	private String conAddress;

	/**
	 * @Fields contacts : 联系人
	 */
	private String contacts;

	/** 
	* @Fields isPay : 是否付款 
	*/ 
	private int isPay;

	public int getIsPay() {
		return isPay;
	}

	public void setIsPay(int ispay) {
		this.isPay = ispay;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	public double getReaPay() {
		return reaPay;
	}

	public void setReaPay(double reaPay) {
		this.reaPay = reaPay;
	}

	public String getConPhone() {
		return conPhone;
	}

	public void setConPhone(String conPhone) {
		this.conPhone = conPhone;
	}

	public String getConAddress() {
		return conAddress;
	}

	public void setConAddress(String conAddress) {
		this.conAddress = conAddress;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyTime == null) ? 0 : buyTime.hashCode());
		result = prime * result + ((conAddress == null) ? 0 : conAddress.hashCode());
		result = prime * result + ((conPhone == null) ? 0 : conPhone.hashCode());
		result = prime * result + ((contacts == null) ? 0 : contacts.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(pay);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(reaPay);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((userInfo == null) ? 0 : userInfo.hashCode());
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
		OrderInfo other = (OrderInfo) obj;
		if (buyTime == null) {
			if (other.buyTime != null)
				return false;
		} else if (!buyTime.equals(other.buyTime))
			return false;
		if (conAddress == null) {
			if (other.conAddress != null)
				return false;
		} else if (!conAddress.equals(other.conAddress))
			return false;
		if (conPhone == null) {
			if (other.conPhone != null)
				return false;
		} else if (!conPhone.equals(other.conPhone))
			return false;
		if (contacts == null) {
			if (other.contacts != null)
				return false;
		} else if (!contacts.equals(other.contacts))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (Double.doubleToLongBits(pay) != Double.doubleToLongBits(other.pay))
			return false;
		if (Double.doubleToLongBits(reaPay) != Double.doubleToLongBits(other.reaPay))
			return false;
		if (userInfo == null) {
			if (other.userInfo != null)
				return false;
		} else if (!userInfo.equals(other.userInfo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderInfo [orderId=" + orderId + ", userInfo=" + userInfo + ", buyTime=" + buyTime + ", pay=" + pay
				+ ", reaPay=" + reaPay + ", conPhone=" + conPhone + ", conAddress=" + conAddress + ", contacts="
				+ contacts + "]";
	}

}
