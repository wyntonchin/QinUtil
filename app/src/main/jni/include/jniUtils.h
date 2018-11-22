#ifndef _JNI_UTILS_H
#define _JNI_UTILS_H

#include <stdio.h>
#include <stdlib.h>

#include <jni.h>
#include <android/log.h>

#ifdef __cplusplus
extern "C" {
#endif

#if 0
/* log macro definition */
#define LOGI(...)	__android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGD(...)	__android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGW(...)	__android_log_print(ANDROID_LOG_WARN, LOG_TAG, __VA_ARGS__)
#define LOGE(...)	__android_log_print(ANDROID_LOG_ERROR,LOG_TAG, __VA_ARGS__)
#endif

#if 0
#define LOG_FUNCTION_START LOGI("Start run %s, %s, %d", __FUNCTION__, __FILE__, __LINE__)
#define LOG_FUNCTION_END   LOGI("End   run %s, %s, %d", __FUNCTION__, __FILE__, __LINE__)
#else
#define LOG_FUNCTION_START
#define LOG_FUNCTION_END
#endif

#define FREE_CHAR_POINTER(x)	if (x) { free(x); (x) = NULL;}

#define SET_JAVA_STRING_FIELD_VALUE(object, field, value)		\
if (value != NULL) {											\
	strValue = (*env)->NewStringUTF(env, value);				\
	(*env)->SetObjectField(env, object, field, strValue);		\
	(*env)->DeleteLocalRef(env, strValue);						\
}

#define SET_JAVA_STRING_FIELD_VALUE_EX(object, field, value)	\
SET_JAVA_STRING_FIELD_VALUE(object, field, value)				\
else {															\
	(*env)->SetObjectField(env, object, field, NULL);			\
}

#define SET_JAVA_BOOLEAN_FIELD_VALUE(object, field, value)		\
if (value != NULL) {											\
	objValue = booleanValueOf(env, atoi(value));				\
	(*env)->SetObjectField(env, object, field, objValue);		\
	(*env)->DeleteLocalRef(env, objValue);						\
}

#define SET_JAVA_BOOLEAN_FIELD_VALUE_EX(object, field, value)	\
SET_JAVA_BOOLEAN_FIELD_VALUE(object, field, value)				\
else {															\
	(*env)->SetObjectField(env, object, field, NULL);			\
}

#define SET_JAVA_DIDL_BOOLEAN_FIELD_VALUE(object, field, value)	\
if (value != NULL) {											\
	DIDL_StrtoBool(&bValue, value);								\
	objValue = booleanValueOf(env, bValue);						\
	(*env)->SetObjectField(env, object, field, objValue);		\
	(*env)->DeleteLocalRef(env, objValue);						\
}

#define SET_JAVA_DIDL_BOOLEAN_FIELD_VALUE_EX(object, field, value)	\
SET_JAVA_DIDL_BOOLEAN_FIELD_VALUE(object, field, value)				\
else {																\
	(*env)->SetObjectField(env, object, field, NULL);				\
}

#define SET_JAVA_DOUBLE_FIELD_VALUE(object, field, value)		\
if (value != NULL) {											\
	objValue = doubleValueOf(env, atof(value));					\
	(*env)->SetObjectField(env, object, field, objValue);		\
	(*env)->DeleteLocalRef(env, objValue);						\
}

#define SET_JAVA_DOUBLE_FIELD_VALUE_EX(object, field, value)	\
	SET_JAVA_DOUBLE_FIELD_VALUE(object, field, value)			\
else {															\
	(*env)->SetObjectField(env, object, field, NULL);			\
}

#define SET_JAVA_FLOAT_FIELD_VALUE(object, field, value)		\
if (value != NULL) {											\
	objValue = floatValueOf(env, atof(value));					\
	(*env)->SetObjectField(env, object, field, objValue);		\
	(*env)->DeleteLocalRef(env, objValue);						\
}

#define SET_JAVA_FLOAT_FIELD_VALUE_EX(object, field, value)		\
	SET_JAVA_FLOAT_FIELD_VALUE(object, field, value)			\
else {															\
	(*env)->SetObjectField(env, object, field, NULL);			\
}

#define SET_JAVA_INTEGER_FIELD_VALUE(object, field, value)		\
if (value != NULL) {											\
	objValue = integerValueOf(env, atoi(value));				\
	(*env)->SetObjectField(env, object, field, objValue);		\
	(*env)->DeleteLocalRef(env, objValue);						\
}

#define SET_JAVA_INTEGER_FIELD_VALUE_EX(object, field, value)	\
	SET_JAVA_INTEGER_FIELD_VALUE(object, field, value)			\
else {															\
	(*env)->SetObjectField(env, object, field, NULL);			\
}

#define SET_JAVA_LONG_FIELD_VALUE(object, field, value)			\
if (value != NULL) {											\
	objValue = longValueOf(env, atoll(value));					\
	(*env)->SetObjectField(env, object, field, objValue);		\
	(*env)->DeleteLocalRef(env, objValue);						\
}

#define SET_JAVA_LONG_FIELD_VALUE_EX(object, field, value)		\
	SET_JAVA_LONG_FIELD_VALUE(object, field, value)				\
else {															\
	(*env)->SetObjectField(env, object, field, NULL);			\
}

jboolean initJniUtils(JNIEnv* env);
void uninitJniUtils(JNIEnv* env);

JNIEnv* getJNIEnv();
JNIEnv* getCurrentThreadJNIEnv();
void detachCurrentThreadJNIEnv();

jboolean registerNativeMethods(JNIEnv* env, const char* className, JNINativeMethod* gMethods, int numMethods);

jboolean jniThrowException(JNIEnv* env, const char* className, const char* msg);

jobject booleanValueOf(JNIEnv* env, jboolean value);
jobject doubleValueOf(JNIEnv* env, jdouble value);
jobject floatValueOf(JNIEnv* env, jfloat value);
jobject integerValueOf(JNIEnv* env, jint value);
jobject longValueOf(JNIEnv* env, jlong value);
jobjectArray createStringArrayObject(JNIEnv* env, const char** ppszValues, int count);

void deleteJniLocalRef(JNIEnv* env, jobject localRef);

jstring newJniStringUTF(JNIEnv* env, const char* value);

const char* getJniStringUTFChars(JNIEnv* env, jstring jstr);
void releaseJniUTFChars(JNIEnv* env, jstring jstr, const char* utf);
void printThizClassName(JNIEnv* env, jobject thiz);

#ifdef __cplusplus
}
#endif

#endif /* _JNI_UTILS_H */
