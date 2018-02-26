package com.ancun.webhook.activityMQ;

import java.io.Serializable;
import java.util.List;

/**
 * 回调
 * create by niling
 */
public class BpsPreserveMainCallBack implements Serializable {
    private static final long serialVersionUID = 5723224316645180115L;

    /**
     * 该对象清单可能传2遍,合二为一才是一份完成的清单。
     * 1遍的情况：isComplete= true(没有osKeyList和sentCaptureList)
     * 或  isComplete = false && hasAttach = false(只有sentCaptureList);
     * 2遍的情况：isComplete = false && hasAttach = true {
     * 第一次：传sentCaptureList
     * 第二次：传osKeyList
     * }
     */
    private String recordNo;
    private Boolean isComplete = false;
    private List<String> osKeyList;//附件
    private List<String> sentCaptureList;
    private Integer partnerId;
    private Boolean hasAttach = false;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public List<String> getOsKeyList() {
        return osKeyList;
    }

    public void setOsKeyList(List<String> osKeyList) {
        this.osKeyList = osKeyList;
    }

    public List<String> getSentCaptureList() {
        return sentCaptureList;
    }

    public void setSentCaptureList(List<String> sentCaptureList) {
        this.sentCaptureList = sentCaptureList;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Boolean getHasAttach() {
        return hasAttach;
    }

    public void setHasAttach(Boolean hasAttach) {
        this.hasAttach = hasAttach;
    }

    @Override
    public String toString() {
        return "BpsPreserveMainCallBack{" +
                "recordNo='" + recordNo + '\'' +
                ", isComplete=" + isComplete +
                ", osKeyList=" + osKeyList +
                ", sentCaptureList=" + sentCaptureList +
                ", partnerId=" + partnerId +
                ", hasAttach=" + hasAttach +
                '}';
    }
}

