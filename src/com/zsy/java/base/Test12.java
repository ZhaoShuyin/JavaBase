package com.zsy.java.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Title com.zsy.java.base
 * @date 2020/4/20
 * @autor Zsy  https://www.cnblogs.com/linus-tan/p/10514909.html
 */

class Test12 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Runnable task1 = new Runnable() {
            public void run() {
                try {
                    System.out.println("task1 start");
                    Thread.sleep(8000);
                    System.out.println("task1 end");
                } catch (InterruptedException e) {
                    System.out.println("task1 interrupted: " + e);
                }
            }
        };

        Runnable task2 = new Runnable() {
            public void run() {
                try {
                    System.out.println(" task2 start");
                    Thread.sleep(1000);
                    System.out.println(" task2 end");
                } catch (InterruptedException e) {
                    System.out.println("task2 interrupted: " + e);
                }
            }
        };

        // 让学生解答某个很难的问题
        executorService.submit(task1);

        // 生学生解答很多问题
        executorService.submit(task2);


        try {
            // 向学生传达“问题解答完毕后请举手示意！”
            executorService.shutdown();
            // 向学生传达“XX分之内解答不完的问题全部带回去作为课后作业！”后老师等待学生答题
            // (所有的任务都结束的时候，返回TRUE)
            if (!executorService.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow(); // 超时的时候向线程池中所有的线程发出中断(interrupted)。
            }
        } catch (InterruptedException e) {
            // awaitTermination方法被中断的时候也中止线程池中全部的线程的执行。
            System.out.println("awaitTermination interrupted: " + e);
            executorService.shutdownNow();
        }
        System.out.println("executorService  end");
    }


}
