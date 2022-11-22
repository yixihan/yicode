package com.yixihan.yicode.common.util;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则校验类
 *
 * @author yixihan
 * @date 2022/11/22 9:34
 */
public class ValidationUtils {

    /**
     * 数字
     */
    private static final String REG_NUMBER = ".*\\d+.*";

    /**
     * 小写字母
     */
    private static final String REG_UPPERCASE = ".*[A-Z]+.*";

    /**
     * 大写字母
     */
    private static final String REG_LOWERCASE = ".*[a-z]+.*";

    /**
     * 特殊符号
     */
    private static final String REG_SYMBOL = ".*[-。，￥！·=；：‘“、~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*";

    /**
     * 密码最小长度
     */
    private static final Integer PASSWORD_MIN_LENGTH = 8;

    /**
     * 密码最大长度
     */
    private static final Integer PASSWORD_MAX_LENGTH = 20;

    /**
     * 密码最小长度
     */
    private static final Integer USERNAME_MIN_LENGTH = 2;

    /**
     * 密码最大长度
     */
    private static final Integer USERNAME_MAX_LENGTH = 8;

    /**
     * 校验邮箱
     */
    public static Boolean validateEmail(String email) {
        String pattern = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

        Pattern r = Pattern.compile (pattern);
        Matcher m = r.matcher (email);
        return m.matches ();
    }

    /**
     * 校验手机号
     */
    public static Boolean validateMobile(String mobile) {
        String pattern = "^((13[0-9])|(14[5-9])|(15([0-3]|[5-9]))|(16[6-7])|(17[1-8])|(18[0-9])|(19[1|3])|(19[5|6])|(19[8|9]))\\d{8}$";

        Pattern r = Pattern.compile (pattern);
        Matcher m = r.matcher (mobile);
        return m.matches ();
    }

    /**
     * 校验用户名
     *
     * @param userName 用户名
     */
    public static Boolean validateUserName(String userName) {
        return !StrUtil.isBlank (userName)
                && userName.length () >= USERNAME_MIN_LENGTH
                && userName.length () <= USERNAME_MAX_LENGTH;
    }

    /**
     * 校验密码
     *
     * @param password 密码
     */
    public static Boolean validatePassword(String password) {
        if (StrUtil.isBlank (password)
                || password.length () < PASSWORD_MIN_LENGTH
                || password.length () > PASSWORD_MAX_LENGTH) {
            return false;
        }
        int i = password.matches (REG_NUMBER) ? 1 : 0;
        i += password.matches (REG_LOWERCASE) ? 1 : 0;
        i += password.matches (REG_UPPERCASE) ? 1 : 0;
        i += password.matches (REG_SYMBOL) ? 1 : 0;

        return i >= 4;
    }
}
