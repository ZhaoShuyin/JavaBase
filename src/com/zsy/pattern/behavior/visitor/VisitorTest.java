package com.zsy.pattern.behavior.visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Title com.zsy.pattern.v_visiter
 * 介绍:访问者模式
 * 将作用于某种数据结构中的各元素的操作分离出来封装成独立的类，
 * 使其在不改变数据结构的前提下可以添加作用于这些元素的新的操作，为数据结构中的每个元素提供多种访问方式。
 * 它将对数据的操作与数据结构进行分离，是行为类模式中最复杂的一种模式。
 *
 * @author Zsy
 * @date 2019/8/24 0:08
 */
public class VisitorTest {

    public static void main(String[] args) {
        ObjectStructure os = new ObjectStructure();
        //添加两个元素
        os.add(new ElementA());
        os.add(new ElementB());

        Visitor visitor = new Visitor1();
        os.accept(visitor);
        System.out.println("------------------------");
        visitor = new Visitor2();
        os.accept(visitor);
    }

}


//抽象访问者
interface Visitor {
    void visit(ElementA element);

    void visit(ElementB element);
}

//具体访问者1类
class Visitor1 implements Visitor {
    public void visit(ElementA element) {
        System.out.println("具体访问者1访问-->" + element.operationA());
    }

    public void visit(ElementB element) {
        System.out.println("具体访问者1访问-->" + element.operationB());
    }
}

//具体访问者2类
class Visitor2 implements Visitor {
    public void visit(ElementA element) {
        System.out.println("具体访问者2访问-->" + element.operationA());
    }

    public void visit(ElementB element) {
        System.out.println("具体访问者2访问-->" + element.operationB());
    }
}

//抽象元素类
interface Element {
    void accept(Visitor visitor);
}

class ElementA implements Element {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationA() {
        return "具体元素A的操作。";
    }
}

class ElementB implements Element {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationB() {
        return "具体元素B的操作。";
    }
}

//对象结构角色
class ObjectStructure {
    private List<Element> list = new ArrayList<Element>();

    public void accept(Visitor visitor) {       //每个元素添加访问者
        Iterator<Element> i = list.iterator();
        while (i.hasNext()) {
            ((Element) i.next()).accept(visitor);
        }
    }

    public void add(Element element) {
        list.add(element);
    }

    public void remove(Element element) {
        list.remove(element);
    }
}