#include <stdio.h>
#include "jniUtils.h"

#define LOG_TAG		"JNI-Utils"

JavaVM *gJavaVM = NULL;

struct JniConstants {
	jclass booleanClass;
	jclass integerClass;
	jclass longClass;
	jclass doubleClass;
	jclass floatClass;
	jclass stringClass;

	jmethodID booleanValueOfMethod;
	jmethodID integerValueOfMethod;
	jmethodID longValueOfMethod;
	jmethodID doubleValueOfMethod;
	jmethodID floatValueOfMethod;
};

struct JniConstants mJniConstants;

static void initJniConstants(JNIEnv* env) {
	mJniConstants.booleanClass = (*env)->NewGlobalRef(env, (*env)->FindClass(env, "java/lang/Boolean"));
	mJniConstants.integerClass = (*env)->NewGlobalRef(env, (*env)->FindClass(env, "java/lang/Integer"));
	mJniConstants.longClass = (*env)->NewGlobalRef(env, (*env)->FindClass(env, "java/lang/Long"));
	mJniConstants.doubleClass = (*env)->NewGlobalRef(env, (*env)->FindClass(env, "java/lang/Double"));
	mJniConstants.floatClass = (*env)->NewGlobalRef(env, (*env)->FindClass(env, "java/lang/Float"));
	mJniConstants.stringClass = (*env)->NewGlobalRef(env, (*env)->FindClass(env, "java/lang/String"));

	mJniConstants.booleanValueOfMethod = (*env)->GetStaticMethodID(env, mJniConstants.booleanClass, "valueOf", "(Z)Ljava/lang/Boolean;");
	mJniConstants.integerValueOfMethod = (*env)->GetStaticMethodID(env, mJniConstants.integerClass, "valueOf", "(I)Ljava/lang/Integer;");
	mJniConstants.longValueOfMethod = (*env)->GetStaticMethodID(env, mJniConstants.longClass, "valueOf", "(J)Ljava/lang/Long;");
	mJniConstants.doubleValueOfMethod = (*env)->GetStaticMethodID(env, mJniConstants.doubleClass, "valueOf", "(D)Ljava/lang/Double;");
	mJniConstants.floatClass = (*env)->GetStaticMethodID(env, mJniConstants.floatClass, "valueOf", "(F)Ljava/lang/Float;");
}

static void uninitJniConstants(JNIEnv* env) {
	(*env)->DeleteGlobalRef(env, mJniConstants.booleanClass);
	(*env)->DeleteGlobalRef(env, mJniConstants.integerClass);
	(*env)->DeleteGlobalRef(env, mJniConstants.longClass);
	(*env)->DeleteGlobalRef(env, mJniConstants.doubleClass);
	(*env)->DeleteGlobalRef(env, mJniConstants.stringClass);
}

jboolean initJniUtils(JNIEnv* env)
{
	initJniConstants(env);
	return JNI_TRUE;
}

void uninitJniUtils(JNIEnv* env)
{
	uninitJniConstants(env);
}

JNIEnv* getJNIEnv()
{
    JNIEnv* env = NULL;
    if ((*gJavaVM)->GetEnv(gJavaVM, (void**) &env, JNI_VERSION_1_4) != JNI_OK) {
    	//LOGE("Failed to obtain JNIEnv");
    }
    return env;
}

JNIEnv* getCurrentThreadJNIEnv()
{
    JNIEnv* env = NULL;
    if ((*gJavaVM)->AttachCurrentThread(gJavaVM, &env, NULL) != JNI_OK) {
    	//LOGE("Failed to obtain current thread JNIEnv");
    }
    return env;
}

void detachCurrentThreadJNIEnv()
{
	(*gJavaVM)->DetachCurrentThread(gJavaVM);
}

/*
 * Throw an exception with the specified class and an optional message.
 */
jboolean jniThrowException(JNIEnv* env, const char* className, const char* msg)
{
    jclass exceptionClass = (*env)->FindClass(env, className);
    if (exceptionClass == NULL) {
        //LOGE("Unable to find exception class %s", className);
        return JNI_FALSE;
    }

    if ((*env)->ThrowNew(env, exceptionClass, msg) != JNI_OK) {
        //LOGE("Failed throwing '%s' '%s'", className, msg);
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

/*
 * Register several native methods for one class.
 */
jboolean registerNativeMethods(JNIEnv* env, const char* className, JNINativeMethod* gMethods, int numMethods)
{
	jclass clazz;

	clazz = (*env)->FindClass(env, className);
	if (clazz == NULL) {
		return JNI_FALSE;
	}

	if ((*env)->RegisterNatives(env, clazz, gMethods, numMethods) < 0) {
		return JNI_FALSE;
	}

	return JNI_TRUE;
}

jobject booleanValueOf(JNIEnv* env, jboolean value)
{
    return (*env)->CallStaticObjectMethod(env, mJniConstants.booleanClass,
    									  mJniConstants.booleanValueOfMethod, value);
}

jobject doubleValueOf(JNIEnv* env, jdouble value)
{
    return (*env)->CallStaticObjectMethod(env, mJniConstants.doubleClass,
        								  mJniConstants.doubleValueOfMethod, value);
}

jobject floatValueOf(JNIEnv* env, jfloat value)
{
	return (*env)->CallStaticObjectMethod(env, mJniConstants.floatClass,
										  mJniConstants.floatValueOfMethod, value);
}

jobject integerValueOf(JNIEnv* env, jint value)
{
	return (*env)->CallStaticObjectMethod(env, mJniConstants.integerClass,
	        							  mJniConstants.integerValueOfMethod, value);
}

jobject longValueOf(JNIEnv* env, jlong value)
{
	return (*env)->CallStaticObjectMethod(env, mJniConstants.longClass,
		       							  mJniConstants.longValueOfMethod, value);
}

jobjectArray createStringArrayObject(JNIEnv* env, const char** ppszValues, int count)
{
	int i = 0;
	jstring strValue = NULL;
	jobjectArray strArray = NULL;

	if (ppszValues != NULL && count > 0) {
		strArray = (*env)->NewObjectArray(env, count, mJniConstants.stringClass, NULL);
		for (i = 0; i < count; i++) {
			strValue = newJniStringUTF(env, ppszValues[i]);
			(*env)->SetObjectArrayElement(env, strArray, i, strValue);
			deleteJniLocalRef(env, strValue);
		}
	}

	return strArray;
}

void deleteJniLocalRef(JNIEnv* env, jobject localRef)
{
	if (env != NULL && localRef != NULL) {
		(*env)->DeleteLocalRef(env, localRef);
	}
}

jstring newJniStringUTF(JNIEnv* env, const char* value)
{
	jstring strValue = NULL;

	if (env != NULL && value != NULL) {
		strValue = (*env)->NewStringUTF(env, value);
	}

	return strValue;
}

const char* getJniStringUTFChars(JNIEnv* env, jstring jstr)
{
	const char* utfValue = NULL;

	if (jstr != NULL) {
		utfValue = (*env)->GetStringUTFChars(env, jstr, NULL);
	}

	return utfValue;
}

/*
 * Release a string created by GetStringUTFChars().
 */
void releaseJniUTFChars(JNIEnv* env, jstring jstr, const char* utf)
{
	if (jstr != NULL && utf != NULL) {
		(*env)->ReleaseStringUTFChars(env, jstr, utf);
	}
}


/*
 * Print current class name
 */
void printThizClassName(JNIEnv *env,jobject thiz){

	jclass cls;
	cls = (*env)->GetObjectClass(env,thiz);

	// First get the class object
	jmethodID mid = (*env)->GetMethodID(env, cls, "getClass", "()Ljava/lang/Class;");
	jobject clsObj = (*env)->CallObjectMethod(env,thiz, mid);

	// Now get the class object's class descriptor
	cls = (*env)->GetObjectClass(env,clsObj);

	// Find the getName() method on the class object
	mid = (*env)->GetMethodID(env,cls, "getName", "()Ljava/lang/String;");

	// Call the getName() to get a jstring object back
	jstring strObj = (jstring)(*env)->CallObjectMethod(env,clsObj, mid);

	// Now get the c string from the java jstring object
	const char* str = (*env)->GetStringUTFChars(env,strObj, NULL);

	// Print the class name
	//LOGI("Calling class is: %s\n", str);

	// Release the memory pinned char array
	(*env)->ReleaseStringUTFChars(env,strObj, str);
}
