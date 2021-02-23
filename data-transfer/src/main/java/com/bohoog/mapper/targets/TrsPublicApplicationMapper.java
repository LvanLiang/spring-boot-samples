package com.bohoog.mapper.targets;

import com.bohoog.entity.TrsPublicApplication;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 依申请公开
 * @author Liangxp
 * @date 2019/11/22 15:59
 */
public interface TrsPublicApplicationMapper {

    int insert(TrsPublicApplication record);

    int insertList(@Param("list") List<TrsPublicApplication> list);
}