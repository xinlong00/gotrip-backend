package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripTradeEnds;
import com.ytzl.gotrip.utils.common.Page;
import java.util.List;
import java.util.Map;


/**
* Rpc服务接口
*
* @author jayden
*/
public interface RpcGotripTradeEndsService {

    public GotripTradeEnds getGotripTradeEndsById(Long id)throws Exception;

    public List<GotripTradeEnds>	getGotripTradeEndsListByMap(Map<String, Object> param)throws Exception;

    public Integer getGotripTradeEndsCountByMap(Map<String, Object> param)throws Exception;

    public Integer insertGotripTradeEnds(GotripTradeEnds gotripTradeEnds)throws Exception;

    public Integer updateGotripTradeEnds(GotripTradeEnds gotripTradeEnds)throws Exception;

    public Integer deleteGotripTradeEndsById(Long id)throws Exception;

    public Page<GotripTradeEnds> queryGotripTradeEndsPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
