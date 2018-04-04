package com.luna.dto;

/**
 * @ClassName: ShopCart
 * @Description:购物车类
 * @author c-kx@outlook.com
 * @date 2015年11月24日 上午10:00:54
 * 
 */
public class ShopCart {
	/**
	 * @Fields shopCartId : 主键
	 */
	private int shopCartId;
	/**
	 * @Fields quantity : 数量
	 */
	private int quantity;
	/**
	 * @Fields mobilePhone : 对应手机
	 */
	private MobilePhoneInfo mobilePhone;
	/**
	 * @Fields user : 所属用户
	 */
	private UserInfo user;

	public int getShopCartId() {
		return shopCartId;
	}

	public void setShopCartId(int shopCartId) {
		this.shopCartId = shopCartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public MobilePhoneInfo getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(MobilePhoneInfo mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mobilePhone == null) ? 0 : mobilePhone.hashCode());
		result = prime * result + quantity;
		result = prime * result + shopCartId;
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
		ShopCart other = (ShopCart) obj;
		if (mobilePhone == null) {
			if (other.mobilePhone != null)
				return false;
		} else if (!mobilePhone.equals(other.mobilePhone))
			return false;
		if (quantity != other.quantity)
			return false;
		if (shopCartId != other.shopCartId)
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
		return "ShoppingCart [cartItemId=" + shopCartId + ", quantity=" + quantity + ", mobilePhone=" + mobilePhone
				+ ", user=" + user + "]";
	}

}
