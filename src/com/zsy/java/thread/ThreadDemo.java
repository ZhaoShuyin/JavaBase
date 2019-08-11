package com.zsy.java.thread;

import com.zsy.java.thread.sample.DaemonThread;
import com.zsy.java.thread.sample.MyThread;
import com.zsy.java.thread.sample.WaitThread;
import com.zsy.java.thread.sample.YieldThread;

/**
 * Title 线程的基本使用
 *
 * @author Zsy
 * @date 2019/8/11 8:35
 */
public class ThreadDemo {

    public static void main(String[] args) {
        //priorityTest();//线程优先级
        //joinTest();    //优先线程
        //yieldTest();   //礼让线程
        //daemonTest();  //后台线程
        //waitNotifyTest();//等待和继续线程
        //InterruptTest(); //中断阻塞状态
    }

    /**
     * Interrupt中断线程的阻塞状态
     */
    private static void InterruptTest() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("阻塞线程开始等待20秒");
                    Thread.sleep(20000);
                    System.out.println("阻塞线程执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("阻塞线程");
        thread.start();
        try {
            System.out.println("主线程3秒后开始中断阻塞状态");
            Thread.sleep(3000);
            System.out.println("---------");
            System.out.println("主线程中断新城阻塞状态");
            thread.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * wait()和notify()必须使用同一个对象锁调用
     */
    private static void waitNotifyTest() {
        WaitThread t = new WaitThread("等待线程");
        t.start();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程 <" + (9 - i) + ">秒后唤醒wait线程");
        }
        synchronized (WaitThread.class) {               //必须使用同一个锁对象去调用
            System.out.println(t.toString() + " 去调用继续执行子线程");
            WaitThread.class.notify();
        }
    }

    /**
     * 后台线程(守护线程)伴随主线程执行
     */
    private static void daemonTest() {
        DaemonThread d1 = new DaemonThread("后台1");
        DaemonThread d2 = new DaemonThread("后台2");
        d1.setDaemon(true);
        d2.setDaemon(true);
        d1.start();
        d2.start();
        Thread.currentThread().setName("主线程");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("<" + Thread.currentThread().getName() + "> - (" + i + ")");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *礼让线程
     */
    private static void yieldTest() {
        YieldThread t1 = new YieldThread("让步");
        MyThread t2 = new MyThread("普通1");
        MyThread t3 = new MyThread("普通2");
        t1.start();
        t2.start();
        t3.start();
    }

    /**
     * join(): 等待该线程执行完毕以后,在执行其他线程
     * 调用时之后的在开启的线程等待本线程执行完毕
     */
    private static void joinTest() {
        MyThread t1 = new MyThread("重要");
        MyThread t2 = new MyThread("次要1");
        MyThread t3 = new MyThread("次要2");
        t1.start();
        t2.start();
        try {
            t1.join();//线程执行完毕,再执行其他线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t3.start();
    }

    /**
     * 优先级范围 1~10 (大概率优先执行)
     */
    private static void priorityTest() {
        MyThread t1 = new MyThread("呵呵");
        MyThread t2 = new MyThread("哈哈");
        MyThread t3 = new MyThread("嘿嘿");
        t1.setPriority(1);
        t2.setPriority(2);
        t3.setPriority(3);
        t1.start();
        t2.start();
        t3.start();
    }


}
