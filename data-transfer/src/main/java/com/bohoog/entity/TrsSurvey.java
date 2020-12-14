package com.bohoog.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Liangxp
 * @date 2019/11/22 15:59
 */
@Data
public class TrsSurvey {
    private String id;

    private Long appid;

    private Integer count;

    private Date crTime;

    private String crUserId;

    private Date endTime;

    private Long moduleid;

    private String opIp;

    private Date opTime;

    private String opUserId;

    private String opUserName;

    private String prefix;

    private Date publicTime;

    private Long siteid;

    private Date startTime;

    private Integer status;

    private String title;

    private String url;

    private Integer visitCount;

    private String limitIp;

    private String limitNum;

    private String showResult;

    private Date invalidTime;

    private Integer nOrder;

    private Integer nTopOrder;

    private Integer category;

    private String exLink;

    private Date endDate;
}