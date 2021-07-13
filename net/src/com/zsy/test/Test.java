package com.zsy.test;

import java.util.Arrays;

/**
 * @Title com.zsy.test
 * @date 2021/5/26
 * @autor Zsy
 */

class Test {

    // 1111 1111 1111 1111     1111 1111    1111 1000
    public static void main(String[] args) {
       /* int[] ints = new int[]{-32768, -8, 0, 1, 32767};
        byte[] bytes = new byte[ints.length * 2];
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i] + "  ====> " + Integer.toBinaryString(ints[i]));
            bytes[2 * i] = (byte) (ints[i] & 255);
            bytes[2 * i + 1] = (byte) (ints[i] >> 8);
            System.out.println("[" + bytes[2 * i] + "  , " + bytes[2 * i + 1] + "]");
            System.out.println(bytes[2 * i] + bytes[2 * i + 1] * 256);
        }
        System.out.println(Arrays.toString(bytes));*/
        byte[] bytes = new byte[120000];
        int[][] ints = new int[12][bytes.length/12/2];
    }
}
