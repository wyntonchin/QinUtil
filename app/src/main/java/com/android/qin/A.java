package com.android.qin;

public class A {
    private volatile A a;

    public A getA() {
        if(a==null){
            synchronized (A.class){
                if (a==null){
                    a = new A();
                }
            }
        }
        return a;
    }
}
