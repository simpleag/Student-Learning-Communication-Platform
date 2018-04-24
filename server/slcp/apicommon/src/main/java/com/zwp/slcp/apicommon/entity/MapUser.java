package com.zwp.slcp.apicommon.entity;

public class MapUser extends MapUserKey {
    private Integer userAttentionType;

    private Long createTime;

    private Long updateTime;

    public MapUser() {
    }

//    public static void main(String[] args) {
//        MapUser mapUser = new MapUser(1L,2L,1,1111L,1111L);
//        System.out.println(mapUser.getUserId());
//    }

    public MapUser(Long userId, Long user2Id, Integer userAttentionType, Long createTime, Long updateTime) {
        this.setUserId(userId);
        this.setUser2Id(user2Id);
        this.userAttentionType = userAttentionType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public MapUser(Long userId, Long user2Id) {
        this.setUserId(userId);
        this.setUser2Id(user2Id);
        this.userAttentionType = 1;
        this.createTime = System.currentTimeMillis();
        this.updateTime = System.currentTimeMillis();
    }

    public Integer getUserAttentionType() {
        return userAttentionType;
    }

    public void setUserAttentionType(Integer userAttentionType) {
        this.userAttentionType = userAttentionType;
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