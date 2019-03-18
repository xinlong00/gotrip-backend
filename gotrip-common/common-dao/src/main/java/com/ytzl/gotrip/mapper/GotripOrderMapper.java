package com.ytzl.gotrip.mapper;
import com.ytzl.gotrip.model.GotripOrder;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface GotripOrderMapper {

	public GotripOrder getGotripOrderById(@Param(value = "id") Long id)throws Exception;

	public List<GotripOrder>	getGotripOrderListByMap(Map<String, Object> param)throws Exception;

	public Integer getGotripOrderCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertGotripOrder(GotripOrder gotripOrder)throws Exception;

	public Integer updateGotripOrder(GotripOrder gotripOrder)throws Exception;

	public Integer deleteGotripOrderById(@Param(value = "id") Long id)throws Exception;

}
