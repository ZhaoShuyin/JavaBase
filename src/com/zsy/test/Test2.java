package com.zsy.test;

/**
 * @Title com.zsy.test
 * @Date 2019/11/18
 * @Autor Zsy
 */
public class Test2 {


    public static void main(String[] args) {
//        int[] ints = new int[]{1, 2, 3};
        String sql = "create table " + "abc " + //" if not exists " +
                "(_id integer primary key autoincrement," +
                "account varchar(50)," +
                "password varchar(20)," +
                "type varchar(20)," +
                "stamp integer" +
                ");";
        System.out.println(sql);
    }


    static class A {
        int[] ints;
    }

}
