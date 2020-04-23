package com.liang.service;

import com.liang.domain.TbOrderItem;

/**
 * @author Liangxp
 * @date 2020/4/22 16:58
 */
public interface TbOrderItemService {


    int deleteByPrimaryKey(Long id);

    int insert(TbOrderItem record);

    int insertSelective(TbOrderItem record);

    TbOrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbOrderItem record);

}
