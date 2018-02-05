package com.ancun.webhook.okhttp.callBack;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 自定义callback
 * @Author: MJ
 * @Date: Created in 2018/2/1
 */
public class PublicityDataCallback implements Callback {
    public static final Logger logger = LoggerFactory.getLogger(PublicityDataCallback.class);

    @Override
    public void onFailure(Call call, IOException e) {
        if (e instanceof SocketTimeoutException) {
            logger.info("超时异常:{}", e.getMessage());
//            call.
//          ((FormBody) ((RealCall) call).originalRequest.body()).encodedValues.String();
//            call.request().body().;
            Buffer buffer = new Buffer();
            try {
                call.request().body().writeTo(buffer);
//                call.
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                buffer.close();
            }
            System.out.println(buffer);

        }
        if (e instanceof ConnectException) {
            logger.info("连接异常:{}", e.getMessage());
            Buffer buffer = new Buffer();
            try {
                call.request().body().writeTo(buffer);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println(buffer);
        }
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        //response.body().string() 是一次性流，调用一次自动关闭
        logger.info("SUCCESS ！ThreadId:" + Thread.currentThread().getId() + " content：" + response.body().string());
    }
}