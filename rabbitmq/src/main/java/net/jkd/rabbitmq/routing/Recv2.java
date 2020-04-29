package net.jkd.rabbitmq.routing;

import com.rabbitmq.client.*;
import net.jkd.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @projectName: exercise
 * @package: net.jkd.rabbitmq.routing
 * @className: Recv1
 * @author: JKD
 * @description: 消费者
 * @date: 2020/4/29 15:57
 * @version: 1.0
 */
public class Recv2 {
    private static  final  String EXCHANGE_NAME = "test_exchange_direct";
    private static  final  String QUEUE_NAME = "test_queue_direct2";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false,false,false,null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME,"error");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME,"info");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME,"warning");
        channel.basicQos(1);
        // 定义一个消费者
        Consumer consumer = new DefaultConsumer(channel) {
            // 消息到达触发这个方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("[2] Recv msg:" + msg);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    {
                        System.out.println("[2] done");
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                }
            }
        };
        // 自动应答 fasle
        boolean autoAck = false;
        // 监听队列
        channel.basicConsume(QUEUE_NAME,autoAck, consumer);
    }
}
