package com.zsy.pattern.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Title com.zsy.pattern.o_observer
 * 介绍:观察者模式
 * 指多个对象间存在一对多的依赖关系，
 * 当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
 * 这种模式有时又称作发布-订阅模式、模型-视图模式，它是对象行为型模式。
 *
 * @author Zsy
 * @date 2019/8/24 0:10
 */
public class ObserverTest {

    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer obs1 = new Observer1();
        Observer obs2 = new Observer2();
        subject.add(obs1);
        subject.add(obs2);
        subject.notifyObserver();
    }

}


//抽象目标(subscribe 订阅者) (Subject 主体)
abstract class Subject {
    protected List<Observer> observers = new ArrayList<Observer>();

    public void add(Observer observer) {
        observers.add(observer);
    }

    public void remove(Observer observer) {
        observers.remove(observer);
    }

    public abstract void notifyObserver(); //通知观察者方法
}

//具体目标(subscribe 订阅者)实现类
class ConcreteSubject extends Subject {
    public void notifyObserver() {
        System.out.println("具体目标发生改变...");
        //通知所有观察者
        for (Object obs : observers) {
            ((Observer) obs).response();
        }
    }
}

//抽象观察者
interface Observer {
    void response(); //反应
}

class Observer1 implements Observer {
    public void response() {
        System.out.println("具体观察者1作出反应！");
    }
}

class Observer2 implements Observer {
    public void response() {
        System.out.println("具体观察者2作出反应！");
    }
}