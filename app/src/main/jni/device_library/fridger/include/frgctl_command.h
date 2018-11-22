#ifndef FRGCTL_COMMAND
#define FRGCTL_COMMAND

#ifdef __cplusplus
extern "C" {
#endif

#define SET_REFRIGERATOR_TEMPERATURE    1    //冰箱冷藏设定温度
#define SET_FREEZE_TEMPERATURE  2    //冰箱冷冻设定温度值   
#define SET_VARIATION_TEMPERATURE   3    //冰箱变温设定温度值

#define SET_WORK_MODE1  4    //运行模式1
#define SET_WORK_MODE2     5    //运行模式2

	//4
#define SET_COMPRESSOR_FREQUENCY    6    //压缩机运行频率
#define SET_NET_STATUS  7    //网络状态
#define SET_FACTORY_CHECK   8    //入厂检测
#define SET_FRIDGE_RESET    9    //冰箱reset


#define SET_WIFI_STATUS1    10   //wifi模块状态位1
#define SET_WIFI_STATUS2    11   //wifi模块状态位2

#define SET_YEAR_HIGH_DATE     12   //当前北京时间：年（高两位）
#define SET_YEAR_LOW_DATE      13   //当前北京时间：年（低两位）
#define SET_MONTH_DATE   14  //当前北京时间：月
#define SET_DAY_DATE   15  //当前北京时间：日
#define SET_HOUR_DATE 16  //当前北京时间：时
#define SET_MINUTE_DATE    17 //当前北京时间：分

#define SET_HISENSE_CLOUD  18 //海信云

#define SET_FUZZY_MODE 19//智能模式
#define SET_SAVE_MODE 20//节能模式
#define SET_SHOW_MODE 21//演示模式
#define SET_REFRIGERATOR_CLOSE 22//冷藏关闭
#define SET_SF_MODE 23//速冻模式
#define SET_VARIATION_CLOSE 24//变温关闭
#define SET_SR_MODE 25//速冷模式

#define SET_HOLIDAY_MODE 26//假日模式
#define SET_DEBACILLI_MODE 27//除菌模式
#define SET_NO_MODE 28//自定义模式


#ifdef __cplusplus
}
#endif

#endif
