package com.zsy.test;

import com.zsy.java.reflect.sample.Student;

import java.util.Arrays;

/**
 * @Title com.zsy.test
 * @date 2019/12/27
 * @autor Zsy
 */

class Test13 {

    public static void main(String[] args) {
        String string = "abc";
        byte[] bytes = string.getBytes();
        System.out.println(Arrays.toString(bytes));
        String s = new String(bytes,0,2);
        System.out.println(s);
    }
}
