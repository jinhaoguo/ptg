package org.gdut.ptg.test.spring.listener;

import org.gdut.ptg.test.spring.event.SimpleEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

/**
 * <pre> 
 * 描述：监听器，需要为spring所管理的bean
 * 作者：guojh
 * 日期:2015-1-25-下午10:14:05
 * </pre>
 */
@Service
public class SimpleListener implements ApplicationListener<SimpleEvent>,Ordered{

	public void onApplicationEvent(SimpleEvent event) {
		System.out.println("监听到SimpleEvent事件，传回来的值为："+event.getSource());
	}

	public int getOrder() {
		return 0;
	}

}
