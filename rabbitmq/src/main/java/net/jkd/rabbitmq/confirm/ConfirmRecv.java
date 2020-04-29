package net.jkd.rabbitmq.confirm;

import com.rabbitmq.client.*;
import net.jkd.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @projectName: exercise
 * @package: net.jkd.rabbitmq.tx
 * @className: TxRecv
 * @author: JKD
 * @description:
 * @date: 2020/4/29 17:04
 * @version: 1.0
 */
public class ConfirmRecv {
    private static final String QUEUE_NAME = "test_queue_confirm1";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("recv[confirm] msg:" + new String (body, "utf-8"));
            }
        });
    }
}
