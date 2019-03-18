package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripProductStore;
import com.ytzl.gotrip.utils.common.Page;
import java.util.List;
import java.util.Map;


/**
* Rpc服务接口
*
* @author jayden
*/
public interface RpcGotripProductStoreService {

    public GotripProductStore getGotripProductStoreById(Long id)throws Exception;

    public List<GotripProductStore>	getGotripProductStoreListByMap(Map<String, Object> param)throws Exception;

    public Integer getGotripProductStoreCountByMap(Map<String, Object> param)throws Exception;

    public Integer insertGotripProductStore(GotripProductStore gotripProductStore)throws Exception;

    public Integer updateGotripProductStore(GotripProductStore gotripProductStore)throws Exception;

    public Integer deleteGotripProductStoreById(Long id)throws Exception;

    public Page<GotripProductStore> queryGotripProductStorePageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
