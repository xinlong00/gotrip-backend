package com.ytzl.gotrip.service;


import com.ytzl.gotrip.model.GotripUser;
import com.ytzl.gotrip.utils.common.Dto;
import com.ytzl.gotrip.vo.userinfo.ItripUserVO;

public interface GotripUserService {

    /**
     * 根据登录帐号查询用户信息
     * @param userCode 登录帐号
     * @return 用户信息(包含密码)
     */
    public GotripUser findByUserCode(String userCode) throws Exception;

    /**
     * 通过手机号注册的
     * @param itripUserVO 用户数据
     */
    void registerByPhone(ItripUserVO itripUserVO) throws Exception;

    /**
     * 手机帐号激活
     * @param user 登录帐号
     * @param code 验证码
     */
    void validatePhone(String user, String code) throws Exception;


    /**
     * 发送邮箱验证码
     * @throws Exception
     */
    void sendMailMessage(ItripUserVO itripUserVO) throws Exception;

    /**
     * 查询用户是否存在
     * @param userCode
     * @throws Exception
     */
    Dto ckUsr(String userCode) throws Exception;
}
