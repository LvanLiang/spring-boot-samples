package com.bohoog.mapper.targets;

import com.bohoog.entity.TrsGovmsgbox;
import com.bohoog.entity.TrsGovmsgboxReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Liangxp
 * @date 2019/11/22 9:46
 */
public interface TrsGovmsgboxMapper {

    int insert(TrsGovmsgbox record);

    int insertList(@Param("list") List<TrsGovmsgbox> list);

    int insertReply(TrsGovmsgboxReply record);
}