package com.liang;

import com.github.pagehelper.PageHelper;
import com.liang.domain.TbOrder;
import com.liang.domain.TbOrderItem;
import com.liang.mapper.TbOrderItemMapper;
import com.liang.mapper.TbOrderMapper;
import com.liang.utils.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试增删改查
 * @author Liangxp
 * @date 2020/4/22 16:37
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingJdbcTests {
    @Resource
    private TbOrderMapper tbOrderMapper;

    @Test
    public void testInsertOrder() {
        TbOrder tbOrder = new TbOrder();
        tbOrder.setId(IdUtil.getId());
        tbOrder.setUserId(4L);
        tbOrder.setOrderId(4L);
        tbOrder.setName("梁效平4");
        tbOrderMapper.insert(tbOrder);
    }

    @Test
    public void testSelectAll() {
        PageHelper.startPage(1,5);
        List<TbOrder> tbOrders = tbOrderMapper.listAll();
        for (TbOrder tbOrder : tbOrders) {
            log.info("订单信息:{}", tbOrder);
        }
        log.info("第二页开始》》》》》》》》》》》");
        PageHelper.startPage(2,5);
        List<TbOrder> tbOrders2 = tbOrderMapper.listAll();
        for (TbOrder tbOrder : tbOrders2) {
            log.info("订单信息:{}", tbOrder);
        }
    }

    @Test
    public void testUpdate() {
        TbOrder tbOrder = new TbOrder();
        tbOrder.setId(1344115040059424L);
        tbOrder.setName("梁效平111111");
        tbOrderMapper.updateByPrimaryKeySelective(tbOrder);
    }

}
