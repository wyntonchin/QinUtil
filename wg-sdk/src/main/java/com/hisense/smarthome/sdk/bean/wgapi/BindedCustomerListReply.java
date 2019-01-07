
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class BindedCustomerListReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 629450560544126913L;
    private List<WGCustomer> customerList;// 用户列表

    public List<WGCustomer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<WGCustomer> customerList) {
        this.customerList = customerList;
    }

}
