
package com.hisense.smarthome.sdk.factory;

import com.hisense.smarthome.sdk.bean.global.HiSDKInfo;
import com.hisense.smarthome.sdk.service.WgApiService;

/**
 * 服务工厂类，获取HiCloud的各种服务实例
 * 
 * @author Merry.Zhao
 */
public class HiCloudServiceFactory {
    /**
     * 获取白电服务实例
     * 
     * @param info HiSDKInfo实例, 需要传入参数</br>
     *            token 安全码, 用于身份验证</br>
     *            可选参数: </br>
     *            languageId 语言信息 见Constants.LANGUAGE_* ;
     * @return 获取白电服务实例
     */
    public static WgApiService getWgApiService(HiSDKInfo info) {
        if (info == null) {
            return null;
        }
        return WgApiService.getInstance(info);
    }
    
}
