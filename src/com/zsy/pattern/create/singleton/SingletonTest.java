package com.zsy.pattern.create.singleton;

/**
 * 单例模式(获取唯一实例)
 */
public class SingletonTest {
    public static void main(String[] args) {

        //多线程单例测试
        new Thread(new Runnable() {
            @Override
            public void run() {
                Instance instance = Instance.getInstance();
                System.out.println("线程1 单例 "+instance.toString());
            }
        }){}.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Instance instance = Instance.getInstance();
                System.out.println("线程2 单例 "+instance.toString());
            }
        }){}.start();

        //通过枚举单例方法(线程安全)
        //在枚举的私有构造方法中实现唯一方法
        Singleton_Other.Singleton_OtherEnum.INSTANCE.doSomething();
    }
}





