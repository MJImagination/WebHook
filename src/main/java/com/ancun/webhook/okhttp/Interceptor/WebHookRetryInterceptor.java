package com.ancun.webhook.okhttp.Interceptor;

import com.ancun.webhook.model.WebHookRecord;
import com.ancun.webhook.okhttp.callBack.WebHookDataCallback;
import com.ancun.webhook.service.WebHookRecordService;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * @Description: 失败重发拦截器
 * @Author: MJ
 * @Date: Created in 2018/2/4
 */
@Component
public class WebHookRetryInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(WebHookRetryInterceptor.class);

    @Value("${okhttp.MAX_RETRY:3}")
    private int MAX_RETRY;  //最大重试次数

    @Value("${okhttp.DELAY:3000}")
    private long DELAY;     //延迟

    @Value("${okhttp.INCREASE_DELY:2000}")
    private long INCREASE_DELY;   //叠加延迟

    public WebHookRetryInterceptor() {
    }

    public WebHookRetryInterceptor(int maxRetry) {
        this.MAX_RETRY = maxRetry;
    }

    public WebHookRetryInterceptor(int maxRetry, long delay) {
        this.MAX_RETRY = maxRetry;
        this.DELAY = delay;
    }

    public WebHookRetryInterceptor(int maxRetry, long delay, long increaseDelay) {
        this.MAX_RETRY = maxRetry;
        this.DELAY = delay;
        this.INCREASE_DELY = increaseDelay;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        logger.info("ThreadId = {}",
                Thread.currentThread().getId());
        RetryWrapper retryWrapper = proceed(chain);
        while (retryWrapper.isNeedReTry()) {
            retryWrapper.retryNum++;
            String sdfsdf = retryWrapper.request().url().toString();

            logger.info("尝试重发 : ThreadId = {} , 第{}次重发 body = {}",
                    Thread.currentThread().getId(),
                    retryWrapper.retryNum, retryWrapper.request());
            try {
                Thread.sleep(DELAY + (retryWrapper.retryNum - 1) * INCREASE_DELY);
            } catch (InterruptedException e) {
                logger.info("InterruptedException :{}", e.getMessage());
            }
            proceed(chain, retryWrapper.request, retryWrapper);
        }
        return retryWrapper.response == null ? chain.proceed(chain.request()) : retryWrapper.response;
    }

    private RetryWrapper proceed(Chain chain) throws IOException {
        Request request = chain.request();
        RetryWrapper retryWrapper = new RetryWrapper(request, MAX_RETRY);

        proceed(chain, request, retryWrapper);

        return retryWrapper;
    }

    private void proceed(Chain chain, Request request, RetryWrapper retryWrapper) throws IOException {
        try {
            Response response = chain.proceed(request);
            retryWrapper.setResponse(response);
        } catch (SocketException e) {
        } catch (SocketTimeoutException e) {
        }
    }

    static class RetryWrapper {
        volatile int retryNum = 0;
        Request request;
        Response response;
        private int maxRetry;

        public RetryWrapper(Request request, int maxRetry) {
            this.request = request;
            this.maxRetry = maxRetry;
        }

        public void setResponse(Response response) {
            this.response = response;
        }

        Response response() {
            return this.response;
        }

        Request request() {
            return this.request;
        }

        /**
         * 判断是否成功
         *
         * @return
         */
        public boolean isSuccessful() {
            return response != null && response.isSuccessful();
        }

        /**
         * 是否需要重发
         *
         * @return
         */
        public boolean isNeedReTry() {
            return !isSuccessful() && retryNum < maxRetry;
        }

        public void setRetryNum(int retryNum) {
            this.retryNum = retryNum;
        }

        public void setMaxRetry(int maxRetry) {
            this.maxRetry = maxRetry;
        }
    }
}