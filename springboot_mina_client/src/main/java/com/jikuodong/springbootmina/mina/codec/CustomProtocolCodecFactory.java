package com.jikuodong.springbootmina.mina.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

/*
    自定义编解码工厂类
*/
public class CustomProtocolCodecFactory implements ProtocolCodecFactory {

    private final ProtocolEncoder encoder;
    private final ProtocolDecoder decoder;

    public CustomProtocolCodecFactory() {
        this(Charset.forName("UTF-8"));
    }

    // 构造方法注入编解码器
    public CustomProtocolCodecFactory(Charset charset) {
        this.encoder = new CustomProtocolEncoder(charset);
        this.decoder = new CustomProtocolDecoder(charset);
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }
}
