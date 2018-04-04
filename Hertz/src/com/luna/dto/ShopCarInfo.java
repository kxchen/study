package com.luna.dto;

import java.util.Date;

/**
 * @ClassName: ShopCarInfo
 * @Description: 购物车实体类
 * @author c-kx@outlook.com
 * @date 2015年12月24日 下午9:00:39
 * 
 */
public class ShopCarInfo {
	/**
	 * @Fields shopCarId : 购物车Id
	 */
	private String shopCarId;
	/**
	 * @Fields carInfo : 车辆信息
	 */
	private CarInfo carInfo;
	/**
	 * @Fields amount : 数量
	 */
	private int amount;
	/**
	 * @Fields userInfo : 用户信息
	 */
	private UserInfo userInfo;
	/**
	 * @Fields 提车时间
	 */
	private Date takeTime;
	/**
	 * @Fields 还车时间
	 */
	private Date retTime;

	public Date getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(Date takeTime) {
		this.takeTime = takeTime;
	}

	public Date getRetTime() {
		return retTime;
	}

	public void setRetTime(Date retTime) {
		this.retTime = retTime;
	}

	public String getShopCarId() {
		return shopCarId;
	}

	public void setShopCarId(String shopCarId) {
		this.shopCarId = shopCarId;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((carInfo == null) ? 0 : carInfo.hashCode());
		result = prime * result + ((shopCarId == null) ? 0 : shopCarId.hashCode());
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
		ShopCarInfo other = (ShopCarInfo) obj;
		if (amount != other.amount)
			return false;
		if (carInfo == null) {
			if (other.carInfo != null)
				return false;
		} else if (!carInfo.equals(other.carInfo))
			return false;
		if (shopCarId == null) {
			if (other.shopCarId != null)
				return false;
		} else if (!shopCarId.equals(other.shopCarId))
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
		return "ShopCarInfo [shopCarId=" + shopCarId + ", carInfo=" + carInfo + ", amount=" + amount + ", userInfo="
				+ userInfo + "]";
	}

}
