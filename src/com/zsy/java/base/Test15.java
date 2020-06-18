package com.zsy.java.base;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Title com.zsy.java.base
 * @date 2020/4/20
 * @autor Zsy
 */

class Test15 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new TimerTask() {
            @Override
            public void run() {
                int i = 0;

                while (true) {
                    if (i > 5) {
//                        executorService.shutdownNow();
                        break;
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
//                          e.printStackTrace();
                    }
                    System.out.println("当前任务:" + (i++));
                }
            }
        });
        int j = 0;
        while (true) {
            j++;
            if (j>20){
                break;
            }
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("isShutdown:" + executorService.isShutdown() + " - isTerminated:" + executorService.isTerminated() + "");
        }

        Future<?> submit = executorService.submit(new TimerTask() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
//                          e.printStackTrace();
                    }
                    System.out.println("第二任务:");
                }
            }
        });


        /*

        TimeUnit.SECONDS.sleep(2);
        //线程池关闭后，第二个任务不会执行了
        executorService.shutdown();*/

/*        TimeUnit.SECONDS.sleep(2);
        executorService.shutdownNow();*/


       /* if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
            // 超时的时候向线程池中所有的线程发出中断(interrupted)。
            executorService.shutdownNow();
        }*/

       /* while (executorService.isShutdown()) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("线程池关闭了....");

            //只有所有的线程都执行完且线程池关闭后，此方法才会返回true
            boolean terminated = executorService.isTerminated();
            System.out.println("是否终止:" + terminated);
            if (terminated){
                break;
            }
        }*/

       /* executorService.submit(new TimerTask() {
            @Override
            public void run() {
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
//                          e.printStackTrace();
                    }
                    System.out.println("当前任务:2");
                }
            }
        });*/


    }


}
