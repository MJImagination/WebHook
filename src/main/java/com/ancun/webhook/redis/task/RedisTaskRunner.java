package com.ancun.webhook.redis.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;

public class RedisTaskRunner implements ApplicationRunner, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(RedisTaskRunner.class);

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("task runner");
    }
}