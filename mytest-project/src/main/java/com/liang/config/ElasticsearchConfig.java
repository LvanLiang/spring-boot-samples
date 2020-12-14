package com.liang.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1、导入依赖
 * 2、编写配置，给容器中注入一个RestHighLevelClient
 * 3、参照API https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-high.html
 * @author Liangxp
 * @date 2020/12/14 10:45
 */
@Configuration
public class ElasticsearchConfig {
    @Value("${es.host}")
    private String host;

    @Value("${es.port}")
    private Integer port;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(host, port, "http")));
        return client;
    }
}
