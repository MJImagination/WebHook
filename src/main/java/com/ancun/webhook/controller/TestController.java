package com.ancun.webhook.controller;


import com.ancun.webhook.model.WebHook;
import com.ancun.webhook.redis.*;
import com.ancun.webhook.redis.redisImpl.RedisBpsPreserveMainCallBack;
import com.ancun.webhook.redis.redisImpl.ReidisTimeRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Random;


@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private RedisServiceImpl service;
    @Autowired
    private WebHookRedisServiceImpl webHookRedisService;
    @Autowired
    private RedisBpsPreserveMainCallBack redisBpsPreserveMainCallBack;
    @Autowired
    private ReidisTimeRange reidisTimeRange;


    //添加
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public void test() {
        System.out.println("start.....");
        RedisModel m = new RedisModel();
        m.setName("张三");
        m.setTel("1111");
        m.setAddress("深圳1");
        m.setRedisKey("zhangsanKey01");
        service.put(m.getRedisKey(), m, -1);

        RedisModel m2 = new RedisModel();
        m2.setName("张三2");
        m2.setTel("2222");
        m2.setAddress("深圳2");
        m2.setRedisKey("zhangsanKey02");
        service.put(m2.getRedisKey(), m2, -1);

        RedisModel m3 = new RedisModel();
        m3.setName("张三3");
        m3.setTel("2222");
        m3.setAddress("深圳2");
        m3.setRedisKey("zhangsanKey03");
        service.put(m3.getRedisKey(), m3, -1);

        WebHook webHook = new WebHook();
        webHook.setRedisKey("webHook01");
        webHook.setStatus(8);
        webHookRedisService.put(webHook.getRedisKey(), webHook, -1);


        System.out.println("add success end...");
    }

    @RequestMapping(value = "/add2", method = RequestMethod.POST)
    @ResponseBody
    public String add2() throws InterruptedException {

        for (int i = 0; i < 3; i++) {
            WebHook webHook = new WebHook();
//            webHook.setRedisKey("webHook01");
            webHook.setRemarks(String.valueOf(i));
            webHook.setStatus(9);
//            Thread.sleep((long)new Random().nextInt(8000)+3000);
            webHook.setRemarks("*****_" + String.valueOf(i));
            TimeRange timeRange = new TimeRange();
            timeRange.setOutTime(new Date().getTime() + (long) new Random().nextInt(1000) + 3000);

            reidisTimeRange.put("redisKey" + i, "outTime", timeRange, -1);
//            redisWebHookRecord.put("redisKey" + i, "webHookKey" , webHook, -1);
        }
        return "true";
    }

    //查询所有对象
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    @ResponseBody
    public Object getAll() {
        return service.getAll();
    }

    //查询所有key
    @RequestMapping(value = "/getKeys", method = RequestMethod.POST)
    @ResponseBody
    public Object getKeys() {
        return service.getKeys();
    }

    //根据key查询
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Object get() {
        RedisModel m = new RedisModel();
        m.setRedisKey("zhangsanKey02");
        return service.get(m.getRedisKey());
    }

    //删除
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public void remove() {
        RedisModel m = new RedisModel();
        m.setRedisKey("zhangsanKey01");
        service.remove(m.getRedisKey());
    }

    //判断key是否存在
    @RequestMapping(value = "/isKeyExists", method = RequestMethod.POST)
    @ResponseBody
    public void isKeyExists() {
        RedisModel m = new RedisModel();
        m.setRedisKey("zhangsanKey01");
        boolean flag = service.isKeyExists(m.getRedisKey());
        System.out.println("zhangsanKey01 是否存在: " + flag);
    }

    //查询当前缓存的数量
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    @ResponseBody
    public Object count() {
        return service.count();
    }

    //清空所有key
    @RequestMapping(value = "/empty", method = RequestMethod.POST)
    @ResponseBody
    public void empty() {
        service.empty();
    }

}