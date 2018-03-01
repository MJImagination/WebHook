package com.ancun.dto;

import java.io.Serializable;

public class WebHookResult
        implements Serializable {
    private static final long serialVersionUID = -8153955144861148041L;
    /**
     * 回调业务类型（1：基础平台保全回调）
     */
    private String businessType = "1";

    /**
     * 回调业务名称（基础平台保全回调）
     */
    private String businessName = "基础平台保全回调";
    /**
     * 是否完成保全 （true / false）
     */
    private Boolean isComplete;
    /**
     * 保全号
     */
    private String recordNo;
    /**
     * 发送时间
     */
    private String sendTime;

    public static long getSerialVersionUID() {
        return -8153955144861148041L;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessName() {
        return this.businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Boolean getComplete() {
        return this.isComplete;
    }

    public void setComplete(Boolean complete) {
        this.isComplete = complete;
    }

    public String getRecordNo() {
        return this.recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public String getSendTime() {
        return this.sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String toString() {
        return "WebHookResult{businessType='" + this.businessType + '\'' + ", businessName='" + this.businessName + '\'' + ", isComplete=" + this.isComplete + ", recordNo='" + this.recordNo + '\'' + ", sendTime='" + this.sendTime + '\'' + '}';
    }
}