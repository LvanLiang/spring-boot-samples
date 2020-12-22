package com.liang.es;

import cn.hutool.json.JSONUtil;
import com.liang.domain.EsAccount;
import com.liang.domain.EsUser;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 操作索引
 * @author Liangxp
 * @date 2020/12/12 16:33
 */
@Slf4j
@SpringBootTest
public class EsTest {

    @Resource
    private RestHighLevelClient client;

    @Test
    void contextLoads() {
        log.info("esClient:{}",client);
    }

    @Test
    public void searchFilterField() throws IOException {
        // 1. 创建检索请求
        SearchRequest searchRequest = new SearchRequest();
        // 1.1 指定索引
        searchRequest.indices("bank");
        // 1.2 构造检索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("age", 38));
        log.info("检索条件:{}", searchSourceBuilder);
        searchRequest.source(searchSourceBuilder);
        // 2. 执行检索条件
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);

        // 3.将检索结果封装为Bean
        SearchHit[] hits = search.getHits().getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            EsAccount esAccount = JSONUtil.toBean(sourceAsString, EsAccount.class);
            log.info("检索结果：{}", esAccount);
        }
    }


    @Test
    public void searchData() throws IOException {
        // 1. 创建检索请求
        SearchRequest searchRequest = new SearchRequest();
        // 1.1 指定索引
        searchRequest.indices("bank");
        // 1.2 构造检索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("address", "mill"));
        // 1.2.1 按照年龄分布进行聚合
        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
        searchSourceBuilder.aggregation(ageAgg);

        // 1.2.2 计算平均年龄
        AvgAggregationBuilder ageAvg = AggregationBuilders.avg("ageAvg").field("age");
        searchSourceBuilder.aggregation(ageAvg);

        // 1.2.3 计算平均薪资
        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
        searchSourceBuilder.aggregation(balanceAvg);

        log.info("检索条件:{}", searchSourceBuilder);
        searchRequest.source(searchSourceBuilder);
        // 2. 执行检索条件
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);

        // 3.将检索结果封装为Bean
        SearchHit[] hits = search.getHits().getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            EsAccount esAccount = JSONUtil.toBean(sourceAsString, EsAccount.class);
            log.info("检索结果：{}", esAccount);
        }


        // 4. 获取聚合信息
        // List<Aggregation> aggregations = search.getAggregations().asList();
        Aggregations aggregations = search.getAggregations();
        Terms ageAgg1 = aggregations.get("ageAgg");
        for (Terms.Bucket bucket : ageAgg1.getBuckets()) {
            log.info("年龄：{}，人数：{}", bucket.getKeyAsString(), bucket.getDocCount());
        }

        Avg ageAvg1 = aggregations.get("ageAvg");
        log.info("平均年龄:{}", ageAvg1.getValue());

        Avg balanceAvg1 = aggregations.get("balanceAvg");
        System.out.println("平均薪资："+balanceAvg1.getValue());
    }

    @Test
    public void indexData() throws IOException {
        EsUser esUser = new EsUser();
        esUser.setUserName("随风奔跑是方向");
        esUser.setAge(22);
        esUser.setGender("男");
        String jsonStr = JSONUtil.toJsonStr(esUser);
        IndexRequest indexRequest = new IndexRequest("users");
        // 设置要保存的内容
        indexRequest.source(jsonStr, XContentType.JSON);
        indexRequest.id("T2niX3YByGdxsMGHkuoK");
        // 执行创建索引和保存数据
        IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
        DocWriteResponse.Result result = index.getResult();
        int status = index.status().getStatus();
        log.info("结果：{},状态：{}", result, status);
    }

}
