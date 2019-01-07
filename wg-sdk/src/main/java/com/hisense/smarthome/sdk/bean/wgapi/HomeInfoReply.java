
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class HomeInfoReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -8209494500973031459L;
    private String homeName;// 家庭名称
    private String homeAddress;// 家庭住址
    private String homedesc;// 描述信息
    private String areaName;// 地区信息
    private int timezone;// 时区信息值从-12到12，负值表示西时区，正值表示东时区
    private String province;//省
    private String city;//市
    private String district;//区
    public String getHomeName() {
        return homeName;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getHomedesc() {
        return homedesc;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setHomedesc(String homedesc) {
        this.homedesc = homedesc;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
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
