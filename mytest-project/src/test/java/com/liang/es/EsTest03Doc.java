package com.liang.es;

import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liang.domain.EsPerson;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 文档操作
 * @author Liangxp
 * @date 2020/12/22 16:04
 */
@Slf4j
@SpringBootTest
public class EsTest03Doc {

    private ObjectMapper mapper = new ObjectMapper();

    private String index = "person";

    @Resource
    private RestHighLevelClient client;

    @Test
    public void testDeleteDoc() throws IOException {
        DeleteRequest request = new DeleteRequest(index, "5fe1b2f9d9457dc9a35fc86f");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        log.info("删除结果：{}", response.getResult());
    }

    @Test
    public void testUpdateDoc() throws IOException {
        // 1.创建要跟新的Map
        Map<String, Object> docMap = new HashMap<>();
        docMap.put("name","张三三三");

        // 2.创建request, 将doc 封装进去
        UpdateRequest request = new UpdateRequest(index, "5fe1b1e2d9456b65995c5ddc");
        request.doc(docMap);

        // 3. client 去操作 request
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        log.info("更新结果：{}", response.getResult());
    }

    @Test
    public void testAddDoc() throws IOException {
        // 准备一个json数据
        EsPerson person = new EsPerson(IdUtil.objectId(), "王五", 25, new Date());
        String json = mapper.writeValueAsString(person);

        // 创建request对象
        IndexRequest request = new IndexRequest(index).id(person.getId());
        request.source(json, XContentType.JSON);

        // 使用client操作request，生成doc
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        log.info("创建结果：{}", response.getResult());

    }
}
