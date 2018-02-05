package com.ancun.webhook.activityMQ;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Destination;

/**
 * @Description: 用于定义消息队列
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
}  