package com.luna.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.luna.dao.IMobilePhoneInfoDAO;
import com.luna.database.DataBase;
import com.luna.dto.Brand;
import com.luna.dto.MobilePhoneInfo;
import com.luna.dto.NetworkType;
import com.luna.dto.OperatingSystem;
import com.luna.dto.ScreenSize;

public class MobilePhoneInfoDAOImpl implements IMobilePhoneInfoDAO {
	private Connection conn = null;

	@Override
	public void setConn(Connection conn) {
		this.conn = conn;

	}

	@Override
	public boolean save(MobilePhoneInfo mobilePhoneInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "insert into mobile_phone(brand_id,model,ope_system_id,net_type_id,scr_size_id,color,price,real_price,descipt,img_path,reg_date,pixels,ram,rom,state) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setInt(i++, mobilePhoneInfo.getBrand().getBrandId());
			pst.setString(i++, mobilePhoneInfo.getModel());
			pst.setInt(i++, mobilePhoneInfo.getOperatingSystem().getOpeSystemId());
			pst.setInt(i++, mobilePhoneInfo.getNetworkType().getNetTypeId());
			pst.setInt(i++, mobilePhoneInfo.getScreenSize().getScrSizeId());
			pst.setString(i++, mobilePhoneInfo.getColor());
			pst.setDouble(i++, mobilePhoneInfo.getPrice());
			pst.setDouble(i++, mobilePhoneInfo.getRealPrice());
			pst.setString(i++, mobilePhoneInfo.getDescipt());
			pst.setString(i++, mobilePhoneInfo.getImgPath());
			java.sql.Date regDate = (java.sql.Date) mobilePhoneInfo.getRegDate();
			pst.setDate(i++, regDate);
			pst.setString(i++, mobilePhoneInfo.getPixels());
			pst.setString(i++, mobilePhoneInfo.getRam());
			pst.setString(i++, mobilePhoneInfo.getRom());
			pst.setInt(i++, mobilePhoneInfo.getState());

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
	public boolean delete(MobilePhoneInfo mobilePhoneInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			String sql = "delete from mobile_phone where mob_phone_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, mobilePhoneInfo.getMobPhoneId());
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
	public boolean update(MobilePhoneInfo mobilePhoneInfo) throws Exception {
		PreparedStatement pst = null;
		boolean flag = false;
		try {

			String sql = "update mobile_phone set brand_id=?,model=?,ope_system_id=?,net_type_id=?,scr_size_id=?,color=?,price=?,real_price=?,descipt=?,img_path=?,reg_date=?,pixels=?,ram=?,rom=?,state=? where mob_phone_id=?";
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setInt(i++, mobilePhoneInfo.getBrand().getBrandId());
			pst.setString(i++, mobilePhoneInfo.getModel());
			pst.setInt(i++, mobilePhoneInfo.getOperatingSystem().getOpeSystemId());
			pst.setInt(i++, mobilePhoneInfo.getNetworkType().getNetTypeId());
			pst.setInt(i++, mobilePhoneInfo.getScreenSize().getScrSizeId());
			pst.setString(i++, mobilePhoneInfo.getColor());
			pst.setDouble(i++, mobilePhoneInfo.getPrice());
			pst.setDouble(i++, mobilePhoneInfo.getRealPrice());
			pst.setString(i++, mobilePhoneInfo.getDescipt());
			pst.setString(i++, mobilePhoneInfo.getImgPath());
			java.sql.Date regDate = (java.sql.Date) mobilePhoneInfo.getRegDate();
			pst.setDate(i++, regDate);
			pst.setString(i++, mobilePhoneInfo.getPixels());
			pst.setString(i++, mobilePhoneInfo.getRam());
			pst.setString(i++, mobilePhoneInfo.getRom());
			pst.setInt(i++, mobilePhoneInfo.getState());
			pst.setInt(i++, mobilePhoneInfo.getMobPhoneId());
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
	public MobilePhoneInfo get(int mobPhoneId) throws Exception {
		MobilePhoneInfo mobilePhoneInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Brand brand = null;
		NetworkType networkType = null;
		OperatingSystem operatingSystem = null;
		ScreenSize screenSize = null;
		try {
			String sql = "select * from mobile_phone_view where mob_phone_id =?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, mobPhoneId);
			rs = pst.executeQuery();
			if (rs.next()) {

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
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs, pst);
		}
		return mobilePhoneInfo;
	}

	@Override
	public List<MobilePhoneInfo> list(int pageSize, int pageNo, String queryString) throws Exception {
		List<MobilePhoneInfo> mobilePhoneInfoList = null;
		MobilePhoneInfo mobilePhoneInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Brand brand = null;
		NetworkType networkType = null;
		OperatingSystem operatingSystem = null;
		ScreenSize screenSize = null;
		try {
			String sql = "select * from mobile_phone_view  " + queryString + " limit ?,?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, pageSize * (pageNo - 1));
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			mobilePhoneInfoList = new ArrayList<MobilePhoneInfo>();
			while (rs.next()) {

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
				mobilePhoneInfoList.add(mobilePhoneInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.free(rs,pst);
		}
		return mobilePhoneInfoList;
	}

	@Override
	public int getCount(String queryString) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(*) from mobile_phone_view";
			pst = conn.prepareStatement(sql + queryString);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			rs.close();
			pst.close();
		}
		return count;
	}

	@Override
	public List<MobilePhoneInfo> hotSale() throws Exception {
		List<MobilePhoneInfo> mobilePhoneInfoList = null;
		MobilePhoneInfo mobilePhoneInfo = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Brand brand = null;
		NetworkType networkType = null;
		OperatingSystem operatingSystem = null;
		ScreenSize screenSize = null;
		try {
			String sql = "select * from hot_sale_view limit ?,? ";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, 0);
			pst.setInt(2, 4);
			rs = pst.executeQuery();
			mobilePhoneInfoList = new ArrayList<MobilePhoneInfo>();
			while (rs.next()) {

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
				mobilePhoneInfoList.add(mobilePhoneInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			rs.close();
			pst.close();
		}
		return mobilePhoneInfoList;
	}

}
