#ifndef _DEVCTRL_LOG_
#define _DEVCTRL_LOG_


#ifdef __cplusplus
extern "C" {
#endif

#define TAG_INFO    "MS_DEV_CTRL_INFO==>"
#define TAG_ERROR   "MS_DEV_CTRL_ERROR==>"
#define TAG_WARN    "MS_DEV_CTRL_WARN==>"
#define TAG_DEBUG   "MS_DEV_CTRL_DEBUG==>"


#if defined(PLT_ANDROID)
#include <jni.h>
#include <android/log.h>

//#define  LOGI_(...)  __android_log_print(ANDROID_LOG_INFO,TAG_INFO,__VA_ARGS__)
//#define  LOGE_(...)  __android_log_print(ANDROID_LOG_ERROR,TAG_ERROR,__VA_ARGS__)
#ifdef DEBUG
#define F_LOGI(...)  __android_log_print(ANDROID_LOG_ERROR,TAG_INFO,__VA_ARGS__)
#define F_LOGE(...)  __android_log_print(ANDROID_LOG_INFO,TAG_ERROR,__VA_ARGS__)
#define F_LOGW(...)  __android_log_print(ANDROID_LOG_WARN,TAG_WARN,__VA_ARGS__)
#define F_LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,TAG_DEBUG,__VA_ARGS__)

#else
#define F_LOGE(...)
#define F_LOGI(...)
#define F_LOGD(...)
#define F_LOGW(...)
#endif


#elif defined(PLT_LINUX)

//#define LOGI_(...) printf(TAG_INFO"%s:%d  ",__FILE__,__LINE__),printf(__VA_ARGS__)
//#define LOGE_(...) printf(TAG_ERROR"%s:%d  ",__FILE__,__LINE__),printf(__VA_ARGS__)



#if defined(DEBUG)
#define F_LOGI(...) printf(TAG_INFO"%s:%d  ",__FILE__,__LINE__),printf(__VA_ARGS__)
#define F_LOGE(...) printf(TAG_ERROR"%s:%d  ",__FILE__,__LINE__),printf(__VA_ARGS__)
#define F_LOGW(...) printf(TAG_WARN"%s:%d  ",__FILE__,__LINE__),printf(__VA_ARGS__)
#define F_LOGD(...) printf(TAG_DEBUG"%s:%d  ",__FILE__,__LINE__),printf(__VA_ARGS__)
#else
#define F_LOGI(...)
#define F_LOGE(...)
#define F_LOGW(...)
#define F_LOGD(...)
#endif

#elif defined(PLAT_WIN32)       
//#define LOGI_(...) printf(TAG_INFO"%s:%d  ",__FILE__,__LINE__),printf(__VA_ARGS__)
//#define LOGE_(...) printf(TAG_ERROR"%s:%d  ",__FILE__,__LINE__),printf(__VA_ARGS__)
#define F_LOGI(...) printf(TAG_INFO"%s:%d  ",__FILE__,__LINE__),printf(__VA_ARGS__)
#define F_LOGE(...) printf(TAG_ERROR"%s:%d  ",__FILE__,__LINE__),printf(__VA_ARGS__)
#define F_LOGW(...) printf(TAG_WARN"%s:%d  ",__FILE__,__LINE__),printf(__VA_ARGS__)

#if defined(DEBUG)
#define F_LOGD(...) printf(TAG_DEBUG"%s:%d  ",__FILE__,__LINE__),printf(__VA_ARGS__)
#else
#define F_LOGD(...)
#endif

#else
#error "ERROR:PLAT_NOTDEFINED!!!"

#endif


#ifdef __cplusplus
}
#endif

#endif
