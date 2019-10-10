package demo1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * describe
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName MinaClientHandler.java
 * @createTime 2019年10月10日 16:43:00
 */
public class MinaClientHandler extends IoHandlerAdapter {
    private static Logger logger = LogManager.getLogger(MinaClientHandler.class);

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
       logger.error("客户端发生异常", cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String msg = message.toString();
        logger.info("客户端接收到数据：" + msg);
    }

}
