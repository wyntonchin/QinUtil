#ifndef _WASHER_STATE
#define _WASHER_STATE

#ifdef  __cplusplus
extern "C"
{
#endif

typedef struct _Washer_Status
{
	// 1
	unsigned char version_h;				//版本高
	// 2
	unsigned char version_l;				//版本低
	// 3
	unsigned char fading:1;					//掉色
	unsigned char elimate_foam:1;		//消泡
	unsigned char auto_rising:1;		//自动加漂
	unsigned char imbalance:1;			//不平衡
	unsigned char gate_lock:1;			//门锁
	unsigned char rev3_2: 1;				//order:1
	unsigned char run_state:1;			//运行/暂停
	unsigned char power_state:1;		//开机/关机
	// 4
	unsigned char function6;				////低端功能6
	//5
	unsigned char rev5:4;
	unsigned char washer_phase:4; 	//洗衣机过程
	//6
	unsigned char washer_prg;				//洗衣机模式/程序
	//7
	unsigned char moto_speed_h;			//电机实际转速高
	//8
	unsigned char moto_speed_l;			//电机实际转速低
	//9
	unsigned char  washer_temprature;	//实际水温
	//10
	unsigned char rev10:4;
	unsigned char rev10_3:1;
	unsigned char add_in_midway:1;		//中途添衣
	unsigned char rev10_1:1;
	unsigned char rev10_0:1;
//	unsigned char rev10:4;
//	unsigned char time_setting:1;
//	unsigned char add_in_midway:1;
	//unsigned char child_lock:1;
//	unsigned char brightness_setting:1;
	//11
	unsigned char soften_setting;		///5.3ºƌ���ϴӂ»�˳¼趨ֵ
	//12
	unsigned char order_hours_left;	//预约剩余小时
	//13
	unsigned char order_min_left;		//预约剩余分钟
	//14
	//unsigned char rev14:2;
	unsigned char washer_fault_info;//:6; //故障信息F01-F99
	//15
	unsigned char time_left_h;				//剩余时间高位
	//16
	unsigned char time_left_l;				//剩余时间低位
	//17
	unsigned char child_observer;			//小孩检测
	//18
	//unsigned char Rinse_time;
	unsigned char detergent_setting;	      ///5.3ºƌ���ϴӂ»�µӼ趨ֵ
	//19
	unsigned char dehydrate_speed_max; //脱水最高允许转速
	//20
	unsigned char dehydrate_speed_min;	//脱水最低允许转速
	//21
	unsigned char work_time_left;				//主洗剩余时间
	//22
	unsigned char rinse_num_left;				//漂洗剩余次数
	//23
	unsigned char dehydrate_time_left;	//脱水剩余时间
	//24
	unsigned char dehydrate_speed_setting;	//设定脱水转速
	//25
	unsigned char wash_temprature_setting;	//设定洗涤水温
	//26
	unsigned char ag_degerming:1; //ag+除菌
	unsigned char strong_wash:1;   //强力
	unsigned char pre_wash:1;	      //预洗
	unsigned char high_water_lev:1;//高水位
	unsigned char airing:1;       //晾护
	unsigned char energy_conserv:1;  //节能
	unsigned char spin_rinse:1;   //漩瀑洗
	unsigned char rev26_0:1;      
	
	//27
	unsigned char use_detergent:1;	//洗涤剂投放
	unsigned char use_softener:1;		//柔顺剂投放
	unsigned char dry:1;						//烘干功能
	unsigned char rev27_4:1;				//
	unsigned char rev27_3:1;				//
	unsigned char brightness_setting_1:1;//亮度设定
	unsigned char crease_immersion:1; 	//防皱浸泡
	unsigned char rev27_0:1;
	//28
	unsigned char water_lel_frq_h;  //水位频率高
	//29
	unsigned char water_lel_frq_l;  //水位频率低
	//30
	unsigned char product_mode;			//生产模式
	//31
	unsigned char indistinct_weight;	//模糊称重
	//32
	unsigned char mainboard_hour_setting;	//主板设置实时小时
	//33
	unsigned char mainboard_min_setting;	//主板设置实时分
	//34
	unsigned char heating_pipe_time_h;		//加热管开启时间高
	//35
	unsigned char function5;							////低端功能5
	//36
	unsigned char inflow_duration_h;			//流程进水时间高位
	//37
	unsigned char inflow_duration_l;			//流程进水时间低位
	//38
	unsigned char turbidity_ad_value;   //浊度检测值
	//39
	unsigned char hardness_ad_value;    //水硬度检测值
	//40
	unsigned char cloth_texture_value;  //布质检测
	//41
	unsigned char detergent_left;				//洗涤剂余量
	//42
	unsigned char soften_left;					//柔顺剂余量
	//43
	unsigned char cur_year_h;						//当前年高
	//44
	unsigned char cur_year_l;						//当前年低
	//45
	unsigned char cur_month;						//当前月
	//46
	unsigned char cur_day;							//当前日期
	//47
	unsigned char GMT_8_hour;						//当前北京小时
	//48
	unsigned char GMT_8_min;						//当前北京分
	//49
	unsigned char on_off_valley_hour;		//峰谷-谷小时
	//50
	unsigned char on_off_valley_min;		//峰谷-谷分钟
	//51
	unsigned char on_off_peak_hour;			//峰谷-峰小时
	//52
	unsigned char on_off_peak_min;			//峰谷-峰分钟
	//53
	unsigned char dry_setting;					//烘干设定
	//54
	unsigned char most_used_prg;				//最常用程序
	//55
	unsigned char most_used_temp_speed; //最常用速度
	//56
	unsigned char most_used_wash_time;	//最常用洗涤时间
	//57
	unsigned char most_used_rinse_num;	//最常用漂洗次数
	//58
	unsigned char pre_wash2;						//预洗2设定值
	//59
	unsigned char machine_mode_area;		//整机型号1
	//60
	unsigned char machine_mode_kg;			//整机型号2
	//61
	unsigned char machine_mode_speed;		//整机型号3
	//62
	unsigned char wash_time_setting;		//洗涤设定时间
	//63
	unsigned char special_stains_value;	//六种特渍值
	//64
	unsigned char rinse_frq_setting;		//漂洗设定次数
	//65
	unsigned char order_time_hour;			//预约设定小时
	//66
	unsigned char order_time_min;				//预约设定分钟
	//67
	unsigned char plus_quickly;					//加快设定值
	//68
	unsigned char plus_rinse;						//加漂设定值
	//69
	unsigned char rev69_7:1;						//onkey_smart_wash:1;
	unsigned char mute:1;								//静音
	unsigned char anti_fade:1;					//防掉色
	unsigned char clean_stop:1;					//一净即停
	unsigned char child_lock:1;					//童锁
	unsigned char flush_light_panel:1;	//筒灯
	unsigned char peak_energy:1;				//峰谷用电开关
	unsigned char who_modify:1;   			//谁改变状态位
	//70
	unsigned char screen_volume;				//屏幕音量
	//71
	unsigned char screen_brightness;		//屏幕亮度
	//72
	unsigned char breathing_light;			//呼吸灯
	//73
	unsigned char prg_start_sound;			//程序开始音
	//74
	unsigned char prg_end_sound;				//程序结束音
	//75
	unsigned char pause_sound;					//暂停音
	//76
	unsigned char func_switch_sound;		//功能选择音
	//77
	unsigned char time_wash;						//时间洗设定值

	unsigned char time_left_h_standby;
	unsigned char time_left_l_standby;

	/**以下为波轮协议库所添加**/
	unsigned char StandbyState							:1;		/**是否为待机状态（开机后未启动）**/
	unsigned char WasherSubPhase;								/* E_WasherPhase 的子状态 */
	//unsigned char RemainDelayTime_Hour;							/* 剩余预约时间：小时 */
	//unsigned char RemainDelayDelayTime_Minute;					/* 剩余预约时间：分钟 */
	unsigned char RemainSoakTime;								/* 剩余浸泡时间 */
	unsigned char SterilizationStatus			:2;		/**杀菌运行状态**/
	unsigned char DrumDryStatus				:2;		/**桶干燥运行状态**/
	unsigned char WaterRecycleStatus			:2;		/**留水运行状态**/
	unsigned char DrumLampStatus				:2;		/**筒灯运行状态**/

	unsigned char FallWashStatus				:2;		/**旋瀑洗运行状态**/
	unsigned char ChildLockStatus				:2;		/**童锁运行状态**/
	unsigned char NighttimeStatus				:2;		/**夜间洗运行状态**/
	unsigned char RinseSprayStatus			:2;		/**漂洗喷淋运行状态**/

	unsigned char PreventFadeStatus			:2;		/**防掉色运行状态**/
	unsigned char CleanAndStopStatus			:2;		/**衣净即停运行状态**/
	unsigned char LuminanceStatus				:2;		/**亮度设定运行状态**/
	unsigned char CleanDrumStatus			:2;		/**洁桶运行状态**/

	unsigned char WaterRinseStatus			:2;		/**注水漂洗运行状态**/
	unsigned char AddSoakStatus				:2;		/**加浸泡运行状态**/
	unsigned char AddRinseStatus				:2;		/**加漂运行状态**/
	unsigned char AirDryStatus				:2;		/**风干运行状态**/

	unsigned char DetergentStatus				:2;		/**洗涤剂投放运行状态**/
	unsigned char SoftenerStatus				:2;		/**软化剂投放运行状态**/
	unsigned char HardnessStatus				:2;		/**硬度运行状态**/
	unsigned char OneKeySmartStatus			:2;		/**一键智洗运行状态**/

	unsigned char WeightSensingStatus			;		/**模糊称重运行状态**/

	unsigned char  Nighttime							:1;		/* 是否开启夜间洗模式 */
	unsigned char  SprayFunction						:1;		/* 是否开启喷淋功能 */
	unsigned char  WaterRecycle							:1;		/* 是否开启留水功能 */
	unsigned char  CleanDrum							:1;		/* 洁桶 */
	unsigned char  WaterfloodRinse						:1;		/* 注水漂洗 */
	unsigned char  DryDum								:1;		/* 是否开启桶干燥功能 */
	unsigned char  AddSoak								:1;		/* 加浸泡 */
	unsigned char  AddRinse								:1;		/* 加漂洗 */

	unsigned char  WeightSensing						:1;		/* 是否开启称重功能 */
	unsigned char  Hardness								:1;		/* 硬度 */


	unsigned char WaterLevelOfFuzzy;							/* 模糊以后的水位 */
	unsigned char MostCommonSpinRpm;							/* 最常用的脱水转速 */
	unsigned char MostCommonSoakTime;							/* 最常用的浸泡时间 */
	unsigned char MostCommonDelayTime;									/* 最常用的预约时间 */
	unsigned char MostCommonSpinTime;							/* 最常用的脱水时间 */
	unsigned char MostCommonWaterStream;						/* 最常用的水流 */
	unsigned char MostCommonSpecialFunction;					/* 最常用的特殊功能 */

	unsigned char ResetWiFiMoudleToFactory;						/* 恢复Wi-Fi模块至出厂设置 */
	unsigned char SettingSoakTime;								/* 设定的浸泡时间 */
	unsigned char SettingSpinTime;								/* 设定的脱水时间 */
	unsigned char SettingWaterStream;							/* 设定的水流 */

	unsigned char SettingRemainTotalTime_H;						/* 设定的洗衣全流程时间高字节*/
	unsigned char SettingRemainTotalTime_L;						/* 设定的洗衣全流程时间低字节 */
	unsigned char SettingWaterLevel;			 				/* 设定的水位高度 */
	unsigned char SettingSoftener;								/* 设定的柔顺剂投放档位 */
	unsigned char SettingDetergent;								/* 设定的洗涤剂投放档位 */

	unsigned char WeatherTemperature_1;							/* 天气温度1 */
	unsigned char WeatherTemperature_2;							/* 天气温度2 */
	unsigned char WeatherHumidity;								/* 温度 */
	unsigned char WeatherUVIndex;							/* 紫外线指数 */
	unsigned char WeatherStatus;							/* 天气状态 */

	unsigned char DisableAutoSetParamByWaterLevel		:1;		/* 是否禁止洗涤时间等参数自动跟随水位变化 */
	unsigned char DisableShortcutKey					:1;		/* 是否禁止类似按洗涤键自动变为单洗等功能 */




}WASHER_STAT;

#ifdef __cplusplus
}
#endif

#endif
