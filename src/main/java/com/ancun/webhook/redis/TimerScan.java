package com.ancun.webhook.redis;


import com.ancun.webhook.Aop.SavePublicityAspect;
import com.ancun.webhook.model.WebHook;
import com.ancun.webhook.model.WebHookRecord;
import com.ancun.webhook.redis.redisImpl.RedisBpsPreserveMainCallBack;
import com.ancun.webhook.service.WebHookRecordService;
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
    @Autowired
    private WebHookService webHookService;

    @Scheduled(cron = "0/10 * *  * * ?")
    public void scanRedis() {
        redisBpsPreserveMainCallBack.OutTimeScan();
    }


    //    @Scheduled(cron = "0/10 * *  * * ?")
    public void create() {
//        webHookRedisServiceImpl2.getAll();
        WebHook webHook = new WebHook();
        webHook.setRedisKey("fdsf");
        WebHook webHook1 = webHookService.createdWebHook(webHook);
        logger.info("************创建表成功" + webHook1);

    }

    /*第一次10秒后执行，当执行完后2秒再执行*/
//    @Scheduled(initialDelay = 10000, fixedDelay = 2000)
//    public void timerInit() {
//        System.out.println("init : "+new Date());
//    }
}
