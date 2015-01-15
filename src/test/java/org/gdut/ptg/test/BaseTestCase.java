package org.gdut.ptg.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试用例基类
 * @author guojh
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:conf/app-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class BaseTestCase {

	/**
	 * 可以运行此测试用例，自动创建activiti数据库
	 * 需要修改app-test-activiti.xml中
	 * processEngineConfiguration配置里的databaseSchemaUpdate=true
	 */
	@Test
	public void test(){
		System.out.println("Hello!");
	}
	
}
