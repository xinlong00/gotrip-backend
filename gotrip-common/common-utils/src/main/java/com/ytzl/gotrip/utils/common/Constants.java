package com.ytzl.gotrip.utils.common;

/**
 * Created by sam on 2018/2/9.
 */
public class Constants {
    //默认显示第一页
    public static final int DEFAULT_PAGE_NO = 1;
    //默认一页10条
    public static final int DEFAULT_PAGE_SIZE = 10;

    public static class RedisExpire {
        public static final long DEFAULT_EXPIRE = 60;//60s 有慢sql，超时时间设置长一点
        public final static int SESSION_TIMEOUT = 2 * 60 * 60;//默认2h
    }

    public static class RedisKeyPrefix{
        //手机验证码前缀 ctrl + shift + x
        public static final  String ACTIVATION_MOBILE_PREFIX = "activation_mobile:";
        public static final  String ACTIVATION_MAIL_PREFIX = "activation_mail:";
    }

    public static class UserActivated{
        //启用
        public static final int USER_ACTIVATED_ENABLE = 1;
        //禁用
        public static final int USER_ACTIVATED_DISABLE = 0;
    }
}
