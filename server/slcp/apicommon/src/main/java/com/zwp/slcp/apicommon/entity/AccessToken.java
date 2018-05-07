package com.zwp.slcp.apicommon.entity;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/4/30.
 */
public class AccessToken implements Serializable {
    private static final long serialVersionUID = -1L;
    private String token;
    private String secret;

    public AccessToken() {
    }

    public AccessToken(String token, String secret) {
        this.token = token;
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
