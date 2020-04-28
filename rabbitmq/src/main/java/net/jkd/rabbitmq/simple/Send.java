package net.jkd.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import net.jkd.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @projectName: exercise
 * @package: net.jkd.rabbitmq.simple
 * @className: Send
 * @author: JKD
 * @description: ������
 * @date: 2020/4/28 15:52
 * @version: 1.0
 */
public class Send {
    private static final String QUEUE_NAME = "test_simple_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        // ��ȡһ������
        Connection connection = ConnectionUtils.getConnection();
        // �������л�ȡһ��ͨ��
        Channel channel = connection.createChannel();
        // ������������
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String msg = "hello simple !";
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        System.out.println("--send msg:" + msg);
        channel.close();
        connection.close();


    }
}
