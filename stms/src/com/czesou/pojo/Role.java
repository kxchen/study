package com.czesou.pojo;

import java.util.Objects;

/**
 * @ClassName: Role
 * @Description: 角色类
 * @author: 陈凯旋
 * @date: 2017年2月23日 下午4:33:32
 */
public class Role {
	private int id;
	/**
	 * @fieldName: name
	 * @fieldType: String
	 * @Description: 角色名称
	 */
	private String roleName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;  //先判断o是否为本对象，如果是就肯定是同一对象了，this 指向当前的对象
	        if (o == null || getClass() != o.getClass()) return false; //再判断o是否为null，和o.类对象和本类对象是否一致
	        Role transport = (Role) o;  //再把o对象强制转化为Role类对象
	        return Objects.equals(roleName, transport.roleName) && Objects.equals(id, transport.id);  //查看两个对象的name和id属性值是否相等,返回结果
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + "]";
	}

}
