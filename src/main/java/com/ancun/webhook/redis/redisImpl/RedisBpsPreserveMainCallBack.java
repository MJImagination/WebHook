package com.ancun.webhook.redis.redisImpl;


import com.ancun.webhook.activityMQ.BpsPreserveAttachCallBack;
import com.ancun.webhook.activityMQ.BpsPreserveMainCallBack;
import com.ancun.webhook.activityMQ.BpsPreserveUrlCallBack;
import com.ancun.webhook.model.WebHook;
import com.ancun.webhook.model.WebHookRecord;
import com.ancun.webhook.redis.AbstractRedisBase;
import com.ancun.webhook.redis.TimeRange;
import com.ancun.webhook.service.WebHookRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/13
 */
@Service
public class RedisBpsPreserveMainCallBack extends AbstractRedisBase<BpsPreserveMainCallBack> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisBpsPreserveMainCallBack.class);

    @Resource(name = "hashOperations")
    private HashOperations<String, String, TimeRange> timeRangeHashOperations;
    @Resource(name = "hashOperations")
    private HashOperations<String, String, Object> hashOperations;
    @Autowired
    private WebHookRecordService webHookRecordService;


    /**
     * 过期时间扫描
     */
    public void OutTimeScan() {
        //遍历所有 webhookKey 为前缀的key
        Set<String> webHookRedissKeys = super.redisTemplate.keys("webhookKey" + "*");
        if (webHookRedissKeys.size() > 0) {
            for (String key : webHookRedissKeys) {
                //存在临界问题   超时从队列去除
                TimeRange timeRange = this.timeRangeHashOperations.get(key, "outTime");
                if (timeRange != null && timeRange.getOutTime() < new Date().getTime()) {
                    BpsPreserveMainCallBack bpsPreserveMainCallBack = super.get(key, "mainCallBackKey");
                    //删除前将已接受的数据存置数据库
                    if (bpsPreserveMainCallBack != null) {
                        WebHookRecord webHookRecord = webHookRecordService.findByRecordNo(bpsPreserveMainCallBack.getRecordNo());
                        List<Object> objectList = hashOperations.values(key);
                        List<String> urlList = new ArrayList<>();
                        List<String> attachList = new ArrayList<>();
                        for (Object o : objectList) {
                            if (o.getClass().equals(BpsPreserveUrlCallBack.class)) {
                                BpsPreserveUrlCallBack bpsPreserveUrlCallBack = (BpsPreserveUrlCallBack) o;
                                urlList.add(bpsPreserveUrlCallBack.getUrl());

                            } else if (o.getClass().equals(BpsPreserveAttachCallBack.class)) {
                                BpsPreserveAttachCallBack bpsPreserveAttachCallBack = (BpsPreserveAttachCallBack) o;
                                attachList.add(bpsPreserveAttachCallBack.getOsKey());
                            }
                        }
                        webHookRecord.setReceiveUrlId(urlList.toString());
                        webHookRecord.setReceiveAttachId(attachList.toString());
                        webHookRecordService.updateWebHookRecord(webHookRecord);
                    }
                    super.redisTemplate.delete(key);
                }

            }
        }
    }

    /**
     * 判断是否保全成功
     */
    public boolean isSuccess(String redisKey) {
        int attachSize = 0; //附件数量
        int urlSize = 0;    //url数量
        int hashKeyAllCount = 0; //对应redisKey里所有的对象数量
        WebHookRecord webHookRecord = new WebHookRecord();
        BpsPreserveMainCallBack bpsPreserveMainCallBack = super.get(redisKey, "mainCallBackKey");
        //存在延迟，有bpsPreserveMainCallBack时才操作
        if (bpsPreserveMainCallBack != null) {
            hashKeyAllCount = (int) super.count(redisKey);
            if (bpsPreserveMainCallBack.getComplete()) {  //没有attache和url
                webHookRecord.setIsComplete(1);
            } else {
                if (bpsPreserveMainCallBack.getOsKeyList() != null) {
                    attachSize = bpsPreserveMainCallBack.getOsKeyList().size();
                }
                if (bpsPreserveMainCallBack.getSentCaptureList() != null) {
                    urlSize = bpsPreserveMainCallBack.getSentCaptureList().size();
                }
            }
            /**
             * 判断是否完成
             */
            if (hashKeyAllCount - 2 == (attachSize + urlSize)) {
                webHookRecord = webHookRecordService.findByRecordNo(bpsPreserveMainCallBack.getRecordNo());
                webHookRecord.setIsComplete(1);
                if (bpsPreserveMainCallBack.getOsKeyList() != null) {
                    webHookRecord.setReceiveAttachId(bpsPreserveMainCallBack.getOsKeyList().toString());
                }
                if (bpsPreserveMainCallBack.getSentCaptureList() != null) {
                    webHookRecord.setReceiveUrlId(bpsPreserveMainCallBack.getSentCaptureList().toString());
                }
                webHookRecord.setIsComplete(1);
                webHookRecordService.updateWebHookRecord(webHookRecord);
                super.redisTemplate.delete(redisKey);
                return true;
            }

        }
        return false;
    }
}