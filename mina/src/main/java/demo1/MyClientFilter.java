package demo1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;


/**
 * describe 自定义客户端过滤器
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName MyClientFilter.java
 * @createTime 2019年10月18日 11:48:00
 */
public class MyClientFilter extends IoFilterAdapter {
    private  static final Logger logger = LogManager.getLogger(MyClientFilter.class);

    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
        logger.info("ClientFilter接收到客户端消息：" + message);
        // 传给下一个过滤器
        nextFilter.messageReceived(session,message);
    }

    @Override
    public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
        logger.info("ClientFilter接收到服务端消息：" + writeRequest.getMessage());
        // 传给下一个过滤器
        nextFilter.messageSent(session, writeRequest);
    }
}
