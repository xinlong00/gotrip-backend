package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripHotelTradingArea;
import com.ytzl.gotrip.utils.common.Page;
import java.util.List;
import java.util.Map;


/**
* Rpc服务接口
*
* @author jayden
*/
public interface RpcGotripHotelTradingAreaService {

    public GotripHotelTradingArea getGotripHotelTradingAreaById(Long id)throws Exception;

    public List<GotripHotelTradingArea>	getGotripHotelTradingAreaListByMap(Map<String, Object> param)throws Exception;

    public Integer getGotripHotelTradingAreaCountByMap(Map<String, Object> param)throws Exception;

    public Integer insertGotripHotelTradingArea(GotripHotelTradingArea gotripHotelTradingArea)throws Exception;

    public Integer updateGotripHotelTradingArea(GotripHotelTradingArea gotripHotelTradingArea)throws Exception;

    public Integer deleteGotripHotelTradingAreaById(Long id)throws Exception;

    public Page<GotripHotelTradingArea> queryGotripHotelTradingAreaPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
