#ifndef _WASHER_BUSININESS
#define _WASHER_BUSININESS

#ifdef __cplusplus
extern "C" {
#endif

typedef unsigned char uchar;
typedef unsigned int  uint;

#include "washer_state.h"
#include "washer_command.h"


/**场景
 *     　改变洗衣机状态（暂停、停止、运行）或修改设置项中内容（童锁等）时，调用此接口获取对应的控制命令帧。调用时传入洗衣机当前
 *　　　工作状态（p_devStatus）、所设置的命令（cmd）。
 *      如果洗衣机工作状态会发生变化，需把新的状态更新到p_devStatus指向的数据结构中。
 *      如果传入参数有误或其他原因造成函数调用失败，可以返回-1。成功调用时要将控制命令帧赋值到p_cmdBuf中。同时将命令长度赋值到len参数。
 */
/**
 *   用途: 获取设置洗衣机工作状态对应的原始命令帧数据（包含帧头+内容+帧尾）
 *   参数：mach_modle--洗衣机型号
 *         P_devStatus--洗衣机工作状态
 *         cmd--指向所要发送的控制命令
 *         num_cmd--命令个数
 *         p_cmdBuf--原始命令帧缓存，由应用层申请，协议库赋值
 *         len-命令帧长度，由协议库赋值
 *   返回值 无
*/
int wsh_get_cmd_control(WASHER_STAT *p_devStatus,
			             COMMAND *cmd,
		                 int num_cmd,
			             uchar *p_cmdBuf,
			             int *len);


						
						
						
						

/**场景
 *         切换程序模式时，需要调用此接口获取对应的控制命令帧，调用时传入洗衣机当前状态(p_devStatus)、所要切换的模式ID（prg_id）
 *         如果洗衣机工作状态会发生变化，需把新的状态更新到p_devStatus指向的数据结构中。
 *         如果传入参数有误或其他原因造成函数调用失败，可以返回-1。成功调用时要将控制命令帧赋值到p_cmdBuf中。同时将命令长度赋值到len参数。
 */
/**
 *   用途: 获取切换洗衣机21种工作模式对应的原始命令帧数据（包含帧头+内容+帧尾）
 *   参数：mach_modle--洗衣机型号
 *         P_devStatus--洗衣机工作状态
 *         prg_id--所要切换的id
 *         p_cmdBuf--原始命令帧缓存，由应用层申请，协议库赋值
 *         len-命令帧长度，由协议库赋值
 *   返回值 无
*/
int wsh_cmd_adj_prog(WASHER_STAT *p_devStatus,
                      enum WASH_PRG_ID prg_id,
                      uchar enable,
		              uchar *p_cmdBuf,
		              int *len);

			





			

/**场景
 *   某种模式下调整某个参数时，需调用该接口获取控制命令帧，调用时传入当前洗衣机工作状态（p_devStatus）及要调整的参数（p_param表示）、
 *   受关联的参数描述符指针（p_desc_list）.解析库需要根据传入的参数做逻辑处理，如果洗衣机工作状态、受关联参数会发生变化，则
 *   需把新的状态更新到p_devStatus及p_desc_list指向的数据结构中。
 *   如果传入参数有误或其他原因造成函数调用失败，可以返回-1。成功调用时要将控制命令帧赋值到p_cmdBuf中，同时将命令长度赋值到len参数。
 */ 
/**
 *   用途: 获取设置洗衣机对应工作模式下参数的原始命令帧数据（包含帧头+内容+帧尾）
 *   参数：mach_modle--洗衣机型号
 *         P_devStatus--洗衣机工作状态
 *         p_param--设置的参数
 *         num_param--设置的参数的数量
 *         p_desc_list--参数描述项
 *         num_desc_list--参数描述项数量
 *         p_cmdBuf--原始命令帧缓存，由应用层申请，协议库赋值
 *         len-命令帧长度，由协议库赋值
 *   返回值 无
*/
int wsh_cmd_adj_param(WASHER_STAT *p_devStatus,
		                            PARAM *p_param,
                                            int  num_param,
		                            PARAM_ITEM_DESC *p_desc_list,
		                            int *num_desc_list,
		                            uchar *p_cmdBuf,
		                            int *len);
				  
		

		
					  
/**场景:
 *   同用途
 *       
*/					  
/**
 *   用途：  获取查询洗衣机工作状态对应的原始命令帧数据（包含帧头+内容+帧尾）
 *   参数：  p_cmdBuf--原始命令帧缓存，由应用层申请，协议库赋值
 *           len--命令帧长度，由协议库赋值
 *   返回值：无
 */
void wsh_cmd_ck_stat(uchar *p_cmdBuf, int *len);





/**场景:
 *       1、第一次进入应用时需要刷新页面，此时需调用该接口把从洗衣机端获取的原始状态数据解析出来显示
 *       2、洗衣机工作状态发生改变时，需调用该接口用来解析新的原始状态数据
*/
/**
 *   用途：  解析洗衣机工作状态原始帧数据（包含帧头+内容+帧尾）
 *   参数：  p_stateBuf--原始状态帧数据缓存
 *           p_devStatus--洗衣机的当前工作状态,由解析库赋值
 *					  
 *   返回值：-1--帧数据非法或无效 0--成功
 */
int wsh_parse_state(uchar *p_stateBuf, 
		            int len_buf, 
		            WASHER_STAT *p_devStatus,
		            PARAM_ITEM_DESC *p_desc_list,
		            int *num_desc_list);

/*2016.6.25*/
/*********************************************/
/*********************************************/
/*Э���2.0 ��ͨ�û��汾��״̬����*/
/*pstate_buf[90] �����Ǳ�ʾ����Ĳ�����*/
/*�����������ź�����ĸ��ֽڷֱ�*/
/*��ʾ����id����Ч�Ƿ񡢹̶��Ƿ�*/
/*������ֵ����������������               */
/*********************************************/
/*********************************************/
int parse_state(unsigned char * Praw_state_buf,int Raw_state_buf_len,int *pstate_buf,int *pstate_buf_len);


/*****************************************************************/
/*****************************************************************/
/*Э���2.0 ��ͨ�û��汾�Ŀ�������Ĳ���************/
/*prog_cmd_buf ����buf �����ݶ�����buf[0], buf[1],buf[2], buf[3],buf[4]*/
/*buf[0]��ʾ����ĸ�����buf[1]��ʾ�����ID��buf[2]��ʾ **/
/*ֵ��buf[3]�������ͣ�1��ʾcontrol,2��ʾprog,3��ʾparam ��**/
/*��Э���1.0�İ汾,buf[4] enable ��ʾ���������Ƿ�           */
/*��������ʱ�õ� buf[5],buf[6],buf[7],buf[8],						 */
/*pstate_buf[90] ������  ��ʾ����Ĳ����������������� */
/*������ĸ��ֽڷֱ��ʾ����id����Ч�Ƿ񡢹̶� */
/*�Ƿ񡢲�����ֵ�� ��������������                                 */
/*****************************************************************/
/*****************************************************************/

int build_cmd(int *prog_cmd_buf,int *pstate_buf,int *pstate_buf_len,
	unsigned char *praw_cmd_buf,int *praw_cmd_buf_len);


/***************************************************************/
/***************************************************************/
/*Э���2.0 �����Ӱ汾����Ϣ�Ա㽫���İ汾Ӧ��*/
/***************************************************************/
/***************************************************************/

int get_version(char* ver_buf,int* len);


#ifdef __cplusplus
}
#endif

#endif





