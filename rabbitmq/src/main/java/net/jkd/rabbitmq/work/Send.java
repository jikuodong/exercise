package net.jkd.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import net.jkd.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @projectName: exercise
 * @package: net.jkd.rabbitmq.work
 * @className: Send
 * @author: JKD
 * @description: 生产者生产消息
 * @date: 2020/4/28 17:08
 * @version: 1.0
 */
public class Send {

    /**
     *                  |---C1
     * P -----Queue-----|
     *                  |---C2
     *
     */

    private static final String QUEUE_NAME = "test_work_queue";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 获取连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取channel
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false, null);

        for (int i = 0; i < 50; i++) {
            String msg = "hello" + i;
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            System.out.println("work queue send:" + msg);
            Thread.sleep(i*20);
        }
        channel.close();
        connection.close();
    }
}
