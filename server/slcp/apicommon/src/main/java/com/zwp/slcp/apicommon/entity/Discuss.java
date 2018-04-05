package com.zwp.slcp.apicommon.entity;

public class Discuss {
    private Long discussId;

    private Long discussAuthorId;

    private String discussTitle;

    private String discussContent;

    private Integer discussCommentNumber;

    private Integer discussTagId;

    private Integer discussState;

    private Long createTime;

    private Long updateTime;

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
        this.discussTitle = discussTitle == null ? null : discussTitle.trim();
    }

    public String getDiscussContent() {
        return discussContent;
    }

    public void setDiscussContent(String discussContent) {
        this.discussContent = discussContent == null ? null : discussContent.trim();
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
}