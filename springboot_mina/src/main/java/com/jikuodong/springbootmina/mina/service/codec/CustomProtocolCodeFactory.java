package com.jikuodong.springbootmina.mina.service.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service.codec
 * @ClassName: CustomProtocolCodeFactory
 * @Author: JKD
 * @Description: 自定义编解码工厂类
 * @Date: 2019/11/12 15:37
 * @Version: 1.0
 */
public class CustomProtocolCodeFactory implements ProtocolCodecFactory {
    private final ProtocolEncoder encoder;
    private final ProtocolDecoder decoder;

    public CustomProtocolCodeFactory() {
        this(Charset.forName("UTF-8"));
    }

    // 构造方法注入编解码器
    public CustomProtocolCodeFactory(Charset charset) {
        this.encoder = new CustomProtocolEncoder(charset);
        this.decoder = new CustomProtocolDecoder(charset);
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
