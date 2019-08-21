package com.zsy.pattern.p_proxy;

/**
 * Title: 设计模式之代理模式
 * 介绍:  给对象增加了本地化的扩展性，增加了存取操作控制
 * 代理对象内部含有目标对象的引用，从而可以在任何时候操作目标对象；
 * 代理对象提供一个与目标对象相同的接口，以便可以在任何时候替代目标对象。
 * 代理对象通常在客户端调用传递给目标对象之前或之后，执行某个操作，而不是单纯地将调用传递给目标对象。
 *
 * 例子: Binder是Android中的一种跨进程通信方式
 *
 * @author Zsy
 * @date 2019/8/2 11:02
 */
public class F_Proxy {
    public static void main(String[] args) {
        //使用代理的对象持有实际操作对象进行操作方法
        AbstractObject obj = new ProxyObject();
        obj.operation();
    }
}


abstract class AbstractObject {
    public abstract void operation();  //操作
}

class RealObject extends AbstractObject {
    @Override
    public void operation() {
        System.out.println("一些操作");   //一些操作
    }
}

class ProxyObject extends AbstractObject {
    RealObject realObject = new RealObject();

    @Override
    public void operation() {
        System.out.println("before");    //调用目标对象之前可以做相关操作
        realObject.operation();
        System.out.println("after");     //调用目标对象之后可以做相关操作
    }
}