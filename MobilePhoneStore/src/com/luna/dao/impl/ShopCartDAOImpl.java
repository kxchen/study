package com.luna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.luna.dao.IShopCartDAO;
import com.luna.database.DataBase;
import com.luna.dto.Brand;
import com.luna.dto.MobilePhoneInfo;
import com.luna.dto.NetworkType;
import com.luna.dto.OperatingSystem;
import com.luna.dto.ScreenSize;
import com.luna.dto.ShopCart;
import com.luna.dto.UserInfo;

public class ShopCartDAOImpl implements IShopCartDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}

	@Override
	public boolean save(ShopCart shopCart) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into shop_cart(quantity,user_id,mob_phone_id) values (?,?,?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setInt(i++, shopCart.getQuantity());
			pst.setInt(i++, shopCart.getUser().getUserId());
			pst.setInt(i++, shopCart.getMobilePhone().getMobPhoneId());
			if (pst.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(pst);
		}
		return flag;
	}

	@Override
	public boolean delete(ShopCart shopCart) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from shop_cart where shop_cart_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, shopCart.getShopCartId());
			if (pst.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(pst);
		}
		return flag;
	}

	@Override
	public boolean deleteAll(UserInfo userInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from shop_cart where user_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userInfo.getUserId());
			if (pst.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(pst);
		}
		return flag;
	}

	@Override
	public boolean update(ShopCart shopCart) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "update shop_cart set quantity=?,user_id=?,mob_phone_id=? where shop_cart_id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setInt(i++, shopCart.getQuantity());
			pst.setInt(i++, shopCart.getUser().getUserId());
			pst.setInt(i++, shopCart.getMobilePhone().getMobPhoneId());
			pst.setInt(i++, shopCart.getShopCartId());
			if (pst.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(pst);
		}
		return flag;
	}

	@Override
	public ShopCart get(int shopCartId) throws Exception {
		ShopCart shopCart = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		MobilePhoneInfo mobilePhoneInfo = null;
		UserInfo userInfo = null;
		Brand brand = null;
		NetworkType networkType = null;
		OperatingSystem operatingSystem = null;
		ScreenSize screenSize = null;
		try {
			String sql = "select * from shop_cart_view where shop_cart_id =?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, shopCartId);
			rs = pst.executeQuery();
			if (rs.next()) {
				shopCart = new ShopCart();
				// 得到数量
				shopCart.setQuantity(rs.getInt("quantity"));
				shopCart.setShopCartId(rs.getInt("shop_cart_id"));

				userInfo = new UserInfo();
				userInfo.setLoginName(rs.getString("login_name"));
				userInfo.setAddress(rs.getString("address"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setRealName(rs.getString("real_name"));
				userInfo.setRegtime(rs.getDate("regtime"));
				userInfo.setUserId(rs.getInt("user_id"));
				// 得到User
				shopCart.setUser(userInfo);

				mobilePhoneInfo = new MobilePhoneInfo();

				brand = new Brand();
				brand.setBrandName(rs.getString("brand_name"));
				brand.setBrandId(rs.getInt("brand_id"));

				networkType = new NetworkType();
				networkType.setNetTypeName((rs.getString("net_type_name")));
				networkType.setNetTypeId(rs.getInt("net_type_id"));

				operatingSystem = new OperatingSystem();
				operatingSystem.setOpeSystemName(rs.getString("ope_system_name"));
				operatingSystem.setOpeSystemId(rs.getInt("ope_system_id"));

				screenSize = new ScreenSize();
				screenSize.setScrSizeName(rs.getString("scr_size_name"));
				screenSize.setScrSizeId(rs.getInt("scr_size_id"));

				mobilePhoneInfo.setBrand(brand);
				mobilePhoneInfo.setNetworkType(networkType);
				mobilePhoneInfo.setOperatingSystem(operatingSystem);
				mobilePhoneInfo.setScreenSize(screenSize);

				mobilePhoneInfo.setModel(rs.getString("model"));
				mobilePhoneInfo.setColor(rs.getString("color"));
				mobilePhoneInfo.setDescipt(rs.getString("descipt"));
				mobilePhoneInfo.setImgPath(rs.getString("img_path"));
				mobilePhoneInfo.setPixels(rs.getString("pixels"));
				mobilePhoneInfo.setRam(rs.getString("ram"));
				mobilePhoneInfo.setRom(rs.getString("rom"));
				mobilePhoneInfo.setPrice(rs.getDouble("price"));
				mobilePhoneInfo.setRealPrice(rs.getDouble("real_price"));
				mobilePhoneInfo.setRegDate(rs.getDate("reg_date"));
				mobilePhoneInfo.setMobPhoneId(rs.getInt("mob_phone_id"));
				mobilePhoneInfo.setState(rs.getInt("state"));
				// 得到MobilePhone
				shopCart.setMobilePhone(mobilePhoneInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return shopCart;
	}

	@Override
	public ShopCart get(UserInfo user, MobilePhoneInfo mobPhone) throws Exception {
		ShopCart shopCart = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		MobilePhoneInfo mobilePhoneInfo = null;
		UserInfo userInfo = null;
		Brand brand = null;
		NetworkType networkType = null;
		OperatingSystem operatingSystem = null;
		ScreenSize screenSize = null;
		try {
			String sql = "select * from shop_cart_view where user_id =? and mob_phone_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, user.getUserId());
			pst.setInt(2, mobPhone.getMobPhoneId());
			rs = pst.executeQuery();
			if (rs.next()) {
				shopCart = new ShopCart();
				// 得到数量
				shopCart.setQuantity(rs.getInt("quantity"));
				shopCart.setShopCartId(rs.getInt("shop_cart_id"));

				userInfo = new UserInfo();
				userInfo.setLoginName(rs.getString("login_name"));
				userInfo.setAddress(rs.getString("address"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setRealName(rs.getString("real_name"));
				userInfo.setRegtime(rs.getDate("regtime"));
				userInfo.setUserId(rs.getInt("user_id"));
				// 得到User
				shopCart.setUser(userInfo);

				mobilePhoneInfo = new MobilePhoneInfo();

				brand = new Brand();
				brand.setBrandName(rs.getString("brand_name"));
				brand.setBrandId(rs.getInt("brand_id"));

				networkType = new NetworkType();
				networkType.setNetTypeName((rs.getString("net_type_name")));
				networkType.setNetTypeId(rs.getInt("net_type_id"));

				operatingSystem = new OperatingSystem();
				operatingSystem.setOpeSystemName(rs.getString("ope_system_name"));
				operatingSystem.setOpeSystemId(rs.getInt("ope_system_id"));

				screenSize = new ScreenSize();
				screenSize.setScrSizeName(rs.getString("scr_size_name"));
				screenSize.setScrSizeId(rs.getInt("scr_size_id"));

				mobilePhoneInfo.setBrand(brand);
				mobilePhoneInfo.setNetworkType(networkType);
				mobilePhoneInfo.setOperatingSystem(operatingSystem);
				mobilePhoneInfo.setScreenSize(screenSize);

				mobilePhoneInfo.setModel(rs.getString("model"));
				mobilePhoneInfo.setColor(rs.getString("color"));
				mobilePhoneInfo.setDescipt(rs.getString("descipt"));
				mobilePhoneInfo.setImgPath(rs.getString("img_path"));
				mobilePhoneInfo.setPixels(rs.getString("pixels"));
				mobilePhoneInfo.setRam(rs.getString("ram"));
				mobilePhoneInfo.setRom(rs.getString("rom"));
				mobilePhoneInfo.setPrice(rs.getDouble("price"));
				mobilePhoneInfo.setRealPrice(rs.getDouble("real_price"));
				mobilePhoneInfo.setRegDate(rs.getDate("reg_date"));
				mobilePhoneInfo.setMobPhoneId(rs.getInt("mob_phone_id"));
				mobilePhoneInfo.setState(rs.getInt("state"));
				// 得到MobilePhone
				shopCart.setMobilePhone(mobilePhoneInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return shopCart;
	}

	@Override
	public List<ShopCart> list(UserInfo user) throws Exception {
		List<ShopCart> ShopCartList = null;
		ShopCart shopCart = null;
		MobilePhoneInfo mobilePhoneInfo = null;
		UserInfo userInfo = null;
		Brand brand = null;
		NetworkType networkType = null;
		OperatingSystem operatingSystem = null;
		ScreenSize screenSize = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select * from shop_cart_view where user_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, user.getUserId());
			rs = pst.executeQuery();
			ShopCartList = new ArrayList<ShopCart>();
			while (rs.next()) {
				shopCart = new ShopCart();
				// 得到数量
				shopCart.setQuantity(rs.getInt("quantity"));
				shopCart.setShopCartId(rs.getInt("shop_cart_id"));

				userInfo = new UserInfo();
				userInfo.setLoginName(rs.getString("login_name"));
				userInfo.setAddress(rs.getString("address"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setPhone(rs.getString("phone"));
				userInfo.setRealName(rs.getString("real_name"));
				userInfo.setRegtime(rs.getDate("regtime"));
				userInfo.setUserId(rs.getInt("user_id"));
				// 得到User
				shopCart.setUser(userInfo);

				mobilePhoneInfo = new MobilePhoneInfo();

				brand = new Brand();
				brand.setBrandName(rs.getString("brand_name"));
				brand.setBrandId(rs.getInt("brand_id"));

				networkType = new NetworkType();
				networkType.setNetTypeName((rs.getString("net_type_name")));
				networkType.setNetTypeId(rs.getInt("net_type_id"));

				operatingSystem = new OperatingSystem();
				operatingSystem.setOpeSystemName(rs.getString("ope_system_name"));
				operatingSystem.setOpeSystemId(rs.getInt("ope_system_id"));

				screenSize = new ScreenSize();
				screenSize.setScrSizeName(rs.getString("scr_size_name"));
				screenSize.setScrSizeId(rs.getInt("scr_size_id"));

				mobilePhoneInfo.setBrand(brand);
				mobilePhoneInfo.setNetworkType(networkType);
				mobilePhoneInfo.setOperatingSystem(operatingSystem);
				mobilePhoneInfo.setScreenSize(screenSize);

				mobilePhoneInfo.setModel(rs.getString("model"));
				mobilePhoneInfo.setColor(rs.getString("color"));
				mobilePhoneInfo.setDescipt(rs.getString("descipt"));
				mobilePhoneInfo.setImgPath(rs.getString("img_path"));
				mobilePhoneInfo.setPixels(rs.getString("pixels"));
				mobilePhoneInfo.setRam(rs.getString("ram"));
				mobilePhoneInfo.setRom(rs.getString("rom"));
				mobilePhoneInfo.setPrice(rs.getDouble("price"));
				mobilePhoneInfo.setRealPrice(rs.getDouble("real_price"));
				mobilePhoneInfo.setRegDate(rs.getDate("reg_date"));
				mobilePhoneInfo.setMobPhoneId(rs.getInt("mob_phone_id"));
				mobilePhoneInfo.setState(rs.getInt("state"));
				// 得到MobilePhone
				shopCart.setMobilePhone(mobilePhoneInfo);
				// 添加到列表
				ShopCartList.add(shopCart);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return ShopCartList;
	}

}
