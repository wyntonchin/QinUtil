//
//  json.c
//  json
//
//  Created by vito on 15/11/23.
//  Copyright ? 2015�� vito. All rights reserved.
//

#include "json.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

//#define J_PRINTF mprintf
//#define J_LOG(...) J_PRINTF("json %s[%d]:", __func__, __LINE__);J_PRINTF(__VA_ARGS__);J_PRINTF("\n");

#ifndef __func__
#define __func__ __FUNCTION__
#endif

#ifndef J_LOG
#define J_LOG(...) 
#endif



#define J_TRUE_STR "mtrue"
#define J_FALSE_STR "mfalse"


#ifdef ESP8266
#define _xtag __attribute__((section(".irom0.text")))
#endif

#ifndef _xtag
#define _xtag 
#endif

//#define DEBUG_MEM
#ifdef DEBUG_MEM
#define malloc dbg_malloc
#define free dbg_free

void *dbg_malloc(size_t size);
void dbg_free(void *p);

#endif

#define MAX_REGIST_VALUE_NAME 20
static const char *g_regist_value_name[MAX_REGIST_VALUE_NAME];

_xtag void json_item_regist_value_name(const char *value)
{
    juint16_t i;
    for(i = 0; i < MAX_REGIST_VALUE_NAME; i++)
    {
        if(g_regist_value_name[i] == J_NULL)
        {
            g_regist_value_name[i] = value;
            return;
        }
    }
}

_xtag static void _json_item_set_name(json_item_t *item, const char *name)
{
    if(name == J_NULL)
    {
        item->name = (char *)"";
        item->registed_name = J_TRUE;
        return;
    }

    //����ע���������
    juint16_t i;
    for(i = 0; i < MAX_REGIST_VALUE_NAME; i++)
    {
        if(g_regist_value_name[i])
        {
            if(strcmp(name, g_regist_value_name[i]) == 0)
            {
                item->name = (char *)g_regist_value_name[i];
                item->registed_name = J_TRUE;
                return;
            }
        }
    }

    item->name = malloc(strlen(name) + 1);
    strcpy(item->name, name);
    item->registed_name = J_FALSE;
}

_xtag json_item_t *json_item_create(json_value_type_t type, const char *name)
{
    json_item_t *item = (json_item_t *)malloc(sizeof(json_item_t));
    item->type = type;
    _json_item_set_name(item, name);
    item->value = J_NULL;
    item->next = J_NULL;
    return item;
}

_xtag void json_item_destroy(json_item_t *item)
{
    if(!item)
    {
        return;
    }

    if(!item->registed_name)
    {
        free(item->name);
    }

    if(item->type == JSON_OBJECT || item->type == JSON_ARRAY)
    {
        json_item_t *subitem = (json_item_t *)item->value;
        json_item_t *curitem;
        while(subitem)
        {
            curitem = subitem;
            subitem = subitem->next;
            json_item_destroy(curitem);
        }
    }
    else
    {
        if(item->value)
        {
            free(item->value);
        }
    }

    free(item);
}

_xtag jbool_t json_object_item_has_subitem(json_item_t *item)
{
    return (item->value != J_NULL);
}

_xtag void json_item_add_subitem(json_item_t *item, json_item_t *subitem)
{
    json_item_t *first = (json_item_t *)item->value;
    if(first == J_NULL)
    {
        item->value = subitem;
    }
    else
    {
        while(first->next)
        {
            first = first->next;
        }
        first->next = subitem;
    }
}

_xtag void json_string_item_set_value(json_item_t *item, const char *value)
{
    if(value != J_NULL)
    {
        item->value = malloc(strlen(value) + 1);
        strcpy(item->value, value);
    }
}

_xtag void _parse_to_echo(json_item_t *item, json_echo_func_t echo_func, char *text_buf)
{
    jbool_t is_obj = item->type == JSON_OBJECT;

    echo_func(text_buf, is_obj ? "{" : "[", J_FALSE);

    json_item_t *subitem = (json_item_t *)item->value;

    while(subitem)
    {
        //value name
        if(is_obj)
        {
            echo_func(text_buf, "\"", J_FALSE);
            echo_func(text_buf, subitem->name, J_FALSE);
            echo_func(text_buf, "\"", J_FALSE);
            echo_func(text_buf, ":", J_FALSE);
        }

        switch(subitem->type)
        {
            case JSON_ARRAY:
            case JSON_OBJECT:
                _parse_to_echo(subitem, echo_func, text_buf);
                break;

            default:
                if(subitem->type == JSON_STRING && subitem->value)
                {
                    echo_func(text_buf, "\"", J_FALSE);
                }

                if(subitem->value == J_NULL)
                {
                    echo_func(text_buf, "null", J_FALSE);
                }
                else
                {
                    echo_func(text_buf, (char *)subitem->value, J_FALSE);
                }

                if(subitem->type == JSON_STRING && subitem->value)
                {
                    echo_func(text_buf, "\"", J_FALSE);
                }
                break;
        }

        if(subitem->next)
        {
            echo_func(text_buf, ",", J_FALSE);
        }

        subitem = subitem->next;
    }

    echo_func(text_buf, is_obj ? "}" : "]", J_FALSE);
}

_xtag void json_item_parse_to_echo(json_item_t *item, json_echo_func_t echo_func, char *text_buf)
{
    switch(item->type)
    {
        case JSON_OBJECT:
        case JSON_ARRAY:
            _parse_to_echo(item, echo_func, text_buf);
            break;

        default:
            J_LOG("type:%d error.", item->type);
    }
}

_xtag json_item_t *json_string_item_create(const char *name, const char *string)
{
    json_item_t *item = json_item_create(JSON_STRING, name);
    json_string_item_set_value(item, string);
    return item;
}

_xtag json_item_t *json_number_item_create(const char *name, const char *number)
{
    json_item_t *item = json_item_create(JSON_NUMBER, name);
    json_number_item_set_value(item, number);
    return item;
}

_xtag json_item_t *json_int32_item_create(const char *name, jint32_t value)
{
    char buf[20] = {0};
    sprintf(buf, "%d", value);

    json_item_t *item = json_item_create(JSON_NUMBER, name);
    json_number_item_set_value(item, buf);
    return item;
}

_xtag json_item_t *json_bool_item_create(const char *name, jbool_t value)
{
    json_item_t *item = json_item_create(JSON_BOOL, name);
    json_bool_item_set_value(item, value);
    return item;
}

_xtag static void echo(char *text_buf, const char *str, jbool_t end)
{
    strcat(text_buf, str);
}

_xtag void json_item_parse_to_text(json_item_t *item, char *text_buf)
{
    text_buf[0] = '\0';
    json_item_parse_to_echo(item, echo, text_buf);
}

_xtag static void eval_echo(char *text_buf, const char *str, jbool_t end)
{
    jint32_t *n = (jint32_t *)text_buf;
    *n = *n + (jint32_t)strlen(str);
}

_xtag jint32_t json_item_eval_parse_text_len(json_item_t *item)
{
    jint32_t n = 0;
    json_item_parse_to_echo(item, eval_echo, (char *)&n);
    return n;
}

_xtag jint32_t _skip_space(const char *text)
{
    jint32_t skip_n = 0;
    while(text[skip_n] != '\0')
    {
        if(text[skip_n] == '\t'
           || text[skip_n] == '\n'
           || text[skip_n] == '\r'
           || text[skip_n] == ' ')
        {
            skip_n++;
        }
        else
        {
            break;
        }
    }
    return skip_n;
}

_xtag json_item_t *_json_parse_array_item(const char *name, const char *text, jint32_t *parsed_len)
{
    return J_NULL;
}

_xtag json_item_t *_json_parse_item(const char *name, const char *text, jint32_t *pos)
{
    char *subname = J_NULL;
    char *subvalue = J_NULL;
    const char *p;
    jint32_t tmp_len;
    json_item_t *subitem;
    jbool_t is_obj = text[*pos] == '{';
    json_item_t *item = json_item_create(is_obj ? JSON_OBJECT : JSON_ARRAY, name);

    (*pos)++; //skip {

    while(text[*pos] != '\0')
    {
        *pos += _skip_space(text + *pos);

        //skip ,
        if(text[*pos] == ',')
        {
            (*pos)++;
        }

        *pos += _skip_space(text + *pos);

        //get object subitem name
        if(is_obj)
        {
            if(text[*pos] == '\"')
            {
                (*pos)++; //skip "
                p = strchr(text + *pos, '\"');
                if(p == J_NULL)
                {
                    goto end;
                }

                tmp_len = (jint32_t)(p - &text[*pos]);
                subname = (char *)(malloc(tmp_len + 1));
                memcpy(subname, text + *pos, tmp_len);
                subname[tmp_len] = '\0';

                *pos = *pos + tmp_len + 1; //skip name, "

                *pos += _skip_space(text + *pos);

                if(text[*pos] != ':')
                {
                    goto end;
                }
                (*pos)++; //skip :
                *pos += _skip_space(text + *pos);
            }
            else
            {
                goto end;
            }
        }

        //get subitem value
        subitem = J_NULL;
        if(text[*pos] == '\"')
        {
            (*pos)++; //skip "
            p = strchr(text + *pos, '\"');
            if(p == J_NULL)
            {
                goto end;
            }

            tmp_len = (jint32_t)(p - &text[*pos]);
            subvalue = (char *)(malloc(tmp_len + 1));
            memcpy(subvalue, text + *pos, tmp_len);
            subvalue[tmp_len] = '\0';

            *pos = *pos + tmp_len + 1; //skip text, "
            subitem = json_string_item_create(subname, subvalue);
        }
        else if(text[*pos] == '{')
        {
            subitem = _json_parse_item(subname, text, pos);
        }
        else if(text[*pos] == '[')
        {
            subitem = _json_parse_item(subname, text, pos);
        }
        else
        {
            p = text + *pos;
            while(1)
            {
                if(*p == '\0')
                {
                    goto end;
                }

                if(*p == ' ' || *p == '}' || *p == ']' || *p == ',')
                {
                    break;
                }
                p++;
            }

            tmp_len = (jint32_t)(p - &text[*pos]);
            subvalue = (char *)(malloc(tmp_len + 1));
            memcpy(subvalue, text + *pos, tmp_len);
            subvalue[tmp_len] = '\0';
            *pos = *pos + tmp_len; //skip text

            if(strcmp(subvalue, J_TRUE_STR) == 0)
            {
                subitem = json_bool_item_create(subname, J_TRUE);
            }
            else if(strcmp(subvalue, J_FALSE_STR) == 0)
            {
                subitem = json_bool_item_create(subname, J_FALSE);
            }
            else if(strcmp(subvalue, "null") == 0)
            {
                subitem = json_item_create(JSON_NULL, subname);
            }
            else
            {
                subitem = json_number_item_create(subname, subvalue);
            }
        }

        if(subname)
        {
            free(subname);
            subname = J_NULL;
        }

        if(subvalue)
        {
            free(subvalue);
            subvalue = J_NULL;
        }

        if(subitem)
        {
            json_item_add_subitem(item, subitem);
        }
        if(text[*pos] == '}' || text[*pos] == ']')
        {
            (*pos)++; // skip }\]
            break;
        }
    }

    return item;

end:

    if(subname)
    {
        free(subname);
    }

    if(subvalue)
    {
        free(subvalue);
    }
    json_item_destroy(item);

    //
    char log_buf[1024] = {0};
    memcpy(log_buf, text + *pos, 30);
    J_LOG("pos:%d format error, context:\n%s", *pos, log_buf);

    return J_NULL;
}

_xtag json_item_t *json_item_parse_from_text(const char *text)
{
    jint32_t n = 0;
    n += _skip_space(text);
    return _json_parse_item(J_NULL, text, &n);
}

_xtag jint32_t json_item_get_int32_value(json_item_t *item)
{
	if (!item)
	{
		return 0;
	}
	return (jint32_t *)item->value;
}

_xtag const char *json_item_get_string_value(json_item_t *item)
{
    if(!item)
    {
        return "";
    }
    return item->value;
}

_xtag json_item_t *json_item_get_subitem_by_name(json_item_t *item, const char *subitem_name)
{
    if(item->type != JSON_OBJECT)
    {
        return J_NULL;
    }

    json_item_t *first = item->value;
    while(first)
    {
        if(strcmp(first->name, subitem_name) == 0)
        {
            return first;
        }
        first = first->next;
    }
    return first;
}

_xtag jint32_t json_item_get_subitem_count(json_item_t *item)
{
    if(item->type != JSON_ARRAY
        && item->type != JSON_OBJECT)
    {
        return 0;
    }

    jint32_t n = 0;
    json_item_t *first = item->value;
    while(first)
    {
        n++;
        first = first->next;
    }
    return n;
}

_xtag json_item_t *json_item_get_subitem_by_index(json_item_t *item, jint32_t subindex)
{
    if(item->type != JSON_ARRAY
        && item->type != JSON_OBJECT)
    {
        return J_NULL;
    }

    jint32_t n = 0;
    json_item_t *first = item->value;
    while(first)
    {
        if(n == subindex)
        {
            return first;
        }
        first = first->next;
        n++;
    }
    return J_NULL;
}

#ifdef DEBUG_MEM

typedef struct mem_item_st
{
    void *mem;
    size_t size;
    struct mem_item_st *next;
}mem_item_t;

static mem_item_t *g_mem_item_list;

_xtag void *dbg_malloc(size_t size)
{
#undef malloc
    mem_item_t *item = malloc(sizeof(mem_item_t));
    item->mem = malloc(size);
    item->size = size;
    item->next = MNULL;

    mem_item_t *first = g_mem_item_list;
    if(first == MNULL)
    {
        g_mem_item_list = item;
    }
    else
    {
        while(first->next)
        {
            first = first->next;
        }
        first->next = item;
    }
    return item->mem;
}

_xtag void dbg_free(void *p)
{
#undef free
    free(p);

    mem_item_t *last = MNULL;
    mem_item_t *first = g_mem_item_list;
    while(first)
    {
        if(first->mem == p)
        {
            if(last == MNULL)
            {
                g_mem_item_list = MNULL;
            }
            else
            {
                last->next = first->next;
            }
            free(first);
            break;
        }

        last = first;
        first = first->next;
    }
}

#endif

_xtag void json_mem_log()
{
#ifdef DEBUG_MEM
    size_t n = 0;
    mem_item_t *first = g_mem_item_list;
    while(first)
    {
        n += first->size;
        first = first->next;
    }

    J_LOG("%zu", n);
#endif
}



