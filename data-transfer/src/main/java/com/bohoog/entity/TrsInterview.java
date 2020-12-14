package com.bohoog.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Liangxp
 * @date 2019/11/22 15:57
 */
@Data
public class TrsInterview {
    private Long id;

    private Date createDate;

    private Date modifyDate;

    private String interviewComment;

    private String deleteContent;

    private Date endTime;

    private String enterCompany;

    private String guests;

    private String isAutoPublish;

    private Integer isNeedAdvance;

    private Integer isNeedItemMemoir;

    private Integer isPublic;

    private String keyword;

    private String liveUrl;

    private String onlineCompany;

    private String operIp;

    private String operUser;

    private String propaganda;

    private String recordAudioUrl;

    private String recordVideoUrl;

    private Long siteId;

    private Date startTime;

    private Integer status;

    private String title;

    private Integer type;

    private Date invalidTime;

    private Integer sortOrder;

    private Integer sortTopOrder;

    private Integer topType;

    private Integer category;

    private String exLink;
}