package com.czesou.pojo;

/**
 * @ClassName: RoleAuthority
 * @Description: 角色权限类
 * @author: 陈凯旋
 * @date: 2017年2月23日 下午4:33:46
 */
public class RoleAuthority {
	private int id;
	/**
	 * @fieldName: role
	 * @fieldType: Role
	 * @Description: 角色
	 */
	private Role role;
	/**
	 * @fieldName: authority
	 * @fieldType: Authority
	 * @Description: 权限
	 */
	private Authority authority;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "RoleAuthority [id=" + id + ", role=" + role + ", authority=" + authority + "]";
	}

}
