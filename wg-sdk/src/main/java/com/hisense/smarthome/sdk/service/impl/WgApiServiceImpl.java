package com.hisense.smarthome.sdk.service.impl;

import com.hisense.smarthome.sdk.bean.SecurityRemoteCmdReply;
import com.hisense.smarthome.sdk.bean.global.BaseInfo;
import com.hisense.smarthome.sdk.bean.global.ErrorInfo;
import com.hisense.smarthome.sdk.bean.global.HiSDKInfo;
import com.hisense.smarthome.sdk.bean.wgapi.AllFloorRoomListReply;
import com.hisense.smarthome.sdk.bean.wgapi.AvailableSleepCurveReply;
import com.hisense.smarthome.sdk.bean.wgapi.BindDeviceListReplay;
import com.hisense.smarthome.sdk.bean.wgapi.BindedCustomerListReply;
import com.hisense.smarthome.sdk.bean.wgapi.BindedHomeDeviceListReply;
import com.hisense.smarthome.sdk.bean.wgapi.CheckAppTemplateVersionReply;
import com.hisense.smarthome.sdk.bean.wgapi.CheckDeviceLibVersionReply;
import com.hisense.smarthome.sdk.bean.wgapi.CmdMetaReply;
import com.hisense.smarthome.sdk.bean.wgapi.ConditionManulSceneListReply;
import com.hisense.smarthome.sdk.bean.wgapi.DefaultLanguageReply;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceBindInfoReplay;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceCmdReplay;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceCmdTimeLineReply;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceExtendInfoReply;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceExternalInfoReply;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceFunctionListReplay;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceLogicStatusListReply;
import com.hisense.smarthome.sdk.bean.wgapi.DevicePatternFunctionListReply;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceStatusByWifiIdReplay;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceStatusMetaReply;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceStatusReply;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceTypeListReply;
import com.hisense.smarthome.sdk.bean.wgapi.FloorListReply;
import com.hisense.smarthome.sdk.bean.wgapi.FloorRoomListReply;
import com.hisense.smarthome.sdk.bean.wgapi.GwCmdMetaReply;
import com.hisense.smarthome.sdk.bean.wgapi.GwStatusMetaReply;
import com.hisense.smarthome.sdk.bean.wgapi.HistoryDeviceListReply;
import com.hisense.smarthome.sdk.bean.wgapi.HistoryStatusReply;
import com.hisense.smarthome.sdk.bean.wgapi.HomeDeviceListReply;
import com.hisense.smarthome.sdk.bean.wgapi.HomeInfoReply;
import com.hisense.smarthome.sdk.bean.wgapi.HomeListReply;
import com.hisense.smarthome.sdk.bean.wgapi.HomeMemberListReply;
import com.hisense.smarthome.sdk.bean.wgapi.HomeQrCodeSessionKeyReply;
import com.hisense.smarthome.sdk.bean.wgapi.JoinHomeApplyListReply;
import com.hisense.smarthome.sdk.bean.wgapi.LatestExeDeviceReply;
import com.hisense.smarthome.sdk.bean.wgapi.LatestExeManulSceneReply;
import com.hisense.smarthome.sdk.bean.wgapi.MsgAndChannelsReplay;
import com.hisense.smarthome.sdk.bean.wgapi.PowerConsumpitonRankReply;
import com.hisense.smarthome.sdk.bean.wgapi.PowerConsumpitonReply;
import com.hisense.smarthome.sdk.bean.wgapi.RecommendModeReplay;
import com.hisense.smarthome.sdk.bean.wgapi.RecommendModeSwitchReply;
import com.hisense.smarthome.sdk.bean.wgapi.RepairTaskAreaReply;
import com.hisense.smarthome.sdk.bean.wgapi.RepirTaskBasicInfoReplay;
import com.hisense.smarthome.sdk.bean.wgapi.RepirTaskQueryReplay;
import com.hisense.smarthome.sdk.bean.wgapi.RoomDeviceListReply;
import com.hisense.smarthome.sdk.bean.wgapi.RoomListReply;
import com.hisense.smarthome.sdk.bean.wgapi.SaveFloorInfoReply;
import com.hisense.smarthome.sdk.bean.wgapi.SaveHomeInfoReply;
import com.hisense.smarthome.sdk.bean.wgapi.SaveRoomInfoRely;
import com.hisense.smarthome.sdk.bean.wgapi.SceneListReply;
import com.hisense.smarthome.sdk.bean.wgapi.ScenePresetIconListReply;
import com.hisense.smarthome.sdk.bean.wgapi.SelfDiagnosisItemsReplay;
import com.hisense.smarthome.sdk.bean.wgapi.ShieldSleepCurveReply;
import com.hisense.smarthome.sdk.bean.wgapi.SimpleSceneListReply;
import com.hisense.smarthome.sdk.bean.wgapi.SleepCurveDetailReply;
import com.hisense.smarthome.sdk.bean.wgapi.TaskResultReplay;
import com.hisense.smarthome.sdk.bean.wgapi.TaskTimeReplay;
import com.hisense.smarthome.sdk.bean.wgapi.UpdateSceneReply;
import com.hisense.smarthome.sdk.bean.wgapi.WifiDeviceVersionReplay;
import com.hisense.smarthome.sdk.bean.wgapi.WifiStatusListReply;
import com.hisense.smarthome.sdk.bean.wgapi.WifiStatusReplay;
import com.hisense.smarthome.sdk.http.HttpHandler;
import com.hisense.smarthome.sdk.parser.WgApiParser;
import com.hisense.smarthome.sdk.service.WgApiService;
import com.hisense.smarthome.sdk.util.Constants;

import java.io.IOException;
import java.util.HashMap;

import static com.hisense.smarthome.sdk.parser.WgApiParser.sceneListReplyParser;


/**
 * 白电服务的实现类
 */
public class WgApiServiceImpl extends WgApiService {

    private static WgApiService service;

    /**
     * 公共数据服务实现类的带参构造方法，定义为protected，防止外部调用
     *
     * @param info HiSDKInfo，其中含有访问Server端所必需的配置信息
     */
    protected WgApiServiceImpl(HiSDKInfo info) {
        super(info);
    }

    /**
     * 获取公共数据服务实例
     *
     * @param info HiSDKInfo，其中含有访问Server端所必需的配置信息
     * @return 公共数据服务实例
     */
    public static WgApiService getInstance(HiSDKInfo info) {
        if (service == null) {
            synchronized (WgApiServiceImpl.class) {
                if (service == null) {
                    service = new WgApiServiceImpl(info);
                }
            }
        } else {
            service.refresh(info);
        }
        service.setHiSDKInfo(info);

        return service;
    }

    @Override
    public MsgAndChannelsReplay getMsgAndChannels(long customerId, long maxMsgId,
                                                  String pushMsgIds) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("customerId", String.valueOf(customerId));
        params.put("maxMsgId", String.valueOf(maxMsgId));
        params.put("pushMsgIds", pushMsgIds);
        setSystemParameters(params);
        String url = assembleUrl("wg/msg/get-msg-and-channels", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        MsgAndChannelsReplay reply = null;
        try {
            reply = WgApiParser.getMsgAndChannelsParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public DeviceStatusByWifiIdReplay getDeviceStatusByWifiId(String wifiId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wifiId", wifiId);
        setSystemParameters(params);
        String url = assembleUrl("wg/ctl/getDeviceStatusByWifiId", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        DeviceStatusByWifiIdReplay reply = null;
        try {
            reply = WgApiParser.getDeviceStatusByWifiIdParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public WifiStatusReplay getWifiStatus(String wifiId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wifiId", wifiId);
        setSystemParameters(params);
        String url = assembleUrl("wg/ctl/getWifiStatus", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        WifiStatusReplay reply = null;
        try {
            reply = WgApiParser.getWifiStatusParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo uploadRemoteCmd(String wifiId, String wgDeviceId, String functionCode,
                                    String ctlCmd, int cmdVersion) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wifiId", wifiId);
        params.put("wgDeviceId", wgDeviceId);
        params.put("functionCode", functionCode);
        params.put("ctlCmd", ctlCmd);
        params.put("cmdVersion", String.valueOf(cmdVersion));
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ctl/uploadRemoteCmd", true),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public DeviceFunctionListReplay getDeviceFunctionList(String wgDeviceId, String wifiId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wgDeviceId", wgDeviceId);
        params.put("wifiId", wifiId);
        setSystemParameters(params);
        String url = assembleUrl("wg/dm/getDeviceFunctionList", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        DeviceFunctionListReplay reply = null;
        try {
            reply = WgApiParser.getDeviceFunctionListParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo createDeviceBind(String wifiId, String wgDeviceId, long homeId,
                                     String deviceNickName, String areaName, String ability) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<>();
        params.put("wifiId", wifiId);
        params.put("wgDeviceId", wgDeviceId);
        params.put("homeId", "" + homeId);
        params.put("deviceNickName", deviceNickName);
        params.put("areaName", areaName);
        params.put("ability", ability);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/createDeviceBind"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo createDeviceBind(String wifiId, String wgDeviceId, long homeId, String deviceNickName, String areaName) {
        return createDeviceBind(wifiId, wgDeviceId, homeId, deviceNickName, areaName, null);
    }

    @Override
    public BaseInfo addUserDeviceBind(String wifiId, String deviceNickName) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wifiId", wifiId);
        params.put("deviceNickName", deviceNickName);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/addUserDeviceBind"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo updateDeviceDefineInfo(String wifiId, String wgDeviceId, String deviceNickName,
                                           String areaName) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wifiId", wifiId);
        params.put("wgDeviceId", wgDeviceId);
        params.put("deviceNickName", deviceNickName);
        params.put("areaName", areaName);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/updateDeviceDefineInfo"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo delUserDeviceBind(String wifiId, String password) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wifiId", wifiId);
        params.put("password", password);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/delUserDeviceBind"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo delDeviceBindAll(String wifiId, String password) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wifiId", wifiId);
        params.put("password", password);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/delDeviceBindAll"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BindDeviceListReplay getBindDeviceList() {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        setSystemParameters(params);
        String url = assembleUrl("wg/dm/getBindDeviceList", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        BindDeviceListReplay reply = null;
        try {
            reply = WgApiParser.getBindDeviceListParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public DeviceBindInfoReplay queryDeviceBindInfo(String wifiId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wifiId", wifiId);
        setSystemParameters(params);
        String url = assembleUrl("wg/dm/queryDeviceBindInfo", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        DeviceBindInfoReplay reply = null;
        try {
            reply = WgApiParser.queryDeviceBindInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public WifiDeviceVersionReplay checkWifiDeviceVersion(String wifiId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wifiId", wifiId);
        setSystemParameters(params);
        String url = assembleUrl("wg/ota/checkWifiDeviceVersion", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        WifiDeviceVersionReplay reply = null;
        try {
            reply = WgApiParser.checkWifiDeviceVersionParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo confirmUpgrade(String wifiId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wifiId", wifiId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ota/confirmUpgrade"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo uploadTaskTime(HashMap<String, String> params) {
        String jsonData = "";
        if (params == null) {
            return null;
        }
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ctl/uploadTaskTime", true),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }


    @Override
    public TaskTimeReplay getTaskTime(String wifiId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<>();
        params.put("wifiId", wifiId);
        setSystemParameters(params);
        String url = assembleUrl("wg/ctl/getTaskTime", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        TaskTimeReplay reply = null;
        try {
            reply = WgApiParser.taskTimeReplayParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo uploadTaskAvaiable(String wifiId, String flag) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wifiId", wifiId);
        params.put("flag", flag);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ctl/uploadTaskAvaiable"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public TaskResultReplay getTaskResult(String wifiId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wifiId", wifiId);
        setSystemParameters(params);
        String url = assembleUrl("wg/ctl/getTaskResult", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        TaskResultReplay reply = null;
        try {
            reply = WgApiParser.taskResultReplayParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public DeviceCmdReplay getDeviceCmd(String deviceId, String wifiId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("deviceId", deviceId);
        params.put("wifiId", wifiId);
        setSystemParameters(params);
        String url = assembleUrl("wg/ctl/getDeviceCmd", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        DeviceCmdReplay reply = null;
        try {
            reply = WgApiParser.deviceCmdReplayParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo repirTaskInsert(HashMap<String, String> params) {
        String jsonData = "";
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/repair/task/insert"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public RepirTaskQueryReplay repirTaskQuery(String taskId, String deviceId, String serviceType) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("taskId", taskId);
        params.put("deviceId", deviceId);
        params.put("serviceType", serviceType);
        setSystemParameters(params);
        String url = assembleUrl("wg/repair/task/query", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        RepirTaskQueryReplay reply = null;
        try {
            reply = WgApiParser.repirTaskQueryReplayParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo repirTaskComment(String taskId, float star, String context) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("taskId", taskId);
        params.put("star", String.valueOf(star));
        params.put("context", context);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/repair/task/comment"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public RepirTaskBasicInfoReplay repirTaskGetBasicInfo() {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        setSystemParameters(params);
        String url = assembleUrl("wg/repair/task/get-basic-info", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        RepirTaskBasicInfoReplay reply = null;
        try {
            reply = WgApiParser.repirTaskBasicInfoReplayParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public SelfDiagnosisItemsReplay getSelfDiagnosisItems() {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        setSystemParameters(params);
        String url = assembleUrl("wg/ac/getSelfDiagnosisItems", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        SelfDiagnosisItemsReplay reply = null;
        try {
            reply = WgApiParser.selfDiagnosisItemsReplayParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public RecommendModeReplay getRecommendMode(String acDeviceId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("acDeviceId", acDeviceId);
        setSystemParameters(params);
        String url = assembleUrl("wg/ac/getRecommendMode", params, true);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        RecommendModeReplay reply = null;
        try {
            reply = WgApiParser.recommendModeReplayParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo saveRecommendModeSwitch(String acDeviceId, String switchFlag) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("acDeviceId", acDeviceId);
        params.put("switchFlag", switchFlag);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ac/saveRecommendModeSwitch"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public RecommendModeSwitchReply getRecommendModeSwitch(String acDeviceId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("acDeviceId", acDeviceId);
        setSystemParameters(params);
        String url = assembleUrl("wg/ac/getRecommendModeSwitch", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        RecommendModeSwitchReply reply = null;
        try {
            reply = WgApiParser.recommendModeSwitchParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public RepairTaskAreaReply getProvinces() {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        setSystemParameters(map);
        String url = assembleUrl("wg/repair/task/area/get-provinces", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        RepairTaskAreaReply reply = null;
        try {
            reply = WgApiParser.repairTaskAreaReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public RepairTaskAreaReply getCities(String parentId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("parentId", parentId);
        setSystemParameters(map);
        String url = assembleUrl("wg/repair/task/area/get-cities", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        RepairTaskAreaReply reply = null;
        try {
            reply = WgApiParser.repairTaskAreaReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public RepairTaskAreaReply getRegions(String parentId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("parentId", parentId);
        setSystemParameters(map);
        String url = assembleUrl("wg/repair/task/area/get-regions", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        RepairTaskAreaReply reply = null;
        try {
            reply = WgApiParser.repairTaskAreaReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public RepairTaskAreaReply getRoads(String parentId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("parentId", parentId);
        setSystemParameters(map);
        String url = assembleUrl("wg/repair/task/area/get-roads", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        RepairTaskAreaReply reply = null;
        try {
            reply = WgApiParser.repairTaskAreaReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo updateWifiDevice(HashMap<String, String> params) {
        String jsonData = "";
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/updateWifiDevice"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);

        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo repairAppFeedbackInsert(String feedbackContent, String mobilePhone,
                                            String customerName) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("feedbackContent", feedbackContent);
        params.put("mobilePhone", mobilePhone);
        params.put("customerName", customerName);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/repair/app/feedback/insert"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public DevicePatternFunctionListReply getDevicePatternFunctionList(String wgDeviceId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("wgDeviceId", wgDeviceId);
        setSystemParameters(map);
        String url = assembleUrl("wg/dm/getDevicePatternFunctionList", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        DevicePatternFunctionListReply reply = null;
        try {
            reply = WgApiParser.devicePatternFunctionListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BindedCustomerListReply getBindedCustomerList(String wgDeviceId, String wifiId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("wgDeviceId", wgDeviceId);
        map.put("wifiId", wifiId);
        setSystemParameters(map);
        String url = assembleUrl("wg/dm/getBindedCustomerList", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        BindedCustomerListReply reply = null;
        try {
            reply = WgApiParser.bindedCustomerListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public HistoryStatusReply getHistoryStatusByPosition(String deviceId, long startPosition,
                                                         long endPosition) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("deviceId", deviceId);
        map.put("startPosition", String.valueOf(startPosition));
        map.put("endPosition", String.valueOf(endPosition));
        setSystemParameters(map);
        String url = assembleUrl("wg/ctl/getHistoryStatusByPosition", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        HistoryStatusReply reply = null;
        try {
            reply = WgApiParser.historyStatusReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public PowerConsumpitonReply getPowerConsumpiton(String acDeviceId, int queryType,
                                                     int queryValue) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("acDeviceId", acDeviceId);
        map.put("queryType", String.valueOf(queryType));
        map.put("queryValue", String.valueOf(queryValue));
        setSystemParameters(map);
        String url = assembleUrl("wg/ac/getPowerConsumpiton", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        PowerConsumpitonReply reply = null;
        try {
            reply = WgApiParser.powerConsumpitonReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public DeviceExtendInfoReply getDeviceExtendInfo(String acDeviceId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("acDeviceId", acDeviceId);
        setSystemParameters(map);
        String url = assembleUrl("wg/ac/getDeviceExtendInfo", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        DeviceExtendInfoReply reply = null;
        try {
            reply = WgApiParser.deviceExtendInfoReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public AvailableSleepCurveReply getAvailableSleepCurve(String acDeviceId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("acDeviceId", acDeviceId);
        setSystemParameters(map);
        String url = assembleUrl("wg/ac/getAvailableSleepCurve", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        AvailableSleepCurveReply reply = null;
        try {
            reply = WgApiParser.availableSleepCurveReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public ShieldSleepCurveReply getShieldSleepCurve(String acDeviceId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("acDeviceId", acDeviceId);
        setSystemParameters(map);
        String url = assembleUrl("wg/ac/getShieldSleepCurve", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        ShieldSleepCurveReply reply = null;
        try {
            reply = (ShieldSleepCurveReply) WgApiParser.availableSleepCurveReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public SleepCurveDetailReply getSleepCurveDetail(String acDeviceId, String curveCode,
                                                     int curveType) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("acDeviceId", acDeviceId);
        map.put("curveCode", curveCode);
        map.put("curveType", String.valueOf(curveType));
        setSystemParameters(map);
        String url = assembleUrl("wg/ac/getSleepCurveDetail", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        SleepCurveDetailReply reply = null;
        try {
            reply = WgApiParser.sleepCurveDetailReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo setSleepCurveOnOff(String acDeviceId, String curveCode, int curveType,
                                       int switchFlag) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("acDeviceId", acDeviceId);
        params.put("curveCode", curveCode);
        params.put("curveType", String.valueOf(curveType));
        params.put("switchFlag", String.valueOf(switchFlag));
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ac/setSleepCurveOnOff"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo setSleepCurveStatus(String acDeviceId, String curveCode, int curveType,
                                        int oprFlag) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("acDeviceId", acDeviceId);
        params.put("curveCode", curveCode);
        params.put("curveType", String.valueOf(curveType));
        params.put("oprFlag", String.valueOf(oprFlag));
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ac/setSleepCurveStatus"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo saveSleepCurveInfo(String acDeviceId, String curveCode, String curveName,
                                       int curveLenth, String curveDetailList) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("acDeviceId", acDeviceId);
        params.put("curveCode", curveCode);
        params.put("curveName", curveName);
        params.put("curveLenth", String.valueOf(curveLenth));
        params.put("curveDetailList", curveDetailList);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ac/saveSleepCurveInfo"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public PowerConsumpitonRankReply getPowerConsumpitonRank(String acDeviceId, int queryType,
                                                             int queryValue) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("acDeviceId", acDeviceId);
        map.put("queryType", String.valueOf(queryType));
        map.put("queryValue", String.valueOf(queryValue));
        setSystemParameters(map);
        String url = assembleUrl("wg/ac/getPowerConsumpitonRank", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        PowerConsumpitonRankReply reply = null;
        try {
            reply = WgApiParser.powerConsumpitonRankReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public DeviceStatusReply getDeviceStatus(String deviceList) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("deviceList", deviceList);
        setSystemParameters(map);
        String url = assembleUrl("wg/ctl/getDeviceStatus", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        DeviceStatusReply reply = null;
        try {
            reply = WgApiParser.deviceStatusReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo deleteHistoryDevice(String wifiId, String wgDeviceId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wifiId", wifiId);
        params.put("wgDeviceId", wgDeviceId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/deleteHistoryDevice"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo setBindShieldOnOff(String wifiId, String wgDeviceId, String shieldFlag) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wifiId", wifiId);
        params.put("wgDeviceId", wgDeviceId);
        params.put("shieldFlag", shieldFlag);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/setBindShieldOnOff"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo delDeviceFromHome(long homeId, String wifiId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("homeId", "" + homeId);
        params.put("wifiId", wifiId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/delDeviceFromHome"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public SaveHomeInfoReply saveHomeInfo(HashMap<String, String> params) {
        String jsonData = "";
        if (params == null) {
            return null;
        }
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/saveHomeInfo"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        SaveHomeInfoReply reply = null;
        try {
            reply = WgApiParser.saveHomeInfoReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public HomeInfoReply getHomeInfo(long homeId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("homeId", "" + homeId);
        setSystemParameters(map);
        String url = assembleUrl("wg/dm/getHomeInfo", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        HomeInfoReply reply = null;
        try {
            reply = WgApiParser.homeInfoReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public HomeListReply getHomeList() {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        setSystemParameters(map);
        String url = assembleUrl("wg/dm/getHomeList", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        HomeListReply reply = null;
        try {
            reply = WgApiParser.homeListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public HomeQrCodeSessionKeyReply abstractgetHomeQrCodeSessionKey(long homeId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("homeId", "" + homeId);
        setSystemParameters(map);
        String url = assembleUrl("wg/dm/getHomeQrCodeSessionKey", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        HomeQrCodeSessionKeyReply reply = null;
        try {
            reply = WgApiParser.homeQrCodeSessionKeyReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo joinHomeByQrCode(String homeName, String sessionkey) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("homeName", homeName);
        params.put("sessionkey", sessionkey);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/joinHomeByQrCode"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public HomeMemberListReply getHomeMemberList(long homeId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("homeId", "" + homeId);
        setSystemParameters(map);
        String url = assembleUrl("wg/dm/getHomeMemberList", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        HomeMemberListReply reply = null;
        try {
            reply = WgApiParser.homeMemberListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo deleteHomeMember(long homeId, String delMemberId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("homeId", "" + homeId);
        params.put("delMemberId", delMemberId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/deleteHomeMember"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo moveHomePermission(long homeId, String toMemberId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("homeId", "" + homeId);
        params.put("toMemberId", toMemberId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/moveHomePermission"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo quitFromHome(long homeId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("homeId", "" + homeId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/quitFromHome"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo deleteHome(long homeId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("homeId", "" + homeId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/deleteHome"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo saveJoinHomeApply(long homeId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("homeId", "" + homeId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/saveJoinHomeApply"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public JoinHomeApplyListReply getJoinHomeApplyList(long homeId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("homeId", "" + homeId);
        setSystemParameters(map);
        String url = assembleUrl("wg/dm/getJoinHomeApplyList", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        JoinHomeApplyListReply reply = null;
        try {
            reply = WgApiParser.joinHomeApplyListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo saveJoinHomeApplyResult(long applyId, int result) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("applyId", "" + applyId);
        params.put("result", "" + result);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/saveJoinHomeApplyResult"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public HomeDeviceListReply getHomeDeviceList(long homeId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("homeId", "" + homeId);
        setSystemParameters(map);
        String url = assembleUrl("wg/dm/getHomeDeviceList", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        HomeDeviceListReply reply = null;
        try {
            reply = WgApiParser.homeDeviceListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BindedHomeDeviceListReply getBindedHomeDeviceList(long homeId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("homeId", "" + homeId);
        setSystemParameters(map);
        String url = assembleUrl("wg/dm/getBindedHomeDeviceList", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        BindedHomeDeviceListReply reply = null;
        try {
            reply = WgApiParser.bindedHomeDeviceListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public HistoryDeviceListReply getHistoryDeviceList() {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        setSystemParameters(map);
        String url = assembleUrl("wg/dm/getHistoryDeviceList", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        HistoryDeviceListReply reply = null;
        try {
            reply = WgApiParser.historyDeviceListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo addHistoryDeviceToHome(String wifiId, String wgDeviceId, long homeId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wifiId", wifiId);
        params.put("wgDeviceId", wgDeviceId);
        params.put("homeId", "" + homeId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/addHistoryDeviceToHome"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public CheckAppTemplateVersionReply checkAppTemplateVersion(String wgDeviceId, int terminaltype,
                                                                String currentversion) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("wgDeviceId", wgDeviceId);
        map.put("terminaltype", "" + terminaltype);
        map.put("currentversion", currentversion);
        setSystemParameters(map);
        String url = assembleUrl("wg/devicelib/checkAppTemplateVersion", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        CheckAppTemplateVersionReply reply = null;
        try {
            reply = WgApiParser.checkAppTemplateVersionReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public CheckDeviceLibVersionReply checkDeviceLibVersion(String wgDeviceId,
                                                            String currentversion, String wifiId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("wgDeviceId", wgDeviceId);
        map.put("currentversion", currentversion);
        map.put("wifiId", wifiId);
        setSystemParameters(map);
        String url = assembleUrl("wg/devicelib/checkDeviceLibVersion", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        CheckDeviceLibVersionReply reply = null;
        try {
            reply = WgApiParser.checkDeviceLibVersionReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo reportUpgradeResult(String wgDeviceId, int targettype, String orgversion,
                                        String newversion, int result) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wgDeviceId", wgDeviceId);
        params.put("targettype", "" + targettype);
        params.put("orgversion", orgversion);
        params.put("newversion", newversion);
        params.put("result", "" + result);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/devicelib/reportUpgradeResult"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    /*
     * 获取设备的状态及参数
     * (non-Javadoc)
     * @see com.hisense.smarthome.sdk.service.WgApiService#getDeviceStatusMeta(java.lang.String)
     */
    @Override
    public DeviceStatusMetaReply getDeviceStatusMeta(String devicetype) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("devicetype", devicetype);
        setSystemParameters(map);
        String url = assembleUrl("wg/ctl/getDeviceStatusMeta", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        DeviceStatusMetaReply reply = null;
        try {
            reply = WgApiParser.deviceStatusMetaReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo setDefaultLanguage(int defaultLanguageId) {
        BaseInfo reply;
        try {
            String jsonData = "";
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("defaultLanguageId", "" + defaultLanguageId);
            setSystemParameters(params);
            jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/setDefaultLanguage"),
                    Constants.ENCODE, params, Constants.DATATYPE_JSON);
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            reply = new BaseInfo();
            reply.setStatus("1");
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorName(e.getMessage());
            reply.setErrorInfo(errorInfo);
        }
        return reply;
    }

    @Override
    public DefaultLanguageReply getDefaultLanguage() {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        setSystemParameters(map);
        String url = assembleUrl("wg/dm/getDefaultLanguage", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        DefaultLanguageReply reply = null;
        try {
            reply = WgApiParser.defaultLanguageReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public DeviceTypeListReply getDeviceTypeList(int appType, int currentVersion) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("appType", "" + appType);
        map.put("currentVersion", "" + currentVersion);
        setSystemParameters(map);
        String url = assembleUrl("wg/dm/getDeviceTypeList", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        DeviceTypeListReply reply = null;
        try {
            reply = WgApiParser.deviceTypeListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo refreshDeviceStatus(String deviceId, String wifiId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("deviceId", deviceId);
        params.put("wifiId", wifiId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ctl/refreshDeviceStatus"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo uploadRemoteLogicCmd(String deviceId, String wifiId, String cmdList, int version) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("deviceId", deviceId);
        params.put("wifiId", wifiId);
        params.put("cmdList", cmdList);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ctl/uploadRemoteLogicCmd", true),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public DeviceLogicStatusListReply getDeviceLogicStatusList(String deviceList) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("deviceList", deviceList);
        setSystemParameters(map);
        String url = assembleUrl("wg/ctl/getDeviceLogicStatusList", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        DeviceLogicStatusListReply reply = null;
        try {
            reply = WgApiParser.deviceLogicStatusListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public DeviceCmdTimeLineReply getDeviceCmdTimeLine(long homeId, String preDay) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("homeId", "" + homeId);
        map.put("preDay", preDay);
        setSystemParameters(map);
        String url = assembleUrl("wg/ctl/getDeviceCmdTimeLine", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        DeviceCmdTimeLineReply reply = null;
        try {
            reply = WgApiParser.deviceCmdTimeLineReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public DeviceExternalInfoReply getDeviceExternalInfo(String deviceId, String wifiId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("wifiId", wifiId);
        map.put("deviceId", deviceId);
        setSystemParameters(map);
        String url = assembleUrl("wg/dm/getDeviceExternalInfo", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        DeviceExternalInfoReply reply = null;
        try {
            reply = WgApiParser.deviceExternalInfoReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public CmdMetaReply getCmdMeta(String devicetype) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("devicetype", devicetype);
        setSystemParameters(map);
        String url = assembleUrl("wg/ctl/getCmdMeta", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        CmdMetaReply reply = null;
        try {
            reply = WgApiParser.cmdMetaReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public UpdateSceneReply updateScene(HashMap<String, String> map) {
        String jsonData = "";
        if (map == null) {
            return null;
        }
        setSystemParameters(map);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ss/updateScene"),
                Constants.ENCODE, map, Constants.DATATYPE_JSON);
        UpdateSceneReply reply = null;
        try {
            reply = WgApiParser.updateSceneReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public SceneListReply getSceneList(long homeId, int multiCondition) {
        String jsonData;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("homeId", String.valueOf(homeId));
        map.put("multiCondition", String.valueOf(multiCondition));
        setSystemParameters(map);
        String url = assembleUrl("wg/ss/getSceneList", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        SceneListReply reply = null;
        try {
            reply = sceneListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public SimpleSceneListReply getSimpleSceneTemplateList() {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        setSystemParameters(map);
        String url = assembleUrl("wg/ss/getSimpleSceneTemplateList", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        SimpleSceneListReply reply = null;
        try {
            reply = WgApiParser.simpleSceneListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo deleteScene(int sceneId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("sceneId", "" + sceneId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ss/deleteScene"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo openSceneSwitch(int sceneId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("sceneId", "" + sceneId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ss/openSceneSwitch"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo closeSceneorSwitch(int sceneId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("sceneId", "" + sceneId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ss/closeSceneorSwitch"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo setManulSceneCondition(long homeId, int sceneConditionId, int sceneId,
                                           String sceneTrigCondition) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("homeId", "" + homeId);
        params.put("sceneConditionId", "" + sceneConditionId);
        params.put("sceneId", "" + sceneId);
        params.put("sceneTrigCondition", sceneTrigCondition);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ss/setManulSceneCondition"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public ConditionManulSceneListReply getConditionManulSceneList(long homeId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("homeId", "" + homeId);
        setSystemParameters(map);
        String url = assembleUrl("wg/ss/getConditionManulSceneList", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        ConditionManulSceneListReply reply = null;
        try {
            reply = WgApiParser.ConditionManulSceneListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo uploadMessage(String content, int msgOwnerType, String wifiId,
                                  String deviceId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("content", content);
        params.put("msgOwnerType", "" + msgOwnerType);
        params.put("wifiId", wifiId);
        params.put("deviceId", deviceId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/msg/uploadMessage"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public RoomListReply getRoomList(long homeId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("homeId", "" + homeId);
        setSystemParameters(map);
        String url = assembleUrl("wg/dm/getRoomList", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        RoomListReply reply = null;
        try {
            reply = WgApiParser.roomListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public RoomDeviceListReply getRoomDeviceList(int roomId, long homeId) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("roomId", "" + roomId);
        map.put("homeId", "" + homeId);
        setSystemParameters(map);
        String url = assembleUrl("wg/dm/getRoomDeviceList", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        RoomDeviceListReply reply = null;
        try {
            reply = WgApiParser.roomDeviceListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public SaveRoomInfoRely saveRoomInfo(int roomId, String roomName, String roomDesc,
                                         long homeId, long floorId, String roomImgFlag) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("roomId", "" + roomId);
        params.put("roomName", roomName);
        params.put("roomDesc", roomDesc);
        params.put("homeId", "" + homeId);
        params.put("floorId", "" + floorId);
        params.put("roomImgFlag", roomImgFlag);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/saveRoomInfo"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        SaveRoomInfoRely reply = null;
        try {
            reply = WgApiParser.saveRoomInfoRelyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo deleteRoom(int roomId, long homeId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("roomId", "" + roomId);
        params.put("homeId", "" + homeId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/deleteRoom"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo addDeviceToRoom(String wifiId, String wgDeviceId, int roomId, long homeId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("wifiId", wifiId);
        params.put("wgDeviceId", wgDeviceId);
        params.put("roomId", "" + roomId);
        params.put("homeId", "" + homeId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/addDeviceToRoom"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.saveRoomInfoRelyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo delDeviceFromRoom(int roomId, long homeId, String wifiId, String wgDeviceId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("roomId", "" + roomId);
        params.put("homeId", "" + homeId);
        params.put("wifiId", wifiId);
        params.put("wgDeviceId", wgDeviceId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/delDeviceFromRoom"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.saveRoomInfoRelyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public LatestExeManulSceneReply getLatestExeManulScene(long homeId, int count) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("homeId", "" + homeId);
        map.put("count", "" + count);
        setSystemParameters(map);
        String url = assembleUrl("wg/ss/getLatestExeManulScene", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        LatestExeManulSceneReply reply = null;
        try {
            reply = WgApiParser.getLatestExeManulSceneReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public LatestExeDeviceReply getLatestExeDevice(long homeId, int count) {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("homeId", "" + homeId);
        map.put("count", "" + count);
        setSystemParameters(map);
        String url = assembleUrl("wg/ctl/getLatestExeDevice", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        LatestExeDeviceReply reply = null;
        try {
            reply = WgApiParser.getLatestExeDeviceReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public SaveFloorInfoReply saveFloorInfo(HashMap<String, String> map) {
        String jsonData = "";
        if (map == null) {
            return null;
        }
        setSystemParameters(map);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/saveFloorInfo"),
                Constants.ENCODE, map, Constants.DATATYPE_JSON);
        SaveFloorInfoReply reply = null;
        try {
            reply = WgApiParser.saveFloorInfoReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo deleteFloor(long floorId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("floorId", "" + floorId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/deleteFloor"),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public FloorListReply getFloorList(long homeId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("homeId", "" + homeId);
        setSystemParameters(params);
        String url = assembleUrl("wg/dm/getFloorList", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        FloorListReply reply = null;
        try {
            reply = WgApiParser.floorListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public FloorRoomListReply getFloorRoomList(long floorId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("floorId", "" + floorId);
        setSystemParameters(params);
        String url = assembleUrl("wg/dm/getFloorRoomList", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        FloorRoomListReply reply = null;
        try {
            reply = WgApiParser.floorRoomListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo saveDeviceExternalInfo(HashMap<String, String> map) {
        String jsonData = "";
        if (map == null) {
            return null;
        }
        setSystemParameters(map);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/saveDeviceExternalInfo"),
                Constants.ENCODE, map, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public ScenePresetIconListReply getScenePresetIconList() {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        setSystemParameters(params);
        String url = assembleUrl("wg/ss/getScenePresetIconList", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        ScenePresetIconListReply reply = null;
        try {
            reply = WgApiParser.scenePresetIconListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public AllFloorRoomListReply getAllFloorRoomList(long homeId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("homeId", "" + homeId);
        setSystemParameters(params);
        String url = assembleUrl("wg/dm/getAllFloorRoomList", params);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        AllFloorRoomListReply reply = null;
        try {
            reply = WgApiParser.allFloorRoomListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo updateSceneExeCycle(HashMap<String, String> map) {
        String jsonData = "";
        if (map == null) {
            return null;
        }
        setSystemParameters(map);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ss/updateSceneExeCycle"),
                Constants.ENCODE, map, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }


    @Override
    public BaseInfo deleteDeviceExternalInfo(String deviceId, String wifiId,
                                             String extInfoKey, int extInfoId) {
        // TODO Auto-generated method stub
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("deviceId", deviceId);
        params.put("wifiId", wifiId);
        params.put("extInfoKey", extInfoKey);
        params.put("extInfoId", "" + extInfoId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/dm/deleteDeviceExternalInfo", true),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return reply;

    }

    @Override
    public WifiStatusListReply getWifiListStatus(String wifiIdList) {
        // TODO Auto-generated method stub
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("wifiIdList", wifiIdList);
        setSystemParameters(map);
        String url = assembleUrl("wg/ctl/getWifiListStatus", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        WifiStatusListReply reply = null;
        try {
            reply = WgApiParser.wifiStatusListReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public GwCmdMetaReply getGwCmdMeta() {
        // TODO Auto-generated method stub
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        setSystemParameters(map);
        String url = assembleUrl("wg/ctl/getGwCmdMeta", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        GwCmdMetaReply reply = null;
        try {
            reply = WgApiParser.gwCmdMetaReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public GwStatusMetaReply getGwStatusMeta() {
        String jsonData = "";
        HashMap<String, String> map = new HashMap<String, String>();
        setSystemParameters(map);
        String url = assembleUrl("wg/ctl/getGwStatusMeta", map);
        jsonData = HttpHandler.httpGetString(url, Constants.ENCODE, Constants.DATATYPE_JSON);
        GwStatusMetaReply reply = null;
        try {
            reply = WgApiParser.gwDevStatusMetaReplyParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public DeviceTypeListReply getDeviceTypeListLocalParser(String jsonStr) {
        // TODO Auto-generated method stub
        DeviceTypeListReply reply = null;
        try {
            reply = WgApiParser.deviceTypeListReplyParser(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;

    }

    @Override
    public HomeListReply getHomeListLocalParser(String jsonStr) {
        // TODO Auto-generated method stub
        HomeListReply reply = null;
        try {
            reply = WgApiParser.homeListReplyParser(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public AllFloorRoomListReply getAllFloorRoomListLocalParser(String jsonStr) {
        // TODO Auto-generated method stub
        AllFloorRoomListReply reply = null;
        try {
            reply = WgApiParser.allFloorRoomListReplyParser(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public HomeDeviceListReply getHomeDeviceListLocalParser(String jsonStr) {
        // TODO Auto-generated method stub
        HomeDeviceListReply reply = null;
        try {
            reply = WgApiParser.homeDeviceListReplyParser(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public GwCmdMetaReply getGwCmdMetaLocalParser(String jsonStr) {
        // TODO Auto-generated method stub
        GwCmdMetaReply reply = null;
        try {
            reply = WgApiParser.gwCmdMetaReplyParser(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public GwStatusMetaReply getGwStatusMetaLocalParser(String jsonStr) {
        // TODO Auto-generated method stub
        GwStatusMetaReply reply = null;
        try {
            reply = WgApiParser.gwDevStatusMetaReplyParser(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public DeviceStatusMetaReply getDeviceStatusMetaLocalParser(String jsonStr) {
        // TODO Auto-generated method stub
        DeviceStatusMetaReply reply = null;
        try {
            reply = WgApiParser.deviceStatusMetaReplyParser(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public CmdMetaReply getCmdMetaLocalParser(String jsonStr) {
        // TODO Auto-generated method stub
        CmdMetaReply reply = null;
        try {
            reply = WgApiParser.cmdMetaReplyParser(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public SecurityRemoteCmdReply uploadSecurityRemoteCmd(String wifiId, String wgDeviceId,
                                                          String functionCode,
                                                          String ctlCmd, int cmdVersion) {
        String jsonData;
        SecurityRemoteCmdReply reply = null;
        HashMap<String, String> params = new HashMap<>();
        params.put("wifiId", wifiId);
        params.put("wgDeviceId", wgDeviceId);
        params.put("functionCode", functionCode);
        params.put("ctlCmd", ctlCmd);
        params.put("cmdVersion", String.valueOf(cmdVersion));
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ctl/uploadSecurityRemoteCmd", true),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        try {
            reply = WgApiParser.getSecurityRemoteCmdParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo manualExeScene(long homeId, long sceneId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("homeId", "" + homeId);
        params.put("sceneId", "" + sceneId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ss/manualExeScene", false),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo addCommonScene(long homeId, long sceneId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("homeId", "" + homeId);
        params.put("sceneId", "" + sceneId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ss/addCommonScene", false),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo deleteCommonScene(long homeId, long sceneId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("homeId", "" + homeId);
        params.put("sceneId", "" + sceneId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ss/deleteCommonScene", false),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo saveAllCommonScenes(long homeId, String sceneIds) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("homeId", "" + homeId);
        params.put("sceneIds", sceneIds);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ss/saveAllCommonScenes", false),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo setTrgScenes(long srcSceneId, String trgSceneIds) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("srcSceneId", "" + srcSceneId);
        params.put("trgSceneIds", trgSceneIds);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ss/setTrgScenes", false),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public BaseInfo deleteTrgScene(long srcSceneId, long trgSceneId) {
        String jsonData = "";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("srcSceneId", "" + srcSceneId);
        params.put("trgSceneId", "" + trgSceneId);
        setSystemParameters(params);
        jsonData = HttpHandler.httpPostString(assembleUrl("wg/ss/deleteTrgScene", false),
                Constants.ENCODE, params, Constants.DATATYPE_JSON);
        BaseInfo reply = null;
        try {
            reply = WgApiParser.getBaseInfoParser(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

}
