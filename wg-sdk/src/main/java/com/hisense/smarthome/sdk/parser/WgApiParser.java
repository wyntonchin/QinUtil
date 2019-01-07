package com.hisense.smarthome.sdk.parser;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hisense.smarthome.sdk.bean.SecurityRemoteCmdReply;
import com.hisense.smarthome.sdk.bean.global.BaseInfo;
import com.hisense.smarthome.sdk.bean.global.ErrorInfo;
import com.hisense.smarthome.sdk.bean.wgapi.AllFloorRoomListReply;
import com.hisense.smarthome.sdk.bean.wgapi.Apply;
import com.hisense.smarthome.sdk.bean.wgapi.AvailableSleepCurveReply;
import com.hisense.smarthome.sdk.bean.wgapi.BindDeviceListReplay;
import com.hisense.smarthome.sdk.bean.wgapi.BindedCustomerListReply;
import com.hisense.smarthome.sdk.bean.wgapi.BindedHomeDeviceListReply;
import com.hisense.smarthome.sdk.bean.wgapi.CheckAppTemplateVersionReply;
import com.hisense.smarthome.sdk.bean.wgapi.CheckDeviceLibVersionReply;
import com.hisense.smarthome.sdk.bean.wgapi.Cmd;
import com.hisense.smarthome.sdk.bean.wgapi.Cmd2;
import com.hisense.smarthome.sdk.bean.wgapi.CmdDetail;
import com.hisense.smarthome.sdk.bean.wgapi.CmdMeta;
import com.hisense.smarthome.sdk.bean.wgapi.CmdMetaReply;
import com.hisense.smarthome.sdk.bean.wgapi.CmdParmMeta;
import com.hisense.smarthome.sdk.bean.wgapi.CmdSimple;
import com.hisense.smarthome.sdk.bean.wgapi.Condition;
import com.hisense.smarthome.sdk.bean.wgapi.ConditionManulSceneListReply;
import com.hisense.smarthome.sdk.bean.wgapi.ConditionSimple;
import com.hisense.smarthome.sdk.bean.wgapi.CrmArea;
import com.hisense.smarthome.sdk.bean.wgapi.CurveDetail;
import com.hisense.smarthome.sdk.bean.wgapi.DefaultLanguageReply;
import com.hisense.smarthome.sdk.bean.wgapi.Device;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceBindInfoReplay;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceCmdReplay;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceCmdTimeLineReply;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceExtendInfoReply;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceExternalInfoReply;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceFunctionListReplay;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceLogicStatusListReply;
import com.hisense.smarthome.sdk.bean.wgapi.DevicePatternFunctionListReply;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceStatus;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceStatusByWifiIdReplay;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceStatusMetaReply;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceStatusReply;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceType;
import com.hisense.smarthome.sdk.bean.wgapi.DeviceTypeListReply;
import com.hisense.smarthome.sdk.bean.wgapi.DiagnosisItem;
import com.hisense.smarthome.sdk.bean.wgapi.Fault;
import com.hisense.smarthome.sdk.bean.wgapi.Floor;
import com.hisense.smarthome.sdk.bean.wgapi.FloorListReply;
import com.hisense.smarthome.sdk.bean.wgapi.FloorRoomListReply;
import com.hisense.smarthome.sdk.bean.wgapi.Function;
import com.hisense.smarthome.sdk.bean.wgapi.FunctionParameter;
import com.hisense.smarthome.sdk.bean.wgapi.GwCmdMetaReply;
import com.hisense.smarthome.sdk.bean.wgapi.GwStatusMetaReply;
import com.hisense.smarthome.sdk.bean.wgapi.HistoryDeviceListReply;
import com.hisense.smarthome.sdk.bean.wgapi.HistoryStatusReply;
import com.hisense.smarthome.sdk.bean.wgapi.Home;
import com.hisense.smarthome.sdk.bean.wgapi.HomeDeviceListReply;
import com.hisense.smarthome.sdk.bean.wgapi.HomeInfoReply;
import com.hisense.smarthome.sdk.bean.wgapi.HomeListReply;
import com.hisense.smarthome.sdk.bean.wgapi.HomeMemberListReply;
import com.hisense.smarthome.sdk.bean.wgapi.HomeQrCodeSessionKeyReply;
import com.hisense.smarthome.sdk.bean.wgapi.JoinHomeApplyListReply;
import com.hisense.smarthome.sdk.bean.wgapi.LatestExeDeviceReply;
import com.hisense.smarthome.sdk.bean.wgapi.LatestExeManulSceneReply;
import com.hisense.smarthome.sdk.bean.wgapi.Member;
import com.hisense.smarthome.sdk.bean.wgapi.Msg;
import com.hisense.smarthome.sdk.bean.wgapi.MsgAndChannelsReplay;
import com.hisense.smarthome.sdk.bean.wgapi.Pattern;
import com.hisense.smarthome.sdk.bean.wgapi.PowerConsumpitonRankReply;
import com.hisense.smarthome.sdk.bean.wgapi.PowerConsumpitonReply;
import com.hisense.smarthome.sdk.bean.wgapi.PowerDetail;
import com.hisense.smarthome.sdk.bean.wgapi.RecommendModeReplay;
import com.hisense.smarthome.sdk.bean.wgapi.RecommendModeSwitchReply;
import com.hisense.smarthome.sdk.bean.wgapi.RepairStatus;
import com.hisense.smarthome.sdk.bean.wgapi.RepairTaskAreaReply;
import com.hisense.smarthome.sdk.bean.wgapi.RepirTaskBasicInfoReplay;
import com.hisense.smarthome.sdk.bean.wgapi.RepirTaskQueryReplay;
import com.hisense.smarthome.sdk.bean.wgapi.Room;
import com.hisense.smarthome.sdk.bean.wgapi.RoomDeviceListReply;
import com.hisense.smarthome.sdk.bean.wgapi.RoomListReply;
import com.hisense.smarthome.sdk.bean.wgapi.SaveFloorInfoReply;
import com.hisense.smarthome.sdk.bean.wgapi.SaveHomeInfoReply;
import com.hisense.smarthome.sdk.bean.wgapi.SaveRoomInfoRely;
import com.hisense.smarthome.sdk.bean.wgapi.Scene;
import com.hisense.smarthome.sdk.bean.wgapi.SceneIcon;
import com.hisense.smarthome.sdk.bean.wgapi.SceneListReply;
import com.hisense.smarthome.sdk.bean.wgapi.ScenePresetIconListReply;
import com.hisense.smarthome.sdk.bean.wgapi.SelfDiagnosisItemsReplay;
import com.hisense.smarthome.sdk.bean.wgapi.SimpleSceneListReply;
import com.hisense.smarthome.sdk.bean.wgapi.SimpleSceneTemplate;
import com.hisense.smarthome.sdk.bean.wgapi.SleepCurve;
import com.hisense.smarthome.sdk.bean.wgapi.SleepCurveDetailReply;
import com.hisense.smarthome.sdk.bean.wgapi.Status;
import com.hisense.smarthome.sdk.bean.wgapi.StatusMeta;
import com.hisense.smarthome.sdk.bean.wgapi.StatusParmMeta;
import com.hisense.smarthome.sdk.bean.wgapi.Task;
import com.hisense.smarthome.sdk.bean.wgapi.TaskExecResult;
import com.hisense.smarthome.sdk.bean.wgapi.TaskItem;
import com.hisense.smarthome.sdk.bean.wgapi.TaskResultReplay;
import com.hisense.smarthome.sdk.bean.wgapi.TaskTimeReplay;
import com.hisense.smarthome.sdk.bean.wgapi.UpdateSceneReply;
import com.hisense.smarthome.sdk.bean.wgapi.WGCustomer;
import com.hisense.smarthome.sdk.bean.wgapi.WifiDeviceVersionReplay;
import com.hisense.smarthome.sdk.bean.wgapi.WifiStatusListReply;
import com.hisense.smarthome.sdk.bean.wgapi.WifiStatusReplay;
import com.hisense.smarthome.sdk.bean.wgapi.EffectiveTime;
import com.hisense.smarthome.sdk.util.SDKUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WgApiParser {
    public static MsgAndChannelsReplay getMsgAndChannelsParser(String jsonString)
            throws IOException {
        MsgAndChannelsReplay response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new MsgAndChannelsReplay();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setPushServerIp(jsonObject.optString("pushServerIp"));
                response.setPushServerPort(jsonObject.optString("pushServerPort"));
                JSONArray jsonArray = jsonObject.optJSONArray("pushChannels");
                List<String> pushChannels = new ArrayList<String>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            String pushChannel = jo.optString("pushChannel");
                            pushChannels.add(pushChannel);
                        }
                    }
                }
                response.setPushChannels(pushChannels);
                JSONArray jsonArray2 = jsonObject.optJSONArray("msgList");
                List<Msg> msgList = new ArrayList<Msg>();
                if (jsonArray2 != null) {
                    int length1 = jsonArray2.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray2.optJSONObject(i);
                        if (jo != null) {
                            Msg msg = new Msg();
                            msg.setContent(jo.optString("content"));
                            msg.setCustomerMsgId(jo.optLong("customerMsgId"));
                            msg.setExpireTime(jo.optLong("expireTime"));
                            msg.setStartTime(jo.optLong("startTime"));
                            msg.setForceRemind(jo.optBoolean("forceRemind"));
                            msg.setFormatId(jo.optLong("formatId"));
                            msg.setMsgId(jo.optLong("msgId"));
                            msg.setMsgType(jo.optInt("msgType"));
                            msg.setShouldArrirved(jo.optBoolean("shouldArrirved"));
                            msg.setMsgLevel(jo.optInt("msgLevel"));
                            msg.setMsgOwnerId(jo.optString("msgOwnerId"));
                            msg.setMsgOwnerType(jo.optInt("msgOwnerType"));
                            msg.setMsgTypeCode(jo.optString("msgTypeCode"));
                            msgList.add(msg);
                        }
                    }
                }
                response.setMsgList(msgList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static DeviceStatusByWifiIdReplay getDeviceStatusByWifiIdParser(String jsonString)
            throws IOException {
        DeviceStatusByWifiIdReplay response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new DeviceStatusByWifiIdReplay();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setDeviceStatus(jsonObject.optString("deviceStatus"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static WifiStatusReplay getWifiStatusParser(String jsonString) throws IOException {
        WifiStatusReplay response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new WifiStatusReplay();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setDeviceStatus(jsonObject.optString("deviceStatus"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static BaseInfo getBaseInfoParser(String jsonString) throws IOException {
        /*JsonElement element = new JsonParser().parse(jsonString);
        JsonObject rsp = element.getAsJsonObject().getAsJsonObject("response");
        int resultCode = rsp.get("resultCode").getAsInt();
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setStatus(String.valueOf(resultCode));
        if (resultCode == 1) {
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorCode(String.valueOf(rsp.get("errorCode").getAsInt()));
            errorInfo.setErrorName(rsp.get("errorDesc").getAsString());
            baseInfo.setErrorInfo(errorInfo);
        }
        return baseInfo;*/
        BaseInfo response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new BaseInfo();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setDesc(jsonObject.optString("desc"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static UpdateSceneReply updateSceneReplyParser(String jsonString) throws IOException {
        UpdateSceneReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new UpdateSceneReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setSceneId(jsonObject.optInt("sceneId"));
                response.setDesc(jsonObject.optString("desc"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static SaveHomeInfoReply saveHomeInfoReplyParser(String jsonString) throws IOException {
        SaveHomeInfoReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new SaveHomeInfoReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setHomeId(jsonObject.optLong("homeId"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static DeviceFunctionListReplay getDeviceFunctionListParser(String jsonString)
            throws IOException {
        DeviceFunctionListReplay response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new DeviceFunctionListReplay();
                response.setOriginalData(jsonString);
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setDeviceName(jsonObject.optString("deviceName"));
                response.setFunctionCount(jsonObject.optInt("functionCount"));
                JSONArray jsonArray = jsonObject.optJSONArray("functionList");
                List<Function> functionList = new ArrayList<Function>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Function function = new Function();
                            function.setFunctionCode(jo.optString("functionCode"));
                            function.setFunctionDesc(jo.optString("functionDesc"));
                            function.setFunctionName(jo.optString("functionName"));
                            function.setCmdCode(jo.optString("cmdCode"));
                            function.setCmdValue(jo.optInt("cmdValue"));
                            function.setFunctionParameterType(
                                    jo.optString("functionParameterType"));

                            JSONArray jsonArrayMetaInfo = jo.optJSONArray("functionParameterList");
                            List<FunctionParameter> functionParameterList = new ArrayList<FunctionParameter>();
                            if (jsonArrayMetaInfo != null) {
                                int length2 = jsonArrayMetaInfo.length();
                                for (int j = 0; j < length2; j++) {
                                    JSONObject jm = jsonArrayMetaInfo.optJSONObject(j);
                                    if (jm != null) {
                                        FunctionParameter functionParameter = new FunctionParameter();
                                        functionParameter.setFunctionParameterDesc(
                                                jm.optString("functionParameterDesc"));
                                        functionParameter.setFunctionParameterName(
                                                jm.optString("functionParameterName"));
                                        functionParameter.setFunctionParameterValue(
                                                jm.optString("functionParameterValue"));
                                        functionParameterList.add(functionParameter);
                                    }
                                }
                            }
                            function.setFunctionParameterList(functionParameterList);
                            functionList.add(function);
                        }
                    }
                }
                response.setFunctionList(functionList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static WifiDeviceVersionReplay checkWifiDeviceVersionParser(String jsonString)
            throws IOException {
        WifiDeviceVersionReplay response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new WifiDeviceVersionReplay();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setLatestVersion(jsonObject.optString("latestVersion"));
                response.setUpgradeFlag(jsonObject.optInt("upgradeFlag"));
                response.setCurrentVersion(jsonObject.optInt("currentVersion"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static DeviceBindInfoReplay queryDeviceBindInfoParser(String jsonString)
            throws IOException {
       /* JsonElement element = new JsonParser().parse(jsonString);
        String json = element.getAsJsonObject().getAsJsonObject("response").toString();
        DeviceBindInfoReplay response = new Gson().fromJson(json, DeviceBindInfoReplay.class);*/
        DeviceBindInfoReplay response = null;
                try {
                    if (!SDKUtil.isEmpty(jsonString)) {
                        response = new DeviceBindInfoReplay();
                        JSONObject jsonObject = new JSONObject(jsonString);
                        jsonObject = jsonObject.optJSONObject("response");
                        response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                        if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                            ErrorInfo errorInfo = new ErrorInfo();
                            errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                            errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                            response.setErrorInfo(errorInfo);
                            return response;
                        }
                        response.setBindDeviceId(jsonObject.optString("bindDeviceId"));
                        response.setBindDeviceName(jsonObject.optString("bindDeviceName"));
                        response.setBindFlag(jsonObject.optInt("bindFlag"));
                        response.setIsVirtualDevice(jsonObject.optInt("isVirtualDevice"));
                        response.setBindHomeId(jsonObject.optLong("bindHomeId"));
                        response.setBindHomeName(jsonObject.optString("bindHomeName"));
                        JSONArray jsonArray = jsonObject.optJSONArray("bindDeviceList");
                        List<Device> deviceList = new ArrayList<Device>();
                        if (jsonArray != null) {
                            int length1 = jsonArray.length();
                            for (int i = 0; i < length1; i++) {
                                JSONObject jo = jsonArray.optJSONObject(i);
                                if (jo != null) {
                                    Device device = new Device();
                                    device.setAreaName(jo.optString("areaName"));
                                    device.setDeviceNickName(jo.optString("deviceNickName"));
                                    device.setWgDeviceId(jo.optString("wgDeviceId"));
                                    device.setWifiId(jo.optString("wifiId"));
                                    device.setDeviceTypeName(jo.optString("deviceTypeName"));
                                    device.setDeviceCode(jo.optString("deviceCode"));
                                    device.setDeviceName(jo.optString("deviceName"));
                                    device.setBrandName(jo.optString("brandName"));
                                    device.setBrandCode(jo.optString("brandCode"));
                                    device.setIsVirtualDevice(jo.optInt("isVirtualDevice"));
                                    device.setDeviceCode(jo.optString("deviceCode"));
                                    device.setExtInfo(jo.optString("extInfo"));
                                    device.setBindDeviceId(jo.optString("bindDeviceId"));
                                    device.setBindDeviceName(jo.optString("bindDeviceName"));
                                    deviceList.add(device);
                                }
                            }
                        }
                        response.setBindDeviceList(deviceList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
        return response;

    }

    public static BindDeviceListReplay getBindDeviceListParser(String jsonString)
            throws IOException {
        BindDeviceListReplay response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new BindDeviceListReplay();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setDeviceCount(jsonObject.optInt("deviceCount"));

                JSONArray jsonArray = jsonObject.optJSONArray("deviceList");
                List<Device> deviceList = new ArrayList<Device>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Device device = new Device();
                            device.setAreaName(jo.optString("areaName"));
                            device.setDeviceNickName(jo.optString("deviceNickName"));
                            device.setWgDeviceId(jo.optString("wgDeviceId"));
                            device.setWifiId(jo.optString("wifiId"));
                            device.setDeviceTypeName(jo.optString("deviceTypeName"));
                            device.setDeviceCode(jo.optString("deviceCode"));
                            device.setDeviceName(jo.optString("deviceName"));
                            device.setBrandName(jo.optString("brandName"));
                            device.setBrandCode(jo.optString("brandCode"));
                            device.setIsVirtualDevice(jo.optInt("isVirtualDevice"));
                            device.setDeviceCode(jo.optString("deviceCode"));
                            device.setExtInfo(jo.optString("extInfo"));
                            deviceList.add(device);
                        }
                    }
                }
                response.setDeviceList(deviceList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static TaskTimeReplay taskTimeReplayParser(String jsonString) throws IOException {
        TaskTimeReplay response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new TaskTimeReplay();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setWifiId(jsonObject.optString("wifiId"));
                response.setAvailable(jsonObject.optInt("available"));
                response.setCustomerid(jsonObject.optInt("customerid"));
                response.setLastUpdateTime(jsonObject.optString("lastUpdateTime"));
                response.setTaskMode(jsonObject.optInt("taskMode"));

                JSONArray jsonArray = jsonObject.optJSONArray("taskItemList");
                List<TaskItem> taskItemList = new ArrayList<TaskItem>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            TaskItem taskItem = new TaskItem();
                            taskItem.setExectime(jo.optString("exectime"));
                            taskItem.setDevType(jo.optInt("devType"));
                            JSONArray jsonArraycmd = jo.optJSONArray("cmdList");
                            List<Cmd> cmdList = new ArrayList<Cmd>();
                            if (jsonArraycmd != null) {
                                int length3 = jsonArraycmd.length();
                                for (int k = 0; k < length3; k++) {
                                    JSONObject jc = jsonArraycmd.optJSONObject(k);
                                    if (jc != null) {
                                        Cmd cmd = new Cmd();
                                        cmd.setCmdId(jc.optInt("cmdId"));
                                        cmd.setCmdOrder(jc.optInt("cmdOrder"));
                                        cmd.setCmdParm(jc.optInt("cmdParm"));
                                        cmdList.add(cmd);
                                    }
                                }
                            }
                            taskItem.setCmdList(cmdList);
                            taskItemList.add(taskItem);
                        }
                    }
                }
                response.setTaskItemList(taskItemList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static DeviceCmdReplay deviceCmdReplayParser(String jsonString) throws IOException {
        DeviceCmdReplay response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new DeviceCmdReplay();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setCount(jsonObject.optInt("count"));
                JSONArray jsonArray = jsonObject.optJSONArray("cmdMetaList");
                List<CmdMeta> cmdMetaList = new ArrayList<CmdMeta>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            CmdMeta cmdMeta = new CmdMeta();
                            cmdMeta.setCmdCode(jo.optString("cmdCode"));
                            cmdMeta.setCmdDesc(jo.optString("cmdDesc"));
                            cmdMeta.setCmdName(jo.optString("cmdName"));
                            cmdMeta.setCmdParm(jo.optInt("cmdParm"));
                            cmdMeta.setCmdValue(jo.optInt("cmdValue"));
                            cmdMeta.setParmType(jo.optString("parmType"));
                            cmdMeta.setTaskFlag(jo.optInt("taskFlag"));

                            JSONArray jsonArrayMetaInfo = jo.optJSONArray("cmdParmMetaList");
                            List<CmdParmMeta> cmdParmMetaList = new ArrayList<CmdParmMeta>();
                            if (jsonArrayMetaInfo != null) {
                                int length2 = jsonArrayMetaInfo.length();
                                for (int j = 0; j < length2; j++) {
                                    JSONObject jm = jsonArrayMetaInfo.optJSONObject(j);
                                    if (jm != null) {
                                        CmdParmMeta cmdParmMeta = new CmdParmMeta();
                                        cmdParmMeta.setParmDesc(jm.optString("parmDesc"));
                                        cmdParmMeta.setParmName(jm.optString("parmName"));
                                        cmdParmMeta.setParmValue(jm.optInt("parmValue"));
                                        cmdParmMeta.setTaskFlag(jm.optInt("taskFlag"));
                                        cmdParmMetaList.add(cmdParmMeta);
                                    }
                                }
                            }
                            cmdMeta.setCmdParmMetaList(cmdParmMetaList);
                            cmdMetaList.add(cmdMeta);
                        }
                    }
                }
                response.setCmdMetaList(cmdMetaList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static TaskResultReplay taskResultReplayParser(String jsonString) throws IOException {
        TaskResultReplay response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new TaskResultReplay();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setCount(jsonObject.optInt("count"));
                JSONArray jsonArray = jsonObject.optJSONArray("cmdMetaList");
                List<TaskExecResult> taskExecResultList = new ArrayList<TaskExecResult>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            TaskExecResult taskExecResult = new TaskExecResult();
                            taskExecResult.setActualExecuteDate(jo.optString("actualExecuteDate"));
                            taskExecResult.setCmdParm(jo.optInt("cmdParm"));
                            taskExecResult.setCmdValue(jo.optInt("cmdValue"));
                            taskExecResult.setDeviceId(jo.optString("deviceId"));
                            taskExecResult.setResultCode(jo.optInt("resultCode"));
                            taskExecResult.setResultMsg(jo.optString("resultMsg"));
                            taskExecResult.setWifiId(jo.optString("wifiId"));

                            taskExecResultList.add(taskExecResult);
                        }
                    }
                }
                response.setTaskExecResultList(taskExecResultList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static RepirTaskQueryReplay repirTaskQueryReplayParser(String jsonString)
            throws IOException {
        RepirTaskQueryReplay response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new RepirTaskQueryReplay();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("tasks");
                List<Task> tasks = new ArrayList<Task>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Task task = new Task();
                            task.setAddress(jo.optString("address"));
                            task.setBookingDate(jo.optString("bookingDate"));
                            task.setBookingDateRange(jo.optString("bookingDateRange"));
                            task.setCityId(jo.optString("cityId"));
                            task.setCityName(jo.optString("cityName"));
                            task.setCommentContext(jo.optString("commentContext"));
                            task.setCommentCreateDate(jo.optLong("commentCreateDate"));
                            task.setCommentStar(jo.optString("commentStar"));
                            task.setCreateDate(jo.optLong("createDate"));
                            task.setCustomerId(jo.optLong("customerId"));
                            task.setCustomerName(jo.optString("customerName"));
                            task.setDeviceId(jo.optString("deviceId"));
                            task.setId(jo.optLong("id"));
                            task.setProvinceId(jo.optString("provinceId"));
                            task.setProvinceName(jo.optString("provinceName"));
                            task.setQuestionDesc(jo.optString("questionDesc"));
                            task.setRegionId(jo.optString("regionId"));
                            task.setRegionName(jo.optString("regionName"));
                            task.setRepairStatus(jo.optInt("repairStatus"));
                            task.setRoadId(jo.optString("roadId"));
                            task.setRoadName(jo.optString("roadName"));
                            task.setServiceType(jo.optInt("serviceType"));
                            task.setTelephone(jo.optString("telephone"));
                            task.setFixedTelephone(jo.optString("fixedTelephone"));
                            task.setUpdateDate(jo.optLong("updateDate"));

                            JSONArray jsonArrayMetaInfo = jo.optJSONArray("statusList");
                            List<RepairStatus> statusList = new ArrayList<RepairStatus>();
                            if (jsonArrayMetaInfo != null) {
                                int length2 = jsonArrayMetaInfo.length();
                                for (int j = 0; j < length2; j++) {
                                    JSONObject jm = jsonArrayMetaInfo.optJSONObject(j);
                                    if (jm != null) {
                                        RepairStatus repairStatus = new RepairStatus();
                                        repairStatus.setName(jm.optString("name"));
                                        repairStatus.setOrder(jm.optInt("order"));
                                        repairStatus.setStatus(jm.optInt("status"));
                                        statusList.add(repairStatus);
                                    }
                                }
                            }
                            task.setStatusList(statusList);
                            jsonArrayMetaInfo = jo.optJSONArray("allStatusList");
                            List<RepairStatus> allStatusList = new ArrayList<RepairStatus>();
                            if (jsonArrayMetaInfo != null) {
                                int length2 = jsonArrayMetaInfo.length();
                                for (int j = 0; j < length2; j++) {
                                    JSONObject jm = jsonArrayMetaInfo.optJSONObject(j);
                                    if (jm != null) {
                                        RepairStatus repairStatus = new RepairStatus();
                                        repairStatus.setName(jm.optString("name"));
                                        repairStatus.setOrder(jm.optInt("order"));
                                        repairStatus.setStatus(jm.optInt("status"));
                                        allStatusList.add(repairStatus);
                                    }
                                }
                            }
                            task.setAllStatusList(allStatusList);

                            tasks.add(task);
                        }
                    }
                }
                response.setTasks(tasks);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static RepirTaskBasicInfoReplay repirTaskBasicInfoReplayParser(String jsonString)
            throws IOException {
        RepirTaskBasicInfoReplay response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new RepirTaskBasicInfoReplay();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("faults");
                List<Fault> faults = new ArrayList<Fault>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Fault fault = new Fault();
                            fault.setDesc(jo.optString("desc"));
                            fault.setId(jo.optLong("id"));
                            fault.setName(jo.optString("name"));
                            faults.add(fault);
                        }
                    }
                }
                response.setFaults(faults);
                jsonArray = jsonObject.optJSONArray("statusList");
                List<RepairStatus> statusList = new ArrayList<RepairStatus>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            RepairStatus repairStatus = new RepairStatus();
                            repairStatus.setName(jo.optString("name"));
                            repairStatus.setOrder(jo.optInt("order"));
                            repairStatus.setStatus(jo.optInt("status"));
                            statusList.add(repairStatus);
                        }
                    }
                }
                response.setStatusList(statusList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static SelfDiagnosisItemsReplay selfDiagnosisItemsReplayParser(String jsonString)
            throws IOException {
        SelfDiagnosisItemsReplay response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new SelfDiagnosisItemsReplay();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setTotalCount(jsonObject.optInt("totalCount"));
                JSONArray jsonArray = jsonObject.optJSONArray("diagnosisItemList");
                List<DiagnosisItem> diagnosisItemList = new ArrayList<DiagnosisItem>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            DiagnosisItem diagnosisItem = new DiagnosisItem();
                            diagnosisItem.setFaultType(jo.optString("faultType"));
                            diagnosisItem.setItemCode(jo.optString("itemCode"));
                            diagnosisItem.setItemDesc(jo.optString("itemDesc"));
                            diagnosisItem.setItemName(jo.optString("itemName"));
                            diagnosisItemList.add(diagnosisItem);
                        }
                    }
                }
                response.setDiagnosisItemList(diagnosisItemList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static RecommendModeReplay recommendModeReplayParser(String jsonString)
            throws IOException {
        RecommendModeReplay response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new RecommendModeReplay();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setModeCode(jsonObject.optString("modeCode"));
                response.setModeCtlCmd(jsonObject.optString("modeCtlCmd"));
                response.setModeDesc(jsonObject.optString("modeDesc"));
                response.setModeFunctionCode(jsonObject.optString("modeFunctionCode"));
                response.setModeName(jsonObject.optString("modeName"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static RecommendModeSwitchReply recommendModeSwitchParser(String jsonString)
            throws IOException {
        RecommendModeSwitchReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new RecommendModeSwitchReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setSwitchFlag(jsonObject.optString("switchFlag"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static RepairTaskAreaReply repairTaskAreaReplyParser(String jsonString)
            throws IOException {
        RepairTaskAreaReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new RepairTaskAreaReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("areas");
                List<CrmArea> areas = new ArrayList<CrmArea>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            CrmArea crmArea = new CrmArea();
                            crmArea.setId(jo.optString("id"));
                            crmArea.setName(jo.optString("name"));
                            crmArea.setParentId(jo.optString("parentId"));
                            crmArea.setType(jo.optString("type"));
                            areas.add(crmArea);
                        }
                    }
                }
                response.setAreas(areas);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static DevicePatternFunctionListReply devicePatternFunctionListReplyParser(
            String jsonString) throws IOException {
        DevicePatternFunctionListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new DevicePatternFunctionListReply();
                response.setOriginalData(jsonString);
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setDeviceName(jsonObject.optString("deviceName"));
                response.setPatternCount(jsonObject.optInt("patternCount"));
                JSONArray jsonArray = jsonObject.optJSONArray("patternList");
                List<Pattern> patternList = new ArrayList<Pattern>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Pattern pattern = new Pattern();
                            pattern.setDefaultRunTime(jo.optInt("defaultRunTime"));
                            pattern.setPatternCode(jo.optString("patternCode"));
                            pattern.setPatternDesc(jo.optString("patternDesc"));
                            pattern.setPatternName(jo.optString("patternName"));
                            JSONArray jsonArrayfunction = jo.optJSONArray("functionList");
                            List<Function> functionList = new ArrayList<Function>();
                            if (jsonArrayfunction != null) {
                                int length2 = jsonArrayfunction.length();
                                for (int a = 0; a < length2; a++) {
                                    JSONObject jofunction = jsonArray.optJSONObject(i);
                                    if (jofunction != null) {
                                        Function function = new Function();
                                        function.setFunctionCode(
                                                jofunction.optString("functionCode"));
                                        function.setFunctionDesc(
                                                jofunction.optString("functionDesc"));
                                        function.setFunctionName(
                                                jofunction.optString("functionName"));
                                        function.setCmdCode(jofunction.optString("cmdCode"));
                                        function.setCmdValue(jofunction.optInt("cmdValue"));
                                        function.setFunctionParameterType(
                                                jofunction.optString("functionParameterType"));

                                        JSONArray jsonArrayMetaInfo = jofunction
                                                .optJSONArray("functionParameterList");
                                        List<FunctionParameter> functionParameterList = new ArrayList<FunctionParameter>();
                                        if (jsonArrayMetaInfo != null) {
                                            int length3 = jsonArrayMetaInfo.length();
                                            for (int j = 0; j < length3; j++) {
                                                JSONObject jm = jsonArrayMetaInfo.optJSONObject(j);
                                                if (jm != null) {
                                                    FunctionParameter functionParameter = new FunctionParameter();
                                                    functionParameter.setFunctionParameterDesc(
                                                            jm.optString("functionParameterDesc"));
                                                    functionParameter.setFunctionParameterName(
                                                            jm.optString("functionParameterName"));
                                                    functionParameter.setFunctionParameterValue(
                                                            jm.optString("functionParameterValue"));
                                                    functionParameterList.add(functionParameter);
                                                }
                                            }
                                        }
                                        function.setFunctionParameterList(functionParameterList);
                                        functionList.add(function);
                                    }
                                }
                            }
                            pattern.setFunctionList(functionList);
                            patternList.add(pattern);
                        }
                    }
                }
                response.setPatternList(patternList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static BindedCustomerListReply bindedCustomerListReplyParser(String jsonString)
            throws IOException {
        BindedCustomerListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new BindedCustomerListReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("customerList");
                List<WGCustomer> customerList = new ArrayList<WGCustomer>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            WGCustomer wgCustomer = new WGCustomer();
                            wgCustomer.setBindTime(jo.optLong("bindTime"));
                            wgCustomer.setCustomerId(jo.optString("customerId"));
                            wgCustomer.setHeadImgUrl(jo.optString("headImgUrl"));
                            wgCustomer.setNickName(jo.optString("nickName"));
                            wgCustomer.setPlatformId(jo.optString("platformId"));
                            customerList.add(wgCustomer);
                        }
                    }
                }
                response.setCustomerList(customerList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static HistoryStatusReply historyStatusReplyParser(String jsonString)
            throws IOException {
        HistoryStatusReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new HistoryStatusReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                response.setDeviceStatusList(jsonObject.optString("deviceStatusList"));

                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static PowerConsumpitonReply powerConsumpitonReplyParser(String jsonString)
            throws IOException {
        PowerConsumpitonReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new PowerConsumpitonReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setQueryType(jsonObject.optInt("queryType"));
                JSONArray jsonArray = jsonObject.optJSONArray("queryResult");
                List<PowerDetail> queryResult = new ArrayList<PowerDetail>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            PowerDetail powerDetail = new PowerDetail();
                            powerDetail.setDataIndex(jo.optInt("dataIndex"));
                            powerDetail.setPowerConsume(jo.optInt("powerConsume"));
                            powerDetail.setRunTimeLenth(jo.optInt("runTimeLenth"));
                            queryResult.add(powerDetail);
                        }
                    }
                }
                response.setQueryResult(queryResult);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static PowerConsumpitonRankReply powerConsumpitonRankReplyParser(String jsonString)
            throws IOException {
        PowerConsumpitonRankReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new PowerConsumpitonRankReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setRank(jsonObject.optString("rank"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static DeviceExtendInfoReply deviceExtendInfoReplyParser(String jsonString)
            throws IOException {
        DeviceExtendInfoReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new DeviceExtendInfoReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setAirCleanFlag(jsonObject.optInt("airCleanFlag"));
                response.setAirCleanBuyUrl(jsonObject.optString("airCleanBuyUrl"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static AvailableSleepCurveReply availableSleepCurveReplyParser(String jsonString)
            throws IOException {
        AvailableSleepCurveReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new AvailableSleepCurveReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("sleepCurveList");
                List<SleepCurve> sleepCurveList = new ArrayList<SleepCurve>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            SleepCurve sleepCurve = new SleepCurve();
                            sleepCurve.setCurveCode(jo.optString("curveCode"));
                            sleepCurve.setCurveName(jo.optString("curveName"));
                            sleepCurve.setCurveType(jo.optInt("curveType"));
                            sleepCurve.setRunFalg(jo.optInt("runFalg"));
                            sleepCurveList.add(sleepCurve);
                        }
                    }
                }
                response.setSleepCurveList(sleepCurveList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static SleepCurveDetailReply sleepCurveDetailReplyParser(String jsonString)
            throws IOException {
        SleepCurveDetailReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new SleepCurveDetailReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setCurveLenth(jsonObject.optInt("curveLenth"));
                response.setRunMode(jsonObject.optInt("runMode"));
                JSONArray jsonArray = jsonObject.optJSONArray("curveDetailList");
                List<CurveDetail> curveDetailList = new ArrayList<CurveDetail>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            CurveDetail curveDetail = new CurveDetail();
                            curveDetail.setOrder(jo.optInt("order"));
                            JSONArray jsonArrayfunction = jo.optJSONArray("cmdList");
                            List<CmdDetail> cmdList = new ArrayList<CmdDetail>();
                            if (jsonArrayfunction != null) {
                                int length2 = jsonArrayfunction.length();
                                for (int a = 0; a < length2; a++) {
                                    JSONObject jofunction = jsonArray.optJSONObject(i);
                                    if (jofunction != null) {
                                        CmdDetail cmdDetail = new CmdDetail();
                                        cmdDetail.setCmdId(jofunction.optInt("cmdId"));
                                        cmdDetail.setCmdParam(jofunction.optInt("cmdParam"));
                                        cmdList.add(cmdDetail);
                                    }
                                }
                            }
                            curveDetail.setCmdList(cmdList);
                            curveDetailList.add(curveDetail);
                        }
                    }
                }
                response.setCurveDetailList(curveDetailList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static DeviceStatusReply deviceStatusReplyParser(String jsonString)
            throws IOException {
        DeviceStatusReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new DeviceStatusReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("deviceStatusList");
                List<DeviceStatus> deviceStatusList = new ArrayList<DeviceStatus>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            DeviceStatus deviceStatus = new DeviceStatus();
                            deviceStatus.setDeviceId(jo.optString("deviceId"));
                            deviceStatus.setDeviceStatus(jo.optString("deviceStatus"));
                            deviceStatus.setWifiId(jo.optString("wifiId"));
                            deviceStatus.setStatusVersion(jo.optInt("statusVersion"));
                            deviceStatus.setCmdVersion(jo.optInt("cmdVersion"));
                            deviceStatusList.add(deviceStatus);
                        }
                    }
                }
                response.setDeviceStatusList(deviceStatusList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static HomeInfoReply homeInfoReplyParser(String jsonString)
            throws IOException {
        HomeInfoReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new HomeInfoReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setHomeAddress(jsonObject.optString("homeAddress"));
                response.setHomedesc(jsonObject.optString("homeDesc"));
                response.setHomeName(jsonObject.optString("homeName"));
                response.setAreaName(jsonObject.optString("areaName"));
                response.setTimezone(jsonObject.optInt("timezone"));
                response.setProvince(jsonObject.isNull("province")?null:jsonObject.optString("province"));
                response.setCity(jsonObject.isNull("city")?null:jsonObject.optString("city"));
                response.setDistrict(jsonObject.isNull("district")?null:jsonObject.optString("district"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static HomeListReply homeListReplyParser(String jsonString)
            throws IOException {
        HomeListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new HomeListReply();
                response.setOriginalData(jsonString);
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("homeList");
                List<Home> homeList = new ArrayList<Home>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Home home = new Home();
                            home.setHomeId(jo.optLong("homeId"));
                            home.setHomeName(jo.optString("homeName"));
                            home.setRoleFlag(jo.optInt("roleFlag"));
                            home.setAddress(jo.optString("address"));
                            home.setProvince(jo.optString("province"));
                            home.setCity(jo.optString("city"));
                            home.setDistrict(jo.optString("district"));
                            homeList.add(home);
                        }
                    }
                }
                response.setHomeList(homeList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static HomeQrCodeSessionKeyReply homeQrCodeSessionKeyReplyParser(String jsonString)
            throws IOException {
        HomeQrCodeSessionKeyReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new HomeQrCodeSessionKeyReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setSessionKey(jsonObject.optString("sessionKey"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static HomeMemberListReply homeMemberListReplyParser(String jsonString)
            throws IOException {
        HomeMemberListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new HomeMemberListReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("memberList");
                List<Member> memberList = new ArrayList<Member>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Member member = new Member();
                            member.setMemberHeadImgUrl(jo.optString("memberHeadImgUrl"));
                            member.setMemberId(jo.optString("memberId"));
                            member.setMemberName(jo.optString("memberName"));
                            member.setRoleFlag(jo.optInt("roleFlag"));
                            memberList.add(member);
                        }
                    }
                }
                response.setMemberList(memberList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static JoinHomeApplyListReply joinHomeApplyListReplyParser(String jsonString)
            throws IOException {
        JoinHomeApplyListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new JoinHomeApplyListReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("applyList");
                List<Apply> applyList = new ArrayList<Apply>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Apply apply = new Apply();
                            apply.setApplyCustomerId(jo.optString("applyCustomerId"));
                            apply.setApplyCustomerName(jo.optString("applyCustomerName"));
                            apply.setApplyId(jo.optLong("applyId"));
                            apply.setHomeId(jo.optLong("homeId"));
                            apply.setHomeName(jo.optString("homeName"));
                            applyList.add(apply);
                        }
                    }
                }
                response.setApplyList(applyList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static HomeDeviceListReply homeDeviceListReplyParser(String jsonString)
            throws IOException {
        HomeDeviceListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new HomeDeviceListReply();
                response.setOriginalData(jsonString);
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("deviceList");
                List<Device> deviceList = new ArrayList<Device>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Device device = new Device();
                            device.setWifiId(jo.optString("wifiId"));
                            device.setBarCode(jo.optString("barCode"));
                            device.setDeviceNickName(jo.optString("deviceNickName"));
                            device.setStatus(jo.optInt("status"));
                            device.setDeviceId(jo.optString("deviceId"));
                            device.setBindTime(jo.optString("bindTime"));
                            device.setWifiModuleType(jo.optString("wifiModuleType"));
                            device.setMutiDevice(jo.optString("mutiDevice"));
                            device.setDeviceSubTypeCode(jo.optString("deviceSubTypeCode"));
                            device.setDeviceTypeCode(jo.optString("deviceTypeCode"));
                            device.setRoomId(jo.optInt("roomId"));
                            device.setRoomName(jo.optString("roomName"));
                            device.setDeviceTypeName(jo.optString("deviceTypeName"));
                            device.setDeviceCode(jo.optString("deviceCode"));
                            device.setDeviceName(jo.optString("deviceName"));
                            deviceList.add(device);
                        }
                    }
                }
                response.setDeviceList(deviceList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static BindedHomeDeviceListReply bindedHomeDeviceListReplyParser(String jsonString)
            throws IOException {
        BindedHomeDeviceListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new BindedHomeDeviceListReply();
                response.setOriginalData(jsonString);
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("deviceList");
                List<Device> deviceList = new ArrayList<Device>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Device device = new Device();
                            device.setWifiId(jo.optString("wifiId"));
                            device.setBarCode(jo.optString("barCode"));
                            device.setDeviceNickName(jo.optString("deviceNickName"));
                            device.setDeviceId(jo.optString("deviceId"));
                            device.setDeviceTypeName(jo.optString("deviceTypeName"));
                            device.setDeviceCode(jo.optString("deviceCode"));
                            device.setDeviceName(jo.optString("deviceName"));
                            device.setBrandName(jo.optString("brandName"));
                            device.setBrandCode(jo.optString("brandCode"));
                            device.setDeviceSubTypeCode(jo.optString("deviceSubTypeCode"));
                            device.setDeviceTypeCode(jo.optString("deviceTypeCode"));
                            device.setDisplayStatusList(jo.optString("displayStatusList"));
                            device.setDisplayCmtList(jo.optString("displayCmtList"));
                            device.setRoomId(jo.optInt("roomId"));
                            device.setRoomName(jo.optString("roomName"));
                            deviceList.add(device);
                        }
                    }
                }
                response.setDeviceList(deviceList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static HistoryDeviceListReply historyDeviceListReplyParser(String jsonString)
            throws IOException {
        HistoryDeviceListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new HistoryDeviceListReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("deviceList");
                List<Device> deviceList = new ArrayList<Device>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Device device = new Device();
                            device.setWifiId(jo.optString("wifiId"));
                            device.setDeviceNickName(jo.optString("deviceNickName"));
                            device.setDeviceId(jo.optString("deviceId"));
                            device.setHomeId(jo.optLong("homeId"));
                            device.setHomeName(jo.optString("homeName"));
                            device.setDeviceSubTypeCode(jo.optString("deviceSubTypeCode"));
                            device.setDeviceTypeCode(jo.optString("deviceTypeCode"));
                            device.setDeviceTypeName(jo.optString("deviceTypeName"));
                            device.setDeviceCode(jo.optString("deviceCode"));
                            device.setDeviceName(jo.optString("deviceName"));
                            deviceList.add(device);
                        }
                    }
                }
                response.setDeviceList(deviceList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }


    public static CheckAppTemplateVersionReply checkAppTemplateVersionReplyParser(String jsonString)
            throws IOException {
        CheckAppTemplateVersionReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new CheckAppTemplateVersionReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setChecksum(jsonObject.optString("checksum"));
                response.setDeviceLibVersion(jsonObject.optString("deviceLibVersion"));
                response.setFilesize(jsonObject.optLong("filesize"));
                response.setLatestVersion(jsonObject.optString("latestVersion"));
                response.setRelatedinfo(jsonObject.optString("relatedinfo"));
                response.setUpgradeFlag(jsonObject.optInt("upgradeFlag"));
                response.setUpgradeUrl(jsonObject.optString("upgradeUrl"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static CheckDeviceLibVersionReply checkDeviceLibVersionReplyParser(String jsonString)
            throws IOException {
        CheckDeviceLibVersionReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new CheckDeviceLibVersionReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setChecksum(jsonObject.optString("checksum"));
                response.setDeviceLibVersion(jsonObject.optString("deviceLibVersion"));
                response.setFilesize(jsonObject.optLong("filesize"));
                response.setLatestVersion(jsonObject.optString("latestVersion"));
                response.setRelatedinfo(jsonObject.optString("relatedinfo"));
                response.setUpgradeFlag(jsonObject.optInt("upgradeFlag"));
                response.setUpgradeUrl(jsonObject.optString("upgradeUrl"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static DeviceStatusMetaReply deviceStatusMetaReplyParser(String jsonString)
            throws IOException {
        DeviceStatusMetaReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new DeviceStatusMetaReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                response.setOriginalData(jsonString);
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setCount(jsonObject.optInt("count"));
                JSONArray jsonArray = jsonObject.optJSONArray("statusMetaList");
                List<StatusMeta> statusMetaList = new ArrayList<StatusMeta>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            StatusMeta statusMeta = new StatusMeta();
                            statusMeta.setStatusCode(jo.optString("statusCode"));
                            statusMeta.setStatusDesc(jo.optString("statusDesc"));
                            statusMeta.setStatusName(jo.optString("statusName"));
                            statusMeta.setStatusType(jo.optInt("statusType"));
                            statusMeta.setStatusValue(jo.optInt("statusValue"));
                            statusMeta.setTaskFlag(jo.optInt("taskFlag"));
                            statusMeta.setUnitFlag(jo.optString("unitFlag"));
                            statusMeta.setParamType(jo.optInt("paramType"));
                            statusMeta.setSceneOprTypeString(jo.optString("sceneOprType"));//cxx
                            statusMeta.setCmdValueList(jo.optString("cmdValueList"));
                            statusMeta.setCmdParmList(jo.optString("cmdParmList"));
                            JSONArray jsonArrayMetaInfo = jo.optJSONArray("statusParmMetaList");
                            List<StatusParmMeta> statusParmMetaList = new ArrayList<StatusParmMeta>();
                            if (jsonArrayMetaInfo != null) {
                                int length2 = jsonArrayMetaInfo.length();
                                for (int j = 0; j < length2; j++) {
                                    JSONObject jm = jsonArrayMetaInfo.optJSONObject(j);
                                    if (jm != null) {
                                        StatusParmMeta statusParmMeta = new StatusParmMeta();
                                        statusParmMeta.setParmDesc(jm.optString("parmDesc"));
                                        statusParmMeta.setParmName(jm.optString("parmName"));
                                        statusParmMeta.setParmValue(jm.optInt("parmValue"));
                                        statusParmMeta.setTaskFlag(jm.optInt("taskFlag"));
                                        statusParmMetaList.add(statusParmMeta);
                                    }
                                }
                            }
                            statusMeta.setStatusParmMetaList(statusParmMetaList);
                            statusMetaList.add(statusMeta);
                        }
                    }
                }
                response.setStatusMetaList(statusMetaList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static DeviceExternalInfoReply deviceExternalInfoReplyParser(String jsonString)
            throws IOException {
        DeviceExternalInfoReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new DeviceExternalInfoReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setExtInfo(jsonObject.optString("extInfo"));
                response.setExtInfoMeta(jsonObject.optString("extInfoMeta"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static DefaultLanguageReply defaultLanguageReplyParser(String jsonString)
            throws IOException {
        DefaultLanguageReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new DefaultLanguageReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setDefaultLanguageId(jsonObject.optInt("defaultLanguageId"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static DeviceTypeListReply deviceTypeListReplyParser(String jsonString)
            throws IOException {
        DeviceTypeListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new DeviceTypeListReply();
                response.setOriginalData(jsonString);
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                int currentVersion = jsonObject.optInt("currentVersion");
                response.setCurrentVersion(currentVersion);
                JSONArray jsonArray = jsonObject.optJSONArray("deviceTypeList");
                List<DeviceType> deviceTypeList = new ArrayList<DeviceType>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            DeviceType deviceType = new DeviceType();
                            deviceType.setDeviceTypeCode(jo.optString("deviceTypeCode"));
                            deviceType.setDeviceSubTypeCode(jo.optString("deviceSubTypeCode"));
                            deviceType.setDeviceTypeIcon(jo.optString("deviceTypeIcon"));
                            deviceType.setDeviceTypeName(jo.optString("deviceTypeName"));
                            deviceType.setDeviceTypePic(jo.optString("deviceTypePic"));
                            deviceType.setDeviceUseType(jo.optString("deviceUseType"));
                            deviceType.setDevLibExtParamType(jo.optString("devLibExtParamType"));
                            deviceType.setDevLibPath(jo.optString("devLibPath"));
                            deviceType.setUiModulePath(jo.optString("uiModulePath"));
                            deviceType.setAttentionUrl(jo.optString("attentionUrl"));
                            deviceType.setFaqUrl(jo.optString("faqUrl"));
                            deviceType.setDeviceIDNumber(jo.optInt("deviceIDNumber"));
                            deviceType.setOnoffStatus(jo.optInt("onoffStatus"));
                            deviceType.setLastUpdateTime(jo.optLong("lastUpdateTime"));
                            deviceType.setCmdUpdateTime(jo.optLong("cmdUpdateTime"));
                            deviceType.setStatusUpdateTime(jo.optLong("statusUpdateTime"));
                            deviceType.setStateKey(jo.optInt("stateKey"));
                            deviceType.setCmdKey(jo.optInt("cmdKey"));
                            deviceType.setNameOn(jo.optString("nameOn"));
                            deviceType.setStateOn(jo.optInt("stateOn"));
                            deviceType.setCmdOn(jo.optInt("cmdOn"));
                            deviceType.setNameOff(jo.optString("nameOff"));
                            deviceType.setStateOff(jo.optInt("stateOff"));
                            deviceType.setCmdOff(jo.optInt("cmdOff"));
                            deviceType.setUiModuleUpdateVersion(jo.optInt("uiModuleUpdateVersion"));
                            deviceType.setDevLibUpdateVersion(jo.optInt("devLibUpdateVersion"));
                            /*
                             * JSONArray jsonArrayMetaInfo =
                             * jo.optJSONArray("displayStatusList");
                             * List<StatusMeta> displayStatusList = new
                             * ArrayList<StatusMeta>(); if (jsonArrayMetaInfo !=
                             * null) { int length2 = jsonArrayMetaInfo.length();
                             * for (int j = 0; j < length2; j++) { JSONObject jm
                             * = jsonArrayMetaInfo.optJSONObject(j); if (jm !=
                             * null) { StatusMeta statusMeta = new StatusMeta();
                             * statusMeta.setStatusCode(jm.optString(
                             * "statusCode"));
                             * statusMeta.setStatusDesc(jm.optString(
                             * "statusDesc"));
                             * statusMeta.setStatusName(jm.optString(
                             * "statusName"));
                             * statusMeta.setStatusValue(jm.optInt("statusValue"
                             * )); displayStatusList.add(statusMeta); } } }
                             * deviceType.setDisplayStatusList(displayStatusList
                             * );
                             */
                            deviceTypeList.add(deviceType);
                        }
                    }
                }
                response.setDeviceTypeList(deviceTypeList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static DeviceLogicStatusListReply deviceLogicStatusListReplyParser(String jsonString)
            throws IOException {
        DeviceLogicStatusListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new DeviceLogicStatusListReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("deviceStatusList");
                List<DeviceStatus> deviceStatusList = new ArrayList<DeviceStatus>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            DeviceStatus deviceStatus = new DeviceStatus();
                            deviceStatus.setDeviceId(jo.optString("deviceId"));
                            deviceStatus.setWifiId(jo.optString("wifiId"));
                            deviceStatus.setStatusVersion(jo.optInt("statusVersion"));
                            deviceStatus.setCmdVersion(jo.optInt("cmdVersion"));
                            JSONArray jsonArrayMetaInfo = jo.optJSONArray("deviceStatus");
                            List<Status> statusList = new ArrayList<Status>();
                            if (jsonArrayMetaInfo != null) {
                                int length2 = jsonArrayMetaInfo.length();
                                for (int j = 0; j < length2; j++) {
                                    JSONObject jm = jsonArrayMetaInfo.optJSONObject(j);
                                    if (jm != null) {
                                        Status status = new Status();
                                        status.setStatusCode(jm.optString("statusCode"));
                                        status.setStatusDesc(jm.optString("statusDesc"));
                                        status.setStatusName(jm.optString("statusName"));
                                        status.setStatusParamValue(jm.optInt("statusParamValue"));
                                        status.setStatusValue(jm.optInt("statusValue"));
                                        statusList.add(status);
                                    }
                                }
                            }
                            deviceStatus.setStatusList(statusList);
                            deviceStatusList.add(deviceStatus);
                        }
                    }
                }
                response.setDeviceStatusList(deviceStatusList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static DeviceCmdTimeLineReply deviceCmdTimeLineReplyParser(String jsonString)
            throws IOException {
        DeviceCmdTimeLineReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new DeviceCmdTimeLineReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("cmdList");
                List<Cmd2> cmdList = new ArrayList<Cmd2>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Cmd2 cmd2 = new Cmd2();
                            cmd2.setCmd(jo.optString("Cmd"));
                            cmd2.setCmdTime(jo.optString("cmdTime"));
                            cmd2.setDevid(jo.optString("devid"));
                            cmd2.setDevNickName(jo.optString("devNickName"));
                            cmd2.setDevStatus(jo.optString("devStatus"));
                            cmd2.setStatus(jo.optString("status"));
                            cmd2.setWifiid(jo.optString("wifiid"));
                            cmd2.setCreater(jo.optString("creater"));
                            cmd2.setExecutortype(jo.optInt("executortype"));
                            cmd2.setExecutor(jo.optString("executor"));
                            cmdList.add(cmd2);
                        }
                    }
                }
                response.setCmdList(cmdList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static CmdMetaReply cmdMetaReplyParser(String jsonString) throws IOException {
        CmdMetaReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new CmdMetaReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                response.setOriginalData(jsonString);
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setCount(jsonObject.optInt("count"));
                JSONArray jsonArray = jsonObject.optJSONArray("cmdMetaList");
                List<CmdMeta> cmdMetaList = new ArrayList<CmdMeta>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            CmdMeta cmdMeta = new CmdMeta();
                            cmdMeta.setCmdCode(jo.optString("cmdCode"));
                            cmdMeta.setCmdDesc(jo.optString("cmdDesc"));
                            cmdMeta.setCmdName(jo.optString("cmdName"));
                            cmdMeta.setCmdParm(jo.optInt("cmdParm"));
                            cmdMeta.setCmdValue(jo.optInt("cmdValue"));
                            cmdMeta.setParmType(jo.optString("parmType"));
                            cmdMeta.setTaskFlag(jo.optInt("taskFlag"));
                            cmdMeta.setCmdId(jo.optInt("cmdId"));
                            cmdMeta.setStatusValueList(jo.optString("statusValueList"));
                            cmdMeta.setStatusParmList(jo.optString("statusParmList"));
                            JSONArray jsonArrayMetaInfo = jo.optJSONArray("cmdParmMetaList");
                            List<CmdParmMeta> cmdParmMetaList = new ArrayList<CmdParmMeta>();
                            if (jsonArrayMetaInfo != null) {
                                int length2 = jsonArrayMetaInfo.length();
                                for (int j = 0; j < length2; j++) {
                                    JSONObject jm = jsonArrayMetaInfo.optJSONObject(j);
                                    if (jm != null) {
                                        CmdParmMeta cmdParmMeta = new CmdParmMeta();
                                        cmdParmMeta.setParmDesc(jm.optString("parmDesc"));
                                        cmdParmMeta.setParmName(jm.optString("parmName"));
                                        cmdParmMeta.setParmValue(jm.optInt("parmValue"));
                                        cmdParmMeta.setTaskFlag(jm.optInt("taskFlag"));
                                        cmdParmMetaList.add(cmdParmMeta);
                                    }
                                }
                            }
                            cmdMeta.setCmdParmMetaList(cmdParmMetaList);
                            cmdMetaList.add(cmdMeta);
                        }
                    }
                }
                response.setCmdMetaList(cmdMetaList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static GwCmdMetaReply gwCmdMetaReplyParser(String jsonString) throws IOException {

        GwCmdMetaReply response = null;
        try {
                if (!SDKUtil.isEmpty(jsonString)) {
                    response = new GwCmdMetaReply();
                    JSONObject jsonObject = null;
                    jsonObject = new JSONObject(jsonString);
                    jsonObject = jsonObject.optJSONObject("response");
                    if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                        Log.e("getGwCmdMeta","into exception");
                        ErrorInfo errorInfo = new ErrorInfo();
                        errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                        errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                        response.setErrorInfo(errorInfo);
                        return response;
                    } else {
                        Log.e("getGwCmdMeta","into normal data");
                        //normal data
                        JsonElement element = new JsonParser().parse(jsonString);
                        String json = element.getAsJsonObject().getAsJsonObject("response").toString();
                        //GwCmdMetaReply response = new Gson().fromJson(json, GwCmdMetaReply.class);
                        response = new Gson().fromJson(json, GwCmdMetaReply.class);
                        //return response;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        //
        //        try {
        //            if (!SDKUtil.isEmpty(jsonString)) {
        //                response = new GwCmdMetaReply();
        //                JSONObject jsonObject = new JSONObject(jsonString);
        //                jsonObject = jsonObject.optJSONObject("response");
        //                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
        //                response.setOriginalData(jsonString);
        //                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
        //                    ErrorInfo errorInfo = new ErrorInfo();
        //                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
        //                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
        //                    response.setErrorInfo(errorInfo);
        //                    return response;
        //                }
        //                JSONArray gwJsonArray = jsonObject.optJSONArray("gwcmdMetaList");
        //                List<GwCmdMeta> gwCmdMetaList =  new ArrayList<GwCmdMeta>();
        //                List<CmdMeta> cmdMetaList = new ArrayList<CmdMeta>();
        //                String cmdMetaJsonStr = null;
        //                if(gwJsonArray != null){
        //                	int length0 = gwJsonArray.length();
        //                	for(int j=0;j<length0;j++){
        //                		JSONObject jo_gw = gwJsonArray.optJSONObject(j);
        //                		GwCmdMeta gwCmdMeta = new GwCmdMeta();
        //                		if(jo_gw != null){
        //                			gwCmdMeta.setDeviceSubTypeCode(jo_gw.optString("deviceSubTypeCode"));
        //                			cmdMetaJsonStr = jo_gw.optString("cmdMetaList");
        //
        //                			if (!SDKUtil.isEmpty(cmdMetaJsonStr)) {
        //                				JSONObject jo_cmdMeta = new JSONObject(cmdMetaJsonStr);
        //                                CmdMeta cmdMeta = new CmdMeta();
        //                                cmdMeta.setCmdCode(jo_cmdMeta.optString("cmdCode"));
        //                                cmdMeta.setCmdDesc(jo_cmdMeta.optString("cmdDesc"));
        //                                cmdMeta.setCmdName(jo_cmdMeta.optString("cmdName"));
        //                                cmdMeta.setCmdParm(jo_cmdMeta.optInt("cmdParm"));
        //                                cmdMeta.setCmdValue(jo_cmdMeta.optInt("cmdValue"));
        //                                cmdMeta.setParmType(jo_cmdMeta.optString("parmType"));
        //                                cmdMeta.setTaskFlag(jo_cmdMeta.optInt("taskFlag"));
        //                                cmdMeta.setCmdId(jo_cmdMeta.optInt("cmdId"));
        //                                cmdMeta.setStatusValueList(jo_cmdMeta.optString("statusValueList"));
        //                                //cmdMeta.setStatusParmList(jo_cmdMeta.optString("statusParmList"));
        //                                JSONArray jsonArrayMetaInfo = jo_cmdMeta.optJSONArray("cmdParmMetaList");
        //                                List<CmdParmMeta> cmdParmMetaList = new ArrayList<CmdParmMeta>();
        //                                if (jsonArrayMetaInfo != null) {
        //                                    int length2 = jsonArrayMetaInfo.length();
        //                                    for (int k = 0; k < length2; k++) {
        //                                        JSONObject jm = jsonArrayMetaInfo.optJSONObject(k);
        //                                        if (jm != null) {
        //                                            CmdParmMeta cmdParmMeta = new CmdParmMeta();
        //                                            cmdParmMeta.setParmDesc(jm.optString("parmDesc"));
        //                                            cmdParmMeta.setParmName(jm.optString("parmName"));
        //                                            cmdParmMeta.setParmValue(jm.optInt("parmValue"));
        //                                            cmdParmMeta.setTaskFlag(jm.optInt("taskFlag"));
        //                                            cmdParmMetaList.add(cmdParmMeta);
        //                                        }
        //                                    }
        //                                }
        //                                cmdMeta.setCmdParmMetaList(cmdParmMetaList);
        //                                cmdMetaList.add(cmdMeta);
        //                			}
        //                			gwCmdMeta.setCmdMetaList(cmdMetaList);
        //                			gwCmdMetaList.add(gwCmdMeta);
        //                		}
        //                	}
        //                }
        //                response.setGwcmdMetaList(gwCmdMetaList);
        //            }
        //        } catch (JSONException e) {
        //            e.printStackTrace();
        //            return null;
        //        }
        return response;

    }

    public static GwStatusMetaReply gwDevStatusMetaReplyParser(String jsonString)
            throws IOException {
        GwStatusMetaReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new GwStatusMetaReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    Log.e("getGwStatusMeta","into exception");
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                } else {
                    Log.e("getGwStatusMeta","into normal");
                    JsonElement element = new JsonParser().parse(jsonString);
                    String json = element.getAsJsonObject().getAsJsonObject("response").toString();
                    response = new Gson().fromJson(json, GwStatusMetaReply.class);
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
             return null;
        }
        //        try {
        //            if (!SDKUtil.isEmpty(jsonString)) {
        //                response = new GwStatusMetaReply();
        //                JSONObject jsonObject = new JSONObject(jsonString);
        //                jsonObject = jsonObject.optJSONObject("response");
        //                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
        //                response.setOriginalData(jsonString);
        //                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
        //                    ErrorInfo errorInfo = new ErrorInfo();
        //                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
        //                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
        //                    response.setErrorInfo(errorInfo);
        //                    return response;
        //                }
        //                JSONArray gwJsonArray = jsonObject.optJSONArray("gwstatusMetaList");
        //                List<GwStatusMeta> gwStatusMetaList =  new ArrayList<GwStatusMeta>();
        //                List<StatusMeta> statusMetaList = new ArrayList<StatusMeta>();
        //                String statusdMetaJsonStr = null;
        //                if(gwJsonArray != null){
        //                	int length0 = gwJsonArray.length();
        //                	for(int j=0;j<length0;j++){
        //                		JSONObject jo_gw = gwJsonArray.optJSONObject(j);
        //                		GwStatusMeta gwStatusMeta = new GwStatusMeta();
        //                		if(jo_gw != null){
        //                			gwStatusMeta.setDeviceSubTypeCode(jo_gw.optString("deviceSubTypeCode"));
        //                			statusdMetaJsonStr = jo_gw.optString("statusMetaList");
        //                			if (!SDKUtil.isEmpty(statusdMetaJsonStr)) {
        //                				JSONObject jo_statusMeta = new JSONObject(statusdMetaJsonStr);
        //                				 StatusMeta statusMeta = new StatusMeta();
        //                                 statusMeta.setStatusCode(jo_statusMeta.optString("statusCode"));
        //                                 statusMeta.setStatusDesc(jo_statusMeta.optString("statusDesc"));
        //                                 statusMeta.setStatusName(jo_statusMeta.optString("statusName"));
        //                                 statusMeta.setStatusType(jo_statusMeta.optInt("statusType"));
        //                                 statusMeta.setStatusValue(jo_statusMeta.optInt("statusValue"));
        //                                 statusMeta.setTaskFlag(jo_statusMeta.optInt("taskFlag"));
        //                                 statusMeta.setUnitFlag(jo_statusMeta.optString("unitFlag"));
        //                                 statusMeta.setParamType(jo_statusMeta.optInt("paramType"));
        //                                 statusMeta.setSceneOprType(jo_statusMeta.optString("sceneOprType"));//cxx
        //                                 statusMeta.setCmdValueList(jo_statusMeta.optString("cmdValueList"));
        //                                 statusMeta.setCmdParmList(jo_statusMeta.optString("cmdParmList"));
        //                                 JSONArray jsonArrayMetaInfo = jo_statusMeta.optJSONArray("statusParmMetaList");
        //                                 List<StatusParmMeta> statusParmMetaList = new ArrayList<StatusParmMeta>();
        //                                 if (jsonArrayMetaInfo != null) {
        //                                     int length2 = jsonArrayMetaInfo.length();
        //                                     for (int k = 0; k < length2; k++) {
        //                                         JSONObject jm = jsonArrayMetaInfo.optJSONObject(j);
        //                                         if (jm != null) {
        //                                             StatusParmMeta statusParmMeta = new StatusParmMeta();
        //                                             statusParmMeta.setParmDesc(jm.optString("parmDesc"));
        //                                             statusParmMeta.setParmName(jm.optString("parmName"));
        //                                             statusParmMeta.setParmValue(jm.optInt("parmValue"));
        //                                             statusParmMeta.setTaskFlag(jm.optInt("taskFlag"));
        //                                             statusParmMetaList.add(statusParmMeta);
        //                                         }
        //                                     }
        //                                 }
        //                                 statusMeta.setStatusParmMetaList(statusParmMetaList);
        //                                 statusMetaList.add(statusMeta);
        //                			}
        //                			gwStatusMeta.setStatusMetaList(statusMetaList);
        //                			gwStatusMetaList.add(gwStatusMeta);
        //                		}
        //                	}
        //                }
        //                response.setGwStatusMetaList(gwStatusMetaList);
        //            }
        //        } catch (JSONException e) {
        //            e.printStackTrace();
        //            return null;
        //        }
        return response;

    }
    public  static SimpleSceneListReply simpleSceneListReplyParser(String jsonString)throws IOException{
        SimpleSceneListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new SimpleSceneListReply();

                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("simpleSceneTemplateList");
                List<SimpleSceneTemplate> simpleSceneTemplateList = new ArrayList<>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            SimpleSceneTemplate simpleScene = new SimpleSceneTemplate();
                            simpleScene.setSimpleSceneId(jo.optInt("simpleSceneId"));
                            simpleScene.setSimpleSceneName(jo.optString("simpleSceneName"));

                            simpleScene.setSimpleSceneDesc(jo.optString("simpleSceneDesc"));
                            simpleScene.setSimpleSceneIconUrl(jo.optString("simpleSceneIconUrl"));
                            simpleScene.setSimpleSceneIconId(jo.optLong("simpleSceneIconId"));
                            simpleScene.setSimpleSceneType(jo.optInt("simpleSceneType"));
                            JSONArray jsonArrayMetaInfo1 = jo.optJSONArray("cmdList");
                            List<CmdSimple> cmdSimpleList = new ArrayList<>();
                            if (jsonArrayMetaInfo1 != null) {
                                int length2 = jsonArrayMetaInfo1.length();
                                for (int j = 0; j < length2; j++) {
                                    JSONObject jm = jsonArrayMetaInfo1.optJSONObject(j);
                                    if (jm != null) {
                                        CmdSimple cmdSimple = new CmdSimple();
                                        cmdSimple.setDeviceTypeCode(jm.optString("deviceTypeCode"));
                                        cmdSimple.setDeviceSubTypeCode(jm.optString("deviceSubTypeCode"));
                                        cmdSimpleList.add(cmdSimple);
                                    }
                                }
                            }
                            simpleScene.setCmdSimpleList(cmdSimpleList);
                            JSONArray jsonArrayMetaInfo2 = jo.optJSONArray("simpleSceneTrigCondition");
                            List<ConditionSimple> sceneTrigConditionSplList = new ArrayList<>();
                            if (jsonArrayMetaInfo2 != null) {
                                int length2 = jsonArrayMetaInfo2.length();
                                for (int j = 0; j < length2; j++) {
                                    JSONObject jm = jsonArrayMetaInfo2.optJSONObject(j);
                                    if (jm != null) {
                                        ConditionSimple condition = new ConditionSimple();
                                        condition.setDeviceTypeCode(jm.optString("deviceTypeCode"));
                                        condition.setDeviceSubTypeCode(jm.optString("deviceSubTypeCode"));
                                        sceneTrigConditionSplList.add(condition);
                                    }
                                }
                            }
                            simpleScene.setConditionSimpleList(sceneTrigConditionSplList);
                            simpleSceneTemplateList.add(simpleScene);
                        }
                    }
                }
                response.setSimpleSceneTemplateList(simpleSceneTemplateList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }
    public static SceneListReply sceneListReplyParser(String jsonString) throws IOException {
        SceneListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new SceneListReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("sceneList");
                List<Scene> sceneList = new ArrayList<Scene>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Scene scene = new Scene();
                            scene.setSceneId(jo.optInt("sceneId"));
                            scene.setSceneName(jo.optString("sceneName"));
                            scene.setSceneType(jo.optString("sceneType"));
                            scene.setStatus(jo.optString("status"));
                            scene.setSceneIconUrl(jo.optString("sceneIconUrl"));
                            scene.setSceneIconId(jo.optLong("sceneIconId"));
                            scene.setExecutionCycle(jo.optInt("executionCycle"));
                            scene.setExecutionDate(jo.optString("executionDate"));
                            scene.setExecutionTime(jo.optString("executionTime"));
                            scene.setConditionRelationship(jo.optInt("conditionRelationship"));
                            scene.setIsCommonScene(jo.optInt("isCommonScene"));
                            scene.setSeqNumber(jo.optInt("seqNumber"));
                            scene.setTrgSceneIds(jo.optString("trgSceneIds"));
                            JSONArray jsonArrayMetaInfo1 = jo.optJSONArray("cmdList");
                            List<Cmd> cmdList = new ArrayList<Cmd>();
                            if (jsonArrayMetaInfo1 != null) {
                                int length2 = jsonArrayMetaInfo1.length();
                                for (int j = 0; j < length2; j++) {
                                    JSONObject jm = jsonArrayMetaInfo1.optJSONObject(j);
                                    if (jm != null) {
                                        Cmd cmd = new Cmd();
                                        cmd.setCmdId(jm.optInt("cmdId"));
                                        cmd.setCmdOrder(jm.optInt("cmdOrder"));
                                        cmd.setCmdParm(jm.optInt("cmdParm"));
                                        cmd.setDeviceId(jm.optString("deviceId"));
                                        cmd.setWifiId(jm.optString("wifiId"));
                                        cmd.setDelayTime(jm.optInt("delayTime"));//cxx
                                        cmdList.add(cmd);
                                    }
                                }
                            }
                            scene.setCmdList(cmdList);
                            JSONArray jsonArrayMetaInfo2 = jo.optJSONArray("sceneTrigCondition");
                            List<Condition> sceneTrigCondition = new ArrayList<>();
                            if (jsonArrayMetaInfo2 != null) {
                                int length2 = jsonArrayMetaInfo2.length();
                                for (int j = 0; j < length2; j++) {
                                    JSONObject jm = jsonArrayMetaInfo2.optJSONObject(j);
                                    if (jm != null) {
                                        Condition condition = new Condition();
                                        condition.setDeviceId(jm.optString("deviceId"));
                                        condition.setOperateType(jm.optString("operateType"));
                                        condition.setStatusParamValue(jm.optInt("statusParamValue"));
                                        condition.setStatusValue(jm.optInt("statusValue"));
                                        condition.setWifiId(jm.optString("wifiId"));
                                        sceneTrigCondition.add(condition);
                                    }
                                }
                            }
                            scene.setSceneTrigCondition(sceneTrigCondition);

                            JSONArray jsonArrayMetaInfo3 = jo.optJSONArray("effectiveTimeList");
                            List<EffectiveTime> effectiveTimeList = new ArrayList<EffectiveTime>();
                            if (jsonArrayMetaInfo3 != null) {
                                int length3 = jsonArrayMetaInfo3.length();
                                for (int j = 0; j < length3; j++) {
                                    JSONObject jm = jsonArrayMetaInfo3.optJSONObject(j);
                                    if (jm != null) {
                                        EffectiveTime efTime = new EffectiveTime();
                                        efTime.setStart(jm.optString("start"));
                                        efTime.setEnd(jm.optString("end"));
                                        effectiveTimeList.add(efTime);
                                    }
                                }
                            }
                            scene.setEffectiveTimeList(effectiveTimeList);
                            sceneList.add(scene);
                        }
                    }
                }
                response.setSceneList(sceneList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static ConditionManulSceneListReply ConditionManulSceneListReplyParser(String jsonString)
            throws IOException {
        ConditionManulSceneListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new ConditionManulSceneListReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("sceneList");
                List<Scene> sceneList = new ArrayList<Scene>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Scene scene = new Scene();
                            scene.setSceneId(jo.optInt("sceneId"));
                            scene.setSceneName(jo.optString("sceneName"));
                            scene.setSceneType(jo.optString("sceneType"));
                            scene.setStatus(jo.optString("status"));
                            scene.setSceneIconUrl(jo.optString("sceneIconUrl"));
                            scene.setSceneIconId(jo.optLong("sceneIconId"));
                            scene.setConSceneId(jo.optInt("conSceneId"));
                            JSONArray jsonArrayMetaInfo1 = jo.optJSONArray("cmdList");
                            List<Cmd> cmdList = new ArrayList<Cmd>();
                            if (jsonArrayMetaInfo1 != null) {
                                int length2 = jsonArrayMetaInfo1.length();
                                for (int j = 0; j < length2; j++) {
                                    JSONObject jm = jsonArrayMetaInfo1.optJSONObject(j);
                                    if (jm != null) {
                                        Cmd cmd = new Cmd();
                                        cmd.setCmdId(jm.optInt("cmdId"));
                                        cmd.setCmdOrder(jm.optInt("cmdOrder"));
                                        cmd.setCmdParm(jm.optInt("cmdParm"));
                                        cmd.setDeviceId(jm.optString("deviceId"));
                                        cmd.setWifiId(jm.optString("wifiId"));
                                        cmdList.add(cmd);
                                    }
                                }
                            }
                            scene.setCmdList(cmdList);
                            JSONArray jsonArrayMetaInfo2 = jo.optJSONArray("sceneTrigCondition");
                            List<Condition> sceneTrigCondition = new ArrayList<Condition>();
                            if (jsonArrayMetaInfo2 != null) {
                                int length2 = jsonArrayMetaInfo2.length();
                                for (int j = 0; j < length2; j++) {
                                    JSONObject jm = jsonArrayMetaInfo2.optJSONObject(j);
                                    if (jm != null) {
                                        Condition condition = new Condition();
                                        condition.setDeviceId(jm.optString("deviceId"));
                                        condition.setOperateType(jm.optString("operateType"));
                                        condition
                                                .setStatusParamValue(jm.optInt("statusParamValue"));
                                        condition.setStatusValue(jm.optInt("statusValue"));
                                        condition.setWifiId(jm.optString("wifiId"));
                                        sceneTrigCondition.add(condition);
                                    }
                                }
                            }
                            scene.setSceneTrigCondition(sceneTrigCondition);
                            sceneList.add(scene);
                        }
                    }
                }
                response.setSceneList(sceneList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static RoomListReply roomListReplyParser(String jsonString)
            throws IOException {
        RoomListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new RoomListReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("roomList");
                List<Room> roomList = new ArrayList<Room>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Room room = new Room();
                            room.setHomeId(jo.optLong("homeId"));
                            room.setHomeName(jo.optInt("homeName"));
                            room.setRoomDesc(jo.optString("roomDesc"));
                            room.setRoomId(jo.optInt("roomId"));
                            room.setRoomName(jo.optString("roomName"));
                            room.setFloorId(jo.optLong("floorId"));
                            room.setRoomImgUrl(jo.optString("roomImgUrl"));
                            room.setRoomImgFlag(jo.optString("roomImgFlag"));
                            roomList.add(room);
                        }
                    }
                }
                response.setRoomList(roomList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static RoomDeviceListReply roomDeviceListReplyParser(String jsonString)
            throws IOException {
        RoomDeviceListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new RoomDeviceListReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("deviceList");
                List<Device> deviceList = new ArrayList<Device>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Device device = new Device();
                            device.setDeviceNickName(jo.optString("deviceNickName"));
                            device.setWifiId(jo.optString("wifiId"));
                            device.setDeviceId(jo.optString("deviceId"));
                            device.setStatus(jo.optInt("status"));
                            device.setBindTime(jo.optString("bindTime"));
                            deviceList.add(device);
                        }
                    }
                }
                response.setDeviceList(deviceList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static SaveRoomInfoRely saveRoomInfoRelyParser(String jsonString) throws IOException {
        SaveRoomInfoRely response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new SaveRoomInfoRely();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setDesc(jsonObject.optString("desc"));
                response.setRoomId(jsonObject.optInt("roomId"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static LatestExeManulSceneReply getLatestExeManulSceneReplyParser(String jsonString)
            throws IOException {
        LatestExeManulSceneReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new LatestExeManulSceneReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setDesc(jsonObject.optString("desc"));
                JSONArray jsonArray = jsonObject.optJSONArray("sceneList");
                List<Scene> sceneList = new ArrayList<Scene>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Scene scene = new Scene();
                            scene.setSceneId(jo.optInt("sceneId"));
                            scene.setSceneName(jo.optString("sceneName"));
                            scene.setExeTime(jo.optLong("exeTime"));
                            sceneList.add(scene);
                        }
                    }
                    response.setSceneList(sceneList);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static LatestExeDeviceReply getLatestExeDeviceReplyParser(String jsonString)
            throws IOException {
        LatestExeDeviceReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new LatestExeDeviceReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setDesc(jsonObject.optString("desc"));
                JSONArray jsonArray = jsonObject.optJSONArray("deviceList");
                List<Device> deviceList = new ArrayList<Device>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Device device = new Device();
                            device.setWifiId(jo.optString("wifiId"));
                            device.setDeviceId(jo.optString("deviceId"));
                            device.setDeviceNickName(jo.optString("deviceNickName"));
                            device.setExeTime(jo.optLong("exeTime"));
                            deviceList.add(device);
                        }
                    }
                    response.setDeviceList(deviceList);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static SaveFloorInfoReply saveFloorInfoReplyParser(String jsonString)
            throws IOException {
        SaveFloorInfoReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new SaveFloorInfoReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                response.setFloorId(jsonObject.optLong("floorId"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static FloorListReply floorListReplyParser(String jsonString)
            throws IOException {
        FloorListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new FloorListReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("floorList");
                List<Floor> floorList = new ArrayList<Floor>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Floor floor = new Floor();
                            floor.setFloorDesc(jo.optString("floorDesc"));
                            floor.setFloorId(jo.optLong("floorId"));
                            floor.setFloorName(jo.optString("floorName"));
                            floor.setFloorType(jo.optInt("floorType"));
                            floorList.add(floor);
                        }
                    }
                    response.setFloorList(floorList);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static FloorRoomListReply floorRoomListReplyParser(String jsonString)
            throws IOException {
        FloorRoomListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new FloorRoomListReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("roomList");
                List<Room> roomList = new ArrayList<Room>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Room room = new Room();
                            room.setHomeId(jo.optLong("homeId"));
                            room.setRoomName(jo.optString("roomName"));
                            room.setRoomDesc(jo.optString("roomDesc"));
                            room.setRoomImgUrl(jo.optString("roomImgUrl"));
                            room.setRoomImgFlag(jo.optString("roomImgFlag"));
                            roomList.add(room);
                        }
                    }
                    response.setRoomList(roomList);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static ScenePresetIconListReply scenePresetIconListReplyParser(String jsonString)
            throws IOException {
        ScenePresetIconListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new ScenePresetIconListReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("sceneIconList");
                List<SceneIcon> sceneIconList = new ArrayList<SceneIcon>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            SceneIcon sceneIcon = new SceneIcon();
                            sceneIcon.setSceneIconDesc(jo.optString("sceneIconDesc"));
                            sceneIcon.setSceneIconId(jo.optLong("sceneIconId"));
                            sceneIcon.setSceneIconName(jo.optString("sceneIconName"));
                            sceneIcon.setSceneIconUrl(jo.optString("sceneIconUrl"));
                            sceneIconList.add(sceneIcon);
                        }
                    }
                    response.setSceneIconList(sceneIconList);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static AllFloorRoomListReply allFloorRoomListReplyParser(String jsonString)
            throws IOException {
        AllFloorRoomListReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new AllFloorRoomListReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                response.setOriginalData(jsonString);
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
                JSONArray jsonArray = jsonObject.optJSONArray("floorList");
                List<Floor> floorList = new ArrayList<Floor>();
                if (jsonArray != null) {
                    int length1 = jsonArray.length();
                    for (int i = 0; i < length1; i++) {
                        JSONObject jo = jsonArray.optJSONObject(i);
                        if (jo != null) {
                            Floor floor = new Floor();
                            floor.setFloorId(jo.optLong("floorId"));
                            floor.setFloorName(jo.optString("floorName"));
                            floor.setFloorType(jo.optInt("floorType"));
                            floor.setFloorDesc(jo.optString("floorDesc"));
                            JSONArray jsonArrayMetaInfo1 = jo.optJSONArray("roomList");
                            List<Room> roomList = new ArrayList<Room>();
                            if (jsonArrayMetaInfo1 != null) {
                                int length2 = jsonArrayMetaInfo1.length();
                                for (int j = 0; j < length2; j++) {
                                    JSONObject jm = jsonArrayMetaInfo1.optJSONObject(j);
                                    if (jm != null) {
                                        Room room = new Room();
                                        room.setRoomId(jm.optInt("roomId"));
                                        room.setRoomDesc(jm.optString("roomDesc"));
                                        room.setRoomName(jm.optString("roomName"));
                                        room.setRoomImgUrl(jm.optString("roomImgUrl"));//
                                        room.setRoomImgFlag(jo.optString("roomImgFlag"));
                                        roomList.add(room);
                                    }
                                }
                            }
                            floor.setRoomList(roomList);
                            floorList.add(floor);
                        }
                    }
                }
                response.setFloorList(floorList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

    public static WifiStatusListReply wifiStatusListReplyParser(String jsonString)
            throws IOException {
        WifiStatusListReply response = null;
                try {
                    if (!SDKUtil.isEmpty(jsonString)) {
                        response = new WifiStatusListReply();
                        JSONObject jsonObject = new JSONObject(jsonString);
                        jsonObject = jsonObject.optJSONObject("response");
                        if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                            Log.e("WifiStatusList","into exception");
                            response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                            ErrorInfo errorInfo = new ErrorInfo();
                            errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                            errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                            response.setErrorInfo(errorInfo);
                            return response;
                        } else {
                            Log.e("WifiStatusList","into normal");
                            JsonElement element = new JsonParser().parse(jsonString);
                            String json = element.getAsJsonObject().getAsJsonObject("response").toString();
                            response = new Gson().fromJson(json, WifiStatusListReply.class);
                            //return response;
                        }
                    }
                    }catch (JSONException e) {
                            e.printStackTrace();
                            return null;
                    }

        //        try {
        //            if (!SDKUtil.isEmpty(jsonString)) {
        //                response = new WifiStatusListReply();
        //                JSONObject jsonObject = new JSONObject(jsonString);
        //                jsonObject = jsonObject.optJSONObject("response");
        //                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
        //                /*response.setWifiStatusList(jsonObject.optString("wifiListStatus"));*/
        //                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
        //                    ErrorInfo errorInfo = new ErrorInfo();
        //                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
        //                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
        //                    response.setErrorInfo(errorInfo);
        //                    return response;
        //                }
        //                JSONArray jsonArray = jsonObject.optJSONArray("wifiListStatus");
        //                List<WifiStatus> wifistatusList = new ArrayList<WifiStatus>();
        //                if (jsonArray != null) {
        //                    int length1 = jsonArray.length();
        //                    for (int i = 0; i < length1; i++) {
        //                        JSONObject jo = jsonArray.optJSONObject(i);
        //                        if (jo != null) {
        //                            WifiStatus status = new WifiStatus();
        //                            status.setWifiId(jo.optString("wifiId"));
        //                            status.setWifiStatus(jo.optInt("Status"));
        //                            wifistatusList.add(status);
        //                        }
        //                    }
        //                }
        //                response.setWifiStatusList(wifistatusList);
        //
        //            }
        //        } catch (JSONException e) {
        //            e.printStackTrace();
        //            return null;
        //        }
        return response;
    }
    public static SecurityRemoteCmdReply getSecurityRemoteCmdParser(String jsonString) throws IOException {
        SecurityRemoteCmdReply response = null;
        try {
            if (!SDKUtil.isEmpty(jsonString)) {
                response = new SecurityRemoteCmdReply();
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject = jsonObject.optJSONObject("response");
                response.setStatus(String.valueOf(jsonObject.optInt("resultCode")));
                if (!SDKUtil.isEmpty(jsonObject.optString("errorCode"))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(jsonObject.optString("errorCode"));
                    errorInfo.setErrorName(jsonObject.optString("errorDesc"));
                    response.setErrorInfo(errorInfo);
                    return response;
                }
               /* response.setDesc(jsonObject.optString("desc"));*/
                response.setDesc(jsonObject.isNull("desc")?null:jsonObject.optString("desc"));
                response.setCmdseq(jsonObject.optLong("cmdseq"));


            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return response;

    }

}
