
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class WifiDeviceVersionReplay extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -154121199249941018L;
    private int upgradeFlag;// number 0：不升级；1：普通升级；2：强制升级
    private String latestVersion;// string 最新版本号
    private int currentVersion;//当前版本号

    public int getUpgradeFlag() {
        return upgradeFlag;
    }

    public void setUpgradeFlag(int upgradeFlag) {
        this.upgradeFlag = upgradeFlag;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

	public int getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(int currentVersion) {
		this.currentVersion = currentVersion;
	}

    
}
