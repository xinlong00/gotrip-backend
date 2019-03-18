package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripHotelFeature;
import com.ytzl.gotrip.utils.common.Page;
import java.util.List;
import java.util.Map;


/**
* Rpc服务接口
*
* @author jayden
*/
public interface RpcGotripHotelFeatureService {

    public GotripHotelFeature getGotripHotelFeatureById(Long id)throws Exception;

    public List<GotripHotelFeature>	getGotripHotelFeatureListByMap(Map<String, Object> param)throws Exception;

    public Integer getGotripHotelFeatureCountByMap(Map<String, Object> param)throws Exception;

    public Integer insertGotripHotelFeature(GotripHotelFeature gotripHotelFeature)throws Exception;

    public Integer updateGotripHotelFeature(GotripHotelFeature gotripHotelFeature)throws Exception;

    public Integer deleteGotripHotelFeatureById(Long id)throws Exception;

    public Page<GotripHotelFeature> queryGotripHotelFeaturePageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
