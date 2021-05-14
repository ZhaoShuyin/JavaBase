package com.zsy.java.annotation.test;

import com.zsy.java.annotation.test.MyAnnotation;

public class AnnonTestA {

    public void methodA() {
    }

    @MyAnnotation(name = "111")
    public void methodA(String a) throws Exception {
        AnnonTestB.methodB("methodA", String.class);
    }

}