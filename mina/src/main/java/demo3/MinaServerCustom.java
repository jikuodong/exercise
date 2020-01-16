package demo3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @ProjectName: mina
 * @Package: demo3
 * @ClassName: MinaServerCustom
 * @Author: JKD
 * @Description: 使用自定义协议的服务端
 * @Date: 2019/11/4 15:24
 * @Version: 1.0
 */
public class MinaServerCustom {
    private static  final Logger logger = LogManager.getLogger(MinaServerCustom.class);

    // 端口

    private static final int MINA_PORT = 7080;

    public static void main(String[] args) {
        IoAcceptor acceptor;

        try {
            // 创建一个非阻塞的服务端server
            acceptor = new NioSocketAcceptor();
            // 设置编码过滤器（自定义）
            acceptor.getFilterChain().addLast("mycoder", new ProtocolCodecFilter(
                                                new CustomProtocolCodecFactory(
                                                        Charset.forName("UTF-8"))));
            // 设置缓冲区大小
            acceptor.getSessionConfig().setReadBufferSize(102400);
            // 设置读写空闲时间
            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
            // 绑定handler
            acceptor.setHandler(new MyServerHandler());
            // 绑定端口
            acceptor.bind(new InetSocketAddress(MINA_PORT));
            logger.info("创建Mina服务端成功，端口：" + MINA_PORT);
        } catch (IOException e) {
            logger.error("创建Mina服务端出错：" + e.getMessage());
        }
    }
}

// 类继承handler
class MyServerHandler extends IoHandlerAdapter {
    private static final Logger logger = LogManager.getLogger(MyServerHandler.class);

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        CustomPack pack = (CustomPack) message;
        logger.info("服务端接收消息成功：" + pack);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        CustomPack pack = (CustomPack) message;
        logger.info("服务端发送消息成功：" + pack);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
      cause.printStackTrace();
      logger.error("服务端处理消息异常：" + cause);
    }
}
