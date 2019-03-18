package com.ytzl.gotrip.mapper;
import com.ytzl.gotrip.model.GotripHotel;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface GotripHotelMapper {

	public GotripHotel getGotripHotelById(@Param(value = "id") Long id)throws Exception;

	public List<GotripHotel>	getGotripHotelListByMap(Map<String, Object> param)throws Exception;

	public Integer getGotripHotelCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertGotripHotel(GotripHotel gotripHotel)throws Exception;

	public Integer updateGotripHotel(GotripHotel gotripHotel)throws Exception;

	public Integer deleteGotripHotelById(@Param(value = "id") Long id)throws Exception;

}
