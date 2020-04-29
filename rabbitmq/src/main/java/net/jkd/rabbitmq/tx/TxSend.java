package net.jkd.rabbitmq.tx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import net.jkd.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @projectName: exercise
 * @package: net.jkd.rabbitmq.tx
 * @className: TxSend
 * @author: JKD
 * @description:
 * @date: 2020/4/29 16:59
 * @version: 1.0
 */
public class TxSend {
    private static final String QUEUE_NAME = "test_queue_tx";
    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String msgString = "hello tx message!";
        try {
            channel.txSelect();
            channel.basicPublish("", QUEUE_NAME, null, msgString.getBytes());
            System.out.println("send:" + msgString);
            channel.txCommit();
        } catch (IOException e) {
            channel.txRollback();
            System.out.println("send message txRollback");
        }
        channel.close();
        connection.close();

    }
}
