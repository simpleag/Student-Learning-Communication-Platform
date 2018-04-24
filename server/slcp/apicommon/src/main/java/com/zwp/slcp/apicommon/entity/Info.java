package com.zwp.slcp.apicommon.entity;

/**
 * type 1为用户赞同文章 2为用户收藏文章 3为赞同讨论 4为收藏讨论 5为用户赞同匿名讨论 6为收藏匿名讨论
 * 7文章评论 8讨论评论 9匿名讨论评价
 * 10文章评论点赞 11 12
 */
public class Info {
    private Long infoId;

    private Long infoReceiveUserId;

    private String intoContent;

    private Integer infoType;

    private Integer infoState;

    private Long createTime;

    private Long updateTime;

    private Long infoTargetId;

    public Info() {
    }

    public Info(Long infoReceiveUserId, Integer infoType, Integer infoState, Long infoTargetId) {
        this.infoReceiveUserId = infoReceiveUserId;
        this.infoType = infoType;
        this.infoState = infoState;
        this.updateTime = System.currentTimeMillis();
        this.infoTargetId = infoTargetId;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public Long getInfoReceiveUserId() {
        return infoReceiveUserId;
    }

    public void setInfoReceiveUserId(Long infoReceiveUserId) {
        this.infoReceiveUserId = infoReceiveUserId;
    }

    public String getIntoContent() {
        return intoContent;
    }

    public void setIntoContent(String intoContent) {
        this.intoContent = intoContent == null ? null : intoContent.trim();
    }

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    public Integer getInfoState() {
        return infoState;
    }

    public void setInfoState(Integer infoState) {
        this.infoState = infoState;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getInfoTargetId() {
        return infoTargetId;
    }

    public void setInfoTargetId(Long infoTargetId) {
        this.infoTargetId = infoTargetId;
    }
}