package com.ytzl.gotrip.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ytzl.gotrip.rpc.api.RpcTokenService;
import com.ytzl.gotrip.service.TokenService;
import org.springframework.stereotype.Service;


@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    @Reference
    private RpcTokenService rpcTokenService;

    @Override
    public String replaceToken(String token, String userAgent) throws Exception {
        return rpcTokenService.replaceToken(token,userAgent);
    }
}
