package com.luna.dto;

/**
 * @ClassName: NetworkType
 * @Description: 网络类型类
 * @author c-kx@outlook.com
 * @date 2015年11月30日 下午8:37:26
 * 
 */
public class NetworkType {
	private int netTypeId;
	/**
	 * @Fields netTypeName : 网络类型名称
	 */
	private String netTypeName;

	public int getNetTypeId() {
		return netTypeId;
	}

	public void setNetTypeId(int netTypeId) {
		this.netTypeId = netTypeId;
	}

	public String getNetTypeName() {
		return netTypeName;
	}

	public void setNetTypeName(String netTypeName) {
		this.netTypeName = netTypeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + netTypeId;
		result = prime * result + ((netTypeName == null) ? 0 : netTypeName.hashCode());
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
		NetworkType other = (NetworkType) obj;
		if (netTypeId != other.netTypeId)
			return false;
		if (netTypeName == null) {
			if (other.netTypeName != null)
				return false;
		} else if (!netTypeName.equals(other.netTypeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NetworkType [netTypeId=" + netTypeId + ", netTypeName=" + netTypeName + "]";
	}

}
