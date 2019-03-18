package com.ytzl.gotrip.mapper;
import com.ytzl.gotrip.model.GotripUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface GotripUserMapper {

	public GotripUser getGotripUserById(@Param(value = "id") Long id)throws Exception;

	public List<GotripUser>	getGotripUserListByMap(Map<String, Object> param)throws Exception;

	public Integer getGotripUserCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertGotripUser(GotripUser gotripUser)throws Exception;

	public Integer updateGotripUser(GotripUser gotripUser)throws Exception;

	public Integer deleteGotripUserById(@Param(value = "id") Long id)throws Exception;

}
