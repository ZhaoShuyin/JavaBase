package com.zsy.test;

import java.util.Arrays;

/**
 * @Title com.zsy.test
 * @date 2020/1/2
 * @autor Zsy
 */

class Test15 {

    public static void main(String[] args) {
       /* int index = 0;
        while (index < 6) {
            for (int i = 0; i < 12 ; ++i) {
                System.out.println("index:"+index+"  i:"+i+ " == "+(12 * index + i));
            }
            ++index;
        }*/

        float[][] floats = new float[2][2];
        floats[0][0] = 1f;
        floats[0][1] = 2f;
        System.out.println(Arrays.toString(floats[0]));
    }
}
