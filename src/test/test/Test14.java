package test.test;

import java.util.Arrays;

/**
 * @Title com.zsy.test
 * @date 2019/12/31
 * @autor Zsy
 */

class Test14 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(conver(new int[]{0,1,2,3,4,5},2f)));
    }

    private static int[] conver(int[] origin, float scale) {
        if (scale == 1 || scale <= 0) {
            return origin;
        }
        int number = (int) (origin.length / scale);
        int[] ints = new int[number];
        for (int i = 0; i < number; i++) {
            ints[i] = origin[(int) (i * scale)];
        }
        return ints;
    }
}
