package com.github.alexeybuzdin.custom;

import com.rabbitmq.client.*;

public class Send {

    public static void main(String args[]) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.99.100");
        factory.setPort(32771);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("capitalize", true, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", "capitalize", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();

//        CamelContext context = new DefaultCamelContext();
//
//        context.addRoutes(new RouteBuilder() {
//            public void configure() {
//                from("timer://simple?period=1000").setBody().simple("Test message: ${id}")
//                    .to("rabbitmq://192.168.99.100:32771/capitalize?" +
//                            "routingKey=in");
//            }
//        });
//        context.start();
    }

}
