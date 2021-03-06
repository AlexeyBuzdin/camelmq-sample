package com.github.alexeybuzdin.capitalize;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.beans.factory.annotation.*;

import java.nio.charset.*;

public class CapitalizeListener implements MessageListener {

    @Autowired
    RabbitTemplate capitalizeTemplate;

    @Override
    public void onMessage(Message message) {
        try {
            String messageStr = new String(message.getBody(), StandardCharsets.UTF_8);
            System.out.println("Received: " + messageStr);
            capitalizeTemplate.convertAndSend(messageStr.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
