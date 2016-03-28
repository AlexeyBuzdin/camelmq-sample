package com.github.alexeybuzdin.custom;

import com.rabbitmq.client.*;

import java.io.*;

public class Recv {

    public static void main(String args[]) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.99.100");
        factory.setPort(32771);
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("capitalize", true, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println(" [x] Received '" + message + "'");
                try {
                    System.out.println(" [x] Received '" + message + "'");
                } finally {
                    System.out.println(" [x] Done");
                    channel.basicAck(envelope.getDeliveryTag(), true);
                }
            }
        };
        channel.basicConsume("capitalize", true, consumer);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//
//        CamelContext context = new DefaultCamelContext();
//
//        context.addRoutes(new RouteBuilder() {
//            public void configure() {
//                from("rabbitmq://192.168.99.100:32771/capitalize?" +
//                        "autoDelete=false&" +
//                                "routingKey=in")
//
//                .to("rabbitmq://192.168.99.100:32771/capitalize?" +
//                        "autoDelete=false&" +
//                        "routingKey=out");
//            }
//        });
//        context.start();
    }
}
