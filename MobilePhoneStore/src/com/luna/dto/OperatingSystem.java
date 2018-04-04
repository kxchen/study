package com.luna.dto;

/**
 * @ClassName: OperatingSystem
 * @Description: 操作系统类
 * @author c-kx@outlook.com
 * @date 2015年11月30日 下午8:22:19
 * 
 */
public class OperatingSystem {
	/**
	 * @Fields opeSystemId : 操作系统Id
	 */
	private int opeSystemId;
	/**
	 * @Fields opeSystemName : 操作系统名称
	 */
	private String opeSystemName;

	public int getOpeSystemId() {
		return opeSystemId;
	}

	public void setOpeSystemId(int opeSystemId) {
		this.opeSystemId = opeSystemId;
	}

	public String getOpeSystemName() {
		return opeSystemName;
	}

	public void setOpeSystemName(String opeSystemName) {
		this.opeSystemName = opeSystemName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + opeSystemId;
		result = prime * result + ((opeSystemName == null) ? 0 : opeSystemName.hashCode());
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
		OperatingSystem other = (OperatingSystem) obj;
		if (opeSystemId != other.opeSystemId)
			return false;
		if (opeSystemName == null) {
			if (other.opeSystemName != null)
				return false;
		} else if (!opeSystemName.equals(other.opeSystemName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OperatingSystem [opeSystemId=" + opeSystemId + ", opeSystemName=" + opeSystemName + "]";
	}

}
