package demo3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @ProjectName: mina
 * @Package: demo3
 * @ClassName: MinaClientCustom
 * @Author: JKD
 * @Description: 使用自定义协议的客户端
 * @Date: 2019/11/4 16:08
 * @Version: 1.0
 */
public class MinaClientCustom {
    private static final Logger logger = LogManager.getLogger(MinaClientCustom.class);

    private static final String MINA_HOST = "127.0.0.1";
    private static final int MINA_PORT = 7080;
    private static long start = 0;

    public static void main(String[] args) {
        // 获取当前系统时间戳
        start = System.currentTimeMillis();
        // 创建一个非阻塞的客户端
        IoConnector connector = new NioSocketConnector();
        // 设置编码过滤器
        connector.getFilterChain().addLast("mycoder",
                new ProtocolCodecFilter(
                        new CustomProtocolCodecFactory(
                                Charset.forName("UTF-8"))));
        // 设置缓冲区大小
        connector.getSessionConfig().setReadBufferSize(102400);
        // 设置空闲时间
        connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        // 绑定逻辑处理Handler
        connector.setHandler(new MyClientHandler());
        // 创建连接
        final ConnectFuture future = connector.connect(new InetSocketAddress(MINA_HOST, MINA_PORT));
        // 这里采用监听方式获取session
        future.addListener(new IoFutureListener<IoFuture>() {
            // 当连接创建完成
            @Override
            public void operationComplete(IoFuture ioFuture) {
                IoSession session = future.getSession();
                sendData(session);
            }
        });
    }

    // 发送数据的方法
    public static  void sendData(IoSession session) {
        logger.info("----------------测试数据准备发送-----------------");
        // 模拟发送100次数据
        for (int i = 0; i < 100; i++) {
            String content = "测试数据：" + i;
            CustomPack pack = new CustomPack((byte) i, content);
            session.write(pack);
        }
        logger.info("----------------测试数据发送完毕-------------------");
    }
}

class MyClientHandler extends IoHandlerAdapter {
    private static final Logger logger = LogManager.getLogger(MyClientHandler.class);

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        CustomPack pack = (CustomPack) message;
        logger.info("客户端接收消息成功：" + pack);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        CustomPack pack = (CustomPack) message;
        logger.info("客户端发送消息成功：" + pack);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        logger.info("客户端处理消息异常：" + cause.getMessage());
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        // 空闲时，关闭session
        if (status == IdleStatus.BOTH_IDLE) {
            logger.info("session进入空闲，准备关闭session");
            session.closeNow();
        }
    }
}