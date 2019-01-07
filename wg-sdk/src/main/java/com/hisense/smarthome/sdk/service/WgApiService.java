package com.hisense.smarthome.sdk.service;

import android.text.TextUtils;

import com.hisense.smarthome.sdk.bean.SecurityRemoteCmdReply;
import com.hisense.smarthome.sdk.bean.global.BaseInfo;
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
import com.hisense.smarthome.sdk.service.impl.WgApiServiceImpl;
import com.hisense.smarthome.sdk.util.Constants;
import com.hisense.smarthome.sdk.util.Params;
import com.hisense.smarthome.sdk.util.SDKUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author YSD
 */
public abstract class WgApiService extends HiCloudService {
    /**
     * 公共数据服务带参构造函数
     *
     * @param info 携带有使用HiCloudSDK所必需的配置信息的数据结构
     */
    protected WgApiService(HiSDKInfo info) {
        super(info);
    }

    /**
     * 获取公共数据服务实例
     *
     * @param info 携带有使用HiCloudSDK所必需的配置信息的数据结构
     * @return 公共数据服务实例
     */
    public static WgApiService getInstance(HiSDKInfo info) {
        return WgApiServiceImpl.getInstance(info);
    }

    @Override
    protected String assembleUrl(String module) {
        if (TextUtils.isEmpty(module)) {
            return "";
        }
        StringBuilder builder = new StringBuilder(Constants.PROTOCAL_HTTP);
        String domain = getHiSDKInfo().getDomainName();
        if (!TextUtils.isEmpty(domain)) {
            builder.append(domain);
        } else {
            builder.append(Constants.DEFAULT_DOMAINNAME);
        }
        builder.append("/").append(module);
        return builder.toString();
    }

    /**
     * 拼接url, post方法使用
     *
     * @param module 域名后参数前的全部字符串, 不包括?
     * @param https  是否使用https
     */

    protected String assembleUrl(String module, boolean https) {
        if (TextUtils.isEmpty(module)) {
            return "";
        }
        StringBuilder builder = null;
        String domain = getHiSDKInfo().getDomainName();

        if (https) {
            builder = new StringBuilder(Constants.PROTOCAL_HTTPS + domain.split(":")[0]);
        } else {
            builder = new StringBuilder(Constants.PROTOCAL_HTTP + domain);
        }
        builder.append("/").append(module).append("");
        return builder.toString();
    }

    protected String assembleUrl(String module, Map<String, String> map, boolean https) {
        if (TextUtils.isEmpty(module)) {
            return "";
        }
        StringBuilder builder = null;
        if (https) {
            builder = new StringBuilder(Constants.PROTOCAL_HTTPS);
        } else {
            builder = new StringBuilder(Constants.PROTOCAL_HTTP);
        }
        String domain = getHiSDKInfo().getDomainName();
        if (!TextUtils.isEmpty(domain)) {
            builder.append(domain);
        } else {
            builder.append(Constants.DEFAULT_DOMAINNAME);
        }
        builder.append("/").append(module).append("?");
        for (Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = SDKUtil.toUTF_8(entry.getValue());
            builder.append(key).append("=").append(value).append("&");
        }
        return builder.toString();
    }

    @Override
    protected String assembleUrl(String module, Map<String, String> map) {
        if (TextUtils.isEmpty(module)) {
            return "";
        }
        StringBuilder builder = new StringBuilder(Constants.PROTOCAL_HTTP);
        String domain = getHiSDKInfo().getDomainName();
        if (!TextUtils.isEmpty(domain)) {
            builder.append(domain);
        } else {
            builder.append(Constants.DEFAULT_DOMAINNAME);
        }
        builder.append("/").append(module).append("?");
        for (Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = SDKUtil.toUTF_8(entry.getValue());
            builder.append(key).append("=").append(value).append("&");
        }
        return builder.toString();
    }

    protected HashMap<String, String> setSystemParameters(HashMap<String, String> params) {
        HiSDKInfo info = getHiSDKInfo();
        if (info == null) {
            return params;
        }
        params.put(Params.ACCESSTOKEN, info.getToken());
        params.put(Params.APIVERSION, info.getVersion());
        params.put(Params.TIMESTAMP, System.currentTimeMillis() / 1000 + "");
        params.put(Params.FORMAT, String.valueOf(Constants.DATATYPE_JSON));
        params.put(Params.LANGUAGEID, info.getLanguageId());
        params.put(Params.SIGN, info.getSign());
        //        params.put(Params.SOURCETYPE, String.valueOf(1));
        params.put(Params.TIMEZONE, String.valueOf(rawOffset));
        return params;
    }

    /**
     * 根据wifi模块id获取设备的状态</br>
     *
     * @param customerId 用户ID</br>
     * @param maxMsgId   上次拉取结果中的最大消息ID</br>
     * @param pushMsgIds 上次拉取后至本次拉取前收到的必达（必须保证APP收到）消息ID列表，以半角逗号连接</br>
     */
    public abstract MsgAndChannelsReplay getMsgAndChannels(long customerId, long maxMsgId,
                                                           String pushMsgIds);

    /**
     * 根据wifi模块id获取对应设备的状态</br>
     *
     * @param wifiId 是 string wifi模块的ID</br>
     * @return
     */
    @Deprecated
    public abstract DeviceStatusByWifiIdReplay getDeviceStatusByWifiId(String wifiId);

    /**
     * 根据wifi模块id获取wifi模块的状态</br>
     *
     * @param wifiId 是 string wifi模块的ID</br>
     * @return
     */
    public abstract WifiStatusReplay getWifiStatus(String wifiId);

    /**
     * 上报控制指令到云端用来远程控制设备</br>
     *
     * @param wifiId       是 string wifi模块的ID</br>
     * @param wgDeviceId   否 string 设备Id 注：兼容性考虑1.0版本可以不传递该参数，2.0版本的应用必须传递该参数
     * @param functionCode 是 string 功能项代码,多个功能标识间用“|”分隔</br>
     * @param ctlCmd       是 string 原始控制指令</br>
     * @param version      是 int 原始控制指令版本号</br>
     * @return
     */
    public abstract BaseInfo uploadRemoteCmd(String wifiId, String wgDeviceId, String functionCode,
                                             String ctlCmd, int cmdVersion);

    /**
     * 根据指定设备id的功能列表</br>
     *
     * @param wgDeviceId 是 string 白电设备的ID</br>
     * @param wifiId     否 string 通信模块的ID: 注：兼容性考虑1.0版本可以不传递该参数，2.0版本的应用必须传递该参数</br>
     * @return
     */
    public abstract DeviceFunctionListReplay getDeviceFunctionList(String wgDeviceId,
                                                                   String wifiId);

    /**
     * 将wifi模块与白电设备进行绑定</br>
     *
     * @param wifiId         是 string wifi模块的ID</br>
     * @param wgDeviceId     是 string 白电设备的ID</br>
     * @param homeId         否 number 家庭</br>
     *                       注：兼容性考虑1.0版本可以不传递该参数，2.0版本的应用必须传递该参数</br>
     * @param deviceNickName 否 string 设备昵称</br>
     * @param areaName       是 string 地址名称</br>
     * @param ability        否 网关能力属性集，长度为32的数值字符串，针对多网关设置起始两个字符，第一个字符表示是否支持多条件，第二个字符表示是否支持多网关，1-支持；2-不支持。</br>
     * @return
     */
    public abstract BaseInfo createDeviceBind(String wifiId, String wgDeviceId, long homeId,
                                              String deviceNickName, String areaName, String ability);

    public abstract BaseInfo createDeviceBind(String wifiId, String wgDeviceId, long homeId,
                                              String deviceNickName, String areaName);

    /**
     * 根据wifi模块id将客户与白电设备进行绑定</br>
     *
     * @param wifiId         是 string wifi模块的ID</br>
     * @param deviceNickName 否 string 设备昵称</br>
     * @return
     */
    @Deprecated
    public abstract BaseInfo addUserDeviceBind(String wifiId, String deviceNickName);

    /**
     * 根据wifi模块id更新设备的自定义信息。</br>
     *
     * @param wifiId         是 string wifi模块的ID</br>
     * @param wgDeviceId     否 string 设备Id 注：兼容性考虑1.0版本可以不传递该参数，2.0版本的应用必须传递该参数</br>
     * @param deviceNickName 是 string 设备昵称</br>
     * @param areaName       是 string 地区名称</br>
     * @return
     */
    public abstract BaseInfo updateDeviceDefineInfo(String wifiId, String wgDeviceId,
                                                    String deviceNickName,
                                                    String areaName);

    /**
     * 根据wifi模块id，将客户与白电设备解除绑定</br>
     *
     * @param wifiId   是 string wifi模块的ID</br>
     * @param password 是 string 客户登陆密码经过加密后的字符串，加密算法：</br>
     *                 md5（密码明文+客户对应的CustomerId）加密</br>
     * @return
     */
    @Deprecated
    public abstract BaseInfo delUserDeviceBind(String wifiId, String password);

    /**
     * 根据wifi模块id，将对应白电设备的绑定关系全部解除。</br>
     *
     * @param wifiId   是 string wifi模块的ID</br>
     * @param password 是 string 客户登陆密码经过加密后的字符串，加密算法：</br>
     *                 md5（密码明文+客户对应的CustomerId）加密</br>
     * @return
     */
    @Deprecated
    public abstract BaseInfo delDeviceBindAll(String wifiId, String password);

    /**
     * 获取登陆客户所绑定的设备列表。</br>
     *
     * @return
     */
    @Deprecated
    public abstract BindDeviceListReplay getBindDeviceList();

    /**
     * 根据wifi模块id获取设备绑定关系。</br>
     *
     * @param wifiId 是 string wifi模块的ID</br>
     * @return
     */
    public abstract DeviceBindInfoReplay queryDeviceBindInfo(String wifiId);

    /**
     * 根据wifi模块id获取对应的升级版本</br>
     *
     * @param wifiId      是 string wifi模块的ID</br>
     * @param wifiId</br>
     * @return
     */
    public abstract WifiDeviceVersionReplay checkWifiDeviceVersion(String wifiId);

    /**
     * 反馈wifi模块需要升级的确认信息</br>
     *
     * @param wifiId 是 string wifi模块的ID</br>
     * @return
     */
    public abstract BaseInfo confirmUpgrade(String wifiId);

    /**
     * 定时任务上传时间表
     *
     * @param map map 中包含wifiId 是 string wifi模块的ID available 是 number
     *            时间表是否可用，0：不可用（不调度执行），1：可用 taskMode 是 number
     *            时间表调度模式，1：执行一次；2：周期性执行（天的周期） taskItemList 是 List <TaskItem>
     *            定时任务项列表转成的json字符串
     * @return
     */
    public abstract BaseInfo uploadTaskTime(HashMap<String, String> map);

    /**
     * 获取定时任务时间表
     *
     * @param wifiId 是 string WIFI模块的ID
     * @return
     */
    public abstract TaskTimeReplay getTaskTime(String wifiId);

    /**
     * 设置定时任务指令是否可用</br>
     * wifiId 是 string wifi模块的ID</br>
     * flag 是 string 可用标识，0：不可用（不调度执行），1：可用</br>
     */
    public abstract BaseInfo uploadTaskAvaiable(String wifiId, String flag);

    /**
     * 获取定时任务结果</br>
     *
     * @param wifiId 是 string WIFI模块的ID</br>
     */
    public abstract TaskResultReplay getTaskResult(String wifiId);

    /**
     * 获取设备的指令及参数</br>
     *
     * @param deviceId 是 string 空调条码</br>
     * @param wifiId   否 string 通信模块Id 注：兼容性考虑1.0版本可以不传递该参数，2.0版本的应用必须传递该参数
     */
    public abstract DeviceCmdReplay getDeviceCmd(String deviceId, String wifiId);

    /**
     * 生成报修单</br>
     *
     * @param map</br> map包含以下内容</br>
     *                 customerName string 是 客户姓名（128）</br>
     *                 telephone string 否 电话（60），和fixedTelephone，二者最少需要填一个值。
     *                 fixedTelephone string 否 固定电话（60），和telephone，二者最少需要填一个值 address
     *                 string 是 详细地址（512）</br>
     *                 telephone string 是 电话（60）</br>
     *                 bookingDate string 是 预约日期，格式为yyyy-mm-dd，如2015-07-01（45）</br>
     *                 region string 否 区县（64）</br>
     *                 road string 否 街道（128）</br>
     *                 areaCode string 是 市级地区代码（32）</br>
     *                 areaName string 是 市级地区名称（64）</br>
     *                 serviceType int 是 服务类型（枚举值）1：安装，2：维修，3：保养</br>
     *                 bookingDateRange string 是 预约时间范围（全天、上午、下午、晚上）（10）</br>
     *                 deviceId string 是 设备ID（64）</br>
     *                 faultId long 是 故障类型ID</br>
     *                 questionDesc string 是 故障描述（4000）</br>
     * @return
     */
    public abstract BaseInfo repirTaskInsert(HashMap<String, String> map);

    /**
     * 查询报修单
     *
     * @param taskId      否 string 维修工单ID，如果为空则忽略该参数，否则使用该ID直接查询。</br>
     * @param deviceId    否 string 设备ID，如果为空则忽略该参数，否则将查询用户下和该设备ID相关的报修工单</br>
     * @param serviceType 否 String 服务类型（枚举值）1：安装，2：维修，3：保养。如果不为空，则将该值作为查询条件</br>
     * @return
     */
    public abstract RepirTaskQueryReplay repirTaskQuery(String taskId, String deviceId,
                                                        String serviceType);

    /**
     * 报修单评价
     *
     * @param taskId  是 long 报修工单ID
     * @param star    是 float 评价星级
     * @param context 是 string 评价内容(4000)
     * @return
     */
    public abstract BaseInfo repirTaskComment(String taskId, float star, String context);

    /**
     * 基础数据获取接口
     *
     * @return
     */
    public abstract RepirTaskBasicInfoReplay repirTaskGetBasicInfo();

    /**
     * 获取空调设备的自诊断项
     *
     * @return
     */
    public abstract SelfDiagnosisItemsReplay getSelfDiagnosisItems();

    /**
     * @param acDeviceId 是 string 空调设备的ID
     * @return
     */
    public abstract RecommendModeSwitchReply getRecommendModeSwitch(String acDeviceId);

    /**
     * 获取空调设备的推荐模式
     *
     * @param acDeviceId 是 string 空调设备的ID
     * @return
     */
    public abstract RecommendModeReplay getRecommendMode(String acDeviceId);

    /**
     * 空调自动推荐模式开关
     *
     * @param acDeviceId 是 string 空调设备的ID
     * @param switchFlag 是 string 1：打开，0：关闭
     * @return
     */
    public abstract BaseInfo saveRecommendModeSwitch(String acDeviceId, String switchFlag);

    /**
     * 获取空调设备的耗电量
     *
     * @param acDeviceId 是 string 空调设备的ID
     * @param queryType  是 number 查询类型：1：按照周查询每天的耗电量2：按照年查询每月的耗电量
     * @param queryValue 是 number queryType=1时：传年份和周数，如：201601
     *                   代表2016年的第一周queryType=2时：传年份，如：2016
     * @return
     */
    public abstract PowerConsumpitonReply getPowerConsumpiton(String acDeviceId, int queryType,
                                                              int queryValue);

    /**
     * 获取空调设备的耗电量排名
     *
     * @param acDeviceId 是 string 空调设备的ID
     * @param queryType  是 number 查询类型：1：按照周查询每天的耗电量2：按照年查询每月的耗电量
     * @param queryValue 是 number queryType=1时：传年份和周数，如：201601
     *                   代表2016年的第一周queryType=2时：传年份，如：2016
     * @return
     */
    public abstract PowerConsumpitonRankReply getPowerConsumpitonRank(String acDeviceId,
                                                                      int queryType, int queryValue);

    /**
     * 获取空调设备的扩展信息
     *
     * @param acDeviceId 是 string 空调设备的ID
     * @return
     */
    public abstract DeviceExtendInfoReply getDeviceExtendInfo(String acDeviceId);

    /**
     * 获取可用的睡眠曲线
     *
     * @param acDeviceId 是 string 空调设备的ID
     * @return
     */
    public abstract AvailableSleepCurveReply getAvailableSleepCurve(String acDeviceId);

    /**
     * 获取屏蔽的睡眠曲线
     *
     * @param acDeviceId 是 string 空调设备的ID
     * @return
     */
    public abstract ShieldSleepCurveReply getShieldSleepCurve(String acDeviceId);

    /**
     * 获取睡眠曲线详细信息
     *
     * @param acDeviceId
     * @param curveCode  是 string 睡眠曲线标识
     * @param curveType  是 number 睡眠曲线类型：1：预置2：自定义
     * @return
     */
    public abstract SleepCurveDetailReply getSleepCurveDetail(String acDeviceId, String curveCode,
                                                              int curveType);

    /**
     * 睡眠曲线启动和关闭
     *
     * @param acDeviceId 是 string 空调设备的ID
     * @param curveCode  是 string 睡眠曲线标识
     * @param curveType  是 number 睡眠曲线类型：1：预置2：自定义
     * @param switchFlag 是 number 启动标识：1：启动2：关闭
     * @return
     */
    public abstract BaseInfo setSleepCurveOnOff(String acDeviceId, String curveCode, int curveType,
                                                int switchFlag);

    /**
     * 睡眠曲线删除屏蔽恢复
     *
     * @param acDeviceId 是 string 空调设备的ID
     * @param curveCode  是 string 睡眠曲线标识
     * @param curveType  是 number 睡眠曲线类型：1：预置2：自定义
     * @param oprFlag    是 number
     *                   启动开关：1：屏蔽(只有预置的可以屏蔽)2：删除(只有自定义的可以删除)3：恢复(只有预置的可以恢复)
     * @return
     */
    public abstract BaseInfo setSleepCurveStatus(String acDeviceId, String curveCode, int curveType,
                                                 int oprFlag);

    /**
     * 保存用户自定义睡眠曲线
     *
     * @param acDeviceId      是 string 空调设备的ID
     * @param curveCode       否 string 睡眠曲线标识 注意：编辑时必须传递该参数
     * @param curveName       是 string 曲线名称
     * @param curveLenth      是 number 曲线时长（单位：小时）
     * @param curveDetailList 是 json格式 睡眠曲线详细信息 curveDetailList睡眠曲线信息 order 是
     *                        number 睡眠曲线的顺序号 cmdList 是 jsonarray 睡眠曲线点的指令列表 cmdList指令信息
     *                        cmdId 是 number 指令标识 cmdParam 是 number 指令参数，注意：1、
     *                        设定温度指令对应的参数是相对值2、 温度的可调范围是：+4~-4
     * @return
     */
    public abstract BaseInfo saveSleepCurveInfo(String acDeviceId, String curveCode,
                                                String curveName, int curveLenth, String curveDetailList);

    /**
     * 3.2.2.4 获取省份数据
     *
     * @return
     */
    public abstract RepairTaskAreaReply getProvinces();

    /**
     * 3.2.2.5 获取城市数据
     *
     * @param parentId 父地区ID
     * @return
     */
    public abstract RepairTaskAreaReply getCities(String parentId);

    /**
     * 3.2.2.6 获取地区数据
     *
     * @param parentId 父地区ID
     * @return
     */
    public abstract RepairTaskAreaReply getRegions(String parentId);

    /**
     * 3.2.2.7 获取街道数据
     *
     * @param parentId 父地区ID
     * @return
     */
    public abstract RepairTaskAreaReply getRoads(String parentId);

    /**
     * 3.2.2.8 更换wifi模块与白电设备绑定
     *
     * @param wifiId     wifi模块的ID
     * @param wgDeviceId 白电设的ID
     * @param areaName   地址名称
     * @return
     */
    public abstract BaseInfo updateWifiDevice(HashMap<String, String> params);

    /**
     * 保存APP意见反馈信息
     *
     * @param feedbackContent 是 string 反馈意见
     * @param mobilePhone     否 string 手机号
     * @param customerName    是 string 客户姓名
     */
    public abstract BaseInfo repairAppFeedbackInsert(String feedbackContent, String mobilePhone,
                                                     String customerName);

    /**
     * 获取设备对应的模式和功能列表
     *
     * @param wgDeviceId
     * @return
     */
    public abstract DevicePatternFunctionListReply getDevicePatternFunctionList(String wgDeviceId);

    /**
     * 获取设备绑定的用户列表
     *
     * @param wgDeviceId
     * @param wifiId     否 string 通信模块Id 注：兼容性考虑1.0版本可以不传递该参数，2.0版本的应用必须传递该参数
     * @return
     */
    public abstract BindedCustomerListReply getBindedCustomerList(String wgDeviceId, String wifiId);

    /**
     * 获取指定时间段内的设备状态
     *
     * @param deviceId      是 string 设备ID
     * @param startPosition 是 number 起始位置
     * @param endPosition   是 number 结束位置
     * @return
     */
    public abstract HistoryStatusReply getHistoryStatusByPosition(String deviceId,
                                                                  long startPosition, long endPosition);

    /**
     * 批量获取指定设备的原始状态
     *
     * @param deviceList 要查询设备对应的wifiid和设备id的列表，格式如下：
     *                   [{“wifiId”:””,”deviceId”:””},{“wifiId”:””,”deviceId”:””},……]
     *                   </br>
     */
    public abstract DeviceStatusReply getDeviceStatus(String deviceList);

    /**
     * 客户与设备绑定关系屏蔽与取消屏蔽
     *
     * @param wifiId     是 string 通信模块Id
     * @param wgDeviceId 是 string 设备Id
     * @param shieldFlag 是 string 状态：1：取消屏蔽2：屏蔽
     * @return
     */
    public abstract BaseInfo setBindShieldOnOff(String wifiId, String wgDeviceId,
                                                String shieldFlag);

    /**
     * 从家庭中删除指定的设备
     *
     * @param homeId 是 long 家庭Id
     * @param wifiId 是 string 通信模块Id
     * @return
     */
    public abstract BaseInfo delDeviceFromHome(long homeId, String wifiId);

    /**
     * 保存家庭的信息
     *
     * @param HashMap<String, String> params homeId 否 number 家庭ID 新建：该参数为空
     *                        更新：该参数为要更新家庭的ID homeName 是 string 家庭的名称 homeAddress 否 string
     *                        家庭住址 desc 否 string 描述信息 areaName 否 string 地区名称
     *                        格式：省市区之间用空格分隔；若为直辖市，市区之间用空格分隔。示例：山东省 青岛市 崂山区北京市 朝阳区
     * @return
     */
    public abstract SaveHomeInfoReply saveHomeInfo(HashMap<String, String> params);

    /**
     * 查询家庭信息
     *
     * @param homeId 是 long 家庭ID
     * @return
     */
    public abstract HomeInfoReply getHomeInfo(long homeId);

    /**
     * 获取客户所属家庭的列表
     *
     * @return
     */
    public abstract HomeListReply getHomeList();

    /**
     * 获取邀请用户加入家庭的二维码sessionkey
     *
     * @param homeId 是 number 家庭ID
     * @return
     */
    public abstract HomeQrCodeSessionKeyReply abstractgetHomeQrCodeSessionKey(long homeId);

    /**
     * 用户扫码二维码加入家庭中
     *
     * @param homeName   是 string 家庭的名称
     * @param sessionkey 是 string 标识二维码有效期的key
     * @return
     */
    public abstract BaseInfo joinHomeByQrCode(String homeName, String sessionkey);

    /**
     * 获取指定家庭下面的所有成员
     *
     * @param homeId 是 number 家庭ID
     * @return
     */
    public abstract HomeMemberListReply getHomeMemberList(long homeId);

    /**
     * 将指定用户从家庭成员中移除
     *
     * @param homeId      是 number 家庭ID
     * @param delMemberId 是 string 要删除成员的ID
     * @return
     */
    public abstract BaseInfo deleteHomeMember(long homeId, String delMemberId);

    /**
     * 家庭管理员权限转移
     *
     * @param homeId     是 number 家庭ID
     * @param toMemberId 是 string 转移目标成员的ID
     * @return
     */
    public abstract BaseInfo moveHomePermission(long homeId, String toMemberId);

    /**
     * 家庭成员退出家庭
     *
     * @param homeId 是 number 家庭ID
     * @return
     */
    public abstract BaseInfo quitFromHome(long homeId);

    /**
     * 删除家庭
     *
     * @param homeId 是 number 家庭ID
     * @return
     */
    public abstract BaseInfo deleteHome(long homeId);

    /**
     * 申请加入家庭
     *
     * @param homeId 是 number 家庭ID
     * @return
     */
    public abstract BaseInfo saveJoinHomeApply(long homeId);

    /**
     * 获取加入家庭申请列表
     *
     * @param homeId 是 number 家庭ID
     */
    public abstract JoinHomeApplyListReply getJoinHomeApplyList(long homeId);

    /**
     * @param applyId 是 number 申请ID
     * @param result  是 number 申请处理结果1：同意2：拒绝
     * @return
     */
    public abstract BaseInfo saveJoinHomeApplyResult(long applyId, int result);

    /**
     * 获取指定家庭下面的所有设备
     *
     * @param homeId 是 number 家庭ID
     * @return
     */
    public abstract HomeDeviceListReply getHomeDeviceList(long homeId);

    /**
     * 获取用户在指定家庭下面所绑定的设备
     *
     * @param homeId 是 number 家庭ID
     * @return
     */
    public abstract BindedHomeDeviceListReply getBindedHomeDeviceList(long homeId);

    /**
     * 获取历史设备列表
     *
     * @return
     */
    public abstract HistoryDeviceListReply getHistoryDeviceList();

    /**
     * @param wifiId     是 string 通信模块Id
     * @param wgDeviceId 是 string 设备Id
     * @param homeId     是 number 家庭Id
     * @return
     */
    public abstract BaseInfo addHistoryDeviceToHome(String wifiId, String wgDeviceId, long homeId);

    /**
     * 删除历史设备
     *
     * @param wifiId     是 string 通信模块Id</br>
     * @param wgDeviceId 是 string 设备Id</br>
     * @return
     */
    public abstract BaseInfo deleteHistoryDevice(String wifiId, String wgDeviceId);

    /**
     * 检测APP模板升级版本
     *
     * @param wgDeviceId     是 string 设备唯一标识
     * @param terminaltype   是 int 终端类型：手机APP：1 电视：2 微信：3
     * @param currentversion 否 string 当前版本
     * @return
     */
    public abstract CheckAppTemplateVersionReply checkAppTemplateVersion(String wgDeviceId,
                                                                         int terminaltype, String currentversion);

    /**
     * 检测设备解析库升级版本
     *
     * @param wgDeviceId     是 string 设备唯一标识
     * @param currentversion 否 string 当前版本
     * @param wifiId         否 String Wifi模块ID一拖多设备必须传该参数
     * @return
     */
    public abstract CheckDeviceLibVersionReply checkDeviceLibVersion(String wgDeviceId,
                                                                     String currentversion, String wifiId);

    /**
     * 上报升级结果
     *
     * @param wgDeviceId 是 string 设备唯一标识
     * @param targettype 是 int 升级对象类别：解析库：1模板：2
     * @param orgversion 是 string 原版本
     * @param newversion 是 string 新版本
     * @param result     Int 升级结果：成功：1包下载失败：6 包校验失败：7升级失败：9
     * @return
     */
    public abstract BaseInfo reportUpgradeResult(String wgDeviceId, int targettype,
                                                 String orgversion,
                                                 String newversion, int result);

    /**
     * 获取设备的状态及参数
     *
     * @param wifiId   是 string 通信模块ID
     * @param deviceId 是 string 空调条码
     * @return
     */
    public abstract DeviceStatusMetaReply getDeviceStatusMeta(String devicetype);

    /**
     * 获取设备扩展属性
     *
     * @param deviceId 是 string 设备ID
     * @param wifiId   否 string 通信模块ID备注：智能网关等一拖多设备必须传该参数
     * @return
     */
    public abstract DeviceExternalInfoReply getDeviceExternalInfo(String deviceId, String wifiId);

    /**
     * 设置默认语言
     *
     * @param defaultLanguageId 是 number 语言，默认为0 0中文 1英语 2 法语 3韩语 4俄语 5日语 6西班牙语
     *                          7德语 8 繁体中文 9 阿拉伯语 10 波斯语 11 泰语 12 意大利语 13 荷兰语
     * @return
     */
    public abstract BaseInfo setDefaultLanguage(int defaultLanguageId);

    /**
     * 获取默认语言
     *
     * @return
     */
    public abstract DefaultLanguageReply getDefaultLanguage();

    /**
     * 获取系统支持的设备类别列表
     *
     * @param appType 是 int 应用类型：1：手机APP2：电视APP3：微信
     * @return
     */
    public abstract DeviceTypeListReply getDeviceTypeList(int appType, int currentVersion);

    /**
     * 手动刷新设备状态
     *
     * @param deviceId 是 string 设备ID
     * @param wifiId   是 string 通信模块ID
     * @return
     */
    public abstract BaseInfo refreshDeviceStatus(String deviceId, String wifiId);

    /**
     * 远程执行逻辑指令
     *
     * @param deviceId 是 string 设备ID
     * @param wifiId   是 string 通信模块ID
     * @param cmdList  是 json 指令列表,list<cmd> 转成json传入
     * @return
     */
    public abstract BaseInfo uploadRemoteLogicCmd(String deviceId, String wifiId, String cmdList, int version);

    /**
     * 批量获取设备的逻辑状态列表
     *
     * @param deviceList
     * @return 是 json
     * 要查询设备对应的wifiid和设备id的列表，格式如下：[{“wifiId”:””,”deviceId”:””},{“wifiId
     * ”:””,”deviceId”:””},……]
     */
    public abstract DeviceLogicStatusListReply getDeviceLogicStatusList(String deviceList);

    /**
     * @param homeId 是 number 家庭Id
     * @param preDay 否 String 默认为0表示今天，1表示昨天，2表示前天，以此类推；取值范围0-9。
     *               具体时间范围可配合timezone参数取不同时区的时间轴
     * @return
     */
    public abstract DeviceCmdTimeLineReply getDeviceCmdTimeLine(long homeId, String preDay);

    /**
     * 3.2.2.57 获取设备的指令及参数（2.1新版）
     *
     * @param devicetype 是 string 设备类型00d 空调00e 洗衣机00f 冰箱010 智能家居011
     *                   吸油烟机012燃气灶013 中央空调
     * @return
     */
    public abstract CmdMetaReply getCmdMeta(String devicetype);

    /**
     * 3.2.2.58 创建或更新场景模式信息
     *
     * @param HashMap<String, String> map
     * @return
     */
    public abstract UpdateSceneReply updateScene(HashMap<String, String> map);

    /**
     * 3.2.2.60 获取场景模式的列表
     *
     * @param homeId是 number 家庭ID
     *                multiCondition: 是否支持多条件场景
     *                1：支持
     *                0：不支持
     *                默认不支持
     * @return
     */
    public abstract SceneListReply getSceneList(long homeId, int multiCondition);

    /**
     * 3.2.2.59 获取场景模式的列表
     *
     * @param 无
     * @return
     */
    public abstract SimpleSceneListReply getSimpleSceneTemplateList();

    /**
     * 3.2.2.60 删除场景模式
     *
     * @param sceneId 是 number 场景ID
     * @return
     */
    public abstract BaseInfo deleteScene(int sceneId);

    /**
     * 3.2.2.61 开启场景模式
     *
     * @param sceneId 是 number 场景ID
     * @return
     */
    public abstract BaseInfo openSceneSwitch(int sceneId);

    /**
     * 3.2.2.62 关闭场景模式
     *
     * @param sceneId 是 number 场景ID
     * @return
     */
    public abstract BaseInfo closeSceneorSwitch(int sceneId);

    /**
     * 3.2.2.63 条件触发手工场景模式设置
     *
     * @param homeId             是 number 家庭ID
     * @param sceneConditionId   否 number 场景条件ID，新创建是为0
     * @param sceneId            是 number 对应手工场景的场景ID
     * @param sceneTrigCondition 是 json字符串，list<condition> 场景触发条件转化成json
     * @return
     */
    public abstract BaseInfo setManulSceneCondition(long homeId, int sceneConditionId, int sceneId,
                                                    String sceneTrigCondition);

    /**
     * 3.2.2.64 获取条件触发手工场景模式的列表
     *
     * @param homeId是 number 家庭ID
     * @return
     */
    public abstract ConditionManulSceneListReply getConditionManulSceneList(long homeId);

    /**
     * 3.2.2.65 上传人机交互消息
     *
     * @param content      是 string 消息内容，值为json格式的string，格式为formatId对应的格式
     * @param msgOwnerType 是 int 消息属主类型：1：用户消息2：设备消息
     * @param wifiId       否 string 通信模块id，msgOwnerType=2时必须传
     * @param deviceId     否 string 设备id，msgOwnerType=2时必须传
     * @return
     */
    public abstract BaseInfo uploadMessage(String content, int msgOwnerType, String wifiId,
                                           String deviceId);

    /**
     * 3.2.2.66 获取用户所属房间列表
     *
     * @param homeId 是 number 家庭ID
     * @return
     */
    public abstract RoomListReply getRoomList(long homeId);

    /**
     * 3.2.2.67 获取指定房间下的所有设备列表
     *
     * @param roomId是 number 房间ID
     * @param homeId是 number 房间所属的家庭ID
     * @return
     */
    public abstract RoomDeviceListReply getRoomDeviceList(int roomId, long homeId);

    /**
     * 3.2.2.68 保存房间的信息
     *
     * @param roomId         否 number 房间ID 新建：该参数为空 更新：该参数为要更新房间的ID
     * @param roomName       是 string 房间的名称
     * @param roomDesc       否 string 描述信息
     * @param homeId         是 number 房间所属的家庭ID
     * @param floorId        否 long 房间所属的楼层ID（不传该参数为默认楼层一层）
     * @param roomImgFlag     否 string 房间图片标记
     * @return
     */
    public abstract SaveRoomInfoRely saveRoomInfo(int roomId, String roomName, String roomDesc,
                                                  long homeId, long floorId, String roomImgFlag);

    /**
     * 3.2.2.69 删除房间
     *
     * @param roomId 是 number 房间ID
     * @param homeId 是 number 房间所属的家庭ID
     * @return
     */
    public abstract BaseInfo deleteRoom(int roomId, long homeId);

    /**
     * 3.2.2.70 将设备加入房间
     *
     * @param wifiId     是 string 通信模块Id
     * @param wgDeviceId 是 string 设备Id
     * @param roomId     是 number 房间Id
     * @param homeId     是 number 房间所属的家庭ID
     * @return
     */
    public abstract BaseInfo addDeviceToRoom(String wifiId, String wgDeviceId, int roomId,
                                             long homeId);

    /**
     * 3.2.2.71 从房间中删除指定的设备
     *
     * @param roomId     是 number 房间Id
     * @param homeId     是 number 房间所属的家庭ID
     * @param wifiId     是 string 通信模块Id
     * @param wgDeviceId 是 string 设备Id
     * @return
     */
    public abstract BaseInfo delDeviceFromRoom(int roomId, long homeId, String wifiId,
                                               String wgDeviceId);

    /**
     * 3.2.2.72 获取指定家庭中最近执行的手动场景
     *
     * @param homeId 是 number 家庭ID
     * @param count是 number 获取最近执行场景的数量，最大100。
     * @return
     */
    public abstract LatestExeManulSceneReply getLatestExeManulScene(long homeId, int count);

    /**
     * 3.2.2.73 获取指定家庭中最近执行的设备
     *
     * @param homeId
     * @param count
     * @return
     */
    public abstract LatestExeDeviceReply getLatestExeDevice(long homeId, int count);

    /**
     * 3.2.2.74 保存楼层的信息
     *
     * @param map
     * @return
     */
    public abstract SaveFloorInfoReply saveFloorInfo(HashMap<String, String> map);

    /**
     * 3.2.2.75 删除楼层
     *
     * @param map
     * @return
     */
    public abstract BaseInfo deleteFloor(long floorId);

    /**
     * 3.2.2.76 获取用户某一家庭下的楼层列表
     *
     * @param homeId
     * @return
     */
    public abstract FloorListReply getFloorList(long homeId);

    /**
     * 3.2.2.77 获取用户某一楼层下的房间列表
     *
     * @param floorId
     * @return
     */
    public abstract FloorRoomListReply getFloorRoomList(long floorId);

    /**
     * 3.2.2.78 保存设备扩展属性信息
     *
     * @param map
     * @return
     */
    public abstract BaseInfo saveDeviceExternalInfo(HashMap<String, String> map);

    /*
     * 删除设备扩展属性信息
     */
    public abstract BaseInfo deleteDeviceExternalInfo(String deviceId, String wifiId, String extInfoKey, int extInfoId);

    /**
     * 3.2.2.79 获取场景模式预置图标列表
     *
     * @return
     */
    public abstract ScenePresetIconListReply getScenePresetIconList();

    /**
     * 3.2.2.80 获取用户某一家庭下的所有楼层和房间列表
     *
     * @param homeId
     * @return
     */
    public abstract AllFloorRoomListReply getAllFloorRoomList(long homeId);

    /**
     * 3.2.2.82 更新场景定时执行周期
     *
     * @param map
     * @return
     */
    public abstract BaseInfo updateSceneExeCycle(HashMap<String, String> map);

    /*
     * 3.2.2.84	批量获取通信模块状态
     */
    public abstract WifiStatusListReply getWifiListStatus(String wifiIdList);

    /*
     * 3.2.2.85	获取网关所有子设备的指令及参数（2.2新版）
     */
    public abstract GwCmdMetaReply getGwCmdMeta();
    /*
     * 获取网关所有子设备的状态及参数（2.2新版）
     */

    public abstract GwStatusMetaReply getGwStatusMeta();

    /*-----------------------增加存储/获取类云端的数据及解析本地数据的接口-----------------------------*/

    /**
     * 获取系统支持的设备类别列表
     *
     * @param appType 是 int 应用类型：1：手机APP2：电视APP3：微信
     * @return
     */
    public abstract DeviceTypeListReply getDeviceTypeListLocalParser(String jsonStr);

    /**
     * 获取客户所属家庭的列表
     *
     * @return
     */
    public abstract HomeListReply getHomeListLocalParser(String jsonStr);

    /**
     * 获取用户某一家庭下的所有楼层和房间列表
     *
     * @param homeId
     * @return
     */
    public abstract AllFloorRoomListReply getAllFloorRoomListLocalParser(String jsonStr);

    /**
     * 获取指定家庭下面的所有设备
     *
     * @param homeId 是 number 家庭ID
     * @return
     */
    public abstract HomeDeviceListReply getHomeDeviceListLocalParser(String jsonStr);

    /*
     * 获取网关所有子设备的指令及参数（2.2新版）
     */
    public abstract GwCmdMetaReply getGwCmdMetaLocalParser(String jsonStr);

    /*
     * 获取网关所有子设备的状态及参数（2.2新版）
     */
    public abstract GwStatusMetaReply getGwStatusMetaLocalParser(String jsonStr);

    /**
     * 获取设备的状态及参数
     *
     * @param wifiId   是 string 通信模块ID
     * @param deviceId 是 string 空调条码
     * @return
     */
    public abstract DeviceStatusMetaReply getDeviceStatusMetaLocalParser(String jsonStr);

    /**
     * 获取设备的指令及参数（2.1新版）
     *
     * @param devicetype 是 string 设备类型00d 空调00e 洗衣机00f 冰箱010 智能家居011
     *                   吸油烟机012燃气灶013 中央空调
     * @return
     */
    public abstract CmdMetaReply getCmdMetaLocalParser(String jsonStr);

    /*远程控制指令安全上报
     *上报控制指令到云端用来远程控制设备。（增加验签、认证功能）
     *
     */

    public abstract SecurityRemoteCmdReply uploadSecurityRemoteCmd(String wifiId, String wgDeviceId,
                                                                   String functionCode,
                                                                   String ctlCmd, int cmdVersion);

    /**
     * 场景手动执行接口，支持手动、条件、定时等场景类型的手动执行
     *
     * @param homeId   是 long 家庭ID
     * @param sceneId 是 long 场景ID
     * @return
     */
    public abstract BaseInfo manualExeScene(long homeId, long sceneId);

    /**
     * 常用场景添加接口
     *
     * @param homeId   是 long 家庭ID
     * @param sceneId 是 long 场景ID
     * @return
     */
    public abstract BaseInfo addCommonScene(long homeId, long sceneId);

    /**
     *  删除常用场景接口
     *
     * @param homeId   是 long 家庭ID
     * @param sceneId 是 long 场景ID
     * @return
     */
    public abstract BaseInfo deleteCommonScene(long homeId, long sceneId);

    /**
     *  全量保存常用场景接口,云端采用全量删除后重新写入的方法，按照传入参数sceneIds列表的顺序重新生成排序值保存到数据库中。
     *
     * @param homeId   是 long 家庭ID
     * @param sceneIds 是 string 场景ID列表，需传入全量的常用场景id列表，用逗号","分隔。云端按照id列表的顺序进行保存。
     * @return
     */
    public abstract BaseInfo saveAllCommonScenes(long homeId, String sceneIds);

    /**
     *  保存关联场景接口,传入主场景id，以及关联的场景id列表（逗号分隔），进行保存。允许批量添加，如果列表是空则代表全部删除。
     *
     * @param srcSceneId   是 long 主场景ID
     * @param trgSceneIds 是 string 关联场景ID列表，用逗号","分隔。如果列表是空则代表全部删除
     * @return
     */
    public abstract BaseInfo setTrgScenes(long srcSceneId, String trgSceneIds);

    /**
     *  保存关联场景接口,传入主场景id，以及关联的场景id列表（逗号分隔），进行保存。允许批量添加，如果列表是空则代表全部删除。
     *
     * @param srcSceneId   是 long 主场景ID
     * @param trgSceneIds 是 long 关联场景ID
     * @return
     */
    public abstract BaseInfo deleteTrgScene (long srcSceneId, long trgSceneId);
}
