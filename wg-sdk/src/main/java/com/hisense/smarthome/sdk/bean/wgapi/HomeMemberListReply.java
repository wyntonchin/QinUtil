
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class HomeMemberListReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -2472855388713057742L;
    private List<Member> memberList;// 家庭成员列表

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

}
