package com.zwp.slcp.sqlserver.controller;

import com.github.pagehelper.PageHelper;
import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.sqlserver.mapper.MapUserMapper;
import com.zwp.slcp.sqlserver.mapper.UserMapper;
import com.zwp.slcp.sqlserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ASUS on 2018/4/5.
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MapUserMapper mapUserMapper;

    @RequestMapping("/findOne")
    @ResponseBody
    public User findOne(Long userId){
        if (StringUtils.isBlank(userId)) {
            return null;
        } else {
            return userMapper.selectByPrimaryKey(userId);
        }
    }

    /**
     * 还需要增加创建时对各数据的正则表达式匹配 输入格式、长度等
     * @param user
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public String createUser(User user) {
        if (StringUtils.isBlank(user)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            if (userMapper.insert(user) == 0) {
                return FrontApiResponseEntity.ERR(ResponseCode.FAIl).build();
            } else {
                return FrontApiResponseEntity.SUCC().build();
            }
        }
    }

    @RequestMapping("/findOneByUserPhoneNumber")
    @ResponseBody
    public User findOneByUserPhoneNumber(String userPhoneNumber) {
        if (StringUtils.isBlank(userPhoneNumber)) {
            return null;
        } else {
            return userMapper.selectByUserPhoneNumber(userPhoneNumber);
        }
    }

    @RequestMapping("/findOneLimitById")
    @ResponseBody
    public LimitUser findOneLimitById(Long userId) {
        if (StringUtils.isBlank(userId)) {
            return null;
        } else {
            return userMapper.selectUserLimitInfo(userId);
        }
    }

    @RequestMapping("/listUserFollows")
    @ResponseBody
    public List<UserFollow> listUserFollows(Long userId){
        if (StringUtils.isBlank(userId)) {
            return null;
        } else {
            return userMapper.selectUserFollow(userId);
        }
    }

    @RequestMapping("/listUserAttention")
    @ResponseBody
    public List<UserFollow> listUserAttention(Long userId){
        if (StringUtils.isBlank(userId)) {
            return null;
        } else {
            return userMapper.selectUserAttetion(userId);
        }
    }

    @RequestMapping("/userAttention")
    @ResponseBody
    public String userAttention(Long userId, Long user2Id){
        MapUserKey key = new MapUserKey();
        key.setUserId(userId);
        key.setUser2Id(user2Id);
        return userService.createUserAttention(key);
    }

    @RequestMapping("/userUnAttention")
    @ResponseBody
    public String userUnAttention(Long userId, Long user2Id){
        MapUserKey key = new MapUserKey();
        key.setUserId(userId);
        key.setUser2Id(user2Id);
        return userService.removeUserAttention(key);
    }

    @RequestMapping("/userUpdate")
    @ResponseBody
    public String userUpdate(User user) {
        if (StringUtils.isBlank(user.getUserId())) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        }
        if (userMapper.updateByPrimaryKeySelective(user) != 0) {
            return FrontApiResponseEntity.SUCC().build();
        } else {
            return FrontApiResponseEntity.SYS_ERR().message("数据库操作异常").build();
        }
    }

//    @RequestMapping("/test")
//    @ResponseBody
//    private String test(Long userId, Long user2Id){
//
//        return FrontApiResponseEntity.SUCC().message(user2Id+" "+userId).build();
//    }


}