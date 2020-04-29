package net.jkd.rabbitmq.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import net.jkd.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @projectName: exercise
 * @package: net.jkd.rabbitmq.pb
 * @className: Send
 * @author: JKD
 * @description: 生产者
 * @date: 2020/4/29 14:21
 * @version: 1.0
 */
public class Send {
    private static  final  String EXCHANGE_NAME = "test_exchange_fanout";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        // 声明交换机
        // 分发
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        // 发送消息
        String msg = "hello ps";

        channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
        System.out.println("Send : " + msg);
        channel.close();
        connection.close();
    }
}
