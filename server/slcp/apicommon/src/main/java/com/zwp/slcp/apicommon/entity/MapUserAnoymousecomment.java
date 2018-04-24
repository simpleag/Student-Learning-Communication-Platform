package com.zwp.slcp.apicommon.entity;

public class MapUserAnoymousecomment extends MapUserAnoymousecommentKey {
    private Integer userApproveType;

    private Long createTime;

    private Long updateTime;

    public MapUserAnoymousecomment() {
    }

    public MapUserAnoymousecomment(Integer userApproveType) {
        this.userApproveType = userApproveType;
    }

    public MapUserAnoymousecomment(Long anoymouseCommentId, Long userId, Integer userApproveType) {
        super(anoymouseCommentId, userId);
        this.userApproveType = userApproveType;
    }

    public Integer getUserApproveType() {
        return userApproveType;
    }

    public void setUserApproveType(Integer userApproveType) {
        this.userApproveType = userApproveType;
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