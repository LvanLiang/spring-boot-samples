package com.bohoog.mapper.targets;

import com.bohoog.entity.TrsInterview;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 在线访谈
 * @author Liangxp
 * @date 2019/11/22 15:57
 */
public interface TrsInterviewMapper {
    int insert(TrsInterview record);

    int insertList(@Param("list") List<TrsInterview> list);
}