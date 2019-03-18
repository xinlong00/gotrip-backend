package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripHotelRoom;
import com.ytzl.gotrip.utils.common.Page;
import java.util.List;
import java.util.Map;


/**
* Rpc服务接口
*
* @author jayden
*/
public interface RpcGotripHotelRoomService {

    public GotripHotelRoom getGotripHotelRoomById(Long id)throws Exception;

    public List<GotripHotelRoom>	getGotripHotelRoomListByMap(Map<String, Object> param)throws Exception;

    public Integer getGotripHotelRoomCountByMap(Map<String, Object> param)throws Exception;

    public Integer insertGotripHotelRoom(GotripHotelRoom gotripHotelRoom)throws Exception;

    public Integer updateGotripHotelRoom(GotripHotelRoom gotripHotelRoom)throws Exception;

    public Integer deleteGotripHotelRoomById(Long id)throws Exception;

    public Page<GotripHotelRoom> queryGotripHotelRoomPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
