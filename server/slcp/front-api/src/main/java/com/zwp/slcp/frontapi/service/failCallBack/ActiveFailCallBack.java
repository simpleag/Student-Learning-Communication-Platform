package com.zwp.slcp.frontapi.service.failCallBack;

import com.zwp.slcp.apicommon.entity.Active;
import com.zwp.slcp.frontapi.service.ActiveService;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ASUS on 2018/4/29.
 */
@Component
public class ActiveFailCallBack implements ActiveService {


    @Override
    public void createActive(String collName, Long userId, Long targetId, Integer type, String activeContent) {

    }

    @Override
    public List<Document> listUsersActives(String collName, Long userId, Integer pageSize, Integer pageNumber) {
        return null;
    }
}
