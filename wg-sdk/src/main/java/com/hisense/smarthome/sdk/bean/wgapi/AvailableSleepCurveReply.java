
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class AvailableSleepCurveReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 5230899627651050769L;
    private List<SleepCurve> sleepCurveList;// 睡眠曲线列表

    public List<SleepCurve> getSleepCurveList() {
        return sleepCurveList;
    }

    public void setSleepCurveList(List<SleepCurve> sleepCurveList) {
        this.sleepCurveList = sleepCurveList;
    }

}
