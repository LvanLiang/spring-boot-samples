package com.bohoog.mapper.source;

import org.apache.ibatis.annotations.Insert;

import java.util.Map;

/**
 * 用来测试数据库
 * @author Liangxp
 * @date 2019/11/21 10:01
 */
public interface SourceUserMapper {
    @Insert("insert into user(id, name) values(#{id}, #{username})")
    int insert(Map paraMap);
}
