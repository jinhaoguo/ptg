package org.gdut.ptg.test.mybatis;

import java.util.List;

import javax.annotation.Resource;

import org.gdut.ptg.model.system.SysRole;
import org.gdut.ptg.model.system.SysUser;
import org.gdut.ptg.service.system.SysRoleService;
import org.gdut.ptg.service.system.SysUserService;
import org.gdut.ptg.test.BaseTestCase;
import org.junit.Test;

public class MyBatisTestCase extends BaseTestCase {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	
	@Test
	public void testGetAll(){
		List<SysUser> all = sysUserService.getAll();
		for(SysUser user:all){
			System.out.println(user);
		}
		List<SysRole> roles = sysRoleService.getAll();
		for(SysRole role:roles){
			System.out.println(role);
		}
	}
	
}
