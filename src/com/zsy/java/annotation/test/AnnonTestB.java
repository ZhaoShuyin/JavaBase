package com.zsy.java.annotation.test;

import java.lang.annotation.Annotation;

import java.lang.reflect.Method;

public class AnnonTestB {

    public static void methodB(String methodName, Class... parameterTypes) throws Exception {
        AnnonTestA annonTestA = new AnnonTestA();
        // 获取AnnotationTest2的Class实例
        Class c = AnnonTestA.class;
        // 获取需要处理的方法Method实例
        Method method = c.getMethod(methodName, parameterTypes);
        Method[] methods = c.getMethods();
        // 判断该方法是否包含MyAnnotation注解
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            // 获取该方法的MyAnnotation注解实例
            MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
            // 执行该方法
            //method.invoke(annonTestA, "12345");
            // 获取myAnnotation
            String value1 = myAnnotation.name();
            System.out.println(value1);
        }

        // 获取方法上的所有注解
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}