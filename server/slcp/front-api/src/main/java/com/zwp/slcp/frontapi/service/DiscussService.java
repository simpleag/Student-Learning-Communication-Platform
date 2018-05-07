package com.zwp.slcp.frontapi.service;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.DetailDiscuss;
import com.zwp.slcp.apicommon.entity.Discuss;
import com.zwp.slcp.apicommon.entity.HomeDiscuss;
import com.zwp.slcp.frontapi.service.failCallBack.DiscussFailCallBack;
import com.zwp.slcp.frontapi.service.failCallBack.UserFailCallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 2018/4/23.
 */
@FeignClient(value = "SQLSERVER", fallback = DiscussFailCallBack.class)
public interface DiscussService {
    @RequestMapping("/discuss/listHomeDiscussByTime")
    @ResponseBody
    PageInfo<HomeDiscuss> listHomeDiscussByTime(@RequestParam(value = "userId") Long userId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/discuss/listHomeDiscussByHot")
    @ResponseBody
    PageInfo<HomeDiscuss> listHomeDiscussByHot(@RequestParam(value = "userId") Long userId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/discuss/listTagHomeDiscussByHot")
    @ResponseBody
    PageInfo<HomeDiscuss> listTagHomeDiscussByHot(@RequestParam(value = "userId") Long userId, @RequestParam(value = "tagId") Integer tagId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/discuss/listTagHomeDiscussByTime")
    @ResponseBody
    PageInfo<HomeDiscuss> listTagHomeDiscussByTime(@RequestParam(value = "userId") Long userId, @RequestParam(value = "tagId") Integer tagId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/discuss/findOneById")
    @ResponseBody
    DetailDiscuss findOneById(@RequestParam(value = "userId") Long userId, @RequestParam(value = "discussId") Long discussId);

    @RequestMapping("/discuss/findOneWithCommentById")
    @ResponseBody
    String findOneWithCommentById(@RequestParam(value = "userId") Long userId, @RequestParam(value = "discussId") Long discussId);

    @RequestMapping("/discuss/findOneWithCommentByCommentId")
    @ResponseBody
    String findOneByCommentId(@RequestParam(value = "userId") Long userId, @RequestParam(value = "discussCommentId") Long discussCommentId);

    @RequestMapping("/discuss/listUsersDiscuss")
    @ResponseBody
    PageInfo<HomeDiscuss> listUsersDiscuss(@RequestParam(value = "userId") Long userId, @RequestParam(value = "authorId") Long authorId, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/discuss/listUsersFavoriteDiscuss")
    @ResponseBody
    PageInfo<HomeDiscuss> listUsersFavoriteDiscuss(@RequestParam(value = "userId") Long userId, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/discuss/createDiscuss")
    @ResponseBody
    String createDiscuss(@RequestBody Discuss discuss);

    @RequestMapping("/discuss/updateDiscuss")
    @ResponseBody
    String updateDiscuss(@RequestBody Discuss discuss);

    @RequestMapping("/discuss/updateUserAttentionType")
    @ResponseBody
    String updateUserAttentionType (@RequestParam(value = "userId") Long userId, @RequestParam(value = "discussId") Long discussId, @RequestParam(value = "type") Integer type);

    
}
