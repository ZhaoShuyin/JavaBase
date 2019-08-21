package com.zsy.pattern.h_bridge;

/**
 * Title: 设计模式之桥接模式
 * 介绍:将抽象部分与实现部分分离，使它们都可以独立的变化
 *
 * @author Zsy
 * @date 2019/8/7 13:12
 */
public class Bridge_Other {
    public static void main(String[] args) {
        Implementor imple = new ConeImplementor();
        Abstraction abs = new RefAbstraction(imple);
        abs.Operation();
    }
}

//角色接口
interface Implementor {
    public void OperationImpl();
}

//具体操作类
class ConeImplementor implements Implementor {
    public void OperationImpl() {
        System.out.println("具体实现类-->操作");
    }
}

//抽象角色类
abstract class Abstraction {
    protected Implementor imple;

    protected Abstraction(Implementor imple) {
        this.imple = imple;
    }

    public abstract void Operation();
}

//扩展抽象角色
class RefAbstraction extends Abstraction {
    protected RefAbstraction(Implementor imple) {
        super(imple);
    }

    public void Operation() {
        System.out.println("实现角色类 --> 操作");
        imple.OperationImpl();
    }
}