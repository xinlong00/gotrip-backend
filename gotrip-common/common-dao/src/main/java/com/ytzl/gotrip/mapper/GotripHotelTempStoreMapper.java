package com.ytzl.gotrip.mapper;
import com.ytzl.gotrip.model.GotripHotelTempStore;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface GotripHotelTempStoreMapper {

	public GotripHotelTempStore getGotripHotelTempStoreById(@Param(value = "id") Long id)throws Exception;

	public List<GotripHotelTempStore>	getGotripHotelTempStoreListByMap(Map<String, Object> param)throws Exception;

	public Integer getGotripHotelTempStoreCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertGotripHotelTempStore(GotripHotelTempStore gotripHotelTempStore)throws Exception;

	public Integer updateGotripHotelTempStore(GotripHotelTempStore gotripHotelTempStore)throws Exception;

	public Integer deleteGotripHotelTempStoreById(@Param(value = "id") Long id)throws Exception;

}
