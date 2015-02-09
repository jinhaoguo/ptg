package org.gdut.ptg.test.mybatis;

import javax.annotation.Resource;

import org.gdut.ptg.model.system.SysRole;
import org.gdut.ptg.model.system.SysUser;
import org.gdut.ptg.service.system.SysRoleService;
import org.gdut.ptg.service.system.SysUserService;
import org.gdut.ptg.test.BaseTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class MyBatisTestCase extends BaseTestCase {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	
	@Test
	@Rollback(true)
	public void testGetAll(){
		SysUser user = new SysUser();
		String userId = "test first";
		user.setId(userId);
		user.setPassword("sdfsdf");
		user.setUserName("for test");
		sysUserService.add(user);
		SysUser getUser = sysUserService.get(userId);
		Assert.assertNotNull(getUser);
		sysUserService.del(user.getId());
		Assert.assertNull(sysUserService.get(userId));
		
		SysRole role = new SysRole();
		String roleId = "test role id";
		role.setId(roleId);
		role.setRoleName("admin");
		sysRoleService.add(role);
		Assert.assertNotNull(sysRoleService.get(roleId));
		sysRoleService.del(roleId);
		Assert.assertNull(sysRoleService.get(roleId));
	}
	
}
