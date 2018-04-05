package com.zwp.slcp.apicommon.entity;

public class MapUserDiscuss extends MapUserDiscussKey {
    private Integer userApproveType;

    private Integer userFavoriteType;

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
}