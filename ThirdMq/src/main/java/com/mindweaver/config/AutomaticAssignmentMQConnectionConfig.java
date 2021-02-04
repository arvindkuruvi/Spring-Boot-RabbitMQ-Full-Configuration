package com.mindweaver.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutomaticAssignmentMQConnectionConfig {
	
	 @Bean(name = "automaticAssignmentConnectionFactory")
	    public CachingConnectionFactory assignmentConnectionFactory(
	            @Value("${spring.rabbitmq.automaticassignmentmq.host}") String host,
	            @Value("${spring.rabbitmq.automaticassignmentmq.port}") int port,
	            @Value("${spring.rabbitmq.automaticassignmentmq.username}") String username,
	            @Value("${spring.rabbitmq.automaticassignmentmq.password}") String password
	           ) {
	        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
	        connectionFactory.setHost(host);
	        connectionFactory.setPort(port);
	        connectionFactory.setUsername(username);
	        connectionFactory.setPassword(password);
	        return connectionFactory;
	    }

	    @Bean(name = "automaticAssignmentRabbitTemplate")
	    public RabbitTemplate assignmentRabbitTemplate(
	            @Qualifier("automaticAssignmentConnectionFactory") ConnectionFactory connectionFactory
	            ) {
	        RabbitTemplate v2RabbitTemplate = new RabbitTemplate(connectionFactory);
	        v2RabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
	        return v2RabbitTemplate;
	    }
	    
	    @Bean
		public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
			return new Jackson2JsonMessageConverter();
		}

	    @Bean(name = "automaticAssignmentContainerFactory")
	    public SimpleRabbitListenerContainerFactory assignmentFactory(
	            @Qualifier("automaticAssignmentConnectionFactory") ConnectionFactory connectionFactory
	    ) {
	        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
	        factory.setConnectionFactory(connectionFactory);
	        return factory;
	    }

	    @Bean(name = "automaticAssignmentRabbitAdmin")
	    public RabbitAdmin assignmentRabbitAdmin(
	            @Qualifier("automaticAssignmentConnectionFactory") ConnectionFactory connectionFactory) {
	        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
	        rabbitAdmin.setAutoStartup(true);
	        return rabbitAdmin;
	    }


}
