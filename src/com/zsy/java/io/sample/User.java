package com.zsy.java.io.sample;

/**
 * Title 随机访问流使用Bean
 *
 * @author Zsy
 * @date 2019/8/10 22:44
 */
public class User {
    public String name;
    public int age;

    final static int LEN = 8;

    /**
     * 名字固定为8字节
     * @param name
     * @param age
     */
    public User(String name, int age) {
        if (name.length() > LEN) {
            name = name.substring(0, 8);
        } else {
            while (name.length() < LEN) {
                name = name + "\u0000";
            }
            this.name = name;
            this.age = age;
        }
    }
}
