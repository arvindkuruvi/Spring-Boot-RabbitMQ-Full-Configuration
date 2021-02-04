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
import org.springframework.context.annotation.Primary;

@Configuration
public class RideRequestMQConnectionConfig {

	// mq primary connection
    @Bean(name = "rideRequestConnectionFactory")
    @Primary
    public CachingConnectionFactory publicConnectionFactory(
            @Value("${spring.rabbitmq.riderequest.host}") String host,
            @Value("${spring.rabbitmq.riderequest.port}") int port,
            @Value("${spring.rabbitmq.riderequest.username}") String username,
            @Value("${spring.rabbitmq.riderequest.password}") String password
          ) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        System.out.println("connectionFactory for rideRequest:"+connectionFactory);
        return connectionFactory;
    }

    @Bean(name = "rideRequestRabbitTemplate")
    @Primary
    public RabbitTemplate publicRabbitTemplate(
            @Qualifier("rideRequestConnectionFactory") ConnectionFactory connectionFactory
           ) {
        RabbitTemplate v1RabbitTemplate = new RabbitTemplate(connectionFactory);
        v1RabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return v1RabbitTemplate;
    }

    @Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

    
    
    @Bean(name = "rideRequestContainerFactory")
    @Primary
    public SimpleRabbitListenerContainerFactory insMessageListenerContainer(
            @Qualifier("rideRequestConnectionFactory") ConnectionFactory connectionFactory
           ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean(name = "rideRequestRabbitAdmin")
    @Primary
    public RabbitAdmin publicRabbitAdmin(
            @Qualifier("rideRequestConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
}
