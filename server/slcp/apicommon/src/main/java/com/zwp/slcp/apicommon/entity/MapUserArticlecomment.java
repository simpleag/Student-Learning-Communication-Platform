package com.zwp.slcp.apicommon.entity;

public class MapUserArticlecomment extends MapUserArticlecommentKey {
    private Integer userApproveType;

    private Long createTime;

    private Long updateTime;

    public MapUserArticlecomment() {
    }

    public MapUserArticlecomment(Integer userApproveType) {
        this.userApproveType = userApproveType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public MapUserArticlecomment(Long articleCommentId, Long userId, Integer userApproveType) {
        super(articleCommentId, userId);
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