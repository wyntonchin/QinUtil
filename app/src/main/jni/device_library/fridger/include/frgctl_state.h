#ifndef _FRGCTL_STATE
#define _FRGCTL_STATE

#ifdef  __cplusplus
extern "C"
{
#endif

	typedef struct _Device_Config
	{
		uchar fuzzy_mode;//����ģʽ 0:�� 1:��
		uchar save_mode;//����ģʽ0:�� 1:��
		uchar show_mode;//��ʾģʽ0:�� 1:��
		uchar sf_mode;//�ٶ�ģʽ0:�� 1:��
		uchar sr_mode;//����ģʽ0:�� 1:��
		uchar holiday_mode;//����ģʽ0:�� 1:��
		uchar debacilli_mode;//����ģʽ0:�� 1:��

		uchar  refrigerator_room;//������� ��0:�� 1:��
		uchar  freeze_room;//�����䶳��0:�� 1:��
		uchar  variation_room;//���������0:�� 1:��
		uchar  soft_freeze_room;//��������0:�� 1:��
		uchar  auto_ice_room;//�����Զ��Ʊ���0:�� 1:��

		uchar  refrigerator_room_open;//������� ��0:�ر�1:��
		uchar  freeze_room_open;//�����䶳��0:�ر�1:��
		uchar  variation_room_open;//���������0:�ر�1:��

		char  refrigerator_min_temperature;//������� ����Сֵ
		char  refrigerator_max_temperature;//������� �����ֵ
		char  variation_min_temperature;//�����������Сֵ
		char  variation_max_temperature;//������������ֵ
		char  freeze_min_temperature;//�����䶳����Сֵ
		char  freeze_max_temperature;//�����䶳�����ֵ

	} Device_Config;

	typedef struct _WGCommonStatus {
		//Byte 1
		uchar WiFiController : 1; //WIFI ���ư�״̬
		uchar MsgStatus : 1; //WIFI ���ư��յ��յ����ڿ��ư���Ϣ�������	
		uchar LinkStatus : 2; //WIFI ���ư���·����������״̬
		uchar LedStat : 1; // LED ����״̬
		uchar PhoneCTL : 1;		//�Ƿ����ֻ�����
		uchar IsWiFiReg : 1;    //WIFI ���ư�ע�����
		uchar IsUserCanceled : 1; //�û�ȡ�����

		//Byte 2
		uchar nullbit : 1;
		uchar LedReq : 1;  //�ҵ���ư����� WIFI ���ư���� LED �ƿ���
		uchar ConfigReq : 1;  //�ҵ���ư����� WIFI ���ư��������ģʽ
		uchar ConfigSelectorReq : 1; //�ҵ���ư����� Wi-Fi ���ư��������ģʽѡ��
		uchar ResetReq : 1;	//�ҵ����� Wi-Fi ģ�鸴λ
		uchar PowerOnTime : 1;	//�ҵ���ư��֪ Wi-Fi ���ư壬�ҵ���ϵ�ʱ��
		uchar SmartCtlMode : 1;	//�ҵ��豸���� Wi-Fi ��ҵ��豸�Ƿ��˳����ܿ���ģʽ,0:on Smart Ctl Mode;1:Quit
		uchar StatusIsChanged : 1; //�ҵ��豸�趨ֵ���趨״̬�����˸ı�,0��δ�����ı䣻1�������˸ı䡣
	}WGCommonStatus;

	typedef struct _Frg_Status
	{

		char  refrigerator_temperature;//��������趨�¶�1
		char  freeze_temperature;//�����䶳�趨�¶�ֵ2
		char  variation_temperature;//��������趨�¶�ֵ3

		uchar  work_mode1;//����ģʽ1       4
		uchar  work_mode2;//����ģʽ2       5

		uchar  mainboard_version;//���ذ�汾��6

		char  refrigerator_real_temperature;//���ʵ���¶�ֵ   7
		char  freeze_real_temperature;//�䶳ʵ���¶�ֵ  8
		char  variation_real_temperature;//����ʵ���¶�ֵ  9
		char  environment_real_temperature;//�����¶�ʵ��ֵ   10

		char  freeze_sensor_real_temperature;//�䶳������������ʵ���¶�ֵ   11
		char  refrigerator_sensor_real_temperature;//���������������ʵ���¶�ֵ   12
		char  variation_sensor_real_temperature;//����������������ʵ���¶�ֵ   13

		uchar  sensor_failure_status;//����������״̬   14
		uchar  control_failure_status;//���ƹ���״̬    15

		uchar  running_status;//����״̬   16
		uchar  mainboard_type;//���ذ�����ֵ  17
		uchar  displayboard_key_setting;//��ʾ�尴��״̬����   18
		uchar  displayboard_type;//��Ʒ��ʾ������ֵ   19
		uchar  compressor_frequency;//ѹ��������Ƶ��   20

		uchar  refrigerator_poweron_ad;//��ؿ�����ADֵ   21
		uchar  refrigerator_poweroff_ad;//���ͣ����ADֵ    22
		uchar  freeze_poweron_ad;//�䶳������ADֵ   23
		uchar  freeze_poweroff_ad;//�䶳ͣ����ADֵ   24
		uchar  variation_poweron_ad;//���¿�����ADֵ  25
		uchar  variation_poweroff_ad;//����ͣ����ADֵ   26
		uchar  sensor_failure_status2;//����������״̬ 27

		uchar  temperature_room_judge;//�����ж�28
		uchar  wifi_setting;//wifiģ���������   29
		uchar  displayboard_version;//��ʾ��汾����   30
		uchar  environment_humidity;//����ʪ��   31
		uchar  model_type;//ģʽ����    32

		char  refrigerator_min_temperature;//�������趨�¶�33
		char  refrigerator_max_temperature;//�������趨�¶�34
		char  variation_min_temperature;//��������趨�¶�35
		char  variation_max_temperature;//��������趨�¶�36
		char  freeze_min_temperature;//�䶳����趨�¶�37
		char  freeze_max_temperature;//�䶳����趨�¶�38

		uchar  wifi_next_sendtime;//WiFi���´η��Ͳ�ѯָ���ʱ����39
		uchar  open_the_door_alarm;//���ű���40
		uchar  displayboard_brand;//��ʾ��Ʒ������41

		uchar  refrigerator_door_open_time;//��ؿ���ʱ��42
		uchar  variation_door_open_time;//���¿���ʱ��43
		uchar  freeze_door_open_time;//�䶳����ʱ��44

		uchar  wifi_handshake_fault_flag;//wifiģ�����ֹ��ϱ�־
		uchar  reserve46;//����46
		uchar  reserve47;//����47
		uchar  reserve48;//����48
		uchar  reserve49;//����49
		uchar  reserve50;//����50
		uchar  reserve51;//����51
		uchar  reserve52;//����52
		uchar  reserve53;//����53
		uchar  reserve54;//����54
		uchar  reserve55;//����55
		uchar  reserve56;//����56
		uchar  reserve57;//����57
		uchar  reserve58;//����58
		uchar  reserve59;//����59
		uchar  reserve60;//����60

		uchar  net_status;//����״̬61
		uchar  factory_check;//�볧���62
		uchar  fridge_reset;//����reset  63

		uchar  wifi_status1;//wifiģ��״̬λ1  64
		uchar  wifi_status2;//wifiģ��״̬λ2  65

		uchar  year_high_date;//��ǰ����ʱ�䣺�꣨����λ��66
		uchar  year_low_date;//��ǰ����ʱ�䣺�꣨����λ��67
		uchar  month_date;//��ǰ����ʱ�䣺��68
		uchar  day_date;//��ǰ����ʱ�䣺��69
		uchar  hour_date;//��ǰ����ʱ�䣺ʱ70
		uchar  minute_date;//��ǰ����ʱ�䣺��71

		uchar  hisense_cloud;//������72

		//��չ

		uchar fuzzy_mode;//����ģʽ---- 0:�ر�1:��73
		uchar save_mode;//����ģʽ---- 0:�ر�1:��74
		uchar show_mode;//��ʾģʽ---- 0:�ر�1:��75
		uchar sf_mode;//�ٶ�ģʽ---- 0:�ر�1:��76
		uchar sr_mode;//����ģʽ---- 0:�ر�1:��77
		uchar holiday_mode;//����ģʽ---- 0:�ر�1:��78
		uchar debacilli_mode;//����ģʽ---- 0:�ر�1:��79

	} Frg_Status;

#ifdef __cplusplus
}
#endif

#endif
