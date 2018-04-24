package com.zwp.slcp.frontapi.service;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.FullTag;
import com.zwp.slcp.apicommon.entity.Tag;
import com.zwp.slcp.frontapi.service.failCallBack.TagFailCallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 2018/4/23.
 */
@FeignClient(value = "SQLSERVER", fallback = TagFailCallBack.class)
public interface TagService {
    @RequestMapping("/userAttentionTags")
    @ResponseBody
    PageInfo<FullTag> userAttentionTags(Long userId, Integer pageNumber, Integer pageSize);

    @RequestMapping("/listUnattentionTags")
    @ResponseBody
    PageInfo<Tag> listUnattentionTags(Long userId, Integer pageNumber, Integer pageSize);

    @RequestMapping("/updateUserAttentionTag")
    @ResponseBody
    String updateUserAttentionTag(Long userId, Integer tagId);


}
