package com.zwp.slcp.apicommon.entity;

public class Anoymous {
    private Long anoymousId;

    private Long anoymousAuthorId;

    private String anoymousTitle;

    private String anoymousContent;

    private Integer anoymousCommentNumber;

    private Integer anoymousState;

    private Long createTime;

    private Long updateTime;



    public Long getAnoymousId() {
        return anoymousId;
    }

    public void setAnoymousId(Long anoymousId) {
        this.anoymousId = anoymousId;
    }

    public Long getAnoymousAuthorId() {
        return anoymousAuthorId;
    }

    public void setAnoymousAuthorId(Long anoymousAuthorId) {
        this.anoymousAuthorId = anoymousAuthorId;
    }

    public String getAnoymousTitle() {
        return anoymousTitle;
    }

    public void setAnoymousTitle(String anoymousTitle) {
        this.anoymousTitle = anoymousTitle == null ? null : anoymousTitle.trim();
    }

    public String getAnoymousContent() {
        return anoymousContent;
    }

    public void setAnoymousContent(String anoymousContent) {
        this.anoymousContent = anoymousContent == null ? null : anoymousContent.trim();
    }

    public Integer getAnoymousCommentNumber() {
        return anoymousCommentNumber;
    }

    public void setAnoymousCommentNumber(Integer anoymousCommentNumber) {
        this.anoymousCommentNumber = anoymousCommentNumber;
    }

    public Integer getAnoymousState() {
        return anoymousState;
    }

    public void setAnoymousState(Integer anoymousState) {
        this.anoymousState = anoymousState;
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
}