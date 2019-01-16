package com.hisense.hitv.user.retrofit;

import java.io.Serializable;

/**
 * @author qinwendong
 * @date 2018/12/19
 * descrption:  response 基类
 */
public class ErrorBean implements Serializable {

    /**
     * resultCode : 1
     * errorCode : 6
     * errorDesc : 参数错误，请参考API文档
     */

    private int resultCode;
    private String desc;
    private int errorCode;
    private String errorDesc;

    public int getResultCode() {
        return resultCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
