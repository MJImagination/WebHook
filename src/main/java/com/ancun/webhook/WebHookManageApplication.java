package com.ancun.webhook;

import com.ancun.webhook.redis.TimerScan;
import com.ancun.webhook.redis.task.RedisTaskRunner;
import org.apache.activemq.thread.TaskRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/1/22
 */
@SpringBootApplication
@PropertySource({"classpath:okhttpConfig.properties", "classpath:activeMQ.properties"})
@ComponentScan(basePackages = {"com.ancun.webhook"})
@EnableCaching
@EnableScheduling
public class WebHookManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebHookManageApplication.class, args);
    }

}
