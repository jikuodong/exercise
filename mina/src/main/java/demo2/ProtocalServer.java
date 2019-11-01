package demo2;

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
 * @Package: demo2
 * @ClassName: ProtocalServer
 * @Author: JKD
 * @Description: 服务端
 * @Date: 2019/11/1 13:59
 * @Version: 1.0
 */
public class ProtocalServer {
    private static final int port = 7080;

    public static void main(String[] args) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("coderc", new ProtocolCodecFilter(new ProtocalFactory(Charset.forName("UTF-8"))));
        acceptor.getSessionConfig().setReadBufferSize(1024);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        acceptor.setHandler(new MyHandler());
        acceptor.bind(new InetSocketAddress(port));
        System.out.println("server start.....");
    }

}
class MyHandler extends IoHandlerAdapter {

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("server->sessionIdle");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("server->exceptionCaught");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("server->messageReceived");
        ProtocalPack pack = (ProtocalPack) message;
        System.out.println("服务端接收：" + pack);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("server->messageSent");
    }
}
