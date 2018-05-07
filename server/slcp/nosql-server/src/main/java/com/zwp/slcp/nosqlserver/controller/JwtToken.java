package com.zwp.slcp.nosqlserver.controller;

import com.zwp.slcp.apicommon.entity.AccessToken;
import com.zwp.slcp.apicommon.redis.RedisUtils;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * Created by ASUS on 2018/4/30.
 */
@RestController
@RequestMapping("/jwt")
public class JwtToken {
    @RequestMapping(value = "/setToken")
    @ResponseBody
    String setToken(Long userId) {
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
        AccessToken accessTokenEntity = new AccessToken(accessToken, randomCode.toString());
        if (!RedisUtils.setStrTime(String.valueOf(userId), accessToken, 600)) {
            return FrontApiResponseEntity.SYS_ERR().build();
        } else {
            return FrontApiResponseEntity.SUCC().data("accessToken", accessToken).build();
        }
    }


}
