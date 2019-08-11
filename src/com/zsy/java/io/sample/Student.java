package com.zsy.java.io.sample;

import java.io.Serializable;

/**
 * 一个对象是否能被序列化,要求这个对象必须实现Serializable接口,如果没有实现这个接口,那么这个对象就不能被序列化
 * 如果一个接口没有抽象方法,那么这样的接口,我们就将其称之为标记型接口
 * <p>
 * java.io.InvalidClassException: cn.itcast01.Student;
 * local class incompatible: stream classdesc serialVersionUID = -2868429640530506011,
 * local class serialVersionUID = -6492778245152073895
 * <p>
 * 当一个类实现了Serializable这个接口以后,那么就相当于给该类打了一个标记 ; 那么就会生成一个 serialVersionUID对其进行标识.
 * 当我们修改了该类以后,这个标识也发生了改变,那么在进行反序列化的时候,它会验证两个serialVersionUID是否相同,如果不同,就报错了.
 * <p>
 * 怎么处理这个问题呢?  保证serialVersionUID是相同的. 怎么 保证相同呢?  自动生成
 * <p>
 * transient: 如果一个类的某一个成员不想被序列化,那么我们就可以使用该关键字进行修饰
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 1151508000462830305L;

    private String name;//transient关键字为不参与序列化

    private int age;

    public Student(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public Student() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
