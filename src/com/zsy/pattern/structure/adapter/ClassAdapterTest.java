package com.zsy.pattern.structure.adapter;

/**
 * Title com.zsy.pattern.h_adapter
 * 介绍:将一个类的接口转换成客户希望的另外一个接口，使得原本由于接口不兼容而不能一起工作的那些类能一起工作。
 *
 * @author Zsy
 * @date 2019/8/23 23:36
 */
public class ClassAdapterTest {

    public static void main(String[] args) {
//        System.out.println("类适配器模式测试：");
//        Target target = new ClassAdapter();
//        target.request();

        System.out.println("对象适配器模式测试：");
        Adaptee adaptee = new Adaptee();
        Target target = new ObjectAdapter(adaptee);
        target.request();

    }

}


//目标接口
interface Target {
    public void request();
}

//适配者接口
class Adaptee {
    public void specificRequest() {
        System.out.println("适配者中的业务代码被调用！");
    }
}

//类适配器类
class ClassAdapter extends Adaptee implements Target {
    public void request() {
        specificRequest();
    }
}


//对象适配器类
class ObjectAdapter implements Target {
    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void request() {
        adaptee.specificRequest();
    }
}