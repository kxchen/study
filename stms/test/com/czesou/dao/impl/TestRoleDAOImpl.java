package com.czesou.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.czesou.db.DataBase;
import com.czesou.pojo.Role;

public class TestRoleDAOImpl {
	Connection conn = null;
	Role role = null;
	RoleDAOImpl roleDAOImpl = new RoleDAOImpl();

	/**
	 * @Title: before
	 * @Description: 每次运行测试方法前都会运行该方法，获取数据库链接和初始化数据操作
	 * @return: void
	 * @throws SQLException
	 */
	@Before
	public void before() throws SQLException {
		role = new Role();
		conn = DataBase.getConn();
		roleDAOImpl.setConn(conn);
	}

	/**
	 * @Title: save
	 * @Description:id和name都输入正常值测试方法是否正常执行
	 * @return: void
	 * @throws Exception
	 */
	@Test
	public void save() throws Exception {
		role.setId(11);
		role.setRoleName("测试员");
		roleDAOImpl.save(role);
	}

	/**
	 * @Title: save1
	 * @Description: 只输入id不输入name测试方法是否正常运行
	 * @return: void
	 * @throws Exception
	 */
	@Test
	public void save1() throws Exception {
		role.setId(12);
		roleDAOImpl.save(role);
	}

	/**
	 * @Title: save2
	 * @Description: 只输入name不输入id测试程序是否正常运行
	 * @return: void
	 * @throws Exception
	 */
	@Test
	public void save2() throws Exception {
		role.setRoleName("测试员");
		boolean flag = roleDAOImpl.save(role);
		Assert.assertEquals(true, flag);
	}

	/**
	 * @Title: save3
	 * @Description: 传入一个空的role测试程序是否正常运行
	 * @return: void
	 * @throws Exception
	 */
	@Test
	public void save3() throws Exception {
		role = null;
		boolean flag = roleDAOImpl.save(role);
		Assert.assertEquals(false, flag);
	}

	/**
	 * @Title: save4
	 * @Description: id输入0测试程序是否正常运行
	 * @return: void
	 * @throws Exception
	 */
	@Test
	public void save4() throws Exception {
		role.setId(0);
		role.setRoleName("测试员");
		roleDAOImpl.save(role);
	}

	/**
	 * @Title: save5
	 * @Description: id输入负数测试程序是否正常运行
	 * @return: void
	 * @throws Exception
	 */
	@Test
	public void save5() throws Exception {
		role.setId(-1);
		role.setRoleName("测试员");
		roleDAOImpl.save(role);
	}

	/**
	 * @Title: save6
	 * @Description: 输入大于int最大值得正数作为id
	 * @return: void
	 * @throws Exception
	 */
	
	/*
	@Test
	public void save6() throws Exception {
		role.setId(15456564856456214545);
		role.setRoleName("测试员");
		roleDAOImpl.save(role);
	}
	 */
	
	/**
	 * @Title: save7
	 * @Description: 输入大于int最大值得负数作为id
	 * @return: void
	 * @throws Exception
	 */
	/*
	@Test
	public void save7() throws Exception {
		role.setId(-5456564856456214545);
		role.setRoleName("测试员");
		roleDAOImpl.save(role);
	}
	 */
	/**
	 * @Title: save8
	 * @Description: 输入正常id，name输入"";
	 * @return: void
	 * @throws Exception
	 */
	@Test
	public void save8() throws Exception {
		role.setId(13);
		role.setRoleName("");
		roleDAOImpl.save(role);
	}

	/**
	 * @Title: save9
	 * @Description: 输入正常id，输入int类型的name
	 * @return: void
	 * @throws Exception
	 */
	/*
	@Test
	public void save9() throws Exception {
		role.setId(14);
		role.setRoleName(152);
		roleDAOImpl.save(role);
	}
	 */
	/**
	 * @Title: save10
	 * @Description: 输入正常name，输入重复id
	 * @return: void
	 * @throws Exception
	 */
	@Test
	public void save10() throws Exception {
		role.setId(11);
		role.setRoleName("测试员");
		roleDAOImpl.save(role);
	}

	/**
	 * @Title: after
	 * @Description: 每次运行测试方法结束都会运行该方法，进行实物的提交和关闭数据库链接
	 * @return: void
	 */
	@After
	public void after() {
		DataBase.commit();
		DataBase.releaseConnection(conn);
	}
}
