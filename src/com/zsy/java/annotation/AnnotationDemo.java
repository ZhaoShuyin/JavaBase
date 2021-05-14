package com.zsy.java.annotation;

/**
 * @Title Java 注解Demo
 * @date 2020/4/13
 * @autor Zsy
 */
public class AnnotationDemo {


    public static void main(String[] args) {
        AnnUse annUse = new AnnUse();

        boolean present = annUse.getClass().isAnnotationPresent(Table.class);
        System.out.println("注解: "+present);
    }

    private static void mothed(){

    }
}
