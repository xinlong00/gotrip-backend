package com.ytzl.gotrip.mapper;
import com.ytzl.gotrip.model.GotripLabelDic;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface GotripLabelDicMapper {

	public GotripLabelDic getGotripLabelDicById(@Param(value = "id") Long id)throws Exception;

	public List<GotripLabelDic>	getGotripLabelDicListByMap(Map<String, Object> param)throws Exception;

	public Integer getGotripLabelDicCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertGotripLabelDic(GotripLabelDic gotripLabelDic)throws Exception;

	public Integer updateGotripLabelDic(GotripLabelDic gotripLabelDic)throws Exception;

	public Integer deleteGotripLabelDicById(@Param(value = "id") Long id)throws Exception;

}
