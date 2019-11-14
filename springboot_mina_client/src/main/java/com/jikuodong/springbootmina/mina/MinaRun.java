package com.jikuodong.springbootmina.mina;

import com.google.gson.Gson;
import com.jikuodong.springbootmina.mina.codec.CustomPack;
import com.jikuodong.springbootmina.mina.model.Message;
import com.jikuodong.springbootmina.mina.model.SentBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @ProjectName: springboot_mina_client
 * @Package: com.jikuodong.springbootmina.mina
 * @ClassName: MinaRun
 * @Author: JKD
 * @Description: 创建mina连接
 * @Date: 2019/11/14 14:10
 * @Version: 1.0
 */

@Component
@ConfigurationProperties(prefix = "mina")
public class MinaRun implements CommandLineRunner {
    private static final Logger logger = LogManager.getLogger(MinaRun.class);

    // 定时线程池
    private static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
    private ScheduledFuture future;

    // 服务端相关
    private String host;
    private int port;
    private long pushDelay;

    // 扫描数量
    private long scanNum;

    private NioSocketConnector connector;
    private IoSession session;

    @Autowired
    public MinaRun(NioSocketConnector connector) {
        this.connector = connector;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info(">>>>>>>>>>>>>>>>>>>> 正在连接远程服务器 >>>>>>>>>>>>>>>>>>>>");
        // 设置服务器地址，端口号
        try {
            InetSocketAddress address = new InetSocketAddress(host, port);
            ConnectFuture future = connector.connect(address);
            // 获取session执行绑定方法
            future.awaitUninterruptibly();
            session = future.getSession();
            bind();
            startPush();
            logger.info("<<<<<<<<<<<<<<<<<<<< 与远程服务器连接成功 <<<<<<<<<<<<<<<<<<<<");
        } catch (Exception e) {
            logger.error("<<<<<<<<<<<<<<<<<<<< 与远程服务器连接失败 <<<<<<<<<<<<<<<<<<<<" + e.getMessage());
            e.printStackTrace();
        }
    }

    private synchronized void startPush() {
        final Runnable syscScanNumRunnable = new Runnable() {
            @Override
            public void run() {
                if (session.isActive()) {
                    // 当扫描数量发生变化时， 才发送同步信息
                    long num = 20;
                    if (scanNum != num) {
                        logger.info(">>>>>>>>>>>>>>>>>>>> 检测到新数据，即将进行同步 >>>>>>>>>>>>>>>>>>>>");
                        scanNum = num;
                        SentBody sent = new SentBody();
                        sent.put("scanNum", String.valueOf(scanNum));
                        sent.setKey(Message.SYNC_HANDLER);
                        // 转换为自定义协议
                        CustomPack pack = new CustomPack(CustomPack.REQUEST, new Gson().toJson(sent, SentBody.class));
                        session.write(pack);
                    }
                } else {
                    logger.warn("<<<<<<<<<<<<<<<<<<<< Mina连接已断开，同步线程终止运行 <<<<<<<<<<<<<<<<<<<<");
                    future.cancel(true);
                }
            }
        };
        // 设置调用的方法，初始化后第一次调用间隔，后期循环间隔，间隔单位
        future = getRefreshExecutor().scheduleAtFixedRate(
                syscScanNumRunnable,
                pushDelay,
                pushDelay,
                TimeUnit.SECONDS
        );
    }

    /**
     * 绑定服务端
     */
    private void bind(){
        SentBody sent = new SentBody();
        // 设置相关信息（需要发送的信息）
        sent.put(Message.SESSION_KEY, "UUID：1221321321321");
        // 服务端处理的key
        sent.setKey(Message.BIND_HANDLER);
        // 转换为自定义协议
        CustomPack pack = new CustomPack(CustomPack.REQUEST, new Gson().toJson(sent, SentBody.class));
        session.write(pack);
    }

    /**
     * 静态方法获取定时线程池
     */
    private static ScheduledThreadPoolExecutor getRefreshExecutor() {
        return MinaRun.scheduledThreadPoolExecutor;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getPushDelay() {
        return pushDelay;
    }

    public void setPushDelay(long pushDelay) {
        this.pushDelay = pushDelay;
    }
}
