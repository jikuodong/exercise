package deno2;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;

/**
 * describe 自定义解码器
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName ProtocalDecoder.java
 * @createTime 2019年10月29日 17:25:00
 */
public class ProtocalDecoder implements ProtocolDecoder {

    private  final AttributeKey CONTEXT = new AttributeKey(this.getClass(),"context");
    private  final Charset charset;
    private int maxPackLength = 100;

    public ProtocalDecoder () {
        this(Charset.defaultCharset());
    }
    public ProtocalDecoder(Charset charset) {
        this.charset = charset;
    }

    public void decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }

    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }

    public void dispose(IoSession ioSession) throws Exception {

    }
    public int getMaxPackLength() {
        return maxPackLength;
    }

    public void setMaxPackLength(int maxPackLength) {
        if (maxPackLength<0) {
            throw  new IllegalArgumentException("maxPackLength参数："+maxPackLength);
        }
        this.maxPackLength = maxPackLength;
    }
}
