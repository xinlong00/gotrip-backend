package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripHotelExtendProperty;
import com.ytzl.gotrip.utils.common.Page;
import java.util.List;
import java.util.Map;


/**
* Rpc服务接口
*
* @author jayden
*/
public interface RpcGotripHotelExtendPropertyService {

    public GotripHotelExtendProperty getGotripHotelExtendPropertyById(Long id)throws Exception;

    public List<GotripHotelExtendProperty>	getGotripHotelExtendPropertyListByMap(Map<String, Object> param)throws Exception;

    public Integer getGotripHotelExtendPropertyCountByMap(Map<String, Object> param)throws Exception;

    public Integer insertGotripHotelExtendProperty(GotripHotelExtendProperty gotripHotelExtendProperty)throws Exception;

    public Integer updateGotripHotelExtendProperty(GotripHotelExtendProperty gotripHotelExtendProperty)throws Exception;

    public Integer deleteGotripHotelExtendPropertyById(Long id)throws Exception;

    public Page<GotripHotelExtendProperty> queryGotripHotelExtendPropertyPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
