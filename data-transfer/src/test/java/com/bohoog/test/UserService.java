package com.bohoog.test;

import com.bohoog.mapper.source.SourceUserMapper;
import com.bohoog.mapper.target.TargetUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author Liangxp
 * @date 2019/11/21 10:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private SourceUserMapper sourceUserMapper;

    @Autowired
    private TargetUserMapper targetUserMapper;

    @Test
    public void testUser(){
        Map<String, String> userMap = targetUserMapper.getUserById("2c90162160a03c390160a1567a230000");
        logger.info("查到的信息{}", userMap);
        int rows = sourceUserMapper.insert(userMap);
        logger.info("rows: {}", rows);
    }
}
