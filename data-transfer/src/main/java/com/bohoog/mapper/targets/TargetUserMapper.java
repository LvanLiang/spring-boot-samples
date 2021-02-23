package com.bohoog.mapper.targets;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * 用来测试数据库
 * @author Liangxp
 * @date 2019/11/21 10:01
 */
public interface TargetUserMapper {
    @Select("select id, username from user_sign where id = #{id}")
    Map<String, String> getUserById(@Param("id") String id);
}
