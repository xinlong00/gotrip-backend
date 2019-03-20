package com.ytzl.gotrip.controller;

import com.ytzl.gotrip.service.TokenService;
import com.ytzl.gotrip.utils.common.Dto;
import com.ytzl.gotrip.utils.common.DtoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api("Token置换控制器")
@RequestMapping("/api")
public class TokenController {

    @Resource
    private TokenService tokenService;

    @ApiOperation(value = "Token置换")
    @PostMapping("/retoken")
    public Dto reToken(
            @ApiParam(value = "token")
            @RequestHeader("token") String token,
            @ApiParam(hidden = true)
            @RequestHeader(value = "user-agent",required = false) String userAgent) throws Exception {
        String dtoStr = tokenService.replaceToken(token,userAgent);
        return DtoUtil.returnDataSuccess(dtoStr);
    }

}
