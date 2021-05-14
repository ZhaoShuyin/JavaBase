package com.zsy.pattern.structure.composite;

import java.util.ArrayList;

/**
 * Title com.zsy.pattern.structure.composite
 * 介绍:组合模式
 * 有时又叫作部分-整体模式，它是一种将对象组合成树状的层次结构的模式，
 * 用来表示“部分-整体”的关系，使用户对单个对象和组合对象具有一致的访问性。
 *
 * @author Zsy
 * @date 2019/8/24 9:56
 */
public class CompositeTest {

    public static void main(String[] args) {
        ICom c0 = new Composite();     //树枝1
        ICom c1 = new Composite();     //树枝2
        ICom leaf1 = new Leaf("1");
        ICom leaf2 = new Leaf("2");
        ICom leaf3 = new Leaf("3");
        c0.add(leaf1);
        c0.add(c1);
        c1.add(leaf2);
        c1.add(leaf3);
        c0.operation();
    }

}

//抽象构件(Component)
interface ICom {
    public void add(ICom c);

    public void remove(ICom c);

    public ICom getChild(int i);

    public void operation();
}

//树叶构件
class Leaf implements ICom {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    public void add(ICom c) {
    }

    public void remove(ICom c) {
    }

    public ICom getChild(int i) {
        return null;
    }

    public void operation() {
        System.out.println("树叶" + name + "：被访问！");
    }
}

//树枝构件(树枝可以包含树枝)
class Composite implements ICom {
    private ArrayList<ICom> children = new ArrayList<ICom>();

    public void add(ICom c) {
        children.add(c);
    }

    public void remove(ICom c) {
        children.remove(c);
    }

    public ICom getChild(int i) {
        return children.get(i);
    }

    public void operation() {
        for (Object obj : children) {
            ((ICom) obj).operation();
        }
    }
}