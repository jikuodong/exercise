package demo3;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;

/**
 * @ProjectName: mina
 * @Package: demo3
 * @ClassName: CustomProtocolEncoder
 * @Author: JKD
 * @Description: 自定义编码器
 * @Date: 2019/11/1 16:53
 * @Version: 1.0
 */
public class CustomProtocolEncoder implements ProtocolEncoder {
    // 编码格式
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
        IoBuffer buffer = IoBuffer.allocate(customPack.getLen()).setAutoExpand(true);
        // 设置长度
    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {

    }
}
