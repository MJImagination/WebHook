package com.ancun.webhook.controller;

import com.ancun.webhook.activityMQ.BpsPreserveAttachCallBack;
import com.ancun.webhook.activityMQ.BpsPreserveMainCallBack;
import com.ancun.webhook.activityMQ.BpsPreserveUrlCallBack;
import com.ancun.webhook.activityMQ.MQProducer;
import com.ancun.webhook.model.WebHook;
import com.ancun.webhook.model.WebHookRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/4
 */
@Controller
@RequestMapping(value = "/mq")
public class ActivityMqController {
    @Autowired
    private MQProducer mqProducer;

    @GetMapping("/activemq/send")
    @ResponseBody
    public String activemq(HttpServletRequest request, String msg) throws InterruptedException {
        msg = StringUtils.isEmpty(msg) ? "This is Empty Msg." : msg;


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 10; i++) {
            BpsPreserveMainCallBack bpsPreserveMainCallBack = new BpsPreserveMainCallBack();
            bpsPreserveMainCallBack.setPartnerId(5);
            bpsPreserveMainCallBack.setRecordNo("*****_" + String.valueOf(i));
            bpsPreserveMainCallBack.setComplete(false);

            int random = new Random().nextInt(10);
            if (random % 2 == 0) {
                System.out.println("random = " + random);
                bpsPreserveMainCallBack.setHasAttach(true);
                List<String> attach = new ArrayList<>();
                attach.add("oskey0");
                attach.add("oskey1");

                List<String> url = new ArrayList<>();
                url.add("urlKey0");
                url.add("urlKey1");
                bpsPreserveMainCallBack.setOsKeyList(attach);
                bpsPreserveMainCallBack.setSentCaptureList(url);
                mqProducer.send3(bpsPreserveMainCallBack);

                for (int j = 0; j < 1; j++) {
                    BpsPreserveAttachCallBack bpsPreserveAttachCallBack = new BpsPreserveAttachCallBack();
                    bpsPreserveAttachCallBack.setRecordNo("*****_" + String.valueOf(i));
                    bpsPreserveAttachCallBack.setOsKey("oskey" + j);
                    mqProducer.attachProducer(bpsPreserveAttachCallBack);

                    BpsPreserveUrlCallBack bpsPreserveUrlCallBack = new BpsPreserveUrlCallBack();
                    bpsPreserveUrlCallBack.setRecordNo("*****_" + String.valueOf(i));
                    bpsPreserveUrlCallBack.setUrl("urlKey" + j);
                    mqProducer.urlProducer(bpsPreserveUrlCallBack);
                }

//                for(int k =1;k<2;k++){
//                    Thread.sleep(  new Random().nextInt(1000));
//                    BpsPreserveAttachCallBack bpsPreserveAttachCallBack = new BpsPreserveAttachCallBack();
//                    bpsPreserveAttachCallBack.setRecordNo("*****_"+String.valueOf(i));
//                    bpsPreserveAttachCallBack.setOsKey("oskey" + k);
//                    mqProducer.attachProducer(bpsPreserveAttachCallBack);
//
//                    BpsPreserveUrlCallBack bpsPreserveUrlCallBack = new BpsPreserveUrlCallBack();
//                    bpsPreserveUrlCallBack.setRecordNo("*****_"+String.valueOf(i));
//                    bpsPreserveUrlCallBack.setUrl("urlKey" + k);
//                    mqProducer.urlProducer(bpsPreserveUrlCallBack);
//                }

            } else {
                bpsPreserveMainCallBack.setHasAttach(false);
                List<String> attach = new ArrayList<>();
                attach.add("oskey0");
                attach.add("oskey1");

                List<String> url = new ArrayList<>();
                url.add("urlKey0");
                url.add("urlKey1");
                bpsPreserveMainCallBack.setSentCaptureList(url);
                mqProducer.send3(bpsPreserveMainCallBack);

                for (int j = 0; j < 1; j++) {
                    BpsPreserveAttachCallBack bpsPreserveAttachCallBack = new BpsPreserveAttachCallBack();
                    bpsPreserveAttachCallBack.setRecordNo("*****_" + String.valueOf(i));
                    bpsPreserveAttachCallBack.setOsKey("oskey" + j);
                    mqProducer.attachProducer(bpsPreserveAttachCallBack);

                    BpsPreserveUrlCallBack bpsPreserveUrlCallBack = new BpsPreserveUrlCallBack();
                    bpsPreserveUrlCallBack.setRecordNo("*****_" + String.valueOf(i));
                    bpsPreserveUrlCallBack.setUrl("urlKey" + j);
                    mqProducer.urlProducer(bpsPreserveUrlCallBack);
                }
            }
        }
        stopWatch.stop();
        System.out.println("发送消息耗时: " + stopWatch.getTotalTimeMillis() + "ms");
        return "Activemq has sent OK.";
    }


    @GetMapping("/activemq/send2")
    @ResponseBody
    public String activemq2(HttpServletRequest request, String msg) {
        msg = StringUtils.isEmpty(msg) ? "This is Empty Msg." : msg;


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 20; i++) {
            System.out.println(i);
            WebHookRecord webHookRecord = new WebHookRecord();
            webHookRecord.setRecordNo("*****_" + String.valueOf(i));
            mqProducer.send2("message");
//            mqProducer.send2(msg + " = " + i);
        }
        stopWatch.stop();
        System.out.println("发送消息耗时: " + stopWatch.getTotalTimeMillis() + "ms");
        return "Activemq has sent OK.";
    }
}
