package com.zsy.pattern.f_factory;

import com.zsy.pattern.f_factory.sample.Animal;
import com.zsy.pattern.f_factory.sample.Cat;
import com.zsy.pattern.f_factory.sample.Dog;

/**
 * Title com.zsy.pattern.f_factory
 * 介绍:工厂模式
 *
 * @author Zsy
 * @date 2019/8/21 22:28
 */
public class Factory {

    public static void main(String[] args) {
        Animal animal = AnimalFactory.createAnimal("cat") ;
        animal.eat() ;

        animal = AnimalFactory.createAnimal("dog") ;
        animal.eat() ;
    }

}

class AnimalFactory {
    public static Animal createAnimal(String animalType) {
        if ("cat".equals(animalType)) {
            return new Cat();
        } else if ("dog".equals(animalType)) {
            return new Dog();
        } else {
            return null;
        }
    }
}