package com.zsy.jni;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Title com.zsy.jni
 * @date 2021/4/20
 * @autor Zsy
 */

public class TestThreadJNI {

    public static void main(String[] args) {

        threadTest("111",30);
        threadTest("222",60);

    }

    private static void threadTest(String name,int count) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.loadLibrary("jni/src/com/zsy/jni/JNIDemo");
                JNIDemo jniDemo = new JNIDemo(name);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println(name + "  ========================= jniDemo.stop() ");
                        jniDemo.stop();
                    }
                }, 1000 * count);
                System.out.println(name + "  ========================= jniDemo.start() ");
                jniDemo.start();
            }
        });
        thread.setName(name);
        thread.start();
    }


}
