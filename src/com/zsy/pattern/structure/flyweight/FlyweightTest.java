package com.zsy.pattern.structure.flyweight;

import java.util.HashMap;

/**
 * Title com.zsy.pattern.structure.flyweight
 * 介绍:享元模式
 * 运用共享技术来有効地支持大量细粒度对象的复用。
 * 它通过共享已经存在的又橡来大幅度减少需要创建的对象数量、避免大量相似类的开销，
 * 从而提高系统资源的利用率。
 *
 * @author Zsy
 * @date 2019/8/24 9:55
 */
public class FlyweightTest {

    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        IF f01 = factory.getFlyweight("a");
        IF f02 = factory.getFlyweight("a");
        IF f03 = factory.getFlyweight("a");
        IF f11 = factory.getFlyweight("b");
        IF f12 = factory.getFlyweight("b");
        f01.operation(new Uf("第1次调用a。"));
        f02.operation(new Uf("第2次调用a。"));
        f03.operation(new Uf("第3次调用a。"));
        f11.operation(new Uf("第1次调用b。"));
        f12.operation(new Uf("第2次调用b。"));
    }

}

//非享元角色
class Uf {
    private String info;

    Uf(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

//抽象享元角色
interface IF {
    public void operation(Uf state);
}

//具体享元角色
class CIF implements IF {
    private String key;

    CIF(String key) {
        this.key = key;
        System.out.println("具体享元" + key + "被创建！");
    }

    public void operation(Uf outState) {
        System.out.print("具体享元" + key + "被调用，");
        System.out.println("非享元信息是:" + outState.getInfo());
    }
}

//享元工厂角色
class FlyweightFactory {
    private HashMap<String, IF> flyweights = new HashMap<String, IF>();

    public IF getFlyweight(String key) {
        IF flyweight = (IF) flyweights.get(key);
        if (flyweight != null) {
            System.out.println("具体享元" + key + "已经存在，被成功获取！");
        } else {
            flyweight = new CIF(key);
            flyweights.put(key, flyweight);
        }
        return flyweight;
    }
}