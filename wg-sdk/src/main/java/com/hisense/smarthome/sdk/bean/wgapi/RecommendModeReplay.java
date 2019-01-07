
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class RecommendModeReplay extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -82667622641189432L;
    private String modeCode;// 推荐模式编码
    private String modeName;// 推荐模式名称
    private String modeDesc;// 推荐模式描述
    private String modeCtlCmd;// 推荐模式原始指令
    private String modeFunctionCode;// 功能项代码

    public String getModeCode() {
        return modeCode;
    }

    public String getModeName() {
        return modeName;
    }

    public String getModeDesc() {
        return modeDesc;
    }

    public String getModeCtlCmd() {
        return modeCtlCmd;
    }

    public String getModeFunctionCode() {
        return modeFunctionCode;
    }

    public void setModeCode(String modeCode) {
        this.modeCode = modeCode;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public void setModeDesc(String modeDesc) {
        this.modeDesc = modeDesc;
    }

    public void setModeCtlCmd(String modeCtlCmd) {
        this.modeCtlCmd = modeCtlCmd;
    }

    public void setModeFunctionCode(String modeFunctionCode) {
        this.modeFunctionCode = modeFunctionCode;
    }

}
