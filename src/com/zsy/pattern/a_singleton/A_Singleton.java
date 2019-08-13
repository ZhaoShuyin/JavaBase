package com.zsy.pattern.a_singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * 单例模式(获取唯一实例)
 *
 */
public class A_Singleton {
    public static void main(String[] args) {
        Company cp = new Company();
        Person ceo1 = CEO.getCeo();//获取唯一单例
        Person ceo2 = CEO.getCeo();//获取唯一单例
        cp.addStaff(ceo1);          //添加单例
        cp.addStaff(ceo2);          //添加单例(只添加了一个)

        Person vp1 = new VP();
        Person vp2 = new VP();

        Person staff1 = new Staff();
        Person staff2 = new Staff();

        cp.addStaff(vp1);
        cp.addStaff(vp2);
        cp.addStaff(staff1);
        cp.addStaff(staff2);

        cp.showAllStaffs();

        //通过枚举单例方法(线程安全)
        //在枚举的私有构造方法中实现唯一方法
        A_Singleton_Other.A_Singleton_OtherEnum.INSTANCE.doSomething();
    }
}


//人的基类
abstract class Person {
    public abstract void talk();
}

// 普通员工
class Staff extends Person {
    @Override
    public void talk() {
    }
}

// 副总裁
class VP extends Person {
    @Override
    public void talk() {
    }
}

// CEO， 单例模式
class CEO extends Person {

    private static final CEO mCeo = new CEO();

    private CEO() {
    }

    public static CEO getCeo() {
        return mCeo;
    }

    @Override
    public void talk() {
        System.out.println("CEO发表讲话");
    }

}

class Company {
    private List<Person> allPersons = new ArrayList<Person>();

    public void addStaff(Person per) {
        allPersons.add(per);
    }

    public void showAllStaffs() {
        for (Person per : allPersons) {
            System.out.println("Obj : " + per.toString());
        }
    }
}



