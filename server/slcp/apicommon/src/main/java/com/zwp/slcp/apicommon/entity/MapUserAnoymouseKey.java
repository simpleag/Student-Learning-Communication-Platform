package com.zwp.slcp.apicommon.entity;

public class MapUserAnoymouseKey {
    private Long anoymouseId;

    private Long userId;

    public MapUserAnoymouseKey() {
    }

    public MapUserAnoymouseKey(Long anoymouseId, Long userId) {
        this.anoymouseId = anoymouseId;
        this.userId = userId;
    }

    public Long getAnoymouseId() {
        return anoymouseId;
    }

    public void setAnoymouseId(Long anoymouseId) {
        this.anoymouseId = anoymouseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}