package com.liang.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author Liangxp
 * @date 2020/7/17 16:47
 */
@Data
public class CountNewsIndexRank {
    private Long id;

    /**
     * 单位id
     */
    private Long orgId;

    /**
     * 单位名称
     */
    private String orgName;

    /**
     * 单位类型(1省；2市州；3县)
     */
    private Integer orgCategory;

    /**
     * 单位所属地区
     */
    private String orgRegion;

    /**
     * 评估总分
     */
    private Integer totalScore;

    /**
     * 月份(格式yyyy-MM)
     */
    private String month;

    /**
     * 当月排名
     */
    private Integer currentMonthRank;

    /**
     * 上个月排名
     */
    private Integer lastMonthRank;

    /**
     * 得分明细：json数据
     */
    private String data;

    /**
     * 排序分类(按照单位表tag值)
     */
    private String rankCategory;

    /**
     * 添加时间
     */
    private Date gmtCreate;
}