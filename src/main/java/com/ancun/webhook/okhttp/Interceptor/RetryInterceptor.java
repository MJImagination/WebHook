package com.ancun.webhook.okhttp.Interceptor;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 *
 * 失败重发拦截器
 * @author MJ
 * @Description:
 * @Date: create 2018/2/4
 */
@Component
public class RetryInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(RetryInterceptor.class);

    @Value("${okhttp.MAX_RETRY:3}")
    private int MAX_RETRY;  //最大重试次数

    @Value("${okhttp.DELAY:3000}")
    private long DELAY;     //延迟

    @Value("${okhttp.INCREASE_DELY:2000}")
    private long INCREASE_DELY;   //叠加延迟

    public RetryInterceptor() {
    }

    public RetryInterceptor(int maxRetry) {
        this.MAX_RETRY = maxRetry;
    }

    public RetryInterceptor(int maxRetry, long delay) {
        this.MAX_RETRY = maxRetry;
        this.DELAY = delay;
    }

    public RetryInterceptor(int maxRetry, long delay, long increaseDelay) {
        this.MAX_RETRY = maxRetry;
        this.DELAY = delay;
        this.INCREASE_DELY = increaseDelay;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        RetryWrapper retryWrapper = proceed(chain);

        while (retryWrapper.isNeedReTry()) {
            retryWrapper.retryNum++;

            logger.info("尝试重发 : ThreadId = {} , 第{}次重发 body = {}",
                    Thread.currentThread().getId(),
                    retryWrapper.retryNum,((FormBody)retryWrapper.request.body()).encodedValue(0));
            try {
                Thread.sleep(DELAY + (retryWrapper.retryNum - 1) * INCREASE_DELY);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
            //e.printStackTrace();
        } catch (SocketTimeoutException e){
            //e.printStackTrace();
        }
    }

    static class RetryWrapper {
        volatile int retryNum = 0;//假如设置为3次重试的话，则最大可能请求5次（默认1次+3次重试 + 最后一次默认）
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

        public boolean isSuccessful() {
            return response != null && response.isSuccessful();
        }

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