DEBUG=y

#following dirs should not be included
EXCLUDE_DIRS=./device_library
#make target under subdirs recrusivly 
MODULE_DIRS_ALL=$(shell find ./device_library -maxdepth 1 -type d)
$(info "Find submodule dirs:" $(MODULE_DIRS_ALL))
MODULE_DIRS_FILTER=$(filter-out $(EXCLUDE_DIRS),$(MODULE_DIRS_ALL))
$(info "Fillter excluded dirs and target dirs are:" $(MODULE_DIRS_FILTER))

#function:make submodules 
#$(1)--dirs include target submodules
define make-submodules-all
$(foreach dir,$(1)\
,$(shell make  -C $(dir)  target=$(addsuffix .so, $(addprefix lib,$(notdir $(patsubst ./%,%,$(dir))))) debug=$(DEBUG) all))
endef

define make-submodules-clean
$(foreach dir,$(1),$(shell make -C $(dir)  target=$(addsuffix .so, $(addprefix lib,$(notdir $(patsubst ./%,%,$(dir))))) debug=$(DEBUG) clean))
endef

define make-submodules-install
$(foreach dir,$(1),$(shell make -C $(dir)  target=$(addsuffix .so, $(addprefix lib,$(notdir $(patsubst ./%,%,$(dir))))) debug=$(DEBUG) outdir=$(shell pwd)/out install))
endef

#make main target
$(info )
#VPATH=.:./impl:./3rd_party/jsoncpp
$(info "Make main target")
VPATH=.:./3rd_party/libb64/src/:./src/
CC=g++
CPPFLAGS=
ifeq ($(DEBUG),y)
CFLAGS=-g -DDEBUG
endif

BUFFERSIZE=16777216
CFLAGS+=-DBUFFERSIZE=$(BUFFERSIZE)

MAXSTATEBUFSIZE=1024*10
CFLAGS+=-DMAXSTATEBUFSIZE=$(MAXSTATEBUFSIZE)

MAXCMDBUFSIZE=1024*10
CFLAGS+=-DMAXCMDBUFSIZE=$(MAXSTATEBUFSIZE)

CFLAGS+=-Wall -O1 -std=c++11 -D_REENTRANT  -fPIC
CFLAGS+=-DPLT_LINUX
LDFLAGS=-fPIC -shared 
INCLUDES=-I./include/  -I./3rd_party/libb64/
	   
TARGET=libdev_control_logic.so
OBJDIR=./.objs
SRCDIR:=$(subst :, ,$(VPATH))
$(info $(SRCDIR))
CPPFILES:=$(foreach files,$(SRCDIR),$(filter-out jni_control_logici.cpp, $(notdir $(wildcard $(files)/*.cpp))))
CFILES:=$(foreach files,$(SRCDIR),$(filter-out jniUtils.c, $(notdir $(wildcard $(files)/*.c))))
$(info "List compiled files:" $(CPPFILES) $(CFILES))
OBJS:=$(patsubst %.cpp, %.o, $(CPPFILES)) $(patsubst %.c, %.o, $(CFILES))
$(info "List obj files:" $(OBJS))


all:objdir $(TARGET) 
	#$(call make-submodules-all,$(MODULE_DIRS_FILTER))

.PHONY:objdir
objdir:

	
%.o:%.cpp
	$(CC) $(CPPFLAGS) $(CFLAGS) $(INCLUDES) -c $< -o $(OBJDIR)/$@
%.o:%.c
	$(CC) $(CPPFLAGS) $(CFLAGS) $(INCLUDES) -c $< -o $(OBJDIR)/$@
	
$(TARGET):$(OBJS)
	$(CC) $(LDFLAGS) $(OBJDIR)/*.o  -o $@

.PHONY:clean 
clean:
	rm -fr $(TARGET) 
	rm -fr $(OBJDIR)/*.o
	#$(call make-submodules-clean,$(MODULE_DIRS_FILTER))
	   
.PHONY:install
install:
ifeq ($(DEBUG),y)
	cp ./$(TARGET)  ./out/Debug/
else
	cp ./$(TARGET)  ./out/Release/
endif
	#$(call make-submodules-install,$(MODULE_DIRS_FILTER))

