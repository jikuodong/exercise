package com.jikuodong.springbootmina.mina.model;

/**
 * @author ZERO
 * @Description 消息常量
 */
public class Message {
    public static class ReturnCode {

        public static String CODE_404 = "404";

        public static String CODE_403 = "403";  //该账号未绑定

        public static String CODE_405 = "405"; //事物未定义

        public static String CODE_200 = "200"; //成功

        public static String CODE_500 = "500"; //未知错误

    }

    /**
     * 账户
     */
    public static final String SESSION_KEY = "account";

    /**
     * 服务端处理的key
     */
    public static final String BIND_HANDLER = "clientBind";
    public static final String SYNC_HANDLER = "clientSync";
    public static final String PUSH_HANDLER = "clientPush";
    public static final String CLOSE_HANDLER = "clientClose";

    /**
     * 服务端心跳请求命令
     */
    public static final String CMD_HEARTBEAT_REQUEST = "hb_request";

    /**
     * 客户端心跳响应命令
     */
    public static final String CMD_HEARTBEAT_RESPONSE = "hb_response";


    public static class MessageType {
        // 用户 踢出下线消息类型
        public static String TYPE_999 = "999";
    }

}
