package com.liang;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.liang.mapper.CountNewsIndexRankMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Liangxp
 * @date 2020/8/18 14:40
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTest {

    @Resource
    private CountNewsIndexRankMapper countNewsIndexRankMapper;

    @Test
    public void testInsert(){
        List<Map<String, Object>> list = countNewsIndexRankMapper.list();
        for (Map<String, Object> map : list) {
            String regionNodes = map.get("regionNodes").toString();
            String[] split = regionNodes.split(",");
            String regionNode = split[split.length - 1];
            countNewsIndexRankMapper.update((long)map.get("id"), regionNode);
            log.info("预警监管新增结果：{}", map);
        }
        PrintStream out = System.out;
        InputStream in = System.in;
    }

    public static void main(String[] args) {
        String dateStr1 = "2017-03-01 23:34:23";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2017-03-01 23:33:23";
        Date date2 = DateUtil.parse(dateStr2);

        //相差一个月，31天
        long betweenDay = DateUtil.between(date1, date2, DateUnit.SECOND);
        System.out.println(betweenDay);
    }
}
