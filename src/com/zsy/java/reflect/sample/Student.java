package com.zsy.java.reflect.sample;

public class Student {
    public String name;     //姓名
    public int age;         //年龄
    private int ranking;    //成绩

    public Student() {
        System.out.println("Student 无参的构造方法");
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Student 私有的构造方法 name =" + name + ", age =" + age);
    }

    public Student(String name, int age, int ranking) {
        this.name = name;
        this.age = age;
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    private int getRank() {
        return ranking;
    }

    @Override
    public String toString() {
        return "{name:" + name + ",age:" + age + ",ranking:" + ranking + "}";
    }
}
