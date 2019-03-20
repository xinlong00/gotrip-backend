package com.ytzl.gotrip.service;

public interface TokenService {

    //消息置换
    public String replaceToken(String token,String userAgent) throws Exception;
}
