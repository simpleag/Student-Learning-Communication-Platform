package com.zwp.slcp.frontapi.service;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.Anoymous;
import com.zwp.slcp.apicommon.entity.DetailAnoymous;
import com.zwp.slcp.apicommon.entity.HomeAnoymous;
import com.zwp.slcp.frontapi.service.failCallBack.AnoymousFailCallBack;
import com.zwp.slcp.frontapi.service.failCallBack.UserFailCallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 2018/4/23.
 */
@FeignClient(value = "SQLSERVER", fallback = AnoymousFailCallBack.class)
public interface AnoymousService {
    @RequestMapping("/anoymous/listHomeAnoymouseByTime")
    @ResponseBody
    PageInfo<HomeAnoymous> listHomeAnoymouseByTime(Long userId, Integer pageNumber, Integer pageSize);

    @RequestMapping("/anoymous/listHomeAnoymouseByHot")
    @ResponseBody
    PageInfo<HomeAnoymous> listHomeAnoymouseByHot(Long userId, Integer pageNumber, Integer pageSize);

    @RequestMapping("/anoymous/findOneById")
    @ResponseBody
    DetailAnoymous findOneById(Long userId, Long anoymousId);

    @RequestMapping("/anoymous/findOneWithCommentById")
    @ResponseBody
    String findOneWithCommentById(Long userId, Long anoymousId);

    @RequestMapping("/anoymous/findOneWithCommentByCommentId")
    @ResponseBody
    String findOneByCommentId(Long userId, Long anoumousCommentId);

    @RequestMapping("/anoymous/listUsersAnoymous")
    @ResponseBody
    PageInfo<HomeAnoymous> listUsersAnoymous(Long userId, Long authorId, Integer pageNum, Integer pageSize);

    @RequestMapping("/anoymous/listUsersFavoriteAnoymous")
    @ResponseBody
    PageInfo<HomeAnoymous> listUsersFavoriteAnoymous(Long userId, Integer pageNum, Integer pageSize);

    @RequestMapping("/anoymous/createAnoymous")
    @ResponseBody
    String createAnoymous(Anoymous anoymous);

    @RequestMapping("/anoymous/updateAnoymous")
    @ResponseBody
    String updateAnoymous(Anoymous anoymous);

    @RequestMapping("/anoymous/updateUserAttentionType")
    @ResponseBody
    String updateUserAttentionType (Long userId, Long discussId, Integer type);


}
