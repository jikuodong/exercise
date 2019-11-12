package com.jikuodong.springboot_mina.mina.service.session;

import com.jikuodong.springboot_mina.mina.service.codec.CustomPack;
import org.apache.mina.core.session.IoSession;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service.session
 * @ClassName: PcmSession
 * @Author: JKD
 * @Description: IoSession包装类
 * @Date: 2019/11/8 10:31
 * @Version: 1.0
 */
public class PcmSession implements Serializable {

    private static final long serialversionUID = 1L;

    // 不参与序列化
    private  transient IoSession session;

    private String gid; // session 全局ID
    private Long nid; // session在本台服务器上的ID
    private String host; // session 绑定的服务器IP
    private String account; // session绑定的账号
    private String message; // session绑定账号的消息
    private Long bindTime; // 登录时间
    private Long heartbeat; // 心跳时间

    public PcmSession() {}

    public PcmSession(IoSession session) {
        this.session = session;
        this.nid = session.getId();
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getBindTime() {
        return bindTime;
    }

    public void setBindTime(Long bindTime) {
        this.bindTime = bindTime;
    }

    public Long getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(Long heartbeat) {
        this.heartbeat = heartbeat;
    }

    public IoSession getSession() {
        return session;
    }

    public void setSession(IoSession session) {
        this.session = session;
    }

    // 将键为key,值为value的用户自定义的属性存储到I/O会话中。
    public void setAttribute(String key, Object value) {
        if (session != null) {
            session.setAttribute(key, value);
        }
    }

    /**
     *  在IO的会话中，判断是否存在包含key-value
     */
    public boolean containAttribute(String key) {
        if (session != null) {
            return session.containsAttribute(key);
        }
        return false;
    }
    /**
     * 从IO的会话中，删除key
     */
    public Object getAttribute(String key){
        if (session != null) {
            return session.getAttribute(key);
        }
        return null;
    }
    /**
     * 在IO会话中，判断是否存在包含key-value
     */
    public void removeAttribute(String key) {
        if (session != null) {
            session.removeAttribute(key);
        }
    }

    /**
     * 获取IP地址
     */
    public SocketAddress getRemoteAddress() {
        if (session !=null) {
            return session.getRemoteAddress();
        }
        return null;
    }

    /**
     * 将消息对象message发送到当前连接的对等体，该方法是异步的。
     * 当消息白真正发送到对等体的时候，IoHandler.messageSent(IoSession, Object)会被调用。
     * 如果需要的话，也可以等消息真正发送出去之后再继续执行后续操作。
     * @param msg 发送的消息
     */
        public void write(Object msg) {
            if (session != null) {
                session.write(msg).isWritten();
            }
        }

        public void write(Object msg, boolean isRequest) {
            if (null != session) {
                byte flag = isRequest ? CustomPack.REQUEST : CustomPack.RESPONSE;
                CustomPack pack = new CustomPack(flag, (String)msg);
            }
        }

    /**
     * 会话是否已经连接
     */
    public boolean isConnected() {
            if (session !=null) {
                return session.isConnected();
            }
            return false;
        }

    /**
     * 会话是否为本地连接
     */
    public boolean isLocalhost() {
            try {
                String ip = InetAddress.getLocalHost().getHostAddress();
                return ip.equals(host);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            return false;
        }

    /**
     * 关闭当前连接。如果参数immediately为true的话，
     * 连接会等到队列中所有的数据发送请求都完成之后才关闭；否则的话就立即关闭。
     */
    public void close(boolean immediately) {
        if (session != null) {
            if (immediately){
                session.closeNow();
            } else {
                session.closeOnFlush();
            }
        }
    }

    @Override
    public boolean equals(Object message) {
        if (message instanceof PcmSession) {
            PcmSession t = (PcmSession) message;
            if (t.nid != null && nid != null) {
                return t.nid.longValue() == nid.longValue() && t.host.equals(host);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        buffer.append("\"").append("gid").append("\":").append("\"").append(gid).append("\"").append(",");
        buffer.append("\"").append("nid").append("\":").append(nid).append(",");
        buffer.append("\"").append("host").append("\":").append("\"").append(host).append("\"").append(",");
        buffer.append("\"").append("account").append("\":").append("\"").append(account).append("\"").append(",");
        buffer.append("\"").append("bindTime").append("\":").append(bindTime).append(",");
        buffer.append("\"").append("heartbeat").append("\":").append(heartbeat);
        buffer.append("}");
        return buffer.toString();
    }
}
