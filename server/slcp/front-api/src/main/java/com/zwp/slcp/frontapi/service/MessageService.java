package com.zwp.slcp.frontapi.service;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.FullMessage;
import com.zwp.slcp.apicommon.entity.Message;
import com.zwp.slcp.frontapi.service.failCallBack.MessageFailCallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 2018/4/23.
 */
@FeignClient(value = "SQLSERVER", fallback = MessageFailCallBack.class)
public interface MessageService {
    @RequestMapping("/message/listUserReceiveMessage")
    @ResponseBody
    PageInfo<FullMessage> listUserReceiveMessage(@RequestParam(value = "userId") Long userId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/message/usersHistoryMessage")
    @ResponseBody
    PageInfo<FullMessage> usersHistoryMessage(@RequestParam(value = "userId") Long userId, @RequestParam(value = "targetUserId") Long targetUserId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/message/createMessage")
    @ResponseBody
    String createMessage(@RequestBody Message message);

    @RequestMapping("/message/updateMessage")
    @ResponseBody
    String updateMessage(@RequestBody Message message);

    @RequestMapping("/message/listUserNewReceiveNumber")
    @ResponseBody
    Long listUserNewReceiveNumber(@RequestParam(value = "userId") Long userId);
}
