package demo3;

import java.nio.ByteBuffer;

/**
 * @ProjectName: mina
 * @Package: demo3
 * @ClassName: CustomPack
 * @Author: JKD
 * @Description: 自定义协议包
 * 协议为-数据长度（4个字节）+ 协议编号（1字节）+ 真实数据。
 * @Date: 2019/11/1 16:15
 * @Version: 1.0
 */
public class CustomPack {

    /**
     *  0x00 表示请求
     */
    public static final byte REQUEST = 0x00;
    /**
     *  0x01表示回复
     */
    public static  final byte RESPONSE = 0x01;
    // 总长度（编号字节 + 长度的字节 + 包体长度字节）
    private int len;
    // 版本号
    private byte flag;
    // 发送人，只是服务端-客户端，暂时无需发送人 接收人
    // private long sender;
    // 接收人
    // private long receiver;
    // 包体
    private String content;

    // 构造方法设置协议
    public CustomPack(byte flag, String content) {
        this.flag = flag;
        this.content = content;
        // 版本类型的长度1个字节，len的长度4个字节，内存的字节数
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

    public static void main(String args[]){
        ByteBuffer buffer = ByteBuffer.allocate(102400);
        System.out.println("--------Test reset----------");
        System.out.println("0:" + buffer);
        buffer.clear();
        System.out.println("1:" + buffer);
        buffer.position(5);
        System.out.println("2:" + buffer);
        buffer.mark();
        System.out.println("3:" + buffer);
        buffer.position(10);
        System.out.println("before reset:" + buffer);
        buffer.reset();
        System.out.println("after reset:" + buffer);

        System.out.println("--------Test rewind--------");
        buffer.clear();
        buffer.position(10);
        buffer.limit(15);
        System.out.println("before rewind:" + buffer);
        buffer.rewind();
        System.out.println("before rewind:" + buffer);

        System.out.println("--------Test compact--------");
        buffer.clear();
        System.out.println("--------Test compact--------" + buffer);
        buffer.put("abcd".getBytes());
        System.out.println("before compact:" + buffer);
        System.out.println(new String(buffer.array()));
        buffer.flip();
        System.out.println("after flip:" + buffer);
        System.out.println(buffer);
        System.out.println((char) buffer.get() + "=====" + buffer);
        System.out.println((char) buffer.get() + "=====" + buffer);
        System.out.println((char) buffer.get() + "=====" + buffer);
        System.out.println((char) buffer.get() + "=====" + buffer);
        System.out.println("after three gets:" + buffer);
        System.out.println("\t" + new String(buffer.array()));
        buffer.compact();
        System.out.println("after compact:" + buffer);
        System.out.println("\t" + new String(buffer.array()));

        System.out.println("------Test get-------------");
        buffer = ByteBuffer.allocate(32);
        buffer.put((byte) 'a').put((byte) 'b').put((byte) 'c').put((byte) 'd')
                .put((byte) 'e').put((byte) 'f');
        System.out.println("before flip()" + buffer);
        // 转换为读取模式
        buffer.flip();
        System.out.println("before get():" + buffer);
        System.out.println((char) buffer.get());
        System.out.println("after get():" + buffer);
        // get(index)不影响position的值
        System.out.println((char) buffer.get(2));
        System.out.println("after get(index):" + buffer);
        byte[] dst = new byte[10];
        buffer.get(dst, 0, 2);
        System.out.println("after get(dst, 0, 2):" + buffer);
        System.out.println("\t dst:" + new String(dst));
        System.out.println("buffer now is:" + buffer);
        System.out.println("\t" + new String(buffer.array()));

        System.out.println("--------Test put-------");
        ByteBuffer bb = ByteBuffer.allocate(32);
        System.out.println("before put(byte):" + bb);
        System.out.println("after put(byte):" + bb.put((byte) 'z'));
        System.out.println("\t" + bb.put(2, (byte) 'c'));
        // put(2,(byte) 'c')不改变position的位置
        System.out.println("after put(2,(byte) 'c'):" + bb);
        System.out.println("\t" + new String(bb.array()));
        // 这里的buffer是 abcdef[pos=3 lim=6 cap=32]
        bb.put(buffer);
        System.out.println("after put(buffer):" + bb);
        System.out.println("\t" + new String(bb.array()));
    }
}
