package com.zwp.slcp.frontapi.service;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.Info;
import com.zwp.slcp.frontapi.service.failCallBack.InfoFailCallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 2018/4/23.
 */
@FeignClient(value = "SQLSERVER", fallback = InfoFailCallBack.class)
public interface InfoService {
    @RequestMapping("/info/listUserReceiveInfo")
    @ResponseBody
    PageInfo<Info> listUserReceiveInfo(@RequestParam(value = "userId") Long userId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/info/listUserNewReceiveCount")
    @ResponseBody
    Long listUserNewReceiveCount(@RequestParam(value = "userId") Long userId);
}
