package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripLabelDic;
import com.ytzl.gotrip.utils.common.Page;
import java.util.List;
import java.util.Map;


/**
* Rpc服务接口
*
* @author jayden
*/
public interface RpcGotripLabelDicService {

    public GotripLabelDic getGotripLabelDicById(Long id)throws Exception;

    public List<GotripLabelDic>	getGotripLabelDicListByMap(Map<String, Object> param)throws Exception;

    public Integer getGotripLabelDicCountByMap(Map<String, Object> param)throws Exception;

    public Integer insertGotripLabelDic(GotripLabelDic gotripLabelDic)throws Exception;

    public Integer updateGotripLabelDic(GotripLabelDic gotripLabelDic)throws Exception;

    public Integer deleteGotripLabelDicById(Long id)throws Exception;

    public Page<GotripLabelDic> queryGotripLabelDicPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
