
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class Home implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6718024944886671297L;
    private String homeName;// 家庭名称
    private long homeId;// 家庭ID
    private int roleFlag;// 当前用户在家庭的角色标识1：管理员2：普通成员
    private String address;//家庭地址
    private String province;//省
    private String city;//市
    private String district;//区
    
    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public long getHomeId() {
        return homeId;
    }

    public void setHomeId(long homeId) {
        this.homeId = homeId;
    }

    public int getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(int roleFlag) {
        this.roleFlag = roleFlag;
    }

    
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

    
}
