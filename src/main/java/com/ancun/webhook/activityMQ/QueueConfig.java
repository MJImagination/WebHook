package com.ancun.webhook.activityMQ;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Destination;

/**
 * @Description: 定义消息队列 - 用于本地测试
 * @Author: MJ
 * @Date: Created in 2018/2/4
 */
@Configuration
public class QueueConfig {

    @Bean(name = "First_Queue")
    public Destination First_Queue() {
        return new ActiveMQQueue(QueueName.First_Queue);
    }

    @Bean(name = "Second_Queue")
    public Destination Second_Queue() {
        return new ActiveMQQueue(QueueName.Second_Queue);
    }

    /**
     * 主体数据清单
     */
    @Bean(name = "BPS_PRESERVE_MAIN_CALL_BACK_QUEUE")
    public Destination callBack() {
        return new ActiveMQQueue(QueueName.BPS_PRESERVE_MAIN_CALL_BACK_QUEUE);
    }

    /**
     * 附件
     */
    @Bean(name = "BPS_PRESERVE_ATTACH_CALL_BACK_QUEUE")
    public Destination attach_Queue() {
        return new ActiveMQQueue(QueueName.BPS_PRESERVE_ATTACH_CALL_BACK_QUEUE);
    }

    /**
     * url
     */
    @Bean(name = "BPS_PRESERVE_URL_CALL_BACK_QUEUE")
    public Destination url_Queue() {
        return new ActiveMQQueue(QueueName.BPS_PRESERVE_URL_CALL_BACK_QUEUE);
    }
}  