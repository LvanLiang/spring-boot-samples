package com.liang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author Liangxp
 * @date 2020/7/17 16:47
 */
@Mapper
public interface CountNewsIndexRankMapper {
    @Select("select id, region_nodes regionNodes from organization")
    List<Map<String, Object>> list();

    @Update("update organization set region_code = #{regionNode} where id = #{id,jdbcType=BIGINT}")
    int update(@Param("id") Long id, @Param("regionNode") String regionNode);
}