package com.jikuodong.springbootmina.mina.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;

/*
 *
 * describe 自定义编码器
 * @author mingchenxu
 * @date 2018/7/23 9:54
 * @param  * @param null
 * @return
 */
public class CustomProtocolEncoder implements ProtocolEncoder {

    private final Charset charset;

    public CustomProtocolEncoder() {
        this.charset = Charset.defaultCharset();
    }

    // 构造方法注入编码格式
    public CustomProtocolEncoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        // 转为自定义协议包
        CustomPack customPack = (CustomPack) message;
        // 初始化缓冲区
        IoBuffer buffer = IoBuffer.allocate(customPack.getLen())
                                  .setAutoExpand(true);
        // 设置长度、报头、内容
        buffer.putInt(customPack.getLen());
        buffer.put(customPack.getFlag());
        if (customPack.getContent() != null) {
            buffer.put(customPack.getContent().getBytes("UTF-8"));
        }
        // 重置mask，发送buffer
        buffer.flip();
        out.write(buffer);
    }

    @Override
    public void dispose(IoSession session) throws Exception {

    }
}
