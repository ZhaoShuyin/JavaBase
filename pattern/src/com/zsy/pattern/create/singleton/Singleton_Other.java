package com.zsy.pattern.create.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例模式的其他方式
 */
public class Singleton_Other {
    private static Singleton_Other mInstance = null;

    private Singleton_Other() {
    }

    public void doSomething() {
        System.out.println("do (Singleton_Other) sth.");
    }

    /**
     * 方式二、double-check， 避免并发时创建了多个实例, 该方式不能完全避免并发带来的破坏.
     */
    public static Singleton_Other getInstance() {
        if (mInstance == null) {
            synchronized (Singleton_Other.class) {//使用线程锁
                if (mInstance == null) {
                    mInstance = new Singleton_Other();
                }
            }
        }
        return mInstance;
    }

    /**
     * 方式三 : 在第一次加载Singleton_OtherHolder时初始化一次mOnlyInstance对象, 保证唯一性, 也延迟了单例的实例化,
     * 如果该单例比较耗资源可以使用这种模式.
     */
    public static Singleton_Other getInstanceFromHolder() {
        return Singleton_OtherHolder.mOnlyInstance;
    }

    /**
     * 静态内部类
     */
    private static class Singleton_OtherHolder {
        private static final Singleton_Other mOnlyInstance = new Singleton_Other();
    }

    /**
     * 方式四 : 枚举单例, 线程安全
     * 枚举的 INSTANCE 作为唯一单例
     */
    enum Singleton_OtherEnum {
        INSTANCE;
        public void doSomething() {
            System.out.println("do sth.");
        }
    }

    /**
     * 方式五 : 注册到容器, 根据key获取对象.一般都会有多种相同属性类型的对象会注册到一个map中
     * instance容器
     */
    private static Map<String, Singleton_Other> objMap = new HashMap<String, Singleton_Other>();

    //注册对象到map中
    public static void registerService(String key, Singleton_Other instance) {
        if (!objMap.containsKey(key)) {
            objMap.put(key, instance);
        }
    }

    //根据key获取对象
    public static Singleton_Other getService(String key) {
        return objMap.get(key);
    }

}