package com.zwp.slcp.frontapi.service.failCallBack;

import com.zwp.slcp.apicommon.entity.LimitUser;
import com.zwp.slcp.apicommon.entity.User;
import com.zwp.slcp.apicommon.entity.UserFollow;
import com.zwp.slcp.frontapi.service.UserServie;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ASUS on 2018/4/23.
 */
@Component
public class UserFailCallBack implements UserServie {
    @Override
    public User findOne(Long userId) {
        return null;
    }

    @Override
    public String createUser(User user) {
        return null;
    }

    @Override
    public User findOneByUserPhoneNumber(String userPhoneNumber) {
        return null;
    }

    @Override
    public User findOneByUserLoginId(String userLogInId) {
        return null;
    }

    @Override
    public LimitUser findOneLimitById(Long userId) {
        return null;
    }

    @Override
    public List<UserFollow> listUserFollows(Long userId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public List<UserFollow> listUserAttention(Long userId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public String userAttention(Long userId, Long user2Id) {
        return null;
    }

    @Override
    public String userUnAttention(Long userId, Long user2Id) {
        return null;
    }

    @Override
    public String userUpdate(User user) {
        return null;
    }
}
