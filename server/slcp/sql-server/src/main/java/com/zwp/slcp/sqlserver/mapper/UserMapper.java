package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.LimitUser;
import com.zwp.slcp.apicommon.entity.User;
import com.zwp.slcp.apicommon.entity.UserFollow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    int insertMapUser(@Param("userId") Long userId, @Param("user2Id") Long user2Id,
                      @Param("updateTime") Long updateTime, @Param("createTime") Long createTime,
                      @Param("userAttentionType") Integer userAttentionType);

    User selectByPrimaryKey(Long userId);

    User selectByUserPhoneNumber(String userPhoneNumber);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //修改关注类型
    int updateUserMap(@Param("userId") Long userId, @Param("user2Id") Long user2Id, @Param("updateTime") Long updateTime,
                      @Param("userAttentionType") Integer userAttentionType);

    User selectByUserPhone(String userPhoneNumber);

    User selectByUserLoginId(String userLoginId);

    int updatePwdByUserLoninId(@Param("userPwd") String userPwd, @Param("updateTime") Long updateTime, @Param("userLoginId") String userLoginId);

    //变更用户某项统计的数量
    int updateNumber(User user);



    //查找用户的基本信息，包括受到的消息和私信数量
    LimitUser selectUserLimitInfo(@Param("userId") Long userId);

    //用户关注的人
    List<UserFollow> selectUserAttetion(Long userId);

    //用户的粉丝
    List<UserFollow> selectUserFollow(Long userId);


}