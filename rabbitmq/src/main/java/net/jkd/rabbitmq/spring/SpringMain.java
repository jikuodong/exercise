package net.jkd.rabbitmq.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @projectName: exercise
 * @package: net.jkd.rabbitmq.spring
 * @className: SpringMain
 * @author: JKD
 * @description: 生产者
 * @date: 2020/5/6 11:08
 * @version: 1.0
 */
public class SpringMain {
    public static void main(String[] args) throws InterruptedException {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:context.xml");
        // RabbitMQ模板
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        // 发送消息
        template.convertAndSend("hello, world!");
        // 休眠1秒
        Thread.sleep(1000);
    }
}
