
package com.hisense.smarthome.sdk.util;

public class Constants {
    /**
     * 会话
     * 
     * @deprecated 请使用{@link Params#TOKEN}代替
     */
    @Deprecated
    public static final String TOKEN = Params.TOKEN;

    public static final String ENCODE = "UTF-8";

    /**
     * API版本信息
     * 
     * @deprecated 请使用{@link Params#APIVERSION}代替
     */
    @Deprecated
    public static final String OPENAPIVERSIONTOKEN = Params.APIVERSION;

    /**
     * API版本信息
     * 
     * @deprecated 请使用{@link Params#APIVERSION}代替
     */
    @Deprecated
    public static final String OPENAPIVERSION = Params.APIVERSION;

    /**
     * 请求Server端时action的参数名称
     * 
     * @deprecated 请使用{@link Params#ACTION}代替
     */
    @Deprecated
    public static final String ACTION = Params.ACTION;
    /**
     * dns解析域名
     */
    public static final String DNS_DOMAINNAME = "lbgs.hismarttv.com";
    /**
     * 默认的域名(Http方式)
     */
    public static final String DEFAULT_DOMAINNAME = "api.hismarttv.com";//219.147.31.20";
    /**
     * 手机应用的默认的域名(Http方式)
     */
    public static final String DEFAULT_PHONE_DOMAINNAME = "app.phone.hismarttv.com";

    /**
     * 微信电视默认域名(Http方式)
     */
    public static final String DEFAULT_WECHATTV_DOMAINNAME = "public.wxtv.hismarttv.com";
    /**
     * 轮播电视默认域名(Http方式)
     */
    public static final String DEFAULT_LIVEAPI_DOMAINNAME = "api.live.hismarttv.com";
    /**
     * 轮播电视日志上报默认域名(Http方式)
     */
    public static final String DEFAULT_CHANNEL_LIVEAPI_DOMAINNAME = "channel.live.hismarttv.com";

    /**
     * 积分服务默认域名（hhtps方式）
     */
    public static final String DEFAULT_SCORE_DOMAINNAME = "api.score.hismarttv.com";
    /**
     * 默认的域名(Https方式)api.hismarttv.com
     */
    public static final String DEFAULT_HTTPSDOMAINNAME = "api.hismarttv.com";

    /**
     * 二维码登陆默认polling域名（http方式）
     */
    public static final String DEFAULT_POLLIG_DOMAINNAME = "api.external.hismarttv.com";

    /**
     * NPS业务域名
     */
    public static final String DEFAULT_NPS_DOMAINNAME = "nps.phone.hismarttv.com";
    /**
     * chcatv直播电视业务域名
     */
    public static final String DEFAULT_CHCATV_DOMAINNAME = "vidaa.chcatv.hismarttv.com";
    /**
     * 新launcher服务默认域名(Http方式)
     */
    public static final String DEFAULT_NEW_LAUMCHER_DOMAINNAME = "api.launcher.hismarttv.com";
    /**
     * vidaa助手检测网速默认域名
     */
    public static final String DEFAULT_API_VIDAAASST_DOMAINNAME = "api-vidaaasst.hismarttv.com";
    /**
     * 微助手默认域名
     */
    public static final String DEFAULT_VODAPP_DOMAINNAME = "portal.vodapp.hismarttv.com";
    /**
     * 潘多拉默认域名
     */
    public static final String DEFAULT_PANDORA_DOMAINNAME = "pandora.hismarttv.com";
    // /**
    // *
    // * 语言
    // * @deprecated 请使用{@link Params#LANGUAGE_ID}代替
    // */
    // @Deprecated
    // public static final String LANGUAGE_ID = Params.LANGUAGE_ID;

    /**
     * 时区
     * 
     * @deprecated 请使用{@link Params#TM_ZONE}代替
     */
    @Deprecated
    public static final String TIMEZONE = Params.TM_ZONE;
    /**
     * 默认时区-东八区
     */
    public static final String DEFAULTTIMEZONE = "8";

    /**
     * 访问模式
     * 
     * @deprecated 请使用{@link Params#TVMODE}代替
     */
    @Deprecated
    public static final String TVMODE = Params.TVMODE;
    /**
     * 默认的访问模式
     */
    public static final String DEFAULTTVMODE = "Hicloud";

    /**
     * 访问模式_Hicloud
     */
    public static final String TVMODE_HICLOUD = "Hicloud";
    /**
     * 访问模式_HiPad
     */
    public static final String TVMODE_HIPAD = "HiPad";

    /**
     * 机型
     * 
     * @deprecated 请使用{@link Params#MACHINETYPE}代替
     */
    public static final String MACHINETYPE = Params.MACHINETYPE;

    /**
     * 操作系统版本
     * 
     * @deprecated 请使用{@link Params#OSVERSION}代替
     */
    @Deprecated
    public static final String OSVERSION = Params.OSVERSION;
    /**
     * 默认的操作系统版本
     */
    public static final String DEFAULTOSVERSION = "2.3";

    /**
     * 操作系统类型
     * 
     * @deprecated 请使用{@link Params#OSTYPE}代替
     */
    @Deprecated
    public static final String OSTYPE = Params.OSTYPE;
    /**
     * 默认的操作系统类型(Android)
     */
    public static final String DEFAULTOSTYPE = "1";

    /**
     * 操作系统类型_HiTVOS
     */
    public static final String OSTYPE_HITVOS = "0";
    /**
     * 操作系统类型_Android
     */
    public static final String OSTYPE_ANDROID = "1";

    /**
     * 校验签名结果的tag号
     */
    public static final String SIGNATURESERVER = "signatureServer";

    // 签名结果

    /**
     * 签名结果-成功
     */
    public static final String SIGNATUREVERIFY_SUCCESS = "0";
    /**
     * 签名结果-失败
     */
    public static final String SIGNATUREVERIFY_FAIL = "1";

    /*************************************************************************/
    /**
     * 中文
     */
    public static final String LANGUAGE_CHINESE = "0";

    /**
     * 英文
     */
    public static final String LANGUAGE_ENGLISH = "1";

    /**
     * 法语
     */
    public static final String LANGUAGE_FRENCH = "2";

    /**
     * 韩语
     */
    public static final String LANGUAGE_KOREAN = "3";

    /**
     * 俄语
     */
    public static final String LANGUAGE_RUSSIAN = "4";

    /**
     * 日语
     */
    public static final String LANGUAGE_JAPANESE = "5";

    /**
     * 西班牙语
     */
    public static final String LANGUAGE_SPANISH = "6";

    /**
     * 德语
     */
    public static final String LANGUAGE_GERMAN = "7";

    /**
     * 语言类型-繁体中文
     */
    public static final String LANGUAGE_CHINESE_T = "8";

    /**
     * 语言类型-阿拉伯语
     */
    public static final String LANGUAGE_ARABIC = "9";

    /**
     * 语言类型-波斯语
     */
    public static final String LANGUAGE_PERSIAN = "10";

    /**
     * 语言类型-泰语
     */
    public static final String LANGUAGE_THAI = "11";

    /**
     * 语言类型-意大利语
     */
    public static final String LANGUAGE_ITALIAN = "12";

    /**
     * 语言类型-荷兰语
     */
    public static final String LANGUAGE_DUTCH = "13";

    /*************************** 搜索服务 ***************************************/
    /**
     * 搜索关键字
     * 
     * @deprecated 请使用{@link Params#SEARCHWORD}代替
     */
    @Deprecated
    public static final String SEARCHWORD = Params.SEARCHWORD;

    /**
     * 各搜索字之间逻辑关系
     * 
     * @deprecated 请使用{@link Params#WORDSRELATION}代替
     */
    @Deprecated
    public static final String WORDSRELATION = Params.WORDSRELATION;

    /**
     * sp播放器
     * 
     * @deprecated 请使用{@link Params#SPPLAYER}代替
     */
    @Deprecated
    public static final String SPPLAYER = Params.SPPLAYER;

    /**
     * ss应用业务代码
     * 
     * @deprecated 请使用{@link Params#SERVICECODE}代替
     */
    @Deprecated
    public static final String SERVICECODE = Params.SERVICECODE;

    /**
     * 搜索服务域名前缀
     */
    public static final String SSPREFIX = "ss/spSearch/getProgrames";

    /**
     * 搜索服务的默认ACTION
     */
    public static final String SSACTION = "";
    /**
     * openApi接口用到的token
     * 
     * @deprecated 请使用{@link Params#ACCESSTOKEN}
     */
    @Deprecated
    public static final String SSTOKEN = Params.ACCESSTOKEN;

    /*************************** 电视汇 *****************************************/
    /**
     * 终端是否有搜台数据标志位
     * 
     * @deprecated 请使用{@link Params#FILTERFLAG}代替
     */
    @Deprecated
    public static final String FILTERFLAG = Params.FILTERFLAG;

    /**
     * 电视聚合服务接口中使用的，传递电视聚合服务业务代码参数
     * 
     * @deprecated 请使用{@link Params#SERVICE_CODE}代替
     */
    @Deprecated
    public static final String SERVICE_CODE = Params.SERVICE_CODE;

    /**
     * 分类ID
     * 
     * @deprecated 请使用{@link Params#CATEGORY_ID}代替
     */
    @Deprecated
    public static final String CATEGORYID = Params.CATEGORY_ID;

    /**
     * 偏移量
     * 
     * @deprecated 请使用{@link Params#OFFSET}代替
     */
    @Deprecated
    public static final String OFFSET = Params.OFFSET;

    /**
     * 请求个数
     * 
     * @deprecated 请使用{@link Params#EXPECTED_COUNT}代替
     */
    @Deprecated
    public static final String EXPECTEDCOUNT = Params.EXPECTED_COUNT;
    /**
     * tag类型
     * 
     * @deprecated 请使用{@link Params#TAGTYPE}代替
     */
    @Deprecated
    public static final String TAGTYPE = Params.TAGTYPE;

    /**
     * 分类类型
     * 
     * @deprecated 请使用{@link Params#CATEGORYTYPE}代替
     */
    @Deprecated
    public static final String CATEGORYTYPE = Params.CATEGORYTYPE;

    /**
     * 搜索关键字
     * 
     * @deprecated 请使用{@link Params#SNS_KEYWORD}代替
     */
    @Deprecated
    public static final String KEYWOED = Params.SNS_KEYWORD;

    /**
     * 搜索方式
     * 
     * @deprecated 请使用{@link Params#SEARCHMODE}代替
     */
    @Deprecated
    public static final String SEARCHMODE = Params.SEARCHMODE;

    /**
     * 期望获得的数据总数
     * 
     * @deprecated 请使用{@link Params#MAXNUM}代替
     */
    @Deprecated
    public static final String MAXNUM = Params.MAXNUM;

    /**
     * 专题ID
     * 
     * @deprecated 请使用{@link Params#ALBUMID}代替
     */
    @Deprecated
    public static final String TOPICID = Params.ALBUMID;

    /**
     * 节目代码
     * 
     * @deprecated 请使用{@link Params#PROGRAMCODE}代替
     */
    @Deprecated
    public static final String PROGRAMCODE = Params.PROGRAMCODE;

    /**
     * 节目开始时间
     * 
     * @deprecated 请使用{@link Params#STARTTIME}代替
     */
    @Deprecated
    public static final String STARTTIME = Params.STARTTIME;

    /**
     * 频道ID
     * 
     * @deprecated 请使用{@link Params#CHANNELID}代替
     */
    @Deprecated
    public static final String CHANNELID = Params.CHANNELID;

    /**
     * 节目ID
     * 
     * @deprecated 请使用{@link Params#PROGRAM_ID}代替
     */
    @Deprecated
    public static final String PROGRAMGID = Params.PROGRAM_ID;

    /**
     * 电视汇URL前缀
     */
    public static final String CHCAPREFIX = "cgi-bin/epg_index.fcgi";

    /**
     * 5.0电视汇URL前缀
     */
    public static final String CHCAPREFIX_V50 = "cgi-bin/chcaportal_index.fcgi";

    /**
     * 节目类别、演职员信息的组合
     * 
     * @deprecated 请使用{@link Params#RELATEINFO}代替
     */
    @Deprecated
    public static final String RELATEINFO = Params.RELATEINFO;
    /******************* SmartTV 5.0 新增 ******************************/
    /**
     * 图片信息参数，比如电视聚合服务中请求Server端时使用
     * 
     * @deprecated 请使用{@link Params#PICINFO}代替
     */
    @Deprecated
    public static final String PICINFO = Params.PICINFO;
    /**
     * 对象类型,用于获取不同类型的数据时使用
     * 
     * @deprecated 请使用{@link Params#OBJTYPE}代替
     */
    @Deprecated
    public static final String OBJTYPE = Params.OBJTYPE;
    /**
     * 预约信息(电视聚合服务使用)
     * 
     * @deprecated 请使用{@link Params#ORDEREDINFO}代替
     */
    @Deprecated
    public static final String ORDEREDINFO = Params.ORDEREDINFO;

    /**
     * Action:EPG Actions
     */
    public static final String EPG_GETSERVICELIST = "CHCA_GetServiceList";
    public static final String EPG_GETCHANNELLISTINFO = "GetChannelListInfo";
    public static final String EPG_REPORTCHANNELINFO = "ReportChannelInfo";
    public static final String EPG_GETCATEGORYLIST = "GetCategoryList";
    public static final String EPG_GETPROGRAMLIST = "GetProgramList";
    public static final String EPG_GETPROGRAMDETAILINFO = "GetProgDetailInfo";
    public static final String EPG_GETALLTHIRDCHANNELINFO = "GetAllThirdChannelInfo";
    public static final String EPG_GETSPECIFIEDPROGLIST = "GetSpecifiedProgList";
    public static final String EPG_GETCUSTOMIZEDPROGLIST = "CHCA_ProgInfoList";
    public static final String EPG_GETCATEGORYPROGLIST = "CHCA_GetCateProgList";
    public static final String EPG_CHCAGETPROGRAMLIST = "CHCA_GetProgramList";
    public static final String ACTION_CHCA_GETAREAINFO = "CHCA_GetAreaInfo";
    public static final String ACTION_CHCA_GETBCINFO = "CHCA_GetBCInfo";
    public static final String ACTION_CHCA_GETBCIRCODEINFO = "CHCA_GetBCIRCodeInfo";
    public static final String ACTION_CHCA_GETBCCHANNELLISTINFO = "CHCA_GetBCChannelListInfo";
    public static final String ACTION_CHCA_GETSRVPROVIDERINFO = "CHCA_GetSRVProviderInfo";
    public static final String ACTION_CHCA_GETORDEREDPROGINFO = "CHCA_GetOrderedProgInfo";
    public static final String ACTION_CHCA_GETPROBLEMINFO = "CHCA_GetProblemInfo";
    public static final String ACTION_CHCA_GETLOCALCHANNELINFO = "CHCA_GetLocalChannelInfo";
    public static final String ACTION_CHCA_SUBSCRIBE = "CHCA_SubscribeInfo";
    public static final String ACTION_CHCA_GETHOTWORDLIST = "CHCA_GetHotWordList";
    public static final String ACTION_CHCA_GETPROGLISTFORDURATION = "CHCA_ProgListForDuration";
    public static final String ACTION_CHCA_GETMULTCONDPROGLIST = "CHCA_MultCondProgList";
    public static final String ACTION_CHCA_GETMATCHEDCHANNELS = "CHCA_GetMatchedChannels";
    public static final String ACTION_CHCA_GETSTATICPROGLIST = "CHCA_GetStaticProgList";
    /**
     * EPG List Key
     */
    public static final String EPG_SEARCHLIST = "SearchList";
    public static final String EPG_CATEGORYCONTENTLIST = "CategoryContentList";
    public static final String EPG_PROMOTIONLIST = "PromotionList";
    public static final String EPG_TOPICCONTENTLIST = "TopicContentList";
    public static final String EPG_DIFFCHANNELPROGRAMLIST = "DiffChannelProgList";
    public static final String EPG_RELATEDCHANNELPROGRAMLSIT = "RelatedChannelProgList";
    public static final String EPG_CHANNELPROGRAMLIST = "ChannelProgList";
    public static final String EPG_CHANNELPREVIEWPROGLIST = "ChannelPreviewProgList";
    public static final String EPG_SPECIFIEDPROGLIST = "SpecifiedProgList";
    public static final String EPG_UPDATEDSPECIFIEDPROGLIST = "UpdatedSpecifiedProgList";

    /**
     * EPG上报的频道信息
     * 
     * @deprecated 请使用{@link Params#CHANNELIDINFO}代替
     */
    @Deprecated
    public static final String EPG_CHANNELINFO = Params.CHANNELIDINFO;

    /**
     * 区分列表类型的key
     * 
     * @deprecated 请使用{@link Params#LISTKEY}代替
     */
    @Deprecated
    public static final String LISTKEY = Params.LISTKEY;

    /**
     * 指定频道id列表
     * 
     * @deprecated 请使用{@link Params#CHANNELIDLIST}代替
     */
    @Deprecated
    public static final String CHANNELIDLIST = Params.CHANNELIDLIST;

    /**
     * 结束时间
     * 
     * @deprecated 请使用{@link Params#ENDTIME}代替
     */
    @Deprecated
    public static final String ENDTIME = Params.ENDTIME;

    /**
     * 最新更新时间
     * 
     * @deprecated 请使用{@link Params#UPDATEDDATE}代替
     */
    @Deprecated
    public static final String UPDATEDDATE = Params.UPDATEDDATE;

    /****************** 核心云服务 ****************************************/
    /**
     * 核心云服务URL前缀
     */
    public static final String LAUNCHERPREFIX = "cgi-bin/epg_index.fcgi";

    /**
     * Action:获取应用推荐或混排内容列表
     */
    public static final String LAUNCHER_GETMIXEDCATCONTENTLIST = "GetMixedCatContentList";

    /**
     * Action:获取混排中的图片列表
     */
    public static final String LAUNCHER_GETPICTURELIST = "GetPcitureList";

    /**
     * Action:获取第三方节目提供商列表
     */
    public static final String LAUNCHER_GETPROGPROVIDERLIST = "GetProgProviderList";

    /**
     * Action:获取推荐或混排分类列表
     */
    public static final String LAUNCHER_GETMIXEDCATEGORYLIST = "GetMixedCategoryList";
    /**
     * Action:获取专题内容明细
     */
    public static final String LAUNCHER_GETTOPICDETAIL = "GetTopicDetail";
    /**
     * Action:获取配置文件下载地址
     */
    public static final String LAUNCHER_GETCONFIGFILEADDR = "GetConfigFileAddr";

    /**
     * Action:IP地址查询
     */
    public static final String LAUNCHER_GETADDRESSVIAIP = "GetAddressViaIp";

    /**
     * Action:根据名称首字母搜索视频
     */
    public static final String LAUNCHER_SEARCHVIAKEYLETTER = "SearchViaKeyLetter";

    /**
     * Action:获取热门推荐列表
     */
    public static final String LAUNCHER_HOTRECOMMEND = "HotRecommend";

    /**
     * Action:获取个人推荐列表
     */
    public static final String LAUNCHER_PERSONALRECOMMEND = "PersonalRecommend";

    /**
     * Action:获取教育分类内容列表
     */
    public static final String LAUNCHER_GETEDUCATCONTENTLIST = "EDUENV_GetCatContentList";

    /**
     * Action:获取混排专题内容列表
     */
    public static final String LAUNCHER_GETMIXEDALBUMCONTENTLIST = "EDUENV_GetMixedTopicContent";

    /**
     * Action:获取指定内容提供商分类列表
     */
    public static final String LAUNCHER_GETCATEGORYLISTFORSPECIFIEDCP = "HOWDINI_GetIcspList";

    /**
     * Action:获取提供商分类内容列表
     */
    public static final String LAUNCHER_GETCONTENTLISTFORCATEGORY = "HOWDINI_GetIcspContentList";

    /**
     * Action:获取目标视频详情
     */
    public static final String LAUNCHER_GETVIDEOINFOBYID = "HOWDINI_GetObjectDetail";

    /**
     * Action:获取 TerraTV 分类列表
     */

    public static final String LAUNCHER_GETTERRATVCATEGORYLIST = "TERRATV_GetCategoryList";

    /**
     * Action:获取 TerraTV 分类内容列表
     */

    public static final String LAUNCHER_GETTERRATVCATEGORYCONTENTLIST = "TERRATV_GetCategoryContentList";

    /**
     * 
     */
    public static final String LAUNCHER_GETTERRATVOBJECTPLAYURL = "TERRATV_GetObjectPlayUrl";
    /**
     * 
     */
    public static final String GETTERRATVCATCONTENT = "GetTerraTVCatContent";

    /**
     * 图片包入口ID
     * 
     * @deprecated 请使用{@link Params#OBJECT_ID}代替
     */
    @Deprecated
    public static final String OBJECT_ID = Params.OBJECT_ID;

    /**
     * 图片类型
     * 
     * @deprecated 请使用{@link Params#OBJECTTYPE}代替
     */
    @Deprecated
    public static final String OBJECT_TYPE = Params.OBJECTTYPE;

    /************************** 广告引擎 **********************************/
    /**
     * 广告位代码
     */
    public static final String ADINFO = "adinfo";

    /**
     * 
     */
    public static final String ADEXTINFO = "adextinfo";

    /**
     * 邮编
     * 
     * @deprecated 请使用{@link Params#ZIPCODE}代替
     */
    @Deprecated
    public static final String ZIPCODE = Params.ZIPCODE;

    /**
     * 国家编码
     * 
     * @deprecated 请使用{@link Params#COUNTRYCODE}代替
     */
    @Deprecated
    public static final String COUNTRYCODE = Params.COUNTRYCODE;

    public static final String ADLINKID = "adlinkid";

    public static final String ADCODE = "adcode";

    public static final String ADPOSITIONOWNER = "adpositionowner";

    /**
     * 机型的广告位置信息
     */
    public static final String ADPOSITIONINFO = "adpositioninfo";

    public static final String ADENGINEPREFIX = "adapi";

    /**
     * AD Actions
     */
    public static final String AD_GETADPOLICY = "getadpolicy";
    public static final String AD_GETADLISTINFO = "getadlistinfo";

    public static final String AD_GETADSTRATEGY = "getadstrategy";
    public static final String AD_GETADLINKINFO = "getadlinkinfo";

    public static final String AD_GETRESOURCEINFO = "getresourceinfo";
    public static final String AD_GETADCONTENT = "getadcontent";

    /*************************************** 升级 **************************/

    /**
     * 整机升级, 当前版本
     * 
     * @deprecated 请使用{@link Params#CURRENTVERSION}代替
     */
    @Deprecated
    public static final String CURRENT_VERSION = Params.CURRENTVERSION;

    /**
     * 整机升级, 设备扩展版本信息
     * 
     * @deprecated 请使用{@link Params#MACHINE_EX_TYPE}代替
     */
    @Deprecated
    public static final String MACHINE_EX_TYPE = Params.MACHINE_EX_TYPE;
    /**
     * 整机升级, 国家码
     * 
     * @deprecated 请使用 {@link Params#STATE_CODE}
     */
    @Deprecated
    public static final String STATE_CODE = Params.STATE_CODE;
    /**
     * 整机升级, 邮编码
     * 
     * @deprecated 请使用 {@link Params#POST_CODE}
     */
    @Deprecated
    public static final String POST_CODE = Params.POST_CODE;
    /**
     * 应用升级, 包名
     * 
     * @deprecated 请使用 {@link Params#APPPACKAGENAME}
     */
    @Deprecated
    public static final String PKGNAME = Params.APPPACKAGENAME;

    /***************************** 用户信息管理 ****************************************/
    /**
     * openApi接口用到的token
     * 
     * @deprecated 请使用{@link Params#ACCESSTOKEN}
     */
    @Deprecated
    public static final String ACCESS_TOKEN = Params.ACCESSTOKEN;

    /**
     * 应用认证的appKey
     * 
     * @deprecated 请使用 {@link Params#APPKEY}
     */
    @Deprecated
    public static final String APPKEY = Params.APPKEY;
    /**
     * 应用认证的appSecret
     * 
     * @deprecated 请使用 {@link Params#APPSECRET}
     */
    @Deprecated
    public static final String APPSECRET = Params.APPSECRET;
    /**
     * 用户登录/注册的appCode(应用认证返回)
     * 
     * @deprecated 请使用 {@link Params#APPCODE}
     */
    @Deprecated
    public static final String APPCODE = Params.APPCODE;
    /**
     * 登录用户名
     * 
     * @deprecated 请使用 {@link Params#LOGINNAME}
     */
    @Deprecated
    public static final String USERNAME = Params.LOGINNAME;
    /**
     * 登录密码
     * 
     * @deprecated 请使用 {@link Params#PASSWORD}
     */
    @Deprecated
    public static final String PASSWORD = Params.PASSWORD;
    /**
     * 用户电子邮箱
     * 
     * @deprecated 请使用 {@link Params#EMAIL}
     */
    @Deprecated
    public static final String EMAIL = Params.EMAIL;
    /**
     * 设备id, 注册和登录时需要
     * 
     * @deprecated 请使用 {@link Params#DEVICEID}
     */
    @Deprecated
    public static final String DEVICEID = Params.DEVICEID;
    /**
     * 是否注册时验证 0-是 1-否
     * 
     * @deprecated 请使用 {@link Params#VALIDATE_ISREGISTER}
     */
    @Deprecated
    public static final String VALIDATE_ISREGISTER = Params.VALIDATE_ISREGISTER;
    /**
     * 用户注册类型参数
     * 
     * @deprecated 请使用 {@link Params#REGISTER_TYPE}
     */
    @Deprecated
    public static final String REGISTER_TYPE = Params.REGISTER_TYPE;
    /**
     * 用户注册类型, 用户名(默认类型)
     */
    public static final String REGISTER_TYPE_NAME = "0";
    /**
     * 用户注册类型, 手机号
     */
    public static final String REGISTER_TYPE_MOBILE = "1";
    /**
     * 用户注册类型, EMAIL
     */
    public static final String REGISTER_TYPE_EMAIL = "2";
    /**
     * 用户注册, 手机号
     * 
     * @deprecated 请使用{@link Params#MOBILEPHONE}
     */
    @Deprecated
    public static final String MOBILEPHONE = Params.MOBILEPHONE;
    /**
     * 绑定, 第三方平台id
     * 
     * @deprecated 请使用{@link Params#THIRD_PLATFORMID}
     */
    @Deprecated
    public static final String THIRD_PLATFORMID = Params.THIRD_PLATFORMID;
    /**
     * 绑定, 第三方授权token
     * 
     * @deprecated 请使用{@link Params#THIRD_TOKEN}
     */
    @Deprecated
    public static final String THIRD_TOKEN = Params.THIRD_TOKEN;
    /**
     * 绑定, 第三方token有效期
     * 
     * @deprecated 请使用{@link Params#THIRD_EXPIREIN}
     */
    @Deprecated
    public static final String THIRD_EXPIREIN = Params.THIRD_EXPIREIN;
    /**
     * 绑定, 第三方userid
     * 
     * @deprecated 请使用{@link Params#THIRD_UID}
     */
    @Deprecated
    public static final String THIRD_UID = Params.THIRD_UID;
    /**
     * 绑定, 第三方授权扩展信息
     * 
     * @deprecated 请使用{@link Params#THIRD_EXINFO}
     */
    @Deprecated
    public static final String THIRD_EXINFO = Params.THIRD_EXINFO;
    /**
     * 直接登录, 绑定的客户id
     * 
     * @deprecated 请使用{@link Params#CUSTOMERID}
     */
    @Deprecated
    public static final String CUSTOMERID = Params.CUSTOMERID;
    /**
     * 获取第三方授权, 回调地址
     * 
     * @deprecated 请使用{@link Params#THIRD_CALLBACK}
     */
    @Deprecated
    public static final String THIRD_CALLBACK = Params.THIRD_CALLBACK;
    /**
     * 用户昵称
     * 
     * @deprecated 请使用{@link Params#NICKNAME}
     */
    @Deprecated
    public static final String NICKNAME = Params.NICKNAME;
    /**
     * 修改密码原密码
     * 
     * @deprecated 请使用{@link Params#OLDPWD}
     */
    @Deprecated
    public static final String OLD_PASSWORD = Params.OLDPWD;
    /**
     * 修改密码类型
     * 
     * @deprecated 请使用{@link Params#TYPE}
     */
    @Deprecated
    public static final String MODIFY_TYPE = Params.TYPE;
    /**
     * 修改密码新密码
     * 
     * @deprecated 请使用{@link Params#NEWPWD}
     */
    @Deprecated
    public static final String NEW_PASSWORD = Params.NEWPWD;

    /**
     * 用户信息的姓名
     * 
     * @deprecated 请使用{@link Params#NAME}
     */
    @Deprecated
    public static final String CUSTOMER_NAME = Params.NAME;
    /**
     * 性别
     * 
     * @deprecated 请使用{@link Params#SEX}
     */
    @Deprecated
    public static final String CUSTOMER_GENDER = Params.SEX;
    /**
     * 性别-男
     */
    public static final int CUSTOMER_GENDER_MALE = 1;
    /**
     * 性别-女
     */
    public static final int CUSTOMER_GENDER_FEMALE = 0;
    /**
     * 性别-其他
     */
    public static final int CUSTOMER_GENDER_OTHER = 2;
    /**
     * 住址
     * 
     * @deprecated {@link Params#ADDRESS}
     */
    @Deprecated
    public static final String CUSTOMER_ADDRESS = Params.ADDRESS;
    /**
     * 邮政编码
     * 
     * @deprecated {@link Params#CUSTOMER_ZIPCODE}
     */
    @Deprecated
    public static final String CUSTOMER_ZIPCODE = Params.CUSTOMER_ZIPCODE;
    /**
     * 证件类型
     * 
     * @deprecated {@link Params#CUSTOMER_IDTYPE}
     */
    @Deprecated
    public static final String CUSTOMER_IDTYPE = Params.CUSTOMER_IDTYPE;
    /**
     * 证件类型-身份证
     */

    public static final int IDTYPE_IDCARD = 1;
    /**
     * 证件类型-护照
     */
    public static final int IDTYPE_PASSPORT = 2;
    /**
     * 证件类型-驾驶证
     */
    public static final int IDTYPE_DRIVING_LIC = 3;
    /**
     * 证件类型-其他
     */
    public static final int IDTYPE_OTHERS = 4;
    /**
     * 证件号码
     * 
     * @deprecated 请使用{@link Params#IDNUMBER}
     */
    @Deprecated
    public static final String IDNUMBER = Params.IDNUMBER;
    /**
     * 固定电话
     * 
     * @deprecated 请使用{@link Params#PHONE}
     */
    @Deprecated
    public static final String CUSTOMER_PHONE = Params.PHONE;
    /**
     * 移动电话
     * 
     * @deprecated 请使用{@link Params#MOBILEPHONE}
     */
    @Deprecated
    public static final String CUSTOMER_MOBILEPHONE = Params.MOBILEPHONE;
    /**
     * 生日
     * 
     * @deprecated 请使用{@link Params#BIRTHDAY}
     */
    @Deprecated
    public static final String CUSTOMER_BIRTHDAY = Params.BIRTHDAY;
    /**
     * 用户头像id
     * 
     * @deprecated 请使用{@link Params#CUSTOMER_PICID}
     */
    @Deprecated
    public static final String CUSTOMER_PICID = Params.CUSTOMER_PICID;
    /**
     * 是否需要输入订购密码
     * 
     * @deprecated 请使用{@link Params#CUSTOMER_NEEDSUBPIN}
     */
    @Deprecated
    public static final String CUSTOMER_NEEDSUBPIN = Params.CUSTOMER_NEEDSUBPIN;
    /**
     * 需要输入订购密码
     */
    public static final int NEEDSUBPIN_YES = 0;
    /**
     * 不需要订购密码
     */
    public static final int NEEDSUBPIN_NO = 1;
    /**
     * 通过手机号找回密码或验证手机号, 验证码
     * 
     * @deprecated 请使用{@link Params#CHECKCODE}
     */
    @Deprecated
    public static final String CHECKCODE = Params.CHECKCODE;
    /**
     * 充值卡号
     * 
     * @deprecated 请使用{@link Params#CARDNUMBER}
     */
    @Deprecated
    public static final String CARDNUMBER = Params.CARDNUMBER;
    /**
     * 充值卡密
     * 
     * @deprecated 请使用{@link Params#CARDPASSWORD}
     */
    @Deprecated
    public static final String CARDPASSWORD = Params.CARDPASSWORD;

    /**
     * 金额
     * 
     * @deprecated 请使用{@link Params#AMOUNT}
     */
    @Deprecated
    public static final String AMOUNT = Params.AMOUNT;

    /**
     * 银行卡语音充值电话号码
     * 
     * @deprecated 请使用{@link Params#PHONENUMBER}
     */
    @Deprecated
    public static final String PHONE_NUMBER = Params.PHONENUMBER;

    /**
     * 银行卡语音充值--证件类型
     * 
     * @deprecated 请使用{@link Params#IDCARDTYPE}
     */
    @Deprecated
    public static final String IDCARD_TYPE = Params.IDCARDTYPE;
    /**
     * 起始日期
     * 
     * @deprecated 请使用{@link Params#STARTDATE}
     */
    @Deprecated
    public static final String STARTDATE = Params.STARTDATE;
    /**
     * 结束日期
     * 
     * @deprecated 请使用{@link Params#ENDDATE}
     */
    @Deprecated
    public static final String ENDDATE = Params.ENDDATE;
    /**
     * 充值类型
     * 
     * @deprecated 请使用{@link Params#RECHARGETYPE}
     */
    @Deprecated
    public static final String CHARGE_TYPE = Params.RECHARGETYPE;
    /**
     * 充值类型-全部
     */
    public static final int CHARGE_TYPE_ALL = 0;
    /**
     * 充值类型-手机充值卡
     */
    public static final int CHARGE_TYPE_PHONECARD = 1;
    /**
     * 充值类型-银行卡
     */
    public static final int CHARGE_TYPE_BANKCARD = 2;
    /**
     * 充值类型-支付
     */
    public static final int CHARGE_TYPE_PAY = 3;
    /**
     * 充值类型-信用卡
     */
    public static final int CHARGE_TYPE_CREDITCARD = 4;
    /**
     * 充值类型-现金
     */
    public static final int CHARGE_TYPE_CASH = 5;
    /**
     * 充值类型-转账
     */
    public static final int CHARGE_TYPE_TRANSFER = 6;
    /**
     * 充值类型-其他
     */
    public static final int CHARGE_TYPE_OTHERS = 7;
    /**
     * 充值类型-充值卡
     */
    public static final int CHARGE_TYPE_RECHARGECARD = 8;

    /**
     * 分页页面大小
     * 
     * @deprecated 请使用{@link Params#PAGESIZE}
     */
    @Deprecated
    public static final String PAGESIZE = Params.PAGESIZE;
    /**
     * 修改订购密码的修改类型
     * 
     * @deprecated 请使用{@link Params#BLOGID}
     */
    @Deprecated
    public static final String ORDER_PASSWORD_MODIFY_TYPE = Params.TYPE;
    /**
     * 账单周期编码
     * 
     * @deprecated 请使用{@link Params#BILL_CYCLE_CODE}
     */
    @Deprecated
    public static final String BILL_CYCLE_CODE = Params.BILL_CYCLE_CODE;
    /**
     * 账单详情类型
     * 
     * @deprecated 请使用{@link Params#BILL_DETAIL_TYPE}
     */
    @Deprecated
    public static final String BILL_DETAIL_TYPE = Params.BILL_DETAIL_TYPE;
    /**
     * 账单详情类型-应用
     */
    public static final int BILL_DETAIL_TYPE_APP = 116;
    /**
     * 第三方分享账号id
     * 
     * @deprecated 请使用{@link Params#BLOGID}
     */
    @Deprecated
    public static final String BLOGID = Params.BLOGID;
    /**
     * 获取第三方认证uri时的回调地址
     * 
     * @deprecated 请使用{@link Params#CALLBACKPATH}
     */
    @Deprecated
    public static final String CALLBACKPATH = Params.CALLBACKPATH;
    /**
     * 绑定第三方账号时的验证码
     * 
     * @deprecated 请使用{@link Params#VERIFYCODE}
     */
    @Deprecated
    public static final String VERIFYCODE = Params.VERIFYCODE;
    /**
     * 绑定第三方账号时requestToken
     * 
     * @deprecated 请使用{@link Params#REQUEST_TOKEN}
     */
    @Deprecated
    public static final String REQUEST_TOKEN = Params.REQUEST_TOKEN;
    /**
     * 绑定第三方账号时requestSecret
     * 
     * @deprecated 请使用{@link Params#REQUEST_SECRET}
     */
    @Deprecated
    public static final String REQUEST_SECRET = Params.REQUEST_SECRET;

    /**
     * 小额支付商户编号
     * 
     * @deprecated 请使用{@link Params#MERCHANTCODE}
     */
    @Deprecated
    public static final String MERCHANTCODE = Params.MERCHANTCODE;
    /**
     * 小额支付支付id
     * 
     * @deprecated 请使用{@link Params#PAYID}
     */
    @Deprecated
    public static final String PAYID = Params.PAYID;
    /**
     * 应用id
     * 
     * @deprecated 请使用{@link Params#APP_ID}
     */
    @Deprecated
    public static final String APPID = Params.APP_ID;
    /**
     * 支付密码
     * 
     * @deprecated 请使用{@link Params#PAYPASSWORD}
     */
    @Deprecated
    public static final String PAYPASSWORD = Params.PAYPASSWORD;
    /**
     * 支付备注
     * 
     * @deprecated 请使用{@link Params#REMARKS}
     */
    @Deprecated
    public static final String REMARKS = Params.REMARKS;

    /*************************************** 手机号绑定 ********************************************/

    /**
     * 手机号码
     * 
     * @deprecated 请使用{@link Params#BIND_PHONENUMBER}
     */
    @Deprecated
    public static final String BIND_PHONENUMBER = Params.BIND_PHONENUMBER;
    /**
     * 绑定类型
     * 
     * @deprecated 请使用{@link Params#BIND_TYPE}
     */
    @Deprecated
    public static final String BIND_TYPE = Params.BIND_TYPE;
    /**
     * 绑定类型-新绑定
     */
    public static final String BIND_TYPE_BIND = "1";
    /**
     * 绑定类型-修改
     */
    public static final String BIND_TYPE_CHANGE = "2";
    /**
     * 昵称
     * 
     * @deprecated 请使用{@link Params#NICKNAME}
     */
    @Deprecated
    public static final String BIND_NICKNAME = Params.NICKNAME;
    /**
     * 验证码
     * 
     * @deprecated 请使用{@link Params#CHECKCODE}
     */
    @Deprecated
    public static final String BIND_CAPTCHA = Params.CHECKCODE;
    /**
     * 手机号绑定修改-旧手机号码
     * 
     * @deprecated 请使用{@link Params#BIND_OLD_PHONENUM}
     */
    @Deprecated
    public static final String BIND_OLD_PHONENUM = Params.BIND_OLD_PHONENUM;
    /**
     * 手机号码绑定修改-新手机号码
     * 
     * @deprecated 请使用{@link Params#BIND_NEW_PHONENUM}
     */
    @Deprecated
    public static final String BIND_NEW_PHONENUM = Params.BIND_NEW_PHONENUM;

    /************************************
     * 充值(支付宝和银视通)
     ********************************************/
    /**
     * 银视通/支付宝充值充值金额
     * 
     * @deprecated 请使用{@link Params#RECHARGE_AMOUNT}
     */
    @Deprecated
    public static final String RECHARGE_AMOUNT = Params.RECHARGE_AMOUNT;
    /**
     * 支付宝充值cashierCode
     * 
     * @deprecated 请使用{@link Params#CASHIER_CODE}
     */
    @Deprecated
    public static final String CASHIER_CODE = Params.CASHIER_CODE;
    /**
     * 支付宝充值终端返回地址
     * 
     * @deprecated 请使用{@link Params#RETURN_URL}
     */
    @Deprecated
    public static final String RETURN_URL = Params.RETURN_URL;

    /********************************************
     * 支付(支付宝和银视通)
     *************************************/
    /**
     * 支付金额
     * 
     * @deprecated 请使用{@link Params#AMOUNT}
     */
    @Deprecated
    public static final String PAY_AMOUNT = Params.AMOUNT;
    /**
     * 支付渠道
     * 
     * @deprecated 请使用{@link Params#PAY_CHANNEL}
     */
    @Deprecated
    public static final String PAY_CHANNEL = Params.PAY_CHANNEL;

    /**
     * 第三方订单号
     * 
     * @deprecated 请使用{@link Params#THIRD_ORDERID}
     */
    @Deprecated
    public static final String THIRD_ORDERID = Params.THIRD_ORDERID;
    /**
     * 订单时间
     * 
     * @deprecated 请使用{@link Params#ORDER_TIME}
     */
    @Deprecated
    public static final String ORDER_TIME = Params.ORDER_TIME;
    /**
     * 商品名称
     * 
     * @deprecated 请使用{@link Params#PRODUCT_NAME}
     */
    @Deprecated
    public static final String PRODUCT_NAME = Params.PRODUCT_NAME;

    /**
     * 通知地址
     * 
     * @deprecated 请使用{@link Params#NOTIFY_URL}
     */
    @Deprecated
    public static final String NOTIFY_URL = Params.NOTIFY_URL;
    /**
     * 签名方式
     * 
     * @deprecated 请使用{@link Params#SIGN_TYPE}
     */
    @Deprecated
    public static final String SIGN_TYPE = Params.SIGN_TYPE;
    /**
     * 签名信息
     * 
     * @deprecated 请使用{@link Params#SIGN_INFO}
     */
    @Deprecated
    public static final String SIGN_INFO = Params.SIGN_INFO;
    /************************************ AppStore ******************************************************/

    /**
     * 日志Tag
     */
    // public static final String LOGTAG_METHODINVOKE = "sdk_method";

    /**
     * 应用商店_海信分类类型
     */
    public static final String APPSTORE_CATEGORYTYPE = "7";

    /**
     * 应用商店_合作专区分类类型
     */
    public static final String PARTER_CATEGORYTYPE = "53";

    /**
     * 数据访问结果-成功
     */
    public static final String RESULT_SUCCESS = "0";
    /**
     * 数据访问结果-失败
     */
    public static final String RESULT_FAIL = "1";
    /*****************************************************************/
    /**
     * 付费类型-全部
     */
    public static final String COSTTYPE_ALL = "0"; // 全部
    /**
     * 付费类型-付费
     */
    public static final String COSTTYPE_PAID = "1"; // 付费
    /**
     * 付费类型-免费
     */
    public static final String COSTYPE_FREE = "2"; // 免费
    /*****************************************************************/

    // 排序方式

    /**
     * 排序方式-按更新时间顺序
     */
    public static final String SORTTYPE_UPDATETIME_ASC = "0"; // 按更新时间顺序
    /**
     * 排序方式-按更新时间倒序
     */
    public static final String SORTTYPE_UPDATETIME_DESC = "1"; // 按更新时间倒序
    /**
     * 排序方式-按分值顺序
     */
    public static final String SORTTYPE_SCORE_ASC = "2"; // 按分值顺序
    /**
     * 排序方式-按分值倒序
     */
    public static final String SORTTYPE_SCORE_DESC = "3"; // 按分值倒序
    /**
     * 排序方式-按下载次数顺序
     */
    public static final String SORTTYPE_DOWNLOADTIMES_ASC = "4"; // 按下载次数顺序
    /**
     * 排序方式-按下载次数倒序
     */
    public static final String SORTTYPE_DOWNLOADTIMES_DESC = "5"; // 按下载次数倒序
    /**
     * 排序方式-按价格顺序序
     */
    public static final String SORTTYPE_PRICE_ASC = "6"; // 按价格顺序
    /**
     * 排序方式-按价格倒序
     */
    public static final String SORTTYPE_PRICE_DESC = "7"; // 按价格倒序

    /*****************************************************************/

    // 排行方式
    /**
     * 排行方式-全部
     */
    public static final String RANKTYPE_ALL = "0"; // 全部
    /**
     * 排行方式-当天
     */
    public static final String RANKTYPE_CURRENTDAY = "1"; // 当天
    /**
     * 排行方式-周
     */
    public static final String RANKTYPE_WEEK = "2"; // 周
    /**
     * 排行方式-当月
     */
    public static final String RANKTYPE_CURRENTMONTH = "3"; // 当月
    /**
     * 排行方式-月度
     */
    public static final String RANKTYPE_MONTH = "4"; // 月度
    /**
     * 排行方式-当年
     */
    public static final String RANKTYPE_CURRENTYEAR = "5"; // 当年
    /*****************************************************************/

    // 编排方式
    /**
     * 编排方式-手工添加
     */
    public static final String SOURCETYPE_MANUAL = "0"; // 手工添加
    /**
     * 编排方式-分类聚合
     */
    public static final String SOURCETYPE_GENRE = "1"; // 分类聚合

    /*****************************************************************/
    // 推荐类型
    /**
     * 推荐类型-应用
     */
    public static final String RECOMMENDEDTYPE_APP = "30"; // 应用
    /**
     * 推荐类型-专题
     */
    public static final String RECOMMENDEDTYPE_ALBUM = "33"; // 专题

    /*****************************************************************/
    // 升级方式

    /**
     * 升级方式-非强制
     */
    public static final String UPGRADETYPE_OPTIONAL = "0"; // 非强制
    /**
     * 升级方式-强制
     */
    public static final String UPGRADETYPE_OBLIGATORY = "1"; // 强制

    /*****************************************************************/
    // 分类类型

    /**
     * 分类类型-分类
     */
    public static final String CATEGORYTYPE_CATEGORY = "1";// 分类
    /**
     * 分类类型-专题
     */
    public static final String CATEGORYTYPE_ALBUM = "2";// 专题

    /*****************************************************************/
    // 是否需要订购密码

    /**
     * 是否需要订购密码-不需要
     */
    public static final String PASSWORDFLAG_NO = "0";// 不需要
    /**
     * 是否需要订购密码-不需要
     */
    public static final String PASSWORDFLAG_YES = "2";// 需要

    /*****************************************************************/

    /**
     * 默认的解密公钥
     */
    public static final String DEFAULT_KEY = "ecf8427e5d933e61";

    /**
     * appstore portal 默认域名
     */
    public static final String APPSTORE_PORTAL1 = "smarttv.hisense.com.appstore";

    /**
     * appstore portal 访问前缀
     */
    public static final String APPSTORE_PREFIX = "cgi-bin/appstore_index.fcgi?action=";

    public static final String APPSTORE_SERVICELISTACTION = "AppStoreGetServiceList";
    public static final String APPSTORE_SEARCHRESULTACTION = "AppSearchList";
    public static final String APPSTORE_RECOMMENDEDAPPSACTION = "AppPromotion";
    public static final String APPSTORE_APPRANKSACTION = "AppTopdown";
    public static final String APPSTORE_NEWARRIVALAPPSACTION = "AppNewArrival";
    public static final String APPSTORE_RELATEDAPPSACTION = "GetRelatedApp";
    public static final String APPSTORE_GRADEAPPACTION = "GradeLevel";
    public static final String APPSTORE_COMMENTAPPACTION = "Comments";
    public static final String APPSTORE_GETAPPCOMMENTSACTION = "FetchComments";
    public static final String APPSTORE_GETAPPCATEGORIESACTION = "AppCategory";
    public static final String APPSTORE_GETAPPCATEGORYCONTENTACTION = "AppCatContent";
    public static final String APPSTORE_GETAPPCATEGORYCONTENTAMOUNTACTION = "AppCatContentTotal";
    public static final String APPSTORE_GETAPPDETAILACTION = "AppInfo";
    public static final String APPSTORE_GETAPPLATESTVERSIONSACTION = "AppVersionInfo";
    public static final String APPSTORE_ORDERAPPACTION = "OrderApp";
    public static final String APPSTORE_CHECKPAYMENTPASSWORDACTION = "CheckPayMentPassWord";
    public static final String APPSTORE_GETALLGENRESACTION = "GetAllGenres";
    public static final String APPSTORE_GETPARTNERSACTION = "GetParter";
    public static final String APPSTORE_GETCUSTOMERGRADEACTION = "FetchGradeLevel";
    public static final String APPSTORE_GETAPPINFOBYPACKAGENAMEACTION = "GetAppPackageInfo";
    public static final String APPSTORE_GETRECOMMENDEDLISTACTION = "GetAppMixedPromotionList";
    public static final String APPSTORE_GETALBUMLISTACTION = "GetAppTopicList";
    public static final String APPSTORE_GETALBUMCONTENTACTION = "GetAppTopicContent";
    public static final String APPSTORE_GETHOTSEARCHKEYACTION = "GetHotSearchKey";
    public static final String APPSTORE_GETAPPPRESETACTION = "GetAppPreset";
    public static final String APPSTORE_GETAPPDLURLACTION = "GetAppDlURL";
    public static final String APPSTORE_GETAPPINFOFROMWEBACTION = "GetAppInfoFromWeb";
    public static final String APPSTORE_REPORTINSTALLRESULTACTION = "GetOperationOfAppFromWeb";
    public static final String APPSTORE_GETAPPDESCACTION = "GetAppDesc";
    public static final String APPSTORE_GETAPPUPGRADEINFOACTION = "GetUpgradeInfo";
    public static final String APPSTORE_GETAPPWHITELISTACTION = "GetAppWhiteList";
    public static final String APPSTORE_GETCOLUMNRECOMMENDEDLISTACTION = "GetAppPromotionByPos";
    public static final String APPSTORE_REPORTAPPACTION = "AppReport";
    public static final String APPSTORE_GETSTARTUPPICACTION = "GetStartupPic";
    public static final String APPSTORE_GETFAVORITEAPPSACTION = "GetPossibleFavor";
    public static final String APPSTORE_GETHOTRANKINGAPPSACTION = "GetAppRank";
    public static final String APPSTORE_GETNAVICATEGORIESACTION = "GetNaviCategoryList";
    public static final String APPSTORE_GETAPPSTORESETTINGS = "GetSysConfig";
    public static final String APPSTORE_REPORTDEVICEINFO = "ReportDeviceInfo";
    public static final String APPSTORE_GETDEVICESWITCH = "GetDeviceSwitch";

    /*****************************************************************/
    // 服务器访问协议

    /**
     * 服务器访问协议-Http
     */
    public static final String PROTOCAL_HTTP = "http://";// Http
    /**
     * 服务器访问协议-Https
     */
    public static final String PROTOCAL_HTTPS = "https://";// Https

    /*************************** PS ************************************/
    /**
     * 协议版本
     * 
     * @deprecated 请使用{@link Params#MSGVERSION}代替
     */
    @Deprecated
    public static final String MSGVERSION = Params.MSGVERSION;

    /**
     * 消息类型
     * 
     * @deprecated 请使用{@link Params#MSGTYPE}代替
     */
    @Deprecated
    public static final String MSGTYPE = Params.MSGTYPE;

    /**
     * 消息ID
     * 
     * @deprecated 请使用{@link Params#MSGID}代替
     */
    @Deprecated
    public static final String MSGID = Params.MSGID;

    /**
     * 终端类型
     */
    public static final String DEVICETYPE = "deviceType";

    /**
     * 机顶盒版本
     */
    public static final String STBVERSION = "stbVersion";

    /**
     * IP地址
     */
    public static final String CLIENTIP = "clientIp";

    /**
     * 子网掩码
     */
    public static final String NETMASK = "netMask";

    /**
     * 语言标识
     */
    public static final String LANGCAPABILITY = "langCapability";

    /**
     * 设备能力
     */
    public static final String DEVICECAPABILITY = "deviceCapability";

    /**
     * 网速
     */
    public static final String NETSPEED = "netSpeed";

    /**
     * 终端状态
     */
    public static final String DEVICESTATUS = "deviceStatus";

    /**
     * 操作状态
     */
    public static final String OPSTATUS = "opStatus";

    /**
     * 行为类型
     */
    public static final String BEHAVIORTYPE = "behaviorType";

    /**
     * 行为
     */
    public static final String BEHAVIOR = "behavior";

    /**
     * 上报的行为个数最大值
     */
    public static final String BEHAVIORNUM = "behaviorNum";

    /**
     * 上报app标识
     */
    public static final String REPORTFLAG = "reportFlag";

    /**
     * 上报时间
     */
    public static final String REPORTTIME = "reportTime";

    /**
     * 行为列表
     */
    public static final String BEHAVIORLIST = "behaviorList";

    /**
     * 上报的应用个数最大值
     */
    public static final String APPNUM = "appNum";

    /**
     * 应用列表
     */
    public static final String APPLIST = "appList";

    /**
     * 日志内容压缩标识
     */
    public static final String ZIPFLAG = "zipFlag";

    /**
     * 终端记录的log上报策略版本号
     */
    public static final String LOGVERSION = "logVersion";

    /**
     * 上报的日志数据的大小
     */
    public static final String LOGSIZE = "logSize";

    /**
     * 上报的日志数据的内容
     */
    public static final String LOGCONTENT = "logContent";

    /**
     * 日志、第三方平台等标识
     * 
     * @deprecated 请使用 {@link Params#OBJECTID}
     */
    @Deprecated
    public static final String OBJECTID = Params.OBJECTID;

    /**
     * 日志、第三方平台等类型
     * 
     * @deprecated 请使用 {@link Params#SNS_OBJECTTYPE}
     */
    @Deprecated
    public static final String OBJECTTYPE = Params.SNS_OBJECTTYPE;

    /**
     * 授权token，用以身份验证
     * 
     * @deprecated 请使用 {@link Params#ACCESSTOKEN}
     */
    @Deprecated
    public static final String PSLOG_TOKEN = Params.ACCESSTOKEN;

    /**
     * API版本号
     * 
     * @deprecated 请使用 {@link Params#APIVERSION}
     */
    @Deprecated
    public static final String PSLOG_VERSION = Params.APIVERSION;

    /**
     * 保留签名认证，由本次请求的所有参数计算的值
     * 
     * @deprecated 请使用 {@link Params#SIGN}
     */
    @Deprecated
    public static final String PSLOG_SIGN = Params.SIGN;

    /**
     * 保留，时间戳 单位：秒数
     * 
     * @deprecated 请使用 {@link Params#TIMESTAMP}
     */
    @Deprecated
    public static final String PSLOG_TIMES_STAMP = Params.TIMESTAMP;

    /**
     * 语言
     * 
     * @deprecated 请使用 {@link Params#LANGUAGEID}
     */
    @Deprecated
    public static final String PSLOG_LANGUAGE_ID = Params.LANGUAGEID;

    /**
     * 获取日志上报策略 ACTION
     */
    public static final String PSLOG_ACTION_STRATEGY = "log/get_strategy";

    /**
     * 获取应用日志上报策略 ACTION
     */
    public static final String PSLOG_ACTION_APPSTRATEGY = "log/get_strategy_all";

    /**
     * 终端日志上报 ACTION
     */
    public static final String PSLOG_ACTION_TERMINAL = "log/report_terminal";

    /**
     * 上报设备能力，进行ps注册 ACTION
     */
    public static final String PSLOG_ACTION_REGISTER = "ps/register";

    /**
     * 上报心跳，维持终端与服务器的连接状态 ACTION
     */
    public static final String PSLOG_ACTION_HEARTBEAT = "ps/report_heartbeat";

    /**
     * 用户行为上报(单条行为上报) ACTION
     */
    public static final String PSLOG_ACTION_BEHAVIOR = "ps/report_behavior";

    /**
     * 用户行为批量上报，最多支持上报30个用户行为 ACTION
     */
    public static final String PSLOG_ACTION_BATCH_BEHAVIOR = "ps/report_batch_behavior";

    /**
     * 应用信息上报。当终端认证通过后向PS发送应用全量上报消息。当应用变化时发送增量或减量消息 ACTION
     */
    public static final String PSLOG_ACTION_REPORT_APP = "ps/report_app";

    /********************************* SNS *****************************************/
    /**
     * 第三方平台评论内容
     * 
     * @deprecated 请使用{@link Params#WEIBOCONTENT}
     */
    @Deprecated
    public static final String WEIBOCONTENT = Params.WEIBOCONTENT;

    /**
     * 转发的第三方第三方平台平台的id
     * 
     * @deprecated 请使用{@link Params#THIRDBLOGIDS}
     */
    @Deprecated
    public static final String THIRDBLOGIDS = Params.THIRDBLOGIDS;

    /**
     * 文件名称
     * 
     * @deprecated 请使用 {@link Params#FILENAME}
     */

    @Deprecated
    public static final String FILENAME = Params.FILENAME;

    /**
     * 返回结果的页码
     * 
     * @deprecated 请使用 {@link Params#PAGE}
     */
    @Deprecated
    public static final String PAGE = Params.PAGE;

    /**
     * 每页个数
     * 
     * @deprecated 请使用 {@link Params#COUNT}
     */
    @Deprecated
    public static final String COUNT = Params.COUNT;

    /**
     * 最大个数
     * 
     * @deprecated 请使用 {@link Params#MAXID}
     */
    @Deprecated
    public static final String MAXID = Params.MAXID;

    /**
     * 第三方平台Id
     * 
     * @deprecated 请使用 {@link Params#SINCEID}
     */
    @Deprecated
    public static final String SINCEID = Params.SINCEID;

    /**
     * 第三方平台类型
     * 
     * @deprecated 请使用 {@link Params#TYPE}
     */
    @Deprecated
    public static final String WEIBOTYPE = Params.TYPE;

    /**
     * 当前所需显示的图片类型
     * 
     * @deprecated 请使用 {@link Params#PICTYPE}
     */
    @Deprecated
    public static final String PICTYPE = Params.PICTYPE;

    /**
     * 搜索类型
     * 
     * @deprecated 请使用 {@link Params#SOTYPE}
     */
    @Deprecated
    public static final String SOTYPE = Params.SOTYPE;

    /**
     * 搜索关键字
     * 
     * @deprecated 请使用 {@link Params#KEYWORD}
     */
    @Deprecated
    public static final String KEYWORD = Params.KEYWORD;

    /**
     * 第三方平台范围
     * 
     * @deprecated 请使用 {@link Params#RANGE}
     */
    @Deprecated
    public static final String RANGE = Params.RANGE;

    /**
     * 第三方平台Id列表
     * 
     * @deprecated 请使用 {@link Params#OBJECTINFO}
     */
    @Deprecated
    public static final String OBJECTINFO = Params.OBJECTINFO;

    /**
     * 要删除的收藏第三方平台Id列表
     * 
     * @deprecated 请使用 {@link Params#OBJECTIDS}
     */
    @Deprecated
    public static final String OBJECTIDS = Params.OBJECTIDS;

    /**
     * 用户头像标示
     * 
     * @deprecated 请使用 {@link Params#PROFILEID}
     */
    @Deprecated
    public static final String PROFILEID = Params.PROFILEID;

    /**
     * 评论类型
     * 
     * @deprecated 请使用 {@link Params#TYPE}
     */
    @Deprecated
    public static final String COMMENTTYPE = Params.TYPE;

    /**
     * 元数据
     * 
     * @deprecated 请使用 {@link Params#METADATA}
     */
    @Deprecated
    public static final String METADATA = Params.METADATA;

    /**
     * 第三方视频参数
     * 
     * @deprecated 请使用 {@link Params#THIRDVVALUE}
     */
    @Deprecated
    public static final String THIRDVVALUE = Params.THIRDVVALUE;

    /**
     * 第三方视频类型
     * 
     * @deprecated 请使用 {@link Params#THIRDVTYPE}
     */
    @Deprecated
    public static final String THIRDVTYPE = Params.THIRDVTYPE;

    /**
     * 视频海报url
     * 
     * @deprecated 请使用 {@link Params#PICURL}
     */
    @Deprecated
    public static final String PICURL = Params.PICURL;

    /**
     * 分享类型
     * 
     * @deprecated 请使用 {@link Params#SHARETYPE}
     */
    @Deprecated
    public static final String SHARETYPE = Params.SHARETYPE;

    /**
     * 应用的链接地址
     * 
     * @deprecated 请使用 {@link Params#APPURL}
     */
    @Deprecated
    public static final String APPURL = Params.APPURL;

    /**
     * 网页url
     * 
     * @deprecated 请使用 {@link Params#WEBURL}
     */
    @Deprecated
    public static final String WEBURL = Params.WEBURL;

    /**
     * 评论Id
     * 
     * @deprecated 请使用 {@link Params#COMMENTID}
     */
    @Deprecated
    public static final String COMMENTID = Params.COMMENTID;

    /**
     * SNS prefix
     */
    public static final String SNS_PREFIX = "api";

    /**
     * SNS appPakageName，应用的包名
     * 
     * @deprecated 请使用 {@link Params#APP_PKNAME}
     */
    @Deprecated
    public static final String SNS_APP_PKNAME = Params.APP_PKNAME;

    /**
     * SNS oauth_token
     * 
     * @deprecated 请使用 {@link Params#OAUTH_TOKEN}
     */
    @Deprecated
    public static final String SNS_OAUTH_TOKEN = Params.OAUTH_TOKEN;

    /**
     * SNS format 返回数据的格式（json或xml）
     * 
     * @deprecated 请使用{@link Params#FORMAT}
     */
    @Deprecated
    public static final String SNS_FORMAT = Params.FORMAT;

    /*********************** SNS Action *************************************/

    /**
     * 删除第三方平台 ACTION
     */
    public static final String SNS_ACTION_DELETE_WEIBO = "weibo/del";

    /**
     * 转发第三方平台 ACTION
     */
    public static final String SNS_ACTION_REPOST_BLOG = "weibo/repost";

    /**
     * 获取热门第三方平台列表 ACTION
     */
    public static final String SNS_ACTION_HOT_BLOGLIST = "weibo/hot";

    /**
     * 获取指定值后更新的第三方平台数量 ACTION
     */
    public static final String SNS_ACTION_SPE_BLOG_COUNT = "weibo/tmline_count";

    /**
     * 按照用户名或者标题搜索第三方平台 ACTION
     */
    public static final String SNS_ACTION_SEARCH_BLOG = "weibo/so";

    /**
     * 获取第三方平台列表 ACTION
     */
    public static final String SNS_ACTION_BLOG_LIST = "weibo/list";

    /**
     * 记录浏览次数到数据库 ACTION
     */
    public static final String SNS_ACTION_MARK_BLOG = "weibo/mark";

    /**
     * 添加第三方平台到收藏列表 ACTION
     */
    public static final String SNS_ACTION_COLLECT_BLOG = "fav/add";

    /**
     * 从收藏夹中删除收藏第三方平台 ACTION
     */
    public static final String SNS_ACTION_CANCEL_BLOG = "fav/del";

    /**
     * 批量删除收藏 ACTION
     */
    public static final String SNS_ACTION_BATCH_CANCEL_BLOG = "fav/del_batch";

    /**
     * 获取收藏列表 ACTION
     */
    public static final String SNS_ACTION_COLLECTION_BLOG = "fav/list";

    /**
     * 获取用户头像列表 ACTION
     */
    public static final String SNS_ACTION_PROFILE_LIST = "user/pro_pics";

    /**
     * 保存用户头像设置 ACTION
     */
    public static final String SNS_ACTION_SAVE_PROFILE = "user/pro_save";

    /**
     * 获取用户头像设置 ACTION
     */
    public static final String SNS_ACTION_PROFILE_CONFIG = "user/pro_conf";

    /**
     * 获取评论ACTION
     */
    public static final String SNS_ACTION_GET_COMMENT = "comment/default";

    /**
     * 分享视频ACTION
     */
    public static final String SNS_ACTION_SHARE_VIDEOLINK = "share/video";

    /**
     * 分享图片
     */
    public static final String SNS_ACTION_SHARE_PIC = "share/pic";

    /**
     * 分享应用
     */
    public static final String SNS_ACTION_SHARE_APP = "share/app";

    /**
     * 分享网页
     */
    public static final String SNS_ACTION_SHARE_WEB = "share/web";

    /**
     * 指定第三方平台“赞”在线视频的接口
     */
    public static final String SNS_ACTION_UP_VIDEO = "video/up";
    /**
     * 获取好友分享的视频的人气接口
     */
    public static final String SNS_ACTION_VIDEO_HITS = "video/hits";

    /**
     * 获取好友分享的视频的接口
     */
    public static final String SNS_ACTION_FRIENDS_VIDEO = "video/friendsShare_list";

    /**
     * 获取好友分享的视频的接口
     */
    public static final String SNS_ACTION_RECOMMEND_VIDEO = "video/recommend_list";

    /**
     * 转发第三方平台
     */
    public static final String SNS_ACTION_FORWARD_BLOG = "weibo/repost";

    /**
     * 获取指定评论
     */
    public static final String SNS_ACTION_GET_DESIGNCOMMENT = "comment/show";

    /**
     * 删除评论
     */
    public static final String SNS_ACTION_DEL_COMMENT = "comment/del";
    /*********************** Storage *************************************/
    /**
     * 文件媒体数据保存返回的文件路径
     * 
     * @deprecated 请使用{@link Params#FILEPATH}
     */
    @Deprecated
    public static final String FILEPATH = Params.FILEPATH;

    /**
     * 区分是拍照应用直接上传还是通过本地上传
     * 
     * @deprecated 请使用{@link Params#USEDTYPE}
     */
    @Deprecated
    public static final String USEDTYPE = Params.USEDTYPE;

    /**
     * 需要存放的目标文件夹Id
     * 
     * @deprecated 请使用{@link Params#CATID}
     */
    @Deprecated
    public static final String CATID = Params.CATID;

    /**
     * 文件夹类型
     * 
     * @deprecated 请使用{@link Params#CATTYPE}
     */
    @Deprecated
    public static final String CATTYPE = Params.CATTYPE;

    /**
     * 是否分享
     * 
     * @deprecated 请使用{@link Params#SHAREFLAG}
     */
    @Deprecated
    public static final String SHAREFLAG = Params.SHAREFLAG;

    /**
     * 是否强制删除
     * 
     * @deprecated 请使用{@link Params#FORCEFLAG}
     */
    @Deprecated
    public static final String FORCEFLAG = Params.FORCEFLAG;

    /**
     * 文件类型
     * 
     * @deprecated 请使用{@link Params#FILETYPE}
     */
    @Deprecated
    public static final String FILETYPE = Params.FILETYPE;

    /**
     * 下载url有效期
     * 
     * @deprecated 请使用{@link Params#PERIOD}
     */
    @Deprecated
    public static final String PERIOD = Params.PERIOD;

    /**
     * 目标文件夹id
     * 
     * @deprecated 请使用{@link Params#DESTCATID}
     */
    @Deprecated
    public static final String DESTCATID = Params.DESTCATID;

    /**
     * 获取上载服务器地址 ACTION
     */
    public static final String STORAGE_UPLOADER_IP = "sys/uploaderIp";

    /**
     * 保存文件元数据到本地数据库 ACTION
     */
    public static final String STORAGE_SAVE_UPLOAD_META = "file/upload_meta";

    /**
     * 根据文件objectId删除文件所涉及的所有媒体数据以及元数据 ACTION
     */
    public static final String STORAGE_DELETE_FILE = "file/del";

    /**
     * 修改文件的元数据信息 ACTION
     */
    public static final String STORAGE_UPDATE_FILE_META = "file/update";

    /**
     * 获取用户文件列表 ACTION
     */
    public static final String STORAGE_FILE_LIST = "dir/content";

    /**
     * 根据文件夹Id、文件名搜索文件列表 ACTION
     */
    public static final String STORAGE_SEARCH_FILE_LIST = "file/so";

    /**
     * 获取文件第三方存储的带签名的url ACTION
     */
    public static final String STORAGE_EFFECTIVE_URL = "file/dlurl";

    /**
     * 根据父文件夹获取文件和文件夹的总数 ACTION
     */
    public static final String STORAGE_FILE_COUNT = "dir/content_count";

    /**
     * 获取用户在其他终端共享的文件总数,开机提醒和共享提醒 ACTION
     */
    public static final String STORAGE_FILE_SHARED = "file/shared";

    /**
     * 获取未读的文件总数 ACTION
     */
    public static final String STORAGE_UNREAD_FILES = "file/unreads";

    /**
     * 文件复制 ACTION
     */
    public static final String STORAGE_COPY_FILE = "file/cp";

    /**
     * 文件移动 ACTION
     */
    public static final String STORAGE_MOVE_FILE = "file/mv";

    /**
     * 获取文件详情 ACTION
     */
    public static final String STORAGE_FILE_DETAIL = "file/info";

    /**
     * 创建文件夹 ACTION
     */
    public static final String STORAGE_MAKE_DIR = "dir/mkdir";

    /**
     * 文件夹元数据修改 ACTION
     */
    public static final String STORAGE_UPDATE_DIR = "dir/update";

    /**
     * 根据文件catId删除文件所涉及的所有媒体数据以及元数据 ACTION
     */
    public static final String STORAGE_DELETE_DIR = "dir/del";

    /**
     * 获取文件夹详情 ACTION
     */
    public static final String STORAGE_DIR_DETAIL = "dir/info";

    /**
     * 文件夹复制 ACTION
     */
    public static final String STORAGE_COPY_DIR = "dir/cp";

    /**
     * 文件夹移动 ACTION
     */
    public static final String STORAGE_MOVE_DIR = "dir/mv";

    /**
     * 获取当前用户总容量、可用容量、已用容量，存储的相片、音乐、视频、家庭留言板类型的存储容量情况 ACTION
     */
    public static final String STORAGE_DISK_USAGE = "disk/du";

    /**
     * 获取垃圾箱内容列表 ACTION
     */
    public static final String STORAGE_RUBBISH_LIST = "rub/list";

    /**
     * 删除垃圾箱中的所有内容 ACTION
     */
    public static final String STORAGE_CLEAR_RUBBISH = "rub/clr";

    /**
     * 恢复垃圾箱中的所有内容 ACTION
     */
    public static final String STORAGE_RECOVERY_RUBBISH = "rub/recover";

    /**
     * 根据objectId删除垃圾箱中的单个文件 ACTION
     */
    public static final String STORAGE_REMOVE_SINGLE_RUBBISH = "rub/file_clr";

    /**
     * 根据objectId恢复垃圾箱中的单个文件 ACTION
     */
    public static final String STORAGE_RECOVERY_SINGLE_FILE = "rub/file_recover";

    /**
     * 根据DcatId删除垃圾箱中的单个文件夹 ACTION
     */
    public static final String STORAGE_REMOVE_SINGLE_DIR_RUBBISH = "rub/dir_clr";

    /**
     * 根据catId恢复垃圾箱中的单个文件夹 ACTION
     */
    public static final String STORAGE_RECOVERY_SINGLE_DIR = "rub/file_recover";

    /**********************************
     * OAuth2.0 接口
     ********************************/
    /**
     * oauth认证, appkey(client_id)
     * 
     * @deprecated 请使用{@link Params#CLIENT_ID}
     */
    @Deprecated
    public static final String OAUTH_CLIENT_ID = Params.CLIENT_ID;

    /**
     * oauth认证, appSecret(client_secret)
     * 
     * @deprecated 请使用{@link Params#CLIENT_SECRET}
     */
    @Deprecated
    public static final String OAUTH_CLIENT_SECRET = Params.CLIENT_SECRET;
    /**
     * oauth认证, 用户名
     * 
     * @deprecated 请使用{@link Params#USER_NAME}
     */
    @Deprecated
    public static final String OAUTH_USERNAME = Params.USER_NAME;
    /**
     * oauth认证, 密码
     * 
     * @deprecated 请使用 {@link Params#PASSWORD}
     */
    @Deprecated
    public static final String OAUTH_PASSWORD = Params.PASSWORD;
    /**
     * oauth认证, 设备id
     * 
     * @deprecated 请使用 {@link Params#DEVICEID}
     */
    @Deprecated
    public static final String OAUTH_DEVICEID = Params.DEVICEID;
    /**
     * oauth认证, web认证后返回的code
     * 
     * @deprecated 请使用 {@link Params#CODE}
     */
    @Deprecated
    public static final String OAUTH_CODE = Params.CODE;
    /**
     * oauth认证, web认证时的回调地址
     * 
     * @deprecated 请使用 {@link Params#REDIRECT_URI}
     */
    @Deprecated
    public static final String OAUTH_REDIRECT_URI = Params.REDIRECT_URI;

    /**
     * oauth web认证，返回type
     * 
     * @deprecated 请使用{@link Params#RESPONSE_TYPE}
     */
    @Deprecated
    public static final String OAUTH_RESPONSE_TYPE = Params.RESPONSE_TYPE;

    /**
     * partner service, 合作方
     * 
     * @deprecated 请使用{@link Params#PARTNER}
     */
    @Deprecated
    public static final String PARTNER = Params.PARTNER;
    /**
     * partner service, cnzz合作方名称
     */
    public static final String PARTNER_CNZZ = "cnzz";

    /**
     * 系统端下发的XML文本数据中表示需要进行解压缩处理的节点
     */
    public static final String COMPRESSCONTENTTAG = "compressContent";
    /**
     * launcher系统端下发的XML文本数据中表示需要进行解压缩处理的节点
     */
    public static final String COMPRESSCONTENTTAG_ROOT = "root";

    /**
     * 请求系统端传入的压缩标志_压缩
     */
    public static final String COMPRESSFLAG_YES = "1";
    /**
     * 请求系统端传入的压缩标志_不压缩
     */
    public static final String COMPRESS_NO = "0";

    /**
     * API版本信息
     */
    public static final String DEFAULTAPIVERSION = "8.0";//API本身的版本7.3
    /**
     * 日志Tag
     */
    public static final String LOGTAG = "HiCloudSDK";

    /**
     * 数据格式_JSON
     */
    public static final int DATATYPE_JSON = 1;
    /**
     * 数据格式_XML
     */
    public static final int DATATYPE_XML = 0;

    /**
     * 节目列表类型_全部
     */
    public static final String PROGLISTTYPE_ALL = "1";
    /**
     * 节目列表类型_更新的
     */
    public static final String PROGLISTTYPE_UPDATED = "2";

    /**
     * 节目列表类型_频道预览节目
     */
    public static final String PROGLISTTYPE_PREVIEW = "8";

    /**
     * 节目列表类型_频道当前播放节目
     */
    public static final String PROGLISTTYPE_CURRENTPLAYING = "9";

    /**
     * 间隔符
     */
    public static final String DELIMITER = "*";
    /**
     * 语言
     * 
     * @deprecated 请使用{@link Params#LANGUAGE_ID}
     */
    @Deprecated
    public static final String LANGUAGEID = Params.LANGUAGE_ID;

    /**
     * 升级_消息版本
     */
    public static final String UPGRADEMSGVERSION = "513";
    /**
     * 升级_消息类型
     */
    public static final String UPGRADEMSGTYPE = "256";
    /**
     * 升级_消息ID
     */
    public static final String UPGRADEMSGID = "66";

    /**
     * OpenAPI调用源_终端
     */
    public static final String INVOKESOURCE_TERMINAL = "1";
    /**
     * OpenAPI调用源_网站
     */
    public static final String INVOKESOURCE_WEB = "2";

    /**
     * 要获取即将播放的节目的标志
     */
    public static final String UPCOMINGFLAG_YES = "1";

    public static final String SDK_RELEASE_VERSION = "1.04.9.0.0_dev";

    /**
     * smarttv5.5 第三方互动广告平台码
     */
    public static final String THIRD_CODE = "thirdcode";

    /**
     * smarttv5.5 获取互动广告授权结果的action
     */
    public static final String ACTION_AD_GETADAUTHINFO = "getadauthinfo";

    /**
     * smarttv5.5 上报异常日志的action
     */
    public static final String PSLOG_ACTION_REPORT_EXCEPTION = "log/report_exception";

    /**
     * DAS1.4 获取异常上报策略action
     */
    public static final String PSLOG_ACTION_GET_EXCEPTION_STRATEGY = "log/get_exp_strategy_all";

    /**
     * DAS1.4 获取异常上报策略action
     */
    public static final String PSLOG_ACTION_REPORT_EXCEPTION_FEEDBACK = "log/report_exp_feedback";

    /**
     * smarttv5.5 上报异常日志的md5
     */
    public static final String MD5_SUM = "md5Sum";

    /**
     * smarttv5.5 上报异常日志的文件名
     */
    public static final String FILE_NAME = "fileName";

    /**
     * 解析域名成功
     */
    public static final int DNS_IP_SUCCESS = 0;
    /**
     * 解析域名失败
     */
    public static final int DNS_IP_FAILD = 1;
    /**
     * 解析到的域名使用起来有缺陷一般为超时
     */
    public static final int DNS_IP_DEFECT = 2;
}
