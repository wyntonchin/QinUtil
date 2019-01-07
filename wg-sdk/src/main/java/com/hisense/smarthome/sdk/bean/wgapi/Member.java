
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class Member implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -9121827328992041631L;
    private String memberId;// 成员ID
    private String memberName;// 家庭成员名称
    private String memberHeadImgUrl;// 成员头像
    private int roleFlag;// 用户在家庭的角色标识1：管理员2：普通成员

    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberHeadImgUrl() {
        return memberHeadImgUrl;
    }

    public int getRoleFlag() {
        return roleFlag;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberHeadImgUrl(String memberHeadImgUrl) {
        this.memberHeadImgUrl = memberHeadImgUrl;
    }

    public void setRoleFlag(int roleFlag) {
        this.roleFlag = roleFlag;
    }

}
