package demo2;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

/**
 * describe 编解码的工厂
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName ProtocalFactory.java
 * @createTime 2019年10月30日 11:15:00
 */
public class ProtocalFactory implements ProtocolCodecFactory {
    private final ProtocalDecoder decoder;
    private final ProtocalEncoder encoder;
    public ProtocalFactory(Charset charset) {
        encoder = new ProtocalEncoder(charset);
        decoder = new ProtocalDecoder(charset);
    }
    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return decoder;
    }
}
