package com.ancun.webhook.activityMQ;

import com.ancun.bps.preserve.common.domain.BpsPreserveAttachCallBack;
import com.ancun.bps.preserve.common.domain.BpsPreserveMainCallBack;
import com.ancun.bps.preserve.common.domain.BpsPreserveUrlCallBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.Queue;

/**
 * @Description: 用于本地测试
 * @Author: MJ
 * @Date: Created in 2018/2/4
 */
@Component
public class MQProducer implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(MQProducer.class);
  
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "First_Queue")
    private Queue First_Queue;

    @Resource(name = "Second_Queue")
    private Queue Second_Queue;

    @Resource(name = "BPS_PRESERVE_MAIN_CALL_BACK_QUEUE")
    private Queue BPS_PRESERVE_MAIN_CALL_BACK_QUEUE;

    @Resource(name = "BPS_PRESERVE_ATTACH_CALL_BACK_QUEUE")
    private Queue BPS_PRESERVE_ATTACH_CALL_BACK_QUEUE;


    @Resource(name = "BPS_PRESERVE_URL_CALL_BACK_QUEUE")
    private Queue BPS_PRESERVE_URL_CALL_BACK_QUEUE;
  
    @Override  
    public void run(String... strings) throws Exception {  
        send("This is a log message.");  
        LOGGER.info("Log Message was sent to the Queue named sample.log");  
    }  

    /*发送消息到定义好的队列中*/
    public void send(String msg) {  
        this.jmsMessagingTemplate.convertAndSend(this.First_Queue, msg);
    }

    public void send2(String msg) {
        this.jmsMessagingTemplate.convertAndSend(this.Second_Queue, msg);
    }

    public void send3(BpsPreserveMainCallBack bpsPreserveMainCallBack) {
        this.jmsMessagingTemplate.convertAndSend(this.BPS_PRESERVE_MAIN_CALL_BACK_QUEUE, bpsPreserveMainCallBack);
    }

    /*动态定义消息队列名*/
    public void sendMessage(Destination destination, final String message) {
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    public void attachProducer(BpsPreserveAttachCallBack bpsPreserveAttachCallBack) {
        this.jmsMessagingTemplate.convertAndSend(this.BPS_PRESERVE_ATTACH_CALL_BACK_QUEUE, bpsPreserveAttachCallBack);
    }

    public void urlProducer(BpsPreserveUrlCallBack bpsPreserveUrlCallBack) {
        this.jmsMessagingTemplate.convertAndSend(this.BPS_PRESERVE_URL_CALL_BACK_QUEUE, bpsPreserveUrlCallBack);
    }
}  