package com.zxy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机邮箱正则表达式校验
 *
 * @author black
 */
public class Reg {
    /**
     * 手机正则
     */
    private static final String REGULAR_PHONE = "^1(3[0-9]|4[5,7]|5[0,1,2,3,5,6,7,8,9]|6[2,5,6,7]|7[0,1,7,8]|8[0-9]|9[1,8,9])\\d{8}$";

    /**
     * 手机邮箱
     */
    private static final String REGULAR_EMAIL = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    /**
     * 手机验证
     * @param Phone
     * @return isMatched
     */
    public static Boolean regexPhone(String Phone) {
        Pattern regexPhone = Pattern.compile(REGULAR_PHONE);
        Matcher matcher = regexPhone.matcher(Phone);
        boolean isMatched = matcher.matches();
        return isMatched;
    }

    /**
     * 邮箱验证
     * @param Email
     * @return isMatched
     */
    public static Boolean regexEmail(String Email) {
        Pattern regexPhone = Pattern.compile(REGULAR_EMAIL);
        Matcher matcher = regexPhone.matcher(Email);
        boolean isMatched = matcher.matches();
        return isMatched;
    }

}
