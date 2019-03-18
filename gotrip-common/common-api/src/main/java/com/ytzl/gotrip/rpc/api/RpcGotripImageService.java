package com.ytzl.gotrip.rpc.api;

import com.ytzl.gotrip.model.GotripImage;
import com.ytzl.gotrip.utils.common.Page;
import java.util.List;
import java.util.Map;


/**
* Rpc服务接口
*
* @author jayden
*/
public interface RpcGotripImageService {

    public GotripImage getGotripImageById(Long id)throws Exception;

    public List<GotripImage>	getGotripImageListByMap(Map<String, Object> param)throws Exception;

    public Integer getGotripImageCountByMap(Map<String, Object> param)throws Exception;

    public Integer insertGotripImage(GotripImage gotripImage)throws Exception;

    public Integer updateGotripImage(GotripImage gotripImage)throws Exception;

    public Integer deleteGotripImageById(Long id)throws Exception;

    public Page<GotripImage> queryGotripImagePageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
