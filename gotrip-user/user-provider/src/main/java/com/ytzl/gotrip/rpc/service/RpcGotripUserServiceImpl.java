package com.ytzl.gotrip.rpc.service;
import com.ytzl.gotrip.mapper.GotripUserMapper;
import com.ytzl.gotrip.model.GotripUser;
import com.ytzl.gotrip.rpc.api.RpcGotripUserService;
import com.ytzl.gotrip.utils.common.EmptyUtils;
import com.ytzl.gotrip.utils.common.Page;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ytzl.gotrip.utils.common.Constants;

@Component
@Service(interfaceClass = RpcGotripUserService.class)
public class RpcGotripUserServiceImpl implements RpcGotripUserService {

    @Resource
    private GotripUserMapper gotripUserMapper;

    @Override
    public GotripUser getGotripUserById(Long id)throws Exception{
        return gotripUserMapper.getGotripUserById(id);
    }

    @Override
    public List<GotripUser>	getGotripUserListByMap(Map<String,Object> param)throws Exception{
        return gotripUserMapper.getGotripUserListByMap(param);
    }

    @Override
    public Integer getGotripUserCountByMap(Map<String,Object> param)throws Exception{
        return gotripUserMapper.getGotripUserCountByMap(param);
    }

    @Override
    public Integer insertGotripUser(GotripUser gotripUser)throws Exception{
            gotripUser.setCreationDate(new Date());
            return gotripUserMapper.insertGotripUser(gotripUser);
    }

    @Override
    public Integer updateGotripUser(GotripUser gotripUser)throws Exception{
        gotripUser.setModifyDate(new Date());
        return gotripUserMapper.updateGotripUser(gotripUser);
    }

    @Override
    public Integer deleteGotripUserById(Long id)throws Exception{
        return gotripUserMapper.deleteGotripUserById(id);
    }

    @Override
    public Page<GotripUser> queryGotripUserPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = gotripUserMapper.getGotripUserCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<GotripUser> gotripUserList = gotripUserMapper.getGotripUserListByMap(param);
        page.setRows(gotripUserList);
        return page;
    }

}
