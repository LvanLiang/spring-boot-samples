package com.bohoog.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Liangxp
 * @date 2019/11/22 15:59
 */
@Data
public class TrsOpinion {
    private Long id;

    private String attachs;

    private Date beginTime;

    private String content;

    private String crIp;

    private Date crTime;

    private String crUser;

    private Date endTime;

    private String htmlContent;

    private Integer isPublic;

    private String operIp;

    private Date operTime;

    private String operUser;

    private String opinionDept;

    private String opinionResult;

    private Date publicTime;

    private String publicUser;

    private Long siteId;

    private Integer status;

    private String theme;

    private String thumbnails;

    private String type;

    private String delreason;

    private Date invalidTime;

    private Integer nOrder;

    private Integer nTopOrder;

    private Integer category;

    private String exLink;
}