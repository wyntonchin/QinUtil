//
//  json.h
//  json
//
//  Created by vito on 15/11/23.
//  Copyright ? 2015Äê vito. All rights reserved.
//

#ifndef json_h
#define json_h
//#include "CTSmart2Types.h"

typedef unsigned char juint8_t;
typedef signed char jint8_t;
typedef unsigned short juint16_t;
typedef signed short jint16_t;
typedef unsigned long juint32_t;
typedef signed long jint32_t;
typedef unsigned long long juint64_t;
typedef signed long long jint64_t;
typedef unsigned char jbool_t;
typedef unsigned char jbyte_t;

#define J_TRUE 1
#define J_FALSE 0
#define J_NULL ((void *)0)

#define J_TRUE_STR "mtrue"
#define J_FALSE_STR "mfalse"


typedef enum
{
    JSON_OBJECT,
    JSON_ARRAY,
    JSON_NUMBER,
    JSON_STRING,
    JSON_BOOL,
    JSON_NULL,
}json_value_type_t;

typedef struct json_item_st
{
    json_value_type_t type;
    jbool_t registed_name;
    char *name;
    void *value;
    struct json_item_st *next;
}json_item_t;

void json_item_regist_value_name(const char *value);

json_item_t *json_item_create(json_value_type_t type, const char *name);
void json_item_destroy(json_item_t *value);

json_item_t *json_string_item_create(const char *name, const char *string);
json_item_t *json_number_item_create(const char *name, const char *number);
json_item_t *json_int32_item_create(const char *name, jint32_t value);
json_item_t *json_bool_item_create(const char *name, jbool_t value);



void json_item_add_subitem(json_item_t *object, json_item_t *subitem);

jbool_t json_object_item_has_subitem(json_item_t *item);
#define json_array_item_has_subitem(item) json_object_item_has_subitem(item)

#define json_object_item_add_subitem(item, subitem) json_item_add_subitem(item, subitem)
#define json_array_item_add_subitem(item, subitem) json_item_add_subitem(item, subitem)

void json_string_item_set_value(json_item_t *item, const char *value);

#define json_number_item_set_value(item, value) json_string_item_set_value(item, value)
#define json_bool_item_set_value(item, value) json_string_item_set_value(item, value ? J_TRUE_STR : J_FALSE_STR)

typedef void (*json_echo_func_t)(char *text_buf, const char *byte, jbool_t end);

jint32_t json_item_eval_parse_text_len(json_item_t *item);
void json_item_parse_to_echo(json_item_t *item, json_echo_func_t echo_func, char *text_buf);
void json_item_parse_to_text(json_item_t *item, char *text_buf);


json_item_t *json_item_parse_from_text(const char *text);

jint32_t json_item_get_int32_value(json_item_t *item);
jbool_t json_item_get_bool_value(json_item_t *item);
const char *json_item_get_string_value(json_item_t *item);
jint32_t json_item_get_subitem_count(json_item_t *item);

json_item_t *json_item_get_subitem_by_name(json_item_t *item, const char *subitem_name);
json_item_t *json_item_get_subitem_by_index(json_item_t *item, jint32_t subindex);


//ASSIST MACRO

#define J_CREATE(type, name) json_item_create(type, name)
#define J_CREATE_N(name, value) json_number_item_create(name, value)
#define J_CREATE_B(name, value) json_bool_item_create(name, value)
#define J_CREATE_I(name, value) json_int32_item_create(name, value)
#define J_CREATE_S(name, value) json_string_item_create(name, value)

#define J_SUB_VALUE_BY_NAME_I(item, name) \
json_item_get_int32_value(json_item_get_subitem_by_name(item, name))

#define J_SUB_VALUE_BY_NAME_S(item, name) \
json_item_get_string_value(json_item_get_subitem_by_name(item, name))

#define J_SUB_VALUE_BY_NAME_B(item, name) \
json_item_get_bool_value(json_item_get_subitem_by_name(item, name))

#endif /* json_h */
