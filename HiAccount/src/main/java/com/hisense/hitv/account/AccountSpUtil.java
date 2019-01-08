package com.hisense.hitv.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.hismart.base.AppUtil;


/**
 * Created by Victor on 2015/8/29
 */
public class AccountSpUtil {
    private static SharedPreferences sp;

    static {
        sp = PreferenceManager.getDefaultSharedPreferences(AppUtil.getApp());
    }

    public static void setInt(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public static int getInt(String key) {
        return getInt(key, -1);
    }

    public static int getInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public static void setLong(String key, long value) {
        sp.edit().putLong(key, value).apply();
    }

    public static long getLong(String key) {
        return getLong(key, -1L);
    }

    public static long getLong(String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    public static void setFloat(String key, float value) {
        sp.edit().putFloat(key, value).apply();
    }

    public static float getFloat(String key) {
        return getFloat(key, -1f);
    }

    public static float getFloat(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public static void setBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public static void setString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public static void remove(String key) {
        sp.edit().remove(key).apply();
    }

    public static void clear() {
        sp.edit().clear().apply();
    }
}
