package com.zwp.slcp.apicommon.entity;

/**
 * Created by ASUS on 2018/4/1.
 */
//用户关注的和用户的粉丝信息
public class UserFollow {
    private Long userId;

    private String userName;

    private String userPicUrl;

    private Integer userAttentionType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Integer getUserAttentionType() {
        return userAttentionType;
    }

    public void setUserAttentionType(Integer userAttentionType) {
        this.userAttentionType = userAttentionType;
    }
}
