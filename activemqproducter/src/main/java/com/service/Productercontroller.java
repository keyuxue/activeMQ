package com.service;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Productercontroller {
    public static final String ACTIVEMQ_URL = "tcp://192.168.252.128:61616";
    public static final String QUEUE_NAME = "QUEUE_NAME01";

    public static void main(String[] args) throws JMSException, JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("QUEUE_NAME");
        MessageProducer messageProducer = session.createProducer(queue);
        for (int i = 0; i < 3; i++) {
            TextMessage textMessage = session.createTextMessage("msg---" + i);
            messageProducer.send(textMessage);
        }
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("*********消息发布带MQ完成");

    }
}
