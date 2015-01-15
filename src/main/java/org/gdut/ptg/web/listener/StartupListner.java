package org.gdut.ptg.web.listener;

import javax.servlet.ServletContextEvent;

import org.gdut.ptg.util.AppUtil;
import org.springframework.web.context.ContextLoaderListener;

/**
 * Spring启动监听器。<br/>
 * 用于注入servletContext和applicationContext。
 * <pre>
 * 在webxml配置如下：
 * &lt;listener>
 *       &lt;listener-class>org.gdut.ptg.web.listener.StartupListner&lt;/listener-class>
 *   &lt;/listener>
 *  <pre>
 *
 */
public class StartupListner extends ContextLoaderListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		AppUtil.init(event.getServletContext());
	}
}
