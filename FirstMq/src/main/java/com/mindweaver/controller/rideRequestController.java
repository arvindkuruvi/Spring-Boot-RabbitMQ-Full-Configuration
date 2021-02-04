package com.mindweaver.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindweaver.config.ApplicationConfigReader;
import com.mindweaver.payload.RideRequestToMq;

@RestController
@RequestMapping("/")
public class rideRequestController {
	
	private static final Logger logger = LoggerFactory.getLogger(rideRequestController.class);
	
	@Resource(name = "rideRequestRabbitTemplate")
    private RabbitTemplate rideRequestRabbitTemplate;
	
	@Autowired
	MessageSender messageSender;
	
	
private com.mindweaver.config.ApplicationConfigReader applicationConfig;
	
	public ApplicationConfigReader getApplicationConfig() {
		return applicationConfig;
	}

	@Autowired
	public void setApplicationConfig(com.mindweaver.config.ApplicationConfigReader applicationConfig) {
		this.applicationConfig = applicationConfig;
	}

	
	
	@GetMapping("/mq")
	private String addToQueue() {
		System.out.println("INside First Queue");
		String exchange = getApplicationConfig().getApp2Exchange();
		String routingKey = getApplicationConfig().getApp2RoutingKey();
		/* Sending to Message Queue */
		try {
			RideRequestToMq mq= new RideRequestToMq();
			mq.setRideRequestId(1L);
			mq.setBusinessOwnerId("2");
			messageSender.sendMessage(rideRequestRabbitTemplate, exchange, routingKey, mq);
			logger.info("Message in queue");
			return "IN_QUEUE";
		} catch (Exception ex) {
			logger.error("Exception occurred while sending message to the queue. Exception= {}", ex);
			return "MESSAGE_QUEUE_SEND_ERROR";
		}
	}

}
