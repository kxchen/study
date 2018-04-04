package com.czesou.pojo;

import java.util.Objects;

/**
 * @ClassName: Department
 * @Description: 系部类
 * @author: 陈凯旋
 * @date: 2017年2月23日 下午4:33:11
 */
public class Department {
	private int id;
	/**
	 * @fieldName: name
	 * @fieldType: String
	 * @Description: 系部名称
	 */
	private String departmentName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true; // 先判断o是否为本对象，如果是就肯定是同一对象了，this 指向当前的对象
		if (o == null || getClass() != o.getClass())
			return false; // 再判断o是否为null，和o.类对象和本类对象是否一致
		Department transport = (Department) o; // 再把o对象强制转化为Department类对象
		return Objects.equals(departmentName, transport.departmentName) && Objects.equals(id, transport.id); // 查看两个对象的name和id属性值是否相等,返回结果
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName + "]";
	}

}
