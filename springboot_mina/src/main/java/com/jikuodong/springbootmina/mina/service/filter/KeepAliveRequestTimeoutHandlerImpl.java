package com.jikuodong.springbootmina.mina.service.filter;

import com.jikuodong.springbootmina.mina.service.model.Message;
import com.jikuodong.springbootmina.mina.service.session.SessionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service.filter
 * @ClassName: KeepAliveRequestTimeoutHandlerImpl
 * @Author: JKD
 * @Description: 心跳超时处理器
 * @Date: 2019/11/12 14:42
 * @Version: 1.0
 */
public class KeepAliveRequestTimeoutHandlerImpl implements KeepAliveRequestTimeoutHandler {

    private static final Logger logger = LogManager.getLogger(KeepAliveRequestTimeoutHandlerImpl.class);
    @Autowired
    SessionManager sessionManager;
    /**
     * 超时最大次数
     */
    private int timeoutNum = 3;

    public KeepAliveRequestTimeoutHandlerImpl () {}

    public KeepAliveRequestTimeoutHandlerImpl (int timeoutNum) {
        this.timeoutNum = timeoutNum;
    }
    @Override
    public void keepAliveRequestTimedOut(KeepAliveFilter filter, IoSession session) throws Exception {
        int isTimeoutNum = (int)session.getAttribute(Message.TIME_OUT_NUM);
        // 没有超过最大次数，超时次数加1
        if (isTimeoutNum <=timeoutNum) {
            session.setAttribute(Message.TIME_OUT_NUM, isTimeoutNum +1 );
        } else {
            // 超过最大次数，关闭会话连接
            String account = (String) session.getAttribute(Message.SESSION_KEY);
            sessionManager.removeSession(account);
            logger.info("<<<<<<<<<<<<<<<<<<<< 终端用户：" + account + " 心跳超过三次无应答，已被关闭 <<<<<<<<<<<<<<<<<<<<");
        }

    }
}
