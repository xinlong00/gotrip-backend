package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripOrderLinkUser;
import com.ytzl.gotrip.utils.common.Page;
import java.util.List;
import java.util.Map;


/**
* Rpc服务接口
*
* @author jayden
*/
public interface RpcGotripOrderLinkUserService {

    public GotripOrderLinkUser getGotripOrderLinkUserById(Long id)throws Exception;

    public List<GotripOrderLinkUser>	getGotripOrderLinkUserListByMap(Map<String, Object> param)throws Exception;

    public Integer getGotripOrderLinkUserCountByMap(Map<String, Object> param)throws Exception;

    public Integer insertGotripOrderLinkUser(GotripOrderLinkUser gotripOrderLinkUser)throws Exception;

    public Integer updateGotripOrderLinkUser(GotripOrderLinkUser gotripOrderLinkUser)throws Exception;

    public Integer deleteGotripOrderLinkUserById(Long id)throws Exception;

    public Page<GotripOrderLinkUser> queryGotripOrderLinkUserPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
