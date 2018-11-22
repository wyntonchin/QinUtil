LOCAL_PATH:=$(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE:= $(notdir $(LOCAL_PATH))
$(info Submodule name: $(LOCAL_MODULE))
LOCAL_ROOT_PATH:=$(LOCAL_PATH)
LOCAL_PATH:=$(LOCAL_PATH)/src
MANAGER_SRC_FILES := $(notdir $(wildcard $(LOCAL_PATH)/*.c))

LOCAL_SRC_FILES := $(MANAGER_SRC_FILES)
$(info Submodule src files: $(MANAGER_SRC_FILES))

LOCAL_C_INCLUDES := $(LOCAL_ROOT_PATH)/include/  $(ROOT_PATH)/  $(ROOT_PATH)/include

$(info check .h:$(LOCAL_C_INCLUDES))
LOCAL_CFLAGS:=-DPLT_ANDROID
LOCAL_LDLIBS := -llog
$(info debug in $(LOCAL_PATH):$(DEBUG))
ifeq ($(DEBUG),y)
LOCAL_CFLAGS+=-DDEBUG -g 
endif
include $(BUILD_SHARED_LIBRARY)