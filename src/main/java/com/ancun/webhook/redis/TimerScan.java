package com.ancun.webhook.redis;


import com.ancun.webhook.redis.redisImpl.RedisBpsPreserveMainCallBack;
import com.ancun.webhook.service.WebHookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description: 定时器
 * @Author: MJ
 * @Date: Created in 2018/2/10
 */
@Component
public class TimerScan {
    public static final Logger logger = LoggerFactory.getLogger(TimerScan.class);
    @Autowired
    private RedisBpsPreserveMainCallBack redisBpsPreserveMainCallBack;

    @Scheduled(cron = "0/10 * *  * * ?")
    public void scanRedis() {
        redisBpsPreserveMainCallBack.OutTimeScan();
    }

}
