package com.ytzl.gotrip.mapper;
import com.ytzl.gotrip.model.GotripHotelExtendProperty;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface GotripHotelExtendPropertyMapper {

	public GotripHotelExtendProperty getGotripHotelExtendPropertyById(@Param(value = "id") Long id)throws Exception;

	public List<GotripHotelExtendProperty>	getGotripHotelExtendPropertyListByMap(Map<String, Object> param)throws Exception;

	public Integer getGotripHotelExtendPropertyCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertGotripHotelExtendProperty(GotripHotelExtendProperty gotripHotelExtendProperty)throws Exception;

	public Integer updateGotripHotelExtendProperty(GotripHotelExtendProperty gotripHotelExtendProperty)throws Exception;

	public Integer deleteGotripHotelExtendPropertyById(@Param(value = "id") Long id)throws Exception;

}
