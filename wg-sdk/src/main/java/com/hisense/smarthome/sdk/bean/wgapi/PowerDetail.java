
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class PowerDetail implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -2995283256917525383L;
    private int dataIndex;// number 数据对应的位置：queryType=1时：代表周数queryType=2时：代表月数
    private int powerConsume;// number 消耗电量
    private int runTimeLenth;// number 运行时长(单位：小时)

    public int getDataIndex() {
        return dataIndex;
    }

    public int getPowerConsume() {
        return powerConsume;
    }

    public int getRunTimeLenth() {
        return runTimeLenth;
    }

    public void setDataIndex(int dataIndex) {
        this.dataIndex = dataIndex;
    }

    public void setPowerConsume(int powerConsume) {
        this.powerConsume = powerConsume;
    }

    public void setRunTimeLenth(int runTimeLenth) {
        this.runTimeLenth = runTimeLenth;
    }
}
