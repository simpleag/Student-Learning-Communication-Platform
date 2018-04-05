package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.LimitUser;
import com.zwp.slcp.apicommon.entity.User;
import com.zwp.slcp.apicommon.entity.UserFollow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    int insertMapUser(Long userId, Long user2Id, Long updateTime, Long createTime, Integer userAttentionType);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //修改关注类型
    int updateUserMap(Long userId, Long user2Id, Long updateTime, Integer userAttentionType);

    User selectByUserPhone(String userPhoneNumber);

    User selectByUserLoginId(String userLoginId);

    int updatePwdByUserLoninId(String userPwd, Long updateTime, String userLoginId);

    int updateNumber(User user);

    //查找用户的基本信息，包括受到的消息和私信数量
    LimitUser selectUserLimitInfo(Long userId);

    //用户关注的人
    List<UserFollow> selectUserAttetion(Long userId);

    //用户的粉丝
    List<UserFollow> selectUserFollow(Long userId);


}