package com.zsy.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title com.zsy.test
 * @date 2019/12/19
 * @autor Zsy
 */

class Test7 {
    public static void main(String[] args) {
        //要转换的list集合
        List<String> testList = new ArrayList<String>() {{
            add("aa");
            add("bb");
            add("cc");
        }};

        //使用toArray(T[] a)方法
        String[] array2 = testList.toArray(new String[testList.size()]);

         //打印该数组
        for (int i = 0; i < array2.length; i++) {
            System.out.println(array2[i]);
        }
    }
}
