package com.zwp.slcp.frontapi.service;

import com.zwp.slcp.apicommon.entity.LimitUser;
import com.zwp.slcp.apicommon.entity.User;
import com.zwp.slcp.apicommon.entity.UserFollow;
import com.zwp.slcp.frontapi.service.failCallBack.UserFailCallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ASUS on 2018/4/23.
 */
@FeignClient(value = "SQLSERVER", fallback = UserFailCallBack.class)
public interface UserServie {
    @RequestMapping("/user/findOne")
    @ResponseBody
    User findOne(Long userId);

    @RequestMapping("/user/create")
    @ResponseBody
    public String createUser(User user);

    @RequestMapping("/user/findOneByUserPhoneNumber")
    @ResponseBody
    public User findOneByUserPhoneNumber(String userPhoneNumber);

    @RequestMapping("/user/findOneLimitById")
    @ResponseBody
    public LimitUser findOneLimitById(Long userId);

    @RequestMapping("/user/listUserFollows")
    @ResponseBody
    List<UserFollow> listUserFollows(Long userId);

    @RequestMapping("/user/listUserAttention")
    @ResponseBody
    public List<UserFollow> listUserAttention(Long userId);

    @RequestMapping("/user/userAttention")
    @ResponseBody
    public String userAttention(Long userId, Long user2Id);

    @RequestMapping("/user/userUnAttention")
    @ResponseBody
    public String userUnAttention(Long userId, Long user2Id);

    @RequestMapping("/user/userUpdate")
    @ResponseBody
    public String userUpdate(User user);

}
