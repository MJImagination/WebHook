package com.ancun.webhook.base;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/1/17
 */
public class JPATimeVO {
    /**
     * 数据库对应字段
     */
    private String timeFiledName;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public String getTimeFiledName() {
        return timeFiledName;
    }

    public void setTimeFiledName(String timeFiledName) {
        this.timeFiledName = timeFiledName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "JPATimeVO{" +
                "timeFiledName='" + timeFiledName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
