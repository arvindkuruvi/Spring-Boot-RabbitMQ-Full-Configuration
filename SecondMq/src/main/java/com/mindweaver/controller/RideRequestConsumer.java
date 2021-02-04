package com.mindweaver.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.mindweaver.config.ApplicationConfigReader;
import com.mindweaver.config.ApplicationConstant;
import com.mindweaver.payload.AutomaticAssignmentMq;
import com.mindweaver.payload.RideRequestToMq;

@Component
public class RideRequestConsumer {

	@Resource(name = "automaticAssignmentRabbitTemplate")
	private RabbitTemplate automaticAssignmentRabbitTemplate;

	private static final Logger log = LoggerFactory.getLogger(RideRequestConsumer.class);

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

	// consumer method
	@RabbitListener(queues = "${app2.queue.name}", containerFactory = "rideRequestContainerFactory")
	public void rideRequestListener(RideRequestToMq rideRequestMQ) {

		System.out.println("-----------------------------------------------------------------");
		try {
			log.info("Received message: {} from rideRequest queue.", rideRequestMQ);
			addToAutomaticAssignmentMQ();

		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				log.info("Delay...");
				try {
					Thread.sleep(ApplicationConstant.MESSAGE_RETRY_DELAY);
				} catch (InterruptedException e) {
				}
				log.info("Throwing exception so that message will be requed in the queue.");
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

	private void addToAutomaticAssignmentMQ() {
		AutomaticAssignmentMq automaticAssignmentMq = new AutomaticAssignmentMq();
		automaticAssignmentMq.setRideRequestId(1L);
		automaticAssignmentMq.setCustomerId("+919686279708");
		automaticAssignmentMq.setBusinessOwnerId("2L");
		automaticAssignmentMq.setPickUpLocationName("banshankari");
		automaticAssignmentMq.setDropOffLocationName("shivaji nagar");
		automaticAssignmentMq.setPickUpLat(12.89755);
		automaticAssignmentMq.setPickUpLong(77.23454);
		automaticAssignmentMq.setDropLat(12.34321);
		automaticAssignmentMq.setDropLong(77.12345);
		String exchange = getApplicationConfig().getAutomaticAssignmentMqExchange();
		String routingKey = getApplicationConfig().getAutomaticAssignmentMqRoutingKey();
		/* Sending to Message Queue */
		try {
			messageSender.sendMessage(automaticAssignmentRabbitTemplate, exchange, routingKey, automaticAssignmentMq);
			log.info("Message IN_QUEUE%%%%%%");
		} catch (Exception ex) {
			log.error("Exception occurred while sending message to the  2nd queue. Exception= {}", ex);
		}

	}

}
