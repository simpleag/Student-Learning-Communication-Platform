package com.zwp.slcp.apicommon.entity;

/**
 * Created by ASUS on 2018/4/2.
 */
public class DetailDiscussComment {
    private Long discussCommentId;

    private Long discussCommentAuthorId;

    private String discussCommentContent;

    private Integer discussListNumber;

    private Long discussReplayUserid;

    private Integer discussCommentState;

    private Long createTime;

    private Long updateTime;

    private Long discussId;

    private String userName;

    private String userPicUrl;

    private Integer approveNumber;

    private Integer userApproveType;

    public Long getDiscussCommentId() {
        return discussCommentId;
    }

    public void setDiscussCommentId(Long discussCommentId) {
        this.discussCommentId = discussCommentId;
    }

    public Long getDiscussCommentAuthorId() {
        return discussCommentAuthorId;
    }

    public void setDiscussCommentAuthorId(Long discussCommentAuthorId) {
        this.discussCommentAuthorId = discussCommentAuthorId;
    }

    public String getDiscussCommentContent() {
        return discussCommentContent;
    }

    public void setDiscussCommentContent(String discussCommentContent) {
        this.discussCommentContent = discussCommentContent;
    }

    public Integer getDiscussListNumber() {
        return discussListNumber;
    }

    public void setDiscussListNumber(Integer discussListNumber) {
        this.discussListNumber = discussListNumber;
    }

    public Long getDiscussReplayUserid() {
        return discussReplayUserid;
    }

    public void setDiscussReplayUserid(Long discussReplayUserid) {
        this.discussReplayUserid = discussReplayUserid;
    }

    public Integer getDiscussCommentState() {
        return discussCommentState;
    }

    public void setDiscussCommentState(Integer discussCommentState) {
        this.discussCommentState = discussCommentState;
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

    public Long getDiscussId() {
        return discussId;
    }

    public void setDiscussId(Long discussId) {
        this.discussId = discussId;
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
