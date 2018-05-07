package com.zwp.slcp.nosqlserver.controller;

import com.zwp.slcp.apicommon.entity.Active;
import com.zwp.slcp.nosqlserver.service.ActiviteService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ASUS on 2018/4/29.
 */

@RestController
@RequestMapping("/active")
public class ActiviteController {
    @Autowired
    private ActiviteService activiteService;

    @RequestMapping(value = "/createActive")
    @ResponseBody
    public void createActive(String collName, Long userId
            , Long targetId, Integer type
            , String activeContent) throws Exception {
        System.out.println("start");
        Active  active = new Active(userId, targetId, type, activeContent);
        activiteService.insertActive(active, collName);
    }

    public static void main(String[] args) {

    }
    @RequestMapping(value = "/listUsersActives")
    @ResponseBody
    public List<Document> listUsersActives(String collName, Long userId
                , Integer pageSize, Integer pageNumber) {
        return activiteService.listActivieByUserIdOrderByDate(userId, collName, pageSize, pageNumber);
    }
}
