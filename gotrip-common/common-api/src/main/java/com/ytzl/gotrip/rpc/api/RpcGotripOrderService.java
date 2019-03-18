package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripOrder;
import com.ytzl.gotrip.utils.common.Page;
import java.util.List;
import java.util.Map;


/**
* Rpc服务接口
*
* @author jayden
*/
public interface RpcGotripOrderService {

    public GotripOrder getGotripOrderById(Long id)throws Exception;

    public List<GotripOrder>	getGotripOrderListByMap(Map<String, Object> param)throws Exception;

    public Integer getGotripOrderCountByMap(Map<String, Object> param)throws Exception;

    public Integer insertGotripOrder(GotripOrder gotripOrder)throws Exception;

    public Integer updateGotripOrder(GotripOrder gotripOrder)throws Exception;

    public Integer deleteGotripOrderById(Long id)throws Exception;

    public Page<GotripOrder> queryGotripOrderPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
