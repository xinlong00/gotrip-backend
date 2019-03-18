package com.ytzl.gotrip.controller;

import com.ytzl.gotrip.model.GotripUser;
import com.ytzl.gotrip.service.GotripUserService;
import com.ytzl.gotrip.utils.common.Dto;
import com.ytzl.gotrip.utils.common.DtoUtil;
import com.ytzl.gotrip.vo.userinfo.ItripUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.logging.Handler;

@RestController
@RequestMapping("/api")
@Api(description = "用户模块控制器")
public class GotripUserController {

    @Resource
    private GotripUserService gotripUserService;


    //value 简单描述    notes 详细描述
    @ApiOperation(value = "根据用户Code查询用户信息",
            notes = "根据用户Code查询用户信息  \n" +
                    "错误码:  \n"+
                    "   30003:参数不能为空"
    )
    @PostMapping("/findByUserCode")
    public Dto findByUserCode(@ApiParam(value = "用户帐号")
                                  @RequestParam("userCode") String userCode) throws Exception {
        GotripUser gotripUser = gotripUserService.findByUserCode(userCode);
        return DtoUtil.returnDataSuccess(gotripUser);
    }

    //判断邮箱是否存在
    @ApiOperation(value = "根据用户Code查询用户是否存在")
    @GetMapping("/ckusr")
    public Dto ckUsr(@ApiParam(value = "用户帐号")
                     @RequestParam(value = "name",required = false) String userCode) throws Exception{
        Dto dto = gotripUserService.ckUsr(userCode);
        return dto;
    }

    @ApiOperation(value = "手机号注册")
    @PostMapping("/registerbyphone")
    public Dto regiserByPhone(
            @RequestBody ItripUserVO itripUserVO
            ) throws Exception {
        gotripUserService.registerByPhone(itripUserVO);
        return DtoUtil.returnDataSuccess("注册成功");
    }

    //PUT /api/validatephone
    @ApiOperation("手机号验证")
    @PutMapping("/validatephone")
    public Dto validatephone(@ApiParam(value = "用户手机号")
                             @RequestParam String user,
                             @ApiParam(value = "短信验证码")
                             @RequestParam String code) throws Exception{
        gotripUserService.validatePhone(user,code);
        return DtoUtil.returnDataSuccess("激活成功");
    }


    //发送邮件
    @ApiOperation("邮箱发送激活码")
    @PostMapping("/doregister")
    public Dto sendMail(@ApiParam("用户邮箱") @RequestBody ItripUserVO itripUserVO) throws Exception {
        gotripUserService.sendMailMessage(itripUserVO);
        return DtoUtil.returnDataSuccess("邮件发送完毕");
    }
}
