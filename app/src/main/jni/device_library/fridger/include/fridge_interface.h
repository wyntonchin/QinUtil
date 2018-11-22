#ifndef _FRIDGE_BUSININESS
#define _FRIDGE_BUSININESS

#ifdef __cplusplus
extern "C" {
#endif

#define uchar   unsigned char
#define ushort  unsigned short
#define uint    unsigned int
#define ulong   unsigned long

#include "frgctl_state.h"
	//#include "stddef.h"

	typedef struct _cmd_msg
	{
		uchar  cmdid;    //命令id
		char p_param;  //命令值
		uchar autoflag; //手动/自动发送标志
		int    length;   //参数长度
	} CMD;

	/**
	*   用途：  获取冰箱控制命令对应的原始命令帧数据（包含帧头+内容+帧尾）
	*   参数：
	*      p_curStatus  指向当前状态结构体的指针,
	*      p_command      命令id+参数（见CMD结构体定义）
	*           p_cmdBuf     原始命令帧缓存，由应用层申请，底层实现进行赋值
	*           len          IN-命令帧缓存总长度, OUT-命令帧实际长度，由底层实现进行赋值
	*   返回值：0--成功   -1--失败
	*/
	int frg_cmd_control_101_0(Frg_Status* p_curStatus, CMD* p_command, unsigned int cmd_count, unsigned char* p_cmdBuf, unsigned int* len);


	/**
	*   用途：  获取查询冰箱状态命令对应的原始命令帧数据（包含帧头+内容+帧尾）
	*   参数：  p_cmdBuf     原始命令帧缓存，由应用层申请，底层实现进行赋值
	*           len          IN-命令帧缓存总长度, OUT-命令帧实际长度，由底层实现进行赋值
	*            autoflag     0-自动发送102指令；1--用户手动查询102#
	*   返回值：无
	*
	*/
	void frg_cmd_check_state_102_0(uchar *p_cmdBuf, int *len, uchar autoflag);

	//int parse_state(unsigned char* praw_state_buf,const size_t raw_state_buf_len,RefParam* pref_param,unsigned char* pstate_buf,int* pstate_buf_len);

	//int build_cmd(const int* porg_cmd_buf,unsigned char* pstate_buf,int* pstate_buf_len,RefParam* pref_param,unsigned char* praw_cmd_buf,int* praw_cmd_buf_len);

	//int get_version(char* ver_buf,size_t len);

	/**
	*   用途：  解析冰箱状态原始帧数据（包含帧头+内容+帧尾）
	*   参数：  p_cmdBuf 原始状态帧数据缓存
	*                     p_devStatus 冰箱的当前状态
	*                     p_devConfig  冰箱的功能配置
	*   返回值：-1--帧数据非法或无效 0--成功
	*/
	int frg_parse_state_102_0(uchar* p_cmdBuf, Frg_Status* p_devStatus, Device_Config*
		p_devConfig);


#ifdef __cplusplus
}
#endif

#endif





