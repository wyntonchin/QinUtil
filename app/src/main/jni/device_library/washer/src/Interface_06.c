#ifdef __cplusplus
extern "C" {
#endif

#include "washer_interface.h"

#define TRUE		1
#define FAIL		0

/*****************************************
	Wash Wear Program  
*****************************************/
#define COTTON_FIBRE_WASH			1
#define MIX_WASH 					2
#define SUPER_QUICKLY_WASH 		3
#define BIG_WASH 					4
#define OUTDOOR_WASH 				5
#define SHIRT_WASH 					6
#define STERILIZE_95_WASH			7
#define PREVENT_ALERGIC_WASH		8
#define CLEAN_BUKET_WASH 			9
#define SINGLE_DEHY_WASH 			10
#define RINSE_DEHY_WASH 			11
#define UNDERWEAR_WASH			12
#define FEATHER_WASH 				13
#define SILK_WASH 					14
#define WOOL_WASH					15		
#define CHEM_FIBER_WASH 			16
#define JEANS_WASH 					17
#define COLOUR_FABRIC_WASH 		18
#define PROTECT_BABY_WASH 		19
#define LINT_TOY_WASH 				20
#define SUPGEN_HAND_WASH 			21
#define ONE_KEY_WASH				22
#define NIGHT_WASH					23

/*****************************************
	Water Temprature
*****************************************/
#define WATER_COLD_TEMP		 0			
#define WATER_20_TEMP			 2
#define WATER_30_TEMP			 3
#define WATER_40_TEMP			 4
#define WATER_50_TEMP			 5
#define WATER_60_TEMP			 6
#define WATER_90_TEMP			 9

/*****************************************
	RINSE TIMES
*****************************************/
#define RINSE_ONE		1			
#define RINSE_TWO		2
#define RINSE_THREE		3
#define RINSE_FOUR		4
#define RINSE_FIVE		5
#define RINSE_SIX		6

/*****************************************
	DEHY SPEED
*****************************************/
#define AVOID_SPEED		 0			
#define SPEED_400		 4
#define SPEED_500		 5
#define SPEED_600		 6
#define SPEED_700		 7
#define SPEED_800		 8
#define SPEED_900		 9
#define SPEED_1000		10
#define SPEED_1100		11
#define SPEED_1200		12
#define SPEED_1300		13
#define SPEED_1400		14

/*****************************************
	SPECIAL BLOTTED
*****************************************/
#define VOID_BLOTTED	0
#define MUD_BLOTTED	1
#define GRASS_BLOTTED	2
#define BLOOD_BLOTTED	3
#define WINE_BLOTTED	4
#define COFFEE_BLOTTED	5
#define FRUIT_BLOTTED	6
#define MILK_BLOTTED	7

/*****************************************
	TIME WASH
*****************************************/
#define EXIT_TIMEWASH	0  
#define FIRST_VAL		1
#define SENCOND_VAL	2
#define THIRD_VAL		3
#define FORTH_VAL		4
#define FIFTH_VAL		5
#define SIXTH_VAL		6
#define SEVENTH_VAL		7

/*****************************************
	WASHER PHASE
*****************************************/
#define WASHER_IDLE		      0
#define WASHER_ORDERWAIT	1
#define WASHER_PREWASH     3
#define WASHER_WASH		4
#define WASHER_RINSE		5
#define WASHER_SPIN		6
#define WASHER_WASHEND	7

/*****************************************
	PUT IN TIME
*****************************************/
#define PUT_OFF       0
#define PUT_AUTO     0
#define PUT_FEW      1
#define PUT_MIDDLE  2
#define PUT_MUCH    3

/*****************************************
	PLUS QUICK TIME 
*****************************************/
#define PLUS_QUICK_0     0
#define PLUS_QUICK_5     1
#define PLUS_QUICK_10   2
#define PLUS_QUICK_15   3

/*****************************************
	FUNCTION5 VALIUE 
*****************************************/
#define FUNCTION5_CANCLE		0
#define FUNCTION5_LIANGHU		1
#define FUNCTION5_PREVZHOU	2
#define FUNCTION5_STRONG		3
#define FUNCTION5_PREWASH		4

/*****************************************
	FUNCTION6 VALIUE 
*****************************************/
#define FUNCTION6_CANCLE             0
#define FUNCTION6_LIANGHU           1
#define FUNCTION6_MORE_DIRTY      2
#define FUNCTION6_FEW_DIRTY        3
#define FUNCTION6_PREWASH          4
/*****************************************
	ORDER TIME 
*****************************************/
#define NO_ORDER       0
#define Huchi_Startaddress		91
#define Rawstate_Startaddress	16
#define Rawstate_Endaddress		106


#define CMD_CONTROL	1
#define CMD_PROG       	2
#define CMD_PARAM		3


typedef unsigned char BOOL;


const unsigned char Prog_Temp_Washertime[][10]=
{
	40,0,40,40,50,50,160,0,0,80,
	40,0,40,40,50,50,160,0,0,80,			//COTTON 
	35,35,35,35,35,35,35,35,35,35,			//MIX  
	5,0,5,10,20,0,0,0,0,0,					//SUPER 15'
	40,40,40,40,40,40,40,40,40,40,			//BIG 
	25,25,25,25,25,25,25,25,25,25,			//OUT DOOR  
	40,40,40,40,40,40,40,40,40,40,  			//SHIRT  
	0,0,0,0,0,0,0,0,0,100,					//95' 
	35,35,35,35,35,35,35,35,35,35,			//PREVENT_ALERGIC
	0,0,0,0,0,0,0,0,0,60,					//CLEAN
	0,0,0,0,0,0,0,0,0,0,					//SINGLE DEHY
	0,0,0,0,0,0,0,0,0,0,					//RINSE DEHY
	40,40,40,40,40,40,40,40,40,40,			//UNDER 
	30,30,30,30,30,30,30,30,30,30,			//FEATER
	25,25,25,25,25,25,25,25,25,25,			//SILK
	33,33,33,33,33,33,33,33,33,33,			//WOOL
	30,30,30,30,30,30,30,30,30,30,			//CHEM_FIBER	
	40,40,40,40,40,40,40,40,40,40,			//JEANS
	40,40,40,40,40,40,40,40,40,40,			//COLOUR
	60,60,60,60,60,60,60,60,60,60,			//BABY
	30,30,30,30,30,30,30,30,30,30,  			//LINT_TOY
	25,25,25,25,25,25,25,25,25,25,			//SUPER HAND
  	35,35,35,35,35,35,35,35,35,35,			//ONE KEY   
  	50,50,50,50,50,50,50,50,50,50			//NIGHT 
};

const unsigned char Prog_Temp_Washertime_Plus[]=
{0,8,1,1,6,6,6,6,6,1,0,0,6,6,2,2,6,6,6,1,2,2,1,5};

const unsigned char Prog_Rinsetime[]=
{0,19,10,6,12,12,13,13,13,0,0,12,12,13,8,8,10,13,13,12,8,8,10,11};

const unsigned char Prog_Rinse_Softtime[]=
{0,11,4,4,6,6,7,6,7,4,0,6,6,7,6,6,4,7,7,6,6,6,4,6};

const unsigned char Prog_Savetime[]=
{0,10,5,0,5,5,5,20,5,0,0,0,5,5,5,5,5,5,5,10,5,5,5,5};

const unsigned char Plusquickly_time[]=
{0,5,10,15};

const unsigned char Prewash2_time[]=
{0,20,30,40};

const unsigned char Prog_Strongtime[]=
{0,30,20,0,30,20,20,0,0,0,0,0,20,0,0,0,20,20,20,20,0,0,20,20};

const unsigned char Wash_Settime[][6]=
{
	0,0,0,0,0,0,
	20,40,50,60,70,80,
	15,25,35,45,55,65,
	0,0,5,10,15,20,
	20,30,40,50,60,70,
	15,20,25,30,35,40,
	20,30,40,50,60,70,
	60,80,100,0,0,0,
	15,25,35,45,55,65,
	40,50,60,70,80,90,
	0,0,0,0,0,0,
	0,0,0,0,0,0,
	20,30,40,50,60,70,
	20,25,30,35,40,45,
	15,20,25,30,35,40,
	0,0,33,0,0,0,
	10,20,30,40,50,60,
	20,30,40,50,60,70,
	20,30,40,50,60,70,
	40,50,60,70,80,90,
	20,25,30,35,40,45,
	15,20,25,30,35,40,
	15,25,35,45,55,65,
	50,50,50,50,50,50
};

const unsigned char Prog_Dehy_Outtime[]=
{0,1,3,1,3,3,3,3,3,1,1,1,3,3,2,2,3,3,3,3,2,2,3,2};

const unsigned char Prog_Dehytime[]=
{0,11,5,4,6,6,6,9,6,4,11,11,6,6,4,4,5,6,6,6,4,4,5,6};

const unsigned char Prog_Pretime[]=
{0,20,20,0,20,20,0,0,0,0,0,0,20,0,0,0,20,20,20,20,0,0,0};

const unsigned char Rinse_times[]=
{0,2,2,2,3,2,2,3,4,1,0,2,3,2,3,2,2,2,2,3,3,3,2,2};

const unsigned char Prog_default_washtime[]=
{0,50,35,5,40,25,40,100,35,60,0,0,40,30,25,33,30,40,40,60,30,25,35,50};

const unsigned char Prog_default_tempreture[]=
{
	WATER_COLD_TEMP,WATER_40_TEMP,WATER_40_TEMP,WATER_20_TEMP,WATER_30_TEMP,
	WATER_30_TEMP,WATER_40_TEMP,WATER_90_TEMP,WATER_40_TEMP,WATER_COLD_TEMP,
	WATER_COLD_TEMP,WATER_COLD_TEMP,WATER_30_TEMP,WATER_30_TEMP,WATER_20_TEMP,
	WATER_40_TEMP,WATER_40_TEMP,WATER_30_TEMP,WATER_20_TEMP,WATER_30_TEMP,
	WATER_30_TEMP,WATER_30_TEMP,WATER_40_TEMP,WATER_30_TEMP
};

const unsigned char Timewash_TotalTime[][6]=
{
	0,0,0,0,0,0,
	0,70,80,90,100,130,
	0,38,43,48,58,78,
	0,15,20,25,30,40,
	0,55,65,75,85,105,
	0,43,48,53,58,68,
	0,45,55,65,75,95,
	0,90,110,130,150,170,
	0,76,81,86,96,116,//daoxiyiyou
	0,40,50,60,70,90,
	0,0,0,0,0,0,
	0,0,0,0,0,0,
	0,55,65,75,85,105,
	0,50,55,60,65,75,
	0,40,45,50,55,65,		///����������޸ĳ���5.31
	0,0,0,0,0,0,
	0,38,43,48,58,78,
	0,60,65,70,75,95,
	0,60,65,70,75,95,
	0,85,95,105,115,145,
	0,45,50,55,60,80,
	0,40,45,50,55,65,
	0,40,45,50,55,65
};

const unsigned char Timewash_Washtime[][6]=
{
	0,0,0,0,0,0,
	0,20,30,40,50,80,
	0,10,15,20,30,50,
	0,5,10,15,20,30,
	0,10,20,30,40,60,
	0,10,15,20,25,35,
	0,10,20,30,40,60,
	0,40,60,80,100,120,
	0,15,20,25,35,55,////daoxiyiyou
	0,30,40,50,60,80,
	0,0,0,0,0,0,
	0,0,0,0,0,0,
	0,10,20,30,40,60,
	0,15,20,25,30,40,
	0,10,15,20,25,35,
	0,0,0,0,0,0,
	0,10,15,20,30,50,
	0,25,30,35,40,60,
	0,25,30,35,40,60,
	0,30,40,50,60,90,
	0,15,20,25,30,50,
	0,10,15,20,25,35,
	0,10,15,20,25,35
};

//���ն�Ӧ��ϴ��ʱ����¶�

const unsigned char Cotton_Jeans_Blotted[][2]=
{
	
	WATER_60_TEMP,60,
	WATER_60_TEMP,60,
	WATER_60_TEMP,50,
	WATER_30_TEMP,20,
	WATER_60_TEMP,40,
	WATER_60_TEMP,55,		//?
	WATER_20_TEMP,30,
	WATER_30_TEMP,20
};

const unsigned char Chemail_Fiber_Mix_Blotted[][2]=
{
	WATER_40_TEMP,60,
	WATER_40_TEMP,60,
	WATER_40_TEMP,50,
	WATER_30_TEMP,20,
	WATER_40_TEMP,40,
	WATER_40_TEMP,55,		//?
	WATER_20_TEMP,30,
	WATER_30_TEMP,20
};

const unsigned char Silk_Blotted[][2]=
{
	WATER_30_TEMP,60,
	WATER_30_TEMP,60,
	WATER_30_TEMP,50,
	WATER_30_TEMP,20,
	WATER_30_TEMP,40,
	WATER_30_TEMP,55,		//?
	WATER_20_TEMP,30,
	WATER_30_TEMP,20
};

const unsigned char Under_Big_Blotted[][2]=
{
	WATER_40_TEMP,60,
	WATER_40_TEMP,60,
	WATER_40_TEMP,50,
	WATER_30_TEMP,20,
	WATER_40_TEMP,40,
	WATER_40_TEMP,55,		//?
	WATER_20_TEMP,30,
	WATER_30_TEMP,20
};

const unsigned char Outdoor_Shirt_Blotted[][2]=
{
	WATER_40_TEMP,60,
	WATER_40_TEMP,60,
	WATER_40_TEMP,50,
	WATER_30_TEMP,20,
	WATER_40_TEMP,40,
	WATER_40_TEMP,55,		//?
	WATER_20_TEMP,30,
	WATER_30_TEMP,20
};

const unsigned char Colour_Wear_Blotted[][2]=
{
	WATER_30_TEMP,60,
	WATER_30_TEMP,60,
	WATER_30_TEMP,50,
	WATER_30_TEMP,20,
	WATER_30_TEMP,40,
	WATER_30_TEMP,55,		//?
	WATER_20_TEMP,30,
	WATER_30_TEMP,20
};

const unsigned char Baby_Nurse_Blotted[][2]=
{
	WATER_30_TEMP,60,
	WATER_30_TEMP,60,
	WATER_30_TEMP,50,
	WATER_30_TEMP,20,
	WATER_30_TEMP,40,
	WATER_30_TEMP,55,		//?
	WATER_20_TEMP,30,
	WATER_30_TEMP,20
};

const unsigned char Hair_Toy_Blotted[][2]=
{
	WATER_40_TEMP,60,
	WATER_40_TEMP,60,
	WATER_40_TEMP,50,
	WATER_30_TEMP,20,
	WATER_40_TEMP,40,
	WATER_40_TEMP,55,		//?
	WATER_20_TEMP,30,
	WATER_30_TEMP,20
};

void Huchi_Proc(WASHER_STAT *p_devStatus,PARAM_ITEM_DESC *p_desc_list,int *num_desc_list)
{

	if (p_devStatus->washer_prg == COTTON_FIBRE_WASH ||p_devStatus->washer_prg == CHEM_FIBER_WASH
		||p_devStatus->washer_prg == BIG_WASH ||p_devStatus->washer_prg == UNDERWEAR_WASH
		||p_devStatus->washer_prg == MIX_WASH ||p_devStatus->washer_prg == OUTDOOR_WASH
		||p_devStatus->washer_prg == JEANS_WASH ||p_devStatus->washer_prg == COLOUR_FABRIC_WASH
		||p_devStatus->washer_prg == PROTECT_BABY_WASH)
	{
		(*num_desc_list) = 0;
		
		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL)
		{
			p_desc_list->pm_id = PID_HIGH_WATER_LEVL;
			p_desc_list->is_valid = FAIL;			
			(*num_desc_list)++;
			p_desc_list++;
		}

		if (p_devStatus->pre_wash == TRUE ||p_devStatus->pre_wash2 != FAIL)	// 5.8�Ķ�״̬��Ļ������
		{
			p_desc_list->pm_id = PID_TIMEWASH_INDEX;
			p_devStatus->time_wash = EXIT_TIMEWASH;						//�Ա�����õ�4.25��
			p_desc_list->is_valid = FAIL;			
			(*num_desc_list)++;
			p_desc_list++;

			p_desc_list->pm_id = PID_SPECIAL_STAINS_SET;
			p_devStatus->special_stains_value = VOID_BLOTTED;					//�Ա�����õ�4.25��
			p_desc_list->is_valid = FAIL;			
			(*num_desc_list)++;
			p_desc_list++;
		}

		
		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL ||p_devStatus->special_stains_value != VOID_BLOTTED||p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_STRONG_WASH;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;

			p_desc_list->pm_id = PID_PRE_WASH;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;
		}

		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL ||p_devStatus->time_wash != EXIT_TIMEWASH ||p_devStatus->special_stains_value != VOID_BLOTTED)
		{
			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}

		if (p_devStatus->time_wash != EXIT_TIMEWASH ||p_devStatus->special_stains_value != VOID_BLOTTED)
		{
			p_desc_list->pm_id = PID_WASH_TEMPRATURE;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;	
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_RINSING_FREQUENCY;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}

		if (p_devStatus->strong_wash == TRUE ||p_devStatus->pre_wash == TRUE ||p_devStatus->high_water_lev == TRUE
			||p_devStatus->pre_wash2 != FAIL)
		{
			p_desc_list->pm_id = PID_ENERGY_SAVE;
			p_desc_list->is_valid = FAIL;			
			(*num_desc_list)++;			
			p_desc_list++;
			
		}

		if (p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_DEHYDRATION_SPEED;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
			
	}
	else if (p_devStatus->washer_prg == WOOL_WASH)
	{
		*num_desc_list = 0;
		
		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL)
		{
			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}

	}
	else if (p_devStatus->washer_prg == SILK_WASH)
	{
		*num_desc_list = 0;
		
		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL ||p_devStatus->special_stains_value != VOID_BLOTTED ||p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list) ++;
			p_desc_list++;

		}

		if (p_devStatus->special_stains_value != VOID_BLOTTED ||p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			//p_desc_list->pm_id = PID_ENERGY_SAVE;
			//p_desc_list->is_valid = FAIL;
			//(*num_desc_list) ++;
			//p_desc_list++;

			p_desc_list->pm_id = PID_WASH_TEMPRATURE;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;	
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_RINSING_FREQUENCY;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list) ++;
			p_desc_list++;
			
			if (p_devStatus->time_wash != EXIT_TIMEWASH)
			{
				p_desc_list->pm_id = PID_DEHYDRATION_SPEED;
				p_desc_list->fix_param = 0xFFFF;
				p_desc_list->is_valid = TRUE;
				(*num_desc_list) ++;
				p_desc_list++;
			}
		}

	}
	else if (p_devStatus->washer_prg == FEATHER_WASH)
	{
		*num_desc_list = 0;
		
		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->energy_conserv == TRUE)
		{
			/*p_desc_list->pm_id = PID_TIMEWASH_INDEX;
			p_devStatus->time_wash = EXIT_TIMEWASH;						//�Ա�����õ�4.25��
			p_desc_list->is_valid = FAIL;			
			(*num_desc_list)++;
			p_desc_list++;*/

			p_desc_list->pm_id = PID_HIGH_WATER_LEVL;
			p_desc_list->is_valid = FAIL;			
			(*num_desc_list)++;
			p_desc_list++;
		}

		if (p_devStatus->high_water_lev == TRUE)
		{
			p_desc_list->pm_id = PID_ENERGY_SAVE;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL)
		{

			p_desc_list->pm_id = PID_HIGH_WATER_LEVL;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;
			
		}

		if (p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_WASH_TEMPRATURE;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;

			/*p_desc_list->pm_id = PID_ENERGY_SAVE;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;*/

			p_desc_list->pm_id = PID_RINSING_FREQUENCY;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;

			p_desc_list->pm_id = PID_DEHYDRATION_SPEED;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
				
		}

	}
	else if (p_devStatus->washer_prg == RINSE_DEHY_WASH)
	{
		(*num_desc_list) = 0;
	}
	else if (p_devStatus->washer_prg == SINGLE_DEHY_WASH)
	{
		(*num_desc_list) = 0;
	}
	else if (p_devStatus->washer_prg == SUPER_QUICKLY_WASH)
	{
		(*num_desc_list) = 0;
		
		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_WASH_TEMPRATURE;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_RINSING_FREQUENCY;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_DEHYDRATION_SPEED;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
	}		
	else if (p_devStatus->washer_prg == SHIRT_WASH)
	{
		*num_desc_list = 0;

		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->strong_wash == TRUE)
		{
			p_desc_list->pm_id = PID_TIMEWASH_INDEX;
			p_devStatus->time_wash = EXIT_TIMEWASH;					//�Ա�����õ�4.25��
			p_desc_list->is_valid = FAIL;			
			(*num_desc_list)++;
			p_desc_list++;

			p_desc_list->pm_id = PID_SPECIAL_STAINS_SET;
			p_devStatus->special_stains_value = VOID_BLOTTED;				//�Ա�����õ�4.25��
			p_desc_list->is_valid = FAIL;			
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL)
		{
			p_desc_list->pm_id = PID_HIGH_WATER_LEVL;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_STRONG_WASH;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;
		}

		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL ||p_devStatus->special_stains_value != VOID_BLOTTED ||p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}

		if (p_devStatus->special_stains_value != VOID_BLOTTED ||p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			
			p_desc_list->pm_id = PID_STRONG_WASH;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;

			p_desc_list->pm_id = PID_WASH_TEMPRATURE;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}


		if (p_devStatus->strong_wash == TRUE ||p_devStatus->high_water_lev == TRUE)
		{

			p_desc_list->pm_id = PID_ENERGY_SAVE;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;
			
		}

		
		if (p_devStatus->special_stains_value != VOID_BLOTTED ||p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_RINSING_FREQUENCY;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;

			if (p_devStatus->time_wash != EXIT_TIMEWASH)
			{
				p_desc_list->pm_id = PID_DEHYDRATION_SPEED;
				p_desc_list->fix_param = 0xFFFF;
				p_desc_list->is_valid = TRUE;
				(*num_desc_list)++;
				p_desc_list++;
			}
		}

	}
	else if (p_devStatus->washer_prg == STERILIZE_95_WASH ||p_devStatus->washer_prg == PREVENT_ALERGIC_WASH)
	{
		*num_desc_list = 0;
		
		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->washer_prg == STERILIZE_95_WASH)
		{
			p_desc_list->pm_id = PID_WASH_TEMPRATURE;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL)
		{
			p_desc_list->pm_id = PID_HIGH_WATER_LEVL;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;
			
			/*p_desc_list->pm_id = PID_TIMEWASH_INDEX;
			p_devStatus->time_wash = EXIT_TIMEWASH;						//�Ա�����õ�4.25��
			p_desc_list->is_valid = FAIL;			
			(*num_desc_list)++;
			p_desc_list++;*/
		}
		
		if (p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_RINSING_FREQUENCY;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_DEHYDRATION_SPEED;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}

	}
	else if (p_devStatus->washer_prg == CLEAN_BUKET_WASH)
	{
		*num_desc_list = 0;
		
		if (p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_WASH_TEMPRATURE;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_RINSING_FREQUENCY;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_DEHYDRATION_SPEED;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
	}
	else if (p_devStatus->washer_prg == LINT_TOY_WASH ||p_devStatus->washer_prg == SUPGEN_HAND_WASH)
	{
		*num_desc_list = 0;

		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->special_stains_value != VOID_BLOTTED ||p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_ENERGY_SAVE;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_WASH_TEMPRATURE;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;

			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_RINSING_FREQUENCY;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;

			if (p_devStatus->time_wash != EXIT_TIMEWASH)
			{
				p_desc_list->pm_id = PID_DEHYDRATION_SPEED;
				p_desc_list->fix_param = 0xFFFF;
				p_desc_list->is_valid = TRUE;
				(*num_desc_list)++;
				p_desc_list++;
			}
		}
		
	}
	else if (p_devStatus->washer_prg == ONE_KEY_WASH)
	{
		(*num_desc_list) = 0;
	}
	else
	{
		(*num_desc_list) = 0;
	}

}

unsigned int Check_COMSum(unsigned char *Dest_Buff,unsigned char Length)
{	
	unsigned int Plus_Sum = 0,i;
	
	for(i = 2;i < Length-4; i++)
	{
		Plus_Sum += Dest_Buff[i];
	}
	
	return(Plus_Sum);
}

/**�����ı�ϴ�»�״̬����ͣ��ֹͣ�����У����޸�����������
//      �ݣ�ͯ���ȣ�ʱ�����ô˽ӿڻ�ȡ��Ӧ�Ŀ�������֡������ʱ��
//     ��ϴ�»���ǰ
/ *     ��
/ *    ����״̬��p_devStatus���������õ����cmd����
/ *    ���ϴ�»�����״̬�ᷢ���仯������µ�״̬���µ�p_devStatusָ�����
//     �ݽṹ��, �������������������ԭ����ɺ�������ʧ�ܣ����Է���-1��
//     �ɹ�����ʱҪ����������֡��ֵ��p_cmdBuf�С�ͬʱ������ȸ�ֵ��len������

 *   ��;: ��ȡ����ϴ�»�����״̬��Ӧ��ԭʼ����֡���ݣ�����֡ͷ+����+֡β��
 *   ������mach_modle--ϴ�»��ͺ�
 *         P_devStatus--ϴ�»�����״̬
 *         cmd--ָ����Ҫ���͵Ŀ�������
 *         num_cmd--�������
 *         p_cmdBuf--ԭʼ����֡���棬��Ӧ�ò����룬Э��⸳ֵ
 *         len-����֡���ȣ���Э��⸳ֵ
 *   ����ֵ ��
*/

int wsh_get_cmd_control(WASHER_STAT *p_devStatus,COMMAND *cmd, int num_cmd,unsigned char *p_cmdBuf,int *len)
{
	unsigned char i,j,Temp_Switch_Buff[150];
	int Temp_Send_Sum1;
	
	for(i = 0; i < 150;i++)
		p_cmdBuf[i] = 0;

	if (cmd->cmd_id == SET_RUN_PAUSE)
	{
		if (p_devStatus->child_lock == TRUE) return(2);
			
		if (*(cmd->param) == TRUE)
		{
			p_cmdBuf[16] |= 0x08;
			p_devStatus->run_state = TRUE;
			
			if (p_devStatus->washer_phase == WASHER_IDLE)
			{
				if (p_devStatus->order_time_hour != FAIL)
					p_devStatus->washer_phase = WASHER_ORDERWAIT;
				else if (p_devStatus->pre_wash == TRUE ||p_devStatus->pre_wash2 != FAIL)
					p_devStatus->washer_phase = WASHER_PREWASH;
				else if (p_devStatus->wash_time_setting != FAIL)
					p_devStatus->washer_phase = WASHER_WASH;
				else if (p_devStatus->rinse_frq_setting != FAIL)
					p_devStatus->washer_phase = WASHER_RINSE;
				else
					p_devStatus->washer_phase = WASHER_SPIN;			
			}
		}
		else
		{
			p_cmdBuf[16] &= 0xF7;
			p_devStatus->run_state = FAIL;
		}
		
		p_cmdBuf[16] |= 0x04;
		
	}
	else if (cmd->cmd_id == SET_POWER)
	{
		if (*(cmd->param) == TRUE)
		{
			p_cmdBuf[16] |= 0x02;
			p_devStatus->power_state = TRUE;
		}
		else
		{
			p_cmdBuf[16] &= 0xFD;
			p_devStatus->power_state = FAIL;
		}
		
		p_cmdBuf[16] |= 0x01;
		
	}
	else if (cmd->cmd_id == SET_CHILD_LK)
	{
		if (*(cmd->param) == TRUE)
		{
			p_cmdBuf[32] |= 0x20;
			p_devStatus->child_lock = TRUE;
		}
		else
		{
			p_cmdBuf[32] &= 0xBF;
			p_devStatus->child_lock = FAIL;
		}
		
		p_cmdBuf[32] |= 0x10;		
	}
	else if (cmd->cmd_id == SET_FLASH_PANEL)
	{
		if (*(cmd->param) == TRUE)
		{
			p_cmdBuf[32] |= 0x08;
			p_devStatus->flush_light_panel = TRUE;
		}
		else
		{
			p_cmdBuf[32] &= 0xF7;
			p_devStatus->flush_light_panel = FAIL;
		}
		
		p_cmdBuf[32] |= 0x04;
		
	}
	else if (cmd->cmd_id == SET_CLEAN_STOP)
	{
		if (*(cmd->param) == TRUE)
		{
			p_cmdBuf[32] |= 0x80;
			p_devStatus->clean_stop = TRUE;
		}
		else
		{
			p_cmdBuf[32] &= 0x7F;
			p_devStatus->clean_stop = FAIL;
		}
		
		p_cmdBuf[32] |= 0x40;
	}
	else if (cmd->cmd_id == SET_ANTI_FADE)
	{
		if (*(cmd->param) == TRUE)
		{
			p_cmdBuf[33] |= 0x02;
			p_devStatus->anti_fade = TRUE;
		}
		else
		{
			p_cmdBuf[33] &= 0xFD;
			p_devStatus->anti_fade = FAIL;
		}
		
		p_cmdBuf[33] |= 0x01;
		
	}
	else if (cmd->cmd_id == SET_MUTE)
	{
		if (*(cmd->param) == TRUE)
		{
			p_cmdBuf[33] |= 0x08;
			p_devStatus->mute = TRUE;
		}
		else
		{
			p_cmdBuf[33] &= 0xF7;
			p_devStatus->mute = FAIL;
		}
		
		p_cmdBuf[33] |= 0x04;
		
	}
	else if (cmd->cmd_id == SET_END_SOUND)
	{
		p_cmdBuf[56] = *(cmd->param);
		p_devStatus->prg_end_sound = *(cmd->param);
		p_cmdBuf[56] |= 0x01;
	}

	p_cmdBuf[0] = 0xF4; 
	p_cmdBuf[1] = 0xF5; 
	p_cmdBuf[2] = 0x00; 
	p_cmdBuf[3] = 0x40; 
	p_cmdBuf[4] = 0x47; 
	p_cmdBuf[5] = 0x00; 
	p_cmdBuf[6] = 0x00; 
	p_cmdBuf[7] = 0x09; 
	p_cmdBuf[8] = 0x01; 
	p_cmdBuf[9] = 0xFE; 
	p_cmdBuf[10] = 0x01; 
	p_cmdBuf[11] = 0x00; 
	p_cmdBuf[12] = 0x00; 
	p_cmdBuf[13] = 0x65; 
	p_cmdBuf[14] = 0x00; 
	p_cmdBuf[15] = 0x00; 

	for (i = 16; i<76; i++ )		
	{
		p_cmdBuf[i] += 2;
		p_cmdBuf[i] ^= 0xFF;
	}

	Temp_Send_Sum1 = Check_COMSum(p_cmdBuf,80);

	p_cmdBuf[76] = (unsigned char)(0x00FF & Temp_Send_Sum1 >> 8);
	p_cmdBuf[77] = (unsigned char)(0x00FF & Temp_Send_Sum1);

	for (i = 16, j = 16; i<78; i++ )		
	{
		if (p_cmdBuf[i] == 0xF4)
		{
			Temp_Switch_Buff[j] = p_cmdBuf[i];
			j++;
			Temp_Switch_Buff[j] = 0xF4;
		}
		else
		{
			Temp_Switch_Buff[j] = p_cmdBuf[i];
		}

		j++;
	}

	for (i = 16; i<j; i++ )		
	{
		p_cmdBuf[i] = Temp_Switch_Buff[i];
	}

	p_cmdBuf[j] = 0xF4;
	p_cmdBuf[j+1] = 0xFB;

	*len = j+2;

	 return(0);
		
}

/**����
// *         �л�����ģʽʱ����Ҫ���ô˽ӿڻ�ȡ��Ӧ�Ŀ�������֡��
//            ����ʱ����ϴ�»���ǰ״̬(p_devStatus)����Ҫ�л���ģʽID��prg_id��
// *         ���ϴ�»�����״̬�ᷢ���仯������µ�״̬���µ�p_devStatusָ������ݽṹ�С�
// *         �������������������ԭ����ɺ�������ʧ�ܣ����Է���-1��
//            �ɹ�����ʱҪ����������֡��ֵ��p_cmdBuf�С�ͬʱ������ȸ�ֵ��len������
// */
/**
 *   ��;: ��ȡ�л�ϴ�»�21�ֹ���ģʽ��Ӧ��ԭʼ����֡���ݣ�����֡ͷ+����+֡β��
 *   ������mach_modle--ϴ�»��ͺ�
 *         P_devStatus--ϴ�»�����״̬
 *         prg_id--��Ҫ�л���id
 *         p_cmdBuf--ԭʼ����֡���棬��Ӧ�ò����룬Э��⸳ֵ
 *         len-����֡���ȣ���Э��⸳ֵ
 *   ����ֵ ��
*/
int wsh_cmd_adj_prog(WASHER_STAT *p_devStatus,enum WASH_PRG_ID prg_id,uchar enable,unsigned char *p_cmdBuf,int *len)
{
	unsigned char i,j,Temp_Switch_Buff[150];
	int Temp_Send_Sum1;
	unsigned int Run_DispAll_Times;	
	
	if (p_devStatus->run_state == TRUE) return(6);
	
	for(i = 0; i < 150;i++)
		p_cmdBuf[i] = 0;
	
	p_devStatus->washer_prg = p_cmdBuf[17] = prg_id;
	p_cmdBuf[17] <<= 1;
	p_cmdBuf[17] |= 0x01;

	if (prg_id == ONE_KEY_WASH)
		p_cmdBuf[33] = 0x30;

	if (enable == TRUE)
	{
		p_cmdBuf[16] |= 0x08;
		p_devStatus->run_state = 1;
		p_cmdBuf[16] |= 0x04;		
	}
	
	p_devStatus->ag_degerming = 0;
	p_devStatus->strong_wash = 0;
	p_devStatus->pre_wash = 0;
	p_devStatus->high_water_lev = 0;
	p_devStatus->airing = 0;
	p_devStatus->energy_conserv = 0;
	p_devStatus->spin_rinse = 0;
	p_devStatus->pre_wash2 = 0;
	p_devStatus->plus_rinse = 0;
	p_devStatus->plus_quickly = 0;
	p_devStatus->special_stains_value = VOID_BLOTTED;
	p_devStatus->time_wash = EXIT_TIMEWASH;
	p_devStatus->order_time_hour = 0;				// 添加预约清零
	p_devStatus->order_time_min = 0;
	p_devStatus->function5 = 0;
	p_devStatus->function6 = 0;
	
	p_cmdBuf[0] = 0xF4; 
	p_cmdBuf[1] = 0xF5; 
	p_cmdBuf[2] = 0x00; 
	p_cmdBuf[3] = 0x40; 
	p_cmdBuf[4] = 0x47; 
	p_cmdBuf[5] = 0x00; 
	p_cmdBuf[6] = 0x00; 
	p_cmdBuf[7] = 0x09; 
	p_cmdBuf[8] = 0x01; 
	p_cmdBuf[9] = 0xFE; 
	p_cmdBuf[10] = 0x01; 
	p_cmdBuf[11] = 0x00; 
	p_cmdBuf[12] = 0x00; 
	p_cmdBuf[13] = 0x65; 
	p_cmdBuf[14] = 0x00; 
	p_cmdBuf[15] = 0x00; 

	for (i = 16; i<76; i++ )		
	{
		p_cmdBuf[i] += 2;
		p_cmdBuf[i] ^= 0xFF;
	}

	Temp_Send_Sum1 = Check_COMSum(p_cmdBuf,80);

	p_cmdBuf[76] = (unsigned char)(0x00FF & Temp_Send_Sum1 >> 8);
	p_cmdBuf[77] = (unsigned char)(0x00FF & Temp_Send_Sum1);

	for (i = 16, j = 16; i<78; i++ )		
	{
		if (p_cmdBuf[i] == 0xF4)
		{
			Temp_Switch_Buff[j] = p_cmdBuf[i];
			j++;
			Temp_Switch_Buff[j] = 0xF4;
		}
		else
		{
			Temp_Switch_Buff[j] = p_cmdBuf[i];
		}

		j++;
	}

	for (i = 16; i<j; i++ )		
	{
		p_cmdBuf[i] = Temp_Switch_Buff[i];
	}

	p_cmdBuf[j] = 0xF4;
	p_cmdBuf[j+1] = 0xFB;
	
	*len = j+2;
	
	p_devStatus->work_time_left = p_devStatus->wash_time_setting = Prog_Temp_Washertime[p_devStatus->washer_prg][p_devStatus->wash_temprature_setting];
	
	Run_DispAll_Times = p_devStatus->wash_time_setting+Prog_Temp_Washertime_Plus[p_devStatus->washer_prg]
	+ Prog_Rinse_Softtime[p_devStatus->washer_prg]+Prog_Dehy_Outtime[p_devStatus->washer_prg]+Prog_Dehytime[p_devStatus->washer_prg];

	if (p_devStatus->rinse_frq_setting > RINSE_ONE)
	{
		Run_DispAll_Times += (p_devStatus->rinse_frq_setting - 1)*Prog_Rinsetime[p_devStatus->washer_prg];
	}
	
	if (p_devStatus->energy_conserv == TRUE)		//����
		Run_DispAll_Times -= Prog_Savetime[p_devStatus->washer_prg];
	
	if (p_devStatus->strong_wash == TRUE)		//ǿ��
	{
		if (p_devStatus->washer_prg == COTTON_FIBRE_WASH && p_devStatus->wash_temprature_setting == WATER_60_TEMP)				//���ܳ���
		{
			Run_DispAll_Times += 80;						
		}
		else
			Run_DispAll_Times += Prog_Strongtime[p_devStatus->washer_prg];
	}

	if (p_devStatus->dehydrate_speed_setting == AVOID_SPEED)//����ˮ
		Run_DispAll_Times -= Prog_Dehytime[p_devStatus->washer_prg];

	if (p_devStatus->pre_wash == TRUE)			//����Ԥϴ
		Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];

	if (p_devStatus->washer_prg == PROTECT_BABY_WASH)
	{
		Run_DispAll_Times += 15;						//��Ԥϴ
	}
	
	if (p_devStatus->special_stains_value != VOID_BLOTTED)		//����ʱ��ʱ�䴦�����0�ָ������ȱʡֵ
	{
		
		Run_DispAll_Times -= p_devStatus->wash_time_setting;
			
		if (p_devStatus->washer_prg == COTTON_FIBRE_WASH ||p_devStatus->washer_prg == JEANS_WASH)
		{
			p_devStatus->wash_temprature_setting = Cotton_Jeans_Blotted[p_devStatus->special_stains_value][0];
			p_devStatus->work_time_left = p_devStatus->wash_time_setting = Cotton_Jeans_Blotted[p_devStatus->special_stains_value][1];

			if (p_devStatus->special_stains_value == BLOOD_BLOTTED)
				Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
		}
		else if (p_devStatus->washer_prg == CHEM_FIBER_WASH ||p_devStatus->washer_prg == MIX_WASH)
		{
			p_devStatus->wash_temprature_setting = Chemail_Fiber_Mix_Blotted[p_devStatus->special_stains_value][0];
			p_devStatus->work_time_left = p_devStatus->wash_time_setting = Chemail_Fiber_Mix_Blotted[p_devStatus->special_stains_value][1];
			
			if (p_devStatus->special_stains_value == BLOOD_BLOTTED)
				Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
		}
		else if (p_devStatus->washer_prg == SILK_WASH)
		{
			p_devStatus->wash_temprature_setting = Silk_Blotted[p_devStatus->special_stains_value][0];
			p_devStatus->work_time_left = p_devStatus->wash_time_setting = Silk_Blotted[p_devStatus->special_stains_value][1];
		}
		else if (p_devStatus->washer_prg == UNDERWEAR_WASH ||p_devStatus->washer_prg == BIG_WASH)
		{
			p_devStatus->wash_temprature_setting = Under_Big_Blotted[p_devStatus->special_stains_value][0];
			p_devStatus->work_time_left = p_devStatus->wash_time_setting = Under_Big_Blotted[p_devStatus->special_stains_value][1];
			if (p_devStatus->special_stains_value == BLOOD_BLOTTED)
				Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
		}
		else if (p_devStatus->washer_prg == OUTDOOR_WASH ||p_devStatus->washer_prg == SHIRT_WASH)
		{
			p_devStatus->wash_temprature_setting = Outdoor_Shirt_Blotted[p_devStatus->special_stains_value][0];
			p_devStatus->work_time_left = p_devStatus->wash_time_setting = Outdoor_Shirt_Blotted[p_devStatus->special_stains_value][1];
		}
		else if (p_devStatus->washer_prg == COLOUR_FABRIC_WASH)
		{
			p_devStatus->wash_temprature_setting = Colour_Wear_Blotted[p_devStatus->special_stains_value][0];
			p_devStatus->work_time_left = p_devStatus->wash_time_setting = Colour_Wear_Blotted[p_devStatus->special_stains_value][1];
		}
		else if (p_devStatus->washer_prg == PROTECT_BABY_WASH)
		{
			p_devStatus->wash_temprature_setting = Baby_Nurse_Blotted[p_devStatus->special_stains_value][0];
			p_devStatus->work_time_left = p_devStatus->wash_time_setting = Baby_Nurse_Blotted[p_devStatus->special_stains_value][1];
			Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
		}
		else if (p_devStatus->washer_prg == LINT_TOY_WASH)
		{
			p_devStatus->wash_temprature_setting = Hair_Toy_Blotted[p_devStatus->special_stains_value][0];
			p_devStatus->work_time_left = p_devStatus->wash_time_setting = Hair_Toy_Blotted[p_devStatus->special_stains_value][1];
			Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
		}

		Run_DispAll_Times += p_devStatus->wash_time_setting;

	}
	
	p_devStatus->time_left_h_standby = p_devStatus->time_left_h = (unsigned char)(Run_DispAll_Times>>8 & 0x00FF);///5.24 �ı���0x00FF
	p_devStatus->time_left_l_standby = p_devStatus->time_left_l = (unsigned char)(Run_DispAll_Times & 0x00FF);

	return(0);

}

/**����
 *   ĳ��ģʽ�µ���ĳ������ʱ������øýӿڻ�ȡ��������֡��
//  ����ʱ���뵱ǰϴ�»�����״̬��p_devStatus����Ҫ�����Ĳ���
//��p_param��ʾ���ܹ����Ĳ���������ָ�루p_desc_list��.��������
//Ҫ���ݴ���Ĳ������߼��������ϴ�»�����״̬���ܹ���
//�����ᷢ���仯��������µ�״̬���µ�p_devStatus��p_desc_listָ��
//�����ݽṹ�С��������������������ԭ����ɺ�������ʧ
//�ܣ����Է���-1���ɹ�����ʱҪ����������֡��ֵ��p_cmdBuf�У�
//ͬʱ������ȸ�ֵ��len������
 */ 
/**
 *   ��;: ��ȡ����ϴ�»���Ӧ����ģʽ�²�����ԭʼ����֡���ݣ�����֡ͷ+����+֡β��
 *   ������mach_modle--ϴ�»��ͺ�
 *         P_devStatus--ϴ�»�����״̬
 *         p_param--���õĲ���
 *         num_param--���õĲ���������
 *         p_desc_list--����������
 *         *num_desc_list--��������������
 *         p_cmdBuf--ԭʼ����֡���棬��Ӧ�ò����룬Э��⸳ֵ
 *         len-����֡���ȣ���Э��⸳ֵ
 *   ����ֵ ��
*/

int wsh_cmd_adj_param(WASHER_STAT *p_devStatus,PARAM *p_param,int num_param,PARAM_ITEM_DESC *p_desc_list,int *num_desc_list, unsigned char *p_cmdBuf,int *len)
{
	unsigned char i,j,Temp_Switch_Buff[150];
	int Temp_Send_Sum1;
	unsigned int Run_DispAll_Times;	
	
	PARAM_ITEM_DESC *p_desc_list_Temp;
		
	for(i = 0; i < 150;i++)
		p_cmdBuf[i] = 0;
		
	p_devStatus->order_time_hour = 0;				// 添加预约清零
	p_devStatus->order_time_min = 0;

	if (p_param ->pm_id == PID_WASH_TIME)
	{
		p_devStatus->work_time_left = p_devStatus->wash_time_setting = p_cmdBuf[27]= *(p_param ->param);
		p_cmdBuf[27] <<= 1;
		p_cmdBuf[27] |= 0x01;	
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_RINSING_FREQUENCY)
	{
		p_devStatus->rinse_num_left = p_devStatus->rinse_frq_setting = p_cmdBuf[28]= *(p_param ->param);
		p_cmdBuf[28] <<= 1;
		p_cmdBuf[28] |= 0x01;
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_WASH_TEMPRATURE)
	{
		p_devStatus->wash_temprature_setting = p_cmdBuf[19]= *(p_param ->param);
		
		if (p_devStatus->washer_prg == COTTON_FIBRE_WASH ||p_devStatus->washer_prg == SUPER_QUICKLY_WASH)					// 4.27 gai
			p_devStatus->work_time_left = p_devStatus->wash_time_setting = Prog_Temp_Washertime[p_devStatus->washer_prg][p_cmdBuf[19]];
		p_cmdBuf[19] <<= 1;
		p_cmdBuf[19] |= 0x01;
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_DEHYDRATION_SPEED)
	{
		p_devStatus->dehydrate_speed_setting = p_cmdBuf[18]= *(p_param ->param);		
		p_cmdBuf[18] <<= 1;
		p_cmdBuf[18] |= 0x01;
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_PRE_WASH)
	{
		p_devStatus->pre_wash = *(p_param ->param);
		
		if (p_devStatus->pre_wash == TRUE)
		{
			p_cmdBuf[21] = 0;
			p_cmdBuf[21] |= 0x03;
			p_devStatus->energy_conserv = FAIL;
			p_devStatus->special_stains_value = VOID_BLOTTED;
			p_devStatus->time_wash = EXIT_TIMEWASH;
		}
		else
		{
			p_cmdBuf[21] &= 0xFD;
			p_cmdBuf[21] |= 0x01;
		}

		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
			
	}
	else if (p_param ->pm_id == PID_ENERGY_SAVE)
	{
		p_devStatus->energy_conserv = *(p_param ->param);
		
		if (p_devStatus->energy_conserv == TRUE)
		{
			p_devStatus->strong_wash =FAIL;   //fun   
			p_devStatus->pre_wash = FAIL;	      //fun
			p_devStatus->pre_wash2 = FAIL;
			p_devStatus->high_water_lev =FAIL;//fun
			p_devStatus->time_wash = EXIT_TIMEWASH;
			p_devStatus->special_stains_value = VOID_BLOTTED;

			p_cmdBuf[20] = 0;		//Ԥϴ����ˮλ��ǿ��
			p_cmdBuf[20] |= 0x0C;
		}
		else
		{
			p_cmdBuf[20] &= 0xF7;
			p_cmdBuf[20] |= 0x04;
		}
		
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
		
	}
	else if (p_param ->pm_id == PID_STRONG_WASH)
	{
		p_devStatus->strong_wash = *(p_param ->param);
		
		if (p_devStatus->strong_wash == TRUE)
		{
			p_cmdBuf[21] |= 0x0C;
			p_devStatus->energy_conserv = FAIL;   //fun   
			p_devStatus->time_wash = EXIT_TIMEWASH;
			p_devStatus->special_stains_value = VOID_BLOTTED;
		}
		else
		{
			p_cmdBuf[21] &= 0xF7;
			p_cmdBuf[21] |= 0x04;
		}
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
		
	}
	else if (p_param ->pm_id == PID_SPECIAL_STAINS_SET)
	{
		p_devStatus->special_stains_value = p_cmdBuf[37] = *(p_param ->param);


		if (p_devStatus->special_stains_value != VOID_BLOTTED)		///5.6 �ĳ��򻥳�
		{
			p_devStatus->strong_wash = FAIL;   //fun   
			p_devStatus->pre_wash = FAIL;	      //fun
			p_devStatus->pre_wash2 = FAIL;
			p_devStatus->energy_conserv = FAIL;//fun
			p_devStatus->time_wash = EXIT_TIMEWASH;
			p_devStatus->rinse_num_left = p_devStatus->rinse_frq_setting = Rinse_times[p_devStatus->washer_prg];
		}
		else
		{
			p_devStatus->wash_temprature_setting = Prog_default_tempreture[p_devStatus->washer_prg];			//5.11���������պ��ֹر����ջظ�ʱ��
			p_devStatus->work_time_left = p_devStatus->wash_time_setting = Prog_default_washtime[p_devStatus->washer_prg];
		}
		
		p_cmdBuf[37] <<= 1;
		p_cmdBuf[37] |= 0x01;

		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);

	}
	else if (p_param ->pm_id == PID_HIGH_WATER_LEVL)
	{
		*num_desc_list = 0;
		
		p_devStatus->high_water_lev = *(p_param ->param);

		if (p_devStatus->high_water_lev == TRUE)
		{
			p_cmdBuf[20] |= 0xC0;
			
		}
		else
		{
			p_cmdBuf[20] &= 0x7F;
			p_cmdBuf[20] |= 0x40;
			
		}

		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
		
	}
	else if (p_param ->pm_id == PID_AIRING)
	{	
		p_devStatus->airing = *(p_param ->param);

		if (p_devStatus->airing == TRUE)
		{
			p_cmdBuf[20] |= 0x30;
		}
		else
		{
			p_cmdBuf[20] &= 0xDF;
			p_cmdBuf[20] |= 0x10;
		}

		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
					
	}
	else if (p_param ->pm_id == PID_SPIN_RINSE)
	{
		p_devStatus->spin_rinse = *(p_param ->param);

		if (p_devStatus->spin_rinse == TRUE)
		{
			p_cmdBuf[20] |= 0x03;
		}
		else
		{
			p_cmdBuf[20] &= 0xFD;
			p_cmdBuf[20] |= 0x01;
		}
		
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
		
	}
	else if (p_param ->pm_id == PID_AG_DEGERMING)
	{	
		p_devStatus->ag_degerming = *(p_param ->param);
		if (p_devStatus->ag_degerming == TRUE)
		{
			p_cmdBuf[21] |= 0x30;
		}
		else
		{
			p_cmdBuf[21] &= 0xDF;
			p_cmdBuf[21] |= 0x10;
		}
		
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
		
	}
	else if (p_param ->pm_id == PID_SOFTEN)
	{
		p_devStatus->use_softener = *(p_param ->param);
		
		if (p_devStatus->use_softener == TRUE)
		{
			p_cmdBuf[22] |= 0xC0;
			p_devStatus->soften_setting = PUT_AUTO;		///5.5 ��Ӧ���µ�Ͷ�Ź���
		}
		else
		{
			p_cmdBuf[22] &= 0x7F;
			p_cmdBuf[22] |= 0x40;
			//p_devStatus->soften_setting = PUT_OFF;		///5.5 ��Ӧ���µ�Ͷ�Ź���
		}

		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
		
	}
	else if (p_param ->pm_id == PID_DRYING)
	{
		p_devStatus->dry = *(p_param ->param);
		
		if (p_devStatus->dry == TRUE)
		{
			p_cmdBuf[22] |= 0x30;
		}
		else
		{
			p_cmdBuf[22] &= 0xDF;
			p_cmdBuf[22] |= 0x20;
		}

		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
		
	}
	else if (p_param ->pm_id == PID_BRIGHTNESS)
	{	
		p_devStatus->brightness_setting_1 = *(p_param ->param);
		
		if (p_devStatus->brightness_setting_1 == TRUE)
		{
			p_cmdBuf[22] |= 0x0C;
		}
		else
		{
			p_cmdBuf[22] &= 0xF7;
			p_cmdBuf[22] |= 0x04;
		}

		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
		
	}
	else if (p_param ->pm_id == PID_WRINKLE_SOAK)
	{
		p_devStatus->crease_immersion = *(p_param ->param);
		
		if (p_devStatus->crease_immersion == TRUE)
		{
			p_cmdBuf[22] |= 0x03;
		}
		else
		{
			p_cmdBuf[22] &= 0xFD;
			p_cmdBuf[22] |= 0x02;
		}

		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
		
	}
	else if (p_param ->pm_id == PID_HARDNESS)
	{

		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);

	}
	else if (p_param ->pm_id == PID_USE_DETERGENT)
	{
		p_devStatus->use_detergent = *(p_param ->param);
				
		if (p_devStatus->use_detergent == TRUE)
		{
			p_cmdBuf[23] |= 0x03;
			p_devStatus->detergent_setting = PUT_MIDDLE;			///5.5 ��Ӧ���µ�Ͷ�Ź���
		}
		else
		{
			p_cmdBuf[23] &= 0xFD;
			p_cmdBuf[23] |= 0x01;								///5.3 �ĳ���(�ز���)����
			//p_devStatus->detergent_setting = PUT_OFF;			///5.5 ��Ӧ���µ�Ͷ�Ź���
		}
		
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);

	}
	else if (p_param ->pm_id == PID_PRE_WASH2)	//3.7add
	{
		p_devStatus->pre_wash2 = p_cmdBuf[24] = *(p_param ->param);
		p_cmdBuf[24] <<= 1;
		p_cmdBuf[24] |= 1;

		if (p_devStatus->pre_wash2 != FAIL)
		{
			p_devStatus->energy_conserv = FAIL;
			p_devStatus->special_stains_value = VOID_BLOTTED;
			p_devStatus->time_wash = EXIT_TIMEWASH;
		}

		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);

	}
	else if (p_param ->pm_id == PID_ORDER_HOURS)
	{
		PARAM *p_param_1;
			
		#if (1)
		Run_DispAll_Times = p_devStatus->wash_time_setting+Prog_Temp_Washertime_Plus[p_devStatus->washer_prg]
		+ Prog_Rinse_Softtime[p_devStatus->washer_prg]+Prog_Dehy_Outtime[p_devStatus->washer_prg]+Prog_Dehytime[p_devStatus->washer_prg];

		if (p_devStatus->rinse_frq_setting >= RINSE_ONE)
		{
			Run_DispAll_Times += (p_devStatus->rinse_frq_setting + p_devStatus->plus_rinse - 1)*Prog_Rinsetime[p_devStatus->washer_prg];
		}																															//3.7add
		
		if (p_devStatus->energy_conserv == TRUE)		//����
			Run_DispAll_Times -= Prog_Savetime[p_devStatus->washer_prg];

		if (p_devStatus->plus_quickly != FAIL)	//3.7add
		{
			Run_DispAll_Times -= Plusquickly_time[p_devStatus->plus_quickly];
		}
		
		if (p_devStatus->strong_wash == TRUE ||p_devStatus->function5 == FUNCTION5_STRONG)			//ǿ��
		{
			if (p_devStatus->washer_prg == COTTON_FIBRE_WASH && p_devStatus->wash_temprature_setting == WATER_60_TEMP)				//���ܳ���
			{
				Run_DispAll_Times += 80;						
			}
			else
				Run_DispAll_Times += Prog_Strongtime[p_devStatus->washer_prg];
		}

		if (p_devStatus->dehydrate_speed_setting == AVOID_SPEED)//����ˮ
			Run_DispAll_Times -= Prog_Dehytime[p_devStatus->washer_prg];

		if (p_devStatus->pre_wash == TRUE)			//����Ԥϴ
			Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
		else if (p_devStatus->pre_wash2 != FAIL)//3.7add
			Run_DispAll_Times += Prewash2_time[p_devStatus->pre_wash2];
		else if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
		}
		
		if (p_devStatus->washer_prg == PROTECT_BABY_WASH)
		{
			Run_DispAll_Times += 15;
		}

		if (p_devStatus->special_stains_value != VOID_BLOTTED)		//����ʱ��ʱ�䴦�����0�ָ������ȱʡֵ
		{
			
			Run_DispAll_Times -= p_devStatus->wash_time_setting;
				
			if (p_devStatus->washer_prg == COTTON_FIBRE_WASH ||p_devStatus->washer_prg == JEANS_WASH)
			{
				p_devStatus->wash_temprature_setting = Cotton_Jeans_Blotted[p_devStatus->special_stains_value][0];
				p_devStatus->work_time_left = p_devStatus->wash_time_setting = Cotton_Jeans_Blotted[p_devStatus->special_stains_value][1];

				if (p_devStatus->special_stains_value == BLOOD_BLOTTED)
					Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
			}
			else if (p_devStatus->washer_prg == CHEM_FIBER_WASH ||p_devStatus->washer_prg == MIX_WASH)
			{
				p_devStatus->wash_temprature_setting = Chemail_Fiber_Mix_Blotted[p_devStatus->special_stains_value][0];
				p_devStatus->work_time_left = p_devStatus->wash_time_setting = Chemail_Fiber_Mix_Blotted[p_devStatus->special_stains_value][1];
				
				if (p_devStatus->special_stains_value == BLOOD_BLOTTED)
					Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
			}
			else if (p_devStatus->washer_prg == SILK_WASH)
			{
				p_devStatus->wash_temprature_setting = Silk_Blotted[p_devStatus->special_stains_value][0];
				p_devStatus->work_time_left = p_devStatus->wash_time_setting = Silk_Blotted[p_devStatus->special_stains_value][1];
			}
			else if (p_devStatus->washer_prg == UNDERWEAR_WASH ||p_devStatus->washer_prg == BIG_WASH)
			{
				p_devStatus->wash_temprature_setting = Under_Big_Blotted[p_devStatus->special_stains_value][0];
				p_devStatus->work_time_left = p_devStatus->wash_time_setting = Under_Big_Blotted[p_devStatus->special_stains_value][1];
				if (p_devStatus->special_stains_value == BLOOD_BLOTTED)
					Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
			}
			else if (p_devStatus->washer_prg == OUTDOOR_WASH ||p_devStatus->washer_prg == SHIRT_WASH)
			{
				p_devStatus->wash_temprature_setting = Outdoor_Shirt_Blotted[p_devStatus->special_stains_value][0];
				p_devStatus->work_time_left = p_devStatus->wash_time_setting = Outdoor_Shirt_Blotted[p_devStatus->special_stains_value][1];
			}
			else if (p_devStatus->washer_prg == COLOUR_FABRIC_WASH)
			{
				p_devStatus->wash_temprature_setting = Colour_Wear_Blotted[p_devStatus->special_stains_value][0];
				p_devStatus->work_time_left = p_devStatus->wash_time_setting = Colour_Wear_Blotted[p_devStatus->special_stains_value][1];
			}
			else if (p_devStatus->washer_prg == PROTECT_BABY_WASH)
			{
				p_devStatus->wash_temprature_setting = Baby_Nurse_Blotted[p_devStatus->special_stains_value][0];
				p_devStatus->work_time_left = p_devStatus->wash_time_setting = Baby_Nurse_Blotted[p_devStatus->special_stains_value][1];
				Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
			}
			else if (p_devStatus->washer_prg == LINT_TOY_WASH)
			{
				p_devStatus->wash_temprature_setting = Hair_Toy_Blotted[p_devStatus->special_stains_value][0];
				p_devStatus->work_time_left = p_devStatus->wash_time_setting = Hair_Toy_Blotted[p_devStatus->special_stains_value][1];
				Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
			}

			Run_DispAll_Times += p_devStatus->wash_time_setting;

		}
	
		#endif
		
		if ((*(p_param ->param)) *60 > Run_DispAll_Times)
		{
			
			p_cmdBuf[25] = p_devStatus->order_hours_left = p_devStatus->order_time_hour = *(p_param ->param);
			p_cmdBuf[25] <<= 1;
			p_cmdBuf[25] |= 0x01;
			p_param_1 = p_param;
			
			p_param_1++;
			
			p_cmdBuf[26] = p_devStatus->order_min_left = p_devStatus->order_time_min = *(p_param_1 ->param);
			p_cmdBuf[26] <<= 1;
			p_cmdBuf[26] |= 0x01;

			Run_DispAll_Times = p_devStatus->order_time_hour *60;

			p_devStatus->time_left_h_standby = p_devStatus->time_left_h = (unsigned char)(Run_DispAll_Times>>8 & 0x00FF);///5.24 �ı���0x00FF
			p_devStatus->time_left_l_standby = p_devStatus->time_left_l = (unsigned char)(Run_DispAll_Times & 0x00FF);
			
			Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
		}
		else
		{
			if ((*(p_param ->param)) != NO_ORDER)
				return (3);
			else
			{
				p_cmdBuf[25] = p_devStatus->order_hours_left = p_devStatus->order_time_hour = *(p_param ->param);
				p_cmdBuf[25] <<= 1;
				p_cmdBuf[25] |= 0x01;
				p_param_1 = p_param;
				
				p_param_1++;
				
				p_cmdBuf[26] = p_devStatus->order_min_left = p_devStatus->order_time_min = *(p_param_1 ->param);
				p_cmdBuf[26] <<= 1;
				p_cmdBuf[26] |= 0x01;

				Run_DispAll_Times = p_devStatus->order_time_hour *60;

				p_devStatus->time_left_h_standby = p_devStatus->time_left_h = (unsigned char)(Run_DispAll_Times>>8 & 0x00FF);///5.24 �ı���0x00FF
				p_devStatus->time_left_l_standby = p_devStatus->time_left_l = (unsigned char)(Run_DispAll_Times & 0x00FF);
				
				Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
			}
		}
		
	}
	else if (p_param ->pm_id == PID_SOFTENER_FREQUENCY)
	{
		p_cmdBuf[29] = p_devStatus->soften_setting = *(p_param ->param);		 
		p_cmdBuf[29] <<= 1;
		p_cmdBuf[29] |= 0x01;

		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_DETERGENT_FREQUENCY)
	{
		p_cmdBuf[30] = p_devStatus->detergent_setting = *(p_param ->param);		 
		p_cmdBuf[30] <<= 1;
		p_cmdBuf[30] |= 0x01;

		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
		
	}
	else if (p_param ->pm_id == PID_TIMEWASH_INDEX)	
	{
		p_cmdBuf[59] = p_devStatus->time_wash = *(p_param ->param);		 
		p_cmdBuf[59] <<= 1;
		p_cmdBuf[59] |= 0x01;


		if (p_devStatus->time_wash != EXIT_TIMEWASH)			///5.6 �ĳ��򻥳�
		{
			p_devStatus->strong_wash =FAIL;   //fun   		///5.3 �ĳ�������
			p_devStatus->pre_wash =FAIL;	      //fun
			p_devStatus->pre_wash2 = FAIL;

			p_devStatus->energy_conserv = FAIL;//fun
			p_devStatus->special_stains_value = VOID_BLOTTED;		///5.6�����޸�
		}

		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
		
	}
	else if (p_param ->pm_id == PID_PEAK_ENERGY)
	{
	}
	else if (p_param ->pm_id == PID_SMART_WASH)
	{
	}
	else if (p_param ->pm_id == PID_SCREEN_VOLUME)
	{
	}
	else if (p_param ->pm_id == PID_SCREEN_BRIGHTNESS)
	{
	}
	else if (p_param ->pm_id == PID_BREATHING_LIGHT)
	{
		p_devStatus->breathing_light = p_cmdBuf[36] = *(p_param ->param);
		p_cmdBuf[36] <<= 1;
		p_cmdBuf[36] |= 1;
		
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
		
	}
	else if (p_param ->pm_id == PID_YEAR_HIGH)
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);

	}
	else if (p_param ->pm_id == PID_YEAR_LOW)
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_MONTH)
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_DAY)
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_HOUR)
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_MINUTE)
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_WEEK)
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_FUNCTION5)
	{
		if (p_devStatus->plus_quickly != PLUS_QUICK_0 && (*(p_param ->param) == FUNCTION5_PREWASH 
			||*(p_param ->param) == FUNCTION5_STRONG))
			return(5);			
			
		p_devStatus->function5 = p_cmdBuf[45] = *(p_param ->param);
		p_cmdBuf[45] <<= 1;
		p_cmdBuf[45] |= 1;
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_FUNCTION6)
	{		
		if (p_devStatus->plus_quickly != PLUS_QUICK_0 && (*(p_param ->param) == FUNCTION6_PREWASH))
			return(5);
		
		if (p_devStatus->use_detergent != TRUE && (*(p_param ->param) == FUNCTION6_MORE_DIRTY ||*(p_param ->param) == FUNCTION6_FEW_DIRTY))
			return(4);														//6.1�����жϿ�����ִ�������ٵ�����
		
		p_devStatus->function6 = p_cmdBuf[46] = *(p_param ->param);
		p_cmdBuf[46] <<= 1;
		p_cmdBuf[46] |= 1;
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_DRY)	
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_DRY1)	
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_WEATHER_TEMPRATURE_0)	
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_WEATHER_TEMPRATURE_1)	
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_HUMIDITY)	
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_ULTRA_VOILET_INDEX)	
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_WEATHER)	
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_RESET_STATE)	
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_START_SOUND)	
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_END_SOUND)	
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_PAUSE_SOUND)	
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_ALARM_SOUND)	
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_SOAKING_TIME)	
	{
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_PLUS_QUICKLY)	// 3.7add
	{
		p_devStatus->plus_quickly = p_cmdBuf[61] = *(p_param ->param);
		
		if (p_devStatus->plus_quickly != FAIL)
		{
			p_devStatus->strong_wash =FAIL;   //fun   
			p_devStatus->pre_wash = FAIL;	      //fun
			p_devStatus->pre_wash2 = FAIL;
			p_devStatus->high_water_lev =FAIL;//fun
			p_devStatus->time_wash = EXIT_TIMEWASH;
			p_devStatus->special_stains_value = VOID_BLOTTED;
		}
		
		p_cmdBuf[61] <<= 1;
		p_cmdBuf[61] |= 1;
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	else if (p_param ->pm_id == PID_PLUS_RINSE)	// 3.7add
	{
		p_devStatus->plus_rinse = p_cmdBuf[62] = *(p_param ->param);
		p_cmdBuf[62] <<= 1;
		p_cmdBuf[62] |= 1;
		Huchi_Proc(p_devStatus,p_desc_list,num_desc_list);
	}
	
	if (p_param ->pm_id != PID_ORDER_HOURS ||p_devStatus->order_time_hour == NO_ORDER)
	{
		if (p_param ->pm_id != PID_TIMEWASH_INDEX  
			||(p_param ->pm_id == PID_TIMEWASH_INDEX  && (*(p_param ->param) == EXIT_TIMEWASH)))
		{
			
			Run_DispAll_Times = p_devStatus->wash_time_setting+Prog_Temp_Washertime_Plus[p_devStatus->washer_prg]
			+ Prog_Rinse_Softtime[p_devStatus->washer_prg]+Prog_Dehy_Outtime[p_devStatus->washer_prg]+Prog_Dehytime[p_devStatus->washer_prg];

			if (p_devStatus->rinse_frq_setting >= RINSE_ONE)
			{
				Run_DispAll_Times += (p_devStatus->rinse_frq_setting + p_devStatus->plus_rinse - 1)*Prog_Rinsetime[p_devStatus->washer_prg];
			}																															//3.7add
			
			if (p_devStatus->energy_conserv == TRUE)		//����
				Run_DispAll_Times -= Prog_Savetime[p_devStatus->washer_prg];

			if (p_devStatus->plus_quickly != FAIL)	      // 3.7add
			{
				Run_DispAll_Times -= Plusquickly_time[p_devStatus->plus_quickly];
			}
			
			if (p_devStatus->strong_wash == TRUE ||p_devStatus->function5 == FUNCTION5_STRONG)			//ǿ��
			{
				if (p_devStatus->washer_prg == COTTON_FIBRE_WASH && p_devStatus->wash_temprature_setting == WATER_60_TEMP)				//���ܳ���
				{
					Run_DispAll_Times += 80;						
				}
				else
					Run_DispAll_Times += Prog_Strongtime[p_devStatus->washer_prg];
			}

			if (p_devStatus->dehydrate_speed_setting == AVOID_SPEED)//����ˮ
				Run_DispAll_Times -= Prog_Dehytime[p_devStatus->washer_prg];

			if (p_devStatus->pre_wash == TRUE)			//����Ԥϴ
				Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
			else if (p_devStatus->pre_wash2 != FAIL)       // 3.7add
				Run_DispAll_Times += Prewash2_time[p_devStatus->pre_wash2]; 
			else if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function6 == FUNCTION6_PREWASH)
			{
				Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
			}
			
			if (p_devStatus->washer_prg == PROTECT_BABY_WASH)
			{
				Run_DispAll_Times += 15;
			}

			if (p_devStatus->special_stains_value != VOID_BLOTTED)		//����ʱ��ʱ�䴦�����0�ָ������ȱʡֵ
			{
				
				Run_DispAll_Times -= p_devStatus->wash_time_setting;
					
				if (p_devStatus->washer_prg == COTTON_FIBRE_WASH ||p_devStatus->washer_prg == JEANS_WASH)
				{
					p_devStatus->wash_temprature_setting = Cotton_Jeans_Blotted[p_devStatus->special_stains_value][0];
					p_devStatus->work_time_left = p_devStatus->wash_time_setting = Cotton_Jeans_Blotted[p_devStatus->special_stains_value][1];

					if (p_devStatus->special_stains_value == BLOOD_BLOTTED)
						Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
				}
				else if (p_devStatus->washer_prg == CHEM_FIBER_WASH ||p_devStatus->washer_prg == MIX_WASH)
				{
					p_devStatus->wash_temprature_setting = Chemail_Fiber_Mix_Blotted[p_devStatus->special_stains_value][0];
					p_devStatus->work_time_left = p_devStatus->wash_time_setting = Chemail_Fiber_Mix_Blotted[p_devStatus->special_stains_value][1];
					
					if (p_devStatus->special_stains_value == BLOOD_BLOTTED)
						Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
				}
				else if (p_devStatus->washer_prg == SILK_WASH)
				{
					p_devStatus->wash_temprature_setting = Silk_Blotted[p_devStatus->special_stains_value][0];
					p_devStatus->work_time_left = p_devStatus->wash_time_setting = Silk_Blotted[p_devStatus->special_stains_value][1];
				}
				else if (p_devStatus->washer_prg == UNDERWEAR_WASH ||p_devStatus->washer_prg == BIG_WASH)
				{
					p_devStatus->wash_temprature_setting = Under_Big_Blotted[p_devStatus->special_stains_value][0];
					p_devStatus->work_time_left = p_devStatus->wash_time_setting = Under_Big_Blotted[p_devStatus->special_stains_value][1];
					if (p_devStatus->special_stains_value == BLOOD_BLOTTED)
						Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
				}
				else if (p_devStatus->washer_prg == OUTDOOR_WASH ||p_devStatus->washer_prg == SHIRT_WASH)
				{
					p_devStatus->wash_temprature_setting = Outdoor_Shirt_Blotted[p_devStatus->special_stains_value][0];
					p_devStatus->work_time_left = p_devStatus->wash_time_setting = Outdoor_Shirt_Blotted[p_devStatus->special_stains_value][1];
				}
				else if (p_devStatus->washer_prg == COLOUR_FABRIC_WASH)
				{
					p_devStatus->wash_temprature_setting = Colour_Wear_Blotted[p_devStatus->special_stains_value][0];
					p_devStatus->work_time_left = p_devStatus->wash_time_setting = Colour_Wear_Blotted[p_devStatus->special_stains_value][1];
				}
				else if (p_devStatus->washer_prg == PROTECT_BABY_WASH)
				{
					p_devStatus->wash_temprature_setting = Baby_Nurse_Blotted[p_devStatus->special_stains_value][0];
					p_devStatus->work_time_left = p_devStatus->wash_time_setting = Baby_Nurse_Blotted[p_devStatus->special_stains_value][1];
					Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
				}
				else if (p_devStatus->washer_prg == LINT_TOY_WASH)
				{
					p_devStatus->wash_temprature_setting = Hair_Toy_Blotted[p_devStatus->special_stains_value][0];
					p_devStatus->work_time_left = p_devStatus->wash_time_setting = Hair_Toy_Blotted[p_devStatus->special_stains_value][1];
					Run_DispAll_Times += Prog_Pretime[p_devStatus->washer_prg];
				}

				Run_DispAll_Times += p_devStatus->wash_time_setting;

			}
		}
		else					//ʱ��ϴ����
		{

			Run_DispAll_Times = Timewash_TotalTime[p_devStatus->washer_prg][p_devStatus->time_wash];
			p_devStatus->work_time_left = p_devStatus->wash_time_setting = Timewash_Washtime[p_devStatus->washer_prg][p_devStatus->time_wash];
			///5.18 �ģ�ȥ��p_cmdBuf[27]�ĸ�ֵ�������·�ֻ��Ҫˢ��APP״̬
		}
	
		p_devStatus->time_left_h_standby = p_devStatus->time_left_h = (unsigned char)(Run_DispAll_Times>>8 & 0x00FF);		///5.24 �ı���0x00FF
		p_devStatus->time_left_l_standby = p_devStatus->time_left_l = (unsigned char)(Run_DispAll_Times & 0x00FF);
	}

	p_cmdBuf[0] = 0xF4; 
	p_cmdBuf[1] = 0xF5; 
	p_cmdBuf[2] = 0x00; 
	p_cmdBuf[3] = 0x40; 
	p_cmdBuf[4] = 0x47; 
	p_cmdBuf[5] = 0x00; 
	p_cmdBuf[6] = 0x00; 
	p_cmdBuf[7] = 0x09; 
	p_cmdBuf[8] = 0x01; 
	p_cmdBuf[9] = 0xFE; 
	p_cmdBuf[10] = 0x01; 
	p_cmdBuf[11] = 0x00; 
	p_cmdBuf[12] = 0x00; 
	p_cmdBuf[13] = 0x65; 
	p_cmdBuf[14] = 0x00; 
	p_cmdBuf[15] = 0x00; 

	for (i = 16; i<76; i++ )		
	{
		p_cmdBuf[i] += 2;
		p_cmdBuf[i] ^= 0xFF;
	}

	Temp_Send_Sum1 = Check_COMSum(p_cmdBuf,80);

	p_cmdBuf[76] = (unsigned char)(0x00FF & Temp_Send_Sum1 >> 8);
	p_cmdBuf[77] = (unsigned char)(0x00FF & Temp_Send_Sum1);

	for (i = 16, j = 16; i<78; i++ )		
	{
		if (p_cmdBuf[i] == 0xF4)
		{
			Temp_Switch_Buff[j] = p_cmdBuf[i];
			j++;
			Temp_Switch_Buff[j] = 0xF4;
		}
		else
		{
			Temp_Switch_Buff[j] = p_cmdBuf[i];
		}

		j++;
	}

	for (i = 16; i<j; i++ )		
	{
		p_cmdBuf[i] = Temp_Switch_Buff[i];
	}

	p_cmdBuf[j] = 0xF4;
	p_cmdBuf[j+1] = 0xFB;
	
	*len = j+2;

	return(0);
	 
}

void status_return_buf(WASHER_STAT *p_devStatus,int *pstate_buf,int *pstate_buf_len)
{
	unsigned char temp_value = 0;
	// 1 	
	pstate_buf[0] = p_devStatus->version_h;
	// 2	
	pstate_buf[1] = p_devStatus->version_l;
	// 3
	temp_value = p_devStatus->fading;
	pstate_buf[2] |= temp_value << 7;
	temp_value = 0;
	temp_value = p_devStatus->elimate_foam;
	pstate_buf[2] |= temp_value << 6;
	temp_value = 0;
	temp_value = p_devStatus->auto_rising;
	pstate_buf[2] |= temp_value << 5;
	temp_value = 0;
	temp_value = p_devStatus->imbalance;
	pstate_buf[2] |= temp_value << 4;
	temp_value = 0;
	temp_value = p_devStatus->gate_lock;
	pstate_buf[2] |= temp_value << 3;
	temp_value = 0;
	temp_value = p_devStatus->run_state;
	pstate_buf[2] |= temp_value << 1;
	temp_value = 0;
	temp_value = p_devStatus->power_state;
	pstate_buf[2] |= temp_value;
			
	// 4
	pstate_buf[3] = p_devStatus->function6;
//	p_devStatus->time_wash_onoff = (p_stateBuf[19]>>1) & 0x01;
	// 5
	pstate_buf[4] = p_devStatus->washer_phase;
	// 6
	pstate_buf[5] = p_devStatus->washer_prg;
	// 7
	pstate_buf[6] = p_devStatus->moto_speed_h; 
	// 8
	pstate_buf[7] = p_devStatus->moto_speed_l;
	// 9
	pstate_buf[8] = p_devStatus->washer_temprature;
	// 10
	temp_value = 0;
	temp_value = p_devStatus->add_in_midway;
	pstate_buf[9] |= temp_value << 2;
	// 11
	pstate_buf[10] = p_devStatus->soften_setting;
	// 12
	pstate_buf[11] = p_devStatus->order_hours_left; 
	// 13
	pstate_buf[12] = p_devStatus->order_min_left;	
	// 14
	//p_devStatus->rev14 = ;
	pstate_buf[13] = p_devStatus->washer_fault_info;// & 0x1F;			5.25 �Ͳ���ͳһ����������
	// 15
	pstate_buf[14] = p_devStatus->time_left_h;
	// 16
	pstate_buf[15] = p_devStatus->time_left_l;
	// 17
	pstate_buf[16] = p_devStatus->child_observer;
	// 18
	pstate_buf[17] = p_devStatus->detergent_setting;
	// 19
	pstate_buf[18] = p_devStatus->dehydrate_speed_max;
	// 20
	pstate_buf[19] = p_devStatus->dehydrate_speed_min;
	// 21
	pstate_buf[20] = p_devStatus->work_time_left;
	// 22
	pstate_buf[21] = p_devStatus->rinse_num_left;
	// 23
	pstate_buf[22] = p_devStatus->dehydrate_time_left;
	// 24
	pstate_buf[23] = p_devStatus->dehydrate_speed_setting;
	// 25
	pstate_buf[24] = p_devStatus->wash_temprature_setting;
	// 26
	temp_value = 0;
	temp_value = p_devStatus->ag_degerming;
	pstate_buf[25] |= temp_value << 7;
	
	temp_value = 0;
	temp_value = p_devStatus->strong_wash;
	pstate_buf[25] |= temp_value << 6;
	
	temp_value = 0;
	temp_value = p_devStatus->pre_wash;
	pstate_buf[25] |= temp_value << 5;

	temp_value = 0;
	temp_value = p_devStatus->high_water_lev;
	pstate_buf[25] |= temp_value << 4;

	temp_value = 0;
	temp_value = p_devStatus->airing;
	pstate_buf[25] |= temp_value << 3;

	temp_value = 0;
	temp_value = p_devStatus->energy_conserv;
	pstate_buf[25] |= temp_value << 2;

	temp_value = 0;
	temp_value = p_devStatus->spin_rinse;
	pstate_buf[25] |= temp_value << 1;

	// 27
	temp_value = 0;
	temp_value = p_devStatus->use_detergent;
	pstate_buf[26] |= temp_value << 7;

	temp_value = 0;
	temp_value = p_devStatus->use_softener;
	pstate_buf[26] |= temp_value << 6;

	temp_value = 0;
	temp_value = p_devStatus->dry;
	pstate_buf[26] |= temp_value << 5;

	temp_value = 0;
	temp_value = p_devStatus->crease_immersion;
	pstate_buf[26] |= temp_value << 1;

	// 28
	pstate_buf[27] = p_devStatus->water_lel_frq_h;  //unit:10hz
	// 29
	pstate_buf[28] = p_devStatus->water_lel_frq_l;  //unit:10hz
	// 30
	pstate_buf[29] = p_devStatus->product_mode;
	// 31
	pstate_buf[30] = p_devStatus->indistinct_weight;
	// 32
	pstate_buf[31] = p_devStatus->mainboard_hour_setting;
	// 33
	pstate_buf[32] = p_devStatus->mainboard_min_setting;
	// 34
	pstate_buf[33] = p_devStatus->heating_pipe_time_h;
	// 35
	pstate_buf[34] = p_devStatus->function5;
	// 36
	pstate_buf[35] = p_devStatus->inflow_duration_h;
	// 37
	pstate_buf[36] = p_devStatus->inflow_duration_l;
	// 38
	pstate_buf[37] = p_devStatus->turbidity_ad_value;   //?
	// 39
	pstate_buf[38] = p_devStatus->hardness_ad_value;   //?
	// 40
	pstate_buf[39] = p_devStatus->cloth_texture_value;  //?
	// 41
	pstate_buf[40] = p_devStatus->detergent_left;
	// 42
	pstate_buf[41] = p_devStatus->soften_left;
	// 43
	pstate_buf[42] = p_devStatus->cur_year_h;
	// 44
	pstate_buf[43] = p_devStatus->cur_year_l;
	// 45
	pstate_buf[44] = p_devStatus->cur_month;
	// 46
	pstate_buf[45] = p_devStatus->cur_day;
	// 47
	pstate_buf[46] = p_devStatus->GMT_8_hour;
	// 48
	pstate_buf[47] = p_devStatus->GMT_8_min;
	// 49
	pstate_buf[48] = p_devStatus->on_off_valley_hour;
	// 50
	pstate_buf[49] = p_devStatus->on_off_valley_min;
	// 51
	pstate_buf[50] = p_devStatus->on_off_peak_hour;
	// 52
	pstate_buf[51] = p_devStatus->on_off_peak_min;
	// 53
	pstate_buf[52] = p_devStatus->dry_setting;
	// 54
	pstate_buf[53] = p_devStatus->most_used_prg;
	// 55
	pstate_buf[54] = p_devStatus->most_used_temp_speed; //?
	// 56
	pstate_buf[55] = p_devStatus->most_used_wash_time;
	// 57
	pstate_buf[56] = p_devStatus->most_used_rinse_num;
	// 58
	pstate_buf[57] = p_devStatus->pre_wash2;			// 3.7add
	// 59
	pstate_buf[58] = p_devStatus->machine_mode_area;
	// 60
	pstate_buf[59] = p_devStatus->machine_mode_kg;
	// 61
	pstate_buf[60] = p_devStatus->machine_mode_speed;
	// 62
	pstate_buf[61] = p_devStatus->wash_time_setting;
	// 63
	pstate_buf[62] = p_devStatus->special_stains_value;
	// 64
	pstate_buf[63] = p_devStatus->rinse_frq_setting;
	// 65
	pstate_buf[64] = p_devStatus->order_time_hour;
	// 66
	pstate_buf[65] = p_devStatus->order_time_min;
	// 67
	pstate_buf[66] = p_devStatus->plus_quickly;		// 3.7add
	// 68
	pstate_buf[67] = p_devStatus->plus_rinse;			// 3.7add
	// 69
	temp_value = 0;
	temp_value = p_devStatus->mute;
	pstate_buf[68] |= temp_value << 6;

	temp_value = 0;
	temp_value = p_devStatus->anti_fade;
	pstate_buf[68] |= temp_value << 5;

	temp_value = 0;
	temp_value = p_devStatus->clean_stop;
	pstate_buf[68] |= temp_value << 4;

	temp_value = 0;
	temp_value = p_devStatus->child_lock;
	pstate_buf[68] |= temp_value << 3;

	temp_value = 0;
	temp_value = p_devStatus->flush_light_panel;
	pstate_buf[68] |= temp_value << 2;

	temp_value = 0;
	temp_value = p_devStatus->peak_energy;
	pstate_buf[68] |= temp_value << 1;

	temp_value = 0;
	temp_value = p_devStatus->who_modify;
	pstate_buf[68] |= temp_value;

	// 70
	pstate_buf[69] = p_devStatus->screen_volume;
	// 71
	pstate_buf[70] = p_devStatus->screen_brightness;
	// 72
	pstate_buf[71] = p_devStatus->breathing_light;
	// 73
	pstate_buf[72] = p_devStatus->prg_start_sound;
	// 74
	pstate_buf[73] = p_devStatus->prg_end_sound;
	// 75
	pstate_buf[74] = p_devStatus->pause_sound;
	// 76
	pstate_buf[75] = p_devStatus->func_switch_sound;
	// 77
	pstate_buf[76] = p_devStatus->time_wash;
	// 78
	pstate_buf[77] = p_devStatus->time_left_h_standby;
	// 79
	pstate_buf[78] = p_devStatus->time_left_l_standby;

	// ����Ϊ79��
}

int build_cmd(int *prog_cmd_buf,int *pstate_buf,int *pstate_buf_len,
	unsigned char *praw_cmd_buf,int *praw_cmd_buf_len)
{
	WASHER_STAT *p_devStatus,p_devStatus1;
	COMMAND cmd_param;
	PARAM p_param[2];
	int enable,i,Huchi_locate;
	int *num_desc_list,Huchi_len,return_value;
	enum WASH_PRG_ID prg_id;
	
	PARAM_ITEM_DESC  Huchi_status[30], *p_desc_list;

	p_devStatus = &p_devStatus1;

	for(i = 0; i <30;i++)
	{
		Huchi_status[i].pm_id= 0;
		Huchi_status[i].is_valid = 0;
		Huchi_status[i].fix_param = 0;
		Huchi_status[i].param = 0;
	}
	
	// 1 	
	p_devStatus->version_h = (unsigned char)pstate_buf[0];
	// 2	
	p_devStatus->version_l = (unsigned char)pstate_buf[1];
	// 3
	p_devStatus->fading = ((unsigned char)pstate_buf[2]>>7) & 0x01;
	p_devStatus->elimate_foam = ((unsigned char)pstate_buf[2]>>6) & 0x01;
	p_devStatus->auto_rising = ((unsigned char)pstate_buf[2]>>5) & 0x01;
	p_devStatus->imbalance = ((unsigned char)pstate_buf[2]>>4) & 0x01;
	p_devStatus->gate_lock = ((unsigned char)pstate_buf[2]>>3) & 0x01;
//	p_devStatus->order = (p_stateBuf[18]>>2) & 0x01;
	p_devStatus->run_state = ((unsigned char)pstate_buf[2]>>1) & 0x01; 
	p_devStatus->power_state = (unsigned char)pstate_buf[2] & 0x01;		
	// 4
	p_devStatus->function6 = (unsigned char)pstate_buf[3];
//	p_devStatus->time_wash_onoff = (p_stateBuf[19]>>1) & 0x01;
	// 5
	p_devStatus->washer_phase = (unsigned char)pstate_buf[4];			// & 0x0F;
	// 6
	p_devStatus->washer_prg = (unsigned char)pstate_buf[5];
	// 7
	p_devStatus->moto_speed_h = (unsigned char)pstate_buf[6]; 
	// 8
	p_devStatus->moto_speed_l = (unsigned char)pstate_buf[7];
	// 9
	p_devStatus->washer_temprature = (unsigned char)pstate_buf[8];
	// 10
	//p_devStatus->time_setting = (p_stateBuf[25]>>3) & 0x01;
	p_devStatus->add_in_midway = ((unsigned char)pstate_buf[9]>>2) & 0x01;
	//p_devStatus->child_lock = (p_stateBuf[25]>>2) & 0x01;
	//p_devStatus->brightness_setting = p_stateBuf[25] & 0x01;
	// 11
	p_devStatus->soften_setting = (unsigned char)pstate_buf[10];
	// 12
	p_devStatus->order_hours_left = (unsigned char)pstate_buf[11]; 
	// 13
	p_devStatus->order_min_left =  (unsigned char)pstate_buf[12];	
	// 14
	//p_devStatus->rev14 = ;
	p_devStatus->washer_fault_info = (unsigned char)pstate_buf[13];// & 0x1F;			5.25 �Ͳ���ͳһ����������
	// 15
	p_devStatus->time_left_h = (unsigned char)pstate_buf[14];
	// 16
	p_devStatus->time_left_l = (unsigned char)pstate_buf[15];
	// 17
	p_devStatus->child_observer = (unsigned char)pstate_buf[16];
	// 18
	p_devStatus->detergent_setting = (unsigned char)pstate_buf[17];
	// 19
	p_devStatus->dehydrate_speed_max = (unsigned char)pstate_buf[18];
	// 20
	p_devStatus->dehydrate_speed_min = (unsigned char)pstate_buf[19];
	// 21
	p_devStatus->work_time_left = (unsigned char)pstate_buf[20];
	// 22
	p_devStatus->rinse_num_left = (unsigned char)pstate_buf[21];
	// 23
	p_devStatus->dehydrate_time_left = (unsigned char)pstate_buf[22];
	// 24
	p_devStatus->dehydrate_speed_setting = (unsigned char)pstate_buf[23];
	// 25
	p_devStatus->wash_temprature_setting = (unsigned char)pstate_buf[24];
	// 26
	p_devStatus->ag_degerming = ((unsigned char)pstate_buf[25]>>7) & 0x01;
	p_devStatus->strong_wash = ((unsigned char)pstate_buf[25]>>6) & 0x01;
	p_devStatus->pre_wash = ((unsigned char)pstate_buf[25]>>5) & 0x01;
	p_devStatus->high_water_lev = ((unsigned char)pstate_buf[25]>>4) & 0x01;
	p_devStatus->airing = ((unsigned char)pstate_buf[25]>>3) & 0x01;
	p_devStatus->energy_conserv = ((unsigned char)pstate_buf[25]>>2) & 0x01;
	p_devStatus->spin_rinse = ((unsigned char)pstate_buf[25]>>1) & 0x01;
	// 27
	p_devStatus->use_detergent = ((unsigned char)pstate_buf[26]>>7) & 0x01;
	p_devStatus->use_softener = ((unsigned char)pstate_buf[26]>>6) & 0x01;
	p_devStatus->dry = ((unsigned char)pstate_buf[26]>>5) & 0x01;
	//p_devStatus->rev27_4 = ;
	//p_devStatus->rev27_3 = ;
	//p_devStatus->brightness_setting_1 = (p_stateBuf[42]>>2) & 0x01;
	p_devStatus->crease_immersion = ((unsigned char)pstate_buf[26]>>1) & 0x01;
	//p_devStatus->rev27_0 = ;
	// 28
	p_devStatus->water_lel_frq_h = (unsigned char)pstate_buf[27];  //unit:10hz
	// 29
	p_devStatus->water_lel_frq_l = (unsigned char)pstate_buf[28];  //unit:10hz
	// 30
	p_devStatus->product_mode = (unsigned char)pstate_buf[29];
	// 31
	p_devStatus->indistinct_weight = (unsigned char)pstate_buf[30];
	// 32
	p_devStatus->mainboard_hour_setting = (unsigned char)pstate_buf[31];
	// 33
	p_devStatus->mainboard_min_setting = (unsigned char)pstate_buf[32];
	// 34
	p_devStatus->heating_pipe_time_h = (unsigned char)pstate_buf[33];
	// 35
	p_devStatus->function5 = (unsigned char)pstate_buf[34];
	// 36
	p_devStatus->inflow_duration_h = (unsigned char)pstate_buf[35];
	// 37
	p_devStatus->inflow_duration_l = (unsigned char)pstate_buf[36];
	// 38
	p_devStatus->turbidity_ad_value = (unsigned char)pstate_buf[37];   //?
	// 39
	p_devStatus->hardness_ad_value = (unsigned char)pstate_buf[38];   //?
	// 40
	p_devStatus->cloth_texture_value = (unsigned char)pstate_buf[39];  //?
	// 41
	p_devStatus->detergent_left = (unsigned char)pstate_buf[40];
	// 42
	p_devStatus->soften_left = (unsigned char)pstate_buf[41];
	// 43
	p_devStatus->cur_year_h = (unsigned char)pstate_buf[42];
	// 44
	p_devStatus->cur_year_l = (unsigned char)pstate_buf[43];
	// 45
	p_devStatus->cur_month = (unsigned char)pstate_buf[44];
	// 46
	p_devStatus->cur_day = (unsigned char)pstate_buf[45];
	// 47
	p_devStatus->GMT_8_hour = (unsigned char)pstate_buf[46];
	// 48
	p_devStatus->GMT_8_min = (unsigned char)pstate_buf[47];
	// 49
	p_devStatus->on_off_valley_hour = (unsigned char)pstate_buf[48];
	// 50
	p_devStatus->on_off_valley_min = (unsigned char)pstate_buf[49];
	// 51
	p_devStatus->on_off_peak_hour = (unsigned char)pstate_buf[50];
	// 52
	p_devStatus->on_off_peak_min = (unsigned char)pstate_buf[51];
	// 53
	p_devStatus->dry_setting = (unsigned char)pstate_buf[52];
	// 54
	p_devStatus->most_used_prg = (unsigned char)pstate_buf[53];
	// 55
	p_devStatus->most_used_temp_speed = (unsigned char)pstate_buf[54]; //?
	// 56
	p_devStatus->most_used_wash_time = (unsigned char)pstate_buf[55];
	// 57
	p_devStatus->most_used_rinse_num = (unsigned char)pstate_buf[56];
	// 58
	p_devStatus->pre_wash2 = (unsigned char)pstate_buf[57];		// 3.7add
	// 59
	p_devStatus->machine_mode_area = (unsigned char)pstate_buf[58];
	// 60
	p_devStatus->machine_mode_kg = (unsigned char)pstate_buf[59];
	// 61
	p_devStatus->machine_mode_speed = (unsigned char)pstate_buf[60];
	// 62
	p_devStatus->wash_time_setting = (unsigned char)pstate_buf[61];
	// 63
	p_devStatus->special_stains_value = (unsigned char)pstate_buf[62];
	// 64
	p_devStatus->rinse_frq_setting = (unsigned char)pstate_buf[63];
	// 65
	p_devStatus->order_time_hour = (unsigned char)pstate_buf[64];
	// 66
	p_devStatus->order_time_min = (unsigned char)pstate_buf[65];
	// 67
	p_devStatus->plus_quickly = (unsigned char)pstate_buf[66];		// 3.7add
	// 68
	p_devStatus->plus_rinse = (unsigned char)pstate_buf[67];			// 3.7add
	// 69
	//p_devStatus->onkey_smart_wash = (p_stateBuf[82]>>7) & 0x01;
	p_devStatus->mute = ((unsigned char)pstate_buf[68]>>6) & 0x01;
	p_devStatus->anti_fade = ((unsigned char)pstate_buf[68]>>5) & 0x01;
	p_devStatus->clean_stop = ((unsigned char)pstate_buf[68]>>4) & 0x01;
	p_devStatus->child_lock = ((unsigned char)pstate_buf[68]>>3) & 0x01;
	p_devStatus->flush_light_panel = ((unsigned char)pstate_buf[68]>>2) & 0x01;
	p_devStatus->peak_energy = ((unsigned char)pstate_buf[68]>>1) & 0x01;
	p_devStatus->who_modify = (unsigned char)pstate_buf[68] & 0x01;
	// 70
	p_devStatus->screen_volume = (unsigned char)pstate_buf[69];
	// 71
	p_devStatus->screen_brightness = (unsigned char)pstate_buf[70];
	// 72
	p_devStatus->breathing_light = (unsigned char)pstate_buf[71];
	// 73
	p_devStatus->prg_start_sound = (unsigned char)pstate_buf[72];
	// 74
	p_devStatus->prg_end_sound = (unsigned char)pstate_buf[73];
	// 75
	p_devStatus->pause_sound = (unsigned char)pstate_buf[74];
	// 76
	p_devStatus->func_switch_sound = (unsigned char)pstate_buf[75];
	// 77
	p_devStatus->time_wash = (unsigned char)pstate_buf[76];
	// 78
	p_devStatus->time_left_h_standby = (unsigned char)pstate_buf[77];
	// 79
	p_devStatus->time_left_l_standby = (unsigned char)pstate_buf[78];

	for(i = 0; i < *pstate_buf_len;i++ )
	{
		pstate_buf[i] = 0;
	}


	if (prog_cmd_buf[3] == CMD_CONTROL)
	{
		cmd_param.cmd_id = prog_cmd_buf[1];
		*(cmd_param.param) = (unsigned char)prog_cmd_buf[2];
		cmd_param.len = 1;
		return_value = wsh_get_cmd_control(p_devStatus,&cmd_param, 1,praw_cmd_buf,praw_cmd_buf_len);
		status_return_buf(p_devStatus,pstate_buf,pstate_buf_len);
		return(return_value);
	}
	else if (prog_cmd_buf[3] == CMD_PROG)
	{
		prg_id = (enum WASH_PRG_ID)prog_cmd_buf[1];
		enable = (uchar)prog_cmd_buf[4];
		return_value = wsh_cmd_adj_prog(p_devStatus,prg_id,enable,praw_cmd_buf,praw_cmd_buf_len);
		status_return_buf(p_devStatus,pstate_buf,pstate_buf_len);
		return(return_value);
	}
	else if (prog_cmd_buf[3] == CMD_PARAM)
	{
		p_param[0].pm_id = (unsigned char)prog_cmd_buf[1];
		*(p_param[0].param) = (unsigned char)prog_cmd_buf[2];
		p_param[0].len = 1;
		
		if (prog_cmd_buf[0] > 1)
		{
			p_param[1].pm_id = (unsigned char)prog_cmd_buf[5];
			*(p_param[1].param) = (unsigned char)prog_cmd_buf[6];
			p_param[1].len = 1;
		}

		Huchi_len = 30;
		num_desc_list = &Huchi_len;
		
		return_value = wsh_cmd_adj_param(p_devStatus,p_param,1,Huchi_status,num_desc_list,praw_cmd_buf,praw_cmd_buf_len);
		status_return_buf(p_devStatus,pstate_buf,pstate_buf_len);
		
		pstate_buf[90] = *num_desc_list;

		Huchi_locate = Huchi_Startaddress;
		
		p_desc_list = Huchi_status;

		if((*num_desc_list) != 0)
		{

			do 
			{
				pstate_buf[Huchi_locate] = p_desc_list->pm_id;
				pstate_buf[Huchi_locate + 1] = p_desc_list->is_valid;
				pstate_buf[Huchi_locate + 2] = p_desc_list->fix_param;
				pstate_buf[Huchi_locate + 3] = p_desc_list->param;
				
				Huchi_locate += 4;		// 7.1 ��3 Ϊ4 

				p_desc_list++;
				
			}while(--(*num_desc_list));

			*pstate_buf_len = Huchi_locate;	// 7.1 ȡ��	+ 1 
		}
		else
		{
			*pstate_buf_len = Huchi_locate;		
		}
		return(return_value);
	}
	else
		return(-1);
	
}

/**����:
// *   ͬ��;
// *       
// *   ��;��  ��ȡ��ѯϴ�»�����״̬��Ӧ��ԭʼ����֡���ݣ�����
//  ֡ͷ+����+֡β��������  p_cmdBuf--ԭʼ����֡���棬��Ӧ�ò�����
// ��Э��⸳ֵlen--����֡���ȣ���Э��⸳ֵ
//  ����ֵ����
 */
void wsh_cmd_ck_stat(unsigned char *p_cmdBuf, int *len)
{
	unsigned int Temp_Sum;
	
	p_cmdBuf[0] = 0xF4;
	p_cmdBuf[1] = 0xF5;
	p_cmdBuf[2] = 0x00;
	p_cmdBuf[3] = 0x40;
	p_cmdBuf[4] = 0x0C;
	p_cmdBuf[5] = 0x00;
	p_cmdBuf[6] = 0x00;
	p_cmdBuf[7] = 0x09;
	p_cmdBuf[8] = 0x01;
	p_cmdBuf[9] = 0xFE;
	p_cmdBuf[10] = 0x01;
	p_cmdBuf[11] = 0x00;
	p_cmdBuf[12] = 0x00;
	p_cmdBuf[13] = 0x66;
	p_cmdBuf[14] = 0x00;
	p_cmdBuf[15] = 0x00;
	p_cmdBuf[16] = 0x01;
	p_cmdBuf[17] = 0x01;
	p_cmdBuf[18] = 0xBC;
	p_cmdBuf[19] = 0xF4;
	p_cmdBuf[20] = 0xFB;
	
	*len = 21;	
	
}

int get_version(char* ver_buf,int* len)
{
	ver_buf = "5.3.3";
	*len = 5;
	return(0);
}

/*����: 			//��̨����״̬ʱҲ����
// *       1����һ�ν���Ӧ��ʱ��Ҫˢ��ҳ�棬��ʱ����øýӿڰѴ�ϴ
//          �»��˻�ȡ��ԭʼ״̬���ݽ���������ʾ
// *       2��ϴ�»�����״̬�����ı�ʱ������øýӿ����������µ�ԭ
//          ʼ״̬����

// *   ��;��  ����ϴ�»�����״̬ԭʼ֡���ݣ�����֡ͷ+����+֡β��
// *   ������  p_stateBuf--ԭʼ״̬֡���ݻ���
//*    p_devStatus--ϴ�»��ĵ�ǰ����״̬,�ɽ����⸳ֵ
// *					  
// *   ����ֵ��-1--֡���ݷǷ�����Ч 0--�ɹ�
 */


int wsh_parse_state(unsigned char *p_stateBuf, int len_buf, WASHER_STAT *p_devStatus,PARAM_ITEM_DESC *p_desc_list,
				    int *num_desc_list)	
{
	unsigned char Tft_rece_buf_index,i,F4_Disp_Count = 0;unsigned char F4_End_Single;
	
//PARAM_ITEM_DESC *p_desc_list_Temp; int *Temp_num_desc_list;
	
	unsigned int Rece_Temp_Sum,Temp_Sum;


	Tft_rece_buf_index = 1;
	
	do
	{
		
		if (p_stateBuf[0] == 0xF4 && p_stateBuf[1] == 0xF5) 
		{//�������ڳ��Ⱥ�ĵ�У���֮ǰ�ĳ���
			
			if (Tft_rece_buf_index > len_buf + 2) return(0);

			if (p_stateBuf[Tft_rece_buf_index] == 0xF4)
			{
				F4_End_Single = 1;
				
				if (p_stateBuf[Tft_rece_buf_index+1] == 0xF4)
				{
					F4_End_Single = 0;
					for (i=Tft_rece_buf_index+1;i<len_buf-F4_Disp_Count-1;i++)
						p_stateBuf[i] = p_stateBuf[i+1];
					F4_Disp_Count++;
				}
				else
				{
					if (p_stateBuf[Tft_rece_buf_index+1] == 0xFB && F4_End_Single == 1)
					{
						F4_End_Single = 0;
						//return(0);
						break;
					}
					else
					{
						F4_End_Single = 0;
						return(-1);
					}
					
				}
			}
			Tft_rece_buf_index++;

		}
		else				//��ͷ
		{

			Tft_rece_buf_index = 2;
			p_stateBuf[0] = p_stateBuf[1];
			p_stateBuf[1] = p_stateBuf[2];

			for (i=2;i<len_buf-1;i++ )
			{
				p_stateBuf[i] = p_stateBuf[i+1];
			}

			len_buf--;
			if (len_buf <= 4) return(-1);
						
		}
		
	}while(1);

	Rece_Temp_Sum = p_stateBuf[Tft_rece_buf_index - 2];
	Rece_Temp_Sum <<= 8;
	Rece_Temp_Sum |= p_stateBuf[Tft_rece_buf_index - 1];
		
	Temp_Sum = Check_COMSum(p_stateBuf,Tft_rece_buf_index + 2);

	if (Rece_Temp_Sum != Temp_Sum) return(-1);

	for(i=16;i<106;i++)		//�ָ�ԭֵ
	{
		p_stateBuf[i] ^= 0xFF;
		p_stateBuf[i] -= 1;
	}
	
	//Analysis_pakage_Proc();

	// 1 	
	p_devStatus->version_h = p_stateBuf[16];
	// 2	
	p_devStatus->version_l = p_stateBuf[17];
	// 3
	p_devStatus->fading = (p_stateBuf[18]>>7) & 0x01;
	p_devStatus->elimate_foam = (p_stateBuf[18]>>6) & 0x01;
	p_devStatus->auto_rising = (p_stateBuf[18]>>5) & 0x01;
	p_devStatus->imbalance = (p_stateBuf[18]>>4) & 0x01;
	p_devStatus->gate_lock = (p_stateBuf[18]>>3) & 0x01;
//	p_devStatus->order = (p_stateBuf[18]>>2) & 0x01;
	p_devStatus->run_state = (p_stateBuf[18]>>1) & 0x01; 
	p_devStatus->power_state = p_stateBuf[18] & 0x01;	
	// 4
	p_devStatus->function6 = p_stateBuf[19];
//	p_devStatus->time_wash_onoff = (p_stateBuf[19]>>1) & 0x01;
	// 5
	p_devStatus->washer_phase = p_stateBuf[20] & 0x0F;
	// 6
	p_devStatus->washer_prg = p_stateBuf[21];
	// 7
	p_devStatus->moto_speed_h = p_stateBuf[22]; 
	// 8
	p_devStatus->moto_speed_l = p_stateBuf[23];
	// 9
	p_devStatus->washer_temprature = p_stateBuf[24];
	// 10
	//p_devStatus->time_setting = (p_stateBuf[25]>>3) & 0x01;
	p_devStatus->add_in_midway = (p_stateBuf[25]>>2) & 0x01;
	//p_devStatus->child_lock = (p_stateBuf[25]>>2) & 0x01;
	//p_devStatus->brightness_setting = p_stateBuf[25] & 0x01;
	// 11
	p_devStatus->soften_setting = p_stateBuf[26];
	// 12
	p_devStatus->order_hours_left = p_stateBuf[27]; 
	// 13
	p_devStatus->order_min_left =  p_stateBuf[28];	
	// 14
	//p_devStatus->rev14 = ;
	p_devStatus->washer_fault_info = p_stateBuf[29];// & 0x1F;			5.25 �Ͳ���ͳһ����������
	// 15
	p_devStatus->time_left_h_standby = p_devStatus->time_left_h = p_stateBuf[30];
	// 16
	p_devStatus->time_left_l_standby = p_devStatus->time_left_l = p_stateBuf[31];
	// 17
	p_devStatus->child_observer = p_stateBuf[32];
	// 18
	p_devStatus->detergent_setting = p_stateBuf[33];
	// 19
	p_devStatus->dehydrate_speed_max = p_stateBuf[34];
	// 20
	p_devStatus->dehydrate_speed_min = p_stateBuf[35];
	// 21
	p_devStatus->work_time_left = p_stateBuf[36];
	// 22
	p_devStatus->rinse_num_left = p_stateBuf[37];
	// 23
	p_devStatus->dehydrate_time_left = p_stateBuf[38];
	// 24
	p_devStatus->dehydrate_speed_setting = p_stateBuf[39];
	// 25
	p_devStatus->wash_temprature_setting = p_stateBuf[40];
	// 26
	p_devStatus->ag_degerming = (p_stateBuf[41]>>7) & 0x01;
	p_devStatus->strong_wash = (p_stateBuf[41]>>6) & 0x01;
	p_devStatus->pre_wash = (p_stateBuf[41]>>5) & 0x01;
	p_devStatus->high_water_lev = (p_stateBuf[41]>>4) & 0x01;
	p_devStatus->airing = (p_stateBuf[41]>>3) & 0x01;
	p_devStatus->energy_conserv = (p_stateBuf[41]>>2) & 0x01;
	p_devStatus->spin_rinse = (p_stateBuf[41]>>1) & 0x01;
	// 27
	p_devStatus->use_detergent = (p_stateBuf[42]>>7) & 0x01;
	p_devStatus->use_softener = (p_stateBuf[42]>>6) & 0x01;
	p_devStatus->dry = (p_stateBuf[42]>>5) & 0x01;
	//p_devStatus->rev27_4 = ;
	//p_devStatus->rev27_3 = ;
	//p_devStatus->brightness_setting_1 = (p_stateBuf[42]>>2) & 0x01;
	p_devStatus->crease_immersion = (p_stateBuf[42]>>1) & 0x01;
	//p_devStatus->rev27_0 = ;
	// 28
	p_devStatus->water_lel_frq_h = p_stateBuf[43];  //unit:10hz
	// 29
	p_devStatus->water_lel_frq_l = p_stateBuf[44];  //unit:10hz
	// 30
	p_devStatus->product_mode = p_stateBuf[45];
	// 31
	p_devStatus->indistinct_weight = p_stateBuf[46];
	// 32
	p_devStatus->mainboard_hour_setting = p_stateBuf[47];
	// 33
	p_devStatus->mainboard_min_setting = p_stateBuf[48];
	// 34
	p_devStatus->heating_pipe_time_h = p_stateBuf[49];
	// 35
	p_devStatus->function5 = p_stateBuf[50];
	// 36
	p_devStatus->inflow_duration_h = p_stateBuf[51];
	// 37
	p_devStatus->inflow_duration_l = p_stateBuf[52];
	// 38
	p_devStatus->turbidity_ad_value = p_stateBuf[53];   //?
	// 39
	p_devStatus->hardness_ad_value = p_stateBuf[54];   //?
	// 40
	p_devStatus->cloth_texture_value = p_stateBuf[55];  //?
	// 41
	p_devStatus->detergent_left = p_stateBuf[56];
	// 42
	p_devStatus->soften_left = p_stateBuf[57];
	// 43
	p_devStatus->cur_year_h = p_stateBuf[58];
	// 44
	p_devStatus->cur_year_l = p_stateBuf[59];
	// 45
	p_devStatus->cur_month = p_stateBuf[60];
	// 46
	p_devStatus->cur_day = p_stateBuf[61];
	// 47
	p_devStatus->GMT_8_hour = p_stateBuf[62];
	// 48
	p_devStatus->GMT_8_min = p_stateBuf[63];
	// 49
	p_devStatus->on_off_valley_hour =p_stateBuf[64];
	// 50
	p_devStatus->on_off_valley_min = p_stateBuf[65];
	// 51
	p_devStatus->on_off_peak_hour =p_stateBuf[66];
	// 52
	p_devStatus->on_off_peak_min = p_stateBuf[67];
	// 53
	p_devStatus->dry_setting = p_stateBuf[68];
	// 54
	p_devStatus->most_used_prg = p_stateBuf[69];
	// 55
	p_devStatus->most_used_temp_speed = p_stateBuf[70]; //?
	// 56
	p_devStatus->most_used_wash_time = p_stateBuf[71];
	// 57
	p_devStatus->most_used_rinse_num = p_stateBuf[72];
	// 58
	p_devStatus->pre_wash2 = p_stateBuf[73];			// 3.7add
	// 59
	p_devStatus->machine_mode_area = p_stateBuf[74];
	// 60
	p_devStatus->machine_mode_kg = p_stateBuf[75];
	// 61
	p_devStatus->machine_mode_speed = p_stateBuf[76];
	// 62
	p_devStatus->wash_time_setting = p_stateBuf[77];
	// 63
	p_devStatus->special_stains_value = p_stateBuf[78];
	// 64
	p_devStatus->rinse_frq_setting = p_stateBuf[79];
	// 65
	p_devStatus->order_time_hour = p_stateBuf[80];
	// 66
	p_devStatus->order_time_min = p_stateBuf[81];
	// 67
	p_devStatus->plus_quickly = p_stateBuf[82];		// 3.7add
	// 68
	p_devStatus->plus_rinse = p_stateBuf[83];			// 3.7add
	// 69
	//p_devStatus->onkey_smart_wash = (p_stateBuf[82]>>7) & 0x01;
	p_devStatus->mute = (p_stateBuf[84]>>6) & 0x01;
	p_devStatus->anti_fade = (p_stateBuf[84]>>5) & 0x01;
	p_devStatus->clean_stop = (p_stateBuf[84]>>4) & 0x01;
	p_devStatus->child_lock = (p_stateBuf[84]>>3) & 0x01;
	p_devStatus->flush_light_panel = (p_stateBuf[84]>>2) & 0x01;
	p_devStatus->peak_energy = (p_stateBuf[84]>>1) & 0x01;
	p_devStatus->who_modify = p_stateBuf[84] & 0x01;
	// 70
	p_devStatus->screen_volume = p_stateBuf[85];
	// 71
	p_devStatus->screen_brightness = p_stateBuf[86];
	// 72
	p_devStatus->breathing_light = p_stateBuf[87];
	// 73
	p_devStatus->prg_start_sound = p_stateBuf[88];
	// 74
	p_devStatus->prg_end_sound = p_stateBuf[89];
	// 75
	p_devStatus->pause_sound = p_stateBuf[90];
	// 76
	p_devStatus->func_switch_sound = p_stateBuf[91];
	// 77
	p_devStatus->time_wash = p_stateBuf[92];

	p_devStatus->time_left_h_standby = p_stateBuf[93];
	// 16
	p_devStatus->time_left_l_standby = p_stateBuf[94];

	
#if (1)
	if (p_devStatus->washer_prg == COTTON_FIBRE_WASH ||p_devStatus->washer_prg == CHEM_FIBER_WASH
		||p_devStatus->washer_prg == BIG_WASH ||p_devStatus->washer_prg == UNDERWEAR_WASH
		||p_devStatus->washer_prg == MIX_WASH ||p_devStatus->washer_prg == OUTDOOR_WASH
		||p_devStatus->washer_prg == JEANS_WASH ||p_devStatus->washer_prg == COLOUR_FABRIC_WASH
		||p_devStatus->washer_prg == PROTECT_BABY_WASH)
	{
		(*num_desc_list) = 0;

		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL)
		{
			p_desc_list->pm_id = PID_HIGH_WATER_LEVL;
			p_desc_list->is_valid = FAIL;			
			(*num_desc_list)++;
			p_desc_list++;
		}

		if (p_devStatus->pre_wash == TRUE ||p_devStatus->pre_wash2 != FAIL)	// 5.8�Ķ�״̬��Ļ������
		{
			p_desc_list->pm_id = PID_TIMEWASH_INDEX;
			p_devStatus->time_wash = EXIT_TIMEWASH;						//�Ա�����õ�4.25��
			p_desc_list->is_valid = FAIL;			
			(*num_desc_list)++;
			p_desc_list++;

			p_desc_list->pm_id = PID_SPECIAL_STAINS_SET;
			p_devStatus->special_stains_value = VOID_BLOTTED;				//�Ա�����õ�4.25��
			p_desc_list->is_valid = FAIL;			
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL 
			||p_devStatus->special_stains_value != VOID_BLOTTED||p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_STRONG_WASH;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;

			p_desc_list->pm_id = PID_PRE_WASH;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;
		}

		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL 
			||p_devStatus->time_wash != EXIT_TIMEWASH ||p_devStatus->special_stains_value != VOID_BLOTTED)
		{
			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}

		if (p_devStatus->time_wash != EXIT_TIMEWASH ||p_devStatus->special_stains_value != VOID_BLOTTED)
		{
			p_desc_list->pm_id = PID_WASH_TEMPRATURE;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;	
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_RINSING_FREQUENCY;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}

		if (p_devStatus->strong_wash == TRUE ||p_devStatus->pre_wash == TRUE ||p_devStatus->high_water_lev == TRUE
			||p_devStatus->pre_wash2 != FAIL)
		{
			p_desc_list->pm_id = PID_ENERGY_SAVE;
			p_desc_list->is_valid = FAIL;			
			(*num_desc_list)++;			
			p_desc_list++;
			
		}

		if (p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_DEHYDRATION_SPEED;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
			
	}
	else if (p_devStatus->washer_prg == WOOL_WASH)
	{
		*num_desc_list = 0;
		
		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL)
		{
			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}

	}
	else if (p_devStatus->washer_prg == SILK_WASH)
	{
		*num_desc_list = 0;
		
		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL 
			||p_devStatus->special_stains_value != VOID_BLOTTED ||p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list) ++;
			p_desc_list++;

		}

		if (p_devStatus->special_stains_value != VOID_BLOTTED ||p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			//p_desc_list->pm_id = PID_ENERGY_SAVE;
			//p_desc_list->is_valid = FAIL;
			//(*num_desc_list) ++;
			//p_desc_list++;

			p_desc_list->pm_id = PID_WASH_TEMPRATURE;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;	
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_RINSING_FREQUENCY;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list) ++;
			p_desc_list++;
			
			if (p_devStatus->time_wash != EXIT_TIMEWASH)
			{
				p_desc_list->pm_id = PID_DEHYDRATION_SPEED;
				p_desc_list->fix_param = 0xFFFF;
				p_desc_list->is_valid = TRUE;
				(*num_desc_list) ++;
				p_desc_list++;
			}
		}
		
	}
	else if (p_devStatus->washer_prg == FEATHER_WASH)
	{
		*num_desc_list = 0;
		
		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL)
		{

			p_desc_list->pm_id = PID_HIGH_WATER_LEVL;
			p_desc_list->is_valid = FAIL;			
			(*num_desc_list)++;
			p_desc_list++;
		}

		if (p_devStatus->high_water_lev == TRUE)
		{
			p_desc_list->pm_id = PID_ENERGY_SAVE;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_WASH_TEMPRATURE;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;

			/*p_desc_list->pm_id = PID_ENERGY_SAVE;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;*/

			p_desc_list->pm_id = PID_RINSING_FREQUENCY;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;

			p_desc_list->pm_id = PID_DEHYDRATION_SPEED;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
				
		}

		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
	}
	else if (p_devStatus->washer_prg == RINSE_DEHY_WASH)
	{
		(*num_desc_list) = 0;
	}
	else if (p_devStatus->washer_prg == SINGLE_DEHY_WASH)
	{
		(*num_desc_list) = 0;
	}
	else if (p_devStatus->washer_prg == SUPER_QUICKLY_WASH)
	{
		(*num_desc_list) = 0;
		
		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_WASH_TEMPRATURE;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_RINSING_FREQUENCY;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_DEHYDRATION_SPEED;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
	}		
	else if (p_devStatus->washer_prg == SHIRT_WASH)
	{
		*num_desc_list = 0;

		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->strong_wash == TRUE)
		{
			p_desc_list->pm_id = PID_TIMEWASH_INDEX;
			p_devStatus->time_wash = EXIT_TIMEWASH;						//�Ա�����õ�4.25��
			p_desc_list->is_valid = FAIL;			
			(*num_desc_list)++;
			p_desc_list++;

			p_desc_list->pm_id = PID_SPECIAL_STAINS_SET;
			p_devStatus->special_stains_value = VOID_BLOTTED;				//�Ա�����õ�4.25��
			p_desc_list->is_valid = FAIL;			
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL)
		{
			p_desc_list->pm_id = PID_HIGH_WATER_LEVL;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_STRONG_WASH;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;
		}

		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL 
			||p_devStatus->special_stains_value != VOID_BLOTTED ||p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}

		if (p_devStatus->special_stains_value != VOID_BLOTTED ||p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			
			p_desc_list->pm_id = PID_STRONG_WASH;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;

			p_desc_list->pm_id = PID_WASH_TEMPRATURE;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}

		
		if (p_devStatus->strong_wash == TRUE ||p_devStatus->high_water_lev == TRUE)
		{

			p_desc_list->pm_id = PID_ENERGY_SAVE;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;
			
		}

		
		if (p_devStatus->special_stains_value != VOID_BLOTTED ||p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_RINSING_FREQUENCY;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;

			if (p_devStatus->time_wash != EXIT_TIMEWASH)
			{
				p_desc_list->pm_id = PID_DEHYDRATION_SPEED;
				p_desc_list->fix_param = 0xFFFF;
				p_desc_list->is_valid = TRUE;
				(*num_desc_list)++;
				p_desc_list++;
			}
		}

	}
	else if (p_devStatus->washer_prg == STERILIZE_95_WASH ||p_devStatus->washer_prg == PREVENT_ALERGIC_WASH)
	{
		*num_desc_list = 0;
		
		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->washer_prg == STERILIZE_95_WASH)
		{
			p_desc_list->pm_id = PID_WASH_TEMPRATURE;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->energy_conserv == TRUE ||p_devStatus->plus_quickly != FAIL)
		{
			p_desc_list->pm_id = PID_HIGH_WATER_LEVL;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;
			
		}
		
		if (p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_RINSING_FREQUENCY;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_DEHYDRATION_SPEED;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}

	}
	else if (p_devStatus->washer_prg == CLEAN_BUKET_WASH)
	{
		*num_desc_list = 0;
		
		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
		if (p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_WASH_TEMPRATURE;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_RINSING_FREQUENCY;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_DEHYDRATION_SPEED;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		
	}
	else if (p_devStatus->washer_prg == LINT_TOY_WASH ||p_devStatus->washer_prg == SUPGEN_HAND_WASH)
	{
		*num_desc_list = 0;

		if (p_devStatus->function5 == FUNCTION5_PREWASH ||p_devStatus->function5 == FUNCTION5_STRONG
			||p_devStatus->function6 == FUNCTION6_PREWASH)
		{
			p_desc_list->pm_id = PID_PLUS_QUICKLY;		//6.3 ����ʾ
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
		}
		

		if (p_devStatus->special_stains_value != VOID_BLOTTED ||p_devStatus->time_wash != EXIT_TIMEWASH)
		{
			p_desc_list->pm_id = PID_ENERGY_SAVE;
			p_desc_list->is_valid = FAIL;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_WASH_TEMPRATURE;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;

			p_desc_list->pm_id = PID_WASH_TIME;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;
			
			p_desc_list->pm_id = PID_RINSING_FREQUENCY;
			p_desc_list->fix_param = 0xFFFF;
			p_desc_list->is_valid = TRUE;
			(*num_desc_list)++;
			p_desc_list++;

			if (p_devStatus->time_wash != EXIT_TIMEWASH)
			{
				p_desc_list->pm_id = PID_DEHYDRATION_SPEED;
				p_desc_list->fix_param = 0xFFFF;
				p_desc_list->is_valid = TRUE;
				(*num_desc_list)++;
				p_desc_list++;
			}
		}
		
	}
	else if (p_devStatus->washer_prg == ONE_KEY_WASH)
	{
		(*num_desc_list) = 0;
	}
	else
	{
		(*num_desc_list) = 0;
	}
	
#endif 

	return(0);

}

int parse_state(unsigned char * Praw_state_buf,int Raw_state_buf_len,int *pstate_buf,int *pstate_buf_len)
{	
	WASHER_STAT Wm_Status;	
	PARAM_ITEM_DESC  Huchi_status[30], *p_desc_list;
	int *num_desc_list,i,return_value,Huchi_locate,Huch_Len;


	for(i = 0; i <*pstate_buf_len;i++)
	{
		pstate_buf[i] = 0;
	}

	for(i = 0; i <30;i++ )
	{
		Huchi_status[i].pm_id= 0;
		Huchi_status[i].is_valid = 0;
		Huchi_status[i].fix_param = 0;
		Huchi_status[i].param = 0;
	}

	Huch_Len = 30;
	num_desc_list = &Huch_Len;	

	return_value = wsh_parse_state(Praw_state_buf, Raw_state_buf_len,&Wm_Status,Huchi_status,num_desc_list);

	if (return_value == -1)
		return(-1);
	else 
	{
		for(i = Rawstate_Startaddress; i < Rawstate_Endaddress;i++)
		{
			pstate_buf[i -Rawstate_Startaddress] = Praw_state_buf[i];
		}
		
		pstate_buf[90] = *num_desc_list;

		Huchi_locate = Huchi_Startaddress;
		
		p_desc_list = Huchi_status;

		/*if((*num_desc_list) != 0)
		{

			
			do 
			{
				pstate_buf[Huchi_locate] = p_desc_list->pm_id;
				pstate_buf[Huchi_locate + 1] = p_desc_list->is_valid;
				pstate_buf[Huchi_locate + 2] = p_desc_list->fix_param;
				pstate_buf[Huchi_locate + 3] = p_desc_list->param;
				
				Huchi_locate += 4;

				p_desc_list++;
				
			}while(--(*num_desc_list));

			*pstate_buf_len = Huchi_locate;		
		}
		else
		{
			*pstate_buf_len = Huchi_locate;		
		}*/

		return(0);
	}
		
}

#ifdef __cplusplus

}

#endif



