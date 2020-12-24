package com.liang.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liang.domain.SmsLogs;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Liangxp
 * @date 2020/12/22 16:58
 */
@Slf4j
@SpringBootTest
public class EsTest04Search {

    private ObjectMapper mapper = new ObjectMapper();

    private String index = "sms_logs_index";

    @Resource
    private RestHighLevelClient client;

    @Test
    public void testMultiMatch() throws IOException {
        SearchRequest request = new SearchRequest(index);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery("北京","province", "smsContent"));
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("查询结果总数：{}", response.getHits().getTotalHits().value);
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("查询结果：{}", hit);
        }
    }

    @Test
    public void testBoolMatch() throws IOException {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("smsContent"," 战士 团队").operator(Operator.AND));
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("总结果数:{}", response.getHits().getTotalHits().value);
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("查询结果：{}", hit.getSourceAsMap());
        }
    }

    @Test
    public void testMatchSearch() throws IOException {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("province", "北京"));
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("查询结果：{}", hit.getSourceAsMap());
        }
    }


    @Test
    public void testMatchAllSearch() throws IOException {
        SearchRequest request = new SearchRequest(index);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("总共记录条数；{}", response.getHits().getTotalHits().value);
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("插叙结果：{}", hit.getSourceAsMap());
        }
    }

    @Test
    public void testTermsSearch() throws IOException {
        SearchRequest request = new SearchRequest(index);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);
        searchSourceBuilder.query(QueryBuilders.termsQuery("province","重庆", "北京"));
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("查询结果：{}", hit.getSourceAsMap());
        }
    }

    @Test
    public void testTermSearch() throws IOException {
        // 1.创建request对象
        SearchRequest request = new SearchRequest(index);

        // 2.查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);
        searchSourceBuilder.query(QueryBuilders.termQuery("province", "重庆"));
        request.source(searchSourceBuilder);

        // 3.执行查询
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        // 4.输出插叙结果
        for (SearchHit hit : response.getHits().getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            log.info("查询结果：{}", sourceAsMap);
        }
    }

    @Test
    public void testBulkCreateDoc() throws Exception {
        // 1.准备多个json 对象
        String longcode = "1008687";
        String mobile = "138340658";
        List<String> companies = new ArrayList<>();
        companies.add("腾讯课堂");
        companies.add("阿里旺旺");
        companies.add("海尔电器");
        companies.add("海尔智家公司");
        companies.add("格力汽车");
        companies.add("苏宁易购");
        List<String> provinces = new ArrayList<>();
        provinces.add("北京");
        provinces.add("重庆");
        provinces.add("上海");
        provinces.add("晋城");

        BulkRequest bulkRequest = new BulkRequest();
        for (int i = 1; i < 16; i++) {
            Thread.sleep(1000);
            SmsLogs smsLogs = new SmsLogs();
            smsLogs.setId(i);
            smsLogs.setCreateDate(new Date());
            smsLogs.setSendDate(new Date());
            smsLogs.setLongCode(longcode + i);
            smsLogs.setMobile(mobile + 2 * i);
            smsLogs.setCorpName(companies.get(i % 5));
            smsLogs.setSmsContent(SmsLogs.doc.substring((i - 1) * 100, i * 100));
            smsLogs.setState(i % 2);
            smsLogs.setOperatorId(i % 3);
            smsLogs.setProvince(provinces.get(i % 4));
            smsLogs.setIpAddr("127.0.0." + i);
            smsLogs.setReplyTotal(i * 3);
            smsLogs.setFee(i * 6 + "");
            String json1 = mapper.writeValueAsString(smsLogs);
            bulkRequest.add(new IndexRequest(index).id(smsLogs.getId().toString()).source(json1, XContentType.JSON));
            System.out.println("数据" + i + smsLogs.toString());
        }

        // 3.client 执行
        BulkResponse responses = client.bulk(bulkRequest, RequestOptions.DEFAULT);

        // 4.输出结果
        System.out.println(responses.getItems().toString());
    }

    @Test
    public void testCreateIndex() throws IOException {
        Settings.Builder settings = Settings.builder().put("number_of_shards", 3).put("number_of_replicas", 1);

        XContentBuilder mappings = JsonXContent.contentBuilder()
                .startObject()
                    .startObject("properties")
                        .startObject("createDate")
                            .field("type", "text")
                        .endObject()
                        .startObject("sendDate")
                            .field("type", "date")
                            .field("format", "yyyy-MM-dd")
                        .endObject()
                        .startObject("longCode")
                            .field("type", "text")
                        .endObject()
                        .startObject("mobile")
                            .field("type", "text")
                        .endObject()
                        .startObject("corpName")
                            .field("type", "text")
                            .field("analyzer", "ik_max_word")
                        .endObject()
                        .startObject("smsContent")
                            .field("type", "text")
                            .field("analyzer", "ik_max_word")
                        .endObject()
                        .startObject("state")
                            .field("type", "integer")
                        .endObject()
                        .startObject("operatorid")
                            .field("type", "integer")
                        .endObject()
                        .startObject("province")
                            .field("type", "keyword")
                        .endObject()
                        .startObject("ipAddr")
                            .field("type", "text")
                        .endObject()
                        .startObject("replyTotal")
                            .field("type", "integer")
                        .endObject()
                        .startObject("fee")
                            .field("type", "integer")
                        .endObject()
                    .endObject()
                .endObject();

        CreateIndexRequest request = new CreateIndexRequest(index).settings(settings).mapping(mappings);

        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        log.info("创建结果：{}", response.toString());
    }
}
