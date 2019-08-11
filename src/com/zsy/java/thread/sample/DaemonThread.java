package com.zsy.java.thread.sample;

/**
 * Title 后台线程开启的仍为后台线程
 *
 * @author Zsy
 * @date 2019/8/11 9:23
 */
public class DaemonThread extends Thread {

    public DaemonThread() {}

    public DaemonThread(String name) {
        super(name) ;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("<"+Thread.currentThread().getName() + "> - (" + i+")");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
