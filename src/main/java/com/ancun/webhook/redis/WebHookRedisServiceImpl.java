package com.ancun.webhook.redis;


import com.ancun.webhook.model.WebHook;
import org.springframework.stereotype.Service;


@Service
public class WebHookRedisServiceImpl extends IRedisService<WebHook> {
    private static final String REDIS_KEY = "TEST_REDIS_KEY";

    @Override
    protected String getRedisKey() {
        return this.REDIS_KEY;
    }
}