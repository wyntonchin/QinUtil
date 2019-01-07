
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class SelfDiagnosisItemsReplay extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -6326720814492365489L;
    private int totalCount;// 自诊断项的个数
    private List<DiagnosisItem> diagnosisItemList;// 自诊断项列表

    public int getTotalCount() {
        return totalCount;
    }

    public List<DiagnosisItem> getDiagnosisItemList() {
        return diagnosisItemList;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setDiagnosisItemList(List<DiagnosisItem> diagnosisItemList) {
        this.diagnosisItemList = diagnosisItemList;
    }

}
