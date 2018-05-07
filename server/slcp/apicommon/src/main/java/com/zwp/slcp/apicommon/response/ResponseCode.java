package com.zwp.slcp.apicommon.response;

/**
 * Created by ASUS on 2018/4/14.
 */
public enum ResponseCode {
    SYS_ERR(500, "系统错误"),
    SUCC(200, "成功"),
    TOKEN_ERROR(401, "token为空或者错误"),

    PARAMERROR(1001, "参数不合法"),
    EXIST(1002, "已存在"),
    FAIl(1003, "操作失败"),
    EMPTYEXCEL(1004, "表格有误"),
    SQLFAIl(1005, "数据库操作失败");

    private Integer code;
    private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
