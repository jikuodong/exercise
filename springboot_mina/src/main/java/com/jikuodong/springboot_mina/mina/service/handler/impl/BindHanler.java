package com.jikuodong.springboot_mina.mina.service.handler.impl;

import com.jikuodong.springboot_mina.mina.service.handler.RequestHandler;
import com.jikuodong.springboot_mina.mina.service.model.Message;
import com.jikuodong.springboot_mina.mina.service.model.ReplyBody;
import com.jikuodong.springboot_mina.mina.service.model.SentBody;
import com.jikuodong.springboot_mina.mina.service.session.DefaultSessionManager;
import com.jikuodong.springboot_mina.mina.service.session.PcmSession;
import com.jikuodong.springboot_mina.mina.service.session.SessionManager;
import com.jikuodong.springboot_mina.util.UuidUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service.handler.impl
 * @ClassName: BindHanler
 * @Author: JKD
 * @Description: 绑定处理handler
 * @Date: 2019/11/8 15:51
 * @Version: 1.0
 */
public class BindHanler implements RequestHandler {

    private final Logger logger = LogManager.getLogger(BindHanler.class);

    // 获取会话管理类
    @Autowired
    private SessionManager sessionManager;
    /**
     * 逻辑处理方法
     * @param newSession 新的会话
     * @param message 接收的消息
     * @return
     */
    @Override
    public ReplyBody process(PcmSession newSession, SentBody message) {
            ReplyBody reply = new ReplyBody();
        try {
            String account = message.get(Message.SESSION_KEY);
            newSession.setAccount(account);
            newSession.setAttribute(Message.SESSION_KEY, account);
            // 超时次数设置为0
            newSession.setAttribute(Message.TIME_OUT_NUM, 0);
            newSession.setGid(UuidUtil.get32UUID());
            // 设置部分所需信息
            newSession.setMessage(message.get("message"));
            // 设置绑定时间，第一次心跳时间
            newSession.setBindTime(System.currentTimeMillis());
            newSession.setHeartbeat(System.currentTimeMillis());
            // 由于客户端断线服务端可能会无法获知的情况，客户端重连时，需要关闭就的连接
            PcmSession oldSession = sessionManager.getSession(account);
            if (oldSession != null && !oldSession.equals(newSession)) {
                // 移除account属性
                oldSession.removeAttribute(Message.SESSION_KEY);
                // 替换oldSession
                sessionManager.replaceSession(account, newSession);
                // 发送踢下线的消息
                ReplyBody rb = new ReplyBody();
                rb.setCode(Message.MessageType.TYPE_999);
                rb.put(Message.SESSION_KEY, account);
                // 判断当前会话是否属于本地的会话
                if (oldSession.isLocalhost()) {
                    oldSession.write(rb.toJson());
                    oldSession.close(true);
                    logger.info(">>>>>>>>>>>>>>>>终端用户：" + account + "已在别处登陆，当前连接已被关闭<<<<<<<<<<<<");
                } else {
                    // 不是则需要发往目标服务器处理
                    // 本服务为提供此功能，需要自行添加
                }
            }
            if (oldSession == null) {
                sessionManager.addSession(account, newSession);
            }
            reply.setCode(Message.ReturnCode.CODE_200);
        } catch (Exception e) {
            reply.setCode(Message.ReturnCode.CODE_500);
            e.printStackTrace();
        }
        if (reply.getCode().equals(Message.ReturnCode.CODE_200)) {
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>终端用户：" + message.get(Message.SESSION_KEY) + "绑定成功<<<<<<<<<<<<<<<<<<<<");
            ConcurrentHashMap<String, PcmSession> map = DefaultSessionManager.getSessions();
            // 遍历map，封装为list数据
            Set<Map.Entry<String, PcmSession>> entrySet = map.entrySet();
            Iterator<Map.Entry<String, PcmSession>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, PcmSession> item =  iterator.next();
                PcmSession pcmSession = item.getValue();
                System.out.println(pcmSession.getAccount());
            }
        } else {
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>终端用户：" + message.get(Message.SESSION_KEY) + "绑定失败<<<<<<<<<<<<<<<<<<<<");
        }
        return reply;
    }
}
