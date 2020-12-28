package com.liang.es;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.metrics.Cardinality;
import org.elasticsearch.search.aggregations.metrics.ExtendedStats;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Liangxp
 * @date 2020/12/28 10:52
 */
@Slf4j
@SpringBootTest
public class EsTest07Search {

    private String indexSms = "sms_logs_index";

    private String indexBank = "bank";

    @Resource
    private RestHighLevelClient client;


    @Test
    public void testExtendedState() throws IOException {
        SearchRequest request = new SearchRequest(indexSms);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.aggregation(AggregationBuilders.extendedStats("agg").field("fee"));
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        ExtendedStats agg = response.getAggregations().get("agg");
        log.info("最大值：{}，最小值：{}", agg.getMaxAsString(), agg.getMinAsString());
    }

    @Test
    public void testAggRange() throws IOException {
        SearchRequest request = new SearchRequest(indexSms);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.aggregation(AggregationBuilders.range("agg").field("fee")
                .addUnboundedTo(30)
                .addRange(30, 60)
                .addUnboundedFrom(60));
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        Range agg = response.getAggregations().get("agg");
        for (Range.Bucket bucket : agg.getBuckets()) {
            String key = bucket.getKeyAsString();
            Object from = bucket.getFrom();
            Object to = bucket.getTo();
            long docCount = bucket.getDocCount();
            log.info("key:{}, from:{}, to:{}, docCount:{}", key, from, to, docCount);
        }
    }

    @Test
    public void testAggCardinality() throws IOException {
        SearchRequest request = new SearchRequest(indexBank);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.aggregation(AggregationBuilders.cardinality("genderAgg").field("gender.keyword"));
        request.source(searchSourceBuilder);
        // 3.执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        // 4.输出返回结果
        Cardinality agg = response.getAggregations().get("genderAgg");
        log.info("去重查询结果：{}", agg.getValue());
    }
}
