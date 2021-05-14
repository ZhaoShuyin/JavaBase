package com.zsy.pattern.behavior.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Title com.zsy.pattern.u_mediator
 * 介绍:中介者模式
 * 定义一个中介对象来封装一系列对象之间的交互，
 * 使原有对象之间的耦合松散，且可以独立地改变它们之间的交互。
 * 中介者模式又叫调停模式，它是迪米特法则的典型应用
 *
 * @author Zsy
 * @date 2019/8/24 0:09
 */
public class MediatorTest {
    public static void main(String[] args) {
        IMediator md = new Mediator();
        Colleague c1, c2;
        c1 = new Colleague1();
        c2 = new Colleague2();
        md.register(c1);
        md.register(c2);
        c1.send();
        System.out.println("-------------");
        c2.send();
    }
}

//抽象中介者
abstract class IMediator {
    public abstract void register(Colleague colleague);

    public abstract void relay(Colleague cl); //转发
}

//具体中介者
class Mediator extends IMediator {
    private List<Colleague> colleagues = new ArrayList<Colleague>();

    public void register(Colleague colleague) {
        if (!colleagues.contains(colleague)) {
            colleagues.add(colleague);
            colleague.setMedium(this);
        }
    }

    public void relay(Colleague cl) {
        for (Colleague ob : colleagues) {
            if (!ob.equals(cl)) {
                ((Colleague) ob).receive();
            }
        }
    }
}

//抽象同事类
abstract class Colleague {
    protected IMediator mediator;

    public void setMedium(IMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receive();

    public abstract void send();
}

//具体同事类
class Colleague1 extends Colleague {
    public void receive() {
        System.out.println("具体同事类1收到请求。");
    }

    public void send() {
        System.out.println("具体同事类1发出请求。");
        mediator.relay(this); //请中介者转发
    }
}

//具体同事类
class Colleague2 extends Colleague {
    public void receive() {
        System.out.println("具体同事类2收到请求。");
    }

    public void send() {
        System.out.println("具体同事类2发出请求。");
        mediator.relay(this); //请中介者转发
    }
}

