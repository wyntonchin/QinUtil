package com.hisense.smarthome.sdk.util;

import android.util.Log;

import com.hisense.smarthome.sdk.annotation.ComplexDescription;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SDKUtil {
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static String toUTF_8(String str) {
        if (isEmpty(str)) {
            return "";
        }
        String result = "";
        try {
            byte[] bs = str.getBytes();
            result = new String(bs, Constants.ENCODE);
        } catch (Exception e) {
            Log.e("SDKUtil", "UnsupportedEncoding!!");
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isRegularBcode(String bcode) {
        if (SDKUtil.isEmpty(bcode)) {
            return false;
        }
        try {
            if (URLEncoder.encode(bcode, "UTF-8").length() == bcode.length()) {
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String byte2HEX(byte ib) {
        char[] Digit = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }

    public static String byte2string(byte[] bt) {
        String digestHexStr = "";
        for (int i = 0; i < bt.length; i++) {
            digestHexStr += byte2HEX(bt[i]);
        }

        return digestHexStr;
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }

    public static String getLocaleLanguage() {
        String languageId = Constants.LANGUAGE_CHINESE;
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String country = locale.getCountry();
        String info = language + "_" + country;
        if (info.equals(Locale.SIMPLIFIED_CHINESE.toString())) {
            languageId = Constants.LANGUAGE_CHINESE;
        } else if (info.equals(Locale.TRADITIONAL_CHINESE.toString())) {
            languageId = Constants.LANGUAGE_CHINESE_T;
        } else if (language.equalsIgnoreCase(Locale.ENGLISH.toString())) {
            languageId = Constants.LANGUAGE_ENGLISH;
        } else if (language.equalsIgnoreCase(Locale.FRENCH.toString())) {
            languageId = Constants.LANGUAGE_FRENCH;
        } else if (language.equalsIgnoreCase("es")) {
            languageId = Constants.LANGUAGE_SPANISH;
        }
        return languageId;
    }

    /**
     * 加密数据
     *
     * @param encryptType 0：简单加密，CSS用， 1:复杂加密，AAA用
     * @param data        ： 要加密的数据
     * @param key         ：加密使用的密钥
     * @param params      :其他参数
     * @return 加密后的数据
     */
    public static String encryptData(int encryptType, String data, String key, String... params) {
        String s = "";
        DESUtil desUtil = new DESUtil();
        switch (encryptType) {
            case 0:
                byte[] pwdb = desUtil.tripleDes(data, key);
                for (byte b : pwdb) {
                    s += DESUtil.byteHEX(b);
                }
                break;
            case 1:
                byte[] pwdbyte = new byte[16];
                byte[] li = new byte[36];
                byte[] ln = string2bytes(params[0], 32);
                System.arraycopy(ln, 0, li, 0, 32);
                byte[] ipb = int2bytes(Integer.parseInt(params[1]));
                System.arraycopy(ipb, 0, li, 32, 4);
                byte[] pwd = new byte[64];
                byte[] cuP = data.getBytes();
                System.arraycopy(cuP, 0, pwd, 0, cuP.length);

                pwdbyte = desUtil.tripleDes(Md5.digest(li), Md5.digest(pwd));
                for (byte b : pwdbyte) {
                    s += DESUtil.byteHEX(b);
                }
                break;
        }
        return s;
    }

    /**
     * string转byte
     *
     * @param str
     * @param length
     * @return byte数组
     */
    public static byte[] string2bytes(String str, int length) {
        byte[] bytes = new byte[length];
        if (str == null) {
            str = "";
        }
        System.arraycopy(str.getBytes(), 0, bytes, 0, str.getBytes().length);
        return bytes;
    }

    public static byte uniteBytes(String src0, String src1) {
        byte b0 = Byte.decode("0x" + src0).byteValue();
        b0 = (byte) (b0 << 4);
        byte b1 = Byte.decode("0x" + src1).byteValue();
        byte ret = (byte) (b0 | b1);
        return ret;
    }

    /**
     * int转byte[4]
     *
     * @param num
     * @return byte数组
     */
    public static byte[] int2bytes(int num) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (num >>> (24 - i * 8));
        }
        return b;
    }

    /**
     * 从XML文本中获取某节点名称的元素的值
     *
     * @param xml
     * @param elementName
     * @return 正确数据
     */
    public static String getElementValueByName(String xml, String elementName) {

        /*
         * List<String > list=getElementsByName(xml,elementName);
         * if(list.size()>0){ return getElementValue(list.get(0)); }else{ return
         * ""; }
         */
        if (SDKUtil.isEmpty(xml)) {
            return "";
        }
        String s = null;
        String matchString = "(<" + elementName + ">)([^>]*)<";
        Pattern pat = Pattern.compile(matchString);
        Matcher m = pat.matcher(xml);
        while (m.find()) {
            s = m.group(2);
        }
        return s;
    }

    /**
     * 从XML文本中获取所有某节点名称的元素
     *
     * @param xml
     * @param elementName
     * @return 正确数据
     */
    public static List<String> getElementsByName(String xml, String elementName) {
        Pattern p = Pattern
                .compile("<" + elementName + "[^>]*?((>.*?</" + elementName + ">)|(/>))");
        Matcher m = p.matcher(xml);
        ArrayList<String> list = new ArrayList<String>();
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }

    /**
     * 获得一个XML节点元素的值
     *
     * @param elementString
     * @return 正确数据
     */
    public static String getElementValue(String elementString) {
        Pattern p = Pattern.compile(">([^<>]*)<");
        Matcher m = p.matcher(elementString);
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }

    public static String getStackTrace(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        } catch (Exception e1) {
            e1.printStackTrace();
            return "";
        }
    }

    public static void LogD(String tag, String log) {

        //final int invokeDepth = 1;
                if (Log.isLoggable(tag, Log.DEBUG)) {
            /*try {
                StackTraceElement[] stacks = new Throwable().getStackTrace();
                String className = stacks[invokeDepth].getClassName();
                className = className.substring(className.lastIndexOf(".") + 1);
                String methodName = stacks[invokeDepth].getMethodName();
                int lineNumber = stacks[invokeDepth].getLineNumber();
                StringBuffer sb = new StringBuffer();
                sb.append(className).append(" ").append(Constants.DEFAULTAPIVERSION).append(" ")
                        .append(methodName).append(" ").append(lineNumber).append(" : ")
                        .append(log);
                Log.d(tag, sb.toString());
            } catch (Exception e) {
                Log.d(tag, log);
                e.printStackTrace();
            }*/
        Log.d(tag, log);
                }

    }

    public static String getTime(String dateString) {
        TimeZone zone = TimeZone.getDefault();
        // Log.d("SDK", "TimeZone:######" + zone);
        TimeZone gmt = TimeZone.getTimeZone("GMT");
        // Log.d("SDK", "TimeZone-GMT:######" + gmt);
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        inputFormat.setTimeZone(gmt);
        Date date = null;
        try {
            date = inputFormat.parse(dateString);
        } catch (ParseException e) {
            LogD("SDKUtil", getStackTrace(e));
        }

        if (date != null) {
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            outputFormat.setTimeZone(zone);
            return outputFormat.format(date);
        } else {
            return "";
        }
    }

    /**
     * 将List中的元素组装成字符串
     *
     * @param list      数据列表
     * @param delimiter 间隔符
     * @return 以间隔符隔开的数据字符串
     */
    public static String join(List<String> list, String delimiter) {
        if (list == null || list.size() == 0 || delimiter == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (String string : list) {
            sb.append(string).append(delimiter);
        }
        sb.delete(sb.length() - delimiter.length(), sb.length());
        return sb.toString();
    }

    /**
     * 将对象转成XML格式
     *
     * @param list 保存有对象数据的了列表
     * @return XML格式的数据
     */
    public static String toXMLString(List<? extends Object> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        String tag = "list";

        try {
            Class<? extends Object> obj = list.get(0).getClass();
            boolean flag = obj.isAnnotationPresent(ComplexDescription.class);
            if (flag) {
                ComplexDescription complexDescription = (ComplexDescription) obj
                        .getAnnotation(ComplexDescription.class);
                tag = complexDescription.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("<").append(tag).append(">");
        for (Object x : list) {
            sb.append(x.toString());
        }
        sb.append("</").append(tag).append(">");
        return sb.toString();

    }

    /**
     * 将对象转成JSON格式,不支持复合数据类型的属性
     *
     * @param list                 保存有对象数据的列表
     * @param fields，JSON中关注的对象的属性
     * @return JSON格式的数据
     */
    public static String toJSONString(List<? extends Object> list, String[] fields) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        String tag = "list";
        /**
         * 获取Bean的List属性描述
         */
        try {
            Class<? extends Object> obj = list.get(0).getClass();
            boolean cFlag = obj.isAnnotationPresent(ComplexDescription.class);
            if (cFlag) {
                ComplexDescription complexDescription = (ComplexDescription) obj
                        .getAnnotation(ComplexDescription.class);
                tag = complexDescription.value();
            }
            sb.append("{\"").append(tag).append("\":[");
            /**
             * 获取Bean中的属性描述及值
             */
            if (fields != null && fields.length > 0) {
                String element = "element";
                for (int i = 0; i < list.size(); i++) {
                    Object bean = list.get(i);
                    sb.append("{");
                    for (int j = 0; j < fields.length; j++) {
                        String fieldName = fields[j];
                        element = fieldName;
                        Field field = bean.getClass().getDeclaredField(fieldName);
                        boolean flag = field.isAnnotationPresent(ComplexDescription.class);
                        if (flag) {
                            ComplexDescription complexDescription = (ComplexDescription) field
                                    .getAnnotation(ComplexDescription.class);
                            element = complexDescription.value();
                        }
                        Method fieldGetMethod = bean.getClass()
                                .getMethod(genGetMethodName(fieldName), new Class[]{});
                        Object fieldValue = fieldGetMethod.invoke(bean, new Object[]{});
                        sb.append("\"" + element + "\":\"").append(String.valueOf(fieldValue))
                                .append("\"");
                        if ((j + 1) < fields.length) {
                            sb.append(",");
                        }
                    }
                    sb.append("}");
                    if ((i + 1) < list.size()) {
                        sb.append(",");
                    }
                }
            }
            sb.append("]}");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

    /**
     * 生成属性的get方法名
     *
     * @param fieldName 属性
     * @return 属性的get方法名
     */
    private static String genGetMethodName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_') {
            startIndex = 1;
        }
        return "get" + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     * 将InputStream转换成某种字符编码的String
     *
     * @param in
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String InputStreamTOString(InputStream in, String encoding) throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        while ((count = in.read(data, 0, 4096)) != -1) {
            outStream.write(data, 0, count);
        }
        data = null;
        return new String(outStream.toByteArray(), encoding/* "ISO-8859-1" */);
    }
}
