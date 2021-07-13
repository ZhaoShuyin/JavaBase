package com.zsy.jni;

/**
 * @Title com.zsy.jni
 * @date 2021/4/19
 * @autor Zsy
 */

public class TestJniAndJna {

    public static void main(String[] args) {
        jniTest(10000);
        System.out.println("=============================================");
        jnaTest(10000);
        System.out.println("=============================================");
//
//        treadTest();
    }

    private static void jniTest(int count) {
        long c = System.currentTimeMillis();
        System.out.println("JNI 开始============");
        System.loadLibrary("/jni/src/com/zsy/jni/JNIDemo");
        JNIDemo jniDemo = new JNIDemo("1");
        for (int i = 0; i < count; i++) {
            int add = jniDemo.add(1000, i);
            if (add != (1000 + i)) {
                System.out.println("=================================== JNI  出错 add: " + add + " ==> " + (1000 + i));
            }
//            System.out.println("计算:" + add);
        }
        System.out.println("JNI 结束==========");
        System.out.println("JNI 次数: " + count + " , 用时: " + (System.currentTimeMillis() - c));
    }

    private static void jnaTest(int count) {
        long c = System.currentTimeMillis();
        System.out.println("JNA 开始============");
        for (int i = 0; i < count; i++) {
            int add = JnaDemo.CLibrary.INSTANCE.add(1000, i);
            if (add != (1000 + i)) {
                System.out.println("=================================== JNA jniTest 出错 add: " + add + " ==> " + (1000 + i));
            }
//            System.out.println("计算:" + add);
        }
        System.out.println("JNA  结束==========");
        System.out.println("JNA  次数: " + count + " , 用时: " + (System.currentTimeMillis() - c));
    }

    private static void treadTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                jniTest(1000);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                jnaTest(1000);
            }
        }).start();
    }
}
