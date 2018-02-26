package com.ancun.webhook.activityMQ;

/**
 * @Description: 定义MQ队列名字
 * @Author: MJ
 * @Date: Created in 2018/2/4
 */
public class QueueName {
    public static final String First_Queue = "First_Queue";
    public static final String Second_Queue = "Second_Queue";
//    public static final String BPS_PRESERVE_CALL_BACK_QUEUE = "bps.preserve.callback.queue";

    /**
     * 主体数据清单
     */
    public static final String BPS_PRESERVE_MAIN_CALL_BACK_QUEUE = "bps.preserve.main.callback.queue";
    /**
     * 附件
     */
    public static final String BPS_PRESERVE_ATTACH_CALL_BACK_QUEUE = "bps.preserve.attach.callback.queue";
    /**
     * url
     */
    public static final String BPS_PRESERVE_URL_CALL_BACK_QUEUE = "bps.preserve.url.callback.queue";
}
