package com.zwp.slcp.apicommon.entity;

public class MapUserAnoymousecommentKey {
    private Long anoymouseCommentId;

    private Long userId;

    public MapUserAnoymousecommentKey() {
    }

    public MapUserAnoymousecommentKey(Long anoymouseCommentId, Long userId) {
        this.anoymouseCommentId = anoymouseCommentId;
        this.userId = userId;
    }

    public Long getAnoymouseCommentId() {
        return anoymouseCommentId;
    }

    public void setAnoymouseCommentId(Long anoymouseCommentId) {
        this.anoymouseCommentId = anoymouseCommentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}