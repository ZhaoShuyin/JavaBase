package com.zsy.java.thread;

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

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("子线程 " + i +" : "+Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        try {
            Thread.sleep(3000);
            System.out.println("主线程3秒后,停止子线程");
//            executorService.shutdown();//停止接收新任务，原来的任务继续执行
//            executorService.shutdownNow();//停止接收新任务，原来的任务停止执行
//            executorService.awaitTermination();//当前线程阻塞
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
