package com.ancun.webhook.base;

/**
 * @author MJ
 * @Description:
 * @Date: create 2018/1/15
 */
public class SocketMessage {

    /**
     * 消息
     */
    private String message;
    /**
     * 内容
     */
    private String date;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SocketMessage{" +
                "message='" + message + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}