package com.zsy.jni;

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

    public static void main(String[] args) {
        System.loadLibrary("src/com/zsy/jni/JNIDemo");
        JNIDemo jniDemo = new JNIDemo();
        String s = jniDemo.testHello(String.valueOf(System.currentTimeMillis()));
        System.out.println("返回==> " + s);

        System.out.println("1+1= " + jniDemo.add(1, 1));
        System.out.println("6-1= " + jniDemo.subtract(6, 1));
        System.out.println("2*3= " + jniDemo.multiply(2, 3));
        System.out.println("10/5= " + jniDemo.divide(10, 5));


    }

}
