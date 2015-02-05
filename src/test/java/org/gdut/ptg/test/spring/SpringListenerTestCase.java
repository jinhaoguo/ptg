package org.gdut.ptg.test.spring;

import org.gdut.ptg.test.BaseTestCase;
import org.gdut.ptg.test.spring.event.SimpleEvent;
import org.gdut.ptg.util.AppUtil;
import org.junit.Test;

public class SpringListenerTestCase extends BaseTestCase {

	@Test
	public void testPublishEvent(){
		SimpleEvent event = new SimpleEvent("自已定义");
		AppUtil.publishEvent(event);
	}
}
