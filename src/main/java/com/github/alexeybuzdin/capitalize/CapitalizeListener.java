package com.github.alexeybuzdin.capitalize;

import org.springframework.amqp.rabbit.core.*;
import org.springframework.beans.factory.annotation.*;

public class CapitalizeListener {

    @Autowired
    RabbitTemplate template;

    public void listen(String foo) {
        try {
            System.out.println("Received: " + foo);
            template.convertAndSend(foo.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
