package com.luna.dto;

import java.util.Date;

/**
 * @ClassName: OrderDetail
 * @Description: 订单详情实体类
 * @author c-kx@outlook.com
 * @date 2015年12月24日 下午8:30:41
 * 
 */

public class OrderDetail {
	/**
	 * @Fields orderDetailId : 订单详情Id
	 */
	private String orderDetailId;
	/**
	 * @Fields orderInfo : 订单信息
	 */
	private OrderInfo orderInfo;
	/**
	 * @Fields reaPrice : 购买时价格
	 */
	private double reaPrice;
	/**
	 * @Fields quantity : 数量
	 */
	private int quantity;
	/**
	 * @Fields carInfo : 车辆和信息
	 */
	private CarInfo carInfo;

	/**
	 * @Fields 提车时间
	 */
	private Date takeTime;
	/**
	 * @Fields 实际还车时间
	 */
	private Date reaRetTime;
	/**
	 * @Fields 还车时间
	 */
	private Date retTime;

	/**
	 * @Fields abolish : 是否取消
	 */
	private int isAbolish;

	/**
	 * @Fields 是否发车
	 */
	private int isSend;
	/**
	 * @Fields 是否收车
	 */
	private int isReceive;
	/**
	 * @Fields .是否还车
	 */
	private int isReturn;

	/**
	 * @Fields paid : 已付金额
	 */
	private double paid;

	public String getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public double getReaPrice() {
		return reaPrice;
	}

	public void setReaPrice(double reaPrice) {
		this.reaPrice = reaPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public Date getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(Date takeTime) {
		this.takeTime = takeTime;
	}

	public Date getReaRetTime() {
		return reaRetTime;
	}

	public void setReaRetTime(Date reaRetTime) {
		this.reaRetTime = reaRetTime;
	}

	public Date getRetTime() {
		return retTime;
	}

	public void setRetTime(Date retTime) {
		this.retTime = retTime;
	}

	public int getIsAbolish() {
		return isAbolish;
	}

	public void setIsAbolish(int isAbolish) {
		this.isAbolish = isAbolish;
	}

	public int getIsSend() {
		return isSend;
	}

	public void setIsSend(int isSend) {
		this.isSend = isSend;
	}

	public int getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(int isReceive) {
		this.isReceive = isReceive;
	}

	public int getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(int isReturn) {
		this.isReturn = isReturn;
	}

	public double getPaid() {
		return paid;
	}

	public void setPaid(double paid) {
		this.paid = paid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carInfo == null) ? 0 : carInfo.hashCode());
		result = prime * result + isAbolish;
		result = prime * result + isReceive;
		result = prime * result + isReturn;
		result = prime * result + isSend;
		result = prime * result + ((orderDetailId == null) ? 0 : orderDetailId.hashCode());
		result = prime * result + ((orderInfo == null) ? 0 : orderInfo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(paid);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		temp = Double.doubleToLongBits(reaPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((reaRetTime == null) ? 0 : reaRetTime.hashCode());
		result = prime * result + ((retTime == null) ? 0 : retTime.hashCode());
		result = prime * result + ((takeTime == null) ? 0 : takeTime.hashCode());
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
		if (carInfo == null) {
			if (other.carInfo != null)
				return false;
		} else if (!carInfo.equals(other.carInfo))
			return false;
		if (isAbolish != other.isAbolish)
			return false;
		if (isReceive != other.isReceive)
			return false;
		if (isReturn != other.isReturn)
			return false;
		if (isSend != other.isSend)
			return false;
		if (orderDetailId == null) {
			if (other.orderDetailId != null)
				return false;
		} else if (!orderDetailId.equals(other.orderDetailId))
			return false;
		if (orderInfo == null) {
			if (other.orderInfo != null)
				return false;
		} else if (!orderInfo.equals(other.orderInfo))
			return false;
		if (Double.doubleToLongBits(paid) != Double.doubleToLongBits(other.paid))
			return false;
		if (quantity != other.quantity)
			return false;
		if (Double.doubleToLongBits(reaPrice) != Double.doubleToLongBits(other.reaPrice))
			return false;
		if (reaRetTime == null) {
			if (other.reaRetTime != null)
				return false;
		} else if (!reaRetTime.equals(other.reaRetTime))
			return false;
		if (retTime == null) {
			if (other.retTime != null)
				return false;
		} else if (!retTime.equals(other.retTime))
			return false;
		if (takeTime == null) {
			if (other.takeTime != null)
				return false;
		} else if (!takeTime.equals(other.takeTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailId=" + orderDetailId + ", orderInfo=" + orderInfo + ", reaPrice=" + reaPrice
				+ ", quantity=" + quantity + ", carInfo=" + carInfo + ", takeTime=" + takeTime + ", reaRetTime="
				+ reaRetTime + ", retTime=" + retTime + ", isAbolish=" + isAbolish + ", isSend=" + isSend
				+ ", isReceive=" + isReceive + ", isReturn=" + isReturn + ", paid=" + paid + "]";
	}

}
