package com.ancun.webhook.controller;

import com.ancun.webhook.activityMQ.MQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ancun on 2017/2/24.
 */
@Controller
@RequestMapping(value = "/mq")
public class ActivityMqController {
    @Autowired
    private MQProducer mqProducer;

    @GetMapping("/activemq/send")
    @ResponseBody
    public String activemq(HttpServletRequest request, String msg) {
        msg = StringUtils.isEmpty(msg) ? "This is Empty Msg." : msg;


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 2; i++) {
            mqProducer.send(msg + " = " + i);
            mqProducer.send2(msg + " = " + i);
        }
        stopWatch.stop();
        System.out.println("发送消息耗时: " + stopWatch.getTotalTimeMillis() + "ms");
        return "Activemq has sent OK.";
    }
}
