package com.zwp.slcp.apicommon.entity;

public class MapUserDiscusscommentKey {
    private Long disscussCommentId;

    private Long userId;

    public MapUserDiscusscommentKey() {
    }

    public MapUserDiscusscommentKey(Long disscussCommentId, Long userId) {
        this.disscussCommentId = disscussCommentId;
        this.userId = userId;
    }

    public Long getDisscussCommentId() {
        return disscussCommentId;
    }

    public void setDisscussCommentId(Long disscussCommentId) {
        this.disscussCommentId = disscussCommentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}