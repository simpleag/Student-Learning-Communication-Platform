package com.zwp.slcp.frontapi.service;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.DiscussComment;
import com.zwp.slcp.frontapi.service.failCallBack.DiscussCommentFailCallBack;
import com.zwp.slcp.frontapi.service.failCallBack.UserFailCallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 2018/4/23.
 */
@FeignClient(value = "SQLSERVER", fallback = DiscussCommentFailCallBack.class)
public interface DiscussCommentService {
    @RequestMapping("/dc/listUsersComment")
    @ResponseBody
    PageInfo<DiscussComment> listUsersComment(Long userId, Integer pageNumber, Integer pageSize);

    @RequestMapping("/dc/createComment")
    @ResponseBody
    String createComment(DiscussComment discussComment);

    @RequestMapping("/dc/updateAttentionType")
    @ResponseBody
    String updateAttentionType(Long userId, Long discussCommentId);

}
