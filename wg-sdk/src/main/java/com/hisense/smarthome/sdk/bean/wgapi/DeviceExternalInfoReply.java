
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class DeviceExternalInfoReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -8496113273795980820L;
    private String extInfo;// json 设备类型扩展信息，key、value对格式的json串，针对不同设备类别返回数据会有所不同
    private String extInfoMeta;// json 设备类型扩展属性元数据

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public String getExtInfoMeta() {
        return extInfoMeta;
    }

    public void setExtInfoMeta(String extInfoMeta) {
        this.extInfoMeta = extInfoMeta;
    }
}
