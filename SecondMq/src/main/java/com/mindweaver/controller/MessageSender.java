package com.mindweaver.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.mindweaver.payload.AutomaticAssignmentMq;

@Component
public class MessageSender {
	
	private static final Logger log = LoggerFactory.getLogger(MessageSender.class);

	public void sendMessage(RabbitTemplate automaticAssignmentRabbitTemplate, String exchange, String routingKey,
			AutomaticAssignmentMq automaticAssignmentMq) {
		log.info("Sending message to the 2nd  queue using routingKey {}. Message= {}", routingKey, automaticAssignmentMq);
		log.info("Sending message to the 2nd queue using routingKey {}. Message= {}" + routingKey
				+ "exchange name" + exchange);
		automaticAssignmentRabbitTemplate.convertAndSend(exchange, routingKey, automaticAssignmentMq);
		log.info("The message has been sent to the 2nd queue.");
		
	}

}
