package com.zwp.slcp.apicommon.entity;

public class MapUserArticlecommentKey {
    private Long articleCommentId;

    private Long userId;

    public MapUserArticlecommentKey() {
    }

    public MapUserArticlecommentKey(Long articleCommentId, Long userId) {
        this.articleCommentId = articleCommentId;
        this.userId = userId;
    }

    public Long getArticleCommentId() {
        return articleCommentId;
    }

    public void setArticleCommentId(Long articleCommentId) {
        this.articleCommentId = articleCommentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}