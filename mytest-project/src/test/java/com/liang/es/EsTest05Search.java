package com.liang.es;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Liangxp
 * @date 2020/12/25 11:48
 */
@Slf4j
@SpringBootTest
public class EsTest05Search {
    private String index = "sms_logs_index";

    @Resource
    private RestHighLevelClient client;

    @Test
    public void testRegexp() throws IOException {
        SearchRequest request = new SearchRequest(index);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.regexpQuery("mobile", "138[0-9]{8}"));
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("regexp查询：{}", hit);
        }
    }

    @Test
    public void testRange() throws IOException {
        SearchRequest request = new SearchRequest(index);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.rangeQuery("fee").gte(10).lte(20));
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("rang查询结果：{}", hit);
        }
    }

    @Test
    public void testWildcard() throws IOException {
        SearchRequest request = new SearchRequest(index);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.wildcardQuery("corpName", "海尔*"));
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("wildcard查询结果：{}", response);
        }
    }

    @Test
    public void testFuzzy() throws IOException {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.fuzzyQuery("corpName", "腾讯客堂").prefixLength(2));
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("fuzzy查询：{}", hit);
        }
    }

    @Test
    public void testPrefix() throws IOException {
        SearchRequest request = new SearchRequest(index);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.prefixQuery("corpName", "海"));
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("prefix查询结果：{}", hit);
        }
    }

    @Test
    public void testId() throws IOException {
        GetRequest request = new GetRequest(index, "1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        log.info("根据id查询结果：{}", response.getSourceAsMap());
    }

    @Test
    public void testIds() throws IOException {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.idsQuery().addIds("1", "2", "3"));
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("ids查询结果：{}", hit);
        }
    }

}
