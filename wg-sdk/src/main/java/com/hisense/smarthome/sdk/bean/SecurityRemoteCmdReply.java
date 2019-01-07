package com.hisense.smarthome.sdk.bean;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

/**
 * Created by Hisense on 2017/10/30.
 */

public class SecurityRemoteCmdReply extends BaseInfo{
    private static final long serialVersionUID = 2070746296595563410L;
    private long cmdseq;

    public long getCmdseq() {
        return cmdseq;
    }

    public void setCmdseq(long cmdseq) {
        this.cmdseq = cmdseq;
    }
}
