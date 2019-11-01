package demo2;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * describe 自定义解码器
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName ProtocalDecoder.java
 * @createTime 2019年10月29日 17:25:00
 */
public class ProtocalDecoder implements ProtocolDecoder {

    private final AttributeKey CONTEXT = new AttributeKey(this.getClass(), "context");
    private final Charset charset;
    private int maxPackLength = 100;

    // 默认的构造函数
    public ProtocalDecoder() {
        this(Charset.defaultCharset());
    }

    public ProtocalDecoder(Charset charset) {
        this.charset = charset;
    }

    // 获得上下文
    public Context getContext(IoSession session) {
        Context context = (Context) session.getAttribute(CONTEXT);
        if (context == null) {
            context = new Context();
            session.setAttribute(CONTEXT, context);
        }
        return context;
    }

    @Override
    public void decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        final int packHeadlength = 5;
        Context context = this.getContext(ioSession);
        context.append(ioBuffer);
        IoBuffer buf = context.getBuffer();
        buf.flip();
        while (buf.remaining() >= packHeadlength) {
            buf.mark();
            int length = buf.getInt();
            byte flag = buf.get();
            if (length < 0 || length > maxPackLength) {
                buf.reset();
                break;
            } else if (length >= packHeadlength && length - packHeadlength <= buf.remaining()) {
                int oldLimit = buf.limit();
                buf.limit(buf.position() + length - packHeadlength);
                String content = buf.getString(context.getDecoder());
                buf.limit(oldLimit);
                ProtocalPack protocalPack = new ProtocalPack(flag, content);
                protocolDecoderOutput.write(protocalPack);
            } else { // 半包
                buf.clear();
                break;
            }
        }
        if (buf.hasRemaining()) {
            IoBuffer temp = IoBuffer.allocate(maxPackLength).setAutoExpand(true);
            temp.put(buf);
            temp.flip();
            buf.reset();
            buf.put(temp);
        } else {
            buf.reset();
        }
    }

    @Override
    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {
        Context context = (Context) ioSession.getAttribute(CONTEXT);
        if (context != null) {
            ioSession.removeAttribute(CONTEXT);
        }
    }

    public int getMaxPackLength() {
        return maxPackLength;
    }

    public void setMaxPackLength(int maxPackLength) {
        if (maxPackLength < 0) {
            throw new IllegalArgumentException("maxPackLength参数：" + maxPackLength);
        }
        this.maxPackLength = maxPackLength;
    }

    private class Context {
        private final CharsetDecoder decoder;
        private IoBuffer buffer;

        private Context() {
            decoder = charset.newDecoder();
            buffer = IoBuffer.allocate(80).setAutoExpand(true);
        }

        // 追加缓冲区数据
        public void append(IoBuffer ioBuffer) {
            this.getBuffer().put(ioBuffer);
        }

        public void reset() {
            decoder.reset();
        }

        public CharsetDecoder getDecoder() {
            return decoder;
        }

        public IoBuffer getBuffer() {
            return buffer;
        }

        public void setBuffer(IoBuffer buffer) {
            this.buffer = buffer;
        }
    }
}
