package com.zsy.pattern.create.factory;

import com.zsy.pattern.create.factory.sample.Animal;
import com.zsy.pattern.create.factory.sample.Cat;
import com.zsy.pattern.create.factory.sample.Dog;

/**
 * Title com.zsy.pattern.f_factory
 * 介绍:工厂模式
 * 工厂方法模式的主要角色如下。
 * 抽象工厂（Abstract Factory）：提供了创建产品的接口，调用者通过它访问具体工厂的工厂方法 newProduct() 来创建产品。
 * 具体工厂（ConcreteFactory）：主要是实现抽象工厂中的抽象方法，完成具体产品的创建。
 * 抽象产品（Product）：定义了产品的规范，描述了产品的主要特性和功能。
 * 具体产品（ConcreteProduct）：实现了抽象产品角色所定义的接口，由具体工厂来创建，它同具体工厂之间一一对应。
 *
 * @author Zsy
 * @date 2019/8/21 22:28
 */
public class FactoryTest {

    public static void main(String[] args) {
        Animal animal = AnimalFactory.createAnimal("cat");
        animal.eat();

        animal = AnimalFactory.createAnimal("dog");
        animal.eat();
    }

}

