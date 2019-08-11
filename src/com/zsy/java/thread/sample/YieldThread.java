package com.zsy.java.thread.sample;

/**
 * Title 礼让线程
 *
 * @author Zsy
 * @date 2019/8/11 9:15
 */
public class YieldThread extends Thread{
    public YieldThread() {}

    public YieldThread(String name) {
        super(name) ;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                if (i>1){//当执行1个以后,可以给其他线程让步了
                   yield();
                }
                System.out.println("<"+Thread.currentThread().getName() + "> - (" + i+")");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
