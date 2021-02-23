package com.bohoog.service;

import com.bohoog.mapper.targets.TrsInterviewMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Liangxp
 * @date 2019/11/22 15:57
 */
@Service
public class TrsInterviewService{
    @Resource
    private TrsInterviewMapper trsInterviewMapper;

}
