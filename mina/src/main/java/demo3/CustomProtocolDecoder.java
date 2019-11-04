package demo3;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;

/**
 * @ProjectName: mina
 * @Package: demo3
 * @ClassName: CustomProtocolDecoder
 * @Author: JKD
 * @Description: 自定义解码器
 * @Date: 2019/11/4 13:26
 * @Version: 1.0
 */
public class CustomProtocolDecoder extends CumulativeProtocolDecoder {
    private final Charset charset;

    public CustomProtocolDecoder() {
        this.charset = Charset.defaultCharset();
    }

    // 构造方法注入编码格式
    public CustomProtocolDecoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        // 包头的长度
        final int PACK_HEAD_LEN = 5;
        // 拆包时，如果可读数据的长度小于包头的长度，就不进行读取
        if (in.remaining() < PACK_HEAD_LEN) {
            return false;
        }
        if (in.remaining() >1) {
            // 标记设为当前
            in.mark();
            // 获取总长度
            int length = in.getInt(in.position());
            // 如果可读数据的长度 小于 总长度 - 包头的长度，则结束拆包，等待下一次
            if (in.remaining() < (length - PACK_HEAD_LEN)) {
                in.reset();
                return false;
            } else {
                // 重置，并读取一条完成记录
                in.reset();
                byte[] bytes = new byte[length];
                // 获取长度4个字节，版本1个字节，内容
                in.get(bytes, 0, length);
                byte flag = bytes[4];
                String content = new String(bytes, PACK_HEAD_LEN, length-PACK_HEAD_LEN,charset);
                // 封装为自定义的java对象
                CustomPack pack = new CustomPack(flag, content);
                out.write(pack);
                // 如果读取一条记录后，还存在数据（粘包），则再次进行调用
                return in.remaining() > 0;
            }
        }
     return false;
    }
}
