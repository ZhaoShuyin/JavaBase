package com.zsy.java.base;

import java.awt.Canvas;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Title com.zsy.java.base
 * @date 2020/4/20
 * @autor Zsy
 */

class Test14 {
    static ExecutorService executorService = Executors.newFixedThreadPool(1);
    static Future<?> submit;
    static int anInt;

    public static void main(String[] args) throws InterruptedException {
        t();
        Thread.sleep(3000);
        System.out.println("取消");
        submit.cancel(false);
        Thread.sleep(3000);
        System.out.println("shutdown");
        executorService.shutdown();
        Thread.sleep(3000);
        System.out.println("shutdownNow");
        executorService.shutdownNow();
        Thread.sleep(3000);
        System.out.println("awaitTermination");
        executorService.awaitTermination(100,TimeUnit.MILLISECONDS);
        t();
        Thread.sleep(3000);
        t();
        Thread.sleep(3000);
        t();
        Thread.sleep(3000);
        t();
        Thread.sleep(3000);
        t();
    }

    private static void t() {
        if (submit != null && !submit.isCancelled()) {
            System.out.println("取消");
            submit.cancel(false);
            return;
        }
        anInt++;
        System.out.println("开启" + anInt);
        submit = executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("线程池任务... " + anInt);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}
