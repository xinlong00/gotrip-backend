package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripAreaDic;
import com.ytzl.gotrip.utils.common.Page;
import java.util.List;
import java.util.Map;


/**
* Rpc服务接口
*
* @author jayden
*/
public interface RpcGotripAreaDicService {

    public GotripAreaDic getGotripAreaDicById(Long id)throws Exception;

    public List<GotripAreaDic>	getGotripAreaDicListByMap(Map<String, Object> param)throws Exception;

    public Integer getGotripAreaDicCountByMap(Map<String, Object> param)throws Exception;

    public Integer insertGotripAreaDic(GotripAreaDic gotripAreaDic)throws Exception;

    public Integer updateGotripAreaDic(GotripAreaDic gotripAreaDic)throws Exception;

    public Integer deleteGotripAreaDicById(Long id)throws Exception;

    public Page<GotripAreaDic> queryGotripAreaDicPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
