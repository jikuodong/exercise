package demo2;

import org.apache.mina.core.future.ConnectFuture;
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
 * @Package: demo2
 * @ClassName: ProtocalClient
 * @Author: JKD
 * @Description: 客户端
 * @Date: 2019/11/1 14:13
 * @version: 1.0
 */
public class ProtocalClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 7080;
    static long counter = 0;
    final static  int fil = 100;
    static long start = 0;
    public static void main(String[] args) {
        start= System.currentTimeMillis();
        IoConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("coderc", new ProtocolCodecFilter(new ProtocalFactory(Charset.forName("UTF-8"))));
//        connector.getFilterChain().addFirst("filter", new MyClientFilter());
        connector.getSessionConfig().setReadBufferSize(1024);
        connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        connector.setHandler(new MyHandlerClient());
        ConnectFuture connectFuture = connector.connect(new InetSocketAddress(HOST, PORT));
        connectFuture.addListener(new IoFutureListener<ConnectFuture>() {
            @Override
            public void operationComplete(ConnectFuture future) {
                if (future.isConnected()) {
                    IoSession session = future.getSession();
                    sendData(session);
                }
            }
        });
    }
    public static void sendData(IoSession ioSession) {
        for (int i = 0; i < fil; i++) {
            String content = "watchmen:" +i;
            ProtocalPack pack = new ProtocalPack((byte) i, content);
            ioSession.write(pack);
            System.out.println("客户端发送数据：" + pack);
        }
    }
}
class MyHandlerClient extends IoHandlerAdapter{

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        ProtocalPack pack = (ProtocalPack) message;
        System.out.println("client->"+ pack);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        if (status== IdleStatus.READER_IDLE){
            session.closeNow();
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("客户端发送消息："+ message);
    }
}
