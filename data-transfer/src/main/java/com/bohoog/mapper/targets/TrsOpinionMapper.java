package com.bohoog.mapper.targets;

import com.bohoog.entity.TrsOpinion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 意见征集
 * @author Liangxp
 * @date 2019/11/22 15:59
 */
public interface TrsOpinionMapper {

    int insert(TrsOpinion record);

    int insertList(@Param("list") List<TrsOpinion> list);
}