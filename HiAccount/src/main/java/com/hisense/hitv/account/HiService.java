package com.hisense.hitv.account;

import android.content.Context;

import com.hisense.hitv.hicloud.bean.account.AppCodeReply;
import com.hisense.hitv.hicloud.bean.account.AppCodeSSO;
import com.hisense.hitv.hicloud.bean.account.CustomerInfo;
import com.hisense.hitv.hicloud.bean.account.ReplyInfo;
import com.hisense.hitv.hicloud.bean.account.SignonReplyInfo;

import java.util.HashMap;

/**
 * Created by Victor on 2018/5/29. (ง •̀_•́)ง
 */
public interface HiService {

    /**
     * App验证
     */
    AppCodeReply appAuth(String appKey, String appSecret);

    /**
     * AppSSO验证
     */
    AppCodeSSO appSSOAuth(String appKey, String appSecret, String deviceId);

    /**
     * 登录
     */
    SignonReplyInfo login(String loginName, String password, String deviceId, String appCode);

    /**
     * 退出登录
     */
    ReplyInfo logout(String token, String deviceId);

    /**
     * 获取客户信息
     */
    CustomerInfo getCustomerInfo(String token);

    /**
     * 修改客户信息
     * HashMap
     * 名称       类型  必选  说明
     * nickName String  是   昵称
     * sex      String  是   性别
     * birthday String  是   生日
     * address  String  是   地址
     * email    String  是   邮箱
     */
    ReplyInfo updateCustomerInfo(String token, HashMap<String, String> map);

    /**
     * 修改密码
     */
    ReplyInfo modifyPassword(String token, String oldPwd, String newPwd);

    /**
     * 刷新token
     */
    SignonReplyInfo refreshToken(String appKey);

    /**
     * 注册
     * 名称           类型  必选  说明
     * loginName    String  是   用户名
     * password     String  是   密码
     * deviceId     String  是   设备认证时必须携带本参数
     * email        String  是   邮箱
     * registType   String  是   注册类型 (用户名:0, 手机号:1, 邮箱:2)
     * mobilePhone  String  是   手机号
     * appCode      String  是   APP认证返回的验证码code
     */
    SignonReplyInfo register(HashMap<String, String> map);

    /**
     * 发送验证码
     */
    ReplyInfo sendCaptcha(String tokenSSO, String phone);

    /**
     * 验证验证码
     */
    ReplyInfo validateCaptcha(String tokenSSO, String phone, String captcha);

    /**
     * 找回密码发送验证码
     */
    ReplyInfo findPasswordSendCaptcha(String loginName, String appCode, String tokenSSO);

    /**
     * 找回密码
     */
    ReplyInfo findPasswordByCaptcha(String loginName, String captcha, String newPassword, String tokenSSO);

    /**
     * 获取设备id
     */
    String getDeviceId(Context context);

    /**
     * 检查token是否过期
     */
    boolean isTokenExpire();

    /**
     * 获取token
     */
    String getToken();
}
