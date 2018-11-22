﻿#ifndef _WASHER_COMMAND
#define _WASHER_COMMAND

#ifdef __cplusplus
extern "C" {
#endif

/*******************command id***************************/
#define SET_IDLE   			     1 //0? 1--ON
#define SET_ORDER          	          2 //0--OFF   1--ON
#define SET_RUN_PAUSE      	      3 //0--PAUSE 1--RUN 
#define SET_POWER		         4 //0--OFF   1--ON

/**********************setting item*********************/
#define SET_CHILD_LK                 5 //0/1 童锁  
#define SET_FLASH_PANEL          6 //0/1 筒灯
//#define SET_SCREEN_VOLUME     7 //音量
//#define SET_BREATHING_LIGHT  8 //呼吸灯
#define SET_CLEAN_STOP            9 //一净即停
#define SET_ANTI_FADE               10 //防掉色
#define SET_END_SOUND	       11 //程序结束音
#define SET_MUTE               12 //设置静音

/*******************program id**************************/
//byte 2
enum WASH_PRG_ID {
      cotton = 1,
      mixture,
      quick_wash_15,
      heavy_wash,
      outdoor_sport,
      shirt,
      sterilization_95,
      anti_allergy,
      self_cleaning,
      only_dehydration,
      rinsing_dehydration,
      underware,
      down_jackets,
      silk_clothing,
      wool,
      chemical_fibre,
      jeans,
      colored_fabric,
      baby_care_wash,
      plush_toys,
      soft_hand_wash,
      onekey_smart_wash,
      night_wash,

      /**以下为波轮协议库添加**/
      Standard=24,			/* 标准（常用）程序 */
      Quick,				/* 快速程序 */
      Strong,				/* 强洗程序 */
      Memory,				/* 记忆程序 */
      OnlyWash,			    /* 单洗程序 */
      OnlyRinse,			/* 单漂洗 */
      Summer				/* 夏衣程序 */
};

/*********************param id*****************************************/
//byte 3
#define PID_DEHYDRATION_SPEED 1  //0--0 
                                 // 4--400 
                                 //5--500 
                                 //6--600
                                 //7--700 
                                 //8--800  
                                 //10--1000 
                                 //12--1200
                                 //13--1400

//byte 4
#define PID_WASH_TEMPRATURE   2 //0--NORMAL
// 2--20
// 3--30
// 4--40
// 5--50
// 6--60
// 9--95

//byte 5                               
#define PID_HIGH_WATER_LEVL 3 //0/1 高水位
#define PID_AIRING                     4 //0/1           晾护
#define PID_ENERGY_SAVE          5//0/1  节能
#define PID_SPIN_RINSE   		      6           
//byte6
#define PID_AG_DEGERMING       7//ag除菌
#define PID_STRONG_WASH        8 //0/1 强力洗
#define PID_PRE_WASH               9 //0/1 预洗

//byte7
#define PID_SOFTEN            10 //0/1 使用柔顺剂
#define PID_DRYING            11 //0/1 烘干
#define PID_BRIGHTNESS       12 //0/1 亮度设定
#define PID_WRINKLE_SOAK      13 //0/1  防皱浸泡

//byte8
#define PID_HARDNESS            14//硬度
#define PID_USE_DETERGENT    15//0/1 使用洗涤剂

//byte9  
#define PID_PRE_WASH2    			16//预洗2

//byte10
#define PID_ORDER_HOURS       17//0-24 hours 预约
//byte11
#define PID_ORDER_MINUTES    18 //0-60 minutes 预约

//byte12
#define PID_WASH_TIME            19 //洗涤时间

//byte13
#define PID_RINSING_FREQUENCY  20//漂洗次数

//byte14
#define PID_SOFTENER_FREQUENCY 21 //0--AUTO 柔顺剂投放频率
//MINOR
//MEDIUM
//MORE
//byte15
#define PID_DETERGENT_FREQUENCY 22 //0--AUTO 洗涤剂投放频率
// 3 --REV


//byte16 reserve

//byte17 //一静即停　筒灯　童锁使用SET_XX
#define PID_PEAK_ENERGY  23//峰谷用电

//byte18 //静音　防掉色　使用SET_XX
#define PID_SMART_WASH  24            //一键智洗

//byte19
#define PID_SCREEN_VOLUME  25          //屏幕音量

//byte20
#define PID_SCREEN_BRIGHTNESS 26     //屏幕亮度

//byte21
#define PID_BREATHING_LIGHT    27 //呼吸灯

//byte22
#define PID_SPECIAL_STAINS_SET     28//0-无
//泥渍
//草渍
//血渍
//酒渍
//咖啡渍
//果渍 
//奶渍

//byte23
#define PID_YEAR_HIGH          29

//byte24
#define PID_YEAR_LOW           30

//byte25
#define PID_MONTH              31

//byte26      
#define PID_DAY                32

//byte27
#define PID_HOUR               33

//byte28
#define PID_MINUTE             34

//byte29
#define PID_WEEK               35

//byte30
#define PID_FUNCTION5      36		//低端功能5

//byte31
#define PID_FUNCTION6      37		//中端功能6

//byte32
#define PID_DRY 38//烘干设定

//byte33 
#define PID_DRY1 39//烘干设定1

//byte34
#define PID_WEATHER_TEMPRATURE_0 40
//byte35
#define PID_WEATHER_TEMPRATURE_1 41

//byte36
#define PID_HUMIDITY           42

//byte37
#define PID_ULTRA_VOILET_INDEX 43 //紫外线指数

//byte38
#define PID_WEATHER            44//天气状态

//byte39
#define PID_RESET_STATE        45//清复位状态

//byte40
#define PID_START_SOUND       46//程序开始音

//byte41
#define PID_END_SOUND           47//程序结束音

//byte42  //暂停提示音
#define  PID_PAUSE_SOUND      48
//byte43  //功能选择音
#define PID_ALARM_SOUND      49

//byte44
#define PID_TIMEWASH_INDEX  50//时间洗索引值

//byte45
#define PID_SOAKING_TIME     51//浸泡时间

#define PID_PLUS_QUICKLY     52//加快时间

#define PID_PLUS_RINSE       53//加漂次数

//以下是波轮洗衣机定义
#define PID_SPIN_TIME			 54	/* 脱水时间 */
#define PID_WATER_RECYLE		 55	/* 留水 */
#define PID_AIR_DRY                      56	/* 风干 */
#define PID_CLEAN_DRUM			 57	/* 洁桶 */
#define PID_NIGHTTIME			 58	/* 夜洗 */
#define PID_DRUM_LAMP			 59	/* 筒灯 */
#define PID_WATER_LEVEL			 60	/* 水位 */
#define PID_WATER_STREAM	     61	/* 水流 */

typedef unsigned char  BOOLEAN;
typedef unsigned char  uint8_t;
typedef unsigned short uint16_t;

typedef struct _washer_param_desc
{
    uint8_t      pm_id;              //����id,PID_XX

    BOOLEAN is_valid;
    int             fix_param; 

    int   param;            
    int   param_idx_offset;    //���������ܲ�������Ӱ��ʱ��offset��end�����궨�·�Χ
    int   param_idx_end;       //���ջ������ۣ�û�в���ֵ��Χ����仯�������������ﲻ�����������������������������������ʹ��*param
}PARAM_ITEM_DESC;

typedef struct _washer_param
{
    uint8_t    pm_id;      //����id,PID_XX
    uint8_t  *param;     //�����趨ֵ��ָ��
    int   len;                   //��������
}PARAM;

typedef struct _washer_command
{
    int        cmd_id;    //����id,SET_XX,��Ӧ״̬���ü�Ӧ���������еĹ���
    uint8_t  *param;     //���������ָ��
    uint8_t  len;	      //��������
}COMMAND;

#ifdef __cplusplus  
}
#endif

#endif
