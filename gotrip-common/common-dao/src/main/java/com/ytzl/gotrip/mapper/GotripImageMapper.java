package com.ytzl.gotrip.mapper;
import com.ytzl.gotrip.model.GotripImage;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface GotripImageMapper {

	public GotripImage getGotripImageById(@Param(value = "id") Long id)throws Exception;

	public List<GotripImage>	getGotripImageListByMap(Map<String, Object> param)throws Exception;

	public Integer getGotripImageCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertGotripImage(GotripImage gotripImage)throws Exception;

	public Integer updateGotripImage(GotripImage gotripImage)throws Exception;

	public Integer deleteGotripImageById(@Param(value = "id") Long id)throws Exception;

}
