package com.ancun.webhook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/1/22
 */
@SpringBootApplication
@PropertySource({"classpath:okhttpConfig.properties", "classpath:activeMQ.properties"})
@ComponentScan(basePackages = {"com.ancun.webhook","com.ancun.webhook.repository"
        ,"com.ancun.webhook.model", "com.ancun.webhook.service"
        ,"com.ancun.webhook.service.impl"})
public class WebHookManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebHookManageApplication.class, args);
    }
}
