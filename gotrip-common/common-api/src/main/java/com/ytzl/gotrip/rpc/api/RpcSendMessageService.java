package com.ytzl.gotrip.rpc.api;


/**
 * 短信发送RPC
 */

public interface RpcSendMessageService {

    /**
     * 发送短信
     * @param phone 手机号，多手机号用,分割
     * @param templateId 模板id｛未上线应用填写1｝官方提供的固定模版id
     * @param code 验证码内容
     */
    public void sendMessage(String phone,String templateId,String code);

    /**
     * 发送邮箱验证码
     * @param to 接收者
     * @param code 验证码内容
     */
//    public void createMailCode(String to,String code);
}
