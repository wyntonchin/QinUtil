
package com.hisense.smarthome.sdk.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DeviceConfig {

    private static String DEVICE_SMARTPHONE = "007";

    private static String code = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    private static final String getProp(String key) {
        try {
            Method me = Class.forName("android.os.SystemProperties").getMethod("get", String.class);
            return (String) me.invoke(null, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 读取设备id, 需要权限 android.permission.READ_PHONE_STATE
     * 
     * @param featurecode
     * @param IMEI
     * @return 设备id
     */
    public static final String getPhoneDeviceId(String devicecode, String IMEI) {
        int length = 24;
        String devicecodeTemp;
        // 手机设备IMEI号长度不固定，标记为863
        devicecodeTemp = "86300b" + devicecode;
        // devicecode中空格用"-"替换
        devicecodeTemp = devicecodeTemp.trim().replace(' ', '-');
        if (devicecodeTemp.length() >= length) {
            devicecodeTemp = devicecodeTemp.substring(0, length);
        } else {
            devicecodeTemp = rightPadding(devicecodeTemp, length);
        }
        StringBuilder deviceId = new StringBuilder();
        deviceId.append(devicecodeTemp).append(IMEI);
        String result = deviceId.toString().toLowerCase();
        return result;
    }

    /**
     * 读取设备id, 需要权限 android.permission.READ_PHONE_STATE
     * 
     * @param context
     * @return 设备id
     */
    public static final String getPhoneDeviceId(Context context) {
        String prefix = getProp("ro.product.hitdeviceprefix");
        if (!TextUtils.isEmpty(prefix)) {// 如果要保证pad的统一这边先判断一下
            return getDeviceId(context);
        }
        String model = getProp("ro.hmct.internal.model");
        if (TextUtils.isEmpty(model)) {
            if (TextUtils.isEmpty(model)) {
                model = getProp("ro.internal.model");
                if (TextUtils.isEmpty(model)) {
                    model = getProp("ro.hmct.ota.product");
                    if (TextUtils.isEmpty(model)) {
                        model = getProp("ro.ota.product");
                        if (TextUtils.isEmpty(model)) {
                            model = getProp("ro.hmct.product.fullname");
                            if (TextUtils.isEmpty(model)) {
                                model = getProp("ro.product.fullname");
                                if (TextUtils.isEmpty(model)) {
                                    return getDeviceId(context);// 如果没有这个属性那么就不是海信新的智能手机类型，使用获取第三方设备的方法获取
                                }
                            }
                        }
                    }
                }
            }
        }
        String devicecode = getModel();
        String IMEI = getPhoneMeid(context);
        int length = 24;
        String devicecodeTemp;
        // 手机设备IMEI号长度不固定，标记为863
        devicecodeTemp = "86300b" + devicecode;
        // devicecode中空格用"-"替换
        devicecodeTemp = devicecodeTemp.trim().replace(' ', '-');
        if (devicecodeTemp.length() >= length) {
            devicecodeTemp = devicecodeTemp.substring(0, length);
        } else {
            devicecodeTemp = rightPadding(devicecodeTemp, length);
        }
        StringBuilder deviceId = new StringBuilder();
        deviceId.append(devicecodeTemp).append(IMEI);
        String result = deviceId.toString().toLowerCase();
        return result;
    }

    public static String getProduct() {
        String product = getPhoneInfo("ro.hmct.ota.product");
        if (product == null) {
            product = getPhoneInfo("ro.ota.product");
        }

        if (product == null) {
            product = android.os.Build.MODEL;
        }
        return product;
    }

    public static String getPhoneMeid(Context context) {
        TelephonyManager tmgr = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String Imei = getImei(context, tmgr);
        if (null == Imei || isIdInvalid(Imei)) {
            Imei = getMeid(context);
        }
        if (null == Imei || isIdInvalid(Imei)) {
            if (null != tmgr) {
                Imei = tmgr.getDeviceId();
            }
        }
        return Imei;
    }

    // android 5.0及以上调getImei接口，5.0以下读gsm.phone.imei属性
    private static String getImei(Context context, TelephonyManager tmgr) {
        Method getPhoneDevideId;
        String imei = null;
        if (getSysVersion() >= 5.0) {
            try {
                if (null != tmgr) {
                    getPhoneDevideId = tmgr.getClass().getMethod("getImei", int.class);
                    imei = (String) getPhoneDevideId.invoke(tmgr, 0);
                    printLog("getImei return=" + imei);
                }

            } catch (Exception e) {
                e.printStackTrace();
                printLog("Exception=" + e.toString());
            }
        } else {
            imei = getPhoneInfo("gsm.phone.imei");
        }
        return imei;
    }

    // 所有版本均取gsm.phone.meid属性
    private static String getMeid(Context context) {
        String meid = getPhoneInfo("gsm.phone.meid");
        printLog("getMeid return=" + meid);
        return meid;
    }

    // 获取版本号 如5.0
    private static float getSysVersion() {
        String version = android.os.Build.VERSION.RELEASE.substring(0, 3).toString();
        return Float.valueOf(version);
    }

    // 读属性
    public static String getPhoneInfo(String tag) {
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            String info = (String) (get.invoke(c, tag, null));
            printLog(tag + ":" + info);
            if (null != info && !info.isEmpty()) {
                return info;
            }
        } catch (Exception e) {
            printLog(e.getMessage());
            return null;
        }

        return null;
    }

    // 判断获取id是否全是0无效
    private static boolean isIdInvalid(String deviceId) {
        return deviceId.matches("[0]+");
    }

    private static final void printLog(String log) {
        SDKUtil.LogD("SDK", "[PhoneUtils] " + log);
    }

    /**
     * 读取设备id, 需要权限 android.permission.READ_PHONE_STATE
     * 
     * @param context
     * @return 设备id
     */
    public static final String getDeviceId(Context context) {
        String prefix = getProp("ro.product.hitdeviceprefix");
        String devicecode = null;
        if (TextUtils.isEmpty(prefix)) {
            String ngbdeviceId = getNGBDeviceId(context);
            if (!TextUtils.isEmpty(ngbdeviceId)) {
                return ngbdeviceId.toString().toLowerCase();
            }
            String channel = null;
            if("PPTV".equals(getProp("ro.product.manufacturer"))){
                channel = getProp("persist.sys.ChannelROM");//pptv渠道号
                if(TextUtils.isEmpty(channel)){
                    channel = getProp("ro.product.channel");//pptv渠道号老板
                }                
            }
            if(!TextUtils.isEmpty(channel)){                   
                prefix = new StringBuilder("863f0000").append("10").append(channel).append("00000000").toString();
            }
            if(!TextUtils.isEmpty(prefix)){
                devicecode = getDeviceCode(context);
            }else{
                channel = "00";
                /*
                 * try { channel = ChannelUtil.getChannelFromApk(context,
                 * ChannelUtil.CHANNEL_KEY); } catch (Exception e) {
                 * e.printStackTrace(); channel = "00"; } if
                 * (SDKUtil.isEmpty(channel)) { channel = "00"; }
                 */
                prefix = "862fff" + channel + "fffefff00000fffe"; // 默认""862ff00fffefff00000fffe"";
                // 第三方设备
                devicecode = getThirdDeviceCode(context);               
            }
        } else if (prefix.substring(3, 6).equals(DEVICE_SMARTPHONE)) {
            devicecode = getSerialNO(context);
        } else {
            devicecode = getDeviceCode(context);
        }

        int length = "2".equals(String.valueOf(prefix.charAt(2))) ? 32 : 8;

        devicecode = formatDeviceCode(devicecode, length);
        StringBuilder deviceId = new StringBuilder();
        deviceId.append(prefix).append(devicecode);
        String result = deviceId.toString().toLowerCase();
        return result;
    }

    public static final String getThirdTVDeviceId(Context context) {
        String prefix = "86200300fffefff00000fffe";
        String devicecode = getThirdDeviceCode(context);
        int length = "2".equals(String.valueOf(prefix.charAt(2))) ? 32 : 8;

        devicecode = formatDeviceCode(devicecode, length);
        StringBuilder deviceId = new StringBuilder();
        deviceId.append(prefix).append(devicecode);
        String result = deviceId.toString().toLowerCase();
        return result;
    }

    public static String getNGBDeviceId(Context mContext) {
        String number = "";
        Uri uri = Uri.parse("content://tv.hitv.android.oam/oamdata");
        final String[] selectionArgs = {
                "device_id"
        };
        final String[] projection = new String[] {
                "OamValues"
        };
        final String selection = "OamName=?";
        Cursor cursor = mContext.getContentResolver().query(uri, projection,
                selection, selectionArgs, null);

        if (cursor == null) {
            return number;
        }
        if (cursor.moveToNext()) {
            String cn = cursor.getString(cursor.getColumnIndex("OamValues"));
            if (!cn.equals("-1")) {
                number = cn;
            }
        }
        cursor.close();
        return number;
    }

    /**
     * 获取NGB已经IPtv设备id和sn的统一接口
     * 
     * @param mContext
     * @return
     */
    public static String[] getIPTVDeviceIdAndSN(Context mContext) {

        String[] info = null;
        if (mContext == null) {
            return null;
        }
        Uri uri = Uri.parse("content://com.hisense.hsmware.signon/deviceinfo/");
        Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            info = new String[2];

            StringBuilder deviceId = new StringBuilder();
            // _ID：无意义, 最多返回一行数据, _ID固定为0.
            String operatorPlatform = cursor.getString(cursor.getColumnIndex("OperatorPlatform"));// 运营商平台
            String operator = cursor.getString(cursor.getColumnIndex("Operator"));
            // 运营商
            /*
             * String featureCode =
             * cursor.getString(cursor.getColumnIndex("FeatureCode"));//设备类型
             * String featureCodeLenth =
             * cursor.getString(cursor.getColumnIndex("FeatureCodeLenth"));//
             * 设备类型长度 String deviceID =
             * cursor.getString(cursor.getColumnIndex("DeviceID"));//设备ID
             */ String servicenumber = cursor.getString(cursor.getColumnIndex("Servicenumber"));// 客户号
            if (SDKUtil.isEmpty(operatorPlatform) || SDKUtil.isEmpty(operator)
                    || SDKUtil.isEmpty(servicenumber)) {
                return null;
            }
            deviceId.append("863f0000").append(operatorPlatform).append(operator).append("00000000")
                    .append(getWiredMacAddress(mContext));
            info[0] = deviceId.toString().toLowerCase();
            info[1] = operatorPlatform + operator + servicenumber;
            info[1] = info[1].toLowerCase();
        }
        return info;
    }

    private static String getThirdDeviceCode(Context ctx) {
        String code = null;
        code = getSerialNO(ctx);
        if (code == null || code.length() == 0) {
            String mac = getMacAddress(ctx);
            if (mac.length() == 0) {
                code = "00000000000000000000000000000000";
            } else {
                code = mac.replace(":", "").toLowerCase();
            }
        }
        SDKUtil.LogD(Constants.LOGTAG, "getThirdDeviceCode:= " + code);
        return formatDeviceCode(code, 32);
    }

    private static String getSerialNO(Context ctx) {
        TelephonyManager tm = null;
        try {
            tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (tm != null) {
            return tm.getDeviceId();
        }
        return null;
    }

    private static String getDeviceCode(Context context) {
        String deviceCode = "";
        String seq = getProp("ro.product.hitdeviceseq"); // ITV, 冰箱设备要求写入
        if (TextUtils.isEmpty(seq)) {
            seq = getProp("sys.product.hitdeviceseq");
        }
        SDKUtil.LogD(Constants.LOGTAG, "getDeviceCode -----seq is : " + seq);
        if (TextUtils.isEmpty(seq)) { // 这里没有实现已经废弃的读取ro.product.hitdeviceid的方法
            String mac = getMacAddress(context);
            if (!TextUtils.isEmpty(mac)) {
                mac = mac.replace(":", "").toLowerCase();
                deviceCode = formatDeviceCode(mac, 8);
            } else {
                deviceCode = "00000000";
            }
        } else {
            deviceCode = getDeviceCodeBySeq(seq);
        }
        SDKUtil.LogD(Constants.LOGTAG, "getDeviceCode:= " + deviceCode);
        return deviceCode;
    }

    /**
     * 获取有线mac 地址获取不到有线获取无线
     * 
     * @param context
     * @return
     */
    public static String getMacAddress(Context context) {
        try {
            String deviceCode = loadFileAsString("/sys/class/net/eth0/address").substring(0, 17);
            if (deviceCode == null) {
                deviceCode = "";
            }
            return deviceCode;
        } catch (IOException e) {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String devicecode = info.getMacAddress();
            if (devicecode == null) {
                devicecode = "";
            }
            return devicecode;
        }
    }

    /**
     * 获取有线的mac地址
     * 
     * @param context
     * @return
     */
    public static String getWiredMacAddress(Context context) {
        try {
            String deviceCode = loadFileAsString("/sys/class/net/eth0/address").substring(0, 17);
            if (deviceCode == null) {
                deviceCode = "";
            }
            if (!TextUtils.isEmpty(deviceCode)) {
                deviceCode = deviceCode.replace(":", "").toLowerCase();
            }
            return deviceCode;
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * 获取wifi的mac地址
     * 
     * @param context
     * @return
     */
    public static String getWifiMacAddress(Context context) {
        try {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String devicecode = info.getMacAddress();
            if (devicecode == null) {
                devicecode = "";
            }
            return devicecode;
        } catch (Exception e) {
            return "";
        }
    }

    private static String loadFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }

    private static String formatDeviceCode(String devicecode, int length) {
        String deviceCode = devicecode;
        if (deviceCode.length() >= length) {
            deviceCode = deviceCode.substring(deviceCode.length() - length);
        } else {
            deviceCode = leftPadding(devicecode, length);
        }
        return deviceCode;
    }

    private static String leftPadding(String src, int length) {
        int strLen = length - src.length();
        StringBuffer sb = new StringBuffer();
        while (strLen > 0) {
            sb.append("0");// 左补0
            strLen--;
        }
        sb.append(src);
        return sb.toString();
    }

    private static String rightPadding(String src, int length) {
        int strLen = length - src.length();
        StringBuffer sb = new StringBuffer();
        sb.append(src);
        while (strLen > 0) {
            sb.append("0");// 右补0
            strLen--;
        }
        return sb.toString();
    }

    private static String getDeviceCodeBySeq(String seq) {
        seq = formatDeviceCode(seq, 9);
        // System.out.println("getDeviceCodeBySeq::seq=" + seq);
        // 序列号 = ((年-1)*372 + (月-1)*31 +(日-1))* 34^2*10^4
        // + （（线体码1）*34 + （线体码2））*10^4
        // +（ 顺序码1） *10^3 + （ 顺序码2） *10^2 + （ 顺序码3） *10 + （ 顺序码4）
        int year = code.indexOf(seq.charAt(0));
        int month = code.indexOf(seq.charAt(1));
        int day = code.indexOf(seq.charAt(2));
        int s1 = code.indexOf(seq.charAt(3));
        int s2 = code.indexOf(seq.charAt(4));
        int o1 = code.indexOf(seq.charAt(5));
        int o2 = code.indexOf(seq.charAt(6));
        int o3 = code.indexOf(seq.charAt(7));
        int o4 = code.indexOf(seq.charAt(8));

        long value1 = ((year - 1) * 372 + (month - 1) * 31 + (day - 1)) * 34 * 34 * 10000L;
        long value2 = (s1 * 34 + s2) * 10000L;
        long value3 = (o1 * 1000 + o2 * 100 + o3 * 10 + o4);

        String deviceCode = Long.toHexString(value1 + value2 + value3);
        return deviceCode;
    }

    /**
     * 获取域名
     * 
     * @return 域名
     */
    public static final String getDomainName() {
        String domainName = "";
        domainName = getProp("ro.domain.name");
        if (TextUtils.isEmpty(domainName)) {
            domainName = getProp("sys.domain.name");
        }
        if (TextUtils.isEmpty(domainName)) {
            domainName = Constants.DEFAULT_DOMAINNAME;
        }
        return domainName;
    }

    public static String getModel() {
        String model = getProp("ro.hmct.internal.model");
        if (TextUtils.isEmpty(model)) {
            if (TextUtils.isEmpty(model)) {
                model = getProp("ro.internal.model");
                if (TextUtils.isEmpty(model)) {
                    model = getProp("ro.hmct.ota.product");
                    if (TextUtils.isEmpty(model)) {
                        model = getProp("ro.ota.product");
                        if (TextUtils.isEmpty(model)) {
                            model = getProp("ro.hmct.product.fullname");
                            if (TextUtils.isEmpty(model)) {
                                model = getProp("ro.product.fullname");
                                if (TextUtils.isEmpty(model)) {
                                    model = android.os.Build.MODEL;
                                }
                            }
                        }
                    }
                }
            }
        }
        return model;
    }

    /**
     * 获取电视防串货号
     * 
     * @param context
     * @return
     */
    public static String getBcCode(Context context) {
        String bcode = "";
        Cursor cursor = context.getContentResolver().query(
                Uri.parse("content://com.hisense.hitv.hicloud.account/bcCode/"), null, null, null,
                null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                bcode = cursor.getString(cursor.getColumnIndex("BcCode"));
                bcode = SDKUtil.isRegularBcode(bcode) ? bcode : "";
            }
            cursor.close();
        }
        return bcode;
    }

    /**
     * 获取电视唯一码
     * 
     * @param context
     * @return
     */
    public static String getSerialTVNo(Context context) {
        String md5Value = null;
        String mac = getWiredMacAddress(context);

        String serialNo = getDeviceId(context) + "|" + mac + "|" + getBcCode(context);
        md5Value = Md5.md5(serialNo);

        return md5Value;

    }

    /**
     * 获取手机唯一码
     * 
     * @param context
     * @return
     */
    public static String getSerialPhoneNo(Context context) {
        String md5Value = null;
        String serialNo = getPhoneDeviceId(context) + "||";
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5Value = getString(md5.digest(serialNo.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5Value;
    }

    public static String getString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(b[i]);
        }
        return sb.toString();
    }

    /**
     * 获取天猫魔盒的设备ID 1001 1001
     * 
     * @return
     */
    public static String getTmallBoxDeviceID(Context context) {
        StringBuilder deviceId = new StringBuilder();
        deviceId.append("863f0000").append("1001").append("1001").append("00000000")
                .append(getWiredMacAddress(context));
        return deviceId.toString().toLowerCase();
    }

    /**
     * 获取网宿的设备ID 1003 1003
     * 
     * @return
     */
    public static String getChinaNetCenterDeviceID(Context context) {
        StringBuilder deviceId = new StringBuilder();
        deviceId.append("863f0000").append("1003").append("1003").append("00000000")
                .append(getWiredMacAddress(context));
        return deviceId.toString().toLowerCase();
    }

    /**
     * 获取河南电信盒子的设备1002 1002
     * 
     * @return
     */
    public static String getHeNanTelecomDeviceID(Context context) {
        StringBuilder deviceId = new StringBuilder();
        deviceId.append("863f0000").append("1002").append("1002").append("00000000")
                .append(getWiredMacAddress(context));
        return deviceId.toString().toLowerCase();
    }

    /**
     * 获取枣阳电信盒子的设备1006 1006
     * 
     * @return
     */
    public static String getZaoYangTelecomDeviceID(Context context) {
        StringBuilder deviceId = new StringBuilder();
        deviceId.append("863f0000").append("1006").append("1006").append("00000000")
                .append(getWiredMacAddress(context));
        return deviceId.toString().toLowerCase();
    }

    /**
     * 获取电信爱游戏的设备1007 1007
     * 
     * @return
     */
    public static String getChinaNetPlayDeviceID(Context context) {
        StringBuilder deviceId = new StringBuilder();
        deviceId.append("863f0000").append("1007").append("1007").append("00000000")
                .append(getWiredMacAddress(context));
        return deviceId.toString().toLowerCase();
    }

    /**
     * 获取CVTE的设备ID 1009 1009
     * 
     * @param context
     * @return
     */
    public static String getCVTEDeviceID(Context context) {
        StringBuilder deviceId = new StringBuilder();
        deviceId.append("863f0000").append("1009").append("1009").append("00000000")
                .append(getWiredMacAddress(context));
        return deviceId.toString().toLowerCase();
    }
    /**
     * 获取江苏移动的设备ID 1012 1012
     * 
     * @param context
     * @return
     */
    public static String getJSCMCCDeviceID(Context context) {
        StringBuilder deviceId = new StringBuilder();
        deviceId.append("863f0000").append("1012").append("1012").append("00000000")
                .append(getWiredMacAddress(context));
        return deviceId.toString().toLowerCase();
    }
}
