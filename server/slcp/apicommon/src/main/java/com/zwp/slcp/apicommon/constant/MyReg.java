package com.zwp.slcp.apicommon.constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ASUS on 2018/4/26.
 */
public class MyReg {
    public static String PWD_RULE = "^[a-zA-Z]\\w{5,17}$";
    public static String EMAIL_RULE = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
    public static String PHONE_RULE = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
    public static String LOGIN_ID_RULE = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";


    public static boolean verity(String str, String rule) {
        Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
       return matcher.matches();
    }
}
