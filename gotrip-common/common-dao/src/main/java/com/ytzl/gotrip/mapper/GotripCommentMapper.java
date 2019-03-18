package com.ytzl.gotrip.mapper;
import com.ytzl.gotrip.model.GotripComment;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface GotripCommentMapper {

	public GotripComment getGotripCommentById(@Param(value = "id") Long id)throws Exception;

	public List<GotripComment>	getGotripCommentListByMap(Map<String, Object> param)throws Exception;

	public Integer getGotripCommentCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertGotripComment(GotripComment gotripComment)throws Exception;

	public Integer updateGotripComment(GotripComment gotripComment)throws Exception;

	public Integer deleteGotripCommentById(@Param(value = "id") Long id)throws Exception;

}
