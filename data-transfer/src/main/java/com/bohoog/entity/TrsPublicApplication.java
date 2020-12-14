package com.bohoog.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Liangxp
 * @date 2019/11/22 15:59
 */
@Data
public class TrsPublicApplication {
    private Long id;

    private Integer applyType;

    private String compName;

    private String contactAddress;

    private String contactPerson;

    private String content;

    private Date createDate;

    private String cruser;

    private String deptId;

    private String deptName;

    private String docNum;

    private Date endDate;

    private String flowStatus;

    private String idNumber;

    private Integer informationType;

    private Integer isAlerted;

    private Integer isCancel;

    private Integer isSendMsg;

    private Integer isTrush;

    private String legalPerson;

    private String legalPersonidnumber;

    private String licenseNumber;

    private String mail;

    private Date modifyDate;

    private String organizationCode;

    private String phone;

    private String resultStatus;

    private String serialNum;

    private Long siteId;

    private String socialCreditCode;

    private String title;

    private Integer totalDealDay;

    private Date trushDate;

    private String trushReason;

    private Integer trushSenderId;

    private String userComp;

    private Integer userType;

    private String username;

    private String zipCode;

    private String arePublish;

    private String isPublish;

    private String netAttachIds;

    private String photoAttachIds;

    private String queryPwd;

    private Integer isSendEmail;

    private String fax;

    private Date acceptTime;
}