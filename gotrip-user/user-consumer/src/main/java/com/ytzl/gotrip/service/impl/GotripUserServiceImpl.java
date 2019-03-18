package com.ytzl.gotrip.service.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.dubbo.config.annotation.Reference;
import com.ytzl.gotrip.ext.utils.RedisUtils;
import com.ytzl.gotrip.model.GotripOrder;
import com.ytzl.gotrip.model.GotripUser;
import com.ytzl.gotrip.rpc.api.RpcGotripUserService;
import com.ytzl.gotrip.rpc.api.RpcSendMessageService;
import com.ytzl.gotrip.service.GotripUserService;
import com.ytzl.gotrip.utils.common.*;
import com.ytzl.gotrip.utils.exception.GotripException;
import com.ytzl.gotrip.vo.userinfo.ItripUserVO;
import org.omg.CORBA.UNKNOWN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sun.invoke.empty.Empty;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.regex.Pattern;

@Service("gotripUserService")
public class GotripUserServiceImpl implements GotripUserService {

    private Logger LOG = LoggerFactory.getLogger(GotripUserServiceImpl.class);

    @Reference
    private RpcGotripUserService rpcGotripUserService;

    @Reference
    private RpcSendMessageService rpcSendMessageService;

    @Resource
    private RedisUtils redisUtils;

    @Autowired
    private JavaMailSender javaMailSender;



    @Override
    public GotripUser findByUserCode(String userCode) throws Exception {
        //校验数据
        if (EmptyUtils.isEmpty(userCode)){
            throw new GotripException("用户Code不能为空", ErrorCode.AUTH_PARAMETER_ERROR);
        }

        Map<String,Object> params = new HashMap<>();
        params.put("userCode",userCode);
        List<GotripUser> gotripUserListByMap =
                rpcGotripUserService.getGotripUserListByMap(params);
        /*if (EmptyUtils.isEmpty(gotripUserListByMap)) {
            throw new GotripException("登录帐号不存在!",ErrorCode.AUTH_PARAMETER_ERROR);
        }*/
        return EmptyUtils.isEmpty(gotripUserListByMap)?null:gotripUserListByMap.get(0);
    }

    @Override
    public void registerByPhone(ItripUserVO itripUserVO) throws Exception {
        //数据校验
        checkRegisterData(itripUserVO);
        if (!validPhone(itripUserVO.getUserCode())) {
            throw new GotripException("手机号码格式不正确",ErrorCode.AUTH_PARAMETER_ERROR);
        }
        //数据入库
        //判断用户是否存在
        GotripUser byUserCode = this.findByUserCode(itripUserVO.getUserCode());
        if (EmptyUtils.isNotEmpty(byUserCode)) {
            throw new GotripException("用户已存在",
                    ErrorCode.AUTH_USER_ALREADY_EXISTS);
        }
        //构建用户信息
        GotripUser gotripUser = new GotripUser();
        BeanUtils.copyProperties(itripUserVO,gotripUser);
        gotripUser.setActivated(Constants.UserActivated.USER_ACTIVATED_DISABLE);
        //密码加密
        String md5UserPassword = DigestUtil.hmacSign(gotripUser.getUserPassword());
        gotripUser.setUserPassword(md5UserPassword);

        //数据入库
        rpcGotripUserService.insertGotripUser(gotripUser);
        //发送短信验证码
        //构建四位验证码
        int code = DigestUtil.randomCode();
        rpcSendMessageService.sendMessage(gotripUser.getUserCode(),"1",""+code);
        //将验证码保存到redis中
        String key = Constants.RedisKeyPrefix.ACTIVATION_MOBILE_PREFIX + gotripUser.getUserCode();
        redisUtils.set(key,""+code,60*3);
    }

    @Override
    public void validatePhone(String user, String code) throws Exception {
        //验证手机号码格式是否正确
        if (!validPhone(user)) {
            throw new GotripException("请输入正确的手机号",ErrorCode.AUTH_PARAMETER_ERROR);
        }

        //验证用户是否存在
        GotripUser gotripUser = this.findByUserCode(user);
        if (EmptyUtils.isEmpty(gotripUser)) {
            throw new GotripException("用户不存在",ErrorCode.AUTH_PARAMETER_ERROR);
        }
        //获取Redis中存储的短信验证码

        String key = Constants.RedisKeyPrefix.ACTIVATION_MOBILE_PREFIX + user;
        String cacheCode = (String) redisUtils.get(key);
        if (EmptyUtils.isEmpty(cacheCode) || !cacheCode.equals(code)) {
            throw new GotripException("验证码已失效",ErrorCode.AUTH_PARAMETER_ERROR);
        }
        //激活用户
        gotripUser.setActivated(Constants.UserActivated.USER_ACTIVATED_ENABLE);
        gotripUser.setUserType(0);
        gotripUser.setFlatID(gotripUser.getId());
        rpcGotripUserService.updateGotripUser(gotripUser);
        LOG.info("----> 用户[{}]激活成功",user);
    }


    @Override
    public void sendMailMessage(ItripUserVO itripUserVO) throws Exception {
        //验证格式
        if (!validEmail(itripUserVO.getUserCode())){
            throw new GotripException("邮箱格式不正确",ErrorCode.AUTH_PARAMETER_ERROR);
        }

        GotripUser byUserCode = this.findByUserCode(itripUserVO.getUserCode());
        if (!EmptyUtils.isEmpty(byUserCode)) {
            throw new GotripException("用户已存在",
                    ErrorCode.AUTH_USER_ALREADY_EXISTS);
        }

        //发送邮件
        int code = DigestUtil.randomCode();
//        rpcSendMessageService.sendMailMessage("1400376680@qq.com",to,code+"");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1400376680@qq.com");
        System.out.println("当前收件邮箱：" + itripUserVO.getUserCode());
        message.setTo(itripUserVO.getUserCode());
        message.setSubject("请验证您的账号");
        message.setText("您的验证码为：" + code);
        try {
            javaMailSender.send(message);
            String key = Constants.RedisKeyPrefix.ACTIVATION_MAIL_PREFIX + itripUserVO.getUserCode();
            //构建用户信息
            GotripUser gotripUser = new GotripUser();
            BeanUtils.copyProperties(itripUserVO,gotripUser);
            gotripUser.setActivated(Constants.UserActivated.USER_ACTIVATED_DISABLE);
            //密码加密
            String md5UserPassword = DigestUtil.hmacSign(gotripUser.getUserPassword());
            gotripUser.setUserPassword(md5UserPassword);
            rpcGotripUserService.insertGotripUser(gotripUser);
            redisUtils.set(key,code + "",60*3);
        } catch (Exception e) {
            System.out.println("邮件发送异常");
        }
        System.out.println("------->    邮件发送完毕");
    }

    @Override
    public Dto ckUsr(String userCode) throws Exception {
        //验证用户是否存在
        GotripUser gotripUser = this.findByUserCode(userCode);
        if (EmptyUtils.isNotEmpty(gotripUser)) {
            throw new GotripException("用户已存在",ErrorCode.AUTH_USER_ALREADY_EXISTS);
        }
        return DtoUtil.returnDataSuccess("用户名可用");
    }

    /**
     * 校验注册数据
     * @param itripUserVO 注册用户信息
     * @throws GotripException
     */
    private void checkRegisterData(ItripUserVO itripUserVO) throws GotripException {
        if (EmptyUtils.isEmpty(itripUserVO)) {
            throw new GotripException("请传递参数", ErrorCode.AUTH_PARAMETER_ERROR);
        }
        if (EmptyUtils.isEmpty(itripUserVO.getUserCode())) {
            throw new GotripException("用户帐号不能为空",ErrorCode.AUTH_PARAMETER_ERROR);
        }
        if (EmptyUtils.isEmpty(itripUserVO.getUserName())) {
            throw new GotripException("用户昵称不能为空",ErrorCode.AUTH_PARAMETER_ERROR);
        }
        if (EmptyUtils.isEmpty(itripUserVO.getUserPassword())) {
            throw new GotripException("用户密码不能为空",ErrorCode.AUTH_PARAMETER_ERROR);
        }
    }

    /**			 *
     * 合法E-mail地址：
     * 1. 必须包含一个并且只有一个符号“@”
     * 2. 第一个字符不得是“@”或者“.”
     * 3. 不允许出现“@.”或者.@
     * 4. 结尾不得是字符“@”或者“.”
     * 5. 允许“@”前的字符中出现“＋”
     * 6. 不允许“＋”在最前面，或者“＋@”
     */
    private boolean validEmail(String email){

        String regex="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$"  ;
        return Pattern.compile(regex).matcher(email).find();
    }
    /**
     * 验证是否合法的手机号
     * @param phone
     * @return
     */
    private boolean validPhone(String phone) {
        String regex="^1[356789]{1}\\d{9}$";
        return Pattern.compile(regex).matcher(phone).find();
    }
}
