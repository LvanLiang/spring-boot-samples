package com.liang.es;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.BoostingQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Liangxp
 * @date 2020/12/26 15:23
 */
@Slf4j
@SpringBootTest
public class EsTest06Search {
    private String index = "sms_logs_index";

    @Resource
    private RestHighLevelClient client;

    @Test
    public void testHighlight() throws IOException {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("smsContent", "团队"));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        // 高亮显示的文字长度、标签样式
        highlightBuilder.field("smsContent", 20).preTags("<font color='red'>").postTags("</font>");
        searchSourceBuilder.highlighter(highlightBuilder);
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("高亮查询结果：{}", hit.getHighlightFields().get("smsContent"));
        }
    }

    /**
     * 根据查询条件去查询文档，不去计算分数，而且filter会对经常被过滤的数据进行缓存。
     * @throws IOException
     */
    @Test
    public void testFilter() throws IOException {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // bool复合查询条件
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.filter(QueryBuilders.termQuery("province", "北京"));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("fee").lte(50));
        searchSourceBuilder.query(boolQueryBuilder);
        request.source(searchSourceBuilder);
        // 执行查询并输出结果
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("filter查询结果：{}", hit.getSourceAsMap());
        }
    }

    @Test
    public void testBoosting() throws IOException {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoostingQueryBuilder boostingQueryBuilder = QueryBuilders.boostingQuery(
                QueryBuilders.matchQuery("smsContent", "战士"),
                QueryBuilders.matchQuery("smsContent", "团队")).negativeBoost(0.6F);
        searchSourceBuilder.query(boostingQueryBuilder);
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("boost查询结果；{}", hit.getSourceAsMap());
        }

    }


    @Test
    public void testBool() throws IOException {
        // 1.创建request
        SearchRequest request = new SearchRequest(index);
        // 2.指定查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        // #省是 晋城 或者北京
        boolQueryBuilder.should(QueryBuilders.termQuery("province", "北京"));
        boolQueryBuilder.should(QueryBuilders.termQuery("province", "晋城"));
        //# 运营商不能是联通
        boolQueryBuilder.mustNot(QueryBuilders.termQuery("operatorId", 2));

        searchSourceBuilder.query(boolQueryBuilder);
        request.source(searchSourceBuilder);

        // 3.执行查询输出结果
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("复合查询结果：{}", hit);
        }
    }

    @Test
    public void testDeleteByQuery() throws IOException {
        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest(index);
        deleteByQueryRequest.setQuery(QueryBuilders.rangeQuery("fee").lt(20));

        BulkByScrollResponse response = client.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);
        log.info("删除结果：{}", response.getTotal());
    }


    @Test
    public void testScroll() throws IOException {
        // 创建request
        SearchRequest request = new SearchRequest(index);
        // 指定scroll过期时间
        request.scroll(TimeValue.timeValueMinutes(1L));
        // 指定查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(4);
        searchSourceBuilder.sort("fee", SortOrder.DESC);
        request.source(searchSourceBuilder);
        // 执行查询，获取返回结果中的scrollId
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        String scrollId = response.getScrollId();
        log.info("-------------------首页数据-------------------");
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("查询结果：{}", hit);
        }


        while (true) {
            // 创建scrollRequest
            SearchScrollRequest searchScrollRequest = new SearchScrollRequest(scrollId);
            // 指定scroll有效时间
            searchScrollRequest.scroll(TimeValue.timeValueMinutes(1L));
            // 执行查询
            SearchResponse scrollResponse = client.scroll(searchScrollRequest, RequestOptions.DEFAULT);
            // 判断查询结果，输出数据
            SearchHit[] hits = scrollResponse.getHits().getHits();
            if (null != hits && hits.length > 0) {
                log.info("-------------------下一页数据-------------------");
                for (SearchHit hit : hits) {
                    log.info("查询结果：{}", hit);
                }
            } else {
                log.info("----------------------结束----------------------");
                break;
            }
        }

        // 删除scroll
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        log.info("删除scroll结果：{}", clearScrollResponse.isSucceeded());
    }
}
