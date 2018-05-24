package com.zwp.slcp.frontapi.service;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.LimitUser;
import com.zwp.slcp.apicommon.entity.MapUser;
import com.zwp.slcp.apicommon.entity.User;
import com.zwp.slcp.apicommon.entity.UserFollow;
import com.zwp.slcp.frontapi.service.failCallBack.UserFailCallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ASUS on 2018/4/23.
 */
@FeignClient(value = "SQLSERVER", fallback = UserFailCallBack.class)
public interface UserServie {
    @RequestMapping("/user/findOne")
    @ResponseBody
    User findOne(@RequestParam(value = "userId") Long userId);

    @RequestMapping("/user/create")
    @ResponseBody
    String createUser(@RequestBody User user);

    @RequestMapping("/user/findOneByUserPhoneNumber")
    @ResponseBody
    User findOneByUserPhoneNumber(@RequestParam(value = "userPhoneNumber") String userPhoneNumber);

    @RequestMapping("/user/findOneByUserLoginId")
    @ResponseBody
    User findOneByUserLoginId(@RequestParam(value = "userLogInId") String userLogInId);

    @RequestMapping("/user/findOneLimitById")
    @ResponseBody
    LimitUser findOneLimitById(@RequestParam(value = "userId") Long userId);

    @RequestMapping("/user/listUserFollows")
    @ResponseBody
    PageInfo<UserFollow> listUserFollows(@RequestParam(value = "userId") Long userId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/user/listUserAttention")
    @ResponseBody
    PageInfo<UserFollow> listUserAttention(@RequestParam(value = "userId") Long userId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/user/userAttention")
    @ResponseBody
    String userAttention(@RequestParam(value = "userId") Long userId, @RequestParam(value = "user2Id") Long user2Id);

    @RequestMapping("/user/userUnAttention")
    @ResponseBody
    String userUnAttention(@RequestParam(value = "userId") Long userId, @RequestParam(value = "user2Id") Long user2Id);

    @RequestMapping("/user/userUpdate")
    @ResponseBody
    String userUpdate(@RequestBody User user);

    @RequestMapping("/user/isAttention")
    @ResponseBody
    MapUser isAttention(@RequestParam(value = "user1Id") Long user1Id, @RequestParam(value = "user2Id") Long user2Id);

}
