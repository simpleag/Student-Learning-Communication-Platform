package com.zwp.slcp.frontapi.service.failCallBack;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.FullMessage;
import com.zwp.slcp.apicommon.entity.Message;
import com.zwp.slcp.frontapi.service.MessageService;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2018/4/23.
 */
@Component
public class MessageFailCallBack implements MessageService {
    @Override
    public PageInfo<FullMessage> listUserReceiveMessage(Long userId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<FullMessage> usersHistoryMessage(Long userId, Long targetUserId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public String createMessage(Message message) {
        return null;
    }

    @Override
    public String updateMessage(Message message) {
        return null;
    }

    @Override
    public Long listUserNewReceiveNumber(Long userId) {
        return null;
    }
}
