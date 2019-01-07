package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

/**
 * Created by Hisense on 2017/10/17.
 */

public class EffectiveTime implements Serializable{

    private static final long serialVersionUID = -1313624118455768309L;
    private String start;//开始时间，格式：HH:mm:ss
    private String end;//结束时间，格式：HH:mm:ss

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
