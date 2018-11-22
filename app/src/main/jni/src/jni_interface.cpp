#include <string>
#include <map>

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <sys/time.h>
#include <unistd.h>

//#ifdef _CLOUD
#define JAVA_DEVICE_CONTROL_LOGIC "com/hismart/easylink/devcontrol/ControlLogic"
//#else
//#define JAVA_DEVICE_CONTROL_LOGIC "com/hmct/easylink/devcontrol/ControlLogic"
//#endif

#include "jniUtils.h"
#include "log.h"
#include "control_logic.h"

//#define NDEBUG //for enable/disable assert

using namespace easylink_2_0;
std::map<std::string,ControlLogic*> moduleMap;
std::map<std::string,int>           moduleMapCount;

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;


extern JavaVM *gJavaVM;
static int module = -1;

static jint native_init(JNIEnv *env, jobject object,jobject thiz, jstring workpath, jbyteArray arrayResv);
static jint native_uninit(JNIEnv *env, jobject object, jobject thiz, jstring workpath);
static jint native_parseState(JNIEnv *env, jobject object, jobject thiz, jstring rawState, jstring refParam, jstring workpath);
static jint native_buildCommand(JNIEnv *env, jobject object, jobject thiz, jintArray arrayRawCmd, jintArray arrayState, jstring refParam, jstring workpath);
static jint native_getVersion(JNIEnv *env, jobject object, jobject thiz, jstring workpath);
static jint native_setDemo(JNIEnv *env, jobject object, jobject thiz, jstring refParam, jstring workpath);
static jint native_gQueryCommand(JNIEnv *env, jobject object, jobject thiz, jstring refParam, jstring workpath);
static jint native_getGwStateMask(JNIEnv *env, jobject object, jobject thiz, jstring workpath);


#ifdef _D__FRG__
static jint native_frg_cmd_ck_state(JNIEnv *env, jobject object, jobject thiz, jstring workPath);
#endif


/*
 * Table of methods associated with the ControlLogic class.
 */
static JNINativeMethod mCtrlLogicMethods[] = {
	/* name, signature, funcPtr */
	{"native_init",                "(Ljava/lang/Object;Ljava/lang/String;[B)I",                                       (void*)native_init},
	{"native_uninit",              "(Ljava/lang/Object;Ljava/lang/String;)I",                                         (void*)native_uninit},

	{"native_parseState",          "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I",     (void*)native_parseState},
	{"native_buildCommand",        "(Ljava/lang/Object;[I[ILjava/lang/String;Ljava/lang/String;)I",                   (void*)native_buildCommand},
	{"native_gQueryCommand",       "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)I",                       (void*)native_gQueryCommand},

	{"native_getVersion",          "(Ljava/lang/Object;Ljava/lang/String;)I",                                         (void*)native_getVersion},
	{"native_setDemo",             "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)I",                       (void*)native_setDemo},
	{"native_getGwStateMask",      "(Ljava/lang/Object;Ljava/lang/String;)I",                                         (void*)native_getGwStateMask},
#ifdef _D__FRG__
	{"native_frg_cmd_ck_state",    "(Ljava/lang/Object;Ljava/lang/String;)I",                                         (void*)native_frg_cmd_ck_state},
#endif
};

int registerCtrlManagerNatives(JNIEnv* env)
{
	jboolean bRet = registerNativeMethods(env, JAVA_DEVICE_CONTROL_LOGIC, mCtrlLogicMethods,
								 sizeof(mCtrlLogicMethods) / sizeof(mCtrlLogicMethods[0]));

	return bRet;
}
/*
 * Register native methods for all classes we know about.
 */
static jboolean registerAllNatives(JNIEnv* env)
{
	jboolean bRet = JNI_FALSE;

	initJniUtils(env);

	/* Register several native methods for washer login handler class. */
	bRet = registerCtrlManagerNatives(env);
	//F_LOGI("registerAllWshCtrlNatives = %d\n", bRet);

	return bRet;
}

jint JNI_OnLoad(JavaVM* vm, void* reserved)
{
	JNIEnv* env = NULL;
	jint result = -1;

	gJavaVM = vm;

	//F_LOGI("Entering JNI_OnLoad\n");

	if ((vm->GetEnv((void**) &env, JNI_VERSION_1_4) == JNI_OK) && registerAllNatives(env)) {
		/* success -- return valid version number */
		result = JNI_VERSION_1_4;
	}


	F_LOGI("version:%s  date:%s\n",ControlLogic::getVersion().c_str(),ControlLogic::getBuildInfo().c_str());
	//F_LOGI("Leaving JNI_OnLoad (result=0x%x)\n", result);
	return result;
}

void JNI_OnUnload(JavaVM* vm, void* reserved)
{
	//F_LOGI("Start JNI_OnUnload ...");

	JNIEnv* env = NULL;
	if (gJavaVM->GetEnv((void**)&env, JNI_VERSION_1_4) != JNI_OK) {
		F_LOGE("Failed to obtain JNIEnv");
	}
	moduleMap.clear();
	uninitJniUtils(env);

	//F_LOGI("Run JNI_OnUnload Done!");
}

#define CHECKOUT_MODULE_START(path) it = moduleMap.find(path);\
	                              if (it != moduleMap.end()) {\
	                        	      F_LOGI("Checkout path:%s\n",path.c_str());\
	                        	      p_controlEmul = it->second;\
	                        	      if(p_controlEmul!=NULL){
                                      //control_xx
//-10=uninitialized
#define CHECKOUT_MODULE_END           }else{ \
                                         F_LOGE("Error when checkout module pointer of path:%s\n",path.c_str());\
                                         ret = -10;  \
                                      }\
	                              }else{\
	                                  F_LOGI("Cannot take reference of path:%s, do nothing!!\n",path.c_str());\
	                                  ret = -10;  \
	                              }


inline int getMaxStateBufSize()
{
    return MAXSTATEBUFSIZE;
    //TODO Better get value from device library
}

inline int getMaxCmdBufSize()
{
    return MAXCMDBUFSIZE;
}

jint native_init(JNIEnv *env, jobject object,jobject thiz, jstring workpath, jbyteArray arrayResv)
{
     std::string path;
     char *resv;
     int   resv_len;
     int   ret = 0;
     bool  unInit = false;

     const char* c_path = NULL;
     if(workpath!=NULL){
    	c_path = env->GetStringUTFChars(workpath,0);
        path = c_path;
     }else{
    	F_LOGE("Require work path to init washer logic module\n");
    	return -10;
     }

     F_LOGI("Init path:%s!!\n",path.c_str());

     if(arrayResv!=NULL){
        resv_len = env->GetArrayLength(arrayResv);
        resv = (char*)malloc(resv_len);
        memset(resv,0,resv_len);
        env->GetByteArrayRegion(arrayResv,0,resv_len,(jbyte*)resv);
     }

	 pthread_mutex_lock(&mutex);

	 F_LOGD("moduleMap:%p",&moduleMap);
	 F_LOGD("moduleMapCount:%p",&moduleMapCount);

	 ControlLogic *p_controlEmul;
	 std::map<std::string, ControlLogic*>::iterator it;
	 it = moduleMap.find(path);
	 if (it != moduleMap.end()) {
	 	 std::map<std::string,int>::iterator map_count_it = moduleMapCount.find(path);

	 	 F_LOGI("Checkout path:%s\n",path.c_str());
	 	 p_controlEmul = it->second;
	 	 if(p_controlEmul!=NULL){
	 		F_LOGE("Path:%s has been initialized",path.c_str());
            if(map_count_it!=moduleMapCount.end()){
               map_count_it->second++;
               F_LOGD("Add count to:%d",map_count_it->second);
            }else{
               F_LOGD("Reset to 1");
    	 	   moduleMapCount.insert(make_pair(path,1));
            }
	 	    unInit = false;
         }else{
	        F_LOGE("Error when checkout module pointer of path:%s\n",path.c_str());
	        //ret = -10;
	        moduleMap.erase(it++);
	        moduleMapCount.erase(map_count_it++);
	        unInit = true;
	     }
	 }else{
	 	 F_LOGI("Path:%s has not been initialized, try to init!!\n",path.c_str());
	 	 //ret = -10;
	 	 unInit = true;
	 }

	 if(unInit){
		 p_controlEmul = new ControlLogic();
		 ret = p_controlEmul->init(path,(void*)resv);
		 F_LOGI("Init device control logic module:%d\n",ret);
		 if(!ret){
		    std::pair<std::map<std::string,ControlLogic*>::iterator,bool> map_op_ret = moduleMap.insert(make_pair(path,p_controlEmul));
		    if(map_op_ret.second){
		    	F_LOGI("Insert control logic module successfully\n");
		    	unInit = false;
		    	moduleMapCount.insert(make_pair(path,1));
		    }else{
		    	ret = map_op_ret.second;
		    	F_LOGI("Insert control logic module failed:%d\n",ret);
		        unInit = true;
		    }
		 }else{
		    F_LOGE("Init device control logic module error\n");
		    delete p_controlEmul;
		 }
	 }

	 pthread_mutex_unlock(&mutex);

	 if(arrayResv!=NULL){
	    free(resv);
	 }
	 env->ReleaseStringUTFChars(workpath,c_path);

     return ret;
}

jint native_uninit(JNIEnv *env, jobject object, jobject thiz, jstring workPath)
{
     jint ret = 0;
     std::string path;

     const char* c_path = env->GetStringUTFChars(workPath,0);
     path = c_path;
     F_LOGI("Uninit %s!!\n",path.c_str());

     std::map<std::string, ControlLogic*>::iterator it;
     std::map<std::string,int>::iterator map_count_it;

     pthread_mutex_lock(&mutex);
	 F_LOGD("moduleMap:%p",&moduleMap);
	 F_LOGD("moduleMapCount:%p",&moduleMapCount);

     map_count_it = moduleMapCount.find(path);
     if(map_count_it!=moduleMapCount.end()){
    	 map_count_it->second--;
    	 F_LOGD("Unref %s, count:%d",path.c_str(),map_count_it->second);
    	 if(map_count_it->second==0){
    	     	 it = moduleMap.find(path);
    	     	 if (it != moduleMap.end()) {
    	     	     F_LOGI("Unref path:%s\n",path.c_str());
    	     	     ControlLogic *p_controlEmul = it->second;
    	     	     if(p_controlEmul!=NULL){
    	     	     	ret = p_controlEmul->uinit();
    	     	     }else{
    	     	     	F_LOGE("Error when checkout module pointer of path:%s\n",path.c_str());
    	     	     	ret = -255;
    	     	     }
    	     	     moduleMap.erase(it++);
    	     	     moduleMapCount.erase(map_count_it++);
    	     	 }else{
    	     	     F_LOGI("Cannot take reference to path:%s, do nothing!!\n",path.c_str());
    	     	     ret = -255;
    	     	 }
    	 }
     }else{
        F_LOGD("Cannot take reference to %s, do nothing",path.c_str());
     }

     pthread_mutex_unlock(&mutex);

     //free space
     env->ReleaseStringUTFChars(workPath,c_path);
     return ret;
}

static jint native_parseState(JNIEnv *env, jobject object, jobject thiz, jstring rawState, jstring refParam, jstring workpath)
{

    jint ret;
	jclass cls;

	jstring tmp_jstr;
    jfieldID tmp_fId;
    jintArray array_state;

    char *state_buf;
    int  state_buf_len = 0;

    std::map<std::string, ControlLogic*>::iterator it;
    ControlLogic *p_controlEmul;


/*  raw_cmd_len = env->GetArrayLength(arrayRawState);
    raw_cmd_buf = (char*)malloc(raw_cmd_len);
    memset(raw_cmd_buf,0,raw_cmd_len);

    env->GetByteArrayRegion(arrayRawState,0,raw_cmd_len,(jbyte*)raw_cmd_buf);*/

    //prepare state_buf
    state_buf_len = getMaxStateBufSize();
    state_buf = (char*)malloc(state_buf_len);
    memset(state_buf,0,state_buf_len);

    std::string extraParam;
    const char* c_extraParam = NULL;
    if(refParam!=NULL){
      c_extraParam = env->GetStringUTFChars(refParam,0);
      extraParam = c_extraParam;
    }else{
      extraParam.clear();
    }

    const char* c_path = env->GetStringUTFChars(workpath,0);
    std::string path = c_path;

    const char* c_raw_state = env->GetStringUTFChars(rawState,0);
    std::string raw_state = c_raw_state;

    F_LOGD("In parse state: raw state:%s", raw_state.c_str());

    pthread_mutex_lock(&mutex);
    CHECKOUT_MODULE_START(path)

    ret = p_controlEmul->parse_state((const std::string)raw_state, (const std::string)extraParam, (int*)state_buf, &state_buf_len);

    CHECKOUT_MODULE_END
    pthread_mutex_unlock(&mutex);

	if((ret==0)&&(state_buf_len != 0)){
	   //devicestate
	   F_LOGD("State buffer len:%d\n",state_buf_len);
	   cls = env->GetObjectClass(thiz);

	   tmp_fId = env->GetFieldID(cls, "devState" , "[I");
	   if(tmp_fId == NULL){
		   F_LOGE("cannot access devState field\n");
		   return -1234;
	   }

	   if(state_buf_len!=0){
		  assert((state_buf_len%sizeof(int))!=0);
	   }

	   array_state = env->NewIntArray(state_buf_len/sizeof(int));
	   env->SetIntArrayRegion(array_state,0,state_buf_len/sizeof(int),(jint*)state_buf);
	   env->SetObjectField(thiz, tmp_fId, array_state);
	}else{
	   F_LOGD("ParseState failed:%d\n",ret);
	}

    //free space
	if(refParam!=NULL){
		env->ReleaseStringUTFChars(refParam,c_extraParam);
	}
	env->ReleaseStringUTFChars(workpath,c_path);
	env->ReleaseStringUTFChars(rawState,c_raw_state);

	free(state_buf);

	return ret;
}

static jint native_buildCommand(JNIEnv *env, jobject object, jobject thiz, jintArray arrayRawCmd, jintArray arrayState, jstring refParam, jstring workpath)
{

	int ret;
	jclass cls;
	jfieldID tmp_fId;
	jintArray array_state;
	jbyteArray array_cmd;


	int *raw_cmd_buf = NULL, *state_buf=NULL, *_state_buf=NULL;
	unsigned char *cmd_buf;

	int state_buf_len = 0, num_array_state = 0;
	int cmd_buf_len   = 0, num_array_cmd, raw_cmd_buf_len;

	std::map<std::string, ControlLogic*>::iterator it;
    ControlLogic *p_controlEmul;

	F_LOGD("obtain control command-->\n");

	const char* c_path = env->GetStringUTFChars(workpath,0);
    std::string path = c_path;

    std::string extraParam;
    const char* c_extraParam = NULL;
    if(refParam!=NULL){
      c_extraParam = env->GetStringUTFChars(refParam,0);
      extraParam = c_extraParam;
      F_LOGD("Extra param:%s",extraParam.c_str());
    }else{
      extraParam.clear();
    }
    //get state buf
    //assert(arrayState!=NULL);
    if(arrayState!=NULL){
       F_LOGD("Try to get current device state\n");
       num_array_state = env->GetArrayLength(arrayState);
       state_buf_len   = num_array_state*sizeof(jint);
       state_buf = (int*)malloc(state_buf_len);
       F_LOGD("Value of state_buf is:%p,",state_buf);
       env->GetIntArrayRegion(arrayState,0,num_array_state,(jint*)state_buf);

       _state_buf = (int*)malloc(getMaxStateBufSize());
       memset((void*)_state_buf, 0, getMaxStateBufSize());
       memcpy((void*)_state_buf, (void*)state_buf, state_buf_len);
       state_buf_len = getMaxStateBufSize();

       free(state_buf);
    }

    //get raw cmd buf
    F_LOGD("Get raw command\n");
    if(arrayRawCmd!=NULL){
       F_LOGD("Test obtain length of cmd buffer");
       num_array_cmd = env->GetArrayLength(arrayRawCmd);
       raw_cmd_buf_len = num_array_cmd*sizeof(jint);
       raw_cmd_buf = (int*)malloc(raw_cmd_buf_len);
       env->GetIntArrayRegion(arrayRawCmd,0,num_array_cmd,(jint*)raw_cmd_buf);
       F_LOGD("Test END");
    }

    cmd_buf_len = getMaxCmdBufSize();
    cmd_buf = (unsigned char*)malloc(cmd_buf_len);
    memset(cmd_buf,0,cmd_buf_len);

    F_LOGD("Ready to build command");
    pthread_mutex_lock(&mutex);

    CHECKOUT_MODULE_START(path)

    ret = p_controlEmul->obtain_cmd((const int*)raw_cmd_buf,
    		                        _state_buf,
	                                &state_buf_len,
	                                extraParam,
	                                cmd_buf,
	                                &cmd_buf_len);

    CHECKOUT_MODULE_END
    pthread_mutex_unlock(&mutex);

    if(arrayRawCmd!=NULL){
       free(raw_cmd_buf);
    }

	//devicestate
	cls = env->GetObjectClass(thiz);
    tmp_fId = env->GetFieldID(cls, "devState" , "[I");
	if(tmp_fId == NULL){
      F_LOGE("cannot access devState field\n");
      return -1234;
	}

	if((state_buf_len != 0)&&(_state_buf!=NULL)){
	   F_LOGD("Length of state_buf is:%d",state_buf_len);
	   array_state = env->NewIntArray(state_buf_len/sizeof(int));
	   env->SetIntArrayRegion(array_state,0,state_buf_len/(sizeof(int)),_state_buf);
	   env->SetObjectField(thiz, tmp_fId, array_state);
	}else{
	   array_state = env->NewIntArray(0);
	   env->SetObjectField(thiz, tmp_fId, array_state);
	}

	if(arrayState!=NULL){
	   F_LOGD("Free state_buf space");
	   F_LOGD("After execute method build_command, value of state_buf is:%p,",state_buf);
	   free(_state_buf);
	}

	if(cmd_buf_len != 0){
	   cls = env->GetObjectClass(thiz);

	   tmp_fId = env->GetFieldID(cls, "devCmd" , "[B");
	   if(tmp_fId == NULL){
		 F_LOGE("cannot access devCmd field\n");
	     return -1234;
	   }

	   array_cmd = env->NewByteArray(cmd_buf_len);
	   env->SetByteArrayRegion(array_cmd,0,cmd_buf_len,(jbyte*)cmd_buf);
	   env->SetObjectField(thiz, tmp_fId, array_cmd);
	}

	F_LOGD("Free cmd_buf space");
    //free space
	if(refParam!=NULL){
		env->ReleaseStringUTFChars(refParam,c_extraParam);
	}
	env->ReleaseStringUTFChars(workpath,c_path);
    free(cmd_buf);

	return ret;

	F_LOGD("obtain control command--<\n");
}

static jint native_getVersion(JNIEnv *env, jobject object, jobject thiz, jstring workpath)
{
    jint ret;
	jclass cls;
	jfieldID tmp_fId;
	jstring ver;

	std::string version;

	std::map<std::string, ControlLogic*>::iterator it;
    ControlLogic *p_controlEmul;

    F_LOGD("native_getVersion-->\n");

    const char* c_path = env->GetStringUTFChars(workpath,0);
    std::string path = c_path;

    pthread_mutex_lock(&mutex);
    CHECKOUT_MODULE_START(path)
    ret = p_controlEmul->getLibVer(version);
    CHECKOUT_MODULE_END
    pthread_mutex_unlock(&mutex);

	if(ret == 0){
	   F_LOGD("version:%s",version.c_str());
       cls = env->GetObjectClass(thiz);

       tmp_fId = env->GetFieldID(cls, "version" , "Ljava/lang/String;");
       if(tmp_fId == NULL){
    	  F_LOGE("cannot access version field\n");
          return -1234;
       }

       ver = env->NewStringUTF(version.c_str());
       env->SetObjectField(thiz, tmp_fId, ver);
    }

	env->ReleaseStringUTFChars(workpath,c_path);
	F_LOGD("native_getVersion return:%d-->\n",ret);
	return ret;
}

static jint native_getGwStateMask(JNIEnv *env, jobject object, jobject thiz, jstring workpath)
{
    jint ret;
	jclass cls;
	jfieldID tmp_fId;
	jstring mask;

	std::string state_mask;

	std::map<std::string, ControlLogic*>::iterator it;
    ControlLogic *p_controlEmul;

    F_LOGD("native_getGwStateMask-->\n");

    const char* c_path = env->GetStringUTFChars(workpath,0);
    std::string path = c_path;

    pthread_mutex_lock(&mutex);
    CHECKOUT_MODULE_START(path)
    ret = p_controlEmul->getGwStateMask(state_mask);
    CHECKOUT_MODULE_END
    pthread_mutex_unlock(&mutex);

	if(ret == 0){
       cls = env->GetObjectClass(thiz);

       tmp_fId = env->GetFieldID(cls, "mask" , "Ljava/lang/String;");
       if(tmp_fId == NULL){
    	  F_LOGE("cannot access mask field\n");
          return -1234;
       }

       mask = env->NewStringUTF(state_mask.c_str());
       env->SetObjectField(thiz, tmp_fId, mask);
    }

	env->ReleaseStringUTFChars(workpath,c_path);
	F_LOGD("native_getGwStateMask return:%d-->\n",ret);
	return ret;
}

static jint native_gQueryCommand(JNIEnv *env, jobject object, jobject thiz, jstring refParam, jstring workpath)
{
    jint ret;
	jclass cls;
	jfieldID tmp_fId;
	jbyteArray array_cmd;

	std::string version;
	unsigned char *cmd_buf;
	unsigned int cmd_buf_len = 0;

	std::map<std::string, ControlLogic*>::iterator it;
    ControlLogic *p_controlEmul;

    F_LOGD("native_gQueryCommand-->\n");

    const char* c_path = env->GetStringUTFChars(workpath,0);
    std::string path = c_path;

    std::string extraParam;
    const char* c_extraParam = NULL;
    if(refParam!=NULL){
       c_extraParam = env->GetStringUTFChars(refParam,0);
       extraParam = c_extraParam;
       F_LOGD("Extra param:%s",extraParam.c_str());
    }else{
       extraParam.clear();
    }

    cmd_buf_len = getMaxCmdBufSize();
    cmd_buf = (unsigned char*)malloc(cmd_buf_len);
    memset(cmd_buf,0,cmd_buf_len);

    pthread_mutex_lock(&mutex);
    CHECKOUT_MODULE_START(path)
    ret = p_controlEmul->state_query_cmd(extraParam,cmd_buf,&cmd_buf_len);
    CHECKOUT_MODULE_END
    pthread_mutex_unlock(&mutex);

	if(ret == 0){
	   cls = env->GetObjectClass(thiz);

	   tmp_fId = env->GetFieldID(cls, "devCmd" , "[B");
	   if(tmp_fId == NULL){
	      F_LOGE("cannot access devCmd field\n");
	      return -1234;
	   }

	   array_cmd = env->NewByteArray(cmd_buf_len);
	   env->SetByteArrayRegion(array_cmd,0,cmd_buf_len,(jbyte*)cmd_buf);
	   env->SetObjectField(thiz, tmp_fId, array_cmd);
    }

	F_LOGD("Free cmd_buf space");
    free(cmd_buf);
    //free space
	if(refParam!=NULL){
		env->ReleaseStringUTFChars(refParam,c_extraParam);
	}
	env->ReleaseStringUTFChars(workpath,c_path);

	F_LOGD("native_gQueryCommand return:%d-->\n",ret);
	return ret;
}

static jint native_setDemo(JNIEnv *env, jobject object, jobject thiz, jstring refParam, jstring workpath)
{
    jint ret;
	jclass cls;
	jfieldID tmp_fId;
	jintArray array_state;

	std::string version;
	int *state_buf=NULL, state_buf_len;

	std::map<std::string, ControlLogic*>::iterator it;
    ControlLogic *p_controlEmul;

    F_LOGD("native_setDemo-->\n");

    const char* c_path = env->GetStringUTFChars(workpath,0);
    std::string path = c_path;

    std::string extraParam;
    const char* c_extraParam = NULL;
    if(refParam!=NULL){
       c_extraParam = env->GetStringUTFChars(refParam,0);
       extraParam = c_extraParam;
       F_LOGD("Extra param:%s",extraParam.c_str());
    }else{
       extraParam.clear();
    }

    state_buf_len = getMaxStateBufSize();
    state_buf = (int*)malloc(state_buf_len);
    memset(state_buf,0,state_buf_len);

    pthread_mutex_lock(&mutex);
    CHECKOUT_MODULE_START(path)
    ret = p_controlEmul->demo(extraParam,state_buf,&state_buf_len);
    CHECKOUT_MODULE_END
    pthread_mutex_unlock(&mutex);

	if(ret == 0){
		cls = env->GetObjectClass(thiz);

	    tmp_fId = env->GetFieldID(cls, "devState" , "[I");
	    if(tmp_fId == NULL){
		   F_LOGE("cannot access devState field\n");
		   return -1234;
	    }

	    F_LOGD("Length of state_buf is:%d",state_buf_len);
		array_state = env->NewIntArray(state_buf_len/sizeof(int));
	    env->SetIntArrayRegion(array_state,0,state_buf_len/(sizeof(int)),state_buf);
	    env->SetObjectField(thiz, tmp_fId, array_state);
    }

	F_LOGD("Free state_buf");
	free(state_buf);
    //free space
	if(refParam!=NULL){
		env->ReleaseStringUTFChars(refParam,c_extraParam);
	}
	env->ReleaseStringUTFChars(workpath,c_path);

	F_LOGD("native_setDemo return:%d-->\n",ret);
	return ret;
}

#ifdef _D__FRG__
jint native_frg_cmd_ck_state(JNIEnv *env, jobject object, jobject thiz, jstring workPath)
{

	int ret;
	jclass cls;
	jfieldID tmp_fId;
	jbyteArray array_cmd;

    unsigned char *p_cmdBuf;
    int           len;

    //prepare cmdbuf
    len = getMaxCmdBufSize();
    p_cmdBuf = (unsigned char*)malloc(len);
    memset(p_cmdBuf,0,len);

    std::map<std::string, ControlLogic*>::iterator it;
    ControlLogic *p_controlEmul;

    std::string path = env->GetStringUTFChars(workPath,0);

    pthread_mutex_lock(&mutex);

    CHECKOUT_MODULE_START(path)

    ret = p_controlEmul->_frg_get_cmd_ck_state(p_cmdBuf, &len);

    CHECKOUT_MODULE_END
    pthread_mutex_unlock(&mutex);

    if(len != 0){

       cls = env->GetObjectClass(thiz);

       tmp_fId = env->GetFieldID(cls, "devCmd" , "[B");
       if(tmp_fId == NULL){
    	  F_LOGE("cannot access devCmd field\n");
    	  return -1234;
       }

       array_cmd = env->NewByteArray(len);
       env->SetByteArrayRegion(array_cmd,0,len,(jbyte*)p_cmdBuf);
       env->SetObjectField(thiz, tmp_fId, array_cmd);
    }

    free(p_cmdBuf);
    return ret;
}
#endif
