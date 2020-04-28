package net.jkd.rabbitmq.simple;

import com.rabbitmq.client.*;
import net.jkd.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @projectName: exercise
 * @package: net.jkd.rabbitmq.simple
 * @className: Recv
 * @author: JKD
 * @description: �����߻�ȡ��Ϣ
 * @date: 2020/4/28 16:04
 * @version: 1.0
 */
public class Recv {
    private static final String QUEUE_NAME = "test_simple_queue";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // ��ȡ����
        Connection connection = ConnectionUtils.getConnection();
        // ����Ƶ��
        Channel channel = connection.createChannel();
        // ��������
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // ����������
        DefaultConsumer consumer = new DefaultConsumer(channel){
            // ��ȡ�������Ϣ
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msgString = new String(body, "utf-8");
                System.out.println("new api recv:" + msgString);
            }
        };
        // ��������
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

    private static void oldApi() throws IOException, TimeoutException, InterruptedException {
        // ��ȡ����
        Connection connection = ConnectionUtils.getConnection();
        // ����Ƶ��
        Channel channel = connection.createChannel();
        // ������е�������
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // ��������
        channel.basicConsume(QUEUE_NAME, consumer);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msgString = new String(delivery.getBody());
            System.out.println("[recv] msg:" + msgString);
        }
    }
}
