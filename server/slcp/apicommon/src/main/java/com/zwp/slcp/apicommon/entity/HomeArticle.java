package com.zwp.slcp.apicommon.entity;

/**
 * Created by ASUS on 2018/4/1.
 */
public class HomeArticle {
    private Long articleId;

    private Long articleAuthorId;

    private String articleTitle;


    private Integer articleCommentNumber;

    private Integer articleTagId;

    private Integer articleState;

    private String articlePicUrl;

    private Long createTime;

    private Long updateTime;

    private Integer approveNumber;

    private Integer favoriteNumber;

    private String userName;

    private String userPicUrl;

    private String tagName;

    private Integer userApproveType;

    private Integer userFavoriteType;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getArticleAuthorId() {
        return articleAuthorId;
    }

    public void setArticleAuthorId(Long articleAuthorId) {
        this.articleAuthorId = articleAuthorId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }


    public Integer getArticleCommentNumber() {
        return articleCommentNumber;
    }

    public void setArticleCommentNumber(Integer articleCommentNumber) {
        this.articleCommentNumber = articleCommentNumber;
    }

    public Integer getArticleTagId() {
        return articleTagId;
    }

    public void setArticleTagId(Integer articleTagId) {
        this.articleTagId = articleTagId;
    }

    public Integer getArticleState() {
        return articleState;
    }

    public void setArticleState(Integer articleState) {
        this.articleState = articleState;
    }

    public String getArticlePicUrl() {
        return articlePicUrl;
    }

    public void setArticlePicUrl(String articlePicUrl) {
        this.articlePicUrl = articlePicUrl;
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
