package com.ytzl.gotrip.mapper;
import com.ytzl.gotrip.model.GotripAreaDic;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface GotripAreaDicMapper {

	public GotripAreaDic getGotripAreaDicById(@Param(value = "id") Long id)throws Exception;

	public List<GotripAreaDic>	getGotripAreaDicListByMap(Map<String, Object> param)throws Exception;

	public Integer getGotripAreaDicCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertGotripAreaDic(GotripAreaDic gotripAreaDic)throws Exception;

	public Integer updateGotripAreaDic(GotripAreaDic gotripAreaDic)throws Exception;

	public Integer deleteGotripAreaDicById(@Param(value = "id") Long id)throws Exception;

}
