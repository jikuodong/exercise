package com.jikuodong.springboot_mina.mina.service.handler;

import com.google.gson.Gson;
import com.jikuodong.springboot_mina.mina.service.codec.CustomPack;
import com.jikuodong.springboot_mina.mina.service.model.Message;
import com.jikuodong.springboot_mina.mina.service.model.ReplyBody;
import com.jikuodong.springboot_mina.mina.service.model.SentBody;
import com.jikuodong.springboot_mina.mina.service.session.PcmSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.net.InetSocketAddress;
import java.util.HashMap;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service.handler
 * @ClassName: ServiceHandler
 * @Author: JKD
 * @Description: 服务端handler
 * @Date: 2019/11/12 9:59
 * @Version: 1.0
 */
public class ServiceHandler extends IoHandlerAdapter {
    private final Logger logger = LogManager.getLogger(ServiceHandler.class);
    // 存放本地处理的handler
    private HashMap<String, RequestHandler> handlers = new HashMap<>();


    /**
     * describe 接收到消息时
     *
     * @method messageReceived
     * @author JKD
     * @param session
     * @param message
     * @return void
     * @Exception
     * @date 2019/11/12 10:04
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        logger.info("<<<<<<<<<<<<<<<<<<<< 获取一条信息，来自sessionId:" + session.getId()+ " <<<<<<<<<<<<<<<<<<<<");
        // 转为自定义协议，取出内容，转为接收的对象
        SentBody sentBody = new Gson().fromJson(((CustomPack)message).getContent(), SentBody.class);
        ReplyBody rb = new ReplyBody();
        PcmSession pcmSession = new PcmSession(session);
        String key = sentBody.getKey();
        // 根据key的不同，调用不同的handler
        RequestHandler handler = handlers.get(key);
        // 如果没有这个handler
        if (handler == null) {
            rb.setCode(Message.ReturnCode.CODE_405);
            rb.setMessage("Service undefined this handler:" + key);
        } else {
            rb = handler.process(pcmSession, sentBody);
        }
        rb.setKey(key);
        pcmSession.write(rb.toJson(), false);
    }

    /**
     * describe 建立连接时
     *
     * @method sessionCreated
     * @author JKD
     * @param session
     * @return void
     * @date 2019/11/12 10:23
     */
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        InetSocketAddress isa = (InetSocketAddress) session.getRemoteAddress();
        // IP
        String address = isa.getAddress().getHostAddress();
        session.setAttribute("address", address);
        logger.info(">>>>>>>>>>>>>>>>>> 来自" + address + " 的终端上线，sessionId：" + session.getId() + "  <<<<<<<<<<<<<");
    }

    /**
     * 打开连接时
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        logger.debug("Open a connection ...");
    }

    /**
     * 关闭连接时
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        PcmSession pcmSession = new PcmSession(session);
        // 获取连接关闭的handler,进行处理
        try {
            RequestHandler handler = handlers.get("clientClose");
            if (handler != null && pcmSession.containAttribute(Message.SESSION_KEY)) {
                handler.process(pcmSession, null);
                logger.info(">>>>>>>>>>>>>>>>>> 终端用户：" + session.getAttribute(Message.SESSION_KEY) + "已下线 <<<<<<<<<<<<<<<<<" );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.sessionClosed(session);
    }

    /**
     * 连接空闲时
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        logger.debug("sessionIdle ... from " +  session.getRemoteAddress());
    }

    /**
     * 捕获到异常
     * @param session
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        logger.error(">>>>>>>>>>>>>>>>>> 终端用户：" + session.getAttribute(Message.SESSION_KEY) + "连接发生异常，即将关闭连接，原因：" + cause.getMessage() + " <<<<<<<<<<<<<<<<<");
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        logger.debug(">>>>>>>>>>>>>>>>>>>> 发送消息成功 >>>>>>>>>>>>>>>>>>>>");
    }

    public HashMap<String, RequestHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(HashMap<String, RequestHandler> handlers) {
        this.handlers = handlers;
        logger.info(">>>>>>>>>>>>>>>>>> Mina服务端启动成功 <<<<<<<<<<<<<<<<<");
    }
}
