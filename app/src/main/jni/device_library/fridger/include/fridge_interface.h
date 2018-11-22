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
		uchar  cmdid;    //����id
		char p_param;  //����ֵ
		uchar autoflag; //�ֶ�/�Զ����ͱ�־
		int    length;   //��������
	} CMD;

	/**
	*   ��;��  ��ȡ������������Ӧ��ԭʼ����֡���ݣ�����֡ͷ+����+֡β��
	*   ������
	*      p_curStatus  ָ��ǰ״̬�ṹ���ָ��,
	*      p_command      ����id+��������CMD�ṹ�嶨�壩
	*           p_cmdBuf     ԭʼ����֡���棬��Ӧ�ò����룬�ײ�ʵ�ֽ��и�ֵ
	*           len          IN-����֡�����ܳ���, OUT-����֡ʵ�ʳ��ȣ��ɵײ�ʵ�ֽ��и�ֵ
	*   ����ֵ��0--�ɹ�   -1--ʧ��
	*/
	int frg_cmd_control_101_0(Frg_Status* p_curStatus, CMD* p_command, unsigned int cmd_count, unsigned char* p_cmdBuf, unsigned int* len);


	/**
	*   ��;��  ��ȡ��ѯ����״̬�����Ӧ��ԭʼ����֡���ݣ�����֡ͷ+����+֡β��
	*   ������  p_cmdBuf     ԭʼ����֡���棬��Ӧ�ò����룬�ײ�ʵ�ֽ��и�ֵ
	*           len          IN-����֡�����ܳ���, OUT-����֡ʵ�ʳ��ȣ��ɵײ�ʵ�ֽ��и�ֵ
	*            autoflag     0-�Զ�����102ָ�1--�û��ֶ���ѯ102#
	*   ����ֵ����
	*
	*/
	void frg_cmd_check_state_102_0(uchar *p_cmdBuf, int *len, uchar autoflag);

	//int parse_state(unsigned char* praw_state_buf,const size_t raw_state_buf_len,RefParam* pref_param,unsigned char* pstate_buf,int* pstate_buf_len);

	//int build_cmd(const int* porg_cmd_buf,unsigned char* pstate_buf,int* pstate_buf_len,RefParam* pref_param,unsigned char* praw_cmd_buf,int* praw_cmd_buf_len);

	//int get_version(char* ver_buf,size_t len);

	/**
	*   ��;��  ��������״̬ԭʼ֡���ݣ�����֡ͷ+����+֡β��
	*   ������  p_cmdBuf ԭʼ״̬֡���ݻ���
	*                     p_devStatus ����ĵ�ǰ״̬
	*                     p_devConfig  ����Ĺ�������
	*   ����ֵ��-1--֡���ݷǷ�����Ч 0--�ɹ�
	*/
	int frg_parse_state_102_0(uchar* p_cmdBuf, Frg_Status* p_devStatus, Device_Config*
		p_devConfig);


#ifdef __cplusplus
}
#endif

#endif





