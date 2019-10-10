package demo1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

/**
 * describe 业务逻辑处理器
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName MinaServerHandler.java
 * @createTime 2019年10月10日 15:05:00
 */
public class MinaServerHandler extends IoHandlerAdapter {
    private static final Logger logger = LogManager.getLogger(MinaServerHandler.class);

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        logger.info("服务端与客户端创建连接...");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        logger.info("服务端与客户端连接打开...");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        logger.info("服务端与客户端连接关闭");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        logger.info(" 服务端进入空闲状态");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        logger.info(" 服务端发生异常", cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String msg = message.toString();
        logger.info("服务端接收到数据为：" + msg);
        if ("bye".equals(msg)) { // 服务端断开连接的条件
            session.closeNow();
        }
        Date date = new Date();
        session.write(date);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        logger.info(" 服务端发送消息成功");
    }


    @Override
    public void inputClosed(IoSession session) throws Exception {
       logger.info("输入关闭");
    }
}
