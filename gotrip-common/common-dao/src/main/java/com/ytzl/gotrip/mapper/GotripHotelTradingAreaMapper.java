package com.ytzl.gotrip.mapper;
import com.ytzl.gotrip.model.GotripHotelTradingArea;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface GotripHotelTradingAreaMapper {

	public GotripHotelTradingArea getGotripHotelTradingAreaById(@Param(value = "id") Long id)throws Exception;

	public List<GotripHotelTradingArea>	getGotripHotelTradingAreaListByMap(Map<String, Object> param)throws Exception;

	public Integer getGotripHotelTradingAreaCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertGotripHotelTradingArea(GotripHotelTradingArea gotripHotelTradingArea)throws Exception;

	public Integer updateGotripHotelTradingArea(GotripHotelTradingArea gotripHotelTradingArea)throws Exception;

	public Integer deleteGotripHotelTradingAreaById(@Param(value = "id") Long id)throws Exception;

}
