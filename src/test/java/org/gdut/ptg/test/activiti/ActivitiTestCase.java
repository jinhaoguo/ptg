package org.gdut.ptg.test.activiti;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.gdut.ptg.test.BaseTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ActivitiTestCase extends BaseTestCase {

	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	TaskService taskService;

	/**
	 * activiti测试用例，对应的部署文件放在test目录下的resources
	 * 加上@Rollback(true)，则测试的数据会在测试用例结束时自动回滚，不影响原数据库数据
	 * @throws IOException
	 */
	@Test
	@Rollback(true)
	public void testDeploy() throws IOException {
		InputStream is = readXmlFile();
		Assert.assertNotNull(is);
		// 发布流程
		Deployment deployment = repositoryService.createDeployment()
				.addInputStream("bpmn20.xml", is).name("holidayRequest")
				.deploy();
		Assert.assertNotNull(deployment);
		System.out.println("deployId:" + deployment.getId());
		// 查询流程定义
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.deploymentId(deployment.getId()).singleResult();

		Long businessKey = new Double(1000000 * Math.random()).longValue();
		// 启动流程
		runtimeService.startProcessInstanceById(processDefinition.getId(),
				businessKey.toString());
		// 查询任务实例
		List<Task> taskList = taskService.createTaskQuery()
				.processDefinitionId(processDefinition.getId()).list();
		Assert.assertNotNull(taskList == null);
		Assert.assertTrue(taskList.size() > 0);
		for (Task task : taskList) {
			System.out.println("task name is " + task.getName()
					+ " ,task key is " + task.getTaskDefinitionKey());
		}
	}

	public InputStream readXmlFile() throws IOException {
		String filePath = "holidayRequest.bpmn";
		return Class.class.getClass().getResource("/" + filePath).openStream();
	}
}
