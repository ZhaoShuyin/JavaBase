package com.zsy.java.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Title com.zsy.java.base
 * @date 2020/4/20
 * @autor Zsy
 */

class Test13 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Runnable[] runnables = new Runnable[2];
        for(int i = 0 ;i< 2;i++){
            int j = i;
            runnables[i] = ()->{
                while (true){
                    System.out.println("当前任务:"+j
                            +"  executorService.isShutdown(): "+executorService.isShutdown()
                            +"  executorService.isTerminated(): "+executorService.isTerminated());
                    if(executorService.isShutdown()){
                        break;
                    }else {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
//                          e.printStackTrace();
                        }
                    }
                }
            };
        }



        for(int i = 0 ;i< 2;i++){
            executorService.execute(runnables[i]);
        }

        TimeUnit.SECONDS.sleep(5);
//        executorService.shutdown();
        executorService.shutdownNow();

        while (!executorService.isShutdown()){
            System.out.println("线程池未关闭...");
        }


        while (executorService.isShutdown()){
            TimeUnit.SECONDS.sleep(1);
            System.out.println("线程池关闭了....");
            boolean terminated = executorService.isTerminated();
            System.out.println("是否终止:"+ terminated);
            if (terminated){
                break;
            }
        }





    }


}
