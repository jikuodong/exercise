package net.jkd.rabbitmq.work;

import com.rabbitmq.client.*;
import net.jkd.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @projectName: exercise
 * @package: net.jkd.rabbitmq.work
 * @className: Recv1
 * @author: JKD
 * @description: ������
 * @date: 2020/4/28 17:16
 * @version: 1.0
 */
public class Recv1 {
    private static final String QUEUE_NAME = "test_work_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        // ��ȡ����
        Connection connection = ConnectionUtils.getConnection();
        // ��ȡƵ��
        Channel channel = connection.createChannel();
        // ��������
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // ����һ��������
        Consumer consumer = new DefaultConsumer(channel) {
            // ��Ϣ���ﴥ���������
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("[1] Recv msg:" + msg);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    {
                        System.out.println("[1] done");
                    }
                }
            }
        };
        boolean autoAck = true;
        // ��������
        channel.basicConsume(QUEUE_NAME,autoAck, consumer);
    }
}
