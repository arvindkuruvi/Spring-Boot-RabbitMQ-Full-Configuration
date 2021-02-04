package com.mindweaver.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.mindweaver.payload.RideRequestToMq;

@Component
public class MessageSender {
	
	private static final Logger log = LoggerFactory.getLogger(MessageSender.class);

	public void sendMessage(RabbitTemplate rideRequestRabbitTemplate, String exchange, String routingKey,
			RideRequestToMq data) {
		log.info("Sending message to the queue using routingKey {}. Message= {}", routingKey, data);
		log.info("Sending message to the queue using routingKey {}. Message= {}" + routingKey
				+ "exchange name" + exchange);
		rideRequestRabbitTemplate.convertAndSend(exchange, routingKey, data);
		log.info("The message has been sent to the queue.");
		
	}

}
