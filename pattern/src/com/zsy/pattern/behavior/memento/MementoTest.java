package com.zsy.pattern.behavior.memento;

/**
 * Title com.zsy.pattern.y_memento
 * 介绍:备忘录模式
 * 在不破坏封装性的前提下，捕获一个对象的内部状态，
 * 并在该对象之外保存这个状态，以便以后当需要时能将该对象恢复到原先保存的状态。
 * 该模式又叫快照模式。
 *
 * @author Zsy
 * @date 2019/8/24 0:06
 */
public class MementoTest {

    public static void main(String[] args) {
        Originator or = new Originator();   //发起者
        Caretaker cr = new Caretaker();     //管理者
        or.setState("S0");
        System.out.println("初始状态:" + or.getState());
        cr.setMemento(or.createMemento()); //保存状态(使用Memento对象保存状态)
        or.setState("S1");
        System.out.println("新的状态:" + or.getState());
        or.restoreMemento(cr.getMemento()); //恢复状态
        System.out.println("恢复状态:" + or.getState());
    }

}


//备忘录
class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

//发起人
class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento createMemento() {
        return new Memento(state);
    }

    public void restoreMemento(Memento m) {
        this.setState(m.getState());
    }
}

//管理者
class Caretaker {
    private Memento memento;

    public void setMemento(Memento m) {
        memento = m;
    }

    public Memento getMemento() {
        return memento;
    }
}