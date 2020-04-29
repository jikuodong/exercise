package net.jkd.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import net.jkd.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @projectName: exercise
 * @package: net.jkd.rabbitmq.topic
 * @className: Send
 * @author: JKD
 * @description:
 * @date: 2020/4/29 16:33
 * @version: 1.0
 */
public class Send {
    private static  final  String EXCHANGE_NAME = "test_exchange_topic";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String msgString = "goods......";
        channel.basicPublish(EXCHANGE_NAME, "goods.add", null, msgString.getBytes());
        System.out.println("----send "+msgString);
        channel.close();
        connection.close();
    }
}
