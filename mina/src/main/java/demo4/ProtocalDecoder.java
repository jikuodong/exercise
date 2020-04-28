package demo4;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

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

    public int getMaxPackLength() {
        return maxPackLength;
    }

    public void setMaxPackLength(int maxPackLength) {
        if(maxPackLength<0){
           throw new IllegalArgumentException("maxPackLength参数："+maxPackLength);
        }
        this.maxPackLength = maxPackLength;
    }

    public ProtocalDecoder() {
        this(Charset.defaultCharset());
    }
    public ProtocalDecoder(Charset charset) {
        this.charset = charset;
    }

    public Context getContext(IoSession session){
        Context ctx = (Context) session.getAttribute(CONTEXT);
        if (ctx == null){
            ctx = new Context();
            session.setAttribute(CONTEXT,ctx);
        }
        return ctx;

    }
    @Override
    public void decode(IoSession ioSession, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        final  int packHeadLength = 5;
        Context ctx = this.getContext(ioSession);
        ctx.append(in);
        IoBuffer buffer = ctx.getBuf();
        buffer.flip();
        while (buffer.remaining()>=5){
            buffer.mark();
            int length = buffer.getInt();
            byte flag = buffer.get();
            if (length <0 || length>maxPackLength){
                buffer.reset();
                break;
            }else if (length >=packHeadLength && length-packHeadLength<=buffer.remaining()){
               int oldLimit = buffer.limit();
               buffer.limit(buffer.position()+length-packHeadLength);
               String context = buffer.getString(ctx.getDecoder());
               buffer.limit(oldLimit);
               ProtocalPack pack = new ProtocalPack(flag, context);
               out.write(pack);
            } else { // 半包
                buffer.clear();
                break;
            }
        }
        if (buffer.hasRemaining()){
            IoBuffer temp = IoBuffer.allocate(maxPackLength).setAutoExpand(true);
            temp.put(buffer);
            temp.flip();
            temp.reset();
            buffer.put(temp);
        } else {
            buffer.reset();
        }

    }

    @Override
    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {
        Context ctx = (Context) ioSession.getAttribute(CONTEXT);
        if (ctx != null) {
            ioSession.removeAttribute(CONTEXT);
        }
    }
    private class Context{
        private final CharsetDecoder decoder;
        private IoBuffer buf;

        public CharsetDecoder getDecoder() {
            return decoder;
        }

        public IoBuffer getBuf() {
            return buf;
        }

        public void setBuf(IoBuffer buf) {
            this.buf = buf;
        }

        private Context() {
            decoder = charset.newDecoder();
            buf = IoBuffer.allocate(80).setAutoExpand(true);
        }
        public void append(IoBuffer in){
            this.getBuf().put(in);
        }
        public void rest() {
            decoder.reset();
        }
    }
}
