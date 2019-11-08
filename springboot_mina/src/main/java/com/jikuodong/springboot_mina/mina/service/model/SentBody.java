package com.jikuodong.springboot_mina.mina.service.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service.model
 * @ClassName: SentBody
 * @Author: JKD
 * @Description: 服务端接收消息对象
 * @Date: 2019/11/7 11:43
 * @Version: 1.0
 */
public class SentBody implements Serializable {

    private static final long serialVersionUID = 1L;

    private String key;
    private HashMap<String, String> data;
    private long timestamp;

    public SentBody() {
        data = new HashMap<String, String>();
        timestamp = System.currentTimeMillis();
    }

    public String get(String k) {
        return data.get(k);
    }
    public void put(String k,String v) {
        data.put(k, v);
    }
    public void remove(String k) {
        data.remove(k);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("?xml version=\"1.0\" encoding=\\\"UTF-8\"?>");
        buffer.append("<sent>");
        buffer.append("<key>").append(key).append("</key>");
        buffer.append("<timestamp>").append(timestamp).append("</timestamp>");
        buffer.append("<data>");
        for (String key : data.keySet()) {
            buffer.append("<" + key + ">").append(data.get(key)).append(
                    "</" + key + ">");
        }
        buffer.append("</data>");
        buffer.append("</sent>");
        return buffer.toString();
    }
    public String toXmlString() {
        return toString();
    }
}
