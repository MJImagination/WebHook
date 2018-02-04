package com.ancun.webhook.okhttp.configuration;

import com.ancun.webhook.okhttp.Interceptor.RetryInterceptor;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.TimeUnit;

/**
 *
 * okhttp配置文件，全局公用一个OkHttpClient
 * @author MJ
 * @Description:
 * @Date: create 2018/2/4
 */
@Configuration
@Scope("singleton")
public class OkhttpConfiguration {
    @Value("${okhttp.CONNECT_TIMEOUT:3000}")
    private  int CONNECT_TIMEOUT;    //设置连接超时时间 MILLISECONDS

    @Value("${okhttp.READ_TIMEOUT:6000}")
    private  int READ_TIMEOUT;   ///设置读取超时时间 MILLISECONDS

    @Value("${okhttp.WRITE_TIMEOUT:8000}")   //设置写入超时时间 MILLISECONDS
    private int WRITE_TIMEOUT;

    @Value("${okhttp.maxRequests:200}")
    private int maxRequests ;  //最大请求数

    @Value("${okhttp.maxRequestsPerHost:30}")
    private int maxRequestsPerHost ; //相同host最大请求数


    @Bean
    public Dispatcher getDispatcher(){
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(maxRequests);
        dispatcher.setMaxRequestsPerHost(maxRequestsPerHost);
        return dispatcher;
    }

    @Autowired
    private Dispatcher dispatcher;
    @Autowired
    private RetryInterceptor retryInterceptor;

    @Bean
    public OkHttpClient initClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)
                .dispatcher(dispatcher)
                .addInterceptor(retryInterceptor)//失败重试
                .build();
        return okHttpClient;
    }



    
}  