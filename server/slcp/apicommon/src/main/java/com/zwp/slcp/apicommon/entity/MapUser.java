package com.zwp.slcp.apicommon.entity;

public class MapUser extends MapUserKey {
    private Integer userAttentionType;

    private Long createTime;

    private Long updateTime;

    public Integer getUserAttentionType() {
        return userAttentionType;
    }

    public void setUserAttentionType(Integer userAttentionType) {
        this.userAttentionType = userAttentionType;
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