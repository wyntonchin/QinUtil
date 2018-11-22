#ifndef _CONTROL_LOGIC_H                   
#define _CONTROL_LOGIC_H

#include <string>
#include <dlfcn.h>

#ifdef __cplusplus
extern "C" {
#endif

typedef struct RefParam_s{
    char *data;   
    size_t len;
}RefParam;

typedef int (*ParseState)(unsigned char *praw_state_buf, const unsigned int raw_state_buf_len, RefParam *pref_param, int *pstate_buf, unsigned int* pstate_num);
typedef int (*BuildCmd)(const int *prog_cmd_buf, int *pstate_buf, unsigned int* pstate_num, RefParam *pref_param, unsigned char *praw_cmd_buf, unsigned int *praw_cmd_buf_len);
typedef int (*GetVersion)(char* ver_buf,unsigned int len);
typedef int (*SetDemo)(RefParam* pref_param,int* pstate, unsigned int* pstate_num);
typedef int (*StateQueryCmd)(RefParam* pref_param, unsigned char* praw_cmd_buf, unsigned int* praw_cmd_buf_len);
typedef int (*GetGwstateMask)(unsigned char* mask_buf, unsigned int* len);

#ifdef _D__FRG__
typedef void (*frg_get_cmd_ck_state)(unsigned char *p_cmdBuf, int *len, unsigned char autoflag);
#endif
#ifdef __cplusplus
}
#endif

namespace easylink_2_0{

class ControlLogic{
   
    #define VERSION "1.01.003"
    #define BUILD_INFO  __DATE__
       
    public:

    /*history*/
    //rev: 1.01.001 init
	//rev: 1.01.002   修改parse_state()和build_command()接口函数定义
	//rev: 1.01.003   按照20160726发布的设备接口规范修改parse_state和build_cmd接口参数类型，添加get_version、state_query_cmd、set_demo接口

     const int SUCCESS = 0;
     //const int INVALID_PROGID     = -1;
     //const int INVALID_COMMAND    = -2;
     //const int INVALID_PARAM      = -3;
     //const int INVALID_STATUS     = -4;
     //const int INVALID_MODULE     = -5;
     //const int INVALID_STATE_BUF  = -6;

     //const int INVALID_DEV_ID  = -7;
     //const int INVALID_DEV_VERSION = -8;
     const int INVALID_WORK_PATH = -1009;
     const int UNINITIALIZED     = -1010;
     const int INVALID_S_PARAM   = -1011;
     const int UNKNOWN_ERROR     = -1234;
     const int INVALID_S_RAW_STATE = -1012;
     const int INVALID_FUNC_POINTER = 0xffff;

     ControlLogic();
     virtual ~ControlLogic();

     static const std::string getVersion()
     {
         return VERSION;
     }
    
     static const std::string getBuildInfo()
     {
         return BUILD_INFO;
     }


     /**
      *
      * @param libPath 动态库绝对路径+文件名，需要文件所在路径有读写权限
      * @param resv    附加参数
      *
      * @return int    0--成功<0--失败
      */
     int init(const std::string& libPath,void *resv);                        
     int uinit();
   

     /**
      *
      * @param s_raw_state 原始状态字节流base64编码得到的字符串
      * @param s_refParam  附加参数，base64编码字符串
      * @param p_stateBuf  解析后的状态缓存
      * @param p_stateBufLen 状态缓存长度
      *
      * @return int 解析结果，具体内容待定义，暂定义0为成功执行
      *
      */
     int parse_state(const std::string& s_raw_state,
			         const std::string& s_refParam,
			         int *p_stateBuf,
			         int *p_stateBufLen);
     
     /**
      *
      * @param p_cmdBuf 原始命令缓存
      * @param p_stateBuf 当前状态缓存
      * @param s_refParam 附加参数，base64编码
      * @param p_cmdBuf 生成命令缓存
      * @param p_cmdBufLen 生成命令缓存长度
      *
      * @return int 执行结果，具体内容待定义，暂定义0为成功
      */
     int obtain_cmd(const int *p_rawcmdBuf, 
		            int *p_stateBuf,
			        int *p_stateBufLen,
			        std::string& s_refParam,
			        unsigned char *p_cmdBuf,
			        int *p_cmdBufLen);

     /**获取协议库版本号**/
     int getLibVer(std::string& version);


     /**
      *
      * @param s_refParam  附加参数，base64编码字符串
      * @param p_stateBuf  解析后的状态缓存
      * @param p_stateBufLen 状态缓存长度
      *
      * @return int 解析结果，具体内容待定义，暂定义0为成功执行
      */
     int demo(const std::string& s_refParam,
	          int *p_stateBuf,
	          int *p_stateBufLen);

     /**
       *
       * @param s_refParam 附加参数，base64编码
       * @param p_cmdBuf 生成命令缓存
       * @param p_cmdBufLen 生成命令缓存长度
       *
       * @return int 执行结果，具体内容待定义，暂定义0为成功
       */
     int state_query_cmd(std::string& s_refParam,
		                 unsigned char *p_cmdBuf,
		                 unsigned int *p_cmdBufLen);






     //device support
     /*获取网关状态标志*/
     int getGwStateMask(std::string& mask);


     void *dlHdlr;
     ParseState     dev_parse_state            = NULL;
     BuildCmd       dev_build_cmd              = NULL;
     GetVersion     _do_get_version            = NULL;
     SetDemo        _do_set_demo               = NULL;
     StateQueryCmd  _do_state_query_cmd        = NULL;
     unsigned char err_num = 0;
     #define MAX_SYMBOL  5
     bool        inited = false;


     //device support
     GetGwstateMask _do_get_gw_state_mask      = NULL;

#ifdef _D__FRG__
     int _frg_get_cmd_ck_state(unsigned char *p_cmdBuf, int *len);
     frg_get_cmd_ck_state __frg_cmd_ck_state = NULL;
#endif

};

   




} //namespace

#endif
