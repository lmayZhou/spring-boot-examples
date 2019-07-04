package com.lmaye.spring.boot.elasticsearch.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * -- ES搜索引擎 Config
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2019/6/11 23:47 星期二
 */
@Configuration
public class ElasticsearchConfig {
    /**
     * TransportClient
     *
     * @return TransportClient
     * @throws UnknownHostException 未知主机异常
     */
    /*@Bean
    public TransportClient transportClient() throws UnknownHostException {
        return new PreBuiltXPackTransportClient(Settings.builder()
            .put("cluster.name", "es-cluster").put("xpack.security.user", "elastic:123456").build())
            .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.0.10"), 9300))
            .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.0.10"), 9301))
            .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.0.10"), 9302));
    }*/
}
