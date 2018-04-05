package com.zwp.slcp.apicommon.entity;

public class Info {
    private Long infoId;

    private Long infoReceiveUserId;

    private String intoContent;

    private Integer infoType;

    private Integer infoState;

    private Long createTime;

    private Long updateTime;

    private Long infoTargetId;

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