package com.zwp.slcp.apicommon.entity;

/**
 * Created by ASUS on 2018/4/29.
 * Type 1 创建文章 2 赞同文章 3收藏文章
 * 4发表文章评论 5发表讨论
 * 6赞同文章评论 7赞同文章评论
 * 8用户关注其他用户
 */
public class Active {

    private Long userId;

    private Long targetId;

    private Integer activeType;

    private String activeContent;

    private Long createTime;

    public Active() {
    }

    public Active(Long userId, Long targetId, Integer activiteType, String activeContent) {
        this.userId = userId;
        this.targetId = targetId;
        this.activeType = activiteType;
        this.activeContent = activeContent;
        this.createTime = System.currentTimeMillis();
    }

    public String getActiveContent() {
        return activeContent;
    }

    public void setActiveContent(String activeContent) {
        this.activeContent = activeContent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Integer getActiveType() {
        return activeType;
    }

    public void setActiveType(Integer activeType) {
        this.activeType = activeType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
