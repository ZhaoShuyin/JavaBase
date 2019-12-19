package com.zsy.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title com.zsy.test
 * @date 2019/12/13
 * @autor Zsy
 */

class Test6 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张三0");
        list.add("李四1");
        list.add("王五2");

        System.out.println(list);

        list.set(1,"替换");

        System.out.println(list);
    }

}
