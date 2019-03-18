package com.ytzl.gotrip.mapper;
import com.ytzl.gotrip.model.GotripProductStore;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface GotripProductStoreMapper {

	public GotripProductStore getGotripProductStoreById(@Param(value = "id") Long id)throws Exception;

	public List<GotripProductStore>	getGotripProductStoreListByMap(Map<String, Object> param)throws Exception;

	public Integer getGotripProductStoreCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertGotripProductStore(GotripProductStore gotripProductStore)throws Exception;

	public Integer updateGotripProductStore(GotripProductStore gotripProductStore)throws Exception;

	public Integer deleteGotripProductStoreById(@Param(value = "id") Long id)throws Exception;

}
