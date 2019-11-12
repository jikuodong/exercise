package com.jikuodong.springboot_mina.mina.service.filter;

import com.jikuodong.springboot_mina.mina.service.codec.CustomPack;
import com.jikuodong.springboot_mina.mina.service.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service.filter
 * @ClassName: KeepAliveFactoryImpl
 * @Author: JKD
 * @Description: 心跳实现类
 * 服务端发送的是hb_request,那么客户端就应该返回hb_response
 * @Date: 2019/11/12 11:15
 * @Version: 1.0
 */
public class KeepAliveFactoryImpl implements KeepAliveMessageFactory {
    private final Logger logger = LogManager.getLogger(KeepAliveFactoryImpl.class);

    // 服务端需要发送请求，客户端无需发送
    private boolean sendHbRequest;

    public  KeepAliveFactoryImpl(boolean isServer) {
        this.sendHbRequest = isServer;
    }

    /**
     * 服务端心跳发送请求命令
     */
    private static final String HEART_BEAT_REQUEST = Message.CMD_HEARTBEAT_REQUEST;

    /**
     * 客户端心跳响应命令
     */
    private static final String HEART_BEAT_RESPONSE = Message.CMD_HEARTBEAT_REAPONSE;

    /**
     * 用来判断接收到的消息是不是一个心跳请求包，是就返回true[接收端使用]
     * @param session
     * @param message
     */
    @Override
    public boolean isRequest(IoSession session, Object message) {
        if (message instanceof CustomPack) {
            CustomPack pack = (CustomPack) message;
            if (pack.getContent().equals(Message.CMD_HEARTBEAT_REQUEST)){
                // 将超时次数设置为0
                session.setAttribute(Message.TIME_OUT_NUM, 0);
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 用来判断接收到的消息是不是一个心跳回复包，是就返回true[发送端使用]
     * @param session
     * @param message
     * @return
     */
    @Override
    public boolean isResponse(IoSession session, Object message) {
        if (message instanceof CustomPack) {
            CustomPack pack = (CustomPack) message;
            return pack.getContent().equals(Message.CMD_HEARTBEAT_REAPONSE);
        }
        return false;
    }

    /**
     * 在需要发送心跳请求包（发送端使用）
     * @param ioSession
     * @return
     */
    @Override
    public Object getRequest(IoSession ioSession) {
        // 是否需要发送心跳请求
        if (sendHbRequest) {
            return new CustomPack(HEART_BEAT_REQUEST);
        }
        return null;
    }

    /**
     * 在需要回复心跳时，用来获取一个心跳回复包【接收端使用】
     * @param ioSession
     * @param o
     */
    @Override
    public Object getResponse(IoSession ioSession, Object o) {
        return new CustomPack(CustomPack.RESPONSE, HEART_BEAT_RESPONSE);
    }

}
