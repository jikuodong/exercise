package com.jikuodong.springbootmina.mina.handler;

import ch.qos.logback.core.net.server.Client;
import com.google.gson.Gson;
import com.jikuodong.springbootmina.mina.codec.CustomPack;
import com.jikuodong.springbootmina.mina.model.Message;
import com.jikuodong.springbootmina.mina.model.ReplyBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: springboot_mina_client
 * @Package: com.jikuodong.springbootmina.mina.handler
 * @ClassName: ClientHandler
 * @Author: JKD
 * @Description: 客户端消息处理Handler
 * @Date: 2019/11/14 10:44
 * @Version: 1.0
 */

@Component
public class ClientHandler extends IoHandlerAdapter {

    private final Logger logger = LogManager.getLogger(ClientHandler.class);

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        CustomPack rPack = (CustomPack) message;
        // 转换为回复类型
        ReplyBody rb = new Gson().fromJson(rPack.getContent(), ReplyBody.class);
        String code = rb.getCode();
        String key = rb.getKey();
        if (code.equals(Message.ReturnCode.CODE_200) && key.equals(Message.BIND_HANDLER)) {
            logger.info("<<<<<<<<<<<<<<<<<< 绑定远程服务器成功 <<<<<<<<<<<<<<<<<<");
        } else if (code.equals(Message.ReturnCode.CODE_200) && key.equals(Message.SYNC_HANDLER)){
            logger.info("<<<<<<<<<<<<<<<<<< 同步远程服务器成功 <<<<<<<<<<<<<<<<<<");
        } else if (code.equals(Message.MessageType.TYPE_999)) {
            sessionClosed(session);
            logger.info("<<<<<<<<<<<<<<<<<<<< 当前账号已在别处登陆，远程连接被关闭 <<<<<<<<<<<<<<<<<<<<");
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        // 消息发送
        logger.info("<<<<<<<<<<<<<<<<<<<< 客户端消息发送成功 <<<<<<<<<<<<<<<<<<<<");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        logger.debug("<<<<<<<<<<<<<<<<<<<< 远程连接已关闭 <<<<<<<<<<<<<<<<<<<<");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        logger.error(">>>>>>>>>>>>>>>>>>>> 连接远程服务器出现异常，原因：" + cause.getMessage() + " <<<<<<<<<<<<<<<<<<<<");
    }
}
