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
public class Recv2 {
    private static final String QUEUE_NAME = "test_work_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取频道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 定义一个消费者
        Consumer consumer = new DefaultConsumer(channel) {
            // 消息到达触发这个方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("[2] Recv msg:" + msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    {
                        System.out.println("[2] done");
                    }
                }
            }
        };
        boolean autoAck = true;
        // 监听队列
        channel.basicConsume(QUEUE_NAME,autoAck, consumer);
    }
}
