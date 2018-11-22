/*****************************************************************/
/*洗衣机协议库接口返回值错误码说明，滚筒协议库、波轮协议库错误码统一定义到该文件中*/
/*****************************************************************/

/*A:滚筒(tft)*/
/*B:波轮*/


/*A:int wsh_get_cmd_control(xx)*/
/*B:int wsh_cmd_adj_param(xx)*/
/*C:int wsh_cmd_adj_prog(xx)*/

/*以上接口返回值统一定义为下面：*/
/*AA--2: 在童锁状态下操作按键但是无效*/
/*AB--3: 在预约设定操作无效 返回 */
/*AB--4: 选重、轻污，在开关没打开时设定操作无效 返回*/
/*AB--5: 加快时，选预洗和强力作无效 返回 */
/*AB--6: 启动后调整出设置参数和电源之外的其他参数 */


/*AB--N:xxxxxx*/
/*BA--N+1:XXXX*/
