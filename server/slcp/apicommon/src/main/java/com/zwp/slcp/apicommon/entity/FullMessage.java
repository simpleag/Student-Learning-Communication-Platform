package com.zwp.slcp.apicommon.entity;

/**
 * Created by ASUS on 2018/4/3.
 */
public class FullMessage {
    private Long messageId;

    private Long messageAuthorId;

    private String messageAuthorName;

    private String messageContent;

    private Long messageReceiveUserid;

    private String messageReceiveName;

    private Integer messageState;

    private Long createTime;

    private Long updateTime;

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

    public String getMessageAuthorName() {
        return messageAuthorName;
    }

    public void setMessageAuthorName(String messageAuthorName) {
        this.messageAuthorName = messageAuthorName;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Long getMessageReceiveUserid() {
        return messageReceiveUserid;
    }

    public void setMessageReceiveUserid(Long messageReceiveUserid) {
        this.messageReceiveUserid = messageReceiveUserid;
    }

    public String getMessageReceiveName() {
        return messageReceiveName;
    }

    public void setMessageReceiveName(String messageReceiveName) {
        this.messageReceiveName = messageReceiveName;
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
