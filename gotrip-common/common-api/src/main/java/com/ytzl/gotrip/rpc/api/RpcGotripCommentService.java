package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripComment;
import com.ytzl.gotrip.utils.common.Page;
import java.util.List;
import java.util.Map;


/**
* Rpc服务接口
*
* @author jayden
*/
public interface RpcGotripCommentService {

    public GotripComment getGotripCommentById(Long id)throws Exception;

    public List<GotripComment>	getGotripCommentListByMap(Map<String, Object> param)throws Exception;

    public Integer getGotripCommentCountByMap(Map<String, Object> param)throws Exception;

    public Integer insertGotripComment(GotripComment gotripComment)throws Exception;

    public Integer updateGotripComment(GotripComment gotripComment)throws Exception;

    public Integer deleteGotripCommentById(Long id)throws Exception;

    public Page<GotripComment> queryGotripCommentPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
