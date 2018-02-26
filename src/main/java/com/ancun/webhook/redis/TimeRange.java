package com.ancun.webhook.redis;

import java.io.Serializable;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/10
 */
public class TimeRange implements Serializable {
    private static final long serialVersionUID = -8933908728817694065L;
    /**
     * 过期时间
     */
    private long outTime;

    public long getOutTime() {
        return outTime;
    }

    public void setOutTime(long outTime) {
        this.outTime = outTime;
    }
}
