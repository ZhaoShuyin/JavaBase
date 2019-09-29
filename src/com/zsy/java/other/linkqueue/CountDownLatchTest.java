package com.zsy.java.other.linkqueue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch的使用
 */
public class CountDownLatchTest {

    public static void main(String[] args) {

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        System.out.println("主线程开始执行 …… ……");
        //第一个子线程执行
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("子线程：" + Thread.currentThread().getName() + "执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println("countDownLatch.getCount()==" + countDownLatch.getCount());
            }
        });
        service.shutdown();

        //第二个子线程执行
        ExecutorService es2 = Executors.newSingleThreadExecutor();
        es2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程：" + Thread.currentThread().getName() + "执行");
                countDownLatch.countDown();
                System.out.println("countDownLatch.getCount()==" + countDownLatch.getCount());
            }
        });
        es2.shutdown();
        System.out.println("等待两个线程执行完毕…… ……" + countDownLatch.getCount());
        try {
//            countDownLatch.await(2, TimeUnit.SECONDS);//最大等待时间
            countDownLatch.await();//导致当前线程等待，直到锁存器计数到0

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个子线程都执行完毕，继续执行主线程");
    }
}