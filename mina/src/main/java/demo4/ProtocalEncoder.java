package demo4;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;

/**
 * @projectName: mina
 * @package: demo4
 * @className: ProtocalEncoder
 * @author: JKD
 * @description: 编码器
 * @date: 2020/4/3 16:43
 * @version: 1.0
 */
public class ProtocalEncoder extends ProtocolEncoderAdapter {

    private final Charset charset;

    public ProtocalEncoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        ProtocalPack value = (ProtocalPack) message;
        IoBuffer buffer = IoBuffer.allocate(value.getLength());
        buffer.setAutoExpand(true);
        buffer.putInt(value.getLength());
        buffer.putInt(value.getFlag());
        if (value.getContent()!=null) {
            buffer.put(value.getContent().getBytes());
        }
        buffer.flip();
        out.write(buffer);
    }
}
