package demo4;

/**
 * @projectName: mina
 * @package: demo4
 * @className: ProtocalPack
 * @author: JKD
 * @description: mina协议包
 * @date: 2020/4/3 16:36
 * @version: 1.0
 */
public class ProtocalPack {
    private int length;
    private byte flag;
    private String content;

    public ProtocalPack(byte flag, String content) {
        this.flag = flag;
        this.content = content;
        int len1 = content == null ? 0 : content.getBytes().length;
        this.length = 5+ len1;
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
        return "ProtocalPack{" +
                "length=" + length +
                ", flag=" + flag +
                ", content='" + content + '\'' +
                '}';
    }
}
