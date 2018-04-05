package com.zwp.slcp.apicommon.entity;

/**
 * Created by ASUS on 2018/4/2.
 */
public class HomeDiscuss {
    private Long discussId;

    private Long discussAuthorId;

    private String discussTitle;

    private Integer discussCommentNumber;

    private Integer discussTagId;

    private Integer discussState;

    private Long createTime;

    private Long updateTime;

    private Integer approveNumber;

    private Integer favoriteNumber;

    private String userName;

    private String userPicUrl;

    private String tagName;

    private Integer userFavoriteType;

    private Integer userApproveType;

    public Long getDiscussId() {
        return discussId;
    }

    public void setDiscussId(Long discussId) {
        this.discussId = discussId;
    }

    public Long getDiscussAuthorId() {
        return discussAuthorId;
    }

    public void setDiscussAuthorId(Long discussAuthorId) {
        this.discussAuthorId = discussAuthorId;
    }

    public String getDiscussTitle() {
        return discussTitle;
    }

    public void setDiscussTitle(String discussTitle) {
        this.discussTitle = discussTitle;
    }

    public Integer getDiscussCommentNumber() {
        return discussCommentNumber;
    }

    public void setDiscussCommentNumber(Integer discussCommentNumber) {
        this.discussCommentNumber = discussCommentNumber;
    }

    public Integer getDiscussTagId() {
        return discussTagId;
    }

    public void setDiscussTagId(Integer discussTagId) {
        this.discussTagId = discussTagId;
    }

    public Integer getDiscussState() {
        return discussState;
    }

    public void setDiscussState(Integer discussState) {
        this.discussState = discussState;
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

    public Integer getApproveNumber() {
        return approveNumber;
    }

    public void setApproveNumber(Integer approveNumber) {
        this.approveNumber = approveNumber;
    }

    public Integer getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setFavoriteNumber(Integer favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPicUrl() {
        return userPicUrl;
    }

    public void setUserPicUrl(String userPicUrl) {
        this.userPicUrl = userPicUrl;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getUserFavoriteType() {
        return userFavoriteType;
    }

    public void setUserFavoriteType(Integer userFavoriteType) {
        this.userFavoriteType = userFavoriteType;
    }

    public Integer getUserApproveType() {
        return userApproveType;
    }

    public void setUserApproveType(Integer userApproveType) {
        this.userApproveType = userApproveType;
    }
}
