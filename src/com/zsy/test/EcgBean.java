package com.zsy.test;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * @Title com.pengyang.ecg.bean.wave
 * @date 2019/11/4
 * @autor Zsy
 */
public class EcgBean {


    public static void main(String[] args) {
        /*int[] convers = convers(new int[]{1, 2, 3, 4, 5, 6}, 2);
        System.out.println(Arrays.toString(convers));*/

        float price = 0.153898f;
        DecimalFormat decimalFormat = new DecimalFormat("0.00%");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p = decimalFormat.format(price );
        System.out.println(p);
    }

    private static int[] convers(int[] origin, int number) {
        int[] ints = new int[origin.length / number];
        for (int j = 0; j < ints.length; j++) {
            ints[j] = getTotal(origin, j, number);
        }
        return ints;
    }

    private static int getTotal(int[] ints, int start, int number) {
        int total = 0;
        for (int i = 0; i < number; i++) {
            total += ints[i + start * number];
        }
        return total / number;
    }


}
