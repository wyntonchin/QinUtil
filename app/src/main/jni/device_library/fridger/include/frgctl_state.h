#ifndef _FRGCTL_STATE
#define _FRGCTL_STATE

#ifdef  __cplusplus
extern "C"
{
#endif

	typedef struct _Device_Config
	{
		uchar fuzzy_mode;//智能模式 0:无 1:有
		uchar save_mode;//节能模式0:无 1:有
		uchar show_mode;//演示模式0:无 1:有
		uchar sf_mode;//速冻模式0:无 1:有
		uchar sr_mode;//速冷模式0:无 1:有
		uchar holiday_mode;//假日模式0:无 1:有
		uchar debacilli_mode;//除菌模式0:无 1:有

		uchar  refrigerator_room;//冰箱冷藏 室0:无 1:有
		uchar  freeze_room;//冰箱冷冻室0:无 1:有
		uchar  variation_room;//冰箱变温室0:无 1:有
		uchar  soft_freeze_room;//冰箱软冻室0:无 1:有
		uchar  auto_ice_room;//冰箱自动制冰室0:无 1:有

		uchar  refrigerator_room_open;//冰箱冷藏 室0:关闭1:打开
		uchar  freeze_room_open;//冰箱冷冻室0:关闭1:打开
		uchar  variation_room_open;//冰箱变温室0:关闭1:打开

		char  refrigerator_min_temperature;//冰箱冷藏 室最小值
		char  refrigerator_max_temperature;//冰箱冷藏 室最大值
		char  variation_min_temperature;//冰箱变温室最小值
		char  variation_max_temperature;//冰箱变温室最大值
		char  freeze_min_temperature;//冰箱冷冻室最小值
		char  freeze_max_temperature;//冰箱冷冻室最大值

	} Device_Config;

	typedef struct _WGCommonStatus {
		//Byte 1
		uchar WiFiController : 1; //WIFI 控制板状态
		uchar MsgStatus : 1; //WIFI 控制板收到空调室内控制板信息正常与否	
		uchar LinkStatus : 2; //WIFI 控制板与路由器的链接状态
		uchar LedStat : 1; // LED 运行状态
		uchar PhoneCTL : 1;		//是否有手机控制
		uchar IsWiFiReg : 1;    //WIFI 控制板注册与否
		uchar IsUserCanceled : 1; //用户取消与否

		//Byte 2
		uchar nullbit : 1;
		uchar LedReq : 1;  //家电控制板请求 WIFI 控制板对其 LED 灯控制
		uchar ConfigReq : 1;  //家电控制板请求 WIFI 控制板进入配置模式
		uchar ConfigSelectorReq : 1; //家电控制板请求 Wi-Fi 控制板进行配置模式选择
		uchar ResetReq : 1;	//家电请求 Wi-Fi 模块复位
		uchar PowerOnTime : 1;	//家电控制板告知 Wi-Fi 控制板，家电的上电时间
		uchar SmartCtlMode : 1;	//家电设备告诉 Wi-Fi 板家电设备是否退出智能控制模式,0:on Smart Ctl Mode;1:Quit
		uchar StatusIsChanged : 1; //家电设备设定值或设定状态发生了改变,0：未发生改变；1：发生了改变。
	}WGCommonStatus;

	typedef struct _Frg_Status
	{

		char  refrigerator_temperature;//冰箱冷藏设定温度1
		char  freeze_temperature;//冰箱冷冻设定温度值2
		char  variation_temperature;//冰箱变温设定温度值3

		uchar  work_mode1;//运行模式1       4
		uchar  work_mode2;//运行模式2       5

		uchar  mainboard_version;//主控板版本号6

		char  refrigerator_real_temperature;//冷藏实际温度值   7
		char  freeze_real_temperature;//冷冻实际温度值  8
		char  variation_real_temperature;//变温实际温度值  9
		char  environment_real_temperature;//环境温度实际值   10

		char  freeze_sensor_real_temperature;//冷冻蒸发器传感器实际温度值   11
		char  refrigerator_sensor_real_temperature;//冷藏蒸发器传感器实际温度值   12
		char  variation_sensor_real_temperature;//变温蒸发器传感器实际温度值   13

		uchar  sensor_failure_status;//传感器故障状态   14
		uchar  control_failure_status;//控制故障状态    15

		uchar  running_status;//运行状态   16
		uchar  mainboard_type;//主控板类型值  17
		uchar  displayboard_key_setting;//显示板按键状态设置   18
		uchar  displayboard_type;//产品显示板类型值   19
		uchar  compressor_frequency;//压缩机运行频率   20

		uchar  refrigerator_poweron_ad;//冷藏开机点AD值   21
		uchar  refrigerator_poweroff_ad;//冷藏停机点AD值    22
		uchar  freeze_poweron_ad;//冷冻开机点AD值   23
		uchar  freeze_poweroff_ad;//冷冻停机点AD值   24
		uchar  variation_poweron_ad;//变温开机点AD值  25
		uchar  variation_poweroff_ad;//变温停机点AD值   26
		uchar  sensor_failure_status2;//传感器故障状态 27

		uchar  temperature_room_judge;//温区判断28
		uchar  wifi_setting;//wifi模块相关设置   29
		uchar  displayboard_version;//显示板版本编码   30
		uchar  environment_humidity;//环境湿度   31
		uchar  model_type;//模式类型    32

		char  refrigerator_min_temperature;//冷藏最低设定温度33
		char  refrigerator_max_temperature;//冷藏最高设定温度34
		char  variation_min_temperature;//变温最低设定温度35
		char  variation_max_temperature;//变温最高设定温度36
		char  freeze_min_temperature;//冷冻最低设定温度37
		char  freeze_max_temperature;//冷冻最高设定温度38

		uchar  wifi_next_sendtime;//WiFi端下次发送查询指令的时间间隔39
		uchar  open_the_door_alarm;//开门报警40
		uchar  displayboard_brand;//显示板品牌区分41

		uchar  refrigerator_door_open_time;//冷藏开门时间42
		uchar  variation_door_open_time;//变温开门时间43
		uchar  freeze_door_open_time;//冷冻开门时间44

		uchar  wifi_handshake_fault_flag;//wifi模块握手故障标志
		uchar  reserve46;//保留46
		uchar  reserve47;//保留47
		uchar  reserve48;//保留48
		uchar  reserve49;//保留49
		uchar  reserve50;//保留50
		uchar  reserve51;//保留51
		uchar  reserve52;//保留52
		uchar  reserve53;//保留53
		uchar  reserve54;//保留54
		uchar  reserve55;//保留55
		uchar  reserve56;//保留56
		uchar  reserve57;//保留57
		uchar  reserve58;//保留58
		uchar  reserve59;//保留59
		uchar  reserve60;//保留60

		uchar  net_status;//网络状态61
		uchar  factory_check;//入厂检测62
		uchar  fridge_reset;//冰箱reset  63

		uchar  wifi_status1;//wifi模块状态位1  64
		uchar  wifi_status2;//wifi模块状态位2  65

		uchar  year_high_date;//当前北京时间：年（高两位）66
		uchar  year_low_date;//当前北京时间：年（低两位）67
		uchar  month_date;//当前北京时间：月68
		uchar  day_date;//当前北京时间：日69
		uchar  hour_date;//当前北京时间：时70
		uchar  minute_date;//当前北京时间：分71

		uchar  hisense_cloud;//海信云72

		//扩展

		uchar fuzzy_mode;//智能模式---- 0:关闭1:打开73
		uchar save_mode;//节能模式---- 0:关闭1:打开74
		uchar show_mode;//演示模式---- 0:关闭1:打开75
		uchar sf_mode;//速冻模式---- 0:关闭1:打开76
		uchar sr_mode;//速冷模式---- 0:关闭1:打开77
		uchar holiday_mode;//假日模式---- 0:关闭1:打开78
		uchar debacilli_mode;//除菌模式---- 0:关闭1:打开79

	} Frg_Status;

#ifdef __cplusplus
}
#endif

#endif
