package com.zwp.slcp.apicommon.entity;

public class Message {
    private Long messageId;

    private Long messageAuthorId;

    private String messageContent;

    private Long messageReceiveUserid;

    private Integer messageState;

    private Long createTime;

    private Long updateTime;

    public Message() {
    }

    public Message(Long messageId) {
        this.messageId = messageId;
        this.updateTime = System.currentTimeMillis();
    }

    public Message(Long messageAuthorId, String messageContent, Long messageReceiveUserid) {
        this.messageAuthorId = messageAuthorId;
        this.messageContent = messageContent;
        this.messageReceiveUserid = messageReceiveUserid;
        this.messageState = 1;
        this.createTime = System.currentTimeMillis();
        this.updateTime = System.currentTimeMillis();
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMessageAuthorId() {
        return messageAuthorId;
    }

    public void setMessageAuthorId(Long messageAuthorId) {
        this.messageAuthorId = messageAuthorId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public Long getMessageReceiveUserid() {
        return messageReceiveUserid;
    }

    public void setMessageReceiveUserid(Long messageReceiveUserid) {
        this.messageReceiveUserid = messageReceiveUserid;
    }

    public Integer getMessageState() {
        return messageState;
    }

    public void setMessageState(Integer messageState) {
        this.messageState = messageState;
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