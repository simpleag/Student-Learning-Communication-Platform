package com.zwp.slcp.apicommon;

import com.zwp.slcp.apicommon.redis.RedisUtils;

/**
 * Created by ASUS on 2018/3/30.
 */
public class Test {
    public static void main(String[] args) {
        String accessTokenEntityString = RedisUtils.getString("6");
        System.out.println(accessTokenEntityString);
    }
}
