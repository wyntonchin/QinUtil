# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

#如果启用优化, 则打开下面三项, 同时注释掉第四项
# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).

#-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
#-optimizationpasses 5
#-allowaccessmodification
-dontoptimize
-dontpreverify

#***********************************************************************************************************#

-keepattributes *Annotation*
-keep class com.hisense.hitv.logging.**



# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**

#下面语句是将layoutlib.jar和android.jar中重复的类产生的note警告关闭
-dontnote dalvik.**
-dontnote android.**
-dontnote javax.microedition.**
-dontnote org.json.*
-dontnote org.xmlpull.v1.*
-dontnote com.android.internal.**





#如果没有引用自升级模块则将下面注释掉
#-dontwarn tv.hitv.android.**

#保留所有抛出的异常
-keepattributes Exceptions

#支持泛型
-keepattributes Signature
# inner classes
-keepattributes EnclosingMethod


#SDK中需要特殊保留的

-keep public class com.hisense.smarthome.sdk.annotation.* {
       <fields>;
        <methods>;
}
-keep class com.hisense.smarthome.sdk.bean.** {
   <fields>;
   <methods>;
 }
-keep class com.hisense.smarthome.sdk.factory.* {
   <fields>;
   <methods>;
}


-keep class com.hisense.smarthome.sdk.service.WgApiService{
public *;
}

-keep class com.hisense.smarthome.sdk.util.Constants{
<fields>;
   <methods>;
}
-keep class com.hisense.smarthome.sdk.util.Md5{
<fields>;
   <methods>;
}
-keep class com.hisense.smarthome.sdk.util.Params{
<fields>;
   <methods>;
}
-keep class com.hisense.smarthome.sdk.util.DeviceConfig{
     public *;
}
-keep class com.hisense.smarthome.sdk.util.ChannelUtil{
     public *;
}

-keep class com.hisense.smarthome.sdk.util.Struct {
   public *;
 }

-keep class com.hisense.smarthome.sdk.util.SDKUtil{
     public *;
}

-keepattributes SourceFile , LineNumberTable

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

##---------------End: proguard configuration for Gson  ----------


