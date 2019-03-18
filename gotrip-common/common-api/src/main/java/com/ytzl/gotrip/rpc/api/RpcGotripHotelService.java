package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripHotel;
import com.ytzl.gotrip.utils.common.Page;
import java.util.List;
import java.util.Map;


/**
* Rpc服务接口
*
* @author jayden
*/
public interface RpcGotripHotelService {

    public GotripHotel getGotripHotelById(Long id)throws Exception;

    public List<GotripHotel>	getGotripHotelListByMap(Map<String, Object> param)throws Exception;

    public Integer getGotripHotelCountByMap(Map<String, Object> param)throws Exception;

    public Integer insertGotripHotel(GotripHotel gotripHotel)throws Exception;

    public Integer updateGotripHotel(GotripHotel gotripHotel)throws Exception;

    public Integer deleteGotripHotelById(Long id)throws Exception;

    public Page<GotripHotel> queryGotripHotelPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
