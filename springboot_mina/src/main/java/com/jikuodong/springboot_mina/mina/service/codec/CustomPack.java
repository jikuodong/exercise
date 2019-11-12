package com.jikuodong.springboot_mina.mina.service.codec;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service.codec
 * @ClassName: CustomPack
 * @Author: JKD
 * @Description: 自定义协议包 协议为——数据长度（4个字节） + 协议编号（1字节） +  真实数据
 * @Date: 2019/11/7 11:08
 * @Version: 1.0
 */
public class CustomPack {

    /**
     * 0x00表示请求
     */
    public static final byte REQUEST = 0x00;

    /**
     * 0x01表示回复
     */
    public static final byte RESPONSE = 0x01;

    // 总长度（编号字节 + 长度的字节 + 包体长度字节）
    private int len;
    // 版本号
    private byte flag;

    // 包体
    private String content;

    public CustomPack(String content) {
        // 不传递类型，统一认为请求
        this.flag = REQUEST;
        this.content = content;
        // 版本类型的长度为1个字节，len的长度4个字节,内容的字节数
        this.len = 1 + 4 + (content == null ? 0 : content.getBytes().length);
    }
    // 构造方法设置协议
    public CustomPack(byte flag, String content) {
        this.flag = flag;
        this.content = content;
        // 版本类型的长度1个字节， len的长度4个字节， 内容的字节数
        this.len = 1 + 4 + (content == null ? 0 : content.getBytes().length);
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CustomPack{" +
                "len=" + len +
                ", flag=" + flag +
                ", content='" + content + '\'' +
                '}';
    }
}
