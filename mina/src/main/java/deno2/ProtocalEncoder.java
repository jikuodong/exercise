package deno2;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;

/**
 * describe 自定义编码器（变成二进制流）
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName ProtocalEncoder.java
 * @createTime 2019年10月29日 16:54:00
 */
public class ProtocalEncoder extends ProtocolEncoderAdapter {

    private final Charset charset;
    public ProtocalEncoder(Charset charset) {
        this.charset = charset;
    }
    public void encode(IoSession ioSession, Object message, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {

        ProtocalPack value = (ProtocalPack) message;
        // 设置缓冲区空间大小
        IoBuffer buffer = IoBuffer.allocate(value.getLength());
        // 设置缓冲区自动增长
        buffer.setAutoExpand(true);
        // 设置包头
        buffer.putInt(value.getLength());
        buffer.put(value.getFlag());
        if (value.getContent() !=null) {
            buffer.put(value.getContent().getBytes());
        }
        buffer.flip();
        protocolEncoderOutput.write(buffer);
    }
}
