package com.ancun.webhook.activityMQ;

import java.io.Serializable;

/**
 * create by niling
 */
public class BpsPreserveUrlCallBack implements Serializable {
    private static final long serialVersionUID = -5022999710743442390L;

    private String recordNo;
    private String url;

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "BpsPreserveUrlCallBack{" +
                "recordNo='" + recordNo + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
