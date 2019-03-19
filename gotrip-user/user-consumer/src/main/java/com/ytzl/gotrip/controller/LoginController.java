package com.ytzl.gotrip.controller;

import com.ytzl.gotrip.model.GotripUser;
import com.ytzl.gotrip.service.GotripUserService;
import com.ytzl.gotrip.service.LoginService;
import com.ytzl.gotrip.service.impl.GotripUserServiceImpl;
import com.ytzl.gotrip.utils.common.Constants;
import com.ytzl.gotrip.utils.common.Dto;
import com.ytzl.gotrip.utils.common.DtoUtil;
import com.ytzl.gotrip.vo.ItripTokenVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

@RestController
@Api(description = "登录注销控制器")
@RequestMapping("/api")
public class LoginController {

    @Resource
    private GotripUserService gotripUserService;

    @Resource
    private LoginService loginService;

    @ApiOperation(value = "登录")
    @PostMapping("/dologin")
    public Dto doLogin(
            @ApiParam(value = "登录帐号")
            @RequestParam("name") String name
            ,@ApiParam(value = "登录密码")
            @RequestParam("password") String password
            ,@ApiParam(hidden = true) @RequestHeader(value = "user-agent",required = false) String userAgent) throws Exception {
        //查询用户信息
        GotripUser gotripUser =
                gotripUserService.findByUserCode(name);
        //登录成功
        String token = loginService.doLogin(gotripUser,password,userAgent);
        //构建返回结果
        ItripTokenVO tokenVO = new ItripTokenVO();
        tokenVO.setToken(token);
        //获取Token生成时间
        String currentTimeStr = token.split("-")[3];
        System.out.println(token + "**********" +currentTimeStr);
        long currTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(currentTimeStr).getTime();
        tokenVO.setGenTime(currTime);
        tokenVO.setExpTime(currTime + Constants.RedisExpire.SESSION_TIMEOUT * 1000);
        return DtoUtil.returnDataSuccess(tokenVO);
    }

    @ApiOperation(value = "用户注销")
    @GetMapping("/logout")
    public Dto logout(@ApiParam(value = "令牌") @RequestHeader("token") String token,
                      @ApiParam(hidden = true) @RequestHeader("user-agent") String userAgent) throws Exception {
        System.out.println("token:" + token);
        System.out.println("user-agent:" + userAgent);
        loginService.logout(token, userAgent);
        return DtoUtil.returnDataSuccess("注销成功");
    }
}
