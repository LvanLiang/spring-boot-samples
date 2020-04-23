package com.liang.service;

import com.liang.domain.TbOrder;

/**
 * @author Liangxp
 * @date 2020/4/22 16:58
 */
public interface TbOrderService {


    int deleteByPrimaryKey(Long id);

    int insert(TbOrder record);

    int insertSelective(TbOrder record);

    TbOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbOrder record);

}
