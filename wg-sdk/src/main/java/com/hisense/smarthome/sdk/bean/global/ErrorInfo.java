package com.hisense.smarthome.sdk.bean.global;

import java.io.Serializable;

public class ErrorInfo implements Serializable {
    private static final long serialVersionUID = -8298461589532954871L;
    private String errorName = "";
    private String errorCode = "";

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
                "errorName='" + errorName + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
