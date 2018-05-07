package com.zwp.slcp.frontapi.service;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.DetailAnoymousComment;
import com.zwp.slcp.apicommon.entity.DetailDiscussComment;
import com.zwp.slcp.apicommon.entity.DiscussComment;
import com.zwp.slcp.frontapi.service.failCallBack.DiscussCommentFailCallBack;
import com.zwp.slcp.frontapi.service.failCallBack.UserFailCallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 2018/4/23.
 */
@FeignClient(value = "SQLSERVER", fallback = DiscussCommentFailCallBack.class)
public interface DiscussCommentService {
    @RequestMapping("/dc/listUsersComment")
    @ResponseBody
    PageInfo<DiscussComment> listUsersComment(@RequestParam(value = "userId") Long userId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/dc/createComment")
    @ResponseBody
    String createComment(@RequestBody DiscussComment discussComment);

    @RequestMapping("/dc/updateAttentionType")
    @ResponseBody
    String updateAttentionType(@RequestParam(value = "userId") Long userId, @RequestParam(value = "discussCommentId") Long discussCommentId);

    @RequestMapping("/dc/listDiscussComment")
    @ResponseBody
    PageInfo<DetailDiscussComment> listDiscussComment(@RequestParam(value = "userId") Long userId, @RequestParam(value = "discussId") Long discussId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);
}
