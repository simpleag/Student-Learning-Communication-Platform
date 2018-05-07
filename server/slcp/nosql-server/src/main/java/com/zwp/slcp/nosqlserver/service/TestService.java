package com.zwp.slcp.nosqlserver.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.zwp.slcp.apicommon.entity.AccessToken;
import com.zwp.slcp.apicommon.entity.Active;
import com.zwp.slcp.apicommon.mongoDB.MongoUtil;
import com.zwp.slcp.apicommon.redis.RedisUtils;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bson.Document;

import java.util.List;
import java.util.Random;


/**
 * Created by ASUS on 2018/4/29.
 */
public class TestService {
    static BasicDBObject KEY_RULE = new BasicDBObject().append("_id",false);
    public static void main(String[] args) throws Exception {
//        Active active = new Active(1L , 1L , 1, "432");
//        Document document = MongoUtil.modelToDc(active);
//        System.out.println(document.toString());
//        MongoUtil.insertOne("active", document);
//
//        BasicDBObject query = new BasicDBObject();
//        query.put("userId", new BasicDBObject("$eq", 1));
//
//
//
//        List<Document> list =  MongoUtil.findByQueryKeyLimitSortSkip("active", query, KEY_RULE, 2, "createTime", 0);
//        System.out.println(list.toString());
//        String str = FrontApiResponseEntity.SUCC().build();
//        JSONObject jsonObject = JSON.parseObject(str);

//        System.out.println(jsonObject);
//        System.out.println(jsonObject.getString("code"));

        Long userId = 3L;
        String randomStr = "JKLMNOPQRSTUVWXYZ1234567890";
        StringBuffer randomCode = new StringBuffer();
        Random random = new Random();
        //生成随机密钥
        for (int i=0;i<4;i++) {
            randomCode.append(randomStr.charAt(random.nextInt(randomStr.length())));
        }

        // 生成令牌,参数可以自行组织,.claim为jwt中自定义的内容
        String accessToken = Jwts.builder().setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .claim("accountId", userId)
                .claim("userType","user")
                .claim("power","1")
                .signWith(SignatureAlgorithm.HS256,
                        randomCode.toString()).compact();
        System.out.println(RedisUtils.setStrTime(accessToken, randomCode.toString(), 600));
    }
}
