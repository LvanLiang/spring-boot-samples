package com.bohoog.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 来信回复
 * @author Liangxp
 * @date 2019/11/21 20:47
 */
@Data
@Builder
public class TrsGovmsgboxReply {
    /**
     * 回复id
     */
    private Long replyId;

    /**
     * 来信id
     */
    private Long dataId;

    private String replyAttachs;

    /**
     * 回复内容
     */
    private String replycontent;

    private String replydept;

    private String replyip;

    /**
     * 回复时间
     */
    private Date replytime;

    private Integer replytype;

    /**
     * 回复部门
     */
    private String replyuser;

    private Integer score;

    /**
     * 站点id
     */
    private Long siteId;

    private String replyhtmlcontent;
}