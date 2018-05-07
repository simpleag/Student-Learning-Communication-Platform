package com.zwp.slcp.frontapi.service;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.FullTag;
import com.zwp.slcp.apicommon.entity.Tag;
import com.zwp.slcp.frontapi.service.failCallBack.TagFailCallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 2018/4/23.
 */
@FeignClient(value = "SQLSERVER", fallback = TagFailCallBack.class)
public interface TagService {
    @RequestMapping("/tag/userAttentionTags")
    @ResponseBody
    PageInfo<FullTag> userAttentionTags(@RequestParam(value = "userId") Long userId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);


    @RequestMapping("/tag/listAllTags")
    @ResponseBody
    PageInfo<Tag> listAllTags(@RequestParam(value = "userId") Long userId);

    @RequestMapping("/tag/listUnattentionTags")
    @ResponseBody
    PageInfo<Tag> listUnattentionTags(@RequestParam(value = "userId") Long userId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/tag/updateUserAttentionTag")
    @ResponseBody
    String updateUserAttentionTag(@RequestParam(value = "userId") Long userId, @RequestParam(value = "tagId") Integer tagId);


}
