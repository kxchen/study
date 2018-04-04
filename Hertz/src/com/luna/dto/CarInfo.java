package com.luna.dto;

import java.util.Date;

/**
 * @ClassName: CarInfo
 * @Description: 车的实体类
 * @author xiazhenghao
 * @date 2015年12月25日 上午10:17:08
 * 
 */
public class CarInfo {
	/**
	 * @Fields brand : 品牌
	 */
	private String brand;
	/**
	 * @Fields model : 车系
	 */
	private String model;
	/**
	 * @Fields type : 车型
	 */
	private String type;
	/**
	 * @Fields buyDate : 购买时间
	 */
	private Date buyDate;
	/**
	 * @Fields km : 公里数
	 */
	private String km;
	/**
	 * @Fields carefulImg : 年审凭证照片
	 */
	private String carefulImg;
	/**
	 * @Fields carImg :车辆照片
	 */
	private String carImg;
	/**
	 * @Fields gears : .排挡
	 */
	private String gears;
	/**
	 * @Fields cc : 排量
	 */
	private String cc;
	/**
	 * @Fields color : 颜色
	 */
	private String color;
	/**
	 * @Fields criteria : 租赁条件
	 */
	private String criteria;
	/**
	 * @Fields price : 出租价格
	 */
	private Double price;
	/**
	 * @Fields seats : 座位数
	 */
	private String seats;
	/**
	 * @Fields isCheck : 是否通过审核
	 */
	private int isCheck;
	/**
	 * @Fields isRent : 是否被租赁
	 */
	private int isRent;
	/**
	 * @Fields relTime : 发布时间
	 */
	private Date relTime;
	/**
	 * @Fields carId : id
	 */
	private String carId;
	/**
	 * @Fields ownerId : 车主
	 */
	private String ownerId;
	/**
	 * @Fields purpose : 车辆用途
	 */
	private String purpose;
	/**
	 * @Fields remarks : 备注
	 */
	private String remarks;

	/**
	 * @Fields paidPer : 已付款百分比
	 */
	private double paidPer;

	/**
	 * @Fields count : 数量
	 */
	private int count;
	/**
	 * @Fields comboName : 套餐名
	 */
	private String comboName;

	public String getBrand() {
		return brand;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public String getKm() {
		return km;
	}

	public void setKm(String km) {
		this.km = km;
	}

	public String getCarefulImg() {
		return carefulImg;
	}

	public void setCarefulImg(String carefulImg) {
		this.carefulImg = carefulImg;
	}

	public String getCarImg() {
		return carImg;
	}

	public void setCarImg(String carImg) {
		this.carImg = carImg;
	}

	public String getGears() {
		return gears;
	}

	public void setGears(String gears) {
		this.gears = gears;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public int getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}

	public int getIsRent() {
		return isRent;
	}

	public void setIsRent(int isRent) {
		this.isRent = isRent;
	}

	public Date getRelTime() {
		return relTime;
	}

	public void setRelTime(Date relTime) {
		this.relTime = relTime;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public double getPaidPer() {
		return paidPer;
	}

	public void setPaidPer(double paidPer) {
		this.paidPer = paidPer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seats == null) ? 0 : seats.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((buyDate == null) ? 0 : buyDate.hashCode());
		result = prime * result + ((carId == null) ? 0 : carId.hashCode());
		result = prime * result + ((carImg == null) ? 0 : carImg.hashCode());
		result = prime * result + ((carefulImg == null) ? 0 : carefulImg.hashCode());
		result = prime * result + ((cc == null) ? 0 : cc.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((criteria == null) ? 0 : criteria.hashCode());
		result = prime * result + ((gears == null) ? 0 : gears.hashCode());
		result = prime * result + isCheck;
		result = prime * result + isRent;
		result = prime * result + ((km == null) ? 0 : km.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(paidPer);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((purpose == null) ? 0 : purpose.hashCode());
		result = prime * result + ((relTime == null) ? 0 : relTime.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		CarInfo other = (CarInfo) obj;
		if (seats == null) {
			if (other.seats != null)
				return false;
		} else if (!seats.equals(other.seats))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (buyDate == null) {
			if (other.buyDate != null)
				return false;
		} else if (!buyDate.equals(other.buyDate))
			return false;
		if (carId == null) {
			if (other.carId != null)
				return false;
		} else if (!carId.equals(other.carId))
			return false;
		if (carImg == null) {
			if (other.carImg != null)
				return false;
		} else if (!carImg.equals(other.carImg))
			return false;
		if (carefulImg == null) {
			if (other.carefulImg != null)
				return false;
		} else if (!carefulImg.equals(other.carefulImg))
			return false;
		if (cc == null) {
			if (other.cc != null)
				return false;
		} else if (!cc.equals(other.cc))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (criteria == null) {
			if (other.criteria != null)
				return false;
		} else if (!criteria.equals(other.criteria))
			return false;
		if (gears == null) {
			if (other.gears != null)
				return false;
		} else if (!gears.equals(other.gears))
			return false;
		if (isCheck != other.isCheck)
			return false;
		if (isRent != other.isRent)
			return false;
		if (km == null) {
			if (other.km != null)
				return false;
		} else if (!km.equals(other.km))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (Double.doubleToLongBits(paidPer) != Double.doubleToLongBits(other.paidPer))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (purpose == null) {
			if (other.purpose != null)
				return false;
		} else if (!purpose.equals(other.purpose))
			return false;
		if (relTime == null) {
			if (other.relTime != null)
				return false;
		} else if (!relTime.equals(other.relTime))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CarInfo [brand=" + brand + ", model=" + model + ", type=" + type + ", buyDate=" + buyDate + ", km=" + km
				+ ", carefulImg=" + carefulImg + ", carImg=" + carImg + ", gears=" + gears + ", cc=" + cc + ", color="
				+ color + ", criteria=" + criteria + ", price=" + price + ", Seats=" + seats + ", isCheck=" + isCheck
				+ ", isRent=" + isRent + ", relTime=" + relTime + ", carId=" + carId + ", ownerId=" + ownerId
				+ ", purpose=" + purpose + ", remarks=" + remarks + ", paidPer=" + paidPer + "]";
	}

}
