package com.zsy.pattern.create.factory;

import com.zsy.pattern.create.factory.sample.Animal;
import com.zsy.pattern.create.factory.sample.Cat;
import com.zsy.pattern.create.factory.sample.Dog;

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