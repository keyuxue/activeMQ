package com.service;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ConsumerController {
    public static final String ACTIVEMQ_URL = "tcp://192.168.252.128:61616";
    public static final String QUEUE_NAME = "QUEUE_NAME01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        /*   Queue queue = session.createQueue("QUEUE_NAME");*/
        Queue queue = session.createQueue("QUEUE_NAME");
        MessageConsumer messageConsumer = session.createConsumer(queue);
        while (true) {
            TextMessage textMessage = (TextMessage) messageConsumer.receive();
            if (null != textMessage) {
                System.out.println("********消费者接受到消息" + textMessage.getText());
            } else {
                break;
            }
        }
        messageConsumer.close();
        session.close();
        connection.close();

    }
}
