package com.github.alexeybuzdin.capitalize;

import org.springframework.amqp.rabbit.connection.*;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.context.annotation.*;

@Configuration
@ImportResource({"classpath:context.xml"})
public class CapitalizeConfiguration {

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setExchange("capitalize");
		template.setRoutingKey("capitalize.out");
		return template;
	}
}
