package com.github.alexeybuzdin.helloworld;

import org.apache.camel.*;
import org.apache.camel.builder.*;
import org.apache.camel.impl.*;

public class Recv {

    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("rabbitmq://192.168.99.100:32771/new.exchange?" +
                        "autoDelete=false&" +
                        "routingKey=camel&" +
                        "queue=hello")
                        .to("stream:out");
            }
        });
        context.start();
    }
}
