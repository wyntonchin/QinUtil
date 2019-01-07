
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;
import java.util.List;

public class Task implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8698217169863563014L;

    private long id;// 工单ID
    private long customerId;// 客户ID
    private String customerName;// 联系人姓名
    private String telephone;// 联系人电话
    private String fixedTelephone;// 固定电话
    private String provinceId;// 省份ID
    private String provinceName;// 省份名称
    private String cityId;// 城市ID
    private String cityName;// 城市名称
    private String regionId;// 地区ID
    private String regionName;// 地区名称
    private String roadId;// 街道ID
    private String roadName;// 街道名称
    private String address;// 详细地址
    private String bookingDate;// 预约日期，格式为yyyy-mm-dd，如2015-07-01
    private String bookingDateRange;// 预约时间范围（全天、上午、下午、晚上）
    private String deviceId;// 设备ID
    private String questionDesc;// 问题描述
    private int serviceType;// 服务类型（枚举值）1：安装，2：维修，3：保养
    private int repairStatus;// 工单状态
    private long createDate;// 创建时间，13位时间戳
    private long updateDate;// 更新时间，13位时间戳
    private long commentCreateDate;// 评分时间
    private String commentStar;// 评分
    private String commentContext;// 评论内容
    private List<RepairStatus> statusList;// 状态列表
    private List<RepairStatus> allStatusList;// 全量状态列表

    public long getId() {
        return id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public String getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getRegionId() {
        return regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getRoadId() {
        return roadId;
    }

    public String getRoadName() {
        return roadName;
    }

    public String getAddress() {
        return address;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getBookingDateRange() {
        return bookingDateRange;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public int getServiceType() {
        return serviceType;
    }

    public int getRepairStatus() {
        return repairStatus;
    }

    public long getCreateDate() {
        return createDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public long getCommentCreateDate() {
        return commentCreateDate;
    }

    public String getCommentStar() {
        return commentStar;
    }

    public String getCommentContext() {
        return commentContext;
    }

    public List<RepairStatus> getStatusList() {
        return statusList;
    }

    public List<RepairStatus> getAllStatusList() {
        return allStatusList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setBookingDateRange(String bookingDateRange) {
        this.bookingDateRange = bookingDateRange;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    public void setRepairStatus(int repairStatus) {
        this.repairStatus = repairStatus;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public void setCommentCreateDate(long commentCreateDate) {
        this.commentCreateDate = commentCreateDate;
    }

    public void setCommentStar(String commentStar) {
        this.commentStar = commentStar;
    }

    public void setCommentContext(String commentContext) {
        this.commentContext = commentContext;
    }

    public void setStatusList(List<RepairStatus> statusList) {
        this.statusList = statusList;
    }

    public void setAllStatusList(List<RepairStatus> allStatusList) {
        this.allStatusList = allStatusList;
    }

    public String getFixedTelephone() {
        return fixedTelephone;
    }

    public void setFixedTelephone(String fixedTelephone) {
        this.fixedTelephone = fixedTelephone;
    }
}
