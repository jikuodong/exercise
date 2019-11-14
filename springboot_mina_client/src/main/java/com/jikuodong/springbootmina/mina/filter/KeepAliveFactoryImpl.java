package com.jikuodong.springbootmina.mina.filter;

import com.jikuodong.springbootmina.mina.codec.CustomPack;
import com.jikuodong.springbootmina.mina.model.Message;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * @ProjectName: springboot_mina_client
 * @Package: com.jikuodong.springbootmina.mina.filter
 * @ClassName: KeepAliveFactoryImpl
 * @Author: JKD
 * @Description: 心跳实现类
 * 服务端发送的是hb_request,那么客户端就应该返回hb_response
 * @Date: 2019/11/14 9:13
 * @Version: 1.0
 */
public class KeepAliveFactoryImpl implements KeepAliveMessageFactory {

    // 服务端需要发送请求，客户端无需发送
    private boolean sendHbRequest;

    public KeepAliveFactoryImpl(boolean isServer) {
        this.sendHbRequest = isServer;
    }

    /**
     * 服务端线条发送请求命令
     */
    private static final String HEART_BEAT_REQUEST = Message.CMD_HEARTBEAT_REQUEST;

    /**
     * 客户端心跳响应命令
     */
    private static final String HEART_BEAT_RESPONSE = Message.CMD_HEARTBEAT_RESPONSE;

    /**
     * 用来判断接收到的消息是不是一个心跳请求包，是就返回true
     * @param session
     * @param message
     * @return
     */
    @Override
    public boolean isRequest(IoSession session, Object message) {
        if (message instanceof CustomPack) {
            CustomPack pack = (CustomPack) message;
            return pack.getContent().equals(Message.CMD_HEARTBEAT_REQUEST);
        }
        return false;
    }

    /**
     *
     * @param session
     * @param message
     * @return
     */
    @Override
    public boolean isResponse(IoSession session, Object message) {
        // 不接受回复包
        return false;
    }

    /**
     * 用来获取一个心跳请求包
     * @param ioSession
     * @return
     */
    @Override
    public Object getRequest(IoSession ioSession) {
        // 无需请求心跳
        return null;
    }

    /**
     * 用来获取一个心跳回复包
     * @param session
     * @param message
     * @return
     */
    @Override
    public Object getResponse(IoSession session, Object message) {
        return new CustomPack(CustomPack.RESPONSE, HEART_BEAT_RESPONSE);
    }
}
