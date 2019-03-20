package com.ytzl.gotrip.controller;

import com.alibaba.fastjson.JSON;
import com.ytzl.gotrip.model.GotripUserLinkUser;
import com.ytzl.gotrip.service.GotripUserLinkUserService;
import com.ytzl.gotrip.utils.common.Dto;
import com.ytzl.gotrip.utils.common.DtoUtil;
import com.ytzl.gotrip.vo.userinfo.ItripAddUserLinkUserVO;
import com.ytzl.gotrip.vo.userinfo.ItripModifyUserLinkUserVO;
import com.ytzl.gotrip.vo.userinfo.ItripSearchUserLinkUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/userinfo")
@Api(description = "用户信息查询模块")
public class GotripUserLinkUserController {

    @Resource
    private GotripUserLinkUserService gotripUserLinkUserService;

    @ApiOperation("修改联系人接口")
    @PostMapping("/modifyuserlinkuser")
    public Dto modifyUserLinkUser(@ApiParam("联系人信息") @RequestBody ItripModifyUserLinkUserVO itripAddUserLinkUserVo,
                               @ApiParam("Token令牌") @RequestHeader("token") String token,
                               @ApiParam("浏览器标识") @RequestHeader("user-agent") String userAgent) throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("userlinkuser",itripAddUserLinkUserVo);
        params.put("token",token);
        params.put("user-agent",userAgent);
        gotripUserLinkUserService.modifyGotripUserLinkUser(params);
        return DtoUtil.returnDataSuccess("修改成功");
    }

    @ApiOperation("删除联系人")
    @GetMapping("/deluserlinkuser")
    public Dto deleteUserLinkUser(@ApiParam("用户id") @RequestParam("ids") long id,
                                  @ApiParam("Token令牌") @RequestHeader("token") String token,
                                  @ApiParam("浏览器标识") @RequestHeader("user-agent") String userAgent) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("token",token);
        map.put("user-agent",userAgent);
        gotripUserLinkUserService.delGotripUserLinkUser(map);
        return DtoUtil.returnDataSuccess("删除成功");
    }

    @ApiOperation("添加常用联系人接口")
    @PostMapping("/adduserlinkuser")
    public Dto addUserLinkUser(@ApiParam("联系人信息") @RequestBody ItripAddUserLinkUserVO itripAddUserLinkUserVo,
                               @ApiParam("Token令牌") @RequestHeader("token") String token,
                               @ApiParam("浏览器标识") @RequestHeader("user-agent") String userAgent) throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("userlinkuser",itripAddUserLinkUserVo);
        params.put("token",token);
        params.put("user-agent",userAgent);
        gotripUserLinkUserService.addGotripUserLinkUser(params);
        return DtoUtil.returnDataSuccess("添加成功");
    }

    @ApiOperation("查询常用联系人接口")
    @PostMapping("/queryuserlinkuser")
    public Dto searchUserLink(@ApiParam("关键字") @RequestBody ItripSearchUserLinkUserVO itripSearchUserLinkUserVO,
                              @ApiParam(value = "token令牌") @RequestHeader("token") String token,
                              @ApiParam(value = "浏览器标识",hidden = true) @RequestHeader("user-agent") String userAgent) throws Exception {
        //开始查询
        System.out.println("查询的名称：" + itripSearchUserLinkUserVO.getLinkUserName());
        Map<String,Object> params = new HashMap<>();
        params.put("linkUserName",itripSearchUserLinkUserVO);
        params.put("token",token);
        params.put("user-agent",userAgent);
        List<GotripUserLinkUser> list = gotripUserLinkUserService.getGotripUserLinkUserListByMap(params);
        return DtoUtil.returnDataSuccess(list);
    }

}
