package com.jikuodong.springbootmina.mina.service.handler.impl;

import com.jikuodong.springbootmina.mina.service.handler.RequestHandler;
import com.jikuodong.springbootmina.mina.service.model.Message;
import com.jikuodong.springbootmina.mina.service.model.ReplyBody;
import com.jikuodong.springbootmina.mina.service.model.SentBody;
import com.jikuodong.springbootmina.mina.service.session.DefaultSessionManager;
import com.jikuodong.springbootmina.mina.service.session.PcmSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springbootmina.mina.service.handler.impl
 * @ClassName: SyncInfoHandler
 * @Author: JKD
 * @Description: 同步信息处理方法
 * @Date: 2019/11/14 15:16
 * @Version: 1.0
 */
public class SyncInfoHandler implements RequestHandler {

    @Autowired
    DefaultSessionManager defaultSessionManager;

    @Override
    public ReplyBody process(PcmSession session, SentBody sent) {
        ReplyBody replyBody = new ReplyBody();
        String account = (String) session.getAttribute(Message.SESSION_KEY);
        // 获取会话管理类，获得PcmSession
        PcmSession pcmSession = defaultSessionManager.getSession(account);
        // 更新对应的扫描数量，通知观察者
        defaultSessionManager.activeNotify(pcmSession.getMessage() + "当前上传数：" + sent.get("scanNum"));
        replyBody.setCode(Message.ReturnCode.CODE_200);
        return replyBody;
    }
}
