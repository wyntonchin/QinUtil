
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;
import java.util.List;

public class Scene implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -148389862692261288L;
    private int sceneId;// 场景Id
    private String sceneName;// 场景名称
    private String sceneType;// 场景类型1-手工触发的场景2-定时触发的场景3-条件触发的场景
    private String status;// 状态：1-开启2-关闭
    private List<Cmd> cmdList;// 设备动作执行列表
    private List<Condition> sceneTrigCondition;// 场景触发条件，仅在 sceneType=3时使用
    private long exeTime;// 场景执行时间utc
    private String sceneIconUrl;// 场景图标URL
    private long sceneIconId;// 场景图标ID
    private int executionCycle;//场景定时执行周期1：未定义2：一次性3：周期性
    private String executionDate;//定时执行日期（每周的执行的天数，以逗号分隔）例如：该定时任务每周的周六和周日执行，可存储为：6,7。
    private String executionTime;//定时执行时间格式：HH:mm:ss
    private int conditionRelationship;//场景条件关系,0：与1：或,默认为0 与
    private int isCommonScene;//是否是常用场景 0-不是 1-是
    private int seqNumber;//若为常用场景，对应的排序序号；若不是常用场景， seqNumber=0
    private String trgSceneIds;//关联场景ID列表，使用逗号”,”分隔

    private List<EffectiveTime> effectiveTimeList;//场景生效时间列表
    private int  conSceneId;//关联的手动场景ID，仅在获取条件触发手动场景中使用
    public int getSceneId() {
        return sceneId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public String getSceneType() {
        return sceneType;
    }

    public String getStatus() {
        return status;
    }

    public List<Cmd> getCmdList() {
        return cmdList;
    }

    public List<Condition> getSceneTrigCondition() {
        return sceneTrigCondition;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public void setSceneType(String sceneType) {
        this.sceneType = sceneType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCmdList(List<Cmd> cmdList) {
        this.cmdList = cmdList;
    }

    public void setSceneTrigCondition(List<Condition> sceneTrigCondition) {
        this.sceneTrigCondition = sceneTrigCondition;
    }

    public long getExeTime() {
        return exeTime;
    }

    public void setExeTime(long exeTime) {
        this.exeTime = exeTime;
    }

    public String getSceneIconUrl() {
        return sceneIconUrl;
    }

    public void setSceneIconUrl(String sceneIconUrl) {
        this.sceneIconUrl = sceneIconUrl;
    }

    public long getSceneIconId() {
        return sceneIconId;
    }

    public void setSceneIconId(long sceneIconId) {
        this.sceneIconId = sceneIconId;
    }

    public int getExecutionCycle() {
        return executionCycle;
    }

    public void setExecutionCycle(int executionCycle) {
        this.executionCycle = executionCycle;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public int getConditionRelationship() {
        return conditionRelationship;
    }

    public void setConditionRelationship(int conditionRelationship) {
        this.conditionRelationship = conditionRelationship;
    }

    public int getIsCommonScene() {
        return isCommonScene;
    }

    public void setIsCommonScene(int isCommonScene) {
        this.isCommonScene = isCommonScene;
    }

    public int getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }

    public String getTrgSceneIds() {
        return trgSceneIds;
    }

    public void setTrgSceneIds(String trgSceneIds) {
        this.trgSceneIds = trgSceneIds;
    }

    public List<EffectiveTime> getEffectiveTimeList() {
        return effectiveTimeList;
    }

    public void setEffectiveTimeList(List<EffectiveTime> effectiveTimeList) {
        this.effectiveTimeList = effectiveTimeList;
    }

    public int getConSceneId() {
        return conSceneId;
    }

    public void setConSceneId(int conSceneId) {
        this.conSceneId = conSceneId;
    }
}
