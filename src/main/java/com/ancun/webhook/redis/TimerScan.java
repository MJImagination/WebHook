package com.ancun.webhook.redis;


import com.ancun.webhook.redis.redisImpl.RedisBpsPreserveMainCallBack;
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
    @Autowired
    private RedisBpsPreserveMainCallBack redisBpsPreserveMainCallBack;


    @Scheduled(cron = "0/3 * *  * * ?")
    public void scanRedis() {
//        webHookRedisServiceImpl2.getAll();
        redisBpsPreserveMainCallBack.OutTimeScan();
    }

    /*第一次10秒后执行，当执行完后2秒再执行*/
//    @Scheduled(initialDelay = 10000, fixedDelay = 2000)
//    public void timerInit() {
//        System.out.println("init : "+new Date());
//    }
}
