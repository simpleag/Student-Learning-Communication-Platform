package com.zwp.slcp.apicommon.entity;

import org.omg.PortableInterceptor.INACTIVE;

/**
 * Created by ASUS on 2018/4/1.
 */
public class DetailArticleComment {
    private Long articleCommentId;

    private Long articleCommentAuthorId;

    private String articleCommentContent;

    private Integer articleListNumber;

    private Long articleReplayUserid;

    private Integer articleCommentState;

    private Long createTime;

    private Long updateTime;

    private String userName;

    private String userPicUrl;

    private Integer approveNumber;

    private Integer userApproveType;

    private Long articleId;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getArticleCommentId() {
        return articleCommentId;
    }

    public void setArticleCommentId(Long articleCommentId) {
        this.articleCommentId = articleCommentId;
    }

    public Long getArticleCommentAuthorId() {
        return articleCommentAuthorId;
    }

    public void setArticleCommentAuthorId(Long articleCommentAuthorId) {
        this.articleCommentAuthorId = articleCommentAuthorId;
    }

    public String getArticleCommentContent() {
        return articleCommentContent;
    }

    public void setArticleCommentContent(String articleCommentContent) {
        this.articleCommentContent = articleCommentContent;
    }

    public Integer getArticleListNumber() {
        return articleListNumber;
    }

    public void setArticleListNumber(Integer articleListNumber) {
        this.articleListNumber = articleListNumber;
    }

    public Long getArticleReplayUserid() {
        return articleReplayUserid;
    }

    public void setArticleReplayUserid(Long articleReplayUserid) {
        this.articleReplayUserid = articleReplayUserid;
    }

    public Integer getArticleCommentState() {
        return articleCommentState;
    }

    public void setArticleCommentState(Integer articleCommentState) {
        this.articleCommentState = articleCommentState;
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

    public Integer getApproveNumber() {
        return approveNumber;
    }

    public void setApproveNumber(Integer approveNumber) {
        this.approveNumber = approveNumber;
    }

    public Integer getUserApproveType() {
        return userApproveType;
    }

    public void setUserApproveType(Integer userApproveType) {
        this.userApproveType = userApproveType;
    }
}
