package com.luna.dto;

import java.util.Date;

/**
 * @ClassName: MobilePhoneInfo
 * @Description:手机类
 * @author c-kx@outlook.com
 * @date 2015年11月24日 上午10:02:07
 * 
 */
public class MobilePhoneInfo {
	/**
	 * 整个类都改掉，添加品牌类， 操作系统类，网络制式类，作为分类标准。
	 */
	private int mobPhoneId;// 主键
	/**
	 * @Fields brand : 品牌
	 */
	private Brand brand;
	/**
	 * @Fields model : 型号
	 */
	private String model;
	/**
	 * @Fields operatingSystem :操作系统
	 */
	private OperatingSystem operatingSystem;
	/**
	 * @Fields networkType : 网络制式
	 */
	private NetworkType networkType;
	/**
	 * @Fields screenSize : 屏幕尺寸
	 */
	private ScreenSize screenSize;
	/**
	 * @Fields color : 颜色
	 */
	private String color;
	/**
	 * @Fields price :价格
	 */
	private double price;
	/**
	 * @Fields realPrice : 实际价格
	 */
	private double realPrice;
	/**
	 * @Fields descipt : 描述
	 */
	private String descipt;
	/**
	 * @Fields imgPath : 图片路径
	 */
	private String imgPath;
	/**
	 * @Fields RegDate : 上架时间
	 */
	private Date regDate;
	/**
	 * @Fields pixels : 像素
	 */
	private String pixels;
	/**
	 * @Fields ram : 运行内存
	 */
	private String ram;
	/**
	 * @Fields rom : 储存
	 */
	private String rom;
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getMobPhoneId() {
		return mobPhoneId;
	}

	public void setMobPhoneId(int mobPhoneId) {
		this.mobPhoneId = mobPhoneId;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(OperatingSystem operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public NetworkType getNetworkType() {
		return networkType;
	}

	public void setNetworkType(NetworkType networkType) {
		this.networkType = networkType;
	}

	public ScreenSize getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(ScreenSize screenSize) {
		this.screenSize = screenSize;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(double realPrice) {
		this.realPrice = realPrice;
	}

	public String getDescipt() {
		return descipt;
	}

	public void setDescipt(String descipt) {
		this.descipt = descipt;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getPixels() {
		return pixels;
	}

	public void setPixels(String pixels) {
		this.pixels = pixels;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getRom() {
		return rom;
	}

	public void setRom(String rom) {
		this.rom = rom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((regDate == null) ? 0 : regDate.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((descipt == null) ? 0 : descipt.hashCode());
		result = prime * result + ((imgPath == null) ? 0 : imgPath.hashCode());
		result = prime * result + mobPhoneId;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((networkType == null) ? 0 : networkType.hashCode());
		result = prime * result + ((operatingSystem == null) ? 0 : operatingSystem.hashCode());
		result = prime * result + ((pixels == null) ? 0 : pixels.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((ram == null) ? 0 : ram.hashCode());
		temp = Double.doubleToLongBits(realPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((rom == null) ? 0 : rom.hashCode());
		result = prime * result + ((screenSize == null) ? 0 : screenSize.hashCode());
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
		MobilePhoneInfo other = (MobilePhoneInfo) obj;
		if (regDate == null) {
			if (other.regDate != null)
				return false;
		} else if (!regDate.equals(other.regDate))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (descipt == null) {
			if (other.descipt != null)
				return false;
		} else if (!descipt.equals(other.descipt))
			return false;
		if (imgPath == null) {
			if (other.imgPath != null)
				return false;
		} else if (!imgPath.equals(other.imgPath))
			return false;
		if (mobPhoneId != other.mobPhoneId)
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (networkType == null) {
			if (other.networkType != null)
				return false;
		} else if (!networkType.equals(other.networkType))
			return false;
		if (operatingSystem == null) {
			if (other.operatingSystem != null)
				return false;
		} else if (!operatingSystem.equals(other.operatingSystem))
			return false;
		if (pixels == null) {
			if (other.pixels != null)
				return false;
		} else if (!pixels.equals(other.pixels))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (ram == null) {
			if (other.ram != null)
				return false;
		} else if (!ram.equals(other.ram))
			return false;
		if (Double.doubleToLongBits(realPrice) != Double.doubleToLongBits(other.realPrice))
			return false;
		if (rom == null) {
			if (other.rom != null)
				return false;
		} else if (!rom.equals(other.rom))
			return false;
		if (screenSize == null) {
			if (other.screenSize != null)
				return false;
		} else if (!screenSize.equals(other.screenSize))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MobilePhoneInfo [mobPhoneId=" + mobPhoneId + ", brand=" + brand + ", model=" + model
				+ ", operatingSystem=" + operatingSystem + ", networkType=" + networkType + ", screenSize=" + screenSize
				+ ", color=" + color + ", price=" + price + ", realPrice=" + realPrice + ", descipt=" + descipt
				+ ", imgPath=" + imgPath + ", RegDate=" + regDate + ", pixels=" + pixels + ", ram=" + ram + ", rom="
				+ rom + "]";
	}

}
