package com.bohoog.mapper.source;

import com.bohoog.entity.TrsGovmsgbox;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 信箱
 * @author Liangxp
 * @date 2019/11/21 15:43
 */
public interface SourceBoxMapper {

    /**
     * 获取表中的总条数
     * @param tableName 表名
     * @return 总条数
     */
    Long getRowsCount(@Param("tableName") String tableName);

    /**
     * 遵义市科学技术局数据
     * @return 信件集合
     */
    List<TrsGovmsgbox> listZyskxjsjBox();

    /**
     * 遵义市人力资源和社会保障
     * [SEX_AUTHOR] 提问者性别0:男 1：女
     * [AUTHOR] 提问人姓名
     * [ADDR] 提问者地址
     * [ISCHECKED] '0'未公开[待分配] '2'未公开[已回复] '3'公开[已回复]
     * [ORG] 处理人
     * [ORG_USER] 处理人id
     * [IDENTITYS] 个人身份
     * @return 信件集合
     */
    List<TrsGovmsgbox> listZysrlzyBox();


    /**
     * 榕江县人民政府
     * @return 信件集合
     */
    List<TrsGovmsgbox> listRjBox();


    /**
     * 黔西南州人民政府
     * @return 信件集合
     */
    List<TrsGovmsgbox> listQxnBox();

}
