package demo1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

/**
 * describe 自定义服务端过滤器
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName MyServerFilter.java
 * @createTime 2019年10月28日 11:36:00
 */
public class MyServerFilter extends IoFilterAdapter {
    private  static final Logger logger = LogManager.getLogger(MyServerFilter.class);

    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
        logger.info("MyServerFilter接收到数据：" + message);
        // 传给下一个过滤器
        nextFilter.messageReceived(session,message);
    }

    @Override
    public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
        logger.info("MyServerFilter发送数据：" + writeRequest.getMessage());
        // 传给下一个过滤器
        nextFilter.messageSent(session, writeRequest);
    }
}
