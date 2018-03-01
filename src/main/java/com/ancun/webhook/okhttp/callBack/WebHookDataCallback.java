package com.ancun.webhook.okhttp.callBack;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import com.ancun.webhook.configuration.BeanContext;
import com.ancun.webhook.model.WebHookRecord;
import com.ancun.webhook.repository.WebHookRepository;
import com.ancun.webhook.service.WebHookRecordService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 自定义callback
 * @Author: MJ
 * @Date: Created in 2018/2/1
 */
@Component
public class WebHookDataCallback implements Callback {
    public static final Logger logger = LoggerFactory.getLogger(WebHookDataCallback.class);

    private WebHookRecordService webHookRecordService;

    @Override
    public void onFailure(Call call, IOException e) {
        //spring多线程中，手动获得对象
        this.webHookRecordService = BeanContext.getApplicationContext().getBean(WebHookRecordService.class);
        if (e instanceof SocketTimeoutException) {
            logger.info("超时异常:{}", e.getMessage());
            String recordNo = call.request().headers().get("recordNo");
            WebHookRecord webHookRecord = webHookRecordService.findByRecordNo(recordNo);
            webHookRecord.setStatus(0);
            webHookRecord.setCallBackContent(e.getMessage());
            webHookRecordService.updateWebHookRecord(webHookRecord);
        }
        if (e instanceof ConnectException) {
            logger.info("连接异常:{}", e.getMessage());
            String recordNo = call.request().headers().get("recordNo");
            WebHookRecord webHookRecord = webHookRecordService.findByRecordNo(recordNo);
            webHookRecord.setStatus(0);
            webHookRecord.setCallBackContent(e.getMessage());
            webHookRecordService.updateWebHookRecord(webHookRecord);
        }
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        //spring多线程中，手动获得对象
        this.webHookRecordService = BeanContext.getApplicationContext().getBean(WebHookRecordService.class);
        logger.info("SUCCESS ！ThreadId:" + Thread.currentThread().getId() + " content：" + response.body().string());
        String recordNo = call.request().headers().get("recordNo");
        WebHookRecord webHookRecord = webHookRecordService.findByRecordNo(recordNo);
        webHookRecord.setStatus(0);
        webHookRecord.setCallBackContent(response.body().string());
        webHookRecordService.updateWebHookRecord(webHookRecord);
    }
}