package com.zwp.slcp.apicommon.entity;

public class AnoymousComment {
    private Long anoymousCommentId;

    private Long anoymousCommentAuthorId;

    private String anoymousCommentContent;

    private Integer anoymousListNumber;

    private Long anoymouseReplayUserid;

    private Integer anoymousCommentState;

    private Long createTime;

    private Long updateTime;

    private Long anoymousId;

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
        this.anoymousCommentContent = anoymousCommentContent == null ? null : anoymousCommentContent.trim();
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
}