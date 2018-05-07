package com.zwp.slcp.frontapi.service;

import com.zwp.slcp.apicommon.entity.Active;
import com.zwp.slcp.frontapi.service.failCallBack.ActiveFailCallBack;
import com.zwp.slcp.frontapi.service.failCallBack.AnoymouseCommentFailCallBack;
import org.bson.Document;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ASUS on 2018/4/29.
 */
@FeignClient(value = "NOSQL", fallback = ActiveFailCallBack.class)
public interface ActiveService {

    @RequestMapping(value = "/active/createActive")
    @ResponseBody
    void createActive(@RequestParam(value = "collName") String collName, @RequestParam(value = "userId") Long userId
            , @RequestParam(value = "targetId") Long targetId, @RequestParam(value = "type") Integer type
            , @RequestParam(value = "activeContent") String activeContent);

    @RequestMapping(value = "/active/listUsersActives")
    @ResponseBody
    List<Document> listUsersActives(@RequestParam(value = "collName") String collName, @RequestParam(value = "userId") Long userId
            , @RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "pageNumber") Integer pageNumber);
}
