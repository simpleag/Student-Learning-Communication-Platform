package com.zwp.slcp.frontapi.controlller;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.FullMessage;
import com.zwp.slcp.apicommon.entity.Message;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.frontapi.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ASUS on 2018/4/26.
 */
@RestController
@CrossOrigin
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    private final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @RequestMapping(value = "/receiveMessage")
    @ResponseBody
    String receiveMessage(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            System.out.println("userId"+ userId);
            PageInfo<FullMessage> pageInfo = messageService.listUserReceiveMessage(userId, pageNumber, pageSize);

            if (pageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            } else {
                return FrontApiResponseEntity.SUCC().data("messageList", pageInfo).build();
            }
        }
    }

    @RequestMapping(value = "/usersHistoryMessage")
    @ResponseBody
    String usersHistoryMessage(Long userId, Long user2Id, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, user2Id, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<FullMessage> pageInfo = messageService.usersHistoryMessage(userId, user2Id, pageNumber, pageSize);
            if (pageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            } else {
                return FrontApiResponseEntity.SUCC().data("messageList", pageInfo).build();
            }
        }
    }

    @RequestMapping(value = "/create")
    @ResponseBody
    String create(Long messageAuthorId, Long messageReceiveUserid, String content) {
        if (StringUtils.isBlank(messageAuthorId, messageReceiveUserid, content)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            Message message = new Message(messageAuthorId, content, messageReceiveUserid);
            return messageService.createMessage(message);

        }
    }
    @RequestMapping(value = "/updateState")
    @ResponseBody
    String updateState(Long messageId, Integer state) {
        if (StringUtils.isBlank(messageId, state)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            Message message = new Message(messageId);
            message.setMessageState(state);
            return messageService.updateMessage(message);

        }
    }
}
