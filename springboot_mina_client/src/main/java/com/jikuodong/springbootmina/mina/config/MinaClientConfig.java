package com.jikuodong.springbootmina.mina.config;

import com.jikuodong.springbootmina.mina.codec.CustomProtocolCodecFactory;
import com.jikuodong.springbootmina.mina.filter.KeepAliveFactoryImpl;
import com.jikuodong.springbootmina.mina.handler.ClientHandler;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.integration.beans.InetSocketAddressEditor;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyEditor;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ProjectName: springboot_mina_client
 * @Package: com.jikuodong.springbootmina.mina.config
 * @ClassName: MinaClientConfig
 * @Author: JKD
 * @Description: mina客户端配置
 * @Date: 2019/11/13 17:03
 * @Version: 1.0
 */
@Configuration
public class MinaClientConfig {

    /**
     * 设置I/O接收器
     */
    private Map<Class<?>, Class<? extends PropertyEditor>> customEditors = new HashMap<>();

    @Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        customEditors.put(SocketAddress.class, InetSocketAddressEditor.class);
        CustomEditorConfigurer configurer = new CustomEditorConfigurer();
        configurer.setCustomEditors(customEditors);
        return configurer;
    }

    /**
     * 线程池filter
     */
    @Bean
    public ExecutorFilter executorFilter() {
        return new ExecutorFilter();
    }

    @Bean
    public MdcInjectionFilter mdcInjectionFilter() {
        return new MdcInjectionFilter(MdcInjectionFilter.MdcKey.remoteAddress);
    }

    /**
     * 编码器filter
     */
    @Bean
    public ProtocolCodecFilter protocolCodecFilter() {
        return new ProtocolCodecFilter(new CustomProtocolCodecFactory());
    }

    /**
     * 日志filter
     */
    @Bean
    public LoggingFilter loggingFilter() {
        LoggingFilter filter = new LoggingFilter();
        return  filter;
    }

    /**
     * 心跳filter
     * @return
     */
    @Bean
    public KeepAliveFilter keepAliveFilter() {
        // 客户端不需要发送心跳
        KeepAliveFactoryImpl keepAliveFactory = new KeepAliveFactoryImpl(false);
        // 注入心跳工厂，读写空闲
        KeepAliveFilter filter = new KeepAliveFilter(keepAliveFactory);
        return filter;
    }

    /**
     * 过滤器链
     * @param executorFilter
     * @param mdcInjectionFilter
     * @param protocolCodecFilter
     * @param loggingFilter
     * @param keepAliveFilter
     * @return
     */
    @Bean
    public DefaultIoFilterChainBuilder defaultIoFilterChainBuilder(ExecutorFilter executorFilter,
                                                                   MdcInjectionFilter mdcInjectionFilter,
                                                                   ProtocolCodecFilter protocolCodecFilter,
                                                                   LoggingFilter loggingFilter,
                                                                   KeepAliveFilter keepAliveFilter) {
        DefaultIoFilterChainBuilder filterChainBuilder = new DefaultIoFilterChainBuilder();
        Map<String, IoFilter> filters = new LinkedHashMap<>();
        filters.put("executor", executorFilter);
        filters.put("mdcInjectionFilter", mdcInjectionFilter);
        filters.put("protocolCodecFilter", protocolCodecFilter);
        filters.put("loggingFilter", loggingFilter);
        filters.put("keepAliveFilter", keepAliveFilter);
        filterChainBuilder.setFilters(filters);
        return filterChainBuilder;
    }

    /**
     * 创建连接
     * @param clientHandler
     * @param defaultIoFilterChainBuilder
     * @return
     */
    @Bean
    public NioSocketConnector nioSocketConnector(ClientHandler clientHandler,
                                                 DefaultIoFilterChainBuilder defaultIoFilterChainBuilder) {
        NioSocketConnector connector = new NioSocketConnector();
        // 设置连接超时时间
        connector.setConnectTimeoutCheckInterval(3000);
        // 设置处理handler
        connector.setHandler(clientHandler);
        // 绑定过滤器
        connector.setFilterChainBuilder(defaultIoFilterChainBuilder);
        return connector;
    }





























}
