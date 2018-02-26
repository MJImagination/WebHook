package com.ancun.webhook.activityMQ;

import java.io.Serializable;

/**
 * create by niling
 */
public class BpsPreserveAttachCallBack implements Serializable {

    private static final long serialVersionUID = 5760977623876036956L;
    private String recordNo;
    private String osKey;

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public String getOsKey() {
        return osKey;
    }

    public void setOsKey(String osKey) {
        this.osKey = osKey;
    }

    @Override
    public String toString() {
        return "BpsPreserveAttachCallBack{" +
                "recordNo='" + recordNo + '\'' +
                ", osKey='" + osKey + '\'' +
                '}';
    }
}
