package com.zwp.slcp.apicommon.entity;

/**
 * Created by ASUS on 2018/4/19.
 */
public class FullTag {
    private Integer tagId;

    private String tagName;

    private String tagInfo;

    private Integer articleNumber;

    private Integer discussNumber;

    private Integer userAttentionType;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagInfo() {
        return tagInfo;
    }

    public void setTagInfo(String tagInfo) {
        this.tagInfo = tagInfo;
    }

    public Integer getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(Integer articleNumber) {
        this.articleNumber = articleNumber;
    }

    public Integer getDiscussNumber() {
        return discussNumber;
    }

    public void setDiscussNumber(Integer discussNumber) {
        this.discussNumber = discussNumber;
    }

    public Integer getUserAttentionType() {
        return userAttentionType;
    }

    public void setUserAttentionType(Integer userAttentionType) {
        this.userAttentionType = userAttentionType;
    }
}
