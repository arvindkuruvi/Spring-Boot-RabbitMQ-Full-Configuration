package com.mindweaver.config;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@EnableRabbit
@Configuration
public class RideRequestRabbitMQConfig implements RabbitListenerConfigurer {
	
	@Resource(name = "rideRequestRabbitAdmin")
    private RabbitAdmin rideRequestRabbitAdmin;
	
	@Resource(name = "automaticAssignmentRabbitAdmin")
    private RabbitAdmin automaticAssignmentRabbitAdmin;
	
	@Autowired
	private ApplicationConfigReader applicationConfig;

	public ApplicationConfigReader getApplicationConfig() {
		return applicationConfig;
	}

	public void setApplicationConfig(ApplicationConfigReader applicationConfig) {
		this.applicationConfig = applicationConfig;
	}
	
	/* This bean is to read the properties file configs */
	@Bean
	public ApplicationConfigReader applicationConfig() {
		return new ApplicationConfigReader();
	}
	
	@Bean
	public TopicExchange getApp2Exchange() {
		return new TopicExchange(getApplicationConfig().getApp2Exchange());
	}

	/* Creating a bean for the Message queue */
	@Bean
	public Queue getApp2Queue() {
		return new Queue(getApplicationConfig().getApp2Queue());
	}
	
	/* Creating a bean for the Message queue Exchange */
	@Bean
	public TopicExchange getAutomaticAssignmentMqExchange() {
		return new TopicExchange(getApplicationConfig().getAutomaticAssignmentMqExchange());
	}

	/* Creating a bean for the Message queue */
	@Bean
	public Queue getAutomaticAssignmentMqQueue() {
		return new Queue(getApplicationConfig().getAutomaticAssignmentMqQueue());
	}
	
	
	

	
	 public RideRequestRabbitMQConfig() {
	}

	@PostConstruct
	    public void RabbitInit() {
		 System.out.println("POST CONSTRUCT of rideRequest");
		 rideRequestRabbitAdmin.declareExchange(new TopicExchange(getApplicationConfig().getApp2Exchange()));
		 rideRequestRabbitAdmin.declareQueue(new Queue(getApplicationConfig().getApp2Queue()));
		 rideRequestRabbitAdmin.declareBinding(
	                BindingBuilder
	                        .bind(getApp2Queue())        //Create Queue Directly
	                        .to(getApp2Exchange())    //Create switches directly to establish associations
	                        .with(getApplicationConfig().getApp2RoutingKey()));    //Specify Routing Key
	 }
	 @PostConstruct
	    public void RabbitInit2() {
		 
		 System.out.println("POST CONSTRUCT of automaticAssignment");
		 automaticAssignmentRabbitAdmin.declareExchange(new TopicExchange(getApplicationConfig().getAutomaticAssignmentMqExchange()));
		 automaticAssignmentRabbitAdmin.declareQueue(new Queue(getApplicationConfig().getAutomaticAssignmentMqQueue()));
		 automaticAssignmentRabbitAdmin.declareBinding(
	                BindingBuilder
	                        .bind(getAutomaticAssignmentMqQueue())        //Create Queue Directly
	                        .to(getAutomaticAssignmentMqExchange())    //Create switches directly to establish associations
	                        .with(getApplicationConfig().getAutomaticAssignmentMqRoutingKey()));    //Specify Routing Key
	    }

	

	 @Override
		public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
			registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
			
		}
		
		@Bean
		public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
			DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
			factory.setMessageConverter(consumerJackson2MessageConverter());
			return factory;
		}
		
		@Bean
		public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
			return new MappingJackson2MessageConverter();
		}
}
