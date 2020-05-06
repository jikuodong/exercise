package net.jkd.rabbitmq.spring;

/**
 * @projectName: exercise
 * @package: net.jkd.rabbitmq.spring
 * @className: MyConsumer
 * @author: JKD
 * @description: 消费者
 * @date: 2020/5/6 11:04
 * @version: 1.0
 */
public class MyConsumer {

    /**
     * 具体执行业务的方法
     * @param foo
     */
    public void listen(String foo) {
        System.out.println("消费者：" + foo);
    }
}
