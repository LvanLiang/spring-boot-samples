package com.liang.es;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Liangxp
 * @date 2020/12/22 15:31
 */
@Slf4j
@SpringBootTest
public class EsTest02Index {

    private String index = "person";

    @Resource
    private RestHighLevelClient client;

    @Test
    public void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        log.info("删除结果：{}", delete);
    }

    @Test
    public void testExist() throws IOException {
        GetIndexRequest request = new GetIndexRequest(index);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        log.info("索引是否存在：{}", exists);
    }


    @Test
    public void testCreateIndex() throws IOException {
        // 1.准备关于索引的settings,2个分配，1一个备份
        Settings.Builder settings = Settings.builder().put("number_of_shards", 2).put("number_of_replicas", 1);

        // 2.设置索引的mapping
        XContentBuilder mappings = JsonXContent.contentBuilder()
                .startObject()
                    .startObject("properties")
                        .startObject("name")
                            .field("type", "text")
                        .endObject()
                        .startObject("age")
                            .field("type", "integer")
                        .endObject()
                        .startObject("birthday")
                            .field("type", "date")
                            .field("format", "yyyy-MM-dd")
                        .endObject()
                    .endObject()
                .endObject();

        // 3. 将settings和mappings 封装到到一个Request对象中
        CreateIndexRequest request = new CreateIndexRequest(index).settings(settings).mapping(mappings);

        // 4.使用client
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);

        log.info("response:{}", response);
    }

}
