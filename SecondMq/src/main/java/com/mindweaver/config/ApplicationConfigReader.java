package com.mindweaver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfigReader {
	
	@Value("${app2.exchange.name}")
	private String app2Exchange;
	
	@Value("${app2.queue.name}")
	private String app2Queue;
	
	@Value("${app2.routing.key}")
	private String app2RoutingKey;

	/* ---for AutomaticAssignmentMQ */
	@Value("${automaticassignmentmq.exchange.name}")
	private String automaticAssignmentMqExchange;
	
	@Value("${automaticassignmentmq.queue.name}")
	private String automaticAssignmentMqQueue;
	
	@Value("${automaticassignmentmq.routing.key}")
	private String automaticAssignmentMqRoutingKey;
	

	public String getApp2Exchange() {
		return app2Exchange;
	}

	public void setApp2Exchange(String app2Exchange) {
		this.app2Exchange = app2Exchange;
	}

	public String getApp2Queue() {
		return app2Queue;
	}

	public void setApp2Queue(String app2Queue) {
		this.app2Queue = app2Queue;
	}

	public String getApp2RoutingKey() {
		return app2RoutingKey;
	}

	public void setApp2RoutingKey(String app2RoutingKey) {
		this.app2RoutingKey = app2RoutingKey;
	}

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
