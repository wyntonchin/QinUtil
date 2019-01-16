package com.hisense.hitv.user.retrofit;

/**
 * @author qinwendong
 * @date 2018/12/19
 * descrption: 登录相关最外层 bean
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.Serializable;

public class BaseInfo<T> implements Serializable {

    /**
     * response : {"resultCode":1,"errorCode":600716,"errorDesc":"app校验失败"}
     * signatureServer : LLwbd9BAywSgjNS8zqjNLTL2C19qCj+RYZWHXywYg2Axrkn24HqH0dnOh6VoNJkL+14VSS7u/ZC4P79gBXuWWg==
     */

    private T response;
    private String signatureServer;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getSignatureServer() {
        return signatureServer;
    }

    public void setSignatureServer(String signatureServer) {
        this.signatureServer = signatureServer;
    }

    public boolean isSuccess(){
        return response != null;
    }
}

