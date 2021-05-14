package com.zsy.java.annotation.test;

import java.lang.annotation.Retention;

import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String name();
}