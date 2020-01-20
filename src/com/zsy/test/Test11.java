package com.zsy.test;

import java.util.Arrays;

/**
 * @Title com.zsy.test
 * @date 2019/12/26
 * @autor Zsy
 */

class Test11 {

    public static void main(String[] args) {
        Test test = new Test();
        int[] ints1 = test.geyInts().clone();
        System.out.println(Arrays.toString(ints1));
        System.out.println(Arrays.toString(test.ints));

        test.ints[0] = 5;
        System.out.println(Arrays.toString(ints1));
    }


    public static class Test {
        int[] ints;

        public int[] geyInts() {
            ints = new int[]{1, 2, 3};
            return ints;
        }
    }

}
