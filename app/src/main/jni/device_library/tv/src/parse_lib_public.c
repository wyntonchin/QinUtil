//
//  parse_lib_public.c
//  EasyLink
//
//  Created by lx on 2018/10/30.
//  Copyright © 2018年 Hisense. All rights reserved.
//

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <pthread.h>
#include "json.h"
#include "parse_lib_public.h"

#define _SHORT_FILE_NAME_ ((strrchr(__FILE__, '/') == NULL)? __FILE__: (strrchr(__FILE__, '/') + 1))
#define HS_LOG_I(fmt,args...) fprintf(stderr, "==>[%s %d]:  "fmt, _SHORT_FILE_NAME_, __LINE__,##args);
#define PARSE_LIB_VERSION       "1.1.2"

#define CMD_TOTAL                 8

#define HS_CONTROL_CMDCNT          "cmdCnt"
#define HS_CONTROL_CMD             "cmd"
#define HS_CONTROL_CMDNUM          "cmdNum"
#define HS_CONTROL_CMDPARA         "cmdPara"

typedef unsigned short      puint16_t;
static pthread_mutex_t mutex4parselib;
static int isInit = 0;

static void parse_lib_init()
{
    pthread_mutex_init(&mutex4parselib, NULL);
    isInit = 1;
}

typedef struct {
    unsigned int cmdCnt;
    int cmd[CMD_TOTAL];
    int cmdPara[CMD_TOTAL];
}cmd_s;

static int parse_cmd(int *input, cmd_s *out)
{
    int i = 0;
    out->cmdCnt = *input++;
    for (i=0; i<out->cmdCnt; i++)
    {
        out->cmd[i] = *input++;
        out->cmdPara[i] = *input++;
    }
    return 0;
}

static void buildJson(json_item_t *streamsJson, jint32_t value,  jint32_t id)
{
    json_item_t *item = J_CREATE(JSON_OBJECT, J_NULL);
    json_item_add_subitem(item, J_CREATE_I(HS_CONTROL_CMDNUM, value));
    json_item_add_subitem(item, J_CREATE_I(HS_CONTROL_CMDPARA, id));
    json_item_add_subitem(streamsJson, item);
}

static void jsonToText(unsigned char *text, json_item_t *json)
{
    
    json_item_parse_to_text(json, (char *)text);
}

/******************************************************************************
 Function: build_cmd
 Description: 生成控制指令
 ******************************************************************************/
int build_cmd(const int *porg_cmd_buf,int *pstate_buf,unsigned int *pstate_num,
              RefParam *pref_param,unsigned char *praw_cmd_buf,unsigned int *praw_cmd_buf_len)
{
    int ret = 0;
    int cmdCnt = 0;
    int cmdNum = 0;
    int cmdPara = 0;
    
    json_item_t *json = J_CREATE(JSON_OBJECT, J_NULL);
    json_item_t *cmdJson = J_CREATE(JSON_ARRAY, HS_CONTROL_CMD);
    
    unsigned long cmd_buf_len;
    cmd_buf_len = strlen((char *)porg_cmd_buf);
    if (cmd_buf_len == 0)
    {
        HS_LOG_I("input cmd empty cmd_buf_len=%lu\n", cmd_buf_len);
        return -1;
    }
    
    if (isInit == 0)
        parse_lib_init();
    
    pthread_mutex_lock(&mutex4parselib);
    
    cmd_s cmd;
    memset(&cmd,0,sizeof(cmd_s));
    
    /*控制命令解析*/
    ret = parse_cmd((int *)porg_cmd_buf,&cmd);
    if(ret == -1)
    {
        pthread_mutex_unlock(&mutex4parselib);
        return -1;
    }
//    for (i=0; i<cmd.cmdCnt; i++)
//    {
//        HS_LOG_I("cmdCnt=%d, cmd[%d]=%d, cmdPara[%d]=%d \n", cmd.cmdCnt, i, cmd.cmd[i], i, cmd.cmdPara[i]);
//    }
    
    cmdCnt = cmd.cmdCnt;
    json_item_t *item = J_CREATE_I(HS_CONTROL_CMDCNT, cmdCnt);
    json_item_add_subitem(json, item);

    for (int i=0; i<cmdCnt; i++) {
        cmdNum = cmd.cmd[i];
        cmdPara = cmd.cmdPara[i];
        buildJson(cmdJson, cmdNum, cmdPara);
    }
    
    json_item_add_subitem(json, cmdJson);
    jsonToText(praw_cmd_buf, json);
    *praw_cmd_buf_len = (unsigned int)strlen((char *)praw_cmd_buf);//控制指令长度
    
    if (strlen((char *)praw_cmd_buf) == 0)
    {
        HS_LOG_I(" buildJson praw_cmd_buf_len =%lu\n", cmd_buf_len);
        pthread_mutex_unlock(&mutex4parselib);
        return -1;
    }
    
    if (json)
    {
        json_item_destroy(json);
    }
    
    pthread_mutex_unlock(&mutex4parselib);
    
    return 0;
}

int get_version(char* ver_buf,unsigned int len)
{
    strcpy(ver_buf,PARSE_LIB_VERSION);
    return 0;
}
