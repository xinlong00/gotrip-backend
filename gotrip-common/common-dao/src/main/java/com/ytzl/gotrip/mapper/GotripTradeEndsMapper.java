package com.ytzl.gotrip.mapper;
import com.ytzl.gotrip.model.GotripTradeEnds;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface GotripTradeEndsMapper {

	public GotripTradeEnds getGotripTradeEndsById(@Param(value = "id") Long id)throws Exception;

	public List<GotripTradeEnds>	getGotripTradeEndsListByMap(Map<String, Object> param)throws Exception;

	public Integer getGotripTradeEndsCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertGotripTradeEnds(GotripTradeEnds gotripTradeEnds)throws Exception;

	public Integer updateGotripTradeEnds(GotripTradeEnds gotripTradeEnds)throws Exception;

	public Integer deleteGotripTradeEndsById(@Param(value = "id") Long id)throws Exception;

}
