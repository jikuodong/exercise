package deno2;

/**
 * describe 封装自定义协议数据包
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName ProtocalPack.java
 * @createTime 2019年10月29日 16:44:00
 */
public class ProtocalPack {
    private int length;
    private byte flag;
    private String content;

    public ProtocalPack(byte flag, String content) {
        this.flag = flag;
        this.content = content;
        // 计算长度
        int length = content==null? 0 : content.getBytes().length;
        this.length = 4 + 1 + length;
    }
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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
        StringBuffer sb = new StringBuffer();
        sb.append("length:").append(length);
        sb.append("flag").append(flag);
        sb.append("content:").append(content);
        return sb.toString();
    }
}
