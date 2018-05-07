package com.zwp.slcp.frontapi.service;

import com.zwp.slcp.frontapi.service.failCallBack.ActiveFailCallBack;
import com.zwp.slcp.frontapi.service.failCallBack.JwtFailCallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 2018/4/30.
 */
@FeignClient(value = "NOSQL", fallback = JwtFailCallBack.class)
public interface JwtService {
    @RequestMapping(value = "/jwt/setToken")
    @ResponseBody
    String setToken(@RequestParam(value = "userId") Long userId);
}
