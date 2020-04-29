package net.jkd.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import net.jkd.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @projectName: exercise
 * @package: net.jkd.rabbitmq.confirm
 * @className: Send1
 * @author: JKD
 * @description: 普通模式
 * @date: 2020/4/29 17:23
 * @version: 1.0
 */
public class Send1 {
    private static final String QUEUE_NAME = "test_queue_confirm1";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        // 创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 生产者调用confirmSelect将channel设置为confirm模式。
        channel.confirmSelect();
        String msgString = "hello confirm message!";
        channel.basicPublish("", QUEUE_NAME, null, msgString.getBytes());
        if (!channel.waitForConfirms()) {
            System.out.println("message send failed!");
        } else {
            System.out.println("message send ok!");
        }
        channel.close();
        connection.close();
    }
}
