package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripUser;
import com.ytzl.gotrip.utils.common.Page;
import java.util.List;
import java.util.Map;


/**
* Rpc服务接口
*
* @author jayden
*/
public interface RpcGotripUserService {

    public GotripUser getGotripUserById(Long id)throws Exception;

    public List<GotripUser>	getGotripUserListByMap(Map<String, Object> param)throws Exception;

    public Integer getGotripUserCountByMap(Map<String, Object> param)throws Exception;

    public Integer insertGotripUser(GotripUser gotripUser)throws Exception;

    public Integer updateGotripUser(GotripUser gotripUser)throws Exception;

    public Integer deleteGotripUserById(Long id)throws Exception;

    public Page<GotripUser> queryGotripUserPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
