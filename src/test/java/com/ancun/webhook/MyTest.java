package com.ancun.webhook;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        List<String> urlList = new ArrayList<>();
        System.out.println(urlList.size() > 0 ? "yes" : "no");

    }
}
