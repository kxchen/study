package com.luna.dto;

/**
 * @ClassName: ScreenSize
 * @Description: 屏幕尺寸类
 * @author c-kx@outlook.com
 * @date 2015年11月30日 下午8:37:52
 * 
 */
public class ScreenSize {
	private int ScrSizeId;
	/**
	 * @Fields scrSizeName : 尺寸大小
	 */
	private String scrSizeName;

	public int getScrSizeId() {
		return ScrSizeId;
	}

	public void setScrSizeId(int scrSizeId) {
		ScrSizeId = scrSizeId;
	}

	public String getScrSizeName() {
		return scrSizeName;
	}

	public void setScrSizeName(String scrSizeName) {
		this.scrSizeName = scrSizeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ScrSizeId;
		result = prime * result + ((scrSizeName == null) ? 0 : scrSizeName.hashCode());
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
		ScreenSize other = (ScreenSize) obj;
		if (ScrSizeId != other.ScrSizeId)
			return false;
		if (scrSizeName == null) {
			if (other.scrSizeName != null)
				return false;
		} else if (!scrSizeName.equals(other.scrSizeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ScreenSize [ScrSizeId=" + ScrSizeId + ", scrSizeName=" + scrSizeName + "]";
	}

}
