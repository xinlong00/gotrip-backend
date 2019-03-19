package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripUser;
import com.ytzl.gotrip.utils.exception.GotripException;

/**
 * @author xinlong00
 */
public interface RpcTokenService {

    //Token:[Mobile|Pc]-userCode(md5)-userId-yyyyMMddHHmmss-浏览器标识

    /**
     * 生成Token
     * @param gotripUser 用户信息
     * @param userAgent  浏览器内核版本
     * @return  Token令牌
     */
    public String generateToken(GotripUser gotripUser,String userAgent);

    /**
     * 保存Token
     * @param token Token令牌
     * @param gotripUser    用户信息
     */
    public void saveToken(String token,GotripUser gotripUser);

    /**
     * 验证Token是否有效
     * @param token  token令牌
     * @param userAgent 浏览器内核版本信息
     * @return  验证结果
     */
    public boolean verifyToken(String token,String userAgent);

    /**
     * 删除Token
     * @param token token令牌
     */
    public void remoteLogin(String token);


    /**
     * 根据Token获取用户信息
     * @param token token令牌
     * @param userAgent 浏览器标识
     * @return 用户信息
     */
    public GotripUser getGotripUser(String token,String userAgent) throws Exception;


    /**
     * Token置换
     */
    public String replaceToken(String token,String userAgent) throws Exception;
}
