package com.zsy.java.thread.sample;

/**
 * Title com.zsy.java.thread.sample
 *
 * @author Zsy
 * @date 2019/8/11 8:51
 */
public class MyThread extends Thread {

    public MyThread() {}

    public MyThread(String name) {
        super(name) ;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("<"+ name + "> - (" + i+")");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name+"执行完毕");
    }
}
