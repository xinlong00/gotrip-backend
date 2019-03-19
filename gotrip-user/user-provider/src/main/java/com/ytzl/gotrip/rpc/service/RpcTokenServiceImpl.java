package com.ytzl.gotrip.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.ytzl.gotrip.ext.utils.RedisUtils;
import com.ytzl.gotrip.model.GotripUser;
import com.ytzl.gotrip.rpc.api.RpcTokenService;
import com.ytzl.gotrip.utils.common.Constants;
import com.ytzl.gotrip.utils.common.DigestUtil;
import com.ytzl.gotrip.utils.common.ErrorCode;
import com.ytzl.gotrip.utils.common.UserAgentUtil;
import com.ytzl.gotrip.utils.exception.GotripException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xinlong00
 */
@Component
@Service(interfaceClass = RpcTokenService.class)
public class RpcTokenServiceImpl implements RpcTokenService {

    @Resource
    private RedisUtils redisUtils;

    @Override
    public String generateToken(GotripUser gotripUser, String userAgent) {
        //Token:[Mobile|Pc]-userCode(md5)-userId-yyyyMMddHHmmss-浏览器标识
        StringBuffer sbToken = new StringBuffer("token:");
        //判断设备
        if (UserAgentUtil.checkAgent(userAgent)) {
            //移动设备
            sbToken.append("MOBILE-");
        }else {
            //PC设备
            sbToken.append("PC-");
        }
        String md5UserCode = DigestUtil.hmacSign(gotripUser.getUserCode());
        sbToken.append(md5UserCode).append("-");
        sbToken.append(gotripUser.getId()).append("-");
        String createTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        System.out.println(createTime);
        sbToken.append(createTime).append("-");
        String md5UserAgent = DigestUtil.hmacSign(userAgent,6);
        sbToken.append(md5UserAgent);
        return sbToken.toString();
    }

    @Override
    public void saveToken(String token, GotripUser gotripUser) {
        if (token.contains("PC-")) {
            //如果是PC则过期时间为两个小时
            redisUtils.set(token, JSON.toJSONString(gotripUser), Constants.RedisExpire.SESSION_TIMEOUT);
        }else if (token.contains("MOBILE-")){
            //如果是移动端则不过期
            redisUtils.set(token, JSON.toJSONString(gotripUser));
        }
    }

    @Override
    public boolean verifyToken(String token, String userAgent) {
        //Token是否存在
        if (!redisUtils.exist(token)) {
            return false;
        }
        //创建Token浏览器和当前浏览器是否一致
        String md5UserAgent = DigestUtil.hmacSign(userAgent,6);
        System.out.println(md5UserAgent);
        return token.contains(md5UserAgent);
    }

    @Override
    public void remoteLogin(String token) {
        redisUtils.expire(token,3);
    }

    @Override
    public GotripUser getGotripUser(String token, String userAgent)throws Exception {
        this.replaceToken(token,userAgent);
        //验证浏览器
        if (!this.verifyToken(token,userAgent)) {
            throw new GotripException("Token无效", ErrorCode.AUTH_TOKEN_INVALID);
        }
        //获取用户信息
        String gotripUserJson = (String)redisUtils.get(token);
        return JSON.parseObject(gotripUserJson,GotripUser.class);
    }

    @Override
    public String replaceToken(String token, String userAgent) throws Exception {
        //判断token失效
        if (!redisUtils.exist(token)) {
            throw new GotripException("Token已失效",ErrorCode.AUTH_TOKEN_INVALID);
        }else{
            //判断user-Agent归属
            String md5UserAgent = DigestUtil.hmacSign(userAgent,6);
            if (!token.contains(md5UserAgent)) {
                return token;
//                //Token无效
//                System.out.println("--->   Token失效");
//                throw new GotripException("Token已失效",ErrorCode.AUTH_TOKEN_INVALID);
            }else{
                //Token可用
                if (redisUtils.ttl(token) == -1){
//                    return token;
                    return token;
//                    throw new GotripException("Token永久",ErrorCode.AUTH_TOKEN_INVALID);
                }else {
                    //Token未失效,判断是否需要更新Token
                    System.out.println("剩余时间：" + redisUtils.ttl(token));
                    if (7200 - redisUtils.ttl(token) > 3600){
                        System.out.println("---->   Token更新置换");
                        GotripUser gotripUser = this.getGotripUser(token,userAgent);
                        this.remoteLogin(token);
                        String newToken = this.generateToken(gotripUser,userAgent);
                        this.saveToken(newToken,gotripUser);
                        return newToken;
                    }else{
                        return token;
//                        System.out.println("--->   Token无需置换");
//                        return token;
//                        throw new GotripException("Token无需更新",ErrorCode.AUTH_TOKEN_INVALID);
                    }
                }
            }
        }
    }
}
