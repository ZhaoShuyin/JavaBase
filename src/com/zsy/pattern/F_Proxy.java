package com.zsy.pattern;

/**
 * Title: 设计模式之代理模式
 * 介绍:
 * 例子:
 * @author Zsy
 * @date 2019/8/2 11:02
 */
public class F_Proxy {
    public static void main(String[] args) {
        AbstractObject obj = new ProxyObject();
        obj.operation();
    }
}


abstract class AbstractObject {
    //操作
    public abstract void operation();
}

class RealObject extends AbstractObject {
    @Override
    public void operation() {
        //一些操作
        System.out.println("一些操作");
    }
}

class ProxyObject extends AbstractObject{
    RealObject realObject = new RealObject();
    @Override
    public void operation() {
        //调用目标对象之前可以做相关操作
        System.out.println("before");
        realObject.operation();
        //调用目标对象之后可以做相关操作
        System.out.println("after");
    }
}