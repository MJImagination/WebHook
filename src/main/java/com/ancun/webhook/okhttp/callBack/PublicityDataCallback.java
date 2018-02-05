package com.ancun.webhook.okhttp.callBack;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 自定义callback
 * @author MJ
 * @Description:
 * @Date: create 2018/2/4
 */
public class PublicityDataCallback implements Callback {
	public static final Logger logger = LoggerFactory.getLogger(PublicityDataCallback.class);
	@Override
	public void onFailure(Call call, IOException e) {
		if (e instanceof SocketTimeoutException) {
			logger.info("超时异常:{}",e.getMessage());
//          ((FormBody) ((RealCall) call).originalRequest.body()).encodedValues.toString();
			Buffer buffer = new Buffer();
			try {
				call.request().body().writeTo(buffer);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println(buffer);

		}
		if (e instanceof ConnectException) {
			logger.info("连接异常:{}",e.getMessage());
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
		logger.info("SUCCESS ！ThreadId:" + Thread.currentThread().getId() + "content：" + response.body().string());
	}
}