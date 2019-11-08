package com.jikuodong.springboot_mina.mina.service.session;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service.session
 * @ClassName: SessionManager
 * @Author: JKD
 * @Description: session管理接口
 * @Date: 2019/11/8 14:27
 * @Version: 1.0
 */
public interface SessionManager {
    /**
     * 添加新的session
     */
    public void addSession(String account, PcmSession session);

    /**
     * 获取session
     */
    PcmSession getSession(String account);

    /**
     * 替换session
     */
    void replaceSession(String account, PcmSession session);

    /**
     * 删除session
     */
    void removeSession(String account);

    /**
     * 删除session
     */
    void removeSession(PcmSession session);
}
