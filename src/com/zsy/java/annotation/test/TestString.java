package com.zsy.java.annotation.test;

import java.io.*;

import java.lang.annotation.Annotation;

import java.lang.reflect.Method;

public class TestString {
    public static void main(String[] args) throws Exception {
        AnnonTestA annonTestA = new AnnonTestA();
        annonTestA.methodA("123");
    }

}