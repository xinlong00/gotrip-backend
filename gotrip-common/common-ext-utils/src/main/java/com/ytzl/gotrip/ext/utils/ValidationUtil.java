package com.ytzl.gotrip.ext.utils;

import com.alibaba.fastjson.JSON;
import com.ytzl.gotrip.model.GotripUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by sam on 2018/4/23.
 */
@Component
public class ValidationUtil {

    @Resource
    private RedisUtils redisUtils;

    /**
     * 通过Token获取用户信息
     *
     * @param token
     * @return
     */
    public GotripUser getUser(String token) {
        try {
            //判断token是否存在
            if (!redisUtils.exist(token)){
                return null;
            }
            String itripUserJson = (String) redisUtils.get(token);
            return JSON.parseObject(itripUserJson, GotripUser.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
