package com.zwp.slcp.nosqlserver.service;

import com.mongodb.BasicDBObject;
import com.zwp.slcp.apicommon.entity.Active;
import com.zwp.slcp.apicommon.mongoDB.MongoUtil;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2018/4/29.
 */
@Service
public class ActiviteService {

    static BasicDBObject KEY_RULE = new BasicDBObject().append("_id",false);

    public List<Document> listActivieByUserIdOrderByDate(Long userId, String collName, Integer pageSize, Integer pageNumber) {
        BasicDBObject query = new BasicDBObject();
        query.put("userId", new BasicDBObject("$eq", userId));
        return MongoUtil.findByQueryKeyLimitSortSkip(collName, query, KEY_RULE, pageSize, "createTime", pageNumber*pageNumber);
    }

    public void insertActive(Active active, String collName) throws Exception {
        Document document = MongoUtil.modelToDc(active);
        MongoUtil.insertOne(collName, document);
    }


}
