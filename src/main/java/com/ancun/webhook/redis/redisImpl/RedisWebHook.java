package com.ancun.webhook.redis.redisImpl;


import com.ancun.webhook.model.WebHook;
import com.ancun.webhook.redis.AbstractRedisBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/13
 */
@Service
public class RedisWebHook extends AbstractRedisBase<WebHook> {
    private static final Logger log = LoggerFactory.getLogger(RedisWebHook.class);


}