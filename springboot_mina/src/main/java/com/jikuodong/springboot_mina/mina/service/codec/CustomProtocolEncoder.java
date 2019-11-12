package com.jikuodong.springboot_mina.mina.service.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service.codec
 * @ClassName: CustomProtocolEncoder
 * @Author: JKD
 * @Description: 自定义编码器
 * @Date: 2019/11/12 15:24
 * @Version: 1.0
 */
public class CustomProtocolEncoder implements ProtocolEncoder {

    private final Charset charset;
    public CustomProtocolEncoder() {
        this.charset = Charset.defaultCharset();
    }

    // 构造方法注入编码格式
    public  CustomProtocolEncoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        // 转为自定义协议包
        CustomPack customPack = (CustomPack) message;
        // 初始化化缓冲区
        IoBuffer buffer = IoBuffer.allocate(customPack.getLen()).setAutoExpand(true);
        // 设置长度、报头、内容
        buffer.putInt(customPack.getLen());
        buffer.put(customPack.getFlag());
        if (customPack.getContent() != null) {
            buffer.put(customPack.getContent().getBytes());
        }
        // 重置mask, 发送buffer
        buffer.flip();
        out.write(buffer);

    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {

    }
}
