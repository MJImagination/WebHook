package com.ancun.webhook.service;

import com.ancun.webhook.BaseTest;
import com.ancun.webhook.model.WebHook;
import com.ancun.webhook.repository.WebHookRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/5
 */
public class WebHookRecordServiceTest extends BaseTest {

    @Autowired
    private WebHookRepository webHookRepository;

    @Test
    public void createdWebHookRecord() {
        WebHook webHook = new WebHook();
        webHook.setCallBackUrl("fd");
        webHookRepository.save(webHook);
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void updateWebHookRecord() {
    }

    @Test
    public void findOneById() {
    }

    @Test
    public void findPageList() {
    }
}