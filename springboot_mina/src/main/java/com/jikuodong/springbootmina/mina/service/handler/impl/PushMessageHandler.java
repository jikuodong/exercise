package com.jikuodong.springbootmina.mina.service.handler.impl;

import com.jikuodong.springbootmina.mina.service.handler.RequestHandler;
import com.jikuodong.springbootmina.mina.service.model.Message;
import com.jikuodong.springbootmina.mina.service.model.ReplyBody;
import com.jikuodong.springbootmina.mina.service.model.SentBody;
import com.jikuodong.springbootmina.mina.service.session.PcmSession;
import com.jikuodong.springbootmina.mina.service.session.SessionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service.handler.impl
 * @ClassName: PushMessageHandler
 * @Author: JKD
 * @Description: 推送消息的handler
 * @Date: 2019/11/12 9:09
 * @Version: 1.0
 */
public class PushMessageHandler implements RequestHandler {
    private final Logger logger = LogManager.getLogger(PushMessageHandler.class);

    // 获取会话管理类
    @Autowired
    private SessionManager sessionManager;

    @Override
    public ReplyBody process(PcmSession session, SentBody sent) {
        ReplyBody replyBody = new ReplyBody();
        // 获取绑定的账户
        String account = sent.getData().get(Message.SESSION_KEY);
        // 获取会话
        PcmSession ios = sessionManager.getSession(account);
        if (ios != null) {
            sent.remove(Message.SESSION_KEY);
            replyBody.setKey(sent.getKey());
            replyBody.setMessage("推送的消息");
            replyBody.setData(sent.getData());
            replyBody.setCode(Message.ReturnCode.CODE_200);
            ios.write(replyBody.toJson());
            logger.info(">>>>>>>>>>>>>>>>>> 服务器发送消息成功，接收用户：" + session.getAccount() + ">>>>>>>>>>>>>>>>>>");
        } else {
            replyBody.setCode(Message.ReturnCode.CODE_500);
            replyBody.setMessage("Mina push message fail");
        }
        return replyBody;
    }
}
