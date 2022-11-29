package com.gjw.action.servics;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-01 16:25
 * @describe:
 **/

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *  es7.6.x 高级客户端测试 API
 */
@Service
public class ElasticsearchJdApplicationTests implements iservice{
    // 面向对象来操作
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;
    // 测试索引的创建 Request PUT kuang_index
    @Override
    public void testCreateIndex() throws IOException {
        // 1、创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("kuang_index");
        // 2、客户端执行请求 IndicesClient,请求后获得响应
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    // 测试获取索引,判断其是否存在
    public boolean testExistIndex(String index) throws IOException {
        GetIndexRequest request = new GetIndexRequest(index);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        return exists;
    }
    // 查询
    @Override
   public Object testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("gz_city_all");
        // 构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 构建查询语句
        searchSourceBuilder.query(QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("cityid",917))
                .should(QueryBuilders.termQuery("cityid",98))
                .should(QueryBuilders.termQuery("name","1234"))
        );
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        return JSON.toJSONString(searchResponse.getHits());
    }

    @Override
    public Object testSearch3(Object...objects) throws IOException {

        List lists = Collections.singletonList(Arrays.toString(objects));
        SearchRequest searchRequest = new SearchRequest();
        // 构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 构建查询语句
        searchSourceBuilder.query(QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("cityid",917))
                .should(QueryBuilders.termQuery("cityid",98))
                .should(QueryBuilders.termQuery("name","1234"))
        );
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        return JSON.toJSONString(searchResponse.getHits());
    }
}