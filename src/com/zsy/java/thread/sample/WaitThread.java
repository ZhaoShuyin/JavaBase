package com.zsy.java.thread.sample;

/**
 * Title com.zsy.java.thread.sample
 *
 * @author Zsy
 * @date 2019/8/11 15:20
 */
public class WaitThread extends Thread {

    private String name;

    public WaitThread() {
    }

    public WaitThread(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10; i++) {
            if (i == 2) {
                try {
                    synchronized (WaitThread.class) {//用哪个对象锁就要用哪个去调用同步方法
                        System.out.println(this.toString()+"调用 Wait线程开始等待");
                        WaitThread.class.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
                System.out.println("<" + name + "> - (" + i + ")");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + "执行完毕");
    }

}
