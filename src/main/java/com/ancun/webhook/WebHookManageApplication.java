package com.ancun.webhook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @author MJ
 * @Description:
 * @Date: create 2018/1/15
 */
@SpringBootApplication
@PropertySource({"okhttpConfig.properties"})
@ComponentScan(basePackages = {"com.ancun.webhook","com.ancun.webhook.repository"
        ,"com.ancun.webhook.model", "com.ancun.webhook.service"
        ,"com.ancun.webhook.service.impl"})
public class WebHookManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebHookManageApplication.class, args);
    }
}
