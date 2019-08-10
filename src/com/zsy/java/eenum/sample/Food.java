package com.zsy.java.eenum.sample;

public interface Food {
    enum Coffee implements Food{ 
        BLACK_COFFEE,DECAF_COFFEE,LATTE,CAPPUCCINO 
    } 
    enum Dessert implements Food{ 
        FRUIT, CAKE, GELATO 
    } 
}