package com.ancun.webhook.redis.redisImpl;

import com.ancun.webhook.activityMQ.BpsPreserveMainCallBack;
import com.ancun.webhook.model.WebHookRecord;
import com.ancun.webhook.redis.AbstractRedisBase;
import com.ancun.webhook.redis.TimeRange;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

/**
 * @author MJ
 * @Description:
 * @Date: create 2018/1/5
 */
@Service
public class ReidisTimeRange extends AbstractRedisBase<TimeRange> {
//    /**
//     * 过期时间扫描
//     */
//    public void OutTimeScan() {
////        log.info("webHook redis start sacn");
//        //获取所有redisKey前缀的redisKey
//        Set<String> webHookRedissKeys = super.redisTemplate.keys("redisKey" + "*");
//        if (webHookRedissKeys.size() > 0) {
//            for (String key : webHookRedissKeys) {
////                try {
////                    log.info(String.valueOf(super.hashOperations.get(key,"webHookKey").getStatus()));
////                } catch (Exception e) {
////                }
//                //判断是否成功
//                if (this.isSuccess(key)) {
//                    super.redisTemplate.delete(key);
//                } else if (super.hashOperations.get(key, "outTime").getOutTime() < new Date().getTime()) {
//                    //删除前，把失败记录json串保存到数据库中
//                    //超时从队列去除
//                    super.redisTemplate.delete(key);
//                }
//
//            }
//        }
////        log.info("webHook redis end sacn");
//    }
//
//
//    /**
//     * 判断是否保全成功
//     */
//    public boolean isSuccess(String redisKey) {
//        //取出rediskey对应的所有hashkey，和清单循环对比
////        WebHookRecord webHookRecord = super.get(redisKey,"outTime");
//
//        int attachSize = 0; //附件数量
//        int urlSize = 0;    //url数量
//        WebHookRecord webHookRecord = new WebHookRecord();
//        BpsPreserveMainCallBack bpsPreserveMainCallBack = super.get(redisKey, "mainCallBackKey");
//        if(bpsPreserveMainCallBack.getComplete()){  //没有attache和url
////            webHookRecord
//        }else{
//            if (bpsPreserveMainCallBack.getOsKeyList() != null) {
//                attachSize= bpsPreserveMainCallBack.getOsKeyList().size();
//            }
//            if (bpsPreserveMainCallBack.getSentCaptureList() != null) {
//                urlSize = bpsPreserveMainCallBack.getSentCaptureList().size();
//            }
//        }
//
//
//        //成功保全到数据库中
//        return false;
//    }

}
