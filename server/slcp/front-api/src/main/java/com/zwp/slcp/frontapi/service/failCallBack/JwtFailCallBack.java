package com.zwp.slcp.frontapi.service.failCallBack;

import com.zwp.slcp.frontapi.service.JwtService;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2018/4/30.
 */
@Component
public class JwtFailCallBack implements JwtService{
    @Override
    public String setToken(Long userId) {
        return null;
    }
}
