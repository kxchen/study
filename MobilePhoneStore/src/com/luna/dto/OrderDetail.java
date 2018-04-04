package com.luna.dto;


/**
 * @ClassName: OrderDetail
 * @Description:订单详情类
 * @author c-kx@outlook.com
 * @date 2015年11月24日 上午10:01:45
 * 
 */
public class OrderDetail {

	private int orderDetailId;

	/**
	 * @Fields mobilePhone : 手机信息
	 */
	private MobilePhoneInfo mobilePhone;
	/**
	 * @Fields order : 订单信息
	 */
	private OrderInfo order;
	/**
	 * @Fields realPrice : 购买时的价格
	 */
	private double buyPrice;
	/**
	 * @Fields amount : 购买数量
	 */
	private double amount;

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public MobilePhoneInfo getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(MobilePhoneInfo mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public OrderInfo getOrder() {
		return order;
	}

	public void setOrder(OrderInfo order) {
		this.order = order;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(buyPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((mobilePhone == null) ? 0 : mobilePhone.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + orderDetailId;
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
		OrderDetail other = (OrderDetail) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (Double.doubleToLongBits(buyPrice) != Double.doubleToLongBits(other.buyPrice))
			return false;
		if (mobilePhone == null) {
			if (other.mobilePhone != null)
				return false;
		} else if (!mobilePhone.equals(other.mobilePhone))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (orderDetailId != other.orderDetailId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailId=" + orderDetailId + ", mobilePhone=" + mobilePhone + ", order=" + order
				+ ", buyPrice=" + buyPrice + ", amount=" + amount + "]";
	}

}
