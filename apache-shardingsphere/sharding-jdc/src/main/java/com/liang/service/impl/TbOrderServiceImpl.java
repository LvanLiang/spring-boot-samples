package com.liang.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.liang.mapper.TbOrderMapper;
import com.liang.domain.TbOrder;
import com.liang.service.TbOrderService;

/**
 * @author Liangxp
 * @date 2020/4/22 16:58
 */
@Service
public class TbOrderServiceImpl implements TbOrderService {

    @Resource
    private TbOrderMapper tbOrderMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tbOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TbOrder record) {
        return tbOrderMapper.insert(record);
    }

    @Override
    public int insertSelective(TbOrder record) {
        return tbOrderMapper.insertSelective(record);
    }

    @Override
    public TbOrder selectByPrimaryKey(Long id) {
        return tbOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TbOrder record) {
        return tbOrderMapper.updateByPrimaryKeySelective(record);
    }

}
