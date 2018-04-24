package com.zwp.slcp.apicommon.entity;


public class MapUserArticle extends MapUserArticleKey {
    private Integer userApproveType;

    private Integer userFavoriteType;

    private Long createTime;

    private Long updateTime;

    public MapUserArticle() {
    }
    public MapUserArticle(Long articleId, Long userId) {
        super(articleId, userId);
    }

    public MapUserArticle(Integer userApproveType, Integer userFavoriteType, Long createTime, Long updateTime) {
        this.userApproveType = userApproveType;
        this.userFavoriteType = userFavoriteType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public MapUserArticle(Long articleId, Long userId, Integer userApproveType, Integer userFavoriteType, Long createTime, Long updateTime) {
        super(articleId, userId);
        this.userApproveType = userApproveType;
        this.userFavoriteType = userFavoriteType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getUserApproveType() {
        return userApproveType;
    }

    public void setUserApproveType(Integer userApproveType) {
        this.userApproveType = userApproveType;
    }

    public Integer getUserFavoriteType() {
        return userFavoriteType;
    }

    public void setUserFavoriteType(Integer userFavoriteType) {
        this.userFavoriteType = userFavoriteType;
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