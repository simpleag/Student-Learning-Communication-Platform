package com.zwp.slcp.frontapi.service;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.Anoymous;
import com.zwp.slcp.apicommon.entity.DetailAnoymous;
import com.zwp.slcp.apicommon.entity.HomeAnoymous;
import com.zwp.slcp.frontapi.service.failCallBack.AnoymousFailCallBack;
import com.zwp.slcp.frontapi.service.failCallBack.UserFailCallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 2018/4/23.
 */
@FeignClient(value = "SQLSERVER", fallback = AnoymousFailCallBack.class)
public interface AnoymousService {
    @RequestMapping("/anoymous/listHomeAnoymouseByTime")
    @ResponseBody
    PageInfo<HomeAnoymous> listHomeAnoymouseByTime(@RequestParam(value = "userId") Long userId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/anoymous/listHomeAnoymouseByHot")
    @ResponseBody
    PageInfo<HomeAnoymous> listHomeAnoymouseByHot(@RequestParam(value = "userId") Long userId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/anoymous/findOneById")
    @ResponseBody
    DetailAnoymous findOneById(@RequestParam(value = "userId") Long userId, @RequestParam(value = "anoymousId") Long anoymousId);

    @RequestMapping("/anoymous/findOneWithCommentById")
    @ResponseBody
    String findOneWithCommentById(@RequestParam(value = "userId") Long userId, @RequestParam(value = "anoymousId") Long anoymousId);

    @RequestMapping("/anoymous/findOneWithCommentByCommentId")
    @ResponseBody
    String findOneByCommentId(@RequestParam(value = "userId") Long userId, @RequestParam(value = "anoumousCommentId") Long anoumousCommentId);

    @RequestMapping("/anoymous/listUsersAnoymous")
    @ResponseBody
    PageInfo<HomeAnoymous> listUsersAnoymous(@RequestParam(value = "userId") Long userId, @RequestParam(value = "authorId") Long authorId, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/anoymous/listUsersFavoriteAnoymous")
    @ResponseBody
    PageInfo<HomeAnoymous> listUsersFavoriteAnoymous(@RequestParam(value = "userId") Long userId, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/anoymous/createAnoymous")
    @ResponseBody
    String createAnoymous(@RequestBody Anoymous anoymous);

    @RequestMapping("/anoymous/updateAnoymous")
    @ResponseBody
    String updateAnoymous(@RequestBody Anoymous anoymous);

    @RequestMapping("/anoymous/updateUserAttentionType")
    @ResponseBody
    String updateUserAttentionType (@RequestParam(value = "userId") Long userId, @RequestParam(value = "discussId") Long discussId, @RequestParam(value = "type") Integer type);


}
