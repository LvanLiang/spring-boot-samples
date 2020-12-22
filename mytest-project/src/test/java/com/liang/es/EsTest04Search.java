package com.liang.es;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Liangxp
 * @date 2020/12/22 16:58
 */
@Slf4j
@SpringBootTest
public class EsTest04Search {
    private String index = "sms_logs_index";

    @Resource
    private RestHighLevelClient client;

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
                            .field("type", "text")
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

            CreateIndexRequest request = new CreateIndexRequest(index).settings(settings) .mapping(mappings);

            CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
            log.info("创建结果：{}", response.toString());
    }
}
