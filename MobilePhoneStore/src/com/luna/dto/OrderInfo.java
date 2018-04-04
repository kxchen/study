package com.luna.dto;

import java.util.Date;

/**
 * @ClassName: OrderInfo
 * @Description:订单类
 * @author c-kx@outlook.com
 * @date 2015年11月24日 上午10:01:13
 * 
 */
public class OrderInfo {
	private long orderId;

	/**
	 * @Fields user : 用户信息
	 */
	private UserInfo user;

	/**
	 * @Fields submitTime : 下单时间
	 */
	private Date submitTime;

	/**
	 * @Fields totalPrice : 订单总价格
	 */
	private double totalPrice;

	/**
	 * 是否已支付
	 * 
	 * @Fields isPay : TODO
	 */
	private int isPay;

	/**
	 * @Fields isDeliver : 是否已发货
	 */
	private int isDeliver;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getIsPay() {
		return isPay;
	}

	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}

	public int getIsDeliver() {
		return isDeliver;
	}

	public void setIsDeliver(int isDeliver) {
		this.isDeliver = isDeliver;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + isDeliver;
		result = prime * result + isPay;
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + ((submitTime == null) ? 0 : submitTime.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (isDeliver != other.isDeliver)
			return false;
		if (isPay != other.isPay)
			return false;
		if (orderId != other.orderId)
			return false;
		if (submitTime == null) {
			if (other.submitTime != null)
				return false;
		} else if (!submitTime.equals(other.submitTime))
			return false;
		if (Double.doubleToLongBits(totalPrice) != Double.doubleToLongBits(other.totalPrice))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderInfo [orderId=" + orderId + ", user=" + user + ", submitTime=" + submitTime + ", totalPrice="
				+ totalPrice + ", isPay=" + isPay + ", isDeliver=" + isDeliver + "]";
	}

}
