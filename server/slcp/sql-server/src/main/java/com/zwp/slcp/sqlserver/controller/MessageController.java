package com.zwp.slcp.sqlserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.FullMessage;
import com.zwp.slcp.apicommon.entity.Message;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.sqlserver.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ASUS on 2018/4/22.
 */
@RestController
@CrossOrigin
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageMapper messageMapper;

    @RequestMapping("/listUserReceiveMessage")
    @ResponseBody
    PageInfo<FullMessage> listUserReceiveMessage(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<FullMessage> fullMessageList = messageMapper.selectByReceiveUserId(userId);
            PageInfo<FullMessage> messagePageInfo = new PageInfo<>(fullMessageList);
            return messagePageInfo;
        }
    }

    @RequestMapping("/usersHistoryMessage")
    @ResponseBody
    PageInfo<FullMessage> usersHistoryMessage(Long userId, Long targetUserId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, targetUserId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<FullMessage> fullMessageList = messageMapper.selectByUsersId(userId, targetUserId);
            PageInfo<FullMessage> messagePageInfo = new PageInfo<>(fullMessageList);
            return messagePageInfo;
        }
    }

    @RequestMapping("/createMessage")
    @ResponseBody
    String createMessage(Message message) {
        if (StringUtils.isBlank(message)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            message.setCreateTime(System.currentTimeMillis());
            message.setMessageState(1);
            if (messageMapper.insert(message) == 0) {
                return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
            } else {
                return FrontApiResponseEntity.SUCC().build();
            }
        }
    }
    @RequestMapping("/updateMessage")
    @ResponseBody
    String updateMessage(Message message) {
        if (StringUtils.isBlank(message)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            message.setUpdateTime(System.currentTimeMillis());
            message.setMessageState(0);
            if (messageMapper.updateByPrimaryKey(message) == 0) {
                return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
            } else {
                return FrontApiResponseEntity.SUCC().build();
            }
        }
    }

}
