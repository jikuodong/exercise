package com.jikuodong.springboot_mina.mina.service.model;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service.model
 * @ClassName: Message
 * @Author: JKD
 * @Description: 消息常量
 * @Date: 2019/11/7 11:31
 * @Version: 1.0
 */
public class Message {
    public static class ReturnCode {
        public static String CODE_404 = "404";
        public static String CODE_403 = "403"; // 该账号为绑定
        public static String CODE_405 = "405"; // 事务未定义
        public static String CODE_200 = "200"; // 成功
        public static String CODE_500 = "500"; // 未知错误
    }

    public static final String SESSION_KEY = "account";

    /**
     * 服务端心跳请求命令
     */
    public static final String CMD_HEARTBEAT_REQUEST = "hb_request";

    /**
     * 客户端心跳响应命令
     */
    public static final String CMD_HEARTBEAT_REAPONSE = "hb_response";

    /**
     * 超时次数
     */
    public static final String TIME_OUT_NUM = "timeOutNum";

    public static class MessageType {
        // 用户踢出下线消息类型

        public static String TYPE_999 = "999";
    }
}
