package com.ancun.webhook.activityMQ;

import com.ancun.bps.preserve.common.domain.BpsPreserveAttachCallBack;
import com.ancun.bps.preserve.common.domain.BpsPreserveMainCallBack;
import com.ancun.bps.preserve.common.domain.BpsPreserveUrlCallBack;
import com.ancun.webhook.model.WebHookRecord;
import com.ancun.webhook.redis.TimeRange;
import com.ancun.webhook.redis.redisImpl.RedisBpsPreserveAttachCallBack;
import com.ancun.webhook.redis.redisImpl.RedisBpsPreserveMainCallBack;
import com.ancun.webhook.redis.redisImpl.RedisBpsPreserveUrlCallBack;
import com.ancun.webhook.redis.redisImpl.ReidisTimeRange;
import com.ancun.webhook.service.WebHookRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/4
 */
@Service
public class MQConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MQConsumer.class);
    @Autowired
    private RedisBpsPreserveMainCallBack redisBpsPreserveMainCallBack;
    @Autowired
    private RedisBpsPreserveAttachCallBack redisBpsPreserveAttachCallBack;
    @Autowired
    private RedisBpsPreserveUrlCallBack redisBpsPreserveUrlCallBack;
    @Autowired
    private ReidisTimeRange reidisTimeRange;
    @Autowired
    private WebHookRecordService webHookRecordService;

    /**
     * BpsPreserveMainCallBack consumer
     * concurrency 动态线程数 1-10 多消费者
     *
     * @param bpsPreserveMainCallBack
     */
    @JmsListener(destination = QueueName.BPS_PRESERVE_MAIN_CALL_BACK_QUEUE, concurrency = "1-10")
    public void receivedQueue3(BpsPreserveMainCallBack bpsPreserveMainCallBack) {
        LOGGER.info("MQ Has received from " + QueueName.BPS_PRESERVE_MAIN_CALL_BACK_QUEUE + ", msg: " + bpsPreserveMainCallBack);
        //过期时间
        TimeRange timeRange = new TimeRange();
        timeRange.setOutTime(new Date().getTime() + (long) new Random().nextInt(8000) + 3000);

        //缓存到redis
        String tempRedisKey = "webhookKey" + "_" + bpsPreserveMainCallBack.getRecordNo();
        LOGGER.error(tempRedisKey);
        reidisTimeRange.put(tempRedisKey, "outTime", timeRange, -1);
        redisBpsPreserveMainCallBack.put(tempRedisKey, "mainCallBackKey", bpsPreserveMainCallBack, -1);

        //清单持久化到数据库
        WebHookRecord webHookRecord = new WebHookRecord();
        webHookRecord.setPartnerId(bpsPreserveMainCallBack.getPartnerId());
//        webHookRecord.setCallBackUrl("wwww.baidu.com");
        webHookRecord.setRecordNo(bpsPreserveMainCallBack.getRecordNo());
        webHookRecord.setOutTime(new Date());
        webHookRecord.setCallNum(0);
        webHookRecord.setStatus(3);
        webHookRecord.setHasAttach(0);
        webHookRecord.setHasUrl(0);
        webHookRecord.setIsListComplete(0);
        webHookRecord.setIsComplete(0);

        /**
         * BpsPreserveMainCallBack清单可能传2遍,合二为一才是一份完成的清单。
         * 1遍的情况：isComplete= true(没有osKeyList和sentCaptureList)
         * 或  isComplete = false && hasAttach = false(只有sentCaptureList);
         * 2遍的情况：isComplete = false && hasAttach = true {
         *    第一次：传sentCaptureList
         *    第二次：传osKeyList
         * }
         */
        if (bpsPreserveMainCallBack.getComplete()) {  //已完成
            webHookRecord.setIsComplete(1);
            webHookRecord.setIsListComplete(1);
            redisBpsPreserveMainCallBack.delete(tempRedisKey);
        } else { //未完成 有 CaptureList 或 传osKeyList
            if (bpsPreserveMainCallBack.getSentCaptureList() != null) {     //有url
                webHookRecord.setUrlIdList(bpsPreserveMainCallBack.getSentCaptureList().toString());
                webHookRecord.setHasUrl(1);
                if (bpsPreserveMainCallBack.getHasAttach() == false) {//没附件
                    webHookRecord.setIsListComplete(1);
                    webHookRecord.setUrlIdList(bpsPreserveMainCallBack.getSentCaptureList().toString());
                }
            }
            if (bpsPreserveMainCallBack.getHasAttach() && bpsPreserveMainCallBack.getOsKeyList() != null) {     //有附件，
                webHookRecord.setAttachIdList(bpsPreserveMainCallBack.getOsKeyList().toString());
                webHookRecord.setHasAttach(1);
                //url由于发送发无法判断，用到的少，暂时考虑一种情况。有附件，直接判定清单是完整的
                webHookRecord.setIsListComplete(1);
                if (bpsPreserveMainCallBack.getSentCaptureList() != null) {
                    webHookRecord.setIsListComplete(1);
                    webHookRecord.setAttachIdList(bpsPreserveMainCallBack.getOsKeyList().toString());
                }
            }
        }
        WebHookRecord tempHookRecord = webHookRecordService.findByRecordNo(bpsPreserveMainCallBack.getRecordNo());
        if (tempHookRecord != null) {
            if (webHookRecord.getAttachIdList() != null) {
                tempHookRecord.setAttachIdList(webHookRecord.getAttachIdList());
            }
            if (webHookRecord.getUrlIdList() != null) {
                tempHookRecord.setUrlIdList(webHookRecord.getUrlIdList());
            }
            webHookRecordService.updateWebHookRecord(tempHookRecord);
        } else {
            webHookRecordService.createdWebHookRecord(webHookRecord);
        }
    }

    /**
     * BpsPreserveAttachCallBack consumer
     * concurrency 动态线程数 1-10 多消费者
     *
     * @param bpsPreserveAttachCallBack
     */
    @JmsListener(destination = QueueName.BPS_PRESERVE_ATTACH_CALL_BACK_QUEUE, concurrency = "1-10")
    public void attachConsumer(BpsPreserveAttachCallBack bpsPreserveAttachCallBack) {
        //缓存到redis
        String tempRedisKey = "webhookKey" + "_" + bpsPreserveAttachCallBack.getRecordNo();
        redisBpsPreserveAttachCallBack.put(tempRedisKey, "attach" + bpsPreserveAttachCallBack.getOsKey(), bpsPreserveAttachCallBack, -1);
        redisBpsPreserveMainCallBack.isSuccess(tempRedisKey);
    }


    /**
     * BpsPreserveUrlCallBack consumer
     * concurrency 动态线程数 1-10 多消费者
     *
     * @param bpsPreserveUrlCallBack
     */
    @JmsListener(destination = QueueName.BPS_PRESERVE_URL_CALL_BACK_QUEUE, concurrency = "1-10")
    public void urlConsumer(BpsPreserveUrlCallBack bpsPreserveUrlCallBack) {
        //缓存到redis
        String tempRedisKey = "webhookKey" + "_" + bpsPreserveUrlCallBack.getRecordNo();
        redisBpsPreserveUrlCallBack.put(tempRedisKey, "url" + bpsPreserveUrlCallBack.getUrl(), bpsPreserveUrlCallBack, -1);
        redisBpsPreserveMainCallBack.isSuccess(tempRedisKey);
    }
}