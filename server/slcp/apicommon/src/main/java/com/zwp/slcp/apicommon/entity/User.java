package com.zwp.slcp.apicommon.entity;

public class User {
    private Long userId;

    private String userName;

    private String userPwd;

    private String userPhoneNumber;

    private String userLoginId;

    private String userPicUrl;

    private String userHonor;

    private String userAnonymouseName;

    private String userAnonymouseHonor;

    private String userIntroduce;

    private Integer userGetApproveNumber;

    private Integer userArticleNumber;

    private Integer userFollowNumber;

    private Integer userDiscussNumber;

    private Integer userAnoymouseNumber;

    private Integer userCommentNumber;

    private Integer userAttentionNumber;

    private Integer userFavoriteNumber;

    private Integer userApproveNumber;

    private Long userBorn;

    private String userSex;

    private String userSchool;

    private String userEducation;

    private Long createTime;

    private Long updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber == null ? null : userPhoneNumber.trim();
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId == null ? null : userLoginId.trim();
    }

    public String getUserPicUrl() {
        return userPicUrl;
    }

    public void setUserPicUrl(String userPicUrl) {
        this.userPicUrl = userPicUrl == null ? null : userPicUrl.trim();
    }

    public String getUserHonor() {
        return userHonor;
    }

    public void setUserHonor(String userHonor) {
        this.userHonor = userHonor == null ? null : userHonor.trim();
    }

    public String getUserAnonymouseName() {
        return userAnonymouseName;
    }

    public void setUserAnonymouseName(String userAnonymouseName) {
        this.userAnonymouseName = userAnonymouseName == null ? null : userAnonymouseName.trim();
    }

    public String getUserAnonymouseHonor() {
        return userAnonymouseHonor;
    }

    public void setUserAnonymouseHonor(String userAnonymouseHonor) {
        this.userAnonymouseHonor = userAnonymouseHonor == null ? null : userAnonymouseHonor.trim();
    }

    public String getUserIntroduce() {
        return userIntroduce;
    }

    public void setUserIntroduce(String userIntroduce) {
        this.userIntroduce = userIntroduce == null ? null : userIntroduce.trim();
    }

    public Integer getUserGetApproveNumber() {
        return userGetApproveNumber;
    }

    public void setUserGetApproveNumber(Integer userGetApproveNumber) {
        this.userGetApproveNumber = userGetApproveNumber;
    }

    public Integer getUserArticleNumber() {
        return userArticleNumber;
    }

    public void setUserArticleNumber(Integer userArticleNumber) {
        this.userArticleNumber = userArticleNumber;
    }

    public Integer getUserFollowNumber() {
        return userFollowNumber;
    }

    public void setUserFollowNumber(Integer userFollowNumber) {
        this.userFollowNumber = userFollowNumber;
    }

    public Integer getUserDiscussNumber() {
        return userDiscussNumber;
    }

    public void setUserDiscussNumber(Integer userDiscussNumber) {
        this.userDiscussNumber = userDiscussNumber;
    }

    public Integer getUserAnoymouseNumber() {
        return userAnoymouseNumber;
    }

    public void setUserAnoymouseNumber(Integer userAnoymouseNumber) {
        this.userAnoymouseNumber = userAnoymouseNumber;
    }

    public Integer getUserCommentNumber() {
        return userCommentNumber;
    }

    public void setUserCommentNumber(Integer userCommentNumber) {
        this.userCommentNumber = userCommentNumber;
    }

    public Integer getUserAttentionNumber() {
        return userAttentionNumber;
    }

    public void setUserAttentionNumber(Integer userAttentionNumber) {
        this.userAttentionNumber = userAttentionNumber;
    }

    public Integer getUserFavoriteNumber() {
        return userFavoriteNumber;
    }

    public void setUserFavoriteNumber(Integer userFavoriteNumber) {
        this.userFavoriteNumber = userFavoriteNumber;
    }

    public Integer getUserApproveNumber() {
        return userApproveNumber;
    }

    public void setUserApproveNumber(Integer userApproveNumber) {
        this.userApproveNumber = userApproveNumber;
    }

    public Long getUserBorn() {
        return userBorn;
    }

    public void setUserBorn(Long userBorn) {
        this.userBorn = userBorn;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool == null ? null : userSchool.trim();
    }

    public String getUserEducation() {
        return userEducation;
    }

    public void setUserEducation(String userEducation) {
        this.userEducation = userEducation == null ? null : userEducation.trim();
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