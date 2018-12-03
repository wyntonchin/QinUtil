/*
 * 文件名称：parse_lib_public.h
 * 文件作者: 孙春晓
 * 特别说明: 此头文件为解析库公共头文件，
 *           云端、解析库实现方、手机端、电视端都会使用，
 *           函数的定义遵循《白电云平台设备解析库规范》，
 *           请勿修改，如需修改，请联系sunchunxiao@hisense.com
 * 创建时间：2016.7.15
 * 修改记录：新建文件
 */

#ifndef PARSELIBPUBLIC_H
#define    PARSELIBPUBLIC_H

#ifdef    __cplusplus
extern "C" {
#endif
    
typedef struct RefParam_s{
    char* data;
    unsigned int len;
}RefParam;
    
    
    /***********************************************************
     *函数名称: build_cmd
     *函数功能：传入修改的指令和当前的状态，返回设备可设备的状态指令
     
     *输入参数：porg_cmd_buf      ---- 需修改的指令个数，指令值和参数值
     pstate_buf        ---- 调用方传入当前状态，实现方返回修改后的状态。
     一个int代表一个指令
     pstate_buf_len    ---- pstate_buf的长度
     
     *输出参数：praw_cmd_buf      ---- 转换后的设备可识别的状态，内存调用方预分配
     praw_cmd_buf_len  ---- 调用方传入分配内存的长度，
     实现方返回实际使用的内存长度
     
     *返回值:  0----成功，else----失败
     **********************************************************/
    int build_cmd(const int* porg_cmd_buf,int* pstate_buf,
                  unsigned int* pstate_num,RefParam* pref_param,
                  unsigned char* praw_cmd_buf,unsigned int* praw_cmd_buf_len);
    
    
    
    /***********************************************************
     *函数名称: get_version
     *函数功能：返回当前解析库的版本
     *输入参数: 无
     *输出参数：ver_buf       ---- 版本号，调用方预分配内存
     len           ---- 调用方传入分配内存的长度，
     实现方返回实际使用的内存长度
     *返回值:  0----成功，else----失败
     **********************************************************/
    int get_version(char* ver_buf,unsigned int len);
    
    
    
    
#ifdef    __cplusplus
}
#endif

#endif /* PARSELIBPUBLIC_H */


