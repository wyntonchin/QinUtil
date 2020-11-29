package com.android.qin;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class H5PluginJsFactory {

    private static HashMap<Class<?>, Class<?>> clazzMap = new HashMap<>();

    private static HashMap<String, Class<?>> typeMap= new HashMap<>();


    static {
        clazzMap.put(AA.class, XX.class);

        clazzMap.put(BB.class, YY.class);
    }

    static {
        typeMap.put("010", XX.class);

        typeMap.put("012", YY.class);
    }



    public static Object getJsInstance(A a) {

            Class<?> x = clazzMap.get(a.getClass());
            try {
                assert x != null;
                System.out.println("getJsInstance x="+x);
                Constructor<?> constructor = x.getConstructor(String.class, String.class);

                Object cat = constructor.newInstance("2", "2.6");//相当于正常情况下的new，会调用构造函数

                return cat;

            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }

        return null;

        //return clazzMap.get(activity.getClass());
    }

    public static void main(String[] args){
        Object a = getJsInstance(new BB());
        System.out.println("main ="+a);

        ((X)a).set();
    }
}
