package com.ancun.webhook.redis.redisImpl;


import com.ancun.bps.preserve.common.domain.BpsPreserveAttachCallBack;
import com.ancun.webhook.redis.AbstractRedisBase;
import com.ancun.webhook.redis.TimeRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/13
 */
@Service
public class RedisBpsPreserveAttachCallBack extends AbstractRedisBase<BpsPreserveAttachCallBack> {
    private static final Logger log = LoggerFactory.getLogger(RedisBpsPreserveAttachCallBack.class);
}