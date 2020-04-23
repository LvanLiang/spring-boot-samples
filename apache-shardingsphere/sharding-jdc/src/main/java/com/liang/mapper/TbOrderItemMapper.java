package com.liang.mapper;

import com.liang.domain.TbOrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Liangxp
 * @date 2020/4/22 16:58
 */
@Mapper
public interface TbOrderItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbOrderItem record);

    int insertSelective(TbOrderItem record);

    TbOrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbOrderItem record);

    List<TbOrderItem> listAll();
}