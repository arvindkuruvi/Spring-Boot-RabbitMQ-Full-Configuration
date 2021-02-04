package com.mindweaver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.mindweaver.config.ApplicationConstant;
import com.mindweaver.payload.AutomaticAssignmentMq;

@Component
public class Consumer {
	
	
	
	private static final Logger log = LoggerFactory.getLogger(Consumer.class);
	
	//consumer method
	@RabbitListener(queues = "${automaticassignmentmq.queue.name}", containerFactory = "automaticAssignmentContainerFactory")
	public void assignmentListener(AutomaticAssignmentMq automaticAssignmentMq) {

		System.out.println("-----------------------------------------------------------------");
		try {
			log.info("Received message: {} from Automatic Assignment queue.", automaticAssignmentMq);
			
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				log.info("Delay...");
				try {
					Thread.sleep(ApplicationConstant.MESSAGE_RETRY_DELAY);
				} catch (InterruptedException e) {
				}
				log.info("Throwing exception so that message will be requed in the 2nd queue.");
				// Note: Typically Application specific exception should be thrown below
				throw new RuntimeException();
			} else {
				throw new AmqpRejectAndDontRequeueException(ex);
			}
		} catch (Exception e) {
			log.error("Internal server error occurred in API call. Bypassing message requeue {}", e);
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
	
	


}
