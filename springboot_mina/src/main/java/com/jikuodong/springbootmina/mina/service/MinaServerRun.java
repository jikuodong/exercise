package com.jikuodong.springbootmina.mina.service;

import com.jikuodong.springbootmina.util.Const;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service
 * @ClassName: MinaServerRun
 * @Author: JKD
 * @Description: 创建mina连接
 * @Date: 2019/11/12 16:53
 * @Version: 1.0
 */
@Component
public class MinaServerRun implements CommandLineRunner {
    private static final Logger logger = LogManager.getLogger(MinaServerRun.class);

    @Autowired
    private NioSocketAcceptor acceptor;

    public MinaServerRun(NioSocketAcceptor acceptor) {
        this.acceptor = acceptor;
    }
    @Override
    public void run(String... args) throws Exception {
        acceptor.bind(new InetSocketAddress(Const.PORT));
        logger.info("---springboot mina server start---");
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                logger.info("---server acceptor unbind---");
                acceptor.unbind();
                logger.info("---server acceptor dispose---");
                acceptor.dispose();
            }
        });
    }
}
