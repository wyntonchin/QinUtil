
package com.hisense.smarthome.sdk.test;

import com.hisense.smarthome.sdk.bean.global.HiSDKInfo;

public class Test {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        HiSDKInfo info = new HiSDKInfo();
        info.setDomainName("10.0.64.107:80");
        info.setToken("114374385737756");

        /*BaspService service = HiCloudServiceFactory.getBaspService(info);
        DomainReplyInfo domain = service.getAppServiceDomain("");
        if (domain != null) {
            if (domain.getReply() == 0) {
                System.out.println(domain.getDomain());
            }
            System.out.println("reply is " + domain.getReply());
        }*/
        System.out.println("reply is null");
    }

}
