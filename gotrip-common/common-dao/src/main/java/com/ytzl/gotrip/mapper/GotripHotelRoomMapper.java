package com.ytzl.gotrip.mapper;
import com.ytzl.gotrip.model.GotripHotelRoom;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface GotripHotelRoomMapper {

	public GotripHotelRoom getGotripHotelRoomById(@Param(value = "id") Long id)throws Exception;

	public List<GotripHotelRoom>	getGotripHotelRoomListByMap(Map<String, Object> param)throws Exception;

	public Integer getGotripHotelRoomCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertGotripHotelRoom(GotripHotelRoom gotripHotelRoom)throws Exception;

	public Integer updateGotripHotelRoom(GotripHotelRoom gotripHotelRoom)throws Exception;

	public Integer deleteGotripHotelRoomById(@Param(value = "id") Long id)throws Exception;

}
