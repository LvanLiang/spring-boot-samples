package com.bohoog.test;

import com.bohoog.mapper.target.TrsPublicApplicationMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 依申请公开数据导入
 * @author Liangxp
 * @date 2019/11/22 15:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrsPublicApplicationService{

    @Autowired
    private TrsPublicApplicationMapper trsPublicApplicationMapper;

}
