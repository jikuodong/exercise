package net.jkd.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @projectName: exercise
 * @package: net.jkd.rabbitmq.util
 * @className: ConnectionUtils
 * @author: JKD
 * @description: ��ȡMQ������
 * @date: 2020/4/28 15:39
 * @version: 1.0
 */
public class ConnectionUtils {


    /**
     * @description ��ȡMQ������
     *
     * @method getConnection
     * @author JKD
     * @return com.rabbitmq.client.Connection
     * @Exception
     * @date 2020/4/28 15:41
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        // ����һ�����ӹ���
        ConnectionFactory factory = new ConnectionFactory();

        // ���÷����ַ
        factory.setHost("127.0.0.1");

        // AMQP
        factory.setPort(5672);

        // vhost
        factory.setVirtualHost("/");

        // �û���
        factory.setUsername("admin");

        // ����
        factory.setPassword("123456");
        return factory.newConnection();
    }
}
