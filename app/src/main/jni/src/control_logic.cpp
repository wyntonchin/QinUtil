#include <string>
#include <iostream>
#include <cstddef>
#include <vector>
#include <stdexcept>


#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <assert.h>

#include "log.h"
/*
#include "b64/encode.h"
#include "b64/decode.h"
*/
#include "ZBase64.h"
#include "control_logic.h"


#define _DEF_STRING(x) #x
#define DEF_TO_STRING(x) _DEF_STRING(x)
#define SOURCE_AT __FILE__ ":" DEF_TO_STRING(__LINE__)
#define ERROR_STR(msg) std::string(SOURCE_AT ":").append(msg)
#define throw_except_if_msg(except,expression,msg) \
    if(expression)\
        throw except(ERROR_STR(msg));

#define throw_except_if(except,expression) throw_except_if_msg(except,expression,#expression)
#define throw_if_msg(expression,msg) throw_except_if_msg(std::invalid_argument,expression,msg)
#define throw_if(expression) throw_except_if(std::invalid_argument,expression)



using namespace easylink_2_0;

ControlLogic::ControlLogic()
{
    F_LOGD("Construct controlLogic object\n");
}

ControlLogic::~ControlLogic()
{
    F_LOGD("Destruct controlLogic object\n");
}

int ControlLogic::init(const std::string& libPath, void *resv)
{
     F_LOGD("Init control logic object with:%s\n",libPath.c_str());

     if (access(libPath.c_str(),0)!=0) {
          F_LOGE("cannot locate%s\n",libPath.c_str());
          return INVALID_WORK_PATH;
     }

     dlHdlr = dlopen(libPath.c_str(),RTLD_LAZY);
     F_LOGD("dlHdlr:%p",dlHdlr);
     if(dlHdlr == NULL){
    	  F_LOGE("Error of dlopen:%s\n",dlerror());
    	  F_LOGE("Cannot load library:%s\n",libPath.c_str());
    	  return INVALID_WORK_PATH;
      }

      dlerror();//clear dlerror message
      const char* error;

      //load method for washer logic
      F_LOGD("Add method symbols\n");
      dev_parse_state=(ParseState)dlsym(dlHdlr, "parse_state");
      if ((error=dlerror())!=NULL) {
          F_LOGE("Cannot load method:parse_state--%s\n",error);
          //abort();
          dev_parse_state = NULL;
          err_num++;
      }

      dev_build_cmd= (BuildCmd)dlsym(dlHdlr, "build_cmd");
      if ((error=dlerror())!=NULL) {
          F_LOGE("Cannot load method: build_cmd--%s\n",error);
          //abort();
          dev_build_cmd = NULL;
          err_num++;
      }
      
      _do_get_version = (GetVersion)dlsym(dlHdlr, "get_version");
      if ((error=dlerror())!=NULL) {
          F_LOGE("Cannot load method: get_version--%s\n",error);
          _do_get_version = NULL;
          err_num++;
      }

      _do_set_demo = (SetDemo)dlsym(dlHdlr, "set_demo");
      if ((error=dlerror())!=NULL) {
           F_LOGE("Cannot load method: set_demo--%s\n",error);
           _do_set_demo = NULL;
           err_num++;
      }

      _do_state_query_cmd = (StateQueryCmd)dlsym(dlHdlr, "state_query_cmd");
      if ((error=dlerror())!=NULL) {
          F_LOGE("Cannot load method: _do_state_query_cmd--%s\n",error);
          _do_state_query_cmd = NULL;
          err_num++;
      }

#ifdef _D__FRG__
      F_LOGD("Add method of getting command for checking fridger state");
      __frg_cmd_ck_state = (frg_get_cmd_ck_state)dlsym(dlHdlr,"frg_cmd_check_state_102_0");
      if ((error=dlerror())!=NULL) {
           F_LOGE("Cannot load method: frg_cmd_check_state_102_0--%s\n",error);
		   __frg_cmd_ck_state = NULL;
      }
#endif

      if(err_num==MAX_SYMBOL){
    	 dlerror();
    	 dlclose(dlHdlr);
    	 dlHdlr = NULL;
    	 if((error = dlerror())!=NULL){
    	    F_LOGE("Update handler failed:%s\n",error);
    	 }else{
    	    F_LOGI("Update handler successfully\n");
    	 }

    	 dev_parse_state = NULL;
    	 dev_build_cmd   = NULL;
    	 _do_get_version = NULL;
    	 _do_set_demo    = NULL;
    	 _do_state_query_cmd = NULL;

    	 err_num=0;
    	 inited = false;
    	 return INVALID_WORK_PATH;
      }else{
    	 F_LOGI("Init ok!\n");
    	 err_num=0;
    	 inited = true;
         return SUCCESS;
      }
}

int ControlLogic::uinit()
{
    const char *error;
    F_LOGI("Destruct control logic object\n");

    if(inited){
       dlerror();
       F_LOGD("dlHdlr:%p",dlHdlr);
       dlclose(dlHdlr);
       dlHdlr = NULL;
       if((error = dlerror())!=NULL){
    	  F_LOGE("dlclose() failed:%s\n",error);
       }else{
    	  F_LOGI("dlclose() handler successfully\n");
       }

       dev_parse_state = NULL;
       dev_build_cmd   = NULL;
       _do_get_version = NULL;
       _do_set_demo    = NULL;
       _do_state_query_cmd = NULL;
    }else{
       F_LOGD("Control logic module has not been initiated, do nothing");
    }

    inited = false;
    return SUCCESS;
}

/*inline std::vector<unsigned char> decode(const std::string& input){
    throw_if(input.empty())
    std::vector<unsigned char> buffer(input.size());

    base64::decoder dc;
    base64::base64_init_decodestate(&dc._state);

    auto count=dc.decode(input.data(),int(input.size()),reinterpret_cast<char*>(buffer.data()));

    assert((unsigned int)count<=buffer.size());

    return std::vector<unsigned char>(buffer.data(),buffer.data()+buffer.size()/sizeof(unsigned char));
}*/

char* Base64Decode(const std::string& input, int& out_size){

    ZBase64 zBase64Util;
    std::string str_decode;
    char* out_char;

    if(input.empty()){
    	F_LOGD("Base64 string is empty!");
    	out_size = 0;
    	return NULL;
    }
    str_decode = zBase64Util.Decode(input.c_str(),input.length(),out_size);
    out_size = str_decode.length();

    out_char = new char[out_size];
    memcpy(out_char, str_decode.c_str(), out_size);
    return out_char;
}


int ControlLogic::parse_state(const std::string& s_raw_state,
                              const std::string& s_refParam,
                              int *p_stateBuf,
                              int *p_stateBufLen)
{
     F_LOGI("Execute method of parsing device state.\n");

     unsigned char *p_raw_state_buf;
     size_t raw_state_buf_len;
     RefParam param;
     int i,len;

     if(dev_parse_state == NULL){
    	F_LOGE("Pointer of dev_parse_state is NULL");
    	return INVALID_FUNC_POINTER;
     }

     F_LOGD("In parse state: raw state:%s", s_raw_state.c_str());

     p_raw_state_buf = (unsigned char*)Base64Decode(s_raw_state,len);
     raw_state_buf_len = len;
     F_LOGD("Length of raw state:%d", raw_state_buf_len);

     if(raw_state_buf_len==0){
    	 return INVALID_S_RAW_STATE;
     }

     for (i=0;i<len;++i)
     {
         F_LOGD("Index:%d, Value:%x",i, p_raw_state_buf[i]);
     }

     param.data = Base64Decode(s_refParam,len);
     param.len = len;
     F_LOGD("Length of extra parameters:%d", param.len);
     /*for (i=0;i<param.len;++i){
         F_LOGD("Index:%d, Value:%x",i,param.data[i]);
     }*/

     //prototype
     //typedef int (*ParseState)(unsigned char *praw_state_buf, const unsigned int raw_state_buf_len, RefParam *pref_param, int *pstate_buf, unsigned int* pstate_num);
     unsigned int pstate_num = (*p_stateBufLen)/sizeof(int);

#ifdef _D__AC__
     char* tmp = (char*)malloc(2);
     param.data = tmp;
     param.data[0] = 0x22;
     param.data[1] = 0x03;
     param.len     = 2;
#endif

     auto ret = dev_parse_state(p_raw_state_buf, (const unsigned int)raw_state_buf_len, &param, p_stateBuf, &pstate_num);

#ifdef _D__AC__
     free(param.data);
     param.data=NULL;
#endif

     *p_stateBufLen = pstate_num*sizeof(int);

     if(p_raw_state_buf!=NULL){
    	delete[] p_raw_state_buf;
     }

     if(param.data != NULL){
    	delete[] param.data;
     }

     F_LOGI("Complete executing method of parsing device state\n");
     return ret;
}

int ControlLogic::obtain_cmd(const int *p_rawCmdBuf, int *p_stateBuf, int *p_stateBufLen, std::string &s_refParam, unsigned char *p_cmdBuf, int *p_cmdBufLen)
{
    F_LOGI("Enter method of obtaining control command\n");

    RefParam param;
    int i,len;

    if(dev_build_cmd == NULL){
       F_LOGE("Pointer of dev_build_cmd is NULL");
       return INVALID_FUNC_POINTER;
    }

    F_LOGD("====>Print command info");
    int n = *(p_rawCmdBuf);
    F_LOGD("====>Number of command:%d",n);
    for(int i=0;i<2*n+1;i++){
       F_LOGD("======>Index:%d Command:%d",i,*(p_rawCmdBuf+i));
    }
    F_LOGD("Print command info====<");

    F_LOGD("====>Print origin state info");
    F_LOGD("====>Number of origin state:%d",*p_stateBufLen/sizeof(jint));
    for(int i=0;i<*p_stateBufLen/sizeof(jint);i++){
    	F_LOGD("Index:%d Origin state:%x",i,*(p_stateBuf+i));
    }
    F_LOGD("Print origin state====<");

    param.data = Base64Decode(s_refParam,len);
    param.len = len;
    F_LOGD("====>Length of extra parameters:%d", param.len);
    for (i=0;i<param.len;++i){
         F_LOGD("Index:%d, Value:%x",i,param.data[i]);
    }

    //prototype
    //typedef int (*BuildCmd)(const int *prog_cmd_buf, int *pstate_buf, unsigned int* pstate_num, RefParam *pref_param, unsigned char *praw_cmd_buf, unsigned int *praw_cmd_buf_len);
    unsigned int pstate_num;
    pstate_num = (*p_stateBufLen)/sizeof(int);
    int ret = dev_build_cmd(p_rawCmdBuf, (int*)p_stateBuf, &pstate_num, &param, p_cmdBuf, (unsigned int*)p_cmdBufLen);
    F_LOGD("Result of build command:%d",ret);

    *p_stateBufLen = pstate_num*sizeof(int);

    if(param.data != NULL){
       delete[] param.data;
    }

    F_LOGD("After call method of build command index:0, value:%d", *((int*)p_stateBuf));

    F_LOGI("Complete executing method of obtaining control command\n");

    return ret;
}

#ifdef _D__FRG__
int ControlLogic::_frg_get_cmd_ck_state(unsigned char *p_cmdBuf, int *len)
{
    F_LOGD("Get command for checking fridger status-->\n");
    unsigned char autoFlag = 1; //1--mannual 0--automatically

    if(__frg_cmd_ck_state == NULL){
       F_LOGE("Pointer of dev_build_cmd is NULL");
       return INVALID_FUNC_POINTER;
    }

	if(__frg_cmd_ck_state!=NULL){
       __frg_cmd_ck_state(p_cmdBuf, len, autoFlag);
	}

    F_LOGD("Get command for checking fridger status<--\n");
    return SUCCESS;
}
#endif

int ControlLogic::getLibVer(std::string& version)
{
	F_LOGD("Get version of logic library");
    if(_do_get_version==NULL){
    	F_LOGE("Pointer to _do_get_version is NULL");
    	return INVALID_FUNC_POINTER;
    }

    char *ver_buf = (char*)malloc(MAXVERSIONBUF);
    memset(ver_buf,0,MAXVERSIONBUF);
    int ret = _do_get_version(ver_buf,MAXVERSIONBUF);

    if(ret==0){
       version.assign((const char*)ver_buf);
       F_LOGD("Version of logic library:%s",version.c_str());
    }else{
       version.clear();
    }

    F_LOGD("Leave method of getting logic library");
    return ret;
}

int ControlLogic::getGwStateMask(std::string& mask)
{
	F_LOGD("Get state mask of gateway");

	int ret = SUCCESS;
	/*if(_do_get_gw_state_mask==NULL){
    	F_LOGE("Pointer to _do_get_version is NULL");
    	return INVALID_FUNC_POINTER;
    }*/

	//try to load interface

	const char* error;
	dlerror();
	if(dlHdlr){
	  _do_get_gw_state_mask = (GetGwstateMask)dlsym(dlHdlr, "get_onoffline_info");
	  //dev_parse_state=(ParseState)dlsym(dlHdlr, "parse_state");
	  if ((error=dlerror())!=NULL) {
	       F_LOGE("Cannot load method: get_onoffline_info--%s\n",error);
	       _do_get_gw_state_mask = NULL;
	       return INVALID_FUNC_POINTER;
	  }else{
		   F_LOGD("Load get_onoffline_info ok");
	  }
	}else{
      F_LOGD("Detect handler is null,may be uninitialized");
      return UNINITIALIZED;
	}



    unsigned char *mask_buf = (unsigned char*)malloc(MAXSTATEBUFSIZE);
    memset(mask_buf,0,MAXSTATEBUFSIZE);

    unsigned int mask_buf_len = MAXSTATEBUFSIZE;
    _do_get_gw_state_mask(mask_buf,&mask_buf_len);


    mask.assign((const char*)mask_buf);
    F_LOGD("Length of mask_buf:%d",mask_buf_len);
    F_LOGD("Mask of gateway state:%s",mask.c_str());

    F_LOGD("Leave method of getting state mask of gateway");
    return ret;
}

int ControlLogic::demo(const std::string& s_refParam,int *p_stateBuf,int *p_stateBufLen)
{
	RefParam param;
	int i,len;

	if(_do_set_demo == NULL){
	   F_LOGE("Pointer of _do_set_demo is NULL");
	   return INVALID_FUNC_POINTER;
	}

	param.data = Base64Decode(s_refParam,len);
	param.len = len;
	F_LOGD("Length of extra parameters:%d", param.len);
	for (i=0;i<param.len;++i){
	     F_LOGD("Index:%d, Value:%x",i,param.data[i]);
	}

	unsigned int pstate_num = (*p_stateBufLen)/sizeof(int);
	auto ret = _do_set_demo(&param, p_stateBuf, &pstate_num);
	*p_stateBufLen = pstate_num*sizeof(int);

	if(param.data != NULL){
	   delete[] param.data;
	}

	F_LOGI("Complete executing method of parsing device state\n");
	return ret;
}

int ControlLogic::state_query_cmd(std::string& s_refParam, unsigned char *p_cmdBuf, unsigned int *p_cmdBufLen)
{
	RefParam param;
	int i,len;

	if(_do_state_query_cmd == NULL){
	   F_LOGE("Pointer of _do_state_query_cmd is NULL");
	   return INVALID_FUNC_POINTER;
	}

	param.data = Base64Decode(s_refParam,len);
	param.len = len;
	F_LOGD("Length of extra parameters:%d", param.len);
	for (i=0;i<param.len;++i){
	  F_LOGD("Index:%d, Value:%x",i,param.data[i]);
	}

	auto ret = _do_state_query_cmd(&param, p_cmdBuf, p_cmdBufLen);
	if(param.data != NULL){
	   delete[] param.data;
    }

    F_LOGI("Complete executing method of obtaining query command\n");
    return ret;
}
