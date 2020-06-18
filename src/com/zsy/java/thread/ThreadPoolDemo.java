package com.zsy.java.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Title ThreadPool的使用
 * <p>
 * Executors: 工厂类
 * public static ExecutorService newCachedThreadPool():				根据任务的数量决定线程池中线程的个数
 * public static ExecutorService newFixedThreadPool(int nThreads):	初始化一个具有指定数量线程的线程池
 * public static ExecutorService newSingleThreadExecutor():			初始化一个具有一个线程的线程池
 * *              线程池中线程的特点:	线程执行完毕以后不会死亡 , 会被线程池回收,称为空闲状态,等待执行下一个任务
 * <p>
 * *    submit(Runnable task) 提交
 * *    shutdown() 关闭线程池
 *
 * @author Zsy
 * @date 2019/8/11 16:48
 */
public class ThreadPoolDemo {

    static boolean running = false;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        running = true;
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Timer timer = new Timer();
                try {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (!running) {
                                cancel();
                            }
                            System.out.println("子线程 : " + Thread.currentThread().getName());
                        }
                    }, 1000, 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                    timer.cancel();
                }
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println("主线程5秒后, shutdownNow");
                    executorService.shutdown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
      /*  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    running = false;
                    System.out.println("主线程10秒后, running = false");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                executorService.submit(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("10秒后 再次使用线程池: " + Thread.currentThread().getName());
                    }
                });
            }
        }, 10000);

    }

}
