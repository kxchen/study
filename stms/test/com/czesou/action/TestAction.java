package com.czesou.action;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.czesou.pojo.Authority;
import com.czesou.pojo.Role;
import com.czesou.pojo.RoleAuthority;
import com.czesou.pojo.User;
import com.czesou.service.RoleAuthorityService;
import com.czesou.service.RoleService;
import com.czesou.service.UserService;

public class TestAction {
	 
	/**
	 * @Title: getRoleAuthority
	 * @Description: 根据用户id查找到当前用户的角色拥有的权限，传入userId=null
	 * @return: void
	 * @throws Exception
	 */
	@Test
	public void getRoleAuthority() throws Exception {
		String userId = null;
		User user = new User();
		UserService userService = new UserService();
		Role role = new Role();
		RoleService roleService = new RoleService();
		RoleAuthorityService authorityService = new RoleAuthorityService();
		List<Authority> roleAuthorities = new ArrayList<Authority>();
		user = userService.get(userId);
		if (user != null) {
			role = roleService.get(user.getRole().getId());
			roleAuthorities = authorityService.authorityList(role);
			System.out.println(roleAuthorities);
		} else
			System.out.println("未找到用户");
	}

	/**
	 * @Title: getRoleAuthority1
	 * @Description:  根据用户id查找到当前用户的角色拥有的权限，传入userId=""
	 * @return: void
	 * @throws Exception
	 */
	@Test
	public void getRoleAuthority1() throws Exception {
		String userId = "";
		User user = new User();
		UserService userService = new UserService();
		Role role = new Role();
		RoleService roleService = new RoleService();
		RoleAuthorityService authorityService = new RoleAuthorityService();
		List<Authority> roleAuthorities = new ArrayList<Authority>();
		user = userService.get(userId);
		if (user != null) {
			role = roleService.get(user.getRole().getId());
			roleAuthorities = authorityService.authorityList(role);
			System.out.println(roleAuthorities);
		} else
			System.out.println("未找到用户");
	}

	/**
	 * @Title: getRoleAuthority2
	 * @Description: 根据用户id查找到当前用户的角色拥有的权限，传入userId=123
	 * @return: void
	 * @throws Exception
	 */
	/*
	@Test
	public void getRoleAuthority2() throws Exception {
		String userId = 123;
		User user = new User();
		UserService userService = new UserService();
		Role role = new Role();
		RoleService roleService = new RoleService();
		RoleAuthorityService authorityService = new RoleAuthorityService();
		List<Authority> roleAuthorities = new ArrayList<Authority>();
		user = userService.get(userId);
		if (user != null) {
			role = roleService.get(user.getRole().getId());
			roleAuthorities = authorityService.authorityList(role);
			System.out.println(roleAuthorities);
		} else
			System.out.println("未找到用户");
	}
*/
	/**
	 * @Title: getRoleAuthority3
	 * @Description: 根据用户id查找到当前用户的角色拥有的权限，传入userId="123"
	 * @return: void
	 * @throws Exception
	 */
	@Test
	public void getRoleAuthority3() throws Exception {
		String userId = "123";
		User user = new User();
		UserService userService = new UserService();
		Role role = new Role();
		RoleService roleService = new RoleService();
		RoleAuthorityService authorityService = new RoleAuthorityService();
		List<Authority> roleAuthorities = new ArrayList<Authority>();
		user = userService.get(userId);
		if (user != null) {
			role = roleService.get(user.getRole().getId());
			roleAuthorities = authorityService.authorityList(role);
			System.out.println(roleAuthorities);
		} else
			System.out.println("未找到用户");
	}

	/**
	 * @Title: getRoleAuthority4
	 * @Description: 根据用户id查找到当前用户的角色拥有的权限，传入userId="测试数据"
	 * @return: void
	 * @throws Exception
	 */
	@Test
	public void getRoleAuthority4() throws Exception {
		String userId = "测试数据";
		User user = new User();
		UserService userService = new UserService();
		Role role = new Role();
		RoleService roleService = new RoleService();
		RoleAuthorityService authorityService = new RoleAuthorityService();
		List<Authority> roleAuthorities = new ArrayList<Authority>();
		user = userService.get(userId);
		if (user != null) {
			role = roleService.get(user.getRole().getId());
			roleAuthorities = authorityService.authorityList(role);
			System.out.println(roleAuthorities);
		} else
			System.out.println("未找到用户");
	}
}
