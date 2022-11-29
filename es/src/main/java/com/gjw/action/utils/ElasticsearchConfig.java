package com.gjw.action.utils;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-01 16:17
 * @describe:
 **/
import lombok.Setter;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sc.elasticsearch")
@Setter
public class ElasticsearchConfig {
    @Bean
    // 硬编码的值可以设置到配置文件，通过@Value读取
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));
        return client;
    }
}

