package test;

import java.util.Arrays;

/**
 * @Title test
 * @date 2021/7/14
 * @autor Zsy
 */

class Test4 {

    public static void main(String[] args) {
        int l = 10000;
        byte[] bytes = new byte[4];
        System.out.println(Integer.toBinaryString(l));
        bytes[0] = (byte) (l << 24);
        bytes[1] = (byte) (l << 16);
        bytes[2] = (byte) (l << 8);
        bytes[3] = (byte) (l & 255);
        System.out.println(Arrays.toString(bytes));
    }
}
