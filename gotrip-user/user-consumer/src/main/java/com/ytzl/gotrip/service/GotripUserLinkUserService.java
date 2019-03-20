package com.ytzl.gotrip.service;

import com.ytzl.gotrip.model.GotripUserLinkUser;
import com.ytzl.gotrip.utils.common.Dto;
import com.ytzl.gotrip.vo.userinfo.ItripAddUserLinkUserVO;

import java.util.List;
import java.util.Map;

public interface GotripUserLinkUserService {
    //查询常用联系人
    public List<GotripUserLinkUser> getGotripUserLinkUserListByMap(Map<String,Object> params) throws Exception;

    //添加常用联系人
    public void addGotripUserLinkUser(Map params) throws Exception;

    //删除常用联系人
    public void delGotripUserLinkUser(Map params) throws Exception;

    //修改常用联系人
    public void modifyGotripUserLinkUser(Map params) throws Exception;
}
