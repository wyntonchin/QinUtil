#define _LNKKEY   0xF4
#define _LNKHEAD   0xF5
#define _LNKEND   0xFB
#define _BUFFSIZE   255
#define _DESTTYPE    0xFE

#define STATUS_NUM 79//状态个数

#include "fridge_interface.h"
#include "frgctl_state.h"
#include "frgctl_command.h"
#include "stddef.h"
#include "log.h"

static unsigned char  checkbuff_valid(unsigned char *buff);
static unsigned char  checkbuff_sum(unsigned char *buff); /* 判断数据帧校验和 */
static unsigned char buff2dataAndCheck(uchar* recebuff, uchar* p_statedata, uchar ilen);
unsigned char data2buff(uchar* p_configdata, uchar* sendbuff);
unsigned char  buff2data(uchar* recebuff, uchar* p_statedata);
int get_version(char* ver_buf, size_t len);

//int parse_state(unsigned char* praw_state_buf,const size_t raw_state_buf_len,RefParam* pref_param,unsigned char* pstate_buf,int* pstate_buf_len);

//int build_cmd(const int* porg_cmd_buf,unsigned char* pstate_buf,int* pstate_buf_len,RefParam* pref_param,unsigned char* praw_cmd_buf,int* praw_cmd_buf_len);

//int get_version(char* ver_buf,size_t len);

typedef struct RefParam_s {
	char* data;
	size_t len;

}RefParam;

/*****************************************************************************
used:检测数据桢是否有效。
@Param1 *buff
@param2 *devicetype
@return 0--ok;~0--网络通讯错误(rst);
********************************************************************************/
unsigned char  checkbuff_valid(unsigned char *buff)
{
	unsigned char ilen;
	unsigned char rst = 0;
	ilen = buff[4];
	if (ilen < 90)
	{
		if ((0 == (buff[3] & 0x3f)) && (0 == buff[6]) && (0 == (buff[0x0c] & 0x3f)))
		{
			if (checkbuff_sum(buff) != 0)
			{
				if (_DESTTYPE == buff[0x07])
				{
					if (0x08 == buff[0x0b])
					{
						rst = 8;
					}
					else
					{
						rst = 0;
					}

				}
				else
				{
					rst = 4;
				}

			}
			else
			{
				rst = 3;
			}
		}
		else
		{
			rst = 2;
		}
	}
	else
	{
		rst = 1;
	}
	return rst;
}

/* *************************************************************************
判断数据帧校验和
**************************************************************************/
unsigned char checkbuff_sum(unsigned char *buff)
{
	unsigned int sum;
	unsigned char i, sumok;
	sum = 0;
	for (i = 2; i <= buff[4] + 4; i++)
	{
		sum += buff[i];
	}
	if (0 == (buff[3] >> 6))
	{
		sum = (sum & 0xff);
		if (sum == (unsigned int)buff[i])
		{
			sumok = 1;
		}
		else
		{
			sumok = 0;
		}
	}
	else
	{
		if (sum == ((unsigned int)buff[i] << 8) + buff[i + 1])
		{
			sumok = 1;
		}
		else
		{
			sumok = 0;
		}
	}
	return sumok;
}

/**
*   用途：解析102 指令获取的冰箱状态原始数据
*   参数：p_stateBuf   冰箱状态数据
*                   p_devStatus  指向状态结构体的指针
*                   p_devConfig指向配置结构体的指针
*                   parseState_x_x(x)函数将解析后的数据赋值到结构体对应成员
*   返回值：0--成功 1--失败
*/
int frg_parse_state_102_0(uchar* p_cmdBuf, Frg_Status* p_devStatus, Device_Config* p_devConfig)
{
	unsigned char id = 0;
	//unsigned char utmp=0;
	//signed char stmp=0;
	unsigned char rst = 0;
	//unsigned char i;
	unsigned char p_statedata[_BUFFSIZE];
	rst = buff2dataAndCheck(p_cmdBuf, p_statedata, _BUFFSIZE);
	if (0 == rst)
	{
		id = 15;

		p_devStatus->refrigerator_temperature = p_statedata[id + 1] - 40;
		p_devStatus->freeze_temperature = p_statedata[id + 2] - 40;
		p_devStatus->variation_temperature = p_statedata[id + 3] - 40;

		p_devStatus->work_mode1 = p_statedata[id + 4];
		if ((p_statedata[id + 4] & 0x40) >> 6)   //fuzzy 智能模式
		{
			if (p_devStatus->refrigerator_temperature != 9)
			{
				p_devStatus->refrigerator_temperature = 5;
			}
			//p_devStatus->refrigerator_temperature  = 5;
			p_devStatus->freeze_temperature = -18;
			//p_devStatus->variation_temperature;

			p_devStatus->fuzzy_mode = 1;
			p_devStatus->save_mode = 0;
			p_devStatus->sf_mode = 0;
			p_devStatus->sr_mode = 0;
			p_devStatus->holiday_mode = 0;
		}

		if ((p_statedata[id + 4] & 0x20) >> 5)   //save  节能模式
		{
			if (p_devStatus->refrigerator_temperature != 9)
			{
				p_devStatus->refrigerator_temperature = 6;
			}
			//p_devStatus->refrigerator_temperature  = 6;
			p_devStatus->freeze_temperature = -17;
			//p_devStatus->variation_temperature;

			p_devStatus->fuzzy_mode = 0;
			p_devStatus->save_mode = 1;
			p_devStatus->sf_mode = 0;
			p_devStatus->sr_mode = 0;
			p_devStatus->holiday_mode = 0;
		}

		if ((p_statedata[id + 4] & 0x04) >> 2)   //sf 速冻模式
		{
			//p_devStatus->refrigerator_temperature;
			p_devStatus->freeze_temperature = -25;
			//p_devStatus->variation_temperature;

			p_devStatus->fuzzy_mode = 0;
			p_devStatus->save_mode = 0;
			p_devStatus->sf_mode = 1;
			p_devStatus->sr_mode = 0;
			p_devStatus->holiday_mode = 0;
		}

		if (p_statedata[id + 4] & 0x01)   //sr 速冷模式
		{
			if (p_devStatus->refrigerator_temperature != 9)
			{
				p_devStatus->refrigerator_temperature = 2;
			}
			//p_devStatus->refrigerator_temperature = 2;
			//p_devStatus->freeze_temperature ;
			//p_devStatus->variation_temperature;

			p_devStatus->fuzzy_mode = 0;
			p_devStatus->save_mode = 0;
			p_devStatus->sf_mode = 0;
			p_devStatus->sr_mode = 1;
			p_devStatus->holiday_mode = 0;
		}

		p_devStatus->work_mode2 = p_statedata[id + 5];
		if ((p_statedata[id + 5] & 0x40) >> 6)   //holiday  假日模式
		{
			if (p_devStatus->refrigerator_temperature != 9)
			{
				p_devStatus->refrigerator_temperature = 15;
			}
			//p_devStatus->refrigerator_temperature = 15;
			p_devStatus->freeze_temperature = -18;
			//p_devStatus->variation_temperature;

			p_devStatus->fuzzy_mode = 0;
			p_devStatus->save_mode = 0;
			p_devStatus->sf_mode = 0;
			p_devStatus->sr_mode = 0;
			p_devStatus->holiday_mode = 1;
		}

		p_devStatus->mainboard_version = p_statedata[id + 6];

		p_devStatus->refrigerator_real_temperature = p_statedata[id + 7] - 40;
		p_devStatus->freeze_real_temperature = p_statedata[id + 8] - 40;
		p_devStatus->variation_real_temperature = p_statedata[id + 9] - 40;
		p_devStatus->environment_real_temperature = p_statedata[id + 10] - 40;

		p_devStatus->refrigerator_sensor_real_temperature = p_statedata[id + 11] - 40;
		p_devStatus->freeze_sensor_real_temperature = p_statedata[id + 12] - 40;
		p_devStatus->variation_sensor_real_temperature = p_statedata[id + 13] - 40;

		p_devStatus->sensor_failure_status = p_statedata[id + 14];
		p_devStatus->control_failure_status = p_statedata[id + 15];

		p_devStatus->running_status = p_statedata[id + 16];
		p_devStatus->mainboard_type = p_statedata[id + 17];
		p_devStatus->displayboard_key_setting = p_statedata[id + 18];
		p_devStatus->displayboard_type = p_statedata[id + 19];
		p_devStatus->compressor_frequency = p_statedata[id + 20];

		p_devStatus->refrigerator_poweron_ad = p_statedata[id + 21];
		p_devStatus->refrigerator_poweroff_ad = p_statedata[id + 22];
		p_devStatus->freeze_poweron_ad = p_statedata[id + 23];
		p_devStatus->freeze_poweroff_ad = p_statedata[id + 24];
		p_devStatus->variation_poweron_ad = p_statedata[id + 25];
		p_devStatus->variation_poweroff_ad = p_statedata[id + 26];
		p_devStatus->sensor_failure_status2 = p_statedata[id + 27];

		p_devStatus->temperature_room_judge = p_statedata[id + 28];
		if ((p_statedata[id + 28] & 0x01))   //有冰箱冷藏 室
		{
			p_devConfig->refrigerator_room = 1;
		}
		else
		{
			p_devConfig->refrigerator_room = 0;
		}

		if ((p_statedata[id + 28] & 0x02 >> 1))   //有冰箱冷冻室
		{
			p_devConfig->freeze_room = 1;
		}
		else
		{
			p_devConfig->freeze_room = 0;
		}

		if ((p_statedata[id + 28] & 0x04) >> 2)   //有冰箱变温室
		{
			p_devConfig->variation_room = 1;
		}
		else
		{
			p_devConfig->variation_room = 0;
		}

		if ((p_statedata[id + 28] & 0x08 >> 3))   //有冰箱软冻室
		{
			p_devConfig->soft_freeze_room = 1;
		}
		else
		{
			p_devConfig->soft_freeze_room = 0;
		}

		if ((p_statedata[id + 28] & 0x10) >> 4)   //有冰箱自动制冰室
		{
			p_devConfig->auto_ice_room = 1;
		}
		else
		{
			p_devConfig->auto_ice_room = 0;
		}

		p_devStatus->wifi_setting = p_statedata[id + 29];
		p_devStatus->displayboard_version = p_statedata[id + 30];
		p_devStatus->environment_humidity = p_statedata[id + 31];
		p_devStatus->model_type = p_statedata[id + 32];
		if ((p_statedata[id + 32] & 0x01))   //有智能模式
		{
			p_devConfig->fuzzy_mode = 1;
		}
		else
		{
			p_devConfig->fuzzy_mode = 0;
		}

		if ((p_statedata[id + 32] & 0x02) >> 1)   //有节能模式
		{
			p_devConfig->save_mode = 1;
		}
		else
		{
			p_devConfig->save_mode = 0;
		}

		if ((p_statedata[id + 32] & 0x04) >> 2)   //有假日模式
		{
			p_devConfig->holiday_mode = 1;
		}
		else
		{
			p_devConfig->holiday_mode = 0;
		}

		if ((p_statedata[id + 32] & 0x08) >> 3)   //有速冻模式
		{
			p_devConfig->sf_mode = 1;
		}
		else
		{
			p_devConfig->sf_mode = 0;
		}

		if ((p_statedata[id + 32] & 0x10) >> 4)   //有速冷模式
		{
			p_devConfig->sr_mode = 1;
		}
		else
		{
			p_devConfig->sr_mode = 0;
		}

		p_devStatus->refrigerator_min_temperature = p_statedata[id + 33] - 40;
		p_devStatus->refrigerator_max_temperature = p_statedata[id + 34] - 40;

		p_devConfig->refrigerator_min_temperature = p_statedata[id + 33] - 40;
		p_devConfig->refrigerator_max_temperature = p_statedata[id + 34] - 40;

		p_devStatus->variation_min_temperature = p_statedata[id + 35] - 40;
		p_devStatus->variation_max_temperature = p_statedata[id + 36] - 40;

		p_devConfig->variation_min_temperature = p_statedata[id + 35] - 40;
		p_devConfig->variation_max_temperature = p_statedata[id + 36] - 40;

		p_devStatus->freeze_min_temperature = p_statedata[id + 37] - 40;
		p_devStatus->freeze_max_temperature = p_statedata[id + 38] - 40;

		p_devConfig->freeze_min_temperature = p_statedata[id + 37] - 40;
		p_devConfig->freeze_max_temperature = p_statedata[id + 38] - 40;

		p_devStatus->wifi_next_sendtime = p_statedata[id + 39];
		p_devStatus->open_the_door_alarm = p_statedata[id + 40];
		p_devStatus->displayboard_brand = p_statedata[id + 41];

		p_devStatus->refrigerator_door_open_time = p_statedata[id + 42];
		p_devStatus->variation_door_open_time = p_statedata[id + 43];
		p_devStatus->freeze_door_open_time = p_statedata[id + 44];
		p_devStatus->wifi_handshake_fault_flag = p_statedata[id + 45];

		return 0;
	}
	else
	{
		return rst;
	}
}

/*   解析102指令----智能家居2.0  
unsigned char* praw_state_buf:冰箱上传的原始数据；
unsigned char* pstate_buf:解析库解析后的数据*/
int parse_state(unsigned char* praw_state_buf, const size_t raw_state_buf_len, RefParam* pref_param, unsigned char* pstate_buf, int* pstate_buf_len)
{
	F_LOGD("In parse state method");
	unsigned char id = 0;
	//unsigned char utmp=0;
	//signed char stmp=0;
	unsigned char rst = 0;
	//unsigned char i;
	unsigned char p_statedata[_BUFFSIZE];
	*pstate_buf_len = STATUS_NUM * 4;
	//Frg_Status* p_devStatus;//
	rst = buff2dataAndCheck(praw_state_buf, p_statedata, _BUFFSIZE);
	int* statbuf = (int*)pstate_buf;
	
	if (0 == rst)
	{
		id = 15;

		//p_devStatus->refrigerator_temperature
        statbuf[0] = (int)(p_statedata[id + 1] - 40);
		//p_devStatus->freeze_temperature 
        statbuf[1] = (int)(p_statedata[id + 2] - 40);
		//p_devStatus->variation_temperature 
        statbuf[2] = (int)(p_statedata[id + 3] - 40);

		//p_devStatus->work_mode1 
        statbuf[3] = (int)(p_statedata[id + 4]);
		if ((p_statedata[id + 4] & 0x40) >> 6)   //fuzzy 智能模式
		{
			if (/*p_devStatus->refrigerator_temperature*/ pstate_buf[0] != 9)
			{
				//p_devStatus->refrigerator_temperature
				statbuf[0] = 5;
			}
			//p_devStatus->refrigerator_temperature  = 5;
			//p_devStatus->freeze_temperature 
			statbuf[1] = -18;
			//p_devStatus->variation_temperature;

			//p_devStatus->fuzzy_mode 
			statbuf[72] = 1;
			//p_devStatus->save_mode 
			statbuf[73] = 0;
			//p_devStatus->sf_mode 
			statbuf[75] = 0;
			//p_devStatus->sr_mode
			statbuf[76] = 0;
			//p_devStatus->holiday_mode 
			statbuf[77] = 0;
		}

		if ((p_statedata[id + 4] & 0x20) >> 5)   //save  节能模式
		{
			if (/*p_devStatus->refrigerator_temperature*/ statbuf[0] != 9)
			{
				//p_devStatus->refrigerator_temperature 
				statbuf[0] = 6;
			}
			//p_devStatus->refrigerator_temperature  = 6;
			//p_devStatus->freeze_temperature 
			statbuf[1] = -17;
			//p_devStatus->variation_temperature;

			//p_devStatus->fuzzy_mode 
			statbuf[72] = 0;
			//p_devStatus->save_mode 
			statbuf[73] = 1;
			//p_devStatus->sf_mode 
			statbuf[75] = 0;
			//p_devStatus->sr_mode 
			statbuf[76] = 0;
			//p_devStatus->holiday_mode 
			statbuf[77] = 0;
		}

		if ((p_statedata[id + 4] & 0x04) >> 2)   //sf 速冻模式
		{
			//p_devStatus->refrigerator_temperature;
			//p_devStatus->freeze_temperature 
			statbuf[1] = -25;
			//p_devStatus->variation_temperature;

			//p_devStatus->fuzzy_mode 
			statbuf[72] = 0;
			//p_devStatus->save_mode
			statbuf[73] = 0;
			//p_devStatus->sf_mode 
			statbuf[75] = 1;
			//p_devStatus->sr_mode 
			statbuf[76] = 0;
			//p_devStatus->holiday_mode 
			statbuf[77] = 0;
		}

		if (p_statedata[id + 4] & 0x01)   //sr 速冷模式
		{
			if (/*p_devStatus->refrigerator_temperature*/ statbuf[0] != 9)
			{
				//p_devStatus->refrigerator_temperature  
				statbuf[0] = 2;
			}
			//p_devStatus->refrigerator_temperature = 2;
			//p_devStatus->freeze_temperature ;
			//p_devStatus->variation_temperature;

			//p_devStatus->fuzzy_mode 
			statbuf[72] = 0;
			//p_devStatus->save_mode 
			statbuf[73] = 0;
			//p_devStatus->sf_mode 
			statbuf[75] = 0;
			//p_devStatus->sr_mode 
			statbuf[76] = 1;
			//p_devStatus->holiday_mode 
			statbuf[77] = 0;
		}

		//p_devStatus->work_mode2 
        statbuf[4] = (int)(p_statedata[id + 5]);
		if ((p_statedata[id + 5] & 0x40) >> 6)   //holiday  假日模式
		{
			if (/*p_devStatus->refrigerator_temperature*/ statbuf[0] != 9)
			{
				//p_devStatus->refrigerator_temperature 
				statbuf[0] = 15;
			}
			//p_devStatus->refrigerator_temperature = 15;
			//p_devStatus->freeze_temperature 
			statbuf[1] = -18;
			//p_devStatus->variation_temperature;

			//p_devStatus->fuzzy_mode 
			statbuf[72] = 0;
			//p_devStatus->save_mode 
			statbuf[73] = 0;
			//p_devStatus->sf_mode 
			statbuf[75] = 0;
			//p_devStatus->sr_mode 
			statbuf[76] = 0;
			//p_devStatus->holiday_mode 
			statbuf[77] = 1;
		}

		//p_devStatus->mainboard_version
        statbuf[5] = (int)(p_statedata[id + 6]);

		//p_devStatus->refrigerator_real_temperature 
        statbuf[6] = (int)(p_statedata[id + 7] - 40);
		//p_devStatus->freeze_real_temperature 
        statbuf[7] = (int)(p_statedata[id + 8] - 40);
		//p_devStatus->variation_real_temperature 
        statbuf[8] = (int)(p_statedata[id + 9] - 40);
		//p_devStatus->environment_real_temperature 
        statbuf[9] =(int)(p_statedata[id + 10] - 40);

		//p_devStatus->refrigerator_sensor_real_temperature 
        statbuf[11] = (int)(p_statedata[id + 11] - 40);
		//p_devStatus->freeze_sensor_real_temperature 
        statbuf[10] = (int)(p_statedata[id + 12] - 40);
		//p_devStatus->variation_sensor_real_temperature 
        statbuf[12] = (int)(p_statedata[id + 13] - 40);

		//p_devStatus->sensor_failure_status 
        statbuf[13] = (int)(p_statedata[id + 14]);
		//p_devStatus->control_failure_status 
        statbuf[14] = (int)(p_statedata[id + 15]);

		//p_devStatus->running_status 
        statbuf[15] = (int)(p_statedata[id + 16]);
		//p_devStatus->mainboard_type 
        statbuf[16] = (int)(p_statedata[id + 17]);
		//p_devStatus->displayboard_key_setting 
        statbuf[17] = (int)(p_statedata[id + 18]);
		//p_devStatus->displayboard_type 
        statbuf[18] = (int)(p_statedata[id + 19]);
		//p_devStatus->compressor_frequency 
        statbuf[19] = (int)(p_statedata[id + 20]);

		////p_devStatus->refrigerator_poweron_ad 
        statbuf[20] = (int)(p_statedata[id + 21]);
		////p_devStatus->refrigerator_poweroff_ad
        statbuf[21] = (int)(p_statedata[id + 22]);
		//p_devStatus->freeze_poweron_ad
		statbuf[22] = (int)(p_statedata[id + 23]);
		//p_devStatus->freeze_poweroff_ad
		statbuf[23] = (int)(p_statedata[id + 24]);
		//p_devStatus->variation_poweron_ad 
		statbuf[24] = (int)(p_statedata[id + 25]);
		//p_devStatus->variation_poweroff_ad 
		statbuf[25] = (int)(p_statedata[id + 26]);
		//p_devStatus->sensor_failure_status2 
		statbuf[26] = (int)(p_statedata[id + 27]);

		//p_devStatus->temperature_room_judge 
		statbuf[27] = (int)(p_statedata[id + 28]);

		//p_devStatus->wifi_setting
		statbuf[28] = (int)(p_statedata[id + 29]);
		//p_devStatus->displayboard_version 
		statbuf[29] = (int)(p_statedata[id + 30]);
		//p_devStatus->environment_humidity 
		statbuf[30] = (int)(p_statedata[id + 31]);
		//p_devStatus->model_type 
		statbuf[31] = (int)(p_statedata[id + 32]);

		//p_devStatus->refrigerator_min_temperature
		statbuf[32] = (int)(p_statedata[id + 33] - 40);
		//p_devStatus->refrigerator_max_temperature 
		statbuf[33] = (int)(p_statedata[id + 34] - 40);

		//p_devStatus->variation_min_temperature 
		statbuf[34] = (int)(p_statedata[id + 35] - 40);
		//p_devStatus->variation_max_temperature 
		statbuf[35] = (int)(p_statedata[id + 36] - 40);

		//p_devStatus->freeze_min_temperature 
		statbuf[36] = (int)(p_statedata[id + 37] - 40);
		//p_devStatus->freeze_max_temperature 
		statbuf[37] = (int)(p_statedata[id + 38] - 40);

		//p_devStatus->wifi_next_sendtime 
		statbuf[38] = (int)(p_statedata[id + 39]);
		//p_devStatus->open_the_door_alarm 
		statbuf[39] = (int)(p_statedata[id + 40]);
		//p_devStatus->displayboard_brand 
		statbuf[40] = (int)(p_statedata[id + 41]);

		//p_devStatus->refrigerator_door_open_time 
		statbuf[41] = (int)(p_statedata[id + 42]);
		//p_devStatus->variation_door_open_time 
		statbuf[42] = (int)(p_statedata[id + 43]);
		//p_devStatus->freeze_door_open_time 
		statbuf[43] = (int)(p_statedata[id + 44]);
		//wifi_handshake_fault_flag
		statbuf[44] = (int)(p_statedata[id + 45]);

		statbuf[45] = (int)(p_statedata[id + 46]);
		statbuf[46] = (int)(p_statedata[id + 47]);
		statbuf[47] = (int)(p_statedata[id + 48]);
		statbuf[48] = (int)(p_statedata[id + 49]);
		statbuf[49] = (int)(p_statedata[id + 50]);
		statbuf[50] = (int)(p_statedata[id + 51]);
		statbuf[51] = (int)(p_statedata[id + 52]);
		statbuf[52] = (int)(p_statedata[id + 53]);
		statbuf[53] = (int)(p_statedata[id + 54]);
		statbuf[54] = (int)(p_statedata[id + 55]);
		statbuf[55] = (int)(p_statedata[id + 56]);
		statbuf[56] = (int)(p_statedata[id + 57]);
		statbuf[57] = (int)(p_statedata[id + 58]);
		statbuf[58] = (int)(p_statedata[id + 59]);
		statbuf[59] = (int)(p_statedata[id + 60]);

		statbuf[60] = 65535;
		statbuf[61] = 65535;
		statbuf[62] = 65535;
		statbuf[63] = 65535;
		statbuf[64] = 65535;
		statbuf[65] = 65535;
		statbuf[66] = 65535;
		statbuf[67] = 65535;
		statbuf[68] = 65535;
		statbuf[69] = 65535;
		statbuf[70] = 65535;
		statbuf[71] = 65535;

		statbuf[72] = (int)(p_statedata[id + 73]);
		statbuf[73] = (int)(p_statedata[id + 74]);
		statbuf[74] = 65535;
		statbuf[75] = (int)(p_statedata[id + 76]);
		statbuf[76] = (int)(p_statedata[id + 77]);
		statbuf[77] = (int)(p_statedata[id + 78]);
		statbuf[78] = 65535;
        
		return 0;
	}
	else
	{
		return rst;
	}
}

/**
*   用途：  获取多个101命令（控制命令）对应的原始命令帧数据（包含帧头+内容+帧尾）及101命令的互斥结果
*   参数：
*      p_curStatus  IN-指向当前状态结构体的指针, OUT-互斥逻辑处理后的状态
*           p_command      命令数组头指针
*           cmd_count    命令个数
*           p_cmdBuf     原始命令帧缓存，由应用层申请，底层实现进行赋值
*           len          IN-命令帧缓存总长度, OUT-命令帧实际长度，由底层实现进行赋值
*   返回值：0         命令可以执行
*           其他负值  如果不可以执行，则返回相应的结果，由冰箱公司给出具体返回值，UI根据返回值弹出提示信息
注意：如果为多命令时，命令顺序为，开关机、模式、风速、其他
*/
int frg_cmd_control_101_0(Frg_Status* p_curStatus, CMD* p_command, unsigned int cmd_count, unsigned char* p_cmdBuf, unsigned int* len)
{
	unsigned int i;
	unsigned char p_configdata[_BUFFSIZE];
	unsigned char rst;
	unsigned char id;
	unsigned int  u16_sum;
	CMD command;
	for (i = 0; i < _BUFFSIZE; i++)
	{
		p_configdata[i] = 0;
	}
	id = 15;
	rst = 0;
	for (i = 0; i < cmd_count; i++)
	{
		command = *p_command;

		switch (command.cmdid)
		{
		case SET_REFRIGERATOR_TEMPERATURE:
		{
			p_curStatus->refrigerator_temperature = command.p_param;
			p_configdata[id + 1] = (command.p_param + 40) << 1 | 0x01;
			break;
		}

		case SET_FREEZE_TEMPERATURE:
		{
			p_curStatus->freeze_temperature = command.p_param;
			p_configdata[id + 2] = (command.p_param + 40) << 1 | 0x01;
			break;
		}
		case SET_VARIATION_TEMPERATURE:
		{
			p_curStatus->variation_temperature = command.p_param;
			p_configdata[id + 3] = (command.p_param + 40) << 1 | 0x01;
			break;
		}

		/*case SET_WORK_MODE1:
		{
		//p_curStatus->work_mode1= command.p_param;
		p_configdata[id+4] = command.p_param;
		break;
		}

		  case SET_WORK_MODE2:
		  {
		  //p_curStatus->work_mode2= command.p_param;
		  p_configdata[id+5] = command.p_param;
		  break;
	}*/

		case SET_FUZZY_MODE://智能模式
		{
			p_curStatus->fuzzy_mode = 0x01;
			p_curStatus->save_mode = 0x00;
			p_curStatus->show_mode = 0x00;
			p_curStatus->sf_mode = 0x00;
			p_curStatus->sr_mode = 0x00;
			p_curStatus->holiday_mode = 0x00;
			//p_curStatus->debacilli_mode= 0x00;//del 2016-06-21

			if (p_curStatus->refrigerator_temperature != 0x09)
			{
				p_curStatus->refrigerator_temperature = 0x05;
			}
			//p_curStatus->refrigerator_temperature = 0x05;
			p_curStatus->freeze_temperature = 0xEE;
			//p_curStatus->variation_temperature= 0x05;

			p_configdata[id + 4] = 0x81;
			p_configdata[id + 5] = 0x01;
			break;
		}

		case SET_HOLIDAY_MODE://假日模式
		{
			p_curStatus->fuzzy_mode = 0x00;
			p_curStatus->save_mode = 0x00;
			p_curStatus->show_mode = 0x00;
			p_curStatus->sf_mode = 0x00;
			p_curStatus->sr_mode = 0x00;
			p_curStatus->holiday_mode = 0x01;
			//p_curStatus->debacilli_mode= 0x00;//del 2016-06-21

			if (p_curStatus->refrigerator_temperature != 0x09)
			{
				p_curStatus->refrigerator_temperature = 0x0F;
			}
			//p_curStatus->refrigerator_temperature = 0x0F;
			p_curStatus->freeze_temperature = 0xEE;
			//p_curStatus->variation_temperature= 0x05;

			p_configdata[id + 4] = 0x01;
			p_configdata[id + 5] = 0x81;
			break;
		}

		case SET_SR_MODE://速冷模式
		{
			p_curStatus->fuzzy_mode = 0x00;
			p_curStatus->save_mode = 0x00;
			p_curStatus->show_mode = 0x00;
			p_curStatus->sf_mode = 0x00;
			p_curStatus->sr_mode = 0x01;
			p_curStatus->holiday_mode = 0x00;
			//p_curStatus->debacilli_mode= 0x00;////del 2016-06-21

			if (p_curStatus->refrigerator_temperature != 0x09)
			{
				p_curStatus->refrigerator_temperature = 0x02;
			}
			//p_curStatus->refrigerator_temperature = 0x02;
			//p_curStatus->freeze_temperature= 0xE7;
			//p_curStatus->variation_temperature= 0x05;

			p_configdata[id + 4] = 0x09;
			p_configdata[id + 5] = 0x01;
			break;

		}

		case SET_SF_MODE://速冻模式
		{
			p_curStatus->fuzzy_mode = 0x00;
			p_curStatus->save_mode = 0x00;
			p_curStatus->show_mode = 0x00;
			p_curStatus->sf_mode = 0x01;
			p_curStatus->sr_mode = 0x00;
			p_curStatus->holiday_mode = 0x00;
			//p_curStatus->debacilli_mode= 0x00;//del 2016-06-21

			if (p_curStatus->refrigerator_temperature == 0x0F)
			{
				p_curStatus->refrigerator_temperature = 0x05;
			}
			//p_curStatus->refrigerator_temperature = 0x05;
			p_curStatus->freeze_temperature = 0xE7;
			//p_curStatus->variation_temperature= 0x05;

			p_configdata[id + 4] = 0x09;
			p_configdata[id + 5] = 0x01;
			break;

		}

		case SET_SAVE_MODE://节能模式
		{
			p_curStatus->fuzzy_mode = 0x00;
			p_curStatus->save_mode = 0x01;
			p_curStatus->show_mode = 0x00;
			p_curStatus->sf_mode = 0x00;
			p_curStatus->sr_mode = 0x00;
			p_curStatus->holiday_mode = 0x00;
			//p_curStatus->debacilli_mode= 0x00;////del 2016-06-21

			if (p_curStatus->refrigerator_temperature != 9)
			{
				p_curStatus->refrigerator_temperature = 0x06;
			}
			//p_curStatus->refrigerator_temperature = 0x06;
			p_curStatus->freeze_temperature = 0xEF;
			//p_curStatus->variation_temperature= 0x05;

			p_configdata[id + 4] = 0x41;
			p_configdata[id + 5] = 0x01;
			break;
		}

		case SET_NO_MODE://自定义模式
		{
			p_curStatus->fuzzy_mode = 0x00;
			p_curStatus->save_mode = 0x00;
			p_curStatus->show_mode = 0x00;
			p_curStatus->sf_mode = 0x00;
			p_curStatus->sr_mode = 0x00;
			p_curStatus->holiday_mode = 0x00;
			//p_curStatus->debacilli_mode= 0x00;////del 2016-06-21

			if (p_curStatus->refrigerator_temperature == 0x0F)
			{
				p_curStatus->refrigerator_temperature = 0x05;
			}

			p_configdata[id + 4] = 0x01;
			p_configdata[id + 5] = 0x01;
			break;
		}


		case SET_COMPRESSOR_FREQUENCY:
		{
			p_curStatus->compressor_frequency = command.p_param;
			p_configdata[id + 6] = command.p_param;
			break;
		}

		case SET_NET_STATUS:
		{
			p_curStatus->net_status = command.p_param;
			p_configdata[id + 7] = command.p_param;
			break;
		}

		case SET_FACTORY_CHECK:
		{
			p_curStatus->factory_check = command.p_param;
			p_configdata[id + 8] = command.p_param;
			break;
		}

		case SET_FRIDGE_RESET:
		{
			p_curStatus->fridge_reset = command.p_param;
			p_configdata[id + 9] = command.p_param;
			break;
		}

		case SET_WIFI_STATUS1:
		{
			p_curStatus->wifi_status1 = command.p_param;
			p_configdata[id + 10] = command.p_param;
			break;
		}

		case SET_WIFI_STATUS2:
		{
			p_curStatus->wifi_status2 = command.p_param;
			p_configdata[id + 11] = command.p_param;
			break;
		}

		case SET_YEAR_HIGH_DATE:
		{
			p_curStatus->year_high_date = command.p_param;
			p_configdata[id + 12] = command.p_param;
			break;
		}

		case SET_YEAR_LOW_DATE:
		{
			p_curStatus->year_low_date = command.p_param;
			p_configdata[id + 13] = command.p_param;
			break;
		}

		case SET_MONTH_DATE:

		{
			p_curStatus->month_date = command.p_param;
			p_configdata[id + 14] = command.p_param;
			break;
		}


		case SET_DAY_DATE:
		{
			p_curStatus->day_date = command.p_param;
			p_configdata[id + 15] = command.p_param;
			break;
		}

		case SET_HOUR_DATE:
		{
			p_curStatus->hour_date = command.p_param;
			p_configdata[id + 16] = command.p_param;
			break;
		}

		case SET_MINUTE_DATE:
		{
			p_curStatus->minute_date = command.p_param;
			p_configdata[id + 17] = command.p_param;
			break;
		}

		case SET_HISENSE_CLOUD:
		{
			p_curStatus->hisense_cloud = command.p_param;
			p_configdata[id + 18] = command.p_param;
			break;
		}

		default:
			break;
		}
		p_command++;
	}
	p_configdata[0x00] = _LNKKEY;
	p_configdata[0x01] = _LNKHEAD;
	p_configdata[0x02] = 0x00;
	p_configdata[0x03] = 0x40;
	p_configdata[0x04] = 0x33;
	p_configdata[0x05] = 0x00;
	p_configdata[0x06] = 0x00;
	p_configdata[0x07] = 0x02;
	p_configdata[0x08] = 0x01;
	p_configdata[0x09] = 0xfe;
	p_configdata[0x0a] = 0x01;
	p_configdata[0x0b] = 0x00;
	p_configdata[0x0c] = 0x00;
	p_configdata[0x0d] = 0x65;
	p_configdata[0x0e] = 0x00;
	p_configdata[0x0f] = 0x00;
	u16_sum = 0;
	for (i = 2; i < 56; i++)
	{
		u16_sum += p_configdata[i];
	}
	p_configdata[i] = (u16_sum >> 8);
	p_configdata[i + 1] = (u16_sum & 0xff);
	p_configdata[i + 2] = _LNKKEY;
	p_configdata[i + 3] = _LNKEND;
	for (i = 0; i < *len; i++)
	{
		p_cmdBuf[i] = 0;
	}
	*len = data2buff(p_configdata, p_cmdBuf);
	return rst;
}

/*   解析101指令----智能家居2.0  
const int* porg_cmd_buf:设置的命令;
unsigned char* pstate_buf:设置完，解析库解析后的冰箱状态;
unsigned char* praw_cmd_buf:设置完命令，上层传给冰箱端的buf数据*/
int build_cmd(const int* porg_cmd_buf, unsigned char* pstate_buf, int* pstate_buf_len, RefParam* pref_param, unsigned char* praw_cmd_buf, int* praw_cmd_buf_len)
{
	int i;
	int j;//add for
	unsigned char p_configdata[_BUFFSIZE];
	unsigned char rst;
	unsigned char id;
	unsigned int  u16_sum;
	int* p_state;

	p_state = (int*)pstate_buf;
    
	//CMD command;
	//Frg_Status* p_curStatus;//
	//CMD* p_command;//----command.cmdid
	//unsigned int cmd_count;//----porg_cmd_buf[0]
	//unsigned char* p_cmdBuf;//
	//unsigned int* len;//
	for (i = 0; i < _BUFFSIZE; i++)
	{
		p_configdata[i] = 0;
	}
	id = 15;
	rst = 0;
	j = 1;
	for (i = 0; i < porg_cmd_buf[0]/*cmd_count*/; i++)
	{
		//command = *p_command;

		switch (porg_cmd_buf[2 * j-1]/*command.cmdid*/)
		{
		case SET_REFRIGERATOR_TEMPERATURE:
		{
			//p_curStatus->refrigerator_temperature 
			
            p_state[0] = porg_cmd_buf[2 * j]/*command.p_param*/;
			p_configdata[id + 1] = ((unsigned char)porg_cmd_buf[2 * j]/*command.p_param*/ + 40) << 1 | 0x01;
			break;
		}

		case SET_FREEZE_TEMPERATURE:
		{
			//p_curStatus->freeze_temperature 
			p_state[1] = porg_cmd_buf[2 * j]/*command.p_param*/;
			p_configdata[id + 2] = ((unsigned char)porg_cmd_buf[2 * j]/*command.p_param*/ + 40) << 1 | 0x01;
			break;
		}
		case SET_VARIATION_TEMPERATURE:
		{
			//p_curStatus->variation_temperature 
			p_state[2] = porg_cmd_buf[2 * j]/*command.p_param*/;
			p_configdata[id + 3] = ((unsigned char)porg_cmd_buf[2 * j]/*command.p_param*/ + 40) << 1 | 0x01;
			break;
		}

		/*case SET_WORK_MODE1:
		{
		//p_curStatus->work_mode1= command.p_param;
		p_configdata[id+4] = command.p_param;
		break;
		}

		  case SET_WORK_MODE2:
		  {
		  //p_curStatus->work_mode2= command.p_param;
		  p_configdata[id+5] = command.p_param;
		  break;
	}*/

		case SET_FUZZY_MODE://智能模式
		{
			//p_curStatus->fuzzy_mode 
			p_state[72] = 0x01;
			//p_curStatus->save_mode 
			p_state[73] = 0x00;
			//p_curStatus->show_mode 
			p_state[74] = 0x00;
			//p_curStatus->sf_mode 
			p_state[75] = 0x00;
			//p_curStatus->sr_mode 
			p_state[76] = 0x00;
			//p_curStatus->holiday_mode 
			p_state[77] = 0x00;
			//p_curStatus->debacilli_mode 
			//p_state[78] = 0x00;//del 20160622 for debacilli_mode
            //F_LOGD("Set fuzzy mode");
			if (/*p_curStatus->refrigerator_temperature*/ p_state[0] != 0x09)
			{
				//p_curStatus->refrigerator_temperature 
				p_state[0] = 0x05;
				//F_LOGD("Set fuzzy mode 01");
			}
			//p_curStatus->refrigerator_temperature = 0x05;
			//p_curStatus->freeze_temperature 
			p_state[1] = -18;
			//p_curStatus->variation_temperature= 0x05;

			p_configdata[id + 4] = 0x81;
			p_configdata[id + 5] = 0x01;
			break;
		}

		case SET_HOLIDAY_MODE://假日模式
		{
			//p_curStatus->fuzzy_mode 
			p_state[72] = 0x00;
			//p_curStatus->save_mode 
			p_state[73] = 0x00;
			//p_curStatus->show_mode 
			p_state[74] = 0x00;
			//p_curStatus->sf_mode 
			p_state[75] = 0x00;
			//p_curStatus->sr_mode 
			p_state[76] = 0x00;
			//p_curStatus->holiday_mode 
			p_state[77] = 0x01;
			//p_curStatus->debacilli_mode 
			//p_state[78] = 0x00;//del 20160622 for debacilli_mode

			if (/*p_curStatus->refrigerator_temperature*/  p_state[0] != 0x09)
			{
				//p_curStatus->refrigerator_temperature  
				//F_LOGD("Set holiday mode");
				p_state[0] = 0x0F;
			}
			//p_curStatus->refrigerator_temperature = 0x0F;
			//p_curStatus->freeze_temperature 
			p_state[1] = -18;
			//p_curStatus->variation_temperature= 0x05;

			p_configdata[id + 4] = 0x01;
			p_configdata[id + 5] = 0x81;
			break;
		}

		case SET_SR_MODE://速冷模式
		{
			//p_curStatus->fuzzy_mode 
			p_state[72] = 0x00;
			//p_curStatus->save_mode  
			p_state[73] = 0x00;
			//p_curStatus->show_mode 
			p_state[74] = 0x00;
			//p_curStatus->sf_mode 
			p_state[75] = 0x00;
			//p_curStatus->sr_mode 
			p_state[76] = 0x01;
			//p_curStatus->holiday_mode 
			p_state[77] = 0x00;
			//p_curStatus->debacilli_mode 
			//p_state[78] = 0x00;//del 20160622 for debacilli_mode

			if (/*p_curStatus->refrigerator_temperature*/ p_state[0] != 0x09)
			{
				//p_curStatus->refrigerator_temperature  
				p_state[0] = 0x02;
			}
			//p_curStatus->refrigerator_temperature = 0x02;
			//p_curStatus->freeze_temperature= 0xE7;
			//p_curStatus->variation_temperature= 0x05;

			p_configdata[id + 4] = 0x09;
			p_configdata[id + 5] = 0x01;
			break;

		}

		case SET_SF_MODE://速冻模式
		{
			//p_curStatus->fuzzy_mode 
			p_state[72] = 0x00;
			//p_curStatus->save_mode 
			p_state[73] = 0x00;
			//p_curStatus->show_mode 
			p_state[74] = 0x00;
			//p_curStatus->sf_mode 
			p_state[75] = 0x01;
			//p_curStatus->sr_mode 
			p_state[76] = 0x00;
			//p_curStatus->holiday_mode 
			p_state[77] = 0x00;
			//p_curStatus->debacilli_mode 
			//p_state[78] = 0x00;//del 20160622 for debacilli_mode

			if (/*p_curStatus->refrigerator_temperature*/ p_state[0] == 0x0F)
			{
				//p_curStatus->refrigerator_temperature  
				p_state[0] = 0x05;
			}
			//p_curStatus->refrigerator_temperature = 0x05;
			//p_curStatus->freeze_temperature 
			p_state[1] = -25;
			//p_curStatus->variation_temperature= 0x05;

			p_configdata[id + 4] = 0x09;
			p_configdata[id + 5] = 0x01;
			break;

		}

		case SET_SAVE_MODE://节能模式
		{
			//p_curStatus->fuzzy_mode 
			p_state[72] = 0x00;
			//p_curStatus->save_mode 
			p_state[73] = 0x01;
			//p_curStatus->show_mode 
			p_state[74] = 0x00;
			//p_curStatus->sf_mode 
			p_state[75] = 0x00;
			//p_curStatus->sr_mode 
			p_state[76] = 0x00;
			//p_curStatus->holiday_mode 
			p_state[77] = 0x00;
			//p_curStatus->debacilli_mode 
			//p_state[78]= 0x00;//del 20160622 for debacilli_mode

			if (/*p_curStatus->refrigerator_temperature*/ p_state[0] != 9)
			{
				//p_curStatus->refrigerator_temperature 
				p_state[0] = 0x06;
			}
			//p_curStatus->refrigerator_temperature = 0x06;
			//p_curStatus->freeze_temperature 
			p_state[1] = -17;
			//p_curStatus->variation_temperature= 0x05;

			p_configdata[id + 4] = 0x41;
			p_configdata[id + 5] = 0x01;
			break;
		}

		case SET_NO_MODE://自定义模式
		{
			//p_curStatus->fuzzy_mode 
			p_state[72] = 0x00;
			//p_curStatus->save_mode 
			p_state[73] = 0x00;
			//p_curStatus->show_mode 
			p_state[74] = 0x00;
			//p_curStatus->sf_mode 
			p_state[75] = 0x00;
			//p_curStatus->sr_mode 
			p_state[76] = 0x00;
			//p_curStatus->holiday_mode 
			p_state[77] = 0x00;
			//p_curStatus->debacilli_mode 
			//p_state[78] = 0x00;//del 20160622 for debacilli_mode

			if (/*p_curStatus->refrigerator_temperature*/ p_state[0] == 0x0F)
			{
				//p_curStatus->refrigerator_temperature 
				p_state[0] = 0x05;
			}

			p_configdata[id + 4] = 0x01;
			p_configdata[id + 5] = 0x01;
			break;
		}


		case SET_COMPRESSOR_FREQUENCY:
		{
			//p_curStatus->compressor_frequency 
			p_state[19] = porg_cmd_buf[2 * j]/*command.p_param*/;
			p_configdata[id + 6] = (unsigned char)porg_cmd_buf[2 * j]/*command.p_param*/;
			break;
		}

		case SET_NET_STATUS:
		{
			//p_curStatus->net_status 
			p_state[60] = porg_cmd_buf[2 * j]/*command.p_param*/;
			p_configdata[id + 7] = (unsigned char)porg_cmd_buf[2 * j]/*command.p_param*/;
			break;
		}

		case SET_FACTORY_CHECK:
		{
			//p_curStatus->factory_check 
			p_state[61] = porg_cmd_buf[2 * j]/*command.p_param*/;
			p_configdata[id + 8] = (unsigned char)porg_cmd_buf[2 * j]/*command.p_param*/;
			break;
		}

		case SET_FRIDGE_RESET:
		{
			//p_curStatus->fridge_reset 
			p_state[62] = porg_cmd_buf[2 * j]/*command.p_param*/;
			p_configdata[id + 9] = (unsigned char)porg_cmd_buf[2 * j]/*command.p_param*/;
			break;
		}

		case SET_WIFI_STATUS1:
		{
			//p_curStatus->wifi_status1 
			p_state[63] = porg_cmd_buf[2 * j]/*command.p_param*/;
			p_configdata[id + 10] = (unsigned char)porg_cmd_buf[2 * j]/*command.p_param*/;
			break;
		}

		case SET_WIFI_STATUS2:
		{
			//p_curStatus->wifi_status2 
			p_state[64] = porg_cmd_buf[2 * j]/*command.p_param*/;
			p_configdata[id + 11] = (unsigned char)porg_cmd_buf[2 * j]/*command.p_param*/;
			break;
		}

		case SET_YEAR_HIGH_DATE:
		{
			//p_curStatus->year_high_date 
			p_state[65] = porg_cmd_buf[2 * j]/*command.p_param*/;
			p_configdata[id + 12] = (unsigned char)porg_cmd_buf[2 * j]/*command.p_param*/;
			break;
		}

		case SET_YEAR_LOW_DATE:
		{
			//p_curStatus->year_low_date 
			p_state[66] = porg_cmd_buf[2 * j]/*command.p_param*/;
			p_configdata[id + 13] = (unsigned char)porg_cmd_buf[2 * j]/*command.p_param*/;
			break;
		}

		case SET_MONTH_DATE:

		{
			//p_curStatus->month_date 
			p_state[67] = porg_cmd_buf[2 * j]/*command.p_param*/;
			p_configdata[id + 14] = (unsigned char)porg_cmd_buf[2 * j]/*command.p_param*/;
			break;
		}


		case SET_DAY_DATE:
		{
			//p_curStatus->day_date 
			p_state[68] = porg_cmd_buf[2 * j]/*command.p_param*/;
			p_configdata[id + 15] = (unsigned char)porg_cmd_buf[2 * j]/*command.p_param*/;
			break;
		}

		case SET_HOUR_DATE:
		{
			//p_curStatus->hour_date 
			p_state[69] = porg_cmd_buf[2 * j]/*command.p_param*/;
			p_configdata[id + 16] = (unsigned char)porg_cmd_buf[2 * j]/*command.p_param*/;
			break;
		}

		case SET_MINUTE_DATE:
		{
			//p_curStatus->minute_date 
			p_state[70] = porg_cmd_buf[2 * j]/*command.p_param*/;
			p_configdata[id + 17] = (unsigned char)porg_cmd_buf[2 * j]/*command.p_param*/;
			break;
		}

		case SET_HISENSE_CLOUD:
		{
			//p_curStatus->hisense_cloud 
			p_state[71] = porg_cmd_buf[2 * j]/*command.p_param*/;
			p_configdata[id + 18] = (unsigned char)porg_cmd_buf[2 * j]/*command.p_param*/;
			break;
		}

		default:
			break;
		}
		//p_command++;
		j++;
	}
	p_configdata[0x00] = _LNKKEY;
	p_configdata[0x01] = _LNKHEAD;
	p_configdata[0x02] = 0x00;
	p_configdata[0x03] = 0x40;
	p_configdata[0x04] = 0x33;
	p_configdata[0x05] = 0x00;
	p_configdata[0x06] = 0x00;
	p_configdata[0x07] = 0x02;
	p_configdata[0x08] = 0x01;
	p_configdata[0x09] = 0xfe;
	p_configdata[0x0a] = 0x01;
	p_configdata[0x0b] = 0x00;
	p_configdata[0x0c] = 0x00;
	p_configdata[0x0d] = 0x65;
	p_configdata[0x0e] = 0x00;
	p_configdata[0x0f] = 0x00;
	u16_sum = 0;
	for (i = 2; i < 56; i++)
	{
		u16_sum += p_configdata[i];
	}
	p_configdata[i] = (u16_sum >> 8);
	p_configdata[i + 1] = (u16_sum & 0xff);
	p_configdata[i + 2] = _LNKKEY;
	p_configdata[i + 3] = _LNKEND;
	for (i = 0; i < *praw_cmd_buf_len /*len*/; i++)
	{
		/*p_cmdBuf*/ praw_cmd_buf[i] = 0;
	}
	//uchar* pconfigdata = (uchar*)p_configdata;
	*praw_cmd_buf_len /*len*/ = data2buff(p_configdata,/*p_cmdBuf*/ praw_cmd_buf);
	return rst;
}

//int get_version(char* ver_buf,size_t len)
//{
//	ASSERT(ver_buf);
//	if(NULL == ver_buf)
//	{
//		return 0;//无效指针
//	}
//	char ver[] = "1.0.0";
//	int ret = memcpy_s(ver_buf,strlen(ver),ver,len);
//	if(0 == ret)
//	{
//		return 1;//成功
//	}
//	return ret;//返回错误代码
//}

int get_version(char* ver_buf, size_t len)
{
	char ver[] = "1.0.0";
	memcpy(ver_buf, ver, strlen(ver));
	return 1;
}

unsigned char ParseWGStatus_30_0(uchar* p_cmdBuf, WGCommonStatus* pWGStatus)
{
	unsigned char rst;
	unsigned char p_statedata[_BUFFSIZE];
	rst = buff2dataAndCheck(p_cmdBuf, p_statedata, _BUFFSIZE);
	if (0 == rst)
	{
		pWGStatus->LedReq = ((p_statedata[17] & 0x40) >> 6);
		pWGStatus->ConfigReq = ((p_statedata[17] & 0x20) >> 5);
		pWGStatus->ConfigSelectorReq = ((p_statedata[17] & 0x10) >> 4);
		pWGStatus->ResetReq = ((p_statedata[17] & 0x08) >> 3);
		pWGStatus->PowerOnTime = ((p_statedata[17] & 0x04) >> 2);
		pWGStatus->SmartCtlMode = ((p_statedata[17] & 0x02) >> 1);
		pWGStatus->StatusIsChanged = (p_statedata[17] & 0x01);
	}
	return rst;
}

/**
*   用途：将手机上发送的数据转换为为发送的缓冲区数据
*   p_configdata   手机发送的数据
*         sendbuff  发往模块的数据
*   返回值：0--失败 !0--成功
*/
unsigned char data2buff(uchar* p_configdata, uchar* sendbuff)
{
	unsigned char ilen;
	//static unsigned char resendkey;
	unsigned char i = 0;
	unsigned char n = 0;
	while (i < 255)
	{
		if ((*p_configdata == _LNKKEY) && (*(p_configdata + 1) == _LNKHEAD))
		{
			break;
		}
		p_configdata++;
		i++;
	}
	ilen = p_configdata[4] + 9;
	i = 0;
	n = 0;
	*sendbuff = *p_configdata;
	while (i < ilen)
	{
		i++;
		sendbuff++;
		p_configdata++;
		*sendbuff = *p_configdata;
		if (*p_configdata == _LNKKEY)
		{
			if ((*(p_configdata + 1) != _LNKHEAD) && (*(p_configdata + 1) != _LNKEND))
			{
				sendbuff++;
				*sendbuff = _LNKKEY;
				n++;
			}
		}
		n++;
	}
	return n;
}
/**
*   用途：将来自WIFI模块的数据转换为手机数据

  *   返回值：0--失败 非0--数据长度
*/
unsigned char  buff2data(uchar* recebuff, uchar* p_statedata)
{
	unsigned char i = 0;
	unsigned char n = 0;
	while (i < 255)
	{
		if ((*recebuff == _LNKKEY) && (*(recebuff + 1) == _LNKHEAD))
		{
			break;
		}
		recebuff++;
		i++;
	}
	while (i < 255)
	{
		*p_statedata = *recebuff;
		if ((*recebuff == _LNKKEY) && (*(recebuff + 1) == _LNKKEY))
		{
			recebuff++;
		}
		if ((*(recebuff - 1) == _LNKKEY) && (*recebuff == _LNKEND))
		{
			return n + 1;
		}
		p_statedata++;
		recebuff++;
		n++;
		i++;
	}
	return 0;
}

void frg_cmd_check_state_102_0(uchar* p_cmdBuf, int* len, uchar autoflag)
{
	p_cmdBuf[0] = 0xF4;
	p_cmdBuf[1] = 0xF5;
	p_cmdBuf[2] = 0x00;
	p_cmdBuf[3] = 0x40;
	p_cmdBuf[4] = 0x0C;
	p_cmdBuf[5] = 0x00;
	p_cmdBuf[6] = 0x00;
	p_cmdBuf[7] = 0x02;
	p_cmdBuf[8] = 0x01;
	p_cmdBuf[9] = 0xFE;
	p_cmdBuf[10] = 0x01;
	p_cmdBuf[11] = 0x00;
	p_cmdBuf[12] = 0x00;
	p_cmdBuf[13] = 0x66;
	p_cmdBuf[14] = 0x00;
	p_cmdBuf[15] = 0x00;
	p_cmdBuf[16] = 0x01;//autoflag
	p_cmdBuf[17] = 0x01;
	p_cmdBuf[18] = 0xB5;
	p_cmdBuf[19] = 0xF4;
	p_cmdBuf[20] = 0xFB;
	*len = 21;
}

unsigned char buff2dataAndCheck(uchar* recebuff, uchar* p_statedata, uchar ilen)
{
	unsigned char rst = 0;
	unsigned char i;
	for (i = 0; i < ilen; i++)
	{
		p_statedata[i] = 0;
	}
	buff2data(recebuff, p_statedata);
	rst = checkbuff_valid(p_statedata);
	return rst;
}
