package com.zwp.slcp.apicommon.entity;

public class MapUserArticleKey {
    private Long articleId;

    private Long userId;

    public MapUserArticleKey() {
    }

    public MapUserArticleKey(Long articleId, Long userId) {
        this.articleId = articleId;
        this.userId = userId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}