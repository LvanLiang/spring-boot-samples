package com.bohoog.mapper.targets;

import com.bohoog.entity.TrsGovmsgboxReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Liangxp
 * @date 2019/11/21 20:47
 */
public interface TrsGovmsgboxReplyMapper {
    int insert(TrsGovmsgboxReply record);

    int insertList(@Param("list") List<TrsGovmsgboxReply> list);
}