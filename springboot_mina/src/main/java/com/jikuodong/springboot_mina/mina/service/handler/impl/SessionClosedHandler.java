package com.jikuodong.springboot_mina.mina.service.handler.impl;

import com.jikuodong.springboot_mina.mina.service.handler.RequestHandler;
import com.jikuodong.springboot_mina.mina.service.model.Message;
import com.jikuodong.springboot_mina.mina.service.model.ReplyBody;
import com.jikuodong.springboot_mina.mina.service.model.SentBody;
import com.jikuodong.springboot_mina.mina.service.session.PcmSession;
import com.jikuodong.springboot_mina.mina.service.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service.handler.impl
 * @ClassName: SessionClosedHandler
 * @Author: JKD
 * @Description: 会话关闭处理
 * @Date: 2019/11/12 10:45
 * @Version: 1.0
 */
public class SessionClosedHandler implements RequestHandler {

    @Autowired
    private SessionManager sessionManager;
    /**
     * 逻辑处理方法
     * @param session
     * @param sent
     * @return
     */
    @Override
    public ReplyBody process(PcmSession session, SentBody sent) {
        ReplyBody rb = new ReplyBody();
        if (session.getAttribute(Message.SESSION_KEY) == null) {
            return null;
        }
        // 在管理类的map中移除
        String account = session.getAttribute(Message.SESSION_KEY).toString();
        sessionManager.removeSession(account);
        return null;
    }
}
