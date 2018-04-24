package com.zwp.slcp.apicommon.response;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by ASUS on 2018/4/14.
 */
public class FrontApiResponseEntity implements Serializable {
    public FrontApiResponseEntity() {
    }

    protected Integer code;
    protected String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer error_code) {
        this.code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    public static Builder SYS_ERR() {
        return new Builder(ResponseCode.SYS_ERR);
    }

    public static Builder ERR(ResponseCode responseCode) {
        return new Builder(responseCode);
    }

    public static Builder ERR(Integer error_code, String message) {
        return new Builder(error_code, message);
    }

    public static Builder SUCC() {
        return new Builder(ResponseCode.SUCC);
    }

    public static Builder LOGIN_EXPIRED(){
        return new Builder(ResponseCode.LOGIN_EXPIRED);
    }

    public FrontApiResponseEntity(Builder builder) {
        this.code = builder.code;
        this.message = builder.message;
    }


    public static class Builder {

        HashMap hashMap = new HashMap();

        private Integer code;
        private String message;


        public Builder(Integer error_code, String message) {
            this.code = error_code;
            this.message = message;
        }

        public Builder(ResponseCode responseCode) {
            this.code = responseCode.getCode();
            this.message = responseCode.getMessage();
        }

        public Builder code(Integer error_code) {
            this.code = error_code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(Object data) {
            hashMap.put("data", data);
            return this;
        }

        public Builder data(String str, Object data) {
            hashMap.put(str, data);
            return this;
        }


        public String build() {
            hashMap.put("code", this.code);
            hashMap.put("message", this.message);
            return JSON.toJSONString(hashMap);
        }
    }
}
