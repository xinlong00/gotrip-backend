package com.ytzl.gotrip.mapper;
import com.ytzl.gotrip.model.GotripOrderLinkUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface GotripOrderLinkUserMapper {

	public GotripOrderLinkUser getGotripOrderLinkUserById(@Param(value = "id") Long id)throws Exception;

	public List<GotripOrderLinkUser>	getGotripOrderLinkUserListByMap(Map<String, Object> param)throws Exception;

	public Integer getGotripOrderLinkUserCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertGotripOrderLinkUser(GotripOrderLinkUser gotripOrderLinkUser)throws Exception;

	public Integer updateGotripOrderLinkUser(GotripOrderLinkUser gotripOrderLinkUser)throws Exception;

	public Integer deleteGotripOrderLinkUserById(@Param(value = "id") Long id)throws Exception;

}
