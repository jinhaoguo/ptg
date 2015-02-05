package org.gdut.ptg.test.spring.event;

import org.springframework.context.ApplicationEvent;

public class SimpleEvent extends ApplicationEvent {
	
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 还可以加入自定义的参数 
	 */
	private String description;

	public SimpleEvent(String source) {
		super(source);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
