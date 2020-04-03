package demo4;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;

/**
 * @projectName: mina
 * @package: demo4
 * @className: ProtocalDecoder
 * @author: JKD
 * @description: 解码器
 * @date: 2020/4/3 16:57
 * @version: 1.0
 */
public class ProtocalDecoder implements ProtocolDecoder {

    private final AttributeKey CONTEXT = new AttributeKey(this.getClass(),"context");

    private final Charset charset;
    private int maxPackLength = 100;

    public ProtocalDecoder() {
        this(Charset.defaultCharset());
    }
    public ProtocalDecoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput out) throws Exception {

    }

    @Override
    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {

    }
}
