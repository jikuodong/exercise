package com.jikuodong.springboot_mina.mina.service.config;

import com.jikuodong.springboot_mina.mina.service.codec.CustomProtocolCodeFactory;
import com.jikuodong.springboot_mina.mina.service.filter.KeepAliveFactoryImpl;
import com.jikuodong.springboot_mina.mina.service.handler.RequestHandler;
import com.jikuodong.springboot_mina.mina.service.handler.ServiceHandler;
import com.jikuodong.springboot_mina.mina.service.handler.impl.BindHanler;
import com.jikuodong.springboot_mina.mina.service.handler.impl.PushMessageHandler;
import com.jikuodong.springboot_mina.util.Const;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.integration.beans.InetSocketAddressEditor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyEditor;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ProjectName: springboot_mina
 * @Package: com.jikuodong.springboot_mina.mina.service.config
 * @ClassName: MinaServerConfig
 * @Author: JKD
 * @Description: Mina 服务端配置
 * @Date: 2019/11/12 15:44
 * @Version: 1.0
 */

@Configuration
public class MinaServerConfig {
    /**
     * 设置I/O接收器
     */
    private Map<Class<?>, Class<? extends PropertyEditor>> customEditors = new HashMap<>();

    private HashMap<String, RequestHandler> handlers = new HashMap<>();

    @Bean
    public BindHanler bindHanler() {
        return new BindHanler();
    }

    @Bean
    public PushMessageHandler pushMessageHandler() {
        return new PushMessageHandler();
    }

    @Bean
    public ServiceHandler serviceHandler(BindHanler bindHanler, PushMessageHandler pushMessageHandler) {
        handlers.put(Const.AUTHEN, bindHanler);
        handlers.put(Const.TIME_CHECK, pushMessageHandler);
        ServiceHandler serviceHandler = new ServiceHandler();
        serviceHandler.setHandlers(handlers);
        return serviceHandler;
    }

    @Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        customEditors.put(SocketAddress.class, InetSocketAddressEditor.class);
        CustomEditorConfigurer configurer = new CustomEditorConfigurer();
        configurer.setCustomEditors(customEditors);
        return configurer;
    }

    /**
     * 线程池filter
     * @return
     */
    @Bean
    public ExecutorFilter executorFilter() {
        return new ExecutorFilter();
    }

    /**
     * 日志信息注入过滤器，MDC(Mapped Diagnostic Context有译作线程映射表)是日志框架维护的一组信息键值对，可向日志输出信息中插入一些想要显示的内容。
     * @return
     */
    @Bean
    public MdcInjectionFilter mdcInjectionFilter() {
        return new MdcInjectionFilter(MdcInjectionFilter.MdcKey.remoteAddress);
    }

    /**
     * 编码器filter
     * @return
     */
    @Bean
    public ProtocolCodecFilter protocolCodecFilter() {
        return new ProtocolCodecFilter(new CustomProtocolCodeFactory());
    }

    /**
     * 日志filter
     * @return
     */
    @Bean
    public LoggingFilter loggingFilter() {
        return new LoggingFilter();
    }

    @Bean
    public KeepAliveFactoryImpl keepAliveFactoryImpl() {
        return new KeepAliveFactoryImpl(true);
    }

    /**
     * 心跳filter
     * @param keepAliveFactory
     * @return
     */
    @Bean
    public KeepAliveFilter keepAliveFilter(KeepAliveFactoryImpl keepAliveFactory){
        // 注入心跳工厂，读写空闲
        KeepAliveFilter filter = new KeepAliveFilter(keepAliveFactory, IdleStatus.BOTH_IDLE);
        // 设置是否forword到下一个filter
        filter.setForwardEvent(true);
        // 设置心跳频率
        filter.setRequestInterval(Const.IDELTIMEOUT);
        return filter;
    }

    /**
     * 过滤链
     */
    @Bean
    public DefaultIoFilterChainBuilder defaultIoFilterChainBuilder(ExecutorFilter executorFilter,
                                                                   MdcInjectionFilter mdcInjectionFilter,
                                                                   ProtocolCodecFilter protocolCodecFilter,
                                                                   LoggingFilter loggingFilter,
                                                                   KeepAliveFilter keepAliveFilter){
        DefaultIoFilterChainBuilder filterChainBuilder = new DefaultIoFilterChainBuilder();
        Map<String, IoFilter> filters = new LinkedHashMap<>();
        filters.put("mdcInjectionFilter",mdcInjectionFilter);
        filters.put("loggingFilter",loggingFilter);
        filters.put("protocolCodecFilter",protocolCodecFilter);
        filters.put("executorFilter",executorFilter);
        filters.put("keepAliveFilter",keepAliveFilter);
        filterChainBuilder.setFilters(filters);
        return filterChainBuilder;
    }

    /**
     * 创建连接
     */
    @Bean(initMethod = "init", destroyMethod = "dispose")
    public NioSocketAcceptor nioSocketAcceptor(ServiceHandler serviceHandler, DefaultIoFilterChainBuilder defaultIoFilterChainBuilder) {
        NioSocketAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, Const.IDELTIMEOUT);
        // 绑定过滤器
        acceptor.setFilterChainBuilder(defaultIoFilterChainBuilder);
        acceptor.setHandler(serviceHandler);
        return acceptor;
    }










}
