package com.zsy.java.base;

/**
 * Title com.zsy.java.base
 * <p>
 * (进制显示)https://blog.csdn.net/makingadream/article/details/53100237
 *
 * @author Zsy
 * @date 2019/8/21 21:50
 */
public class BaseDemo {

    public static void main(String[] args) {
        int a = 0;
        int b = 2;
        int c = 16;
        int d = -2;
        System.out.println(a + " 二进制 :" + toBinaryString(a));
        System.out.println(b + " 二进制 :" + toBinaryString(b));
        System.out.println(c + " 二进制 :" + toBinaryString(c));
        System.out.println(d + " 二进制 :" + toBinaryString(d));

        System.out.println(a + " 八进制 :" + toHexString(a));
        System.out.println(b + " 八进制 :" + toHexString(b));
        System.out.println(c + " 八进制 :" + toHexString(c));
        System.out.println(d + " 八进制 :" + toHexString(d));

        System.out.println("=========================");
        int h = 2;
        int i = 0;
        System.out.println(toBinaryString(i) + " - " + i);
        i = h >> 1;              //>>表示右移，如果该数为正，则高位补0，若为负数，则高位补1；
        System.out.println(toBinaryString(i) + " - " + i);
        i = h >>> 1;            //>>>表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0
        System.out.println(toBinaryString(i) + " - " + i);

        System.out.println("=========================");
        int x = 1;
        int y = 2;
        System.out.println("原   x=" + toBinaryString(x) + " y=" + toBinaryString(y));
        x = x ^ y;  //01 ^ 10 = 11
        System.out.println("异或 x=" + toBinaryString(x) + " y=" + toBinaryString(y));
        y = x ^ y;  //11 ^ 10 = 01
        System.out.println("异或 x=" + toBinaryString(x) + " y=" + toBinaryString(y));
        x = x ^ y;  //11 ^ 01 = 10
        System.out.println("异或 x=" + x + " y=" + y);
    }


    /**
     * int 以二进制 显示
     */
    public static String toBinaryString(int i) {
        return Integer.toBinaryString(i);
    }

    /**
     * int 以二进制 显示
     */
    public static String toHexString(int i) {
        return Integer.toHexString(i);
    }

}
