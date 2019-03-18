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
        return token.contains(md5UserAgent);
    }

    @Override
    public void remoteLogin(String token) {
        redisUtils.expire(token,3);
    }

    @Override
    public GotripUser getGotripUser(String token, String userAgent)throws Exception {
        //验证浏览器
        if (!this.verifyToken(token,userAgent)) {
            throw new GotripException("Token无效", ErrorCode.AUTH_TOKEN_INVALID);
        }
        //获取用户信息
        String gotripUserJson = (String)redisUtils.get(token);
        return JSON.parseObject(gotripUserJson,GotripUser.class);
    }
}
