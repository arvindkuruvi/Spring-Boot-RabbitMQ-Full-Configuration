package com.mindweaver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfigReader {
	
	
	/* ---for AutomaticAssignmentMQ */
	@Value("${automaticassignmentmq.exchange.name}")
	private String automaticAssignmentMqExchange;
	
	@Value("${automaticassignmentmq.queue.name}")
	private String automaticAssignmentMqQueue;
	
	@Value("${automaticassignmentmq.routing.key}")
	private String automaticAssignmentMqRoutingKey;
	

	public String getAutomaticAssignmentMqExchange() {
		return automaticAssignmentMqExchange;
	}

	public void setAutomaticAssignmentMqExchange(String automaticAssignmentMqExchange) {
		this.automaticAssignmentMqExchange = automaticAssignmentMqExchange;
	}

	public String getAutomaticAssignmentMqQueue() {
		return automaticAssignmentMqQueue;
	}

	public void setAutomaticAssignmentMqQueue(String automaticAssignmentMqQueue) {
		this.automaticAssignmentMqQueue = automaticAssignmentMqQueue;
	}

	public String getAutomaticAssignmentMqRoutingKey() {
		return automaticAssignmentMqRoutingKey;
	}

	public void setAutomaticAssignmentMqRoutingKey(String automaticAssignmentMqRoutingKey) {
		this.automaticAssignmentMqRoutingKey = automaticAssignmentMqRoutingKey;
	}

	

	
}
