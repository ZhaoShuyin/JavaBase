package com.zsy.jni;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Title com.zsy.jni
 * @date 2021/4/19
 * @autor Zsy
 */

public class JNIDemo {

    public native String testHello(String string);

    public native int add(int a, int b);

    public native int subtract(int a, int b);

    public native int multiply(int a, int b);

    public native int divide(int a, int b);

    public native void start();

    public native void stop();

    private String name;

    public JNIDemo(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        System.out.println(name+" C ==> "+value);
    }

    public static void main(String[] args) {
        System.loadLibrary("src/com/zsy/jni/JniDll");
        JNIDemo jniDemo = new JNIDemo("11");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("========================= jniDemo.stop() ");
                jniDemo.stop();
            }
        },1000*60);
        System.out.println("========================= jniDemo.start() ");
        jniDemo.start();
    }

}
