package com.zsy.pattern.behavior.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 设计模式之迭代器模式
 * 介绍:该接口必须定义实现迭代功能的最小定义方法集比如提供hasNext()和next()方法
 * 例子:
 *
 * @author Zsy
 * @date 2019/8/2 11:10
 */
public class Iterator {

    public static void main(String[] args) {
        Aggregate ag = new ConcreteAggregate();
        ag.add("中山大学");
        ag.add("华南理工");
        ag.add("韶关学院");
        System.out.print("聚合的内容有：");
        ZIterator it = ag.getIterator();
        while (it.hasNext()) {
            Object ob = it.next();
            System.out.print(ob.toString() + "\t");
        }

        Object ob = it.first();
        System.out.println("\nFirst：" + ob.toString());
    }

}


//抽象聚合
interface Aggregate {
    public void add(Object obj);

    public void remove(Object obj);

    public ZIterator getIterator();
}

//具体聚合
class ConcreteAggregate implements Aggregate {
    private List<Object> list = new ArrayList<Object>();

    public void add(Object obj) {
        list.add(obj);
    }

    public void remove(Object obj) {
        list.remove(obj);
    }

    public ZIterator getIterator() {
        return (new ConcreteIterator(list));
    }
}

//抽象迭代器
interface ZIterator {
    Object first();

    Object next();

    boolean hasNext();
}


//具体迭代器
class ConcreteIterator implements ZIterator {
    private List<Object> list = null;
    private int index = -1;

    public ConcreteIterator(List<Object> list) {
        this.list = list;
    }

    public boolean hasNext() {
        if (index < list.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    public Object first() {
        index = 0;
        Object obj = list.get(index);
        return obj;
    }

    public Object next() {
        Object obj = null;
        if (this.hasNext()) {
            obj = list.get(++index);
        }
        return obj;
    }
}