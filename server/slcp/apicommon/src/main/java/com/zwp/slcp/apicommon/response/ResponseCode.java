package com.zwp.slcp.apicommon.response;

/**
 * Created by ASUS on 2018/4/14.
 */
public enum ResponseCode {
    SYS_ERR(500, "系统错误"),
    SUCC(200, "成功"),
    SMS_TOO_OFTEN(600, "验证码发送过于频繁"),
    VERIFYCODE_EXPIRED(601, "验证码过期"),
    SMS_VERIFYCODE_WRONG(602, "手机验证码错误"),

    PHONE_EXIST(603, "手机号已注册"),
    PHONE_NOTREGISTERED(604, "手机号未注册"),
    PASSWORD_WRONG(605, "帐号密码错误"),
    VERIFYCODE_WRONG(606, "验证码错误"),
    NAME_EXIST(607, "该昵称已存在"),
    EMAIL_EXIST(608, "该邮箱已被使用"),
    ACCOUNT_NOTEXIST(609,"帐号不存在"),

    LOGIN_EXPIRED(700, "登录过期"),
    ORIN_PWD_WRONG(701, "原密码错误"),
    PWD_WRONG(702, "密码错误"),
    BALANCE_NOT_ENOUGH(704, "资金不足"),
    REALNAME_NOT_SAME(705, "您提交的姓名与实名信息不一致"),

    ADMIN_NAME_NOTEXIST(800, "管理员不存在"),
    ADMIN_PWD_WRONG(802, "管理员密码错误"),
    ADMIN_FROZEN(803, "该管理员被冻结"),
    ADMIN_NOT_PERMITTED(804, "该管理员没有该操作权限"),
    ADMIN_NAME_EXIST(805, "该账户名已存在"),

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
