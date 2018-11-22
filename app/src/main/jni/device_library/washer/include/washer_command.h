#ifndef _WASHER_COMMAND
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
#define SET_CHILD_LK                 5 //0/1 ç«¥é”  
#define SET_FLASH_PANEL          6 //0/1 ç­’ç¯
//#define SET_SCREEN_VOLUME     7 //éŸ³é‡
//#define SET_BREATHING_LIGHT  8 //å‘¼å¸ç¯
#define SET_CLEAN_STOP            9 //ä¸€å‡€å³åœ
#define SET_ANTI_FADE               10 //é˜²æ‰è‰²
#define SET_END_SOUND	       11 //ç¨‹åºç»“æŸéŸ³
#define SET_MUTE               12 //è®¾ç½®é™éŸ³

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

      /**ä»¥ä¸‹ä¸ºæ³¢è½®åè®®åº“æ·»åŠ **/
      Standard=24,			/* æ ‡å‡†ï¼ˆå¸¸ç”¨ï¼‰ç¨‹åº */
      Quick,				/* å¿«é€Ÿç¨‹åº */
      Strong,				/* å¼ºæ´—ç¨‹åº */
      Memory,				/* è®°å¿†ç¨‹åº */
      OnlyWash,			    /* å•æ´—ç¨‹åº */
      OnlyRinse,			/* å•æ¼‚æ´— */
      Summer				/* å¤è¡£ç¨‹åº */
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
#define PID_HIGH_WATER_LEVL 3 //0/1 é«˜æ°´ä½
#define PID_AIRING                     4 //0/1           æ™¾æŠ¤
#define PID_ENERGY_SAVE          5//0/1  èŠ‚èƒ½
#define PID_SPIN_RINSE   		      6           
//byte6
#define PID_AG_DEGERMING       7//agé™¤èŒ
#define PID_STRONG_WASH        8 //0/1 å¼ºåŠ›æ´—
#define PID_PRE_WASH               9 //0/1 é¢„æ´—

//byte7
#define PID_SOFTEN            10 //0/1 ä½¿ç”¨æŸ”é¡ºå‰‚
#define PID_DRYING            11 //0/1 çƒ˜å¹²
#define PID_BRIGHTNESS       12 //0/1 äº®åº¦è®¾å®š
#define PID_WRINKLE_SOAK      13 //0/1  é˜²çš±æµ¸æ³¡

//byte8
#define PID_HARDNESS            14//ç¡¬åº¦
#define PID_USE_DETERGENT    15//0/1 ä½¿ç”¨æ´—æ¶¤å‰‚

//byte9  
#define PID_PRE_WASH2    			16//é¢„æ´—2

//byte10
#define PID_ORDER_HOURS       17//0-24 hours é¢„çº¦
//byte11
#define PID_ORDER_MINUTES    18 //0-60 minutes é¢„çº¦

//byte12
#define PID_WASH_TIME            19 //æ´—æ¶¤æ—¶é—´

//byte13
#define PID_RINSING_FREQUENCY  20//æ¼‚æ´—æ¬¡æ•°

//byte14
#define PID_SOFTENER_FREQUENCY 21 //0--AUTO æŸ”é¡ºå‰‚æŠ•æ”¾é¢‘ç‡
//MINOR
//MEDIUM
//MORE
//byte15
#define PID_DETERGENT_FREQUENCY 22 //0--AUTO æ´—æ¶¤å‰‚æŠ•æ”¾é¢‘ç‡
// 3 --REV


//byte16 reserve

//byte17 //ä¸€é™å³åœã€€ç­’ç¯ã€€ç«¥é”ä½¿ç”¨SET_XX
#define PID_PEAK_ENERGY  23//å³°è°·ç”¨ç”µ

//byte18 //é™éŸ³ã€€é˜²æ‰è‰²ã€€ä½¿ç”¨SET_XX
#define PID_SMART_WASH  24            //ä¸€é”®æ™ºæ´—

//byte19
#define PID_SCREEN_VOLUME  25          //å±å¹•éŸ³é‡

//byte20
#define PID_SCREEN_BRIGHTNESS 26     //å±å¹•äº®åº¦

//byte21
#define PID_BREATHING_LIGHT    27 //å‘¼å¸ç¯

//byte22
#define PID_SPECIAL_STAINS_SET     28//0-æ— 
//æ³¥æ¸
//è‰æ¸
//è¡€æ¸
//é…’æ¸
//å’–å•¡æ¸
//æœæ¸ 
//å¥¶æ¸

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
#define PID_FUNCTION5      36		//ä½ç«¯åŠŸèƒ½5

//byte31
#define PID_FUNCTION6      37		//ä¸­ç«¯åŠŸèƒ½6

//byte32
#define PID_DRY 38//çƒ˜å¹²è®¾å®š

//byte33 
#define PID_DRY1 39//çƒ˜å¹²è®¾å®š1

//byte34
#define PID_WEATHER_TEMPRATURE_0 40
//byte35
#define PID_WEATHER_TEMPRATURE_1 41

//byte36
#define PID_HUMIDITY           42

//byte37
#define PID_ULTRA_VOILET_INDEX 43 //ç´«å¤–çº¿æŒ‡æ•°

//byte38
#define PID_WEATHER            44//å¤©æ°”çŠ¶æ€

//byte39
#define PID_RESET_STATE        45//æ¸…å¤ä½çŠ¶æ€

//byte40
#define PID_START_SOUND       46//ç¨‹åºå¼€å§‹éŸ³

//byte41
#define PID_END_SOUND           47//ç¨‹åºç»“æŸéŸ³

//byte42  //æš‚åœæç¤ºéŸ³
#define  PID_PAUSE_SOUND      48
//byte43  //åŠŸèƒ½é€‰æ‹©éŸ³
#define PID_ALARM_SOUND      49

//byte44
#define PID_TIMEWASH_INDEX  50//æ—¶é—´æ´—ç´¢å¼•å€¼

//byte45
#define PID_SOAKING_TIME     51//æµ¸æ³¡æ—¶é—´

#define PID_PLUS_QUICKLY     52//åŠ å¿«æ—¶é—´

#define PID_PLUS_RINSE       53//åŠ æ¼‚æ¬¡æ•°

//ä»¥ä¸‹æ˜¯æ³¢è½®æ´—è¡£æœºå®šä¹‰
#define PID_SPIN_TIME			 54	/* è„±æ°´æ—¶é—´ */
#define PID_WATER_RECYLE		 55	/* ç•™æ°´ */
#define PID_AIR_DRY                      56	/* é£å¹² */
#define PID_CLEAN_DRUM			 57	/* æ´æ¡¶ */
#define PID_NIGHTTIME			 58	/* å¤œæ´— */
#define PID_DRUM_LAMP			 59	/* ç­’ç¯ */
#define PID_WATER_LEVEL			 60	/* æ°´ä½ */
#define PID_WATER_STREAM	     61	/* æ°´æµ */

typedef unsigned char  BOOLEAN;
typedef unsigned char  uint8_t;
typedef unsigned short uint16_t;

typedef struct _washer_param_desc
{
    uint8_t      pm_id;              //²ÎÊıid,PID_XX

    BOOLEAN is_valid;
    int             fix_param; 

    int   param;            
    int   param_idx_offset;    //¹ØÁª²ÎÊıÊÜ²ÎÊıµ÷ÕûÓ°ÏìÊ±£¬offset¼°endÓÃÀ´±ê¶¨ĞÂ·¶Î§
    int   param_idx_end;       //°´ÕÕ»áÒéÌÖÂÛ£¬Ã»ÓĞ²ÎÊıÖµ·¶Î§ÕûÌå±ä»¯µÄÇé¿ö£¬Òò´ËÕâÀï²»ÔÙÃèÊö¸ÃÇé¿ö£¬Èç¹ûºóÆÚÓĞÕâÖÖÇé¿ö£¬¿ÉÒÔÊ¹ÓÃ*param
}PARAM_ITEM_DESC;

typedef struct _washer_param
{
    uint8_t    pm_id;      //²ÎÊıid,PID_XX
    uint8_t  *param;     //²ÎÊıÉè¶¨ÖµµÄÖ¸Õë
    int   len;                   //²ÎÊı³¤¶È
}PARAM;

typedef struct _washer_command
{
    int        cmd_id;    //ÃüÁîid,SET_XX,¶ÔÓ¦×´Ì¬ÉèÖÃ¼°Ó¦ÓÃÉèÖÃÏîÖĞµÄ¹¦ÄÜ
    uint8_t  *param;     //ÃüÁî²ÎÊıµÄÖ¸Õë
    uint8_t  len;	      //²ÎÊı³¤¶È
}COMMAND;

#ifdef __cplusplus  
}
#endif

#endif
