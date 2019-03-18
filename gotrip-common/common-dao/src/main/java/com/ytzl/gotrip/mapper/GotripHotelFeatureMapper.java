package com.ytzl.gotrip.mapper;
import com.ytzl.gotrip.model.GotripHotelFeature;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface GotripHotelFeatureMapper {

	public GotripHotelFeature getGotripHotelFeatureById(@Param(value = "id") Long id)throws Exception;

	public List<GotripHotelFeature>	getGotripHotelFeatureListByMap(Map<String, Object> param)throws Exception;

	public Integer getGotripHotelFeatureCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertGotripHotelFeature(GotripHotelFeature gotripHotelFeature)throws Exception;

	public Integer updateGotripHotelFeature(GotripHotelFeature gotripHotelFeature)throws Exception;

	public Integer deleteGotripHotelFeatureById(@Param(value = "id") Long id)throws Exception;

}
