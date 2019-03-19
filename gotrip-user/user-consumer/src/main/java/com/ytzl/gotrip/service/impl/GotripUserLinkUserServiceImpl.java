package com.ytzl.gotrip.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.ytzl.gotrip.model.GotripUser;
import com.ytzl.gotrip.model.GotripUserLinkUser;
import com.ytzl.gotrip.rpc.api.RpcGotripUserLinkUserService;
import com.ytzl.gotrip.rpc.api.RpcTokenService;
import com.ytzl.gotrip.service.GotripUserLinkUserService;
import com.ytzl.gotrip.utils.common.ErrorCode;
import com.ytzl.gotrip.utils.exception.GotripException;
import com.ytzl.gotrip.vo.userinfo.ItripSearchUserLinkUserVO;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("gotripUserLinkUserService")
public class GotripUserLinkUserServiceImpl implements GotripUserLinkUserService {

    @Reference
    private RpcTokenService rpcTokenService;

    @Reference
    private RpcGotripUserLinkUserService rpcGotripUserLinkUserService;

    @Override
    public List<GotripUserLinkUser> getGotripUserLinkUserListByMap(Map<String,Object> params) throws Exception {
        GotripUser gotripUser = rpcTokenService.getGotripUser((String)params.get("token"),(String)params.get("user-agent"));
        long id = gotripUser.getId();
        ItripSearchUserLinkUserVO itripSearchUserLinkUserVO = (ItripSearchUserLinkUserVO) params.get("linkUserName");
        GotripUserLinkUser gotripUserLinkUser = new GotripUserLinkUser();
        gotripUserLinkUser.setLinkUserName(itripSearchUserLinkUserVO.getLinkUserName());
        gotripUserLinkUser.setUserId(Math.toIntExact(id));
        String toJSONString = JSON.toJSONString(gotripUserLinkUser);

        System.out.println("Json数据：" + toJSONString);
        BeanUtils.copyProperties(params.get("linkUserName"),gotripUserLinkUser);
        Map param = JSON.parseObject(toJSONString,Map.class);
        System.out.println("获取到的参数：" + id);
        return (List<GotripUserLinkUser>) rpcGotripUserLinkUserService.searchGorpUserLink(param);
    }

    @Override
    public void addGotripUserLinkUser(Map params) throws Exception {
        GotripUser gotripUser = rpcTokenService.getGotripUser((String)params.get("token"),(String)params.get("user-agent"));
        long id = gotripUser.getId();
        GotripUserLinkUser gotripUserLinkUser = new GotripUserLinkUser();
        BeanUtils.copyProperties(params.get("userlinkuser"),gotripUserLinkUser);
        gotripUserLinkUser.setUserId(Math.toIntExact(id));
        gotripUserLinkUser.setCreationDate(new Date());
        JSON.parseObject(JSON.toJSONString(gotripUserLinkUser),Map.class);
        System.out.println(JSON.toJSONString(gotripUserLinkUser));
        rpcGotripUserLinkUserService.insertGotripUserLinkUser(gotripUserLinkUser);
    }

    @Override
    public void delGotripUserLinkUser(Map params) throws Exception {
        GotripUser gotripUser = rpcTokenService.getGotripUser((String)params.get("token"),(String)params.get("user-agent"));
        Integer integer = rpcGotripUserLinkUserService.deleteGotripUserLinkUserById(Long.parseLong(String.valueOf(params.get("id"))));
        if (integer == 0){
            throw new GotripException("删除失败", ErrorCode.BIZ_QUERY_HOTEL_DETAILS_FAIL);
        }
    }

    @Override
    public void modifyGotripUserLinkUser(Map params) throws Exception {
        GotripUser gotripUser = rpcTokenService.getGotripUser((String)params.get("token"),(String)params.get("user-agent"));
        long id = gotripUser.getId();
        GotripUserLinkUser gotripUserLinkUser = new GotripUserLinkUser();
        BeanUtils.copyProperties(params.get("userlinkuser"),gotripUserLinkUser);
        gotripUserLinkUser.setUserId(Math.toIntExact(id));
        JSON.parseObject(JSON.toJSONString(gotripUserLinkUser),Map.class);
        System.out.println(JSON.toJSONString(gotripUserLinkUser));
        try {
            rpcGotripUserLinkUserService.updateGotripUserLinkUser(gotripUserLinkUser);
        }catch (Exception e){
            throw new GotripException("修改失败",ErrorCode.BIZ_QUERY_HOTEL_DETAILS_FAIL);
        }
    }
}
