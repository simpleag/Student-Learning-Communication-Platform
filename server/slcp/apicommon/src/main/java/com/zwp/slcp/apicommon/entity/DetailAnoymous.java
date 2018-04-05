package com.zwp.slcp.apicommon.entity;

/**
 * Created by ASUS on 2018/4/5.
 */
public class DetailAnoymous {
    private Long anoymousId;

    private Long anoymousAuthorId;

    private String anoymousTitle;

    private String anoymousContent;

    private Integer anoymousCommentNumber;

    private Integer anoymousState;

    private Long createTime;

    private Long updateTime;

    private Integer approveNumber;

    private Integer favoriteNumber;

    private String userAnonymouseName;

    private String userAnonymouseHonor;

    private Integer userApproveType;

    private Integer userFavoriteType;

    public Long getAnoymousId() {
        return anoymousId;
    }

    public void setAnoymousId(Long anoymousId) {
        this.anoymousId = anoymousId;
    }

    public Long getAnoymousAuthorId() {
        return anoymousAuthorId;
    }

    public void setAnoymousAuthorId(Long anoymousAuthorId) {
        this.anoymousAuthorId = anoymousAuthorId;
    }

    public String getAnoymousTitle() {
        return anoymousTitle;
    }

    public void setAnoymousTitle(String anoymousTitle) {
        this.anoymousTitle = anoymousTitle;
    }

    public String getAnoymousContent() {
        return anoymousContent;
    }

    public void setAnoymousContent(String anoymousContent) {
        this.anoymousContent = anoymousContent;
    }

    public Integer getAnoymousCommentNumber() {
        return anoymousCommentNumber;
    }

    public void setAnoymousCommentNumber(Integer anoymousCommentNumber) {
        this.anoymousCommentNumber = anoymousCommentNumber;
    }

    public Integer getAnoymousState() {
        return anoymousState;
    }

    public void setAnoymousState(Integer anoymousState) {
        this.anoymousState = anoymousState;
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

    public String getUserAnonymouseName() {
        return userAnonymouseName;
    }

    public void setUserAnonymouseName(String userAnonymouseName) {
        this.userAnonymouseName = userAnonymouseName;
    }

    public String getUserAnonymouseHonor() {
        return userAnonymouseHonor;
    }

    public void setUserAnonymouseHonor(String userAnonymouseHonor) {
        this.userAnonymouseHonor = userAnonymouseHonor;
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
}
