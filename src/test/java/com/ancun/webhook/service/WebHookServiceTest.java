package com.ancun.webhook.service;

import com.ancun.webhook.BaseTest;
import com.ancun.webhook.enums.WebHookEnum;
import com.ancun.webhook.model.WebHook;
import com.ancun.webhook.repository.WebHookRepository;
import com.ancun.webhook.service.impl.WebHookServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/6
 */
public class WebHookServiceTest extends BaseTest {

    @Autowired
    private WebHookRepository webHookRepository;

    @Autowired
    private WebHookServiceImpl webHookService;

    @Test

    public void createdWebHook() {
        WebHook webHook = new WebHook();
        webHook.setPartnerId(121123);
        webHook.setCallBackUrl("http://localhost:8070/echar/getHistogram");
        webHook.setCreateTime(new Date());
        webHook.setRemarks("回调测试");
        webHook.setStatus(WebHookEnum.STATUS_ON.getValue());
        webHookService.createdWebHook(webHook);
    }

    @Test
    public void deleteById() {
    }

    @Test

    public void updateWebHook() {
//        WebHook webHook = webHookService.findOneById(Long.valueOf(6));
//        webHook.setStatus(0);
//        webHookService.updateWebHook(webHook);
    }

    @Test
//    @Cacheable(key ="#p0")
//    @Cacheable(value="WebHookServiceTest")
    public void findOneById() {
//        System.out.println(webHookService.findOneById(Long.valueOf(6)));
    }

    @Test
    public void findPageList() {
    }
}