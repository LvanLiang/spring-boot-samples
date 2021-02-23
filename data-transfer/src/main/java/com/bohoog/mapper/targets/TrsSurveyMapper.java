package com.bohoog.mapper.targets;

import com.bohoog.entity.TrsSurvey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 在线调查
 * @author Liangxp
 * @date 2019/11/22 15:59
 */
public interface TrsSurveyMapper {

    int insert(TrsSurvey record);

    int insertList(@Param("list") List<TrsSurvey> list);
}