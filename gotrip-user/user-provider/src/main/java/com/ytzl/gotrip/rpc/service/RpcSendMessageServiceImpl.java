package com.ytzl.gotrip.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.ytzl.gotrip.properites.SmsProperties;
import com.ytzl.gotrip.rpc.api.RpcSendMessageService;
import com.ytzl.gotrip.utils.exception.GotripException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Set;

@Component
@Service(interfaceClass = RpcSendMessageService.class)
public class RpcSendMessageServiceImpl implements RpcSendMessageService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Resource
    private SmsProperties smsProperties;

    @Override
    public void sendMessage(String phone, String templateId, String code) {
        HashMap<String, Object> result = null;
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        restAPI.init(smsProperties.getServerIP(), smsProperties.getServerPort());// 初始化服务器地址和端口，生产环境配置成app.cloopen.com，端口是8883. 
        restAPI.setAccount(smsProperties.getAccountSid(), smsProperties.getAccountToken());
        // 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在控制首页中看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN。
        restAPI.setAppId(smsProperties.getAppId());
        // 请使用管理控制台中已创建应用的APPID。
        result = restAPI.sendTemplateSMS(phone, templateId, new String[]{code, "3"});
        System.out.println("SDKTestGetSubAccounts result=" + result);
        if ("000000".equals(result.get("statusCode"))) {
            //正常返回输出data包体信息（map）
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for (String key : keySet) {
                Object object = data.get(key);
                System.out.println(key + " = " + object);
            }
        } else {
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
        }
    }

    @Override
    public void createMailCode(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1400376680@qq.com");
        message.setTo(to);
        message.setSubject("请验证您的账号");
        message.setText("您的验证码为：" + code);
        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println("邮件发送异常");
        }
        System.out.println("------->    邮件发送完毕");
    }
}
