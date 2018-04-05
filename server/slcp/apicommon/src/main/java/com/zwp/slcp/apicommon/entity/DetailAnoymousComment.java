package com.zwp.slcp.apicommon.entity;

/**
 * Created by ASUS on 2018/4/5.
 */
public class DetailAnoymousComment {
    private Long anoymousCommentId;

    private Long anoymousCommentAuthorId;

    private String anoymousCommentContent;

    private Integer anoymousListNumber;

    private Long anoymouseReplayUserid;

    private Integer anoymousCommentState;

    private Long createTime;

    private Long updateTime;

    private Long anoymousId;

    private String userAnonymouseName;

    private Integer approveNumber;

    private Integer userApproveType;

    public Long getAnoymousCommentId() {
        return anoymousCommentId;
    }

    public void setAnoymousCommentId(Long anoymousCommentId) {
        this.anoymousCommentId = anoymousCommentId;
    }

    public Long getAnoymousCommentAuthorId() {
        return anoymousCommentAuthorId;
    }

    public void setAnoymousCommentAuthorId(Long anoymousCommentAuthorId) {
        this.anoymousCommentAuthorId = anoymousCommentAuthorId;
    }

    public String getAnoymousCommentContent() {
        return anoymousCommentContent;
    }

    public void setAnoymousCommentContent(String anoymousCommentContent) {
        this.anoymousCommentContent = anoymousCommentContent;
    }

    public Integer getAnoymousListNumber() {
        return anoymousListNumber;
    }

    public void setAnoymousListNumber(Integer anoymousListNumber) {
        this.anoymousListNumber = anoymousListNumber;
    }

    public Long getAnoymouseReplayUserid() {
        return anoymouseReplayUserid;
    }

    public void setAnoymouseReplayUserid(Long anoymouseReplayUserid) {
        this.anoymouseReplayUserid = anoymouseReplayUserid;
    }

    public Integer getAnoymousCommentState() {
        return anoymousCommentState;
    }

    public void setAnoymousCommentState(Integer anoymousCommentState) {
        this.anoymousCommentState = anoymousCommentState;
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

    public Long getAnoymousId() {
        return anoymousId;
    }

    public void setAnoymousId(Long anoymousId) {
        this.anoymousId = anoymousId;
    }

    public String getUserAnonymouseName() {
        return userAnonymouseName;
    }

    public void setUserAnonymouseName(String userAnonymouseName) {
        this.userAnonymouseName = userAnonymouseName;
    }


    public Integer getApproveNumber() {
        return approveNumber;
    }

    public void setApproveNumber(Integer approveNumber) {
        this.approveNumber = approveNumber;
    }

    public Integer getUserApproveType() {
        return userApproveType;
    }

    public void setUserApproveType(Integer userApproveType) {
        this.userApproveType = userApproveType;
    }
}
