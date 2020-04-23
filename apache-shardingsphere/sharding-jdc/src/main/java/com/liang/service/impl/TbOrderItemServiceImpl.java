package com.liang.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.liang.mapper.TbOrderItemMapper;
import com.liang.domain.TbOrderItem;
import com.liang.service.TbOrderItemService;

/**
 * @author Liangxp
 * @date 2020/4/22 16:58
 */
@Service
public class TbOrderItemServiceImpl implements TbOrderItemService {

    @Resource
    private TbOrderItemMapper tbOrderItemMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tbOrderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TbOrderItem record) {
        return tbOrderItemMapper.insert(record);
    }

    @Override
    public int insertSelective(TbOrderItem record) {
        return tbOrderItemMapper.insertSelective(record);
    }

    @Override
    public TbOrderItem selectByPrimaryKey(Long id) {
        return tbOrderItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TbOrderItem record) {
        return tbOrderItemMapper.updateByPrimaryKeySelective(record);
    }

}
