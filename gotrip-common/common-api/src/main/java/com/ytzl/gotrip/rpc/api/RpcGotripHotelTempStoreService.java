package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripHotelTempStore;
import com.ytzl.gotrip.utils.common.Page;
import java.util.List;
import java.util.Map;


/**
* Rpc服务接口
*
* @author jayden
*/
public interface RpcGotripHotelTempStoreService {

    public GotripHotelTempStore getGotripHotelTempStoreById(Long id)throws Exception;

    public List<GotripHotelTempStore>	getGotripHotelTempStoreListByMap(Map<String, Object> param)throws Exception;

    public Integer getGotripHotelTempStoreCountByMap(Map<String, Object> param)throws Exception;

    public Integer insertGotripHotelTempStore(GotripHotelTempStore gotripHotelTempStore)throws Exception;

    public Integer updateGotripHotelTempStore(GotripHotelTempStore gotripHotelTempStore)throws Exception;

    public Integer deleteGotripHotelTempStoreById(Long id)throws Exception;

    public Page<GotripHotelTempStore> queryGotripHotelTempStorePageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
