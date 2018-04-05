package com.zwp.slcp.apicommon.entity;

public class Article {
    private Long articleId;

    private Long articleAuthorId;

    private String articleTitle;

    private String articleContent;

    private Integer articleCommentNumber;

    private Integer articleTagId;

    private Integer articleState;

    private String articlePicUrl;

    private Long createTime;

    private Long updateTime;

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
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
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
        this.articlePicUrl = articlePicUrl == null ? null : articlePicUrl.trim();
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