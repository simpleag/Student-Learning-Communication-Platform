package com.zwp.slcp.apicommon.entity;

public class MapUserDiscussKey {
    private Long discussId;

    private Long userId;

    public MapUserDiscussKey() {
    }

    public MapUserDiscussKey(Long discussId, Long userId) {
        this.discussId = discussId;
        this.userId = userId;
    }

    public Long getDiscussId() {
        return discussId;
    }

    public void setDiscussId(Long discussId) {
        this.discussId = discussId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}