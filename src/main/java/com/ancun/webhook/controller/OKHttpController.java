package com.ancun.webhook.controller;

import com.ancun.webhook.okhttp.callBack.PublicityDataCallback;
import okhttp3.*;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/4
 */
@Controller
@RequestMapping(value = "/com/mj/http")
public class OKHttpController {
    public static final Logger logger = LoggerFactory.getLogger(OKHttpController.class);
    @Autowired
    private OkHttpClient okHttpClient;

    @RequestMapping("/okgo")
    @ResponseBody
    public String okgo() {
        String url = "http://localhost:8070/echar/getHistogram";
        for (int j = 0; j < 2000; j++) {
            RequestBody body = new FormBody.Builder()
                    .add("key", String.valueOf(j))
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new PublicityDataCallback());
        }
        return "true";
    }


}
