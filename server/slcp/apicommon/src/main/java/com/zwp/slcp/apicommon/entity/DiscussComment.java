package com.zwp.slcp.apicommon.entity;

public class DiscussComment {
    private Long discussCommentId;

    private Long discussCommentAuthorId;

    private String discussCommentContent;

    private Integer discussListNumber;

    private Long discussReplayUserid;

    private Integer discussCommentState;

    private Long createTime;

    private Long updateTime;

    private Long discussId;

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
        this.discussCommentContent = discussCommentContent == null ? null : discussCommentContent.trim();
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
}