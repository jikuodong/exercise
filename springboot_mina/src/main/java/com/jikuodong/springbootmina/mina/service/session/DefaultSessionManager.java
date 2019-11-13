package com.jikuodong.springbootmina.mina.service.session;

import com.jikuodong.springbootmina.mina.service.model.Message;
import org.springframework.context.annotation.Configuration;

import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service.session
 * @ClassName: DefaultSessionManager
 * @Author: JKD
 * @Description: 自带默认 session管理实现
 * @Date: 2019/11/8 14:35
 * @Version: 1.0
 */
@Configuration
public class DefaultSessionManager extends Observable implements SessionManager {

    /**
     * 存放session的线程安全的map集合
     */
    private static ConcurrentHashMap<String, PcmSession> sessions  = new ConcurrentHashMap<>();

    /**
     * 线程安全ed自增类，用于统计连接数
     */
    private static final AtomicInteger connectionCounter = new AtomicInteger(0);

    /**
     * 添加session
     * @param account
     * @param session
     */
    @Override
    public void addSession(String account, PcmSession session) {
        if (null != session) {
            sessions .put(account, session);
            connectionCounter.incrementAndGet();
            // 被观察者方法
            setChanged();
            notifyObservers(session.getMessage() + "上线");
        }
    }

    /**
     * 获取session
     * @param account
     * @return
     */
    @Override
    public PcmSession getSession(String account) {
        return sessions .get(account);
    }

    /**
     * 替换session方法，通过account
     * @param account
     * @param session
     */
    @Override
    public void replaceSession(String account, PcmSession session) {
        sessions.put(account, session);
        // 被观察者方法
        setChanged();
        notifyObservers(session.getMessage()+"已在别处登录");
    }

    /**
     * 移除session通过accont
     * @param account
     */
    @Override
    public void removeSession(String account) {
        String msg = getSession(account).getMessage();
        sessions.remove(account);
        connectionCounter.decrementAndGet();
        // 被观察者方法
        setChanged();
        notifyObservers(msg = "已下线");
    }

    /**
     * 移除session通过session
     * @param pcmSession
     */
    @Override
    public void removeSession(PcmSession pcmSession) {
        String account = (String) pcmSession.getAttribute(Message.SESSION_KEY);
        removeSession(account);
    }

    public static ConcurrentHashMap<String, PcmSession> getSessions() {
        return sessions;
    }

    /**
     * 无意义的空方法
     * 解决：当更新的是结合中一个对象的一个属性时，需要通知观察者更新
     */
    public void activeNotify(String msg) {
        // 被观察者方法
        setChanged();
        notifyObservers(msg);
    }
}
