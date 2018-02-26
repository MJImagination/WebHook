package com.ancun.webhook;

import com.ancun.webhook.activityMQ.BpsPreserveMainCallBack;
import com.ancun.webhook.model.WebHook;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/10
 */
public class MyTest {
    public static void main(String[] aggs) {

    }

    @Test
    public void test1() {
        System.out.println(new Date().getTime());
    }

    @Test
    public void test() {
//        long millisecond = 1483159625851l;
        long millisecond = new Date().getTime();
        Date date = new Date(millisecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss SSS a");
        System.out.println("毫秒[1483159625851]对应日期时间字符串：" + format.format(date));
        test2();
    }

    public void test2() {
//        long millisecond = 1483159625851l;
        long millisecond = new Date().getTime() + 60 * 1000;
        Date date = new Date(millisecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss SSS a");
        System.out.println("毫秒[1483159625851]对应日期时间字符串：" + format.format(date));
    }

    @Test
    public void test3() {
        for (int i = 0; i < 10; i++) {
            int random = new Random().nextInt(10);
            if (random % 2 == 0) {
                System.out.println(random);
            } else {
                System.out.println(random + ":" + random % 2);
            }
        }

    }
}
