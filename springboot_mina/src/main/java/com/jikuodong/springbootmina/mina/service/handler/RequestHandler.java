package com.jikuodong.springbootmina.mina.service.handler;

import com.jikuodong.springbootmina.mina.service.model.ReplyBody;
import com.jikuodong.springbootmina.mina.service.model.SentBody;
import com.jikuodong.springbootmina.mina.service.session.PcmSession;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service.handler
 * @ClassName: RequestHandler
 * @Author: JKD
 * @Description: Mina的请求处理接口，必须实现此接口
 * @Date: 2019/11/8 15:44
 * @Version: 1.0
 */
public interface RequestHandler {
    ReplyBody process(PcmSession session, SentBody sent);
}
