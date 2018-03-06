package com.ancun.webhook.configuration;

import com.ancun.webhook.enums.WebHookEnum;
import com.ancun.webhook.model.WebHook;
import com.ancun.webhook.redis.redisImpl.RedisWebHook;
import com.ancun.webhook.service.WebHookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.HashOperations;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 缓存 webhook表数据，用于获取回调地址
 * @Author: MJ
 * @Date: Created in 2018/2/26
 */
@Configuration
public class RedisRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisRefreshedListener.class);

    @Autowired
    private RedisWebHook redisWebHook;
    @Autowired
    private WebHookService webHookService;

    @Resource(name = "hashOperations")
    private HashOperations<String, String, String> hashOperations;

    private static final String webHookRedisKey = "webHook";

    @Override

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (redisWebHook.isExistRedisKey(webHookRedisKey)) {
            redisWebHook.delete(webHookRedisKey);
        }
        List<WebHook> webHooks = webHookService.findAllWebHook(WebHookEnum.STATUS_ON.getValue());
        if (webHooks != null && webHooks.size() > 0) {
            putALl(webHookRedisKey, webHooks);
        }
    }

    /**
     * 缓存所有
     *
     * @param redisKey
     * @param webHookList
     */
    public void putALl(String redisKey, List<WebHook> webHookList) {
        Map<String, String> doMainMap = new HashMap<>();
        for (WebHook webHook : webHookList) {
            doMainMap.put(String.valueOf(webHook.getPartnerId()), webHook.getCallBackUrl());
        }
        hashOperations.putAll(redisKey, doMainMap);
    }

}