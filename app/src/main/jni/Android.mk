LOCAL_PATH:=$(call my-dir)

include $(CLEAR_VARS)

#for enable or disable DEBUG log output/-g option
DEBUG=y

LOCAL_MODULE:= dev_control_logic

MANAGER_SRC_FILES := ./src/control_logic.cpp \
                     ./src/jni_interface.cpp \
                     ./src/jniUtils.c


#CJSON_LIB_FILES := ./3rd_party/libb64/src/cdecode.c \
                   ./3rd_party/libb64/src/cencode.c

CBASE64_LIB_FILES := ./3rd_party/ZBase64_cpp/ZBase64.cpp

LOCAL_SRC_FILES := $(MANAGER_SRC_FILES) \
                   $(CBASE64_LIB_FILES)

$(warning $(LOCAL_SRC_FILE))

LOCAL_C_INCLUDES := $(LOCAL_PATH)/ \
                    $(LOCAL_PATH)/include \
                    $(LOCAL_PATH)/3rd_party/ZBase64_cpp/

#BUFFERSIZE=16777216
#LOCAL_CPPFLAGS+=-DBUFFERSIZE=$(BUFFERSIZE)

MAXSTATEBUFSIZE=1024*10
LOCAL_CPPFLAGS+=-DMAXSTATEBUFSIZE=$(MAXSTATEBUFSIZE)

MAXCMDBUFSIZE=1024*10
LOCAL_CPPFLAGS+=-DMAXCMDBUFSIZE=$(MAXSTATEBUFSIZE)

MAXVERSIONBUF=100
LOCAL_CPPFLAGS+=-DMAXVERSIONBUF

###############_CLOUD:use new package name
LOCAL_CPPFLAGS+=-D_CLOUD

LOCAL_CPPFLAGS+=-DPLT_ANDROID

#LOCAL_CPPFLAGS:=-DPLT_ANDROID
ifeq ($(DEBUG),y)
$(info Check debug mode in main mk:$(DEBUG))
LOCAL_CFLAGS+=-DDEBUG -g
LOCAL_CPPFLAGS+=-DDEBUG -g
#LOCAL_CPPFLAGS+=-D_D__FRG__
#LOCAL_CPPFLAGS+=-D_D__AC__
endif

#endif

LOCAL_CPPFLAGS += -std=c++11 -fexceptions
LOCAL_LDLIBS := -llog -latomic

include $(BUILD_SHARED_LIBRARY)

#include submodules
include $(CLEAR_VARS)

ROOT_PATH:=$(LOCAL_PATH)
$(info Washer logic module root dir:$(ROOT_PATH))
include $(call all-makefiles-under, $(LOCAL_PATH)/device_library)