package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripUserLinkUser;
import com.ytzl.gotrip.utils.common.Page;
import java.util.List;
import java.util.Map;


/**
* Rpc服务接口
*
* @author jayden
*/
public interface RpcGotripUserLinkUserService {

    public GotripUserLinkUser getGotripUserLinkUserById(Long id)throws Exception;

    public List<GotripUserLinkUser>	getGotripUserLinkUserListByMap(Map<String, Object> param)throws Exception;

    public Integer getGotripUserLinkUserCountByMap(Map<String, Object> param)throws Exception;

    public Integer insertGotripUserLinkUser(GotripUserLinkUser gotripUserLinkUser)throws Exception;

    public Integer updateGotripUserLinkUser(GotripUserLinkUser gotripUserLinkUser)throws Exception;

    public Integer deleteGotripUserLinkUserById(Long id)throws Exception;

    public Page<GotripUserLinkUser> queryGotripUserLinkUserPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
