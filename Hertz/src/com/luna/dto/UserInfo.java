package com.luna.dto;

/**
 * @ClassName: UserInfo
 * @Description: 用户实体类
 * @author c-kx@outlook.com
 * @date 2015年12月23日 上午9:20:13
 * 
 */
public class UserInfo {
	/**
	 * @Fields phone : 手机号码
	 */
	private String phone;
	/**
	 * @Fields password : 密码
	 */
	private String password;

	/**
	 * @Fields userName : 姓名
	 */
	private String userName;
	/**
	 * @Fields sex : 性别
	 */
	private String sex;
	/**
	 * @Fields idNum :身份证号码
	 */
	private String idNum;
	/**
	 * @Fields idImg : 身份证图片
	 */
	private String idImg;
	/**
	 * @Fields driverImg : 驾驶证图片
	 */
	private String driverImg;

	/**
	 * @Fields isAct : 是否激活
	 */
	private int isAct;
	/**
	 * @Fields isCer : 是否实名认证
	 */
	private int isCer;
	/**
	 * @Fields code : 激活码
	 */
	private String code;

	/**
	 * @Fields userId : 用户Id
	 */
	private String userId;
	/**
	 * @Fields remBal : 余额
	 */
	private double remBal;
	/**
	 * @Fields disCou : 优惠卷
	 */
	private double disCou;
	/**
	 * @Fields headImg : 头像
	 */
	private String headImg;
	/**
	 * @Fields address : 地址
	 */

	private String address;
	/**
	 * @Fields othPhone :紧急联系人电话
	 */
	private String othPhone;
	/**
	 * @Fields othName : 紧急联系人
	 */
	private String othName;
	/**
	 * @Fields other : 紧急联系人关系
	 */
	private String other;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOthPhone() {
		return othPhone;
	}

	public void setOthPhone(String othPhone) {
		this.othPhone = othPhone;
	}

	public String getOthName() {
		return othName;
	}

	public void setOthName(String othName) {
		this.othName = othName;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getIdImg() {
		return idImg;
	}

	public void setIdImg(String idImg) {
		this.idImg = idImg;
	}

	public String getDriverImg() {
		return driverImg;
	}

	public void setDriverImg(String driverImg) {
		this.driverImg = driverImg;
	}

	public int getIsAct() {
		return isAct;
	}

	public void setIsAct(int isAct) {
		this.isAct = isAct;
	}

	public int getIsCer() {
		return isCer;
	}

	public void setIsCer(int isCer) {
		this.isCer = isCer;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getRemBal() {
		return remBal;
	}

	public void setRemBal(double remBal) {
		this.remBal = remBal;
	}

	public double getDisCou() {
		return disCou;
	}

	public void setDisCou(double disCou) {
		this.disCou = disCou;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		long temp;
		temp = Double.doubleToLongBits(disCou);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((driverImg == null) ? 0 : driverImg.hashCode());
		result = prime * result + ((headImg == null) ? 0 : headImg.hashCode());
		result = prime * result + ((idImg == null) ? 0 : idImg.hashCode());
		result = prime * result + ((idNum == null) ? 0 : idNum.hashCode());
		result = prime * result + isAct;
		result = prime * result + isCer;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		temp = Double.doubleToLongBits(remBal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		UserInfo other = (UserInfo) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (Double.doubleToLongBits(disCou) != Double.doubleToLongBits(other.disCou))
			return false;
		if (driverImg == null) {
			if (other.driverImg != null)
				return false;
		} else if (!driverImg.equals(other.driverImg))
			return false;
		if (headImg == null) {
			if (other.headImg != null)
				return false;
		} else if (!headImg.equals(other.headImg))
			return false;
		if (idImg == null) {
			if (other.idImg != null)
				return false;
		} else if (!idImg.equals(other.idImg))
			return false;
		if (idNum == null) {
			if (other.idNum != null)
				return false;
		} else if (!idNum.equals(other.idNum))
			return false;
		if (isAct != other.isAct)
			return false;
		if (isCer != other.isCer)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (Double.doubleToLongBits(remBal) != Double.doubleToLongBits(other.remBal))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserInfo [phone=" + phone + ", password=" + password + ", userName=" + userName + ", sex=" + sex
				+ ", idNum=" + idNum + ", idImg=" + idImg + ", driverImg=" + driverImg + ", isAct=" + isAct + ", isCer="
				+ isCer + ", code=" + code + ", userId=" + userId + ", remBal=" + remBal + ", disCou=" + disCou
				+ ", headImg=" + headImg + "]";
	}

}
