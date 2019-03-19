package com.ytzl.gotrip.rpc.service;
import com.ytzl.gotrip.mapper.GotripUserLinkUserMapper;
import com.ytzl.gotrip.model.GotripUser;
import com.ytzl.gotrip.model.GotripUserLinkUser;
import com.ytzl.gotrip.rpc.api.RpcGotripUserLinkUserService;
import com.ytzl.gotrip.utils.common.EmptyUtils;
import com.ytzl.gotrip.utils.common.Page;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ytzl.gotrip.utils.common.Constants;

@Component
@Service(interfaceClass = RpcGotripUserLinkUserService.class)
public class RpcGotripUserLinkUserServiceImpl implements RpcGotripUserLinkUserService {

    @Autowired
    private GotripUserLinkUserMapper gotripUserLinkUserMapper;

    @Override
    public GotripUserLinkUser getGotripUserLinkUserById(Long id)throws Exception{
        return gotripUserLinkUserMapper.getGotripUserLinkUserById(id);
    }

    @Override
    public List<GotripUserLinkUser>	getGotripUserLinkUserListByMap(Map<String,Object> param)throws Exception{
        return gotripUserLinkUserMapper.getGotripUserLinkUserListByMap(param);
    }

    @Override
    public List<GotripUserLinkUser> searchGorpUserLink(Map<String, Object> param) throws Exception {
        return gotripUserLinkUserMapper.searchGorpUserLink(param);
    }

    @Override
    public Integer getGotripUserLinkUserCountByMap(Map<String,Object> param)throws Exception{
        return gotripUserLinkUserMapper.getGotripUserLinkUserCountByMap(param);
    }

    @Override
    public Integer insertGotripUserLinkUser(GotripUserLinkUser gotripUserLinkUser)throws Exception{
            gotripUserLinkUser.setCreationDate(new Date());
            return gotripUserLinkUserMapper.insertGotripUserLinkUser(gotripUserLinkUser);
    }

    @Override
    public Integer updateGotripUserLinkUser(GotripUserLinkUser gotripUserLinkUser)throws Exception{
        gotripUserLinkUser.setModifyDate(new Date());
        return gotripUserLinkUserMapper.updateGotripUserLinkUser(gotripUserLinkUser);
    }

    @Override
    public Integer deleteGotripUserLinkUserById(Long id)throws Exception{
        return gotripUserLinkUserMapper.deleteGotripUserLinkUserById(id);
    }

    @Override
    public Page<GotripUserLinkUser> queryGotripUserLinkUserPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = gotripUserLinkUserMapper.getGotripUserLinkUserCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<GotripUserLinkUser> gotripUserLinkUserList = gotripUserLinkUserMapper.getGotripUserLinkUserListByMap(param);
        page.setRows(gotripUserLinkUserList);
        return page;
    }


}
