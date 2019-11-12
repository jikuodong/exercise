package com.jikuodong.springboot_mina.util;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.util
 * @ClassName: Const
 * @Author: JKD
 * @Description: 常量
 * @Date: 2019/11/12 16:13
 * @Version: 1.0
 */
public class Const {
    public static final int PORT = 8090;

    // idel时间，单位秒
    public static final int IDELTIMEOUT = 180;

    // session_key采用设备编号
    public static final String SESSION_KEY = "account";

    // 超时KEY
    public static final String TIME_OUT_KEY = "time_out";

    // 超时次数
    public static final int TIME_OUT_NUM = 3;

    // 登录验证
    public static final String AUTHEN = "1";

    // 验证服务器时间
    public static final String TIME_CHECK = "2";

    // 心跳包
    public static final int HEART_BEAT = 3;

    // 版本
    public static final String VERSION = "V1.0";
}
