
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class DefaultLanguageReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 3999567035847698839L;
    private int defaultLanguageId;// 语言，默认为0 0中文 1英语 2 法语 3韩语 4俄语 5日语 6西班牙语 7德语
                                  // 8 繁体中文 9 阿拉伯语 10 波斯语 11 泰语 12 意大利语 13 荷兰语

    public int getDefaultLanguageId() {
        return defaultLanguageId;
    }

    public void setDefaultLanguageId(int defaultLanguageId) {
        this.defaultLanguageId = defaultLanguageId;
    }

}
