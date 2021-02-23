package com.bohoog.test;

import com.bohoog.mapper.targets.TrsInterviewMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 在线访谈数据导入
 * @author Liangxp
 * @date 2019/11/22 15:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrsInterviewService{
    @Resource
    private TrsInterviewMapper trsInterviewMapper;

}
