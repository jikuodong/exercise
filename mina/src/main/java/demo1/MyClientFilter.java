package demo1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

import java.util.Date;


/**
 * describe 自定义客户端过滤器
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName MyClientFilter.java
 * @createTime 2019年10月18日 11:48:00
 */
public class MyClientFilter extends IoFilterAdapter {
    private  static final Logger logger = LogManager.getLogger(MyClientFilter.class);
    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
        IoBuffer ioBuffer = (IoBuffer) message;
        byte[] b = new byte [ioBuffer.limit()];
        ioBuffer.get(b);
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            buffer.append((char) b [i]);
        }
        logger.info("ClientFilter接收到客户端消息：{}", hexStr2Str(bytesToString2(b)));
        Date date = new Date();
        session.write(date);
        // 传给下一个过滤器
//        JSONObject jsonObject = JSONObject.fromObject(hexStr2Str(bytesToString2(b)));
//        System.out.println(jsonObject);
//        System.out.println(jsonObject.get("id"));
        nextFilter.messageReceived(session, ioBuffer);
    }

    @Override
    public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
        logger.info("ClientFilter接收到服务端消息：" + writeRequest.getMessage());
        // 传给下一个过滤器
        nextFilter.messageSent(session, writeRequest);
    }


    /**
     * 16进制直接转换成为字符串(无需Unicode解码)
     *
     * @param hexStr
     * @return
     */
    public static String hexStr2Str(String hexStr) {
        if (hexStr == null || hexStr.equals("")) {
            return null;
        }
        hexStr = hexStr.replace(" ", "");
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }


    public String bytesToString2(byte[] bytes) {
        final char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = hexArray[v >>> 4];
            hexChars[i * 2 + 1] = hexArray[v & 0x0F];

            sb.append(hexChars[i * 2]);
            sb.append(hexChars[i * 2 + 1]);
            sb.append(' ');
        }
        return sb.toString();
    }
}
