package demo1;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * describe 创建一个简单的服务端程序
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName MinaServer.java
 * @createTime 2019年10月09日 17:04:00
 */
public class MinaServer {
    private static final Logger logger = LogManager.getLogger(MinaServer.class);
    private  static final int PORT = 3005;

    public static void main(String[] args) {
        // 创建连接
        IoAcceptor acceptor = null;
        try {
            // 创建一个非阻塞的server端的Socket
            acceptor= new NioSocketAcceptor();
            // 设置过滤器（使用Mina提供的文本换行符编辑解码器）
            acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter( // 添加信息过滤器
                    new TextLineCodecFactory(
                            Charset.forName("UTF-8"),
                            LineDelimiter.WINDOWS.getValue(),
                            LineDelimiter.WINDOWS.getValue()
                            )
                    )
            );
            // 设置读取数据的缓冲区大小
            acceptor.getSessionConfig().setReadBufferSize(2048);
            // 读写通道10秒内无操作进入空闲状态
            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
            // 绑定逻辑处理器
            acceptor.setHandler(new MinaServerHandler()); // 添加业务处理
            // 绑定端口
            acceptor.bind(new InetSocketAddress(PORT));
            logger.info("服务端启动成功...  端口号为：" + PORT);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("服务端启动出错-----" + e);
        }
    }
}
