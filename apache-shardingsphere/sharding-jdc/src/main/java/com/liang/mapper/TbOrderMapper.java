package com.liang.mapper;

import com.liang.domain.TbOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Liangxp
 * @date 2020/4/22 16:58
 */
@Mapper
public interface TbOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbOrder record);

    int insertSelective(TbOrder record);

    TbOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbOrder record);

    List<TbOrder> listAll();

}