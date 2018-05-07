package com.zwp.slcp.frontapi.service;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.AnoymousComment;
import com.zwp.slcp.apicommon.entity.DetailAnoymousComment;
import com.zwp.slcp.frontapi.service.failCallBack.AnoymouseCommentFailCallBack;
import com.zwp.slcp.frontapi.service.failCallBack.UserFailCallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 2018/4/24.
 */
@FeignClient(value = "SQLSERVER", fallback = AnoymouseCommentFailCallBack.class)
public interface AnoymousCommentService {
    @RequestMapping("/anoymouseComment/listUsersComment")
    @ResponseBody
    PageInfo<AnoymousComment> listUsersComment(@RequestParam(value = "userId") Long userId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/anoymouseComment/createComment")
    @ResponseBody
    String createComment(@RequestBody AnoymousComment anoymousComment);

    @RequestMapping("/anoymouseComment/updateAttentionType")
    @ResponseBody
    String updateAttentionType(@RequestParam(value = "userId") Long userId, @RequestParam(value = "anoymousCommentId") Long anoymousCommentId);

    @RequestMapping("/anoymouseComment/listAnoymousComment")
    @ResponseBody
    PageInfo<DetailAnoymousComment> listAnoymousComment(@RequestParam(value = "userId") Long userId, @RequestParam(value = "anoymousId") Long anoymousId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);
}
