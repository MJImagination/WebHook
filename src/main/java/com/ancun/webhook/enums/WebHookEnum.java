package com.ancun.webhook.enums;


public enum WebHookEnum {

    /*回调状态*/
    STATUS_ON(1, "启用"),

    STATUS_OFF(0, "关闭"),

    /*回调结果*/
    STATUS_SUCCESS(1, "成功"),

    STATUS_FAILURE(0, "失败"),

    /*是否包含file*/
    HAS_FILE(1, "有file"),

    HAS_NO_FILE(0, "没有file"),


    /*是否包含urls*/
    HAS_URLS(1, "有urls"),

    HAS_NO_URLS(0, "没有file"),


    /*transprot类型*/
    TRANSPORT_TYPE_FILE(1, "file"),

    TRANSPORT_TYPE_URLS(2, "urls");

    private int value;
    private String name;

    WebHookEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static String getName(int value) {
        for (WebHookEnum webHookEnum : WebHookEnum.values()) {
            if (webHookEnum.getValue() == value) {
                return webHookEnum.name;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
