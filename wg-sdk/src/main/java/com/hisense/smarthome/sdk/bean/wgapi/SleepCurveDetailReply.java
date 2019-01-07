
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class SleepCurveDetailReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 958320237934423018L;
    private int curveLenth;// number 曲线时长（单位：小时）
    private int runMode;// number 运行模式（预置曲线会下发该字段）：1：制热模式2：制冷模式3：除湿模式
    private List<CurveDetail> curveDetailList;// 睡眠曲线详细信息

    public int getCurveLenth() {
        return curveLenth;
    }

    public int getRunMode() {
        return runMode;
    }

    public List<CurveDetail> getCurveDetailList() {
        return curveDetailList;
    }

    public void setCurveLenth(int curveLenth) {
        this.curveLenth = curveLenth;
    }

    public void setRunMode(int runMode) {
        this.runMode = runMode;
    }

    public void setCurveDetailList(List<CurveDetail> curveDetailList) {
        this.curveDetailList = curveDetailList;
    }

}
