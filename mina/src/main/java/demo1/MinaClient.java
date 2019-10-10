package demo1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * describe Mina客户端程序
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName MinaClient.java
 * @createTime 2019年10月10日 15:53:00
 */
public class MinaClient {
    private static final Logger logger = LogManager.getLogger(MinaClient.class);
    // 定义IP地址
    private static final String HOST = "127.0.0.1";
    // 定义端口
    private  static final int PORT = 3005;

    public static void main(String[] args) {
        IoSession ioSession = null;
        // 创建一个非阻塞的客户端
        IoConnector ioConnector = new NioSocketConnector();
        // 设置超时时间
        ioConnector.setConnectTimeoutMillis(30000);
        // 设置编码解码器
        ioConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                new TextLineCodecFactory(
                        Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.getValue(),
                        LineDelimiter.WINDOWS.getValue()
                )
        ));
        // 添加业务逻辑处理类, 添加业务处理
        ioConnector.setHandler(new MinaClientHandler());
        try {
            // 创建连接
            ConnectFuture future = ioConnector.connect(new InetSocketAddress(HOST, PORT));
            // 等待连接创建完成
            future.awaitUninterruptibly();
            // 获得session
            ioSession = future.getSession();
            ioSession.write("我爱你mina");
        } catch (Exception e) {
            logger.error("客户端链接异常...", e);
        }
        // 等待连接断开
        ioSession.getCloseFuture().awaitUninterruptibly();
        ioConnector.dispose();
    }
}
