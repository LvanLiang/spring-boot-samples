package com.bohoog.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Liangxp
 * @date 2019/11/22 9:46
 */
@Data
public class TrsGovmsgbox {
    private Long id;

    /**
     * 不管这个是什么，0就能展示出来了
     */
    private Long parentId = 0L;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建提交时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 是否公开 0：不公开 1：公开
     */
    private Integer arepublic = 1;

    /**
     * 事发区域
     */
    private String area;

    private String attachs;

    /**
     * 证据号码
     */
    private String cardid;

    /**
     * 证件类型
     */
    private String cardtype;

    /**
     * 事发城市
     */
    private String city;

    /**
     * 来信人通讯地址
     */
    private String address;

    /**
     * 信件内容
     */
    private String content;

    /**
     * 来信用户ip
     */
    private String crip;

    /**
     * 来信人姓名
     */
    private String cruser;

    /**
     * 来信人姓名
     */
    private String username;

    /**
     * 处理部门id
     */
    private Long dealdeptid;

    /**
     * 处理部门
     */
    private String dealdeptname;

    /**
     * 邮编
     */
    private String districtCode;

    /**
     * 来信人邮箱
     */
    private String email;

    /**
     * 来信人电话
     */
    private String phone;

    /**
     * 诉求类型"1">建议 "2">表扬 "3">咨询 "8">投诉
     */
    private String govmsgboxtype;

    /**
     * 是否匿名 0：不匿名 1：匿名
     */
    private Integer isAnonymous;

    /**
     * 信件编号
     */
    private String queryNumber;

    /**
     * 性别 1：男 2：女
     */
    private Integer sex;

    /**
     * -1：延期审核 3：待分配 4:待回复 5：待审核 6：已超期 7：已办结 8：已办理 9：废件箱
     */
    private Integer status;

    /**
     * 站点id
     */
    private Long siteid;

    /**
     * 默认未删除
     */
    private Integer delayFlag = -1;

    // ===============回复信息===================
    /**
     * 回复内容
     */
    private String reply;

    /**
     * 回复用户
     */
    private String replyUser;

    /**
     * 回复时间
     */
    private Date replyTime;
    // ===============回复信息===================

    private Date finishtime;

    private String forwardUserName;

    private String govmsgboxDesc;

    private Integer govmsgboxflag;

    private String govmsgboxtype1;

    private String htmlcontent;

    private Integer isapply;

    private Integer isBack;

    private Integer isDeadline;

    private Integer isMagorMsg;

    private Integer ispublic;

    private Integer isreply;

    private Integer isSuperviseFlag;

    private Integer isUnionDeptAllReply;

    private String location;

    private String operip;

    private String operuser;

    private String otherTag;

    private String province;

    private Date publictime;

    private String recommendTag;

    private String region;

    private Integer score;

    private String street;

    private Date submitTime;

    private Integer thumbStatus;

    private String thumbnails;

    private Date toassignTime;

    private Date toexamineTime;

    private Date toreplyTime;

    private String dealDeptName;

    private String externalId;

    private String isAnonymousLetter;

    private String queryPwd;

    private Long appId;

    private Long initialAppId;

    private Long initialSiteId;

    private String isForward;

    private Integer initialIsPublic;


    private Integer isWaitDoTurnMultiApply;

    private Integer timeLeft;

    private Integer remind;


    private String career;

    private String nativePlace;

    private String nickName;

    private Long dealuserid;

    private Long forwardDeptId;

    private Long examineDeptId;

    private Long examineUserId;

    private Integer lastCooperateTargeTyp;

    private Date handleTime;

    private Date trashTime;

    private Date lastReplyTime;

    private Date countRemainDayStartTime;

    private Date delayApplyTime;

    private Integer totalDays;

    private String extra1 ;
    private String extra10;
    private String extra2 ;
    private String extra3 ;
    private String extra4 ;
    private String extra5 ;
    private String extra6 ;
    private String extra7 ;
    private String extra8 ;
    private String extra9 ;

}