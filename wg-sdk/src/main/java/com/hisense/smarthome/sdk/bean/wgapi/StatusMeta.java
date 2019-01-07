package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;
import java.util.List;

public class StatusMeta implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7491516167024216175L;

    private String statusName;// string 指令名称
    private String statusDesc;// string 指令描述
    private String statusCode;// string 指令编码
    private int statusType;// number 状态集：102、30
    private int statusValue;// number 实际的状态值
    private List<StatusParmMeta> statusParmMetaList;// 状态参数列表
    private int taskFlag;// 状态是否可以在终端展示：1：可以展示0：不可以展示
    private int paramType;// number 状态参数的类型：1：布尔型；2：枚举型；3：数字型
    private String unitFlag;// 计量单位标记
    private String cmdValueList;// 此状态受哪些控制指令的影响，，多个控制指令均会影响时，用逗号分隔，比如“3,5,7,9”
    private String cmdParmList;// 此状态受哪些控制指令的影响，包括参数值信息，JSON格式：[{"cmdValue":3,"cmdParmValue":[1,2]},{"cmdValue":5,"cmdParmValue":[1,2]}]
    private List<SceneOprTypeBean> sceneOprType;
    private String sceneOprTypeString;

    public String getSceneOprTypeString() {
        return sceneOprTypeString;
    }

    public void setSceneOprTypeString(String sceneOprTypeString) {
        this.sceneOprTypeString = sceneOprTypeString;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusType() {
        return statusType;
    }

    public void setStatusType(int statusType) {
        this.statusType = statusType;
    }

    public int getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(int statusValue) {
        this.statusValue = statusValue;
    }

    public List<StatusParmMeta> getStatusParmMetaList() {
        return statusParmMetaList;
    }

    public void setStatusParmMetaList(List<StatusParmMeta> statusParmMetaList) {
        this.statusParmMetaList = statusParmMetaList;
    }

    public int getTaskFlag() {
        return taskFlag;
    }

    public void setTaskFlag(int taskFlag) {
        this.taskFlag = taskFlag;
    }

    public int getParamType() {
        return paramType;
    }

    public void setParamType(int paramType) {
        this.paramType = paramType;
    }

    public String getUnitFlag() {
        return unitFlag;
    }

    public void setUnitFlag(String unitFlag) {
        this.unitFlag = unitFlag;
    }

    public String getCmdValueList() {
        return cmdValueList;
    }

    public void setCmdValueList(String cmdValueList) {
        this.cmdValueList = cmdValueList;
    }

    public String getCmdParmList() {
        return cmdParmList;
    }

    public void setCmdParmList(String cmdParmList) {
        this.cmdParmList = cmdParmList;
    }

    public List<SceneOprTypeBean> getSceneOprType() {
        return sceneOprType;
    }

    public void setSceneOprType(List<SceneOprTypeBean> sceneOprType) {
        this.sceneOprType = sceneOprType;
    }


    public static class SceneOprTypeBean implements Serializable{
        private static final long serialVersionUID = 7356923386234481663L;
        /**
         * oprType : 1
         * oprName : 等于
         */

        private int oprType;
        private String oprName;

        public int getOprType() {
            return oprType;
        }

        public void setOprType(int oprType) {
            this.oprType = oprType;
        }

        public String getOprName() {
            return oprName;
        }

        public void setOprName(String oprName) {
            this.oprName = oprName;
        }
    }
}
